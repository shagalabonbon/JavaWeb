package javaweb.repository;

import java.util.List;

import javaweb.model.entity.User;

public interface UserDAO {
	
	// 多筆資料：查詢所有使用者
	
	List<User> findAllUsers();
	
	// 單筆資料：根據 Username 查詢該筆使用者
	
	User getUser(String username);
	
	// 新增
	
	void addUser(User user);
	
	// 修改 active 狀態
	
	void updateUserActive(Integer userId ,Boolean active);
	
	// 修改 role 狀態
	
	void updateUserRole(Integer userId,String role);
	
	// 刪除
	
	void deleteUser(Integer userId);
	
	// 修改密碼
	
	void updatePasswordHash(Integer userId,String newPasswordHash);
	
}
