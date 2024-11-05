package javaweb.servlet;

import java.io.IOException;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/* 模式：Model 0

 * 連接方式： Get 
 * 執行位置： http://localhost:8080/JavawebTest/lottery
 * 執行位置： http://localhost:8080/JavawebTest/lucky

   http      = 通訊協定
   localhost = 主機名稱
   8080      = 連接埠   	( port )   單一服務對應一個埠,預設 port 為 80 可省略
   /javaweb  = 項目路徑 	( context path )
   /lottery  = Servlet	( Servlet path )
   無        = path info
*/

@WebServlet(urlPatterns = {"/lottery","/lucky"})  // 代表可用兩個網址

public class LotteryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  // doGet(請求,回應)
		// 產生電腦選號 ( 四星彩 )
		Random random = new Random();
		int n1 = random.nextInt(10);  // 0~9 隨機數 總共印出四位數 XXXX = n1n2n3n4
		int n2 = random.nextInt(10);
		int n3 = random.nextInt(10);
		int n4 = random.nextInt(10);
		
		// 印出結果 ( 利用 resp 回傳給瀏覽器 )  
		resp.getWriter().print("<HTML>");
		resp.getWriter().print("<H1>");
		resp.getWriter().print(n1);
		resp.getWriter().print(n2);
		resp.getWriter().print(n3);
		resp.getWriter().print(n4);
		resp.getWriter().print("</H1>");
		resp.getWriter().print("</HTML>");  // getWriter() 方法返回一個 PrintWriter 物件，可以用來向 HTTP 回應中寫入數據
		
	}
}
