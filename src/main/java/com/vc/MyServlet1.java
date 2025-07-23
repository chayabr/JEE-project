package com.vc;

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
 * Servlet implementation class MyServlet1
 */
@WebServlet("/register")
public class MyServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("tbName");
		String password=request.getParameter("tbPass");
		Connection con=null;
		PreparedStatement st1=null;
		try {
			//load Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish connection
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/jeeproject","root","Chaya@1234");
			//create table
			//Statement st=con.createStatement();
			//st.executeUpdate("create table GmailUser(name varchar(50),password varchar(50))");
			//insert table
			st1=con.prepareStatement("insert into GmailUser values( ?, ?)");
			st1.setString(1, name);
			st1.setString(2, password);
			int noOfRows = st1.executeUpdate();
			System.out.println("No of rows inserted: "+noOfRows);	

		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
            try { if (st1 != null) st1.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
		dispatcher.forward(request, response);
		
	}

}
