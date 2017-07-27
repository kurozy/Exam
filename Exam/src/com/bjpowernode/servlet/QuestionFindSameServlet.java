package com.bjpowernode.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.bean.Question;

/**
 * Servlet implementation class QuestionFindSameServlet
 */
@WebServlet("/QuestionFindSameServlet")
public class QuestionFindSameServlet extends HttpServlet {
	/*
	 *  ����:ͨ����ҳ��ѯ����ѯ�����ظ�����������
	 *  ���裺
	 *         1.��ȡ�û�ͨ����������͵ġ�����ҳ����
               2.���㡾��ǰҳ����ʼ��λ�á�
               3.JDBC�����͡���ҳ��ѯ��������ݿ���
                                         ��ѯ�����ڵ�ǰҳ������
               4.���õ�����ͨ��I/O�����͵��û�������� 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	      //1.��ȡ�û�ͨ����������͵ġ�����ҳ����
	      String str_Page=request.getParameter("pageNo");
	      String sql="select title,count(*) from question group by title having count(*) >=2  limit ?,3";
	      int pageNo= str_Page!=null && !"".equals(str_Page)?Integer.valueOf(str_Page):1;
	      //2.���㡾��ǰҳ����ʼ��λ�á�
	      int startLine = (pageNo-1) * 3;
	      // 3.JDBC�����͡���ҳ��ѯ��������ݿ��в�ѯ�����ڵ�ǰҳ������
	      Map map = new HashMap();
	      int totalPage = 0;//��ҳ��
	      try{
	          Class.forName("com.mysql.jdbc.Driver");
	          Connection con = DriverManager.getConnection("jdbc:mysql:///examdb", "root", "RULONG0");
	          PreparedStatement ps= con.prepareStatement(sql);
		      ps.setInt(1, startLine);
		      ResultSet rs=ps.executeQuery();
		   //������ҳ��
		      PreparedStatement ps2=con.prepareStatement("select count(*) from (select title,count(*) from question group by title having count(*) >=2)t1");
		      ResultSet rs2=ps2.executeQuery();
		      rs2.next();
		      int totalCount=rs2.getInt(1);
		      totalPage = totalCount%3==0?totalCount/3:totalCount/3+1;
		  //4.���õ�����ͨ��I/O�����͵��û��������			      
		      while(rs.next()){
		    	 String title = rs.getString("title");
		    	 int num = rs.getInt("count(*)");
		    	 map.put(title, num);
		      }//user������
      }catch(Exception ex){
    	  ex.printStackTrace();
      }
      //4.���õ�����ͨ��I/O�����͵��û��������
      request.setAttribute("questionMap", map);
      request.setAttribute("pageNo", pageNo);
      request.setAttribute("totalPage", totalPage);
      RequestDispatcher pack= request.getRequestDispatcher("/exammanager/questionSame.jsp");
      pack.forward(request, response);

	
	}

}
