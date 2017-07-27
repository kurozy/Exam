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
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ���ܣ������û���Ϣ
	 * ���裺
	 *           1)�����������͵ĸ��º������
                 2��JDBC�����º���������͵����ݿ�
                 3������ת�������á��û���Ϣ����ѯ���û��������
                                               չʾ���µ�����
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.��ȡ��������������͵��������
        String userCode = request.getParameter("userCode");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String userId= request.getParameter("userId");
        /*
         *  ���ڶ�ȡ�����������ʱ���������쳣
         */
          //Integer.valueOf(userCode);
        //2.ͨ����JDBC�����õ��Ĳ������뵽���ݿ���
        Connection con = null;
        try{
        	Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql:///examdb", "root", "RULONG0");
            String sql="update users set userCode=?,userName=?,password=?,email=?,tel=? where userId=? ";	        
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, userCode);
            ps.setString(2, userName);
            ps.setString(3, password);
            ps.setString(4, email);
            ps.setString(5, tel);
            ps.setInt(6, Integer.valueOf(userId));
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
        //3������ת�������á��û���Ϣ����ѯ���û��������չʾ���µ�����
          request.getRequestDispatcher("/UserFindServlet").forward(request, response);

	}

}
