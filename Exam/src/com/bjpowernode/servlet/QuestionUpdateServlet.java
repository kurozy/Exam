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
 * Servlet implementation class QuestionUpdateServlet
 */
@WebServlet("/QuestionUpdateServlet")
public class QuestionUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ���ܣ���������
	 * ���裺
	 *     1.���������Ϣ
	 *     2.��������
	 *     3.����ת��
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //0.�ֲ�����
        Connection con=null;
        PreparedStatement ps=null;
        String sql="update question set title=?,optionA=?,optionB=?,optionC=?,optionD=?,answer=? where id=?";
	    //1.���������Ϣ
        String title = request.getParameter("title");
        String optionA = request.getParameter("optionA");
        String optionB = request.getParameter("optionB");
        String optionC = request.getParameter("optionC");
        String optionD = request.getParameter("optionD");
        String answer = request.getParameter("answer");
        String id = request.getParameter("questionId");
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
            ps.setInt(7, Integer.valueOf(id));
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
	       //3.����ת�������ѯ����
	       request.getRequestDispatcher("/QuestionFindServlet").forward(request, response);
	}

}
