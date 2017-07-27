<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<title>Insert title here</title>
		<link href="style/style.css" rel="stylesheet" type="text/css" />
	</head>
	<!--left-->
	<div id="left" style="float: left;">
		<!--s_left-->
		<div class="s_left" id="nav">
		

		<div class="s_left">
			<p>
				系统管理
			</p>
			<ul>
				<li>
                             <!-- ${pageContext.request.contextPath} -->
					<a href="/Exam/UserFindServlet"
						style="background: url(images/user.png) no-repeat 15px center;"
						target="content">用户管理</a>
				</li>
				<li>

					<a href="./content.html"
						style="background: url(images/tb.gif) no-repeat 15px center;"
						target="content">学员成绩分析</a>
				</li>

				<li>

					<a href="/Exam/QuestionFindSameServlet"
						style="background: url(images/top_14.gif) no-repeat 15px center;"
						target="content">试题查重</a>
				</li>
				<li>

					<a href="./exammanager/examAdd.html"
						style="background: url(images/top_8.gif) no-repeat 15px center;"
						target="content">添加试题</a>
				</li>
				<li>
					<a href="/Exam/QuestionFindServlet"
						style="background: url(images/top_6.gif) no-repeat 15px center;"
						target="content">试题管理</a>

				</li>
			</ul>
		</div>

	</div>