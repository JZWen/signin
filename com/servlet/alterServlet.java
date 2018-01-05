package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entite.User;
import com.service.UserService;
import com.serviceImpl.UserServiceImpl;

/**
 * Servlet implementation class alterServlet
 */
@WebServlet("/alterServlet")
public class alterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public alterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//这里是修改信息的地方
		//首先获取前端的数据
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String name=request.getParameter("name");
		String grade = request.getParameter("grade");
		String pwd1=request.getParameter("pwd1");
		List<User> userList = new ArrayList<User> ();
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setGrade(grade);
		if(pwd1==null) {
			//只修改其他基本信息
			UserService us = new UserServiceImpl();
			if(us.alterUserInfo(user)) {
				//修改成功的话，
				userList.set(1, user);
				request.setAttribute("userList", user);
				request.getRequestDispatcher("info.jsp").forward(request,response);
			}
		}
		else {
			//说明您要修改密码和其他信息
			user.setPassword(pwd1);
			UserService us1 = new UserServiceImpl();
			UserService us2 = new UserServiceImpl();
			if(us1.alterPassword(user)&&us2.alterUserInfo(user)) {
				userList.add(0, user);
				request.setAttribute("userList" ,user);
				request.getRequestDispatcher("info.jsp").forward(request,response);
			}
			else System.out.println("修改密码错误");
			//request.getRequestDispatcher("info.jsp").forward(request,response);
		}
		
	}

}
