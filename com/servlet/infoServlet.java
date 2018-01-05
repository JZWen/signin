package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class loginServlet
 */
@WebServlet("/infoServlet")
public class infoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public infoServlet() {
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
		HttpSession session = request.getSession();
		User user = new User();
		user=(User) session.getAttribute("user");
		UserService us = new UserServiceImpl(); 
		List<User> userList = new ArrayList<User> ();
		userList = us.byIdUserInfo(user.getId());
		User user1 = new User();
		user1=userList.get(0);
		request.setAttribute("userList", user1);
		request.getRequestDispatcher("info.jsp").forward(request,response);
	}
}
