package javaweb.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/* 
 * 執行網址：http://localhost:8080/JavawebTest/bmi?h=170&w=60
 * ? : 帶入參數 (得到BMI的資料)
 
 */

@WebServlet("/bmi")
public class BMIServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 取得 ? 後參數
		String h = req.getParameter("h");  // getParameter() 從 HTTP 請求中獲取用戶輸入或數據參數
		String w = req.getParameter("w");
		
		// 檢查參數
		if(h==null||w==null) {
			req.setAttribute("message","請輸入身高與體重");
			req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
			return;
		}
		
		// 計算 BMI
		double height = Double.parseDouble(h);
		double weight = Double.parseDouble(w);
		double bmi = weight / Math.pow(height/100, 2);
		
		/*
	    double height = 170 ;
		double weight = 60 ;
		double bmi = weight / Math.pow(height/100, 2);
		*/
		
		
		// 將資料傳給 JSP
		req.setAttribute("height", height);
		req.setAttribute("weight", weight);
		req.setAttribute("bmi", bmi);
		
		
		// 導向 JSP 印出資料
		req.getRequestDispatcher("/WEB-INF/view/bmi.jsp").forward(req, resp);
		
	}
	
	
}
