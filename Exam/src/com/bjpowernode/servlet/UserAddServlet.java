package com.bjpowernode.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/user/add")
public class UserAddServlet extends HttpServlet {
	
	/*
	         ���� �� ����û���Ϣ
	         ���� ��
	          1.��ȡ��������������͵��������
	          2.ͨ����JDBC�����õ��Ĳ������뵽���ݿ���     
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        //1.��ȡ��������������͵��������
		        String userCode = request.getParameter("userCode");
		        String userName = request.getParameter("userName");
		        String password = request.getParameter("password");
		        String email = request.getParameter("email");
		        String tel = request.getParameter("tel");
		        String flag =request.getParameter("flag");
		        /*
		         *  ���ڶ�ȡ�����������ʱ���������쳣
		         */
		          //Integer.valueOf(userCode);
		        //2.ͨ����JDBC�����õ��Ĳ������뵽���ݿ���
		        Connection con = null;
		        try{
		        	Class.forName("com.mysql.jdbc.Driver");
			        con = DriverManager.getConnection("jdbc:mysql:///examdb", "root", "RULONG0");
		            String sql="insert into users(userCode,userName,password,email,tel,flag) values(?,?,?,?,?,?)";	        
		            PreparedStatement ps=con.prepareStatement(sql);
		            ps.setString(1, userCode);
		            ps.setString(2, userName);
		            ps.setString(3, password);
		            ps.setString(4, email);
		            ps.setString(5, tel);
		            ps.setInt(6, Integer.valueOf(flag));
		            ps.executeUpdate();
		        }catch(Exception ex){
		        	ex.printStackTrace();
		        }finally{
		        	try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        RequestDispatcher pack= request.getRequestDispatcher("/UserFindServlet");
			     pack.forward(request, response);
	}

}








