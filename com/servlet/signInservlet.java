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
		 * ǩ��
		 */
		HttpSession session = request.getSession();
		User user = new User();
		user=(User) session.getAttribute("user");
		//�ȵ�user�����ж��Ƿ�ǩ��
		UserService us1 = new UserServiceImpl();
		if(us1.byIdSignIn(user.getId())) {
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		user.setSignin(d);
		//���ú�������
		UserService us = new UserServiceImpl();
		if(us.signIn(user)) {
			//ǩ���ɹ�
			request.getRequestDispatcher("homePage.html").forward(request,response);
		}
		else response.sendRedirect("homePage.html");
	}
		//���û���ǩ��
		else 
			response.sendRedirect("homePage.html");
	}
	
}
