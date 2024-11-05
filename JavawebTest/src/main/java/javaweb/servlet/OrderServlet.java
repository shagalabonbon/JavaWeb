package javaweb.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javaweb.model.dto.OrderDto;
import javaweb.model.dto.UserCert;
import javaweb.service.OrderService;

/* 訂單
 * 
 *   +------------+------------+-------------+--------------+-----------+-------------+--------------+----------------+
	 |  order_id  |  user_id   | order_date  | product_id   |  quantity |  unit_price |  subtotal    | order_status   |
	 +------------+------------+-------------+--------------+-----------+-------------+--------------+----------------+
	 | 1          | 1          | 2024-09-19  | 1            | 2         |  30000.00   |  60000.00    | Finished       |
	 | 2          | 1          | 2024-09-20  | 2            | 5			|  15000.00   |  75000.00    | Finished       |
	 | 3          | 3          | 2024-09-21  | 3            | 3			|  3000.00    |  9000.00	 | Pending        |
	 | 4          | 4          | 2024-09-22  | 2            | 1			|  15000.00   |  15000.00	 | Cancel         |
	 | 5          | 5          | 2024-09-23  | 5            | 4			|  8000.00    |  32000.00    | Pending        |
	 +------------+------------+-------------+--------------+-----------+-------------+--------------+----------------+

-- 創建訂單表
    create table if not exists product (
	order_id     int primary key auto_increment comment '訂單Id',
	user_id      int not null                   comment 'User Id',
	order_date   varchar(50) not null           comment '訂購日期',
	product_id   int not null                   comment '商品Id',
	quantity     int not null default 0         comment '訂購數量', 
	unit_price   decimal(10, 2) not null		comment '商品單價',
	subtotal     decimal(10, 2) not null        comment '小記',
	order_status Enum('Pending','Finished','Cancel') not null default 'Pending' comment '訂單狀態',
	); 
 * */

@WebServlet("/order/*")
public class OrderServlet extends HttpServlet {
	
	OrderService orderService = new OrderService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pathInfo = req.getPathInfo() + "";
		
		HttpSession session = req.getSession();                                                 // 從 Session 獲取需要的資訊
		UserCert userCert = (UserCert)session.getAttribute("userCert");
		Integer userId = userCert.getUserId();
		
		
		switch(pathInfo) {
		
		case"/finish":
			orderService.updateOrderStatus(userId, "Pending", "Finished");                      // 修改訂單狀態
			req.setAttribute("message", "購物_結帳完畢");
			req.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(req, resp);
			break;
		
		case"/cancel":
			orderService.updateOrderStatus(userId, "Pending", "Cancel");                        // 修改訂單狀態
			req.setAttribute("message", "購物_取消完畢");                                  
			req.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(req, resp);
			break;
			
		case"/history":
			List<OrderDto> orderFinishedDtos = orderService.findAllOrders(userId, "Finished");  // 提取狀態為 "Finished"、"Cancel" 的訂單
			List<OrderDto> orderCancelDtos   = orderService.findAllOrders(userId, "Cancel");
			
			req.setAttribute("orderFinishedDtos", orderFinishedDtos);                           // 傳送至 history.jsp 顯示
			req.setAttribute("orderCancelDtos", orderCancelDtos);
			
			req.getRequestDispatcher("/WEB-INF/view/history.jsp").forward(req, resp);
		
		default:
			req.getRequestDispatcher("/WEB-INF/view/order.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] productIds = req.getParameterValues("productId");        // getParameterValues 回傳 String[]
 		String[] unitPrices = req.getParameterValues("unitPrice");
		String[] amounts    = req.getParameterValues("amount"); 
		
		System.out.println(Arrays.toString(productIds)+"<= productIds");  // order.jsp 隱藏欄，方便後台判讀商品 id
		System.out.println(Arrays.toString(unitPrices)+"<=unitPrices");
		System.out.println(Arrays.toString(amounts)+"<=amounts");
		
		// 新增訂單
		
		HttpSession session = req.getSession();                           
		UserCert userCert = (UserCert)session.getAttribute("userCert");
		orderService.batchAddOrders(userCert.getUserId(),productIds,unitPrices,amounts);  // 從 Session 憑證中找到 userID 
	    
	    // 訂單新增後，重導回購物車
	    
	    resp.sendRedirect("/JavawebTest/cart");
	}
	
}
