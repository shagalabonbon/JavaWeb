package javaweb.servlet;

import java.io.IOException;
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


@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	
	private OrderService orderService = new OrderService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		UserCert userCert = (UserCert)session.getAttribute("userCert");
		
		// 使用者購物車資料
		
		List<OrderDto> orderDtos = orderService.findAllOrders(userCert.getUserId(), "Pending");  // 只顯示狀態為 "Pending" 的訂單
		
		req.setAttribute("orderDtos", orderDtos);
		
		req.getRequestDispatcher("/WEB-INF/view/cart.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
