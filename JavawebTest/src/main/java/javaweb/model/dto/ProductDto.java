package javaweb.model.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDto {
	private Integer productId;
	private String  productName;
	private BigDecimal price;
	private Integer stockQuantity;
}
