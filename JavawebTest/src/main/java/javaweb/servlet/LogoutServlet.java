package javaweb.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 登出 -> 將 session 取消

@WebServlet("/logout")                               
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		session.invalidate();                     // 所有 session 失效 ( sessionId 重發 )
//      session.setAttribute("userCert", null);   // 指定 session 失效 ( sessionId 不會重發 )
		
		String message = "登出成功";
		req.setAttribute("message", message);
		req.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(req, resp);
		
	}
	
}
