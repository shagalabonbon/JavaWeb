package javaweb.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/fruit/*","/users","/user/update/password","/order/*","/cart"})    // @ WebFilter 設定要攔截的路徑
public class LoginFilter extends HttpFilter {
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("攔截過濾："+request.getRequestURI());          // getRequestURL 用於獲取當前請求的完整 URL
		System.out.println("攔截過濾："+request.getRequestURL());          // 攔截 URL ：http://localhost:8080/JavawebTest/users

		
		// 判斷是否有憑證 --------------------------------------------
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userCert")==null){                        // 表示尚未登入，無憑證
			
			session.setAttribute("redirectURL", request.getRequestURL());  // 將請求的 URL 儲存於 Session 中，以便登入後能重新前往
			
			response.sendRedirect("/JavawebTest/login");                   // 引導至登入畫面 ( response.sendRedirect )
			
		}else{
			
			chain.doFilter(request, response);                             // chain.doFilter： 將請求傳遞到下一個 Filter 或 Servlet
			
		}
	}
	
}




/* 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=utf-8");
 */