package com.kh.start.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Test {
	public static void main(String args[]) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		Scanner sc =new Scanner(System.in);
		System.out.println("ZONE_ID를 입력해주세요");
		String zoneId = sc.nextLine();
		System.out.println("ZONE_NAME을 입력해주세요");
		String zoneName = sc.nextLine();
		System.out.println("ZONE_TYPE을 입력해주세요");
		String zoneType = sc.nextLine();
		
		String sql = "INSERT INTO ZONE VALUES('50,'테스트','테스트')";
		sql = "INSERT INTO ZONE VALUES('"+zoneId+"'"+zoneName+"'"+zoneType+"')";
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ZONE VALUES");
		sb.append("'"+zoneId+"',");
		sb.append("'"+zoneName+"',");
		sb.append("'"+zoneType+"',");
		sql = sb.toString();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver등록 성공");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","C##MB","MB");
			System.out.println("DB연결 성공!");
			
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			System.out.println("Statement 객체생성");
			
			result = stmt.executeUpdate(sql);
			System.out.println("SQL실행문 성공");
			
			if(result>0) {
				conn.commit();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("65행을가시오");
			System.out.println("1. object.jar 파일을 추가하지않음");
			System.out.println("2. oracle.jdbc.driver.OracleDriver 오타확인");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("URL을 잘못적은경우 ->69행오타확인");
			System.out.println("username / password login denied ->70행 71행 오타확인");
			System.out.println("SQLSyntaErrorException -> SQL문 오타확인");
			System.out.println("NULLPointerException -> JDBC객체들 확인하기");
		}finally{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
