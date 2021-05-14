package com;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AccountAPI
 */
@WebServlet("/AccountAPI")
public class AccountAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Account acc;
    
    
    public AccountAPI() {
        super();
        acc = new Account();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    		{
    		 String output = acc.createAccount(
    				 request.getParameter("firstName"),
    				 request.getParameter("lastName"),
    				 request.getParameter("gender"),
    				 Integer.parseInt(request.getParameter("mobile")),
    				 request.getParameter("email"),
    				 request.getParameter("password"));
    		 
    		response.getWriter().write(output);
    		}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
			{
			 Map paras = getParasMap(request);
			 String output = acc.deleteAccount(paras.get("acc_Id").toString());
			response.getWriter().write(output);
			}

	private Map getParasMap(HttpServletRequest request) {
		
		Map<String, String> map = new HashMap<String, String>();
		try
		 {
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		 String queryString = scanner.hasNext() ?
		 scanner.useDelimiter("\\A").next() : "";
		 scanner.close();
		 String[] params = queryString.split("&");
		 for (String param : params)
		 { 
			 String[] p = param.split("=");
			 map.put(p[0], p[1]);
			 }
			 }
			catch (Exception e)
			 {}
			return map;
			}
		 

}
