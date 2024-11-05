package javaweb.model.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Product {
	private Integer productId;
	private String  productName;
	private BigDecimal price;
	private Integer stockQuantity;
}
