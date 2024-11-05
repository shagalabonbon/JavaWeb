package javaweb.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javaweb.model.entity.Product;

public interface ProductDAO {
	
	List<Product> findAllProduct();
	
	void addProduct(Product product);
	
	void updateProductQuantity(Integer productId,Integer stockQuantity);
	
	void updateProductPrice(Integer productId,BigDecimal price);
	
	void deleteProduct(Integer productId);
	
	Product getProduct(String productName);
	
	// 銷售排行 < 商品名稱, 銷售額 >
	
	Map<String, Double> querySalesRanking();
 
	
}
