package dao;

import model.Users;

public interface UserDao {
	
	//create
	void insert(Users user);
	
	//read
	String selectAll();
	Users selectByAccount(String account);
	Users selectByName(String name);
	Users selectByAccountAndPassword(String account, String password);
	
	//update
	void update(Users user);
	
	//delete
	void delete(int userId);

}
