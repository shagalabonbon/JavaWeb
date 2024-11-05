package javaweb.model.entity;

import lombok.Data;


// User 資料表紀錄
/* 建立 User 資料表
     create table if not exists users(
	    user_id int primary key auto_increment comment '使用者ID',
        username varchar(50) not null unique comment '使用者名稱' ,
        password_hash varchar(255) not null comment '使用者hash密碼' ,
        salt varchar(255) not null comment '隨機鹽' ,
        email varchar(255) comment '電子郵件' ,
        active boolean default false comment '帳號啟動' ,
        role varchar(50) not null default 'ROLE_USER' comment '角色權限' 
     );
*/                        

/* Lombok 自動快速創建方法
 * 
@Data                            	// @Data 代表全部套用
@NoArgsConstructor
@AllArgsConstructor                 // 有參數建構子
@Getter                             

*/

@Data
public class User {
	private Integer userId;           	// User 表格屬性
	private String username;
	private String passwordHash;
	private String salt;
	private String email;
	private Boolean active;
	private String role;
}
