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
		
		//����Ҫһϵ���ж�
		UserService us = new UserServiceImpl();
		
		//�ȿ���û��ǩ��
		if(!us.byIdSignIn(user.getId())) {
			//����ȷ���Ѿ�ǩ����
			//��ȡ��ǰʱ��
			Timestamp signout = new Timestamp(System.currentTimeMillis()); 
			user.setSignout(signout);
			//��ѯǩ����ʱ��
			UserService us1 = new UserServiceImpl();
			Timestamp signin=us1.byIdSignTime(user.getId());
			user.setSignin(signin);
			if(signin.getYear()==signout.getYear()&&signin.getMonth()==signout.getMonth()&&signin.getDate()==signout.getDate()) {
				//��֤ǩ��ʱ����ͬһ��
				float time=(float)(signout.getHours()-signin.getHours());
				float min=signout.getMinutes()-signin.getMinutes();
				time=time+min/60;
				user.setTime(time);
				
				//Ȼ���ٲ��뵽��¼����
				UserService us3 = new UserServiceImpl();
				if(us3.insertRecond(user)) {
					//��ǩ����Ϣɾ��
				UserService us4 = new UserServiceImpl();
				us4.delByIdSignin(user.getId());
				request.getRequestDispatcher("login.html").forward(request,response);
				}
				//����ʧ�ܵĻ��͵���ԭ����ҳ��
				else response.sendRedirect("homePage.html");
			}
			else {
				//������ͬһ�죬��ô��ɾ������ǩ����¼
				UserService us2 = new UserServiceImpl();
				us2.delByIdSignin(user.getId());
				response.sendRedirect("homePage.html");
			}
			}
		else {
			System.out.println("����û��ǩ��");
			response.sendRedirect("homePage.html");
		}
	}

}
