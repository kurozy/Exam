package com.bjpowernode.servlet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bjpowernode.bean.Users;

/**
 * Servlet implementation class ScoreDownLoadServlet
 */
@WebServlet("/ScoreDownLoadServlet")
public class ScoreDownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ���ܣ���ѯ��ǰѧԱ��Ϣ��������
	 * ���裺
	 *    1.���ѧԱ��ID���
	 *    2.��ѯѧԱ���Գɼ�
	 *    3.��ѧԱ���Գɼ�����excel�ĵ�
	 *    4.�����ɵ�excel�ĵ����ص�ѧԱ�Ŀͻ���
	 *    
	 *  poi���߰�����Excel�ĵ������̣�
	 *   1.���ڴ��д���һ��Excel�ĵ�
	 *   2.��Excel�ĵ��д���һ��sheet
	 *   3.ռ��sheet��ָ��λ�õġ��ж���
	 *   4.ռ��[�ж���]ָ����Ԫ��
	 *   5.��������ӵ�����Ԫ��
	 *   6.ͨ������������ڴ���Excel�ĵ����벢�����
	 *     Ӳ����  
	 *    
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   //0.�ֲ�����
		    HttpSession session = request.getSession();
		    Users user=(Users) session.getAttribute("userKey");
		    Connection con = null;
		    PreparedStatement ps=null;
		    ResultSet rs = null;
		    String sql="select * from score where userId = ?";
		    HSSFWorkbook excelObj = new HSSFWorkbook();
		    HSSFSheet sheetObj = excelObj.createSheet(user.getUserName()+"�ɼ�һ��");
		    int line = 0;
		    ServletContext application=this.getServletContext();
		    String proPath=application.getRealPath("/");
		    OutputStream out = new FileOutputStream(proPath+"score.xls");
		   //1.���ѧԱ��ID���
		    int userId = user.getUserId();
		   //2.��ѯѧԱ���Գɼ�
		    try{
	        	Class.forName("com.mysql.jdbc.Driver");
		        con = DriverManager.getConnection("jdbc:mysql:///examdb", "root", "RULONG0");		           
	            ps=con.prepareStatement(sql);
	            ps.setInt(1, Integer.valueOf(userId));
	            rs=ps.executeQuery();
	            while(rs.next()){
	            	HSSFRow row = sheetObj.createRow(line++);
	            	HSSFCell c0=row.createCell(0);
	            	HSSFCell c1=row.createCell(1);
	            	HSSFCell c2=row.createCell(2);
	            	c0.setCellValue(rs.getString("userName"));
	            	c1.setCellValue(rs.getString("userScore"));
	            	c2.setCellValue(rs.getString("examTime"));
	            }
	            excelObj.write(out);
	            out.close();
	        //3.��ѧԱ���Գɼ�����excel�ĵ�    
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
		   // 4.�����ɵ�excel�ĵ����ص�ѧԱ�Ŀͻ���
		    response.setContentType("text/plain");
		    response.setHeader("Content-Disposition", "attachment;filename=score.xls");
		    InputStream in = new FileInputStream(proPath+"score.xls");
		    ServletOutputStream out2=response.getOutputStream();
		    byte array[]=new byte[1024];
		    while(in.read(array)!=-1){
		    	out2.write(array);
		    }
		    in.close();
	}

}
