package com.FinalInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.HashMap;

public class fact_dim 
{
	java.sql.PreparedStatement pst;
	java.sql.PreparedStatement pst2;
	java.sql.PreparedStatement pst3;
	java.sql.PreparedStatement pst1;
	java.sql.PreparedStatement pst12;
	java.sql.PreparedStatement pst13;
	String cat_name[];
	int customer=0;
	int comrate=0;
	int location=0;
	int quality=0;
	int mode=0;
	int agree=0;
	int market=0;
	Statement stmt=null;
	//Statement stmt2=null;
	
	
	private Connection con;
	public fact_dim(Connection con)
	{
		this.con=con;
	}

	public void fetchInfoFromTable()
	{
	
		
		///////FACTT
		String sql1="SELECT * from p_info";
		try {
			
		pst= con.prepareStatement(sql1);
		//pst.setString(1, info);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
		{
		String aa = rs.getString("p_info");
		String data = aa;
		
		
		if(aa.indexOf(' ') != -1)
		{
		aa=aa.replace(" ","_");
		System.out.println("\n Data objects ");
		System.out.println(aa);
		stmt = con.createStatement();
		String sql5="CREATE TABLE "+ aa+
				"(id VARCHAR(100) NOT NULL)";
		
		System.out.println(sql5);
		stmt.executeUpdate(sql5);
		}
		else
			
		{ 
			if(aa.equals("Order"))
			{
			aa = aa+"1";
			}
			if(aa.equals("National"))
			{
			aa = aa+"1";
			}
			System.out.println("\n Data objects ");
			System.out.println(aa);
			stmt = con.createStatement();
			String sql5="CREATE TABLE "+aa+
					"(id VARCHAR(100) NOT NULL,"+ 
					" PRIMARY KEY(id))";
			
			System.out.println(sql5);
			stmt.executeUpdate(sql5);
		}
		
		String sql2="SELECT * from infoAttribute where p_info=?";
		
		
		pst2= con.prepareStatement(sql2);
		pst2.setString(1,data);
		
		ResultSet rs2 = pst2.executeQuery();
		System.out.println("\n Attributes");
		while(rs2.next())
		{
		System.out.println("hi");
		String aa2 = rs2.getString("attribute");
		if(aa2.indexOf(' ') != -1)
			{
			
			aa2=aa2.replace(" ","_");
			System.out.println(aa2);
			}
		String dt= rs2.getString("dataType");
		
		System.out.println(aa2+"  "+dt);
		//stmt2 = con.createStatement();
		String sql4 = "ALTER TABLE "+aa+" ADD "+aa2+" "+dt;
		System.out.println(sql4);
		
		//pst3= con.prepareStatement(sql4);
		stmt.executeUpdate(sql4);
		}
	
		}
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	
	
		}
}
