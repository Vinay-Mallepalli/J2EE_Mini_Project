package com.wini.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wini.dao.EmployeeDao;
import com.wini.model.Employee;

@WebServlet("/")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		String path = request.getServletPath();
		
		switch(path)
		{
			case "/insert": showEmployeeRegPage(request,response);
			break;
			
			case "/add": insertDetails(request,response);
			break;
			
			case "/display":getEmployeeDetails(request,response);
			break;
			
			default : showStartingPage(request,response);
			break;
		}
	}
	
	private void getEmployeeDetails(HttpServletRequest request, HttpServletResponse response)
	{
		ArrayList<Employee> display = EmployeeDao.Display();
		request.setAttribute("employees", display);
		RequestDispatcher rd = request.getRequestDispatcher("Display.jsp");
		
		try 
		{
			rd.forward(request, response);
		}
		catch (ServletException | IOException e) 
		{
			e.printStackTrace();
		}	
	}

	private void insertDetails(HttpServletRequest request, HttpServletResponse response) 
	{
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		long mobile = Long.parseLong(request.getParameter("mobile"));
		
		Employee employee = new Employee(name, email, mobile);
		
		EmployeeDao.insert(employee);
		try 
		{
			response.sendRedirect("admin");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	private void showEmployeeRegPage(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			response.sendRedirect("EmployeeReg.jsp");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	private void showStartingPage(HttpServletRequest request, HttpServletResponse response)
	{
		try 
		{
			response.sendRedirect("Admin.jsp");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
