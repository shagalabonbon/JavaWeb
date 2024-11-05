package javaweb.model.dto;

import lombok.Data;

// Dto 功能是「篩選」前端要顯示的資料         Servlet <- Dto <- userInfo ( DB )

@Data
public class UserDto {
	private Integer userId;     // 篩選要顯示的屬性
	private String username;
	private String email;
	private Boolean active;
	private String role;
}



