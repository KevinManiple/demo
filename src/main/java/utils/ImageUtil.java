package utils;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageUtil {

	/**
	 * 压缩图片
	 * 
	 * @param im
	 * @param toWidth
	 * @param toHeight
	 * @return
	 * @see
	 */
	private static BufferedImage zoomImage(final BufferedImage im, final int toWidth, final int toHeight)
			throws Exception {
		BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
		result.getGraphics()
				.drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
		return result;
	}

	/**
	 * 获取图片
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 * @see
	 */
	private static BufferedImage getImage(final String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		InputStream inStream = conn.getInputStream();
		return ImageIO.read(inStream);
	}

	/**
	 * 压缩图片
	 * 
	 * @param path
	 * @param toWidth
	 * @param toHeight
	 * @return
	 * @throws Exception
	 * @see
	 */
	public static BufferedImage resize(final String path, final int toWidth, final int toHeight) throws Exception {
		return zoomImage(getImage(path), toWidth, toHeight);
	}

}
