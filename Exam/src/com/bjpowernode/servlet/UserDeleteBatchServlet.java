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
 * Servlet implementation class UserDeleteBatchServlet
 */
@WebServlet("/UserDeleteBatchServlet")
public class UserDeleteBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * ���ܣ�������ɾ���û���Ϣ
	 * ���裺
	 *    1.�����������͵ġ�������Ҫɾ�����û���š� ����
          2.jdbc�������������ݱ༭�ɶ�Ӧ��sql�������ѹ��
            PS�����С����С�
          3.ͨ�������ʽ���ж��������͵���Щsql���������ݿ���
                             �Ƿ�����ִ�У��Ӷ��������Ƿ���������ύ��������ع�.  

          4.ͨ������ת������tomcat���롾UserFindServlet��չʾ
                             ɾ��֮��ʣ����û���Ϣ 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              //0.�ֲ���������
		        String userIdArray[]=null;
		        Connection con = null;
		        PreparedStatement ps=null;
		      //1.�����������͵ġ�������Ҫɾ�����û���š� ����
		        userIdArray= request.getParameterValues("ck");
		      //2.jdbc�������������ݱ༭�ɶ�Ӧ��sql�������ѹ��
	              //PS�����С����С�
		        try{
		        	Class.forName("com.mysql.jdbc.Driver");
			        con = DriverManager.getConnection("jdbc:mysql:///examdb", "root", "RULONG0");
		            con.setAutoCommit(false);//���ݿ⽫�������Ȩ������ǰ����Ҳ�ͽ���con
		            ps=con.prepareStatement("delete from users where userid=?");
		            for(int i=0;i<userIdArray.length;i++){
		            	String userId = userIdArray[i];
		            	ps.setInt(1, Integer.valueOf(userId));
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
		        RequestDispatcher pack= request.getRequestDispatcher("/UserFindServlet");
			    pack.forward(request, response);
	}

}







