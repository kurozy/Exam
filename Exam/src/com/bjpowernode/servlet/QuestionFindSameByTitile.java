package com.bjpowernode.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.bean.Question;

/**
 * Servlet implementation class QuestionFindSameByTitile
 */
@WebServlet("/QuestionFindSameByTitile")
public class QuestionFindSameByTitile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ���ܣ���ѯ��ǰ�ظ��������м�¼
	 * ����:
	 *     1.�����������
	 *     2.JDBC��ѯ��ǰ���������ظ�����
	 *     3.ͨ������ת����������û��������
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		     //0.�ֲ���������
		     String title = null;
		     String sql ="select * from question where title=?";
		     Connection con = null;
		     PreparedStatement ps = null;
		     ResultSet rs = null;
		     List questionList = new ArrayList();
		     //1.�����������
		     title = request.getParameter("title");
		     //2.JDBC��ѯ��ǰ���������ظ�����
		     try{
		    	 Class.forName("com.mysql.jdbc.Driver");
		         con = DriverManager.getConnection("jdbc:mysql:///examdb", "root", "RULONG0");
		         ps= con.prepareStatement(sql);
			     ps.setString(1, title);
			     rs=ps.executeQuery();
			     while(rs.next()){
			    	   Question q=new Question();
			    	   q.setId(rs.getInt("id"));
				       q.setTitle(rs.getString("title"));
				       q.setOptionA(rs.getString("optionA"));
				       q.setOptionB(rs.getString("optionB"));
				       q.setOptionC(rs.getString("optionC"));
				       q.setOptionD(rs.getString("optionD"));
				       q.setAnswer(rs.getString("answer"));
				       q.setAuthor(rs.getString("author"));
				       q.setModifyTime(rs.getDate("modifyTime"));
				       questionList.add(q);
			     }
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
		     //3.ͨ������ת����������û��������
		     request.setAttribute("questionList", questionList);
		     request.setAttribute("title", title);
		     RequestDispatcher pack= request.getRequestDispatcher("/exammanager/questionSame_Edit.jsp");
		     pack.forward(request, response);
	}

}
