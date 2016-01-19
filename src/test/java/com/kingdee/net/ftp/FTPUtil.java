package com.kingdee.net.ftp;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * TFP 工具类
 * 
 * @author Kai.Zhao
 * @version 1.0
 * @see
 */
public class FTPUtil {

	public static void main(String[] args) {
		/*try {
			FTPUtil.getInstance().uploadFile(
					new File("C:\\cmbc\\18675559296\\cDRwMWx2ajY2ZDlqYzVzbThib2l0ajJvZGs=.png"), "cmbc/18675559296",
					"1.png");
			;
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		test();
	}

	private static void showServerReply(final FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String aReply : replies) {
				System.out.println("SERVER: " + aReply);
			}
		}
	}

	public static void test() {

		String server = "172.20.10.48";
		int port = 21;
		String user = "kevin";
		String pass = "kevin";
		String userID = "cmbc/18675559296";

		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(server, port);
			showServerReply(ftpClient);
			//			showServerReply();
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("Operation failed. Server reply code: " + replyCode);
				return;
			}
			boolean success = ftpClient.login(user, pass);
			showServerReply(ftpClient);
			//			showServerReply();
			if (!success) {
				System.out.println("Could not login to the server");
				return;
			}
			ftpClient.enterLocalPassiveMode();
			showServerReply(ftpClient);

			success = ftpClient.changeWorkingDirectory(userID);
			showServerReply(ftpClient);
			//			showServerReply();
			if (success) {
				System.out.println("Successfully created directory: " + userID);
			} else {
				System.out.println("Failed to create directory. See server's reply.");
			}

			// ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			// APPROACH #1: uploads first file using an InputStream
			String firstRemoteFile = "3.png";
			File firstLocalFile = new File("C:\\Users\\Administrator\\Pictures\\3.png");
//			InputStream inputStream = new FileInputStream(firstLocalFile);
			
			BufferedImage image =ImageIO.read(firstLocalFile);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", os);
			InputStream inputStream = new ByteArrayInputStream(os.toByteArray());

			System.out.println("Start uploading first file");
			boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
			showServerReply(ftpClient);
			inputStream.close();
			if (done) {
				System.out.println("The first file is uploaded successfully.");
			}

			File secondLocalFile = new File("C:\\Users\\Administrator\\Pictures\\filetransfer.jpg");
			String secondRemoteFile = "2.jpg";
			inputStream = new FileInputStream(secondLocalFile);
			System.out.println("Start uploading second file");
			done = ftpClient.storeFile(secondRemoteFile, inputStream);
			showServerReply(ftpClient);
			inputStream.close();
			if (done) {
				System.out.println("The second file is uploaded successfully.");
			}

			// APPROACH #2: uploads second file using an OutputStream
			//			File secondLocalFile = new File("C:\\cmbc\\18675559296\\M3VvYTcxN2w5djVuazY1ZmxlZDZrbWV2ZDY=.png");
			//			String secondRemoteFile = "cmbc/" + userID + "/" + "M3VvYTcxN2w5djVuazY1ZmxlZDZrbWV2ZDY=.png";
			//			inputStream = new FileInputStream(secondLocalFile);
			//
			//			System.out.println("Start uploading second file");
			//			OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
			//			byte[] bytesIn = new byte[4096];
			//			int read = 0;
			//
			//			while ((read = inputStream.read(bytesIn)) != -1) {
			//				outputStream.write(bytesIn, 0, read);
			//			}
			//			inputStream.close();
			//			outputStream.close();

			showServerReply(ftpClient);

		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					showServerReply(ftpClient);
					ftpClient.disconnect();
					showServerReply(ftpClient);
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
