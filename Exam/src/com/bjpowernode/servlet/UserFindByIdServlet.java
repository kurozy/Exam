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

import com.bjpowernode.bean.Users;

/**
 * Servlet implementation class UserFindByIdServlet
 */
@WebServlet("/UserFindByIdServlet")
public class UserFindByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ���ܣ����ݡ��û���š���ѯ�û���Ϣ�����͸������
	 * ���裺
	 *     1�������������͵ġ�����������ݡ�
           2��JDBC��ѯ�롾����������ݡ�������[������]
           3)[������]����ת��Ϊ�����ʽ����
             ResultSet------>Users����
           4��ͨ������ת������Users�������͵�
                               ָ��jspҳ����  
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    //0.�ֲ�����
		      String userId = null;
		      Connection con = null;
		      PreparedStatement ps = null;
		      ResultSet rs = null;
		      String sql ="select * from users where userId=?";
		      Users user = new Users();
		    //1.�����������͵ġ�����������ݡ�
		      userId=request.getParameter("ck");
		    //2.JDBC��ѯ�롾����������ݡ�������[������]
		      try{
		        	Class.forName("com.mysql.jdbc.Driver");
			        con = DriverManager.getConnection("jdbc:mysql:///examdb", "root", "RULONG0");		           
		            ps=con.prepareStatement(sql);
		            ps.setInt(1, Integer.valueOf(userId));
		            rs=ps.executeQuery();//����������ѯ����˲�ѯֻ��һ������
		      //3)[������]����ת��Ϊ�����ʽ����
		            rs.next();
		            user.setUserId(rs.getInt("userId"));
		            user.setUserName(rs.getString("userName"));
		            user.setPassword(rs.getString("password"));
		            user.setUserCode(rs.getString("userCode"));
		            user.setEmail(rs.getString("email"));
		            user.setTel(rs.getString("tel"));
		            user.setFlag(rs.getInt("flag"));
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
		        request.setAttribute("userKey", user);
		        request.getRequestDispatcher("/usermanager/userEdit.jsp").forward(request, response);
	}

}
