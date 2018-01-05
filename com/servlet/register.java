package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entite.User;
import com.service.UserService;
import com.serviceImpl.UserServiceImpl;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String grade = request.getParameter("grade");
		String password = request.getParameter("password");
		//String id = request.getParameter(arg0);
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setGrade(grade);
		user.setPassword(password);
		
		UserService us1 = new UserServiceImpl();
		if(us1.byIdUser(id)) {
		UserService us = new UserServiceImpl();
		if(us.register(user)) {
			HttpSession session = request.getSession(); 
			session.setAttribute("user", user);
			
			request.getRequestDispatcher("homePage.html").forward(request,response);	
		}
		//注册失败，然后回到注册界面
		else response.sendRedirect("register.html");
		}
		//注册用户存在，然后就回到注册界面
		else response.sendRedirect("register.html");
	}
}
