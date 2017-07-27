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

/**
 * Servlet implementation class QuestionDeleteBatchServlet
 */
@WebServlet("/QuestionDeleteBatchServlet")
public class QuestionDeleteBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ���ܣ�������ɾ��ָ�����ظ���������
	 * ���裺
	 *     1.���������
	 *     2.jdbc������ɾ����������
	 *     3.����ת�����ص��ظ������ѯ
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //0.�ֲ���������
	        String questionIdArray[]=null;
	        Connection con = null;
	        PreparedStatement ps=null;
	      //1.���������
	        questionIdArray= request.getParameterValues("ck");    
	      //2.jdbc�������������ݱ༭�ɶ�Ӧ��sql�������ѹ��
            //PS�����С����С�
	        try{
	        	Class.forName("com.mysql.jdbc.Driver");
		        con = DriverManager.getConnection("jdbc:mysql:///examdb", "root", "RULONG0");
	            con.setAutoCommit(false);//���ݿ⽫�������Ȩ������ǰ����Ҳ�ͽ���con
	            ps=con.prepareStatement("delete from question where id=?");
	            for(int i=0;i<questionIdArray.length;i++){
	            	String id = questionIdArray[i];
	            	ps.setInt(1, Integer.valueOf(id));
	            	ps.addBatch();
	            }
	        }catch(Exception ex){
	        	ex.printStackTrace();
	        }
	      //3.ͨ�������ʽ���ж��������͵���Щsql���������ݿ���
        //  �Ƿ�����ִ�У��Ӷ��������Ƿ���������ύ��������ع�. 
	        try{
	          ps.executeBatch();
	          con.commit();
	        }catch(SQLException ex){
	        	ex.printStackTrace();
	        	try {
					con.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	       // 4.ͨ������ת������tomcat���롾UserFindServlet��չʾ
         // ɾ��֮��ʣ����û���Ϣ  
	        RequestDispatcher pack= request.getRequestDispatcher("/QuestionFindSameByTitile");
		    pack.forward(request, response);

	}

}
