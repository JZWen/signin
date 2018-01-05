package com.userdaoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.entite.User;
import com.userdao.Userdao;

public class UserdaoImpl implements Userdao{
	//连接数据库
		private String driver ="com.mysql.jdbc.Driver";
		private String Url="jdbc:mysql://localhost/signinsystem1.0?characterEncoding=UTF-8";
		private String username="root";
		private String pwd="123456789";
		
		
		private Connection conn;
		private PreparedStatement ps;
		private ResultSet rs;
		
		public UserdaoImpl(){
			//空白构造方法
				//创建数据库连接
				try {
					Class.forName(driver);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				try {
					conn = DriverManager.getConnection(Url, username, pwd);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
	@Override
	public boolean login(String id, String password) {
String sql="select * from user where id=? and password=?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			rs=ps.executeQuery();
			if(rs.next()){
				closeAll();
				return true;
			}
			else {
				closeAll();
			return false;
			}		
			
		} catch (SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
	
		}
		//closeAll();
		return false;
	}
	//@Override
		public void closeAll() {
			try {
				if(rs!=null){
				rs.close();
				}
				if(ps!=null){
				ps.close();
				}
				if(conn!=null){
				conn.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*
		 * (non-Javadoc)  注册
		 * @see com.userdao.Userdao#register(com.entite.User)
		 */
		@Override
		public boolean register(User user) {
			String sql = "insert into user(id,name,grade,password) value(?,?,?,?)";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getGrade());
				ps.setString(4, user.getPassword());
				ps.executeUpdate();
				closeAll();
				return true;
			} catch (SQLException e) {
				closeAll();
				e.printStackTrace();
			}
			closeAll();
			return false;
		}

		/*
		 * 通过id查询User信息，判断该用户是否存在
		 * @see com.userdao.Userdao#byIdUser(java.lang.String)
		 */
		@Override
		public boolean byIdUser(String id) {

			String sql = "select * from user where id=?";		
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				if(rs.next()) {
					closeAll();
					return false;
				}
			} catch (SQLException e) {
				closeAll();
				e.printStackTrace();
			}
			return true;
		}

		@Override
		public List<User> byIdUserInfo(String id) {
			String sql = "select name,grade from user where id=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				if(rs.next()) {
				User user = new User();
				user.setId(id);
				user.setName(rs.getString("name"));
				user.setGrade(rs.getString("grade"));
				
				List<User> userList = new ArrayList<User>();
				userList.add(user);
				closeAll();
				return userList;
				}else {
					closeAll();
					return null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				closeAll();
				e.printStackTrace();
			}
			closeAll();
			return null;
		}

		@Override
		public boolean signIn(User user) {
			String sql = "insert into signin(id,grade,name,SignInTime) value(?,?,?,?)";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, user.getId());
				ps.setString(2, user.getGrade());
				ps.setString(3, user.getName());
				ps.setTimestamp(4, user.getSignin());
				ps.execute();
				closeAll();
				return true;
			} catch (SQLException e) {
				closeAll();
				e.printStackTrace();
			}
			closeAll();
			return false;
		}

		@Override
		public boolean byIdSignIn(String id) {
			String sql ="select * from signin where id=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				if(rs.next()) {
					closeAll();
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				closeAll();
				e.printStackTrace();
			}
			closeAll();
			return true;
		}

		@Override
		public Timestamp byIdSignTime(String id) {
			String sql = "select SignInTime from signin where id=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				if(rs.next()) {
					Timestamp time=rs.getTimestamp("SignInTime");
					closeAll();
					return time;
				}
			} catch (SQLException e) {
				closeAll();
				e.printStackTrace();
			}
			closeAll();
			return null;
		}

		@Override
		public boolean delByIdSign(String id) {
			String sql = "delete from signin where id=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				ps.executeUpdate();
				closeAll();
				return true;
			} catch (SQLException e) {
				closeAll();
				e.printStackTrace();
			}
			closeAll();
			return false;
		}

		@Override
		public boolean InsertRecond(User user) {
			String sql ="insert into signinrecond(id,name,grade,SignInTime,SignOutTime,time) value(?,?,?,?,?,?)";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getGrade());
				ps.setTimestamp(4, user.getSignin());
				ps.setTimestamp(5, user.getSignout());
				ps.setFloat(6, user.getTime());
				ps.executeUpdate();
				closeAll();
				return true;
			} catch (SQLException e) {
				closeAll();
				e.printStackTrace();
			}
			return false;
		}

		@Override
		public boolean alterUserInfo(User user) {
			//分别在user表里面。然后签到表，记录表分别修改
			String sql ="update user set name=?,grade=? where id = ?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, user.getName());
				ps.setString(2, user.getGrade());
				ps.setString(3, user.getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				closeAll();
				return false;
			}
			sql="update signin set name=?,grade=? where id = ?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, user.getName());
				ps.setString(2, user.getGrade());
				ps.setString(3, user.getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				closeAll();
				return false;
			}
			sql="update signinrecond set name=?,grade=? where id = ?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, user.getName());
				ps.setString(2, user.getGrade());
				ps.setString(3, user.getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				closeAll();
				return false;
			}
			closeAll();
			return true;
		}

		@Override
		public boolean alterPassword(User user) {
			//修改用户密码
			String sql="update user set password=? where id = ?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, user.getPassword());
				ps.setString(2, user.getId());
				ps.executeUpdate();
				closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
				closeAll();
				return false;
			}
			return true;
		}

		@Override
		public boolean idPwd(User user) {
			String sql="update user set password=? where id=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, user.getPassword());
				ps.setString(2, user.getId());
				ps.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			//return true;
		}
		
		
}
