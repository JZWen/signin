package com.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
 * Servlet implementation class signInservlet
 */
@WebServlet("/signInservlet")
public class signInservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signInservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 签到
		 */
		HttpSession session = request.getSession();
		User user = new User();
		user=(User) session.getAttribute("user");
		//等到user后，先判断是否签到
		UserService us1 = new UserServiceImpl();
		if(us1.byIdSignIn(user.getId())) {
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		user.setSignin(d);
		//调用函数插入
		UserService us = new UserServiceImpl();
		if(us.signIn(user)) {
			//签到成功
			request.getRequestDispatcher("homePage.html").forward(request,response);
		}
		else response.sendRedirect("homePage.html");
	}
		//该用户已签到
		else 
			response.sendRedirect("homePage.html");
	}
	
}
