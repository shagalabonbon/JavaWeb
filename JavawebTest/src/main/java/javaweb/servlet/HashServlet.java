package javaweb.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static javaweb.utils.Hash.getHash;
import static javaweb.utils.Hash.getSalt;   


/*
 *  網址： /hash?password=1234
 *  得到： 
 *      1. hashpassword [不含鹽]
 *      
 *      2. salt + hashpassword ( password+salt ) [含鹽] 
 */

@WebServlet("/hash")
public class HashServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password = req.getParameter("password");
		String hash = getHash(password);
		resp.getWriter().println("hash:"+hash);
		String salt = getSalt();
		resp.getWriter().println("salt:"+salt);
		String hashSalt = getHash(password, salt);
		resp.getWriter().println("hashSalt:"+hashSalt);
	}
	
	
	
	
	
	
}


// Base64 是一種將二進位數據編碼為 ASCII 字符串的編碼方式


/* 
 * Random、SecureRandom
 * 
 * Random：可藉由大量數據推測數值
 * 
 * SecureRandom：無法藉由數學邏輯推測數值
 */

   


