package com.FinalInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.HashMap;

public class cat_containment 
{
	java.sql.PreparedStatement pst;
	java.sql.PreparedStatement pst2;
	java.sql.PreparedStatement pst3;
	java.sql.PreparedStatement pst1;
	java.sql.PreparedStatement pst12;
	java.sql.PreparedStatement pst13;
	String dbname="P2";
	String cat_name[];
	
	Statement stmt=null;
	Statement stmt2=null;
	
	
	
	private Connection con;
	public cat_containment(Connection con)
	{
		this.con=con;
	}

	public void containment()
	{
	
		
		///////FACTT/////
		String sql1="SELECT * from category_subcategory";
		try {
			
		pst= con.prepareStatement(sql1);
		//pst.setString(1, info);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
		{
		//
		String data=rs.getString("category_name");
		
		//System.out.println(data);
		//System.out.println(aa);
		
		String sql3="SELECT * from category_subcategory where category_name=?";
		pst2= con.prepareStatement(sql3);
		pst2.setString(1,data);
		
		ResultSet rs3 = pst2.executeQuery();
		//System.out.println(rs3.next());
		
		while(rs3.next())
		{
			String aa = rs3.getString("subcategory_of");
			if(aa.indexOf(' ') != -1)
			{
			aa=aa.replace(" ","_");
			}
			String x=aa+"_id";

			if(data.indexOf(' ') != -1)
			{
			data=data.replace(" ","_");
			}
			
			if(data.equals("Order"))
			{
			data = data+"1";
			}
			ResultSet rs4 = con.getMetaData().getTables(null, null, aa,null);
			if (!rs4.next())
			{
				stmt = con.createStatement();
				String sql6="CREATE TABLE "+ aa+
						"("+aa+"_id VARCHAR(100) NOT NULL"+
								" PRIMARY KEY ("+aa+"_id))";
				
				//System.out.println(sql6);
				stmt.executeUpdate(sql6);
				//System.out.println("hi");	
		
	    		String sql5="ALTER TABLE "+data+
				  " ADD FOREIGN KEY ("+aa+"_id) REFERENCES "+aa+" ("+aa+"_id)";
				  
				//System.out.println(sql5);
				stmt.executeUpdate(sql5);
		
			}

		//System.out.println("Bye");
		
		}
		}
		
	
		}
		
		
		catch(NullPointerException e)
		{
			System.out.println("No Containment found");
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	
	
	
		}
}
