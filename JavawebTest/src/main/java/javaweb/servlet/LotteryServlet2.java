package javaweb.servlet;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

	/* 模式：Model 1
	   Servlet 負責邏輯
	   JSP 負責資料呈現
	   
	   連結方式：Get
	   執行位置：http://localhost:8080/JavawebTest/lottery2
	*/

@WebServlet(urlPatterns = {"/lottery2"})
public class LotteryServlet2 extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 產生電腦選號 ( 四星彩 )
		Random random = new Random();
		int n1 = random.nextInt(10); // 0~9 隨機數 
		int n2 = random.nextInt(10);
		int n3 = random.nextInt(10);
		int n4 = random.nextInt(10);

		// 利用 req.setAttribute() 傳遞要呈現的資料給 JSP 
		req.setAttribute("n1", n1);
		req.setAttribute("n2", n2);
		req.setAttribute("n3", n3);
		req.setAttribute("n4", n4);

		// 利用 RequestDispatcher 導向到指定 JSP
		req.getRequestDispatcher("/WEB-INF/view/lottery2.jsp").forward(req, resp);
 
		// .RequestDispatcher("WEB-INF資料夾JSP位置")
		// .forward(req, resp); 
	}
	
	
	
}
