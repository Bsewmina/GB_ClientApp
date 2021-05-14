package com;

import java.sql.*;

public class Account {
	
	private Connection connect() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1.:3306/gadgetbadget_db","root","toor");
			System.out.println("-------------Connection succeed----------");
		}catch(Exception e){
			System.out.println("-------------Error connectiong to database---------/n " + e.fillInStackTrace());
		}
		
		return con;
	}
	
	public String viewallAccounts() {
		
		String result = "";
		
		try {
			
			Connection con = connect();		
			
			if(con == null) {
				return "	Null connection Error !!!";
			}
			
			result ="<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6\" crossorigin=\"anonymous\">"
					+ "<table class=\"table table-striped mt-4 l-10 r-10\">"
					+ "  <thead >"
					+ "    <tr class=\"table-dark\">"
					+ "      <th scope=\"col\">acc_id</th>"
					+ "      <th scope=\"col\">First Name</th>"
					+ "      <th scope=\"col\">Last Name</th>"
					+ "      <th scope=\"col\">Gender</th>"
					+ "		<th scope=\"col\">Mobile No</th>"
					+ "      <th scope=\"col\">Email</th>"
					+ "      <th scope=\"col\">Password</th>"
					+ " 	<th scope=\"col\">Update</th>"
					+ " 	<th scope=\"col\">Remove</th>"
					+ "    </tr>"
					+ "  </thead>"
					+ "  <tbody>";
					
					String sql ="SELECT * FROM account";
					Statement stt = con.createStatement();
					ResultSet rs = stt.executeQuery(sql);
			
					while (rs.next()) {
						
						String acc_Id = Integer.toString(rs.getInt("acc_id"));
						result += "    <tr><th scope=\"row\">" + acc_Id + "</th>";
						result += "      <td>" + rs.getString("first_name")  + "</td>";
						result += "      <td>" + rs.getString("last_name") + "</td>";
						result += "      <td>" + rs.getString("gender") + "</td>";
						result += "      <td>" + Integer.toString(rs.getInt("mobile_no"))	+ "</td>";
						result += "      <td>" + rs.getString("email") + "</td>";
						result += "      <td>" + rs.getString("password") + "</td>";
						
						result +="<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-accountid='" + acc_Id + "'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' "
								+ "class='btnRemove btn btn-danger' data-accountid='" + acc_Id + "'></td></tr>";
					}
					
						result += "</tbody></table>";
			
					con.close();
		}catch(Exception e) {
			System.err.println(e);
			return "	Error while retreaving Data !!! ";
		}
				
		return result;
	}
	
	
	
	public String createAccount(String fname,String lname,String gender,int num,String email,String pass) {
		String output;
		try {
			
			Connection con = connect();
			PreparedStatement ps = null;
			
			if(con == null) {
				return "	Null connection Error !!!";
			}
			
			String sql = "INSERT INTO account VALUES (NULL,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,fname);
            ps.setString(2,lname);
            ps.setString(3,gender);
            ps.setInt(4,num);
            ps.setString(5,email);
            ps.setString(6,pass);
            
            ps.execute();
            
            ps.close();
			con.close();
			
			String accounts = viewallAccounts();
			 output = "{\"status\":\"success\", \"data\": \"" + accounts + "\"}";
			 
		}catch(Exception e) {
			
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
			
			System.err.println("error while insering !!!!!\n" + e.getMessage());
			
		}
		
		return output;
		
	}
	
	
	public String updateAccount(int acc_id,String fname,String lname,String gender,int num,String email,String pass) {
		
		//System.out.println("---------------caling  2 ------------");
		String output;
		try {

			Connection con = connect();
			PreparedStatement ps = null;
			
			if(con == null) {
				return "	Null connection Error !!!";
			}
			
			String sql = "UPDATE account SET acc_id=?, first_name=?, last_name=?, gender=?, mobile_no=?, email=?, password=? WHERE acc_id = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1,acc_id);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, gender);
			ps.setInt(5, num);
			ps.setString(6, email);
			ps.setString(7, pass);
			ps.setInt(8,acc_id);
			
			ps.execute();
			
			ps.close();
			con.close();
			
			String accounts = viewallAccounts();
			 output = "{\"status\":\"success\", \"data\": \"" + accounts + "\"}";
		}catch(Exception e) {
			
			System.err.println("error while Updating !!!!!\n" + e.getMessage());
			output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
		}
		
		return output;
		
	}
	
	public String deleteAccount(String acc_id) {
		String output;
		try {
			
			Connection con = connect();
			PreparedStatement ps = null;
			
			if (con == null) {
				return "	Null connection Error !!!";
			}
			
			String sql = "DELETE FROM account WHERE acc_id = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(acc_id));
			
			ps.execute();
			
			ps.close();
			con.close();
			
			String accounts = viewallAccounts();
			 output = "{\"status\":\"success\", \"data\": \"" + accounts + "\"}";
			 
		}catch(Exception e) {
			
			System.err.println("error while Deleting !!!!!\n" + e.getMessage());
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
			//return "error while Deleting !!!!!";
		}
		
		return output;
	}
	
	

}
