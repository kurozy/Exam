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
 * Servlet implementation class QuestionDeleteServlet
 */
@WebServlet("/QuestionDeleteServlet")
public class QuestionDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ���ܣ�һ��ɾ��һ������
	 * ���裺
	 *            1.�����������͵�IDֵ��-----��������
                  2.jdbc����ɾ������   ------����ҵ��
                     delete from ���� where �����ֶ�=��IDֵ��
                  3.���á���ҳ��ѯServlet����Ӧ   
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	      //0.�ֲ���������
	       String questionId = null;
	       String sql ="delete from question where id=?";
	       Connection con = null;
	       PreparedStatement ps = null;
	      //1.���������������͵ġ������š�
	       questionId=request.getParameter("questionId");
	      //2.JDBC������õ�sql�������͵����ݿ�ִ��
	       try{
	    	   Class.forName("com.mysql.jdbc.Driver");
		       con = DriverManager.getConnection("jdbc:mysql:///examdb", "root", "RULONG0");
		       ps=con.prepareStatement(sql);
		       ps.setInt(1, Integer.valueOf(questionId));
		       ps.executeUpdate();
	       }catch(Exception ex){
	    	   ex.printStackTrace();
	       }
	       //ͨ������ת����ʽ����tomcat������á������ҳ��ѯSerlvet�� 
	       RequestDispatcher pack= request.getRequestDispatcher("/QuestionFindServlet");
	       pack.forward(request, response);

	}

}
