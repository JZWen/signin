package com.servlet;

import java.io.IOException;
import java.sql.Timestamp;

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
 * Servlet implementation class signOutServlet
 */
@WebServlet("/signOutServlet")
public class signOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signOutServlet() {
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
		
		//首先要一系列判断
		UserService us = new UserServiceImpl();
		
		//先看有没有签到
		if(!us.byIdSignIn(user.getId())) {
			//这里确定已经签到了
			//获取当前时间
			Timestamp signout = new Timestamp(System.currentTimeMillis()); 
			user.setSignout(signout);
			//查询签到的时间
			UserService us1 = new UserServiceImpl();
			Timestamp signin=us1.byIdSignTime(user.getId());
			user.setSignin(signin);
			if(signin.getYear()==signout.getYear()&&signin.getMonth()==signout.getMonth()&&signin.getDate()==signout.getDate()) {
				//保证签到时间在同一天
				float time=(float)(signout.getHours()-signin.getHours());
				float min=signout.getMinutes()-signin.getMinutes();
				time=time+min/60;
				user.setTime(time);
				
				//然后再插入到记录表中
				UserService us3 = new UserServiceImpl();
				if(us3.insertRecond(user)) {
					//将签到信息删除
				UserService us4 = new UserServiceImpl();
				us4.delByIdSignin(user.getId());
				request.getRequestDispatcher("login.html").forward(request,response);
				}
				//插入失败的话就调回原来的页面
				else response.sendRedirect("homePage.html");
			}
			else {
				//不是在同一天，那么就删除该条签到记录
				UserService us2 = new UserServiceImpl();
				us2.delByIdSignin(user.getId());
				response.sendRedirect("homePage.html");
			}
			}
		else {
			System.out.println("您还没有签到");
			response.sendRedirect("homePage.html");
		}
	}

}
