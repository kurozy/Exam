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
import javax.servlet.http.HttpSession;

import com.bjpowernode.bean.Users;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ���ܣ���¼��֤:
	 * ���裺1��ȡ�û���������
	 *     2��ѯ�û��Ƿ����
	 *       2.1 �����ڣ��ض��򵽵�¼ҳ����
	 *       2.2 ����
	 *               �����û���Ϣ��session��
	 *               �����û�������ж�menu������
	 *               ��ת��index.jsp
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    //0.�ֲ���������
		       String userName,password=null;
		       Connection con = null;
		       PreparedStatement ps=null;
		       ResultSet rs =null;
		       String sql="select * from users where userName=? and password=?";
		       Users user  = null;
		    //1.��ȡ�û���������
		       request.setCharacterEncoding("utf-8");
		       userName = request.getParameter("userName");
		       password = request.getParameter("password");
		    //2��ѯ�û��Ƿ����
		       try{
		        	Class.forName("com.mysql.jdbc.Driver");
			        con = DriverManager.getConnection("jdbc:mysql:///examdb", "root", "RULONG0");		           
		            ps=con.prepareStatement(sql);
		            ps.setString(1, userName);
		            ps.setString(2, password);
		            rs=ps.executeQuery();//����������ѯ����˲�ѯֻ��һ������
		      //3)[������]����ת��Ϊ�����ʽ����
		           while( rs.next()){
		        	   user = new Users();
		        	   user.setUserId(rs.getInt("userId"));
			            user.setUserName(rs.getString("userName"));
			            user.setPassword(rs.getString("password"));
			            user.setUserCode(rs.getString("userCode"));
			            user.setEmail(rs.getString("email"));
			            user.setTel(rs.getString("tel"));
			            user.setFlag(rs.getInt("flag"));
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
		       
		       if(user==null){
		    	   response.sendRedirect("/Exam/login.html");
		       }else{
		    	   HttpSession session = request.getSession();
		    	   session.setAttribute("userKey", user);
		    	   int flag = user.getFlag();
		    	   if(flag==1){//ѧԱ
		    		   request.setAttribute("menuKey","menu_student.jsp" );
		    	   }else{
		    		   request.setAttribute("menuKey","menu_teacher.jsp" );
		    	   }
		    	   request.getRequestDispatcher("/index.jsp").forward(request, response);
		       }
	}

}
