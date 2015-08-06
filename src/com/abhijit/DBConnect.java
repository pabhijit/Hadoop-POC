package com.abhijit;

import java.sql.*;

public class DBConnect {

	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
	static final String DB_URL = "jdbc:oracle:thin:@devrac1-vip.valuecentric.com:1521/reportdv_serv";

	//  Database credentials
	static final String USER = "VACATP";
	static final String PASS = "VCATP17!";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "select * from VALUECENTRIC_1.abc_drefs where rokid = 713115";
			ResultSet rs = stmt.executeQuery(sql);
			int id = 0;
			while(rs.next()){
				id  = rs.getInt("id");
			}
			System.out.println("Id : "+ id);
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException sqle){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}
		}
	}//end main
}
