package javaweb.repository;

import java.util.List;

import javaweb.model.entity.Order;


public interface OrderDAO {
	
	// 尋找該使用者相同狀態的全部訂單
	
	public List<Order> findAllOrders(Integer userId,String orderStatus); 
	
	// 同時新增多筆訂單
	
	public void batchAddOrders(List<Order> orders);   
	
	// 同時更新多筆訂單狀態  ( batch 批次 )
	
	public void batchUpdateOrderStatus(List<Integer> orderIds,String orderStatus); 
		
}
