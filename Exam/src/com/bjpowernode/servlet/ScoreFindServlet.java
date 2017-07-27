package com.bjpowernode.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjpowernode.bean.UserScore;
import com.bjpowernode.bean.Users;

/**
 * Servlet implementation class ScoreFindServlet
 */
@WebServlet("/ScoreFindServlet")
public class ScoreFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ���ܣ���ҳ��ѯ��ǰ�û��ĳɼ�
	 * ���裺       1.��ȡ�û�ͨ����������͵ġ�����ҳ����
               2.���㡾��ǰҳ����ʼ��λ�á�
               3.JDBC�����͡���ҳ��ѯ��������ݿ���
                                         ��ѯ�����ڵ�ǰҳ������
               4.���õ�����ͨ��I/O�����͵��û��������   
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	      //1.��ȡ�û�ͨ����������͵ġ�����ҳ����
	      String str_Page=request.getParameter("pageNo");
          int pageNo= str_Page!=null && !"".equals(str_Page)?Integer.valueOf(str_Page):1;
          HttpSession session = request.getSession();
          Users user=(Users) session.getAttribute("userKey");
        // HttpSession session=request.getSession();
         //Users user=(Users) session.getAttribute("userKey");
        //2.���㡾��ǰҳ����ʼ��λ�á�
        int startLine = (pageNo-1) * 3;
        // 3.JDBC�����͡���ҳ��ѯ��������ݿ��в�ѯ�����ڵ�ǰҳ������
        List list = new ArrayList();
        int totalPage = 0;//��ҳ��
        try{
	          Class.forName("com.mysql.jdbc.Driver");
	          Connection con = DriverManager.getConnection("jdbc:mysql:///examdb", "root", "RULONG0");
	          PreparedStatement ps= con.prepareStatement("select * from score where userName=?  limit ?,3");
		      ps.setString(1, user.getUserName());
	          ps.setInt(2, startLine);
		      ResultSet rs=ps.executeQuery();
		   //������ҳ��
		      PreparedStatement ps2=con.prepareStatement("select count(*) from score where userName=? ");
		      ps2.setString(1, user.getUserName());
		      ResultSet rs2=ps2.executeQuery();
		      rs2.next();
		      int totalCount=rs2.getInt(1);
		      totalPage = totalCount%3==0?totalCount/3:totalCount/3+1;
		  //4.���õ�����ͨ��I/O�����͵��û��������			      
		      while(rs.next()){
		    	   UserScore us = new UserScore();
		    	   us.setUserScore(rs.getInt("userScore"));
		    	   us.setExamTime(rs.getDate("examTime"));
		    	   us.setUserName(rs.getString("userName"));
		    	   us.setUserId(rs.getInt("userId"));
		    	  list.add(us);
		      }//user������
        }catch(Exception ex){
      	  ex.printStackTrace();
        }
        //4.���õ�����ͨ��I/O�����͵��û��������
        request.setAttribute("scoreList", list);
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("totalPage", totalPage);
        RequestDispatcher pack= request.getRequestDispatcher("/user/examsScore.jsp");
        pack.forward(request, response);

	}

	

}
