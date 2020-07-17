package com.FinalInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.HashMap;

public class dimension 
{
	java.sql.PreparedStatement pst;
	java.sql.PreparedStatement pst2;
	java.sql.PreparedStatement pst3;
	java.sql.PreparedStatement pst1;
	java.sql.PreparedStatement pst12;
	java.sql.PreparedStatement pst13;
	String dbname="P3";
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
	public dimension(Connection con)
	{
		this.con=con;
	}

	public void fetchInfoFromTable()
	{
	
		
		///////FACTT/////
		String sql1="SELECT * from category_attributes";
		try {
			
		pst= con.prepareStatement(sql1);
		//pst.setString(1, info);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
		{
		String aa = rs.getString("category_name");
		String data=rs.getString("p_info");
		
		ResultSet rs2 = con.getMetaData().getTables(null, null,aa, null);
		try {
		if (!rs2.next()) { 
			if(aa.indexOf(' ') != -1)
			{
			aa=aa.replace(" ","_");
			}
			stmt = con.createStatement();
			String sql5="CREATE TABLE "+ aa+
					"("+aa+"_id VARCHAR(100) NOT NULL"+
							" PRIMARY KEY ("+aa+"_id))";
			
			System.out.println(sql5);
			stmt.executeUpdate(sql5);
			/*
			String sql7="SELECT * from category_subcategory where category_name=?";
			pst2= con.prepareStatement(sql7);
			pst2.setString(1,aa);
			
			ResultSet rs7 = pst2.executeQuery();
			String a=rs7.getString("category_name");
			
			
			ResultSet rs8 = con.getMetaData().getTables(null, null, a,null);
			if(!rs8.next())
			{
				String sqla="CREATE TABLE "+ a+
						"("+a+"_id VARCHAR(100) NOT NULL"+
								" PRIMARY KEY ("+a+"_id))";
				
				System.out.println(sqla);
				stmt.executeUpdate(sqla);
			
			
			}
			*/
			
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		
		String sql3="SELECT * from category_attributes where p_info=?";
		
		
		pst2= con.prepareStatement(sql3);
		pst2.setString(1,data);
		
		ResultSet rs3 = pst2.executeQuery();
		System.out.println(rs3.next());
		System.out.println("\n Attributes");
		while(rs3.next())
		{
		//System.out.println("hi");
		String aa2 = rs3.getString("attribute_name");
		String dt= rs3.getString("dataType");
		String cat=rs3.getString("category_name");
		
		ResultSet rs4 = con.getMetaData().getColumns(null, null, aa,aa2);
		System.out.println(rs4);
		try {
		if (!rs4.next()) { 
			if(aa2.indexOf(' ') != -1)
			{
			aa2=aa2.replace(" ","_");
			}
			if(cat.equals(aa))
			{
			stmt = con.createStatement();
			String sql6="ALTER TABLE "+aa+" ADD "+aa2+" "+dt  ;
			
			System.out.println(sql6);
			stmt.executeUpdate(sql6);
			}
			
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		
		}
		
		//System.out.println("Bye");
		
		}
		}
	
		}
		
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	
	
		}
}
