package javaweb.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javaweb.model.dto.OrderDto;
import javaweb.model.entity.Order;
import javaweb.model.entity.Product;
import javaweb.repository.OrderDAO;
import javaweb.repository.OrderDAOImpl;
import javaweb.repository.ProductDAO;
import javaweb.repository.ProductDAOImpl;

public class OrderService {
	
	private OrderDAO orderDAO = new OrderDAOImpl();
	
	private ProductDAO pruductDAO = new ProductDAOImpl();
	
	// 同時新增多筆訂單
	
	public void batchAddOrders(Integer userId,String[] productIds,String[] unitPrices,String[] amounts) {
		
		List<Order> orders = new ArrayList<Order>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  // SimpleDateFormat 設定時間顯示格式 
		
		for(int i=0;i<productIds.length;i++) {
			Integer productId = Integer.parseInt(productIds[i]);    // 使用者 id
			Integer amount    = Integer.parseInt(amounts[i]);       // 數量
			Double  unitPrice = Double.parseDouble(unitPrices[i]);  // 單價 
			
			// 過濾有效的訂單
			if(amount <= 0) {
				continue;
			}
			
			Order order = new Order();
			order.setUserId(userId);
			order.setOrderDate(sdf.format(new Date()));             // 利用指定格式顯示訂單時間
			order.setProductId(productId);
			order.setQuantity(amount);
			order.setUnitPrice(unitPrice);
			order.setSubtotal((int)(order.getQuantity()*order.getUnitPrice()));   
			order.setOrderStatus("Pending");  
			
			orders.add(order);
		}
		
		orderDAO.batchAddOrders(orders);  // 呼叫 orderDAO 批次新增商品訂單
		
		
		/* order 後臺顯示 ( JSP 隱藏欄 )  

		   userID     
		   productID  [1, 2, 3, 4, 5]
		   unitPrice  [10, 20, 30, 40, 50]
	       amounts    [5, 0, 3, 0, 2]
	        
		 */
	
	}
	
	
	// 尋找所有訂單 ( Dto 版本 )
	
	public List<OrderDto> findAllOrders(Integer userId, String orderStatus){
		
		List<Order> orders = orderDAO.findAllOrders(userId, orderStatus);
		
		// 取得所有商品
		
		List<Product> products = pruductDAO.findAllProduct();
		
		// 將 order 轉為 orderDto
		
		List<OrderDto> orderDtos = new ArrayList<OrderDto>();
		
		for(Order order : orders ) {
			OrderDto orderDto = new OrderDto();
			orderDto.setOrderId(order.getOrderId());
			orderDto.setUserId(order.getUserId());
			orderDto.setOrderDate(order.getOrderDate());
			orderDto.setProductId(order.getProductId());
			orderDto.setQuantity(order.getQuantity());
			orderDto.setUnitPrice(order.getUnitPrice());
			orderDto.setSubtotal(order.getSubtotal());
			orderDto.setOrderStatus(order.getOrderStatus());
			
//			Optional<Product> optProduct = products.stream().filter(p->p.getProductId().equals(orderDto.getProductId())).findFirst();
			
//			if(optProduct.isPresent()) {
//				orderDto.setProductName(optProduct);
//			
			
			// 透過 productId 找到對應的 productNam
//			orderDto.setProductName(products.stream()
//									.filter(p->p.getProductId().equals(orderDto.getProductId()))
//									.findFirst()
//									.orElseGet(null)
//									.getProductName());   // 使用 Stream 取出所有商品名稱
			
			orderDtos.add(orderDto);
		}
		
		return orderDtos;
		
	}
	
	
	// 更新訂單狀態
	
	public void updateOrderStatus(Integer userId,String fromOrderStatus,String toOrderStatus ) {
		
		List<Order> orders = orderDAO.findAllOrders(userId, fromOrderStatus);                            // 取得所有相同狀態的訂單
		
		List<Integer> orderIds = orders.stream().map(o->o.getOrderId()).collect(Collectors.toList());    // 從 orders 中得到所有的訂單 ID
		
		orderDAO.batchUpdateOrderStatus(orderIds, toOrderStatus);                                        // 批次修改成新狀態
	
		/* userID = 1
		 * fromOrderStatus : 修改前狀態
		 * toOrderStatus   : 更新後狀態
	    */	
	}
	
	
	
	
	
	
}
