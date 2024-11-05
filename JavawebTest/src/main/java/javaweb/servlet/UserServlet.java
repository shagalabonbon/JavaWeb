package javaweb.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javaweb.model.dto.UserCert;
import javaweb.model.dto.UserDto;
import javaweb.service.UserService;

/**

MVC + 自訂框架
 
 request   +-------------+          +-------------+          +---------+
---------> | UserServlet | -------> | UserService | -------> | UserDao | ------->    MySQL
           | (Controller)| <------- |             | <------- |         | <------- (web.users)
 		   +-------------+  UserDto +-------------+   User   +---------+
 			       |         (Dto)                  (Entity)
 			       |
 			       v
 		   +-------------+
<--------- |   user.jsp  |
 response  |    (View)   |
 		   +-------------+                 

查詢全部: GET  /user
查詢單筆: GET  /user/get?username=admin
新增單筆: POST /user/add
修改單筆: POST /user/update?userId=1
刪除單筆: GET  /user/delete?userId=1 



* */


@WebServlet(urlPatterns = {"/user/*", "/users"})
public class UserServlet extends HttpServlet{

	private UserService userService = new UserService();      // 創建一個 UserService，當 User 有需求就呼叫方法
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pathInfo = req.getPathInfo();                  // pathInfo 只會拿到問號前面資訊 ( /get )
 
		if(pathInfo==null || pathInfo.equals("/*")) {
			
			List<UserDto> userDtos = userService.findAll();   // 使用 UserService 查詢全部資料，並存入 userDtos
			
			req.setAttribute("userDtos",userDtos);            // 將 userDtos 存入 request -> jsp 接收並呈現
			
			req.getRequestDispatcher("/WEB-INF/view/user.jsp").forward(req, resp); // 重導至 user.jsp (內)
			
			return;
		}
		
		else if(pathInfo.equals("/delete")){
			String userId = req.getParameter("userId");
			userService.deleteUser(userId);
			
			// 刪除完畢，重新執行頁面
			resp.sendRedirect("/JavawebTest/user");
			return;
		}
		
		// 取得 user 資料並導入到修改頁面
		
		else if(pathInfo.equals("/get")){
			
			String username = req.getParameter("username");  // 從網址取得 username
			UserDto userDto = userService.getUser(username); // 使用 userDto 篩選需要的資訊
			
			req.setAttribute("userDto",userDto);
			
			req.getRequestDispatcher("/WEB-INF/view/user_update.jsp").forward(req, resp);
			
			return;
		}
		
		else if(pathInfo.equals("/update/password")) {
			req.getRequestDispatcher("/WEB-INF/view/update_password.jsp").forward(req, resp);
			return;
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pathInfo = req.getPathInfo();
		
		// 取得表單資料
		
		String username = req.getParameter("username"); 
		String password = req.getParameter("password"); 
		String email = req.getParameter("email"); 
		String role = req.getParameter("role"); 
		
		String active = req.getParameter("active"); // update 專用
	    String userId = req.getParameter("userId"); // update 專用
	    
	    String oldPassword = req.getParameter("oldPassword"); // update/password 專用
	    String newPassword = req.getParameter("newPassword"); // update/password 專用
		
		switch(pathInfo) {
		case "/add":
			userService.appendUser(username, password, email, role);
			break;
			
		case "/update":
			userService.updateUser(userId,active,role);
			break;
			
		case "/update/password":
			
			HttpSession session = req.getSession();                          // 取得當前 Session
			
			UserCert userCert = (UserCert)session.getAttribute("userCert");  // 取得憑證 ( 登入時已設進 Session 屬性 )  -> 確保目前為登入狀態
			
			try {
				
				userService.updatePassword(userCert.getUserId(), userCert.getUsername(), oldPassword, newPassword);
				req.setAttribute("message", "密碼更新成功");
				req.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(req, resp);
			} catch (Exception e) {
				req.setAttribute("message", e.getMessage());
				req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
			}
			return;
		}
		
		// 重導至指定URL (外)
		
		resp.sendRedirect("/JavawebTest/user");
		
	}
		

	
}


/*  製作步驟
 *    
 *  (1) 建立物件，設定屬性 & 封裝 ( getter/setter ) 
 * 
 *  (2) 建立 Dto 物件 ( 屬性為要顯示的資訊 ) 
 *  
 *  (3) 撰寫 DAO 方法 ( 用戶、資料庫的 CRUD ) ( 可先標註出 CRUD 的網址與類型，撰寫 Servlet 比較方便 )
 *  
 *  (4) 撰寫 userService ( 設定 Client 端會用到的方法，從 DAO 呼叫方法 )
 * 
 *  (5) 撰寫 userServlet ( 設定「URL」 , 實作 doGet、doPost , 從 Service 呼叫方法 )
 *  
 *  (6) 撰寫 user.jsp    ( 從 Servlet 取得資料 , 設定顯示邏輯 ) 
 * 
 * */





