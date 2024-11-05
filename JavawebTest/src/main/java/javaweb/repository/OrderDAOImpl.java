package javaweb.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javaweb.model.entity.Order;


public class OrderDAOImpl extends BaseDAO implements OrderDAO  {

	@Override
	public List<Order> findAllOrders(Integer userId, String orderStatus) {
		
		List<Order> orders = new ArrayList<>();                   
		                                                                     // 文本塊（text blocks）可在字串中使用換行文字 ( """ """ )
		String sql="""                                               
				      select order_id, user_id, order_date, product_id,            
                             quantity, unit_price, subtotal, order_status
                      from orders
                      where user_id = ? and order_status= ? 
                      
                   """.trim();                                             
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1,userId);
			pstmt.setString(2,orderStatus);
			
			try(ResultSet rs=pstmt.executeQuery()){
				
				while(rs.next()) {
					Order order = new Order(); // 建立 order 物件並將資料配置進去
					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("user_id"));
					order.setOrderDate(rs.getString("order_date"));
					order.setProductId(rs.getInt("product_id"));
					order.setQuantity(rs.getInt("quantity"));
					order.setUnitPrice(rs.getDouble("unit_price"));
					order.setSubtotal(rs.getInt("subtotal"));
					order.setOrderStatus(rs.getString("order_status"));
					
					orders.add(order);    // 加入 order 集合
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}

	@Override
	public void batchAddOrders(List<Order> orders) {
		
		String sql= """
				    insert into 
				    orders(user_id, order_date, product_id, quantity, unit_price, subtotal, order_status) 
				    values (?,?,?,?,?,?,?)
				    
				    """.trim();
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.clearBatch();    // clearBatch 清除批次內容 ( 重要!! )
			
			for(Order order:orders) {
				pstmt.setInt(1, order.getUserId());
				pstmt.setString(2, order.getOrderDate());
				pstmt.setInt(3, order.getProductId());
				pstmt.setInt(4, order.getQuantity());
				pstmt.setDouble(5, order.getUnitPrice());
				pstmt.setInt(6, order.getSubtotal());
				pstmt.setString(7, order.getOrderStatus());
				
				pstmt.addBatch();  // addBatch 將 SQL 添加到批處理中 ( 用於執行多條相似的 SQL 語句 )
			}
			
			pstmt.executeBatch();  // executeBatch 執行所有添加的 SQL 語句 ( 可減少與資料庫的交互次數 )

		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		// executeBatch ： 只涉及修改數據
		// ResultSet    ： 需要返回結果時

	}
	
	// 同時更新多筆訂單狀態 --------------------------------------------------------
	
	@Override
	public void batchUpdateOrderStatus(List<Integer> orderIds,String orderStatus) {
		
		String sql= """
				       update orders
				       set order_status = ?
				       where order_id = ?
				    
			        """.trim();
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.clearBatch();    
			for(Integer orderId : orderIds) {
				pstmt.setString(1, orderStatus);  // 固定狀態
				pstmt.setInt   (2, orderId);
				pstmt.addBatch();  
			}
			pstmt.executeBatch();  
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
