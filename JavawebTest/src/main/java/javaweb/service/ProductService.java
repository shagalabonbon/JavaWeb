package javaweb.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javaweb.model.dto.ProductDto;
import javaweb.model.entity.Product;
import javaweb.repository.ProductDAO;
import javaweb.repository.ProductDAOImpl;

public class ProductService {
	
	private ProductDAO productDAO = new ProductDAOImpl();
	
	// 全部商品
	
	public List<ProductDto> findAll() {
		
		List<ProductDto> productDtos = new ArrayList<>(); 

		List<Product> products = productDAO.findAllProduct();

		for(Product product : products) {
			ProductDto productDto = new ProductDto();
			productDto.setProductId(product.getProductId());
			productDto.setProductName(product.getProductName());
			productDto.setPrice(product.getPrice());
			productDto.setStockQuantity(product.getStockQuantity());

			productDtos.add(productDto); // 將 productDto 存入 List<ProductDto>
		}
		return productDtos;
	}

	// 新增商品

	public void appendProduct(String productName,String price,String stockQuantity) {

		// 根據上列參數封裝到 User 物件中
		Product product = new Product();
		product.setProductName(productName);
		product.setPrice(new BigDecimal(price));
		product.setStockQuantity(Integer.parseInt(stockQuantity));

		productDAO.addProduct(product);

	}

	// 刪除物品

	public void deleteProduct(String productId) {
		productDAO.deleteProduct(Integer.parseInt(productId));
	}

	// 取得指定物品資料

	public ProductDto getPruduct(String productName) {
		Product product = productDAO.getProduct(productName);

		if (product == null) {
			return null;
		}

		ProductDto productDto = new ProductDto();
		productDto.setProductName(product.getProductName());
		productDto.setProductId(product.getProductId());
		productDto.setPrice(product.getPrice());
		productDto.setStockQuantity(product.getStockQuantity());
		
		return productDto;  // 資料皆須轉為 Dto 物件 ( 顯示指定資訊 )
	}

	// 修改特定物品資訊

	public void updateProduct(String productId,String price,String stockQuantity) {
		if (price!=null) {
			productDAO.updateProductPrice(Integer.parseInt(productId),new BigDecimal(price));
		}
		
		if (stockQuantity!=null) {
			productDAO.updateProductQuantity(Integer.parseInt(productId), Integer.parseInt(stockQuantity));
		}
	}
	
	
	// 銷售排行
	
	public Map<String, Double> querySalesRanking() {
		return productDAO.querySalesRanking();
	}
	
	
}
