package encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.security.NoSuchAlgorithmException;

import cn.egame.common.util.Utils;

public class FileEncryptAndDecrypt {
	public static final int ENCRYPT_SIZE = 20;
	public static final String APK_KEY = "{&$%!5(h^#$<!#c*?@6@";

	public static void encrypt(String fileUrl, String key) {
		try {
			File file = new File(fileUrl);
			if (!file.exists()) {
				return;
			}
			byte[] KEYVALUE = key.getBytes();
			RandomAccessFile randomFile = new RandomAccessFile(fileUrl, "rw");
			InputStream in = new FileInputStream(fileUrl);
			byte[] buffer = new byte[ENCRYPT_SIZE];
			int r, pos, keylen;
			pos = 0;
			keylen = KEYVALUE.length;
			// first section encrypt
			if ((r = in.read(buffer)) > 0) {
				for (int i = 0; i < r; i++) {
					buffer[i] ^= KEYVALUE[pos];
					if (pos == keylen)
						pos = 0;
				}
				randomFile.write(buffer);
			}
			in.close();
			randomFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void decrypt(String fileUrl, String key) {
		try {
			File file = new File(fileUrl);
			if (!file.exists()) {
				return;
			}
			byte[] KEYVALUE = key.getBytes();
			RandomAccessFile randomFile = new RandomAccessFile(fileUrl, "rw");
			InputStream in = new FileInputStream(fileUrl);
			byte[] buffer = new byte[ENCRYPT_SIZE];
			int r, pos, keylen;
			pos = 0;
			keylen = KEYVALUE.length;
			// first section decrypt
			if ((r = in.read(buffer)) > 0) {
				for (int i = 0; i < r; i++) {
					buffer[i] ^= KEYVALUE[pos];
					if (pos == keylen)
						pos = 0;
				}
				randomFile.write(buffer);
			}
			in.close();
			randomFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isEncrypted(String fileUrl) {
		boolean isEncrypted = false;
		try {
			File file = new File(fileUrl);
			if (!file.exists()) {
				isEncrypted = false;
			}
			InputStream in = new FileInputStream(fileUrl);
			byte[] buffer = new byte[4];
			int r;
			if ((r = in.read(buffer)) > 0) {
				if (buffer[0] == 0x50 && buffer[1] == 0x4B && buffer[2] == 0x03
						&& buffer[3] == 0x04) {
					isEncrypted = false;
				} else {
					isEncrypted = true;
				}

			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isEncrypted;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		long startTime = System.currentTimeMillis();
		System.out.println(encrypt1("e://5018218_1.apk", APK_KEY));
		System.out.println("----"+(System.currentTimeMillis() - startTime)+"ms");
		System.out.println(Utils.encryptMD5("e://5018218_1.apk"));
	}

	public static  String encrypt1(String fileUrl, String key) {
		InputStream in = null;
		try {
			File file = new File(fileUrl);
			if (!file.exists()) {
				return null;
			}
			byte[] KEYVALUE = key.getBytes();
			in = new FileInputStream(fileUrl);
			int r, pos;
			pos = 0;
			byte[] buffer1 = new byte[in.available()];
			if ((r = in.read(buffer1)) > 0) {
				for (int i = 0; i < r; i++) {
//					buffer1[i] ^= KEYVALUE[pos];
				}
			}
			return byteToHexString(buffer1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static String byteToHexString(byte[] tmp) {
		String s;
		// ????????????????????? 16 ?????????
		char str[] = new char[16 * 2]; // ??????????????? 16 ??????????????????????????????????????????
		// ??????????????? 16 ???????????? 32 ?????????
		int k = 0; // ??????????????????????????????????????????
		for (int i = 0; i < 16; i++) { // ?????????????????????????????? MD5 ??????????????????
			// ????????? 16 ?????????????????????
			byte byte0 = tmp[i]; // ?????? i ?????????
			str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // ??????????????? 4 ??????????????????,
			// >>> ??????????????????????????????????????????
			str[k++] = hexDigits[byte0 & 0xf]; // ??????????????? 4 ??????????????????
		}
		s = new String(str); // ?????????????????????????????????
		return s;
	}

	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
}
