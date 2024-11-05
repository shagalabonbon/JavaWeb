package javaweb.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javaweb.model.dto.ProductDto;
import javaweb.service.ProductService;

/**
-- 商品 product
+------------+------------------+----------+----------------+
| product_id | product_name     | price    | stock_quantity |
+------------+------------------+----------+----------------+
| 1          | PC               | 30000.00 | 50             |
| 2          | Mobile           | 15000.00 | 100            |
| 3          | MusicBox         | 3000.00  | 200            |
| 4          | Pad              | 20000.00 | 75             |
| 5          | Watch            | 8000.00  | 150            |
+------------+------------------+----------+----------------+

 -- 創建商品表
create table if not exists product (
	product_id int primary key auto_increment comment '商品Id',
	product_name varchar(50) not null unique comment '商品名稱',
	price decimal(10, 2) not null comment '商品價格',
	stock_quantity int not null default 0 comment '商品庫存'
); 


 MVC + 自訂框架
  
  request   +----------------+             +----------------+          +------------+
 ---------> | ProductServlet | ----------> | ProductService | -------> | ProductDao | ------->    MySQL
            | (Controller)   | <---------- |                | <------- |            | <------- (web.product)
  			+----------------+  ProductDto +----------------+  Product +------------+
  			       |              (Dto)                       (Entity)
  			       |
  			       v
  			+-------------+
 <--------- | product.jsp |
  response	|    (View)   |
  			+-------------+                 
 
 查詢全部: GET  /product, /products
 
 查詢全部: GET  /product
 查詢單筆: GET  /product/get?productName=banana
 新增單筆: POST /product/add
 修改單筆: POST /product/update?productId=1
 刪除單筆: GET  /product/delete?productId=1
 
*/  

@WebServlet(urlPatterns = {"/products","/product/*","/product/sales/ranking"})
public class ProductServlet extends HttpServlet {
    
	private ProductService productService = new ProductService();  // 創建使用者端服務 
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pathInfo = req.getPathInfo();
		
		if(pathInfo==null || pathInfo.equals("/*")) {
			
			List<ProductDto> productDtos = productService.findAll();                      // 向 Service 取得服務   ( 提取 Dto 資料 )
			
			req.setAttribute("productDtos",productDtos);                                  // 將資料存入 request 端 ( setAttribute )
			
			req.getRequestDispatcher("/WEB-INF/view/product.jsp").forward(req, resp);;    // 導向目標 jsp 檔 ( /WEB-INF/view )
			
			return;
		}
		
		
		if(pathInfo.equals("/delete")){
			
			String productId = req.getParameter("productId");              // getParameter 獲取 url 資料
			
			productService.deleteProduct(productId);	   				   // 向 Service 取得服務 ( 刪除 )
			
			resp.sendRedirect("/JavawebTest/products");                    // 導回原畫面 ( /products )
			
			return;
		}	 
			
		if(pathInfo.equals("/get")){
			
			String productName = req.getParameter("productName");                                // getParameter 獲取 url 資料
			
			ProductDto productDto = productService.getPruduct(productName);                      // 向 Service 取得服務
			
			req.setAttribute("productDto",productDto);                                           // 將資料存入 request 端 ( setAttribute )
			
			req.getRequestDispatcher("/WEB-INF/view/product_update.jsp").forward(req, resp);;    // 導向目標 jsp 檔 ( /WEB-INF/view )
			
			return;
		}	
		
		switch(pathInfo) {
		
		case"/product/sales/ranking":
			req.setAttribute("salesRankingMap", productService.querySalesRanking());
			req.getRequestDispatcher("/WEB-INF/view/sales_ranking.jsp").forward(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 獲取方法所需資料
		
		String productId     = req.getParameter("productId");
		String productName   = req.getParameter("productName");
		String price         = req.getParameter("price");
		String stockQuantity = req.getParameter("stockQuantity");
		
		String pathInfo = req.getPathInfo();
		
		// POST 方法
		
		switch(pathInfo) {
		case"/add":
			productService.appendProduct(productName,price,stockQuantity);
			break;
		case"/update":
			productService.updateProduct(productId, price, stockQuantity);
			break;
		}
		// 重導至指定URL (外)
		resp.sendRedirect("/JavawebTest/product");
	}
	
}
