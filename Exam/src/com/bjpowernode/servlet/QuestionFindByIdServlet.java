package com.bjpowernode.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.bean.Question;
import com.bjpowernode.bean.Users;

/**
 * Servlet implementation class QuestionFindByIdServlet
 */
@WebServlet("/QuestionFindByIdServlet")
public class QuestionFindByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //0.�ֲ�����
	      String questionId = null;
	      Connection con = null;
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      String sql ="select * from question where id=?";
	      Users user = new Users();
	      Question q= new Question();
	    //1.�����������͵ġ�����������ݡ�
	      questionId=request.getParameter("questionId");
	    //2.JDBC��ѯ�롾����������ݡ�������[������]
	      try{
	        	Class.forName("com.mysql.jdbc.Driver");
		        con = DriverManager.getConnection("jdbc:mysql:///examdb", "root", "RULONG0");		           
	            ps=con.prepareStatement(sql);
	            ps.setInt(1, Integer.valueOf(questionId));
	            rs=ps.executeQuery();//����������ѯ����˲�ѯֻ��һ������
	      //3)[������]����ת��Ϊ�����ʽ����
	            rs.next();
	              
		    	  q.setId(rs.getInt("id"));
		    	  q.setAnswer(rs.getString("answer"));
		    	  q.setTitle(rs.getString("title"));
		    	  q.setOptionA(rs.getString("optionA"));
		    	  q.setOptionB(rs.getString("optionB"));
		    	  q.setOptionC(rs.getString("optionC"));
		    	  q.setOptionD(rs.getString("optionD"));
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
	      //4��ͨ������ת������Users�������͵�
	        request.setAttribute("questionKey", q);
	        request.getRequestDispatcher("/exammanager/examEdit.jsp").forward(request, response);

	}

}
