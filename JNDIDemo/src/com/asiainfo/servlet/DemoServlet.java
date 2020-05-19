package com.asiainfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/demo")
public class DemoServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Context cxt = new InitialContext();
			DataSource dataSource = (DataSource) cxt.lookup("java:comp/env/test");
			Connection connection = dataSource.getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from flower");
			ResultSet rs = pstmt.executeQuery();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			while (rs.next()) {
				writer.write(rs.getInt(1)+"&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString(2)+"</br>");
			}
			writer.flush();
			writer.close();
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
