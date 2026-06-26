package dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Users;
import dao.UserDao;
import util.DbConnection;

public class UserDaoImpl implements UserDao{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	Connection conn=DbConnection.getDb();

	@Override
	public void insert(Users user) {
		// TODO Auto-generated method stub
		String sql="Insert into users(account,password,name,role_id)"
				+"values(?,?,?,?)";
		try (PreparedStatement ps =conn.prepareStatement(sql)){
			
	        ps.setString(1, user.getAccount());
	        ps.setString(2, user.getPassword());
	        ps.setString(3, user.getName());
	        ps.setInt(4, user.getRoleId());
	        
	        ps.executeUpdate();
		}catch(Exception e) {
			 e.printStackTrace();
		}
	}

	@Override
	public String selectAll() {
		// TODO Auto-generated method stub
		String sql="select * from users";
		String showAll ="";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				showAll=showAll
						+"id:"+rs.getInt("user_id")
						+"\t帳號："+rs.getString("account")
						+"\t密碼："+rs.getString("password")
						+"\t姓名："+rs.getString("name")
						+"\t："+rs.getString("role_id");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return showAll;
	}

	@Override
	public Users selectByAccount(String account) {
		// TODO Auto-generated method stub
		Users user=null;
		String sql ="select * from users where account=?";
		
		try {
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, account);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			user = new Users();
			
            user.setUserId(rs.getInt("user_id"));
            user.setAccount(rs.getString("account"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setRoleId(rs.getInt("role_id"));
            Timestamp ts = rs.getTimestamp("createAt");
            
            if(ts != null)
            {
            	user.setCreateAt(ts.toLocalDateTime());
            }
            
		}
		
		}catch(Exception e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		}
		
		return user;
	}
	
	@Override
	public Users selectByName(String name) {
		// TODO Auto-generated method stub
		Users user=null;
		String sql ="select * from users where name=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new Users();
				
	            user.setUserId(rs.getInt("user_id"));
	            user.setAccount(rs.getString("account"));
	            user.setPassword(rs.getString("password"));
	            user.setName(rs.getString("name"));
	            user.setRoleId(rs.getInt("role_id"));
	            Timestamp ts = rs.getTimestamp("createAt");
	            
	            if(ts != null)
	            {
	            	user.setCreateAt(ts.toLocalDateTime());
	            }
	            
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public Users selectByAccountAndPassword(String account, String password) {
		// TODO Auto-generated method stub
		Users user=null;
		String sql=			    
				"select * " +
			    "from users u " +
			    "join roles r " +
			    "on u.role_id = r.role_id " +
			    "where u.account=? and u.password=?";
		try {
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, account);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			user = new Users();
			
            user.setUserId(rs.getInt("user_id"));
            user.setAccount(rs.getString("account"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setRoleId(rs.getInt("role_id"));
            
		}
		
		}catch(Exception e) {
			 e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public void update(Users user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int userId) {
		// TODO Auto-generated method stub
		
	}

}
