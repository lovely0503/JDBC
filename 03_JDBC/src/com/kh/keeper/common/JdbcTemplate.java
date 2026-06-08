package com.kh.keeper.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcTemplate {
	/*
	 * JDBC과정에 사용되는 반복적으로 작성해야하는 코드
	 * => 메소드로 정의해둘 클래스
	 */
	//Driver등록
	public static void registDriver() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//DB연결 정보를 이용해서 Connection생성해서 반환해주는 메소드
	public static Connection getConnection() {
		
		
		try {
			
			Properties prop = new Properties();
			
			prop.load(new FileInputStream("resources/connection.properties"));
			
			//String url = prop.getProperty("URL");
			//System.out.println(url);
			
			Connection conn = DriverManager.getConnection(prop.getProperty("URL")
											,prop.getProperty("USERNAME")
											,prop.getProperty("PASSWORD"));
			conn.setAutoCommit(false);
			
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//Connection 객체를 이용해서 commit 시켜주는 메소드
	public static void commit(Connection conn) {
		
		try {
			if(conn!= null) {
			conn.commit();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	//Connection 객체를 받아서 반납해주는 메소드
	public static void close(Connection conn) {
		try {
			if(null != conn) {
			conn.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}
