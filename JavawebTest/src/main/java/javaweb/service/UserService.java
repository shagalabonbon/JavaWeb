package javaweb.service;

import java.util.ArrayList;
import java.util.List;

import javaweb.exception.PasswordInvalidException;
import javaweb.exception.UserNotFoundException;
import javaweb.model.dto.UserDto;
import javaweb.model.entity.User;
import javaweb.repository.UserDAO;
import javaweb.repository.UserDAOImpl;
import javaweb.utils.Hash;

public class UserService {
	private UserDAO userDAO = new UserDAOImpl();
	
	// 全部使用者
	
	public List<UserDto> findAll(){
		
		List<UserDto> userDtos = new ArrayList<>();   // 最後返回的 List<UserDto> (4)
		
		List<User> users = userDAO.findAllUsers();    // 向 userDao 索取 List<User> 集合 (1)
		
		for(User user :users) {                       // 將所有 User 轉成 UserDto (2)
			UserDto userDto = new UserDto();
			userDto.setUserId(user.getUserId());
			userDto.setUsername(user.getUsername());  
			userDto.setEmail(user.getEmail());
			userDto.setActive(user.getActive());
			userDto.setRole(user.getRole());
			
			userDtos.add(userDto);                    // 將 userDto 存入 List<UserDto> (3)
		}
		return userDtos; 
	}
	
	// 新增使用者  
	
	public void appendUser(String username,String password,String email,String role) {
		
		// 密碼加鹽
		
		String salt = Hash.getSalt();                        // 得到隨機鹽
		String passwordHash = Hash.getHash(password,salt);   // 進行 Hash
		Boolean action = false;                              // Email 驗證狀態
		
		// 將參數封裝到新的 User 物件
		
		User user = new User();                
		user.setUsername(username);
		user.setPasswordHash(passwordHash);
		user.setSalt(salt);
		user.setEmail(email);
		user.setActive(action);
		user.setRole(role);
		
		userDAO.addUser(user);   
	}
	
	// 刪除使用者 
	
	public void deleteUser(String userId) {
		userDAO.deleteUser(Integer.parseInt(userId));
	}
	
	// 取得指定使用者 
	
	public UserDto getUser(String username) {
		
		User user = userDAO.getUser(username);
		
		if(user==null) { 
			return null; 
		}
		
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setUsername(user.getUsername());
		userDto.setEmail(user.getEmail());
		userDto.setActive(user.getActive());
		userDto.setRole(user.getRole());
		
		return userDto;
	}
	
	// 修改特定使用者 
	
	public void updateUser(String userId,String active,String role) {
		
		if(!active.isEmpty()) {
			userDAO.updateUserActive(Integer.parseInt(userId), Boolean.parseBoolean(active));
		}
		
		if(!role.isEmpty()) {
			userDAO.updateUserRole(Integer.parseInt(userId), role);	
		}
		
	}
	
	
	// 變更密碼
	
	public void updatePassword(Integer userId,String username,String oldPassword,String newPassword) throws UserNotFoundException ,PasswordInvalidException{
		User user = userDAO.getUser(username);		
		if(user==null) {
			throw new UserNotFoundException();
		}
		
		// 驗證舊密碼
		
		String oldPasswordHash = Hash.getHash(oldPassword,user.getSalt());
		if(!oldPasswordHash.equals(user.getPasswordHash())) {
			throw new PasswordInvalidException("舊密碼輸入錯誤");
		}
		
		// 產生新密碼 Hash
		
		String newPasswordHash = Hash.getHash(newPassword,user.getSalt());  // 使用舊的鹽
		
		// 更新資料庫資訊
		
		userDAO.updatePasswordHash(userId, newPasswordHash);
	}
	
	
	
}


