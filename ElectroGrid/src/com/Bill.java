package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class Bill 
{ //A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/item_database", "root", "root"); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String insertBill(String Acc, String Date, String Unit, String Price, String Total){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into items (`B_ID`,`bill_Acc`,`bill_Date`,`bill_Unit`,`bill_price`,`bill_Total`)"+" values (?, ?, ?, ?, ?,?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, 0); 
						preparedStmt.setString(2, Acc); 
						preparedStmt.setString(3, Date); 
						preparedStmt.setDouble(4, Double.parseDouble(Unit)); 
						preparedStmt.setString(5, Price); 
						preparedStmt.setString(6, Total); 
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						
						String newBills = readBills(); 
						
						output = "{\"status\":\"success\",\"data\":\""+newBills+"\"}"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "{\"status\":\"error\", \"data\":\"Error while inserting the Bill.\"}"; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
		public String readBills() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
		 output = "<table border=\"1\" style=\"background-color:#0dcaf0\"  class=\"table\"><tr>"
		 		+ "<th>Account Number</th><th>Date</th>"
		 		+ "<th>Units</th>"
		 		+ "<th>Price</th>"
		 		+ "<th>Total</th>"
		 		+ "<th>Update</th>"
		 		+ "<th>Remove</th></tr>"; 
		
		 String query = "select * from items"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String B_ID = Integer.toString(rs.getInt("B_ID")); 
		 String bill_Acc = rs.getString("bill_Acc"); 
		 String bill_Date = rs.getString("bill_Date"); 
		 String bill_Unit = Double.toString(rs.getDouble("bill_Unit")); 
		 String bill_price = rs.getString("bill_price"); 
		 String bill_Total = rs.getString("bill_Total"); 
		 // Add into the html table
		 output += "<tr><td><input id='hidBillIDUpdate' name='hidBillIDUpdate' type='hidden' value='"+B_ID+"'>"+bill_Acc +"</td>"; 
		 output += "<td>" + bill_Date + "</td>"; 
		 output += "<td>" + bill_Unit + "</td>"; 
		 output += "<td>" + bill_price + "</td>"; 
		 output += "<td>" + bill_Total + "</td>"; 
		 // buttons
		 
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-secondary' data-billid='" + B_ID + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-billid='" + B_ID + "'></td></tr>"; 
		 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 
		catch (Exception e) 
		 { 
		 output = "Error while reading the Bills."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

			
			
		public String updateBill(String ID, String Acc, String Date, String Unit, String Price,String Total) {
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE items SET bill_Acc=?,bill_Date=?,bill_Unit=?,bill_price=?,bill_Total=? WHERE B_ID=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							preparedStmt.setString(1, Acc); 
							preparedStmt.setString(2, Date); 
							preparedStmt.setDouble(3, Double.parseDouble(Unit)); 
							preparedStmt.setString(4, Price); 
							preparedStmt.setString(5, Total); 
							preparedStmt.setInt(6, Integer.parseInt(ID)); 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							String newBills = readBills(); 
							output = "{\"status\":\"success\",\"data\":\""+newBills+"\"}"; 

					} 
					
					catch (Exception e){ 
						
						output = "{\"status\":\"error\",\"data\":\"Error while updating the Bill.\"}"; 

						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deleteBill(String billid){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from items where B_ID=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(billid)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newBills = readBills(); 
						 output = "{\"status\":\"success\",\"data\":\""+newBills+"\"}"; 

					} 
					
					catch (Exception e){ 
						output = "{\"status\":\"error\",\"data\":\"Error while deleting the Bill.\"}";
						System.err.println(e.getMessage()); 
					} 
					return output; 
			}


			
			
			
			
			
} 
