package com.kh.start.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestRun {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql ="""
					SELECT
						ZONE_ID
						,ZONE_NAME
						,ZONE_TYPE
						FROM
							ZONE
						ORDER
							BY
							ZONE_ID DESC
					""";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록!");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE",
												"C##MB",
												"MB");
			System.out.println("연결성공!");
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			System.out.println(rset);
			
			while(rset.next()) {
				String zoneId = rset.getString("ZONE_ID");
				String zoneName = rset.getString("ZONE_NAME");
				String zoneTYPE = rset.getString("ZONE_TYPE");
				
				System.out.println("번호 : "+ zoneId
									+",구역명 : "+zoneName
									+",구역타입 :"+zoneTYPE);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("1.ojdbc.jar 파일을 추가하지않음");
			System.out.println("2.oracle.jdbc.driver.OracleDriver 오타확인");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("jdbc:oracle:thin:@localhost:1521:XE에 오타가있다");
			System.out.println("사용자 계정명/ 비밀번호에 오타가있다");
			System.out.println("SQL문의 문법에 오타가있다");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		try {
			if(rset != null && !rset.isClosed())
			rset.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}try {
			if(stmt != null && !stmt.isClosed())
			stmt.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}try {
			if(conn != null && !conn.isClosed())
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		}
	}

}
