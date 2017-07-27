package com.bjpowernode.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
     
	/*
	 *  ����:���ݵõ����û���š�����ɾ����һ��ֻɾ��һ���û���Ϣ
	 *  ���裺
	 *         1.���������������͵ġ��û���š�
               2.JDBC������õ�sql�������͵����ݿ�ִ��
               3.ͨ������ת����ʽ����tomcat������á��û���ҳ��ѯSerlvet�� 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		      //0.�ֲ���������
		       String userId = null;
		       String sql ="delete from users where userId=?";
		       Connection con = null;
		       PreparedStatement ps = null;
		      //1.���������������͵ġ��û���š�
		       userId=request.getParameter("userId");
		      //2.JDBC������õ�sql�������͵����ݿ�ִ��
		       try{
		    	   Class.forName("com.mysql.jdbc.Driver");
			       con = DriverManager.getConnection("jdbc:mysql:///examdb", "root", "RULONG0");
			       ps=con.prepareStatement(sql);
			       ps.setInt(1, Integer.valueOf(userId));
			       ps.executeUpdate();
		       }catch(Exception ex){
		    	   ex.printStackTrace();
		       }
		       //ͨ������ת����ʽ����tomcat������á��û���ҳ��ѯSerlvet�� 
		       RequestDispatcher pack= request.getRequestDispatcher("/UserFindServlet");
		       pack.forward(request, response);
	}

}








