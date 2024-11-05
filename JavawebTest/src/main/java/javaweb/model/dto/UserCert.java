package javaweb.model.dto;

// 使用者憑證 ( Certification ) ： 登入成功後會獲得的憑證資料

public class UserCert {
	private Integer userId;
	private String username;
	private String role;	
//	private Date loginTime;   可顯示登入時間
	
	public UserCert(Integer userId, String username, String role) {
		this.userId = userId;
		this.username = username;
		this.role = role;
	}
	
	
	// 憑證無法更改 ( 只可用 getter 取得資訊 )

	public Integer getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getRole() {
		return role;
	}
	
	
	// 顯示憑證內容
	
	@Override
	public String toString() {
		return "UserCert [userId=" + userId + ", username=" + username + ", role=" + role + "]";   // UserCert [userId=userId,username=username,role=role]
	}

	
}
