package javaweb.repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javaweb.model.entity.Product;

public class ProductDAOImpl extends BaseDAO implements ProductDAO {     // 撰寫 SQL 方法

	@Override
	public List<Product> findAllProduct() {
		String sql = "SELECT product_id,product_name,price,stock_quantity FROM products";
		
		List<Product> products = new ArrayList<>();  // 儲存提出來的資料
		
		try(Statement stmt = conn.createStatement(); 
			ResultSet rs = stmt.executeQuery(sql) ){
			
			while(rs.next()){   // ResultSet 指標逐行向下
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));         // 選擇提取的欄位與型態 getType()
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getBigDecimal("price"));
				product.setStockQuantity(rs.getInt("stock_quantity"));
				
				products.add(product); // 將提出的物件放入 List 統整
			}
		}catch(SQLException e) {  
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public void addProduct(Product product) {
		
		String sql = "insert into products(product_name,price,stock_quantity) values (?,?,?)";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, product.getProductName());  // 設定 ? 值
			pstmt.setBigDecimal(2, product.getPrice());
			pstmt.setInt(3, product.getStockQuantity());
			
			int rowCount = pstmt.executeUpdate();         // prepareStatement 中 executeUpdate()方法：確認資料更新數為 1 行
			if(rowCount!=1) {
				throw new RuntimeException("新增失敗");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateProductQuantity(Integer productId,Integer stockQuantity) {
		String sql = "update products set stock_quantity = ? where product_id = ?" ;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1, stockQuantity);   
			pstmt.setInt(2, productId);
			
			int rowCount = pstmt.executeUpdate();         // prepareStatement 中 executeUpdate()方法：確認資料更新數為 1 行
			if(rowCount!=1) {
				throw new RuntimeException("修改失敗 商品id："+ productId +" 數量："+ stockQuantity);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateProductPrice(Integer productId,BigDecimal price) {
		String sql = "update products set price = ? where product_id = ?" ;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setBigDecimal(1, price);   
			pstmt.setInt(2, productId);
			
			int rowCount = pstmt.executeUpdate();         // prepareStatement 中 executeUpdate()方法：確認資料更新數為 1 行
			if(rowCount!=1) {
				throw new RuntimeException("修改失敗 商品id："+ productId +" 價格："+ price);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProduct(Integer productId) {
		String sql = "delete from products where product_id = ?" ;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1,productId);
		
		int rowcount=pstmt.executeUpdate();
		if(rowcount!=1) {
			throw new RuntimeException("修改失敗 productId："+productId);
		}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Product getProduct(String productName) {
		String sql = "select product_id,product_name,price,stock_quantity from products where product_name = ?" ;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()){
			
			pstmt.setString(1,productName);
			
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getBigDecimal("price"));
				product.setStockQuantity(rs.getInt("stock_quantity"));
				
				return product;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Double> querySalesRanking() {
		String sql="""                                               
				   SELECT p.product_name, SUM(o.subtotal) AS total_sales
				   FROM orders o
				   LEFT JOIN product p ON o.product_id = p.product_id
			       GROUP BY p.product_name
				   ORDER BY total_sales DESC 
           """.trim();
		
		Map<String, Double> map = new LinkedHashMap<>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()){
				
			while(rs.next()) {
				String key   = rs.getString("product_name");
				Double value = rs.getDouble("total_sales");
				
				map.put(key, value);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return map ;
	}
	
	

}
