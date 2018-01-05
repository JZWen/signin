package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entite.Judge;
import com.entite.User;
import com.service.UserService;
import com.serviceImpl.UserServiceImpl;
import com.userdao.Userdao;
import com.userdaoImpl.UserdaoImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		System.out.println(id);
		System.out.println(password);
		
		
		UserService us1 = new UserServiceImpl();
		if(us1.login(id, password)) {
			User user = new User();
			UserService us = new UserServiceImpl();
			List<User> userList = new ArrayList<User>();
			userList=us.byIdUserInfo(id);
			user.setId(userList.get(0).getId());
			user.setName(userList.get(0).getName());
			user.setGrade(userList.get(0).getGrade());
			
			HttpSession session = request.getSession(); 
			session.setAttribute("user", user);
			request.getRequestDispatcher("homePage.html").forward(request,response);
		}else {
			System.out.println(2222);
			response.sendRedirect("login.html");
		}
	}
}
