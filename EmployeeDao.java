package com.wini.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.wini.model.Employee;

public class EmployeeDao {

	public static final String url = "jdbc:mysql://localhost:3306/miniproject";
	public static final String user = "root";
	public static final String password = "admin";
	public static Connection cn = null;
	public static  PreparedStatement ps = null;
	public static  Statement stm = null;
	public static  ResultSet rs = null;
	
	public static void insert(Employee employee)
	{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn=DriverManager.getConnection(url,user,password);
			
			String query = "insert into employee(name,email,mobile) values(?,?,?)";
			ps=cn.prepareStatement(query);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getEmail());
			ps.setLong(3, employee.getMobile());
			
			ps.executeUpdate();		
		} 
		
		catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		} 
		
	}

	public static ArrayList<Employee> Display() {
		
		ArrayList<Employee> al = new ArrayList<Employee>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn=DriverManager.getConnection(url,user,password);
			String query = "select * from employee";
			stm = cn.createStatement();
			rs=stm.executeQuery(query);
			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				long mobile = rs.getLong("mobile");
				Employee employee = new Employee(id, name, email, mobile);
				al.add(employee);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return al;
		
		
	}
}
	
	