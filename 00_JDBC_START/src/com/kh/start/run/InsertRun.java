package com.kh.start.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertRun {

	public static void main(String[] args) {
		// JDBC 맛보기
		/*
		 * JAVA를 사용하지 않았을때 ZONE테이블에 한 행 인서트하려면 어떤절차가 필요했지?
		 * 
		 * 1. 디비버를 실행한다(클라이언트 프로그램)
		 * 
		 * 2. 접속하기(콘센트)누름
		 * 
		 * 3. ORACLE선택
		 * 
		 * 4. IP주소,PORT번호, 사용자이름, 비밀번호 가지고 연결
		 * 
		 * 5. 새 SQL편집기
		 * 
		 * 6. INSERT문 작성 => INSERT INTO ZONE VALUES('값','값','값')
		 * 
		 * 7. SQL문을 실행
		 * 
		 * 8. UpdatedRows : 1
		 * 
		 * 9. COMMIT
		 */
		// 0) 필요한 변수 세팅
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		//ZONE 테이블에 사용자가 입력한값을 DBMS로 보내서 => ZONE테이블에 INSERT
		Scanner sc = new Scanner(System.in);
		System.out.println("zone_ID를 입력해주세요");
		String zoneId = sc.nextLine();
		System.out.println("ZONE_NAME을 입력해주세요");
		String zoneName = sc.nextLine();
		System.out.println("ZONE_TYPE을 입력해주세요");
		String zoneType = sc.nextLine();
		
		String sql = "INSERT INTO ZONE VALUES('50','테스트','테스트')";
		sql = "INSERT INTO ZONE VALUES('"+zoneId+"',"
										+"'"+ zoneName+"',"
										+"'"+zoneType+"')";
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ZONE VALUES");
		sb.append("'"+zoneId+"',");
		sb.append("'"+zoneName+"',");
		sb.append("'"+zoneType+"',");
		sql = sb.toString();
		//나는 오라클 DBMS에다가 작업할거다
		
		//리플렉션을 사용해서 동적으로 클래스 로드
		//1)JDBC Driver 등록 -> ORACLE -> ojdbc.jar
		//2) DB서버랑 연결(IP주소, PORT번호, 사용자이름, 비밀번호)
		
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driver등록 성공!");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE",
												"C##MB",
												"MB");
			System.out.println("DB연결 성공!");
			
			//학습을 위해 오토커밋 끄기
			conn.setAutoCommit(false);
		
			//3)새 SQL편집기 열기
			stmt = conn.createStatement();
			System.out.println("Statement 객체 생성");
			
			//4)SQL문 실행
			// executeUpdate(DML문)
			result = stmt.executeUpdate(sql);
			System.out.println("SQL문 실행 성공!");
			
			if(result > 0) {
				conn.commit();
			}
	
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("65행을 가시오");
			System.out.println("1. ojdbc.jar 파일을 추가하지않음");
			System.out.println("2. oracle.jdbc.driver.OracleDriver 오타확인");
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("URL을 잘못적은경우 -> 69행오타확인");
			System.out.println("username / password login denied->70행 71행 오타확인 ");
			System.out.println("SQLSyntaxErrorException -> SQL문 오타확인");
			System.out.println("NullPointerException -> JDBC객체들 확인하기");
		}finally {
			try {
			stmt.close();
			conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			}
		
		}

}
