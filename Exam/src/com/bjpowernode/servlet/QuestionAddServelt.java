package com.bjpowernode.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuestionAddServelt
 */
@WebServlet("/QuestionAddServelt")
public class QuestionAddServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 *  ���ܣ��������
	 *  ���裺
	 *     1.������������͵��������----��������
           2.jdbc��������                -----��������
           3.�����ݿ��е��������͵��û������---��Ӧ 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		      //0.�ֲ�����
		      String title,optionA,optionB,optionC,optionD,answer=null;
		      Connection con =null;
		      PreparedStatement ps=null;
		      String sql="insert into question(title,optionA,optionB,optionC,optionD,answer) values(?,?,?,?,?,?)";
		      //1.������������͵��������
		      title = request.getParameter("title");
		      optionA = request.getParameter("optionA");
		      optionB = request.getParameter("optionB");
		      optionC = request.getParameter("optionC");
		      optionD = request.getParameter("optionD");
		      answer = request.getParameter("answer");
		      // 2.jdbc��������                -----��������
		      try{
		        	Class.forName("com.mysql.jdbc.Driver");
			        con = DriverManager.getConnection("jdbc:mysql:///examdb", "root", "RULONG0");		            
		            ps=con.prepareStatement(sql);
		            ps.setString(1, title);
		            ps.setString(2, optionA);
		            ps.setString(3, optionB);
		            ps.setString(4, optionC);
		            ps.setString(5, optionD);
		            ps.setString(6, answer);
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
		      //3.�����ݿ��е��������͵��û������---��Ӧ
		      request.getRequestDispatcher("/QuestionFindServlet").forward(request, response);
		      
	}

}
