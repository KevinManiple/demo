package utils;

public class AESDemo {

	public static void main(String[] args) {
		// String passphrase = request.getParameter("passphrase");
		// int iterationCount = Integer.parseInt(request.getParameter("iterationCount"));
		// int keySize = Integer.parseInt(request.getParameter("keySize"));
		// String salt = request.getParameter("salt");
		// String ciphertext = request.getParameter("ciphertext");
		// String iv = request.getParameter("iv");

		String passphrase = "ahdfsdfsdfhs23424";
		int iterationCount = 1000;
		int keySize = 128;
		String salt = "8b55c569517708eb2e405686b5e98466";
		String iv = "f6cf44ec99f5742365f5b096c06f0077";
		String ciphertext = "iX/NraNL7mKA5zCzZZ1vVg==";
		String plain = "abc134";
		AesUtil aesUtil = new AesUtil(keySize, iterationCount);
		System.out.println(aesUtil.encrypt(salt, iv, passphrase, plain));
		String plaintext = aesUtil.decrypt(salt, iv, passphrase, ciphertext);
		System.out.println(plaintext);
	}
}
