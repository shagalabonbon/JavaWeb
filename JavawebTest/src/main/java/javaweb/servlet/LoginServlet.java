package javaweb.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javaweb.exception.CertException;
import javaweb.model.dto.UserCert;
import javaweb.service.CertService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet  {  
	
	CertService certService = new CertService();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);     // 重導至 login.jsp ( 點擊網址時 )
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");  
		String password = req.getParameter("password");
		
		
		// 取得憑證 ( 驗證帳密 ) ----------------------------------------------------------
		
		UserCert userCert = null;
		
		try {
			
			userCert = certService.getCert(username,password);  // 從 CertService 取得憑證
			
		}catch(CertException e) {
			
			req.setAttribute("message", e.getMessage());          
			req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);  // 錯誤資訊移至 error.jsp 顯示
			return;
		
		}
		
		
		// 將憑證放入 Session ( 方便其他 Servlet 進行取用與驗證 ) ---------------------------
		
		HttpSession session = req.getSession();     // 獲取 HTTP Session 
		
		session.setAttribute("userCert",userCert);  // 將憑證設為 Session 的屬性 
		
		session.setAttribute("locale", req.getLocale()); // 可將其他需要的資訊也設成 Session 屬性 ( 地區 )  
		
		req.setAttribute("message", "登入成功"); 
		
  
		// 檢查 Session 中的 URL 是否有資料 ( LoginFilter 在未登入時會儲存請求網址 ) ---------
		
		if(session.getAttribute("redirectURL")==null) {
			
			req.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(req, resp);  
			
		}else {
			
			String redirectURL = session.getAttribute("redirectURL").toString();  // 取得網址
			
			resp.sendRedirect(redirectURL);          // 引導至 Session 中儲存的網址
			
			req.setAttribute("redirectURL", null);   // 清空 redirectURL ( 一次性跳轉，之後可繼續儲存其他網址 )
		}
		
		 
	}
	
}
