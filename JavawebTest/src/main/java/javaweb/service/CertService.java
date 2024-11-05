package javaweb.service;

import javaweb.exception.CertException;
import javaweb.exception.PasswordInvalidException;
import javaweb.exception.UserNotFoundException;
import javaweb.model.dto.UserCert;
import javaweb.model.entity.User;
import javaweb.repository.UserDAO;
import javaweb.repository.UserDAOImpl;
import javaweb.utils.Hash;

// 憑證服務

public class CertService {
	
	private UserDAO userDAO = new UserDAOImpl();
	
	// 取得憑證 ( 提供帳號密碼 )
	
	public UserCert getCert(String username,String password) throws CertException{
		
		User user = userDAO.getUser(username);
		
		if(user==null) {
			throw new UserNotFoundException("查無此人");              // 查詢使用者
		}
		
		String passwordHash = Hash.getHash(password,user.getSalt());  // 驗證密碼  
		if(!passwordHash.equals(user.getPasswordHash())){
			throw new PasswordInvalidException("密碼錯誤");
		}
		
		UserCert userCert = new UserCert(user.getUserId(),user.getUsername(),user.getRole());  // 提供憑證 ( 提供給 LoginServlet )
		return userCert ;  
	} 
}
