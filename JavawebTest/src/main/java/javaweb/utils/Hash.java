package javaweb.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class Hash {
	
	// 產生雜湊

	public static String getHash(String password) {
		try {

			// 加密演算法：SHA-256
			MessageDigest md = MessageDigest.getInstance("SHA-256"); // MessageDigest 用於生成數據的哈希值(摘要)
																	 // getInstance("algorithm") 獲取指定算法的 MessageDigest 實例
			// 進行加密
			byte[] bytes = md.digest(password.getBytes()); // digest(byte[])：計算並返回 HashCode ，並重置 MessageDigest 以便進行新的計算

			// 將byte [] 透過 Base64 編碼方便儲存
			return Base64.getEncoder().encodeToString(bytes); 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

    /*--------------------------------------------------------------------------------------------------*/

	// 產生鹽

	public static String getSalt() {
		
		// SecureRandom 加密強隨機數生成器
		SecureRandom sr = new SecureRandom(); 
		
		// 設置鹽的長度 ( byte[] )
		byte[] salt = new byte[16];   
		sr.nextBytes(salt);           // nextBytes 將隨機數填入
		
		// Base64 編碼轉為 String
		return Base64.getEncoder().encodeToString(salt);  
	}

	// 產生含鹽雜湊

	public static String getHash(String password, String salt) {
		try {

			// 加密演算法：SHA-256
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			// 加鹽
			md.update(salt.getBytes());                       // update(salt)：將鹽加入計算

			// 進行加密
			byte[] bytes = md.digest(password.getBytes());    // digest()：進行 Hash 計算

			// Base64 編碼轉為 String
			return Base64.getEncoder().encodeToString(bytes);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
