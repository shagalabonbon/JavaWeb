package javaweb.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	protected static Connection conn;
	
	static {
		String username = "root";
		String password = "900108";
		String dbUrl = "jdbc:mysql://localhost:3306/web?serverTimezone=Asia/Taipei&characterEncoding=utf-8&useUnicode=true";
			
		             // < 通訊協議> : <子協議> : <資料庫名稱>                                  
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 驅動： MySQL8.0 之後的驅動
			conn = DriverManager.getConnection(dbUrl,username,password);
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
