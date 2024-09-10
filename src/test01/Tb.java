package test01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Tb{
	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:oracle";
	private static final String USER = "web5";
	private static final String PASSWORD = "0910";

	public static void main(String[] args) {
		Connection conn = null;

		try{
			Class.forName(JDBC_DRIVER);
			System.out.println("드라이버 로드 성공");
			
			conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			System.out.println("데이터베이스 연결 성공");
			
			String insertSQL = """
					INSERT INTO member(id, name, age, addr)
					VALUES(?, ?, ?, ?);
					""";
			
			PreparedStatement pstmt = conn.prepareStatement(insertSQL);
			pstmt.setInt(1, 7);
			pstmt.setString(2, "한여름");
			pstmt.setInt(3, 20);
			pstmt.setString(4, "전주");
			
			int insertCount = pstmt.executeUpdate();
			System.out.println(insertCount + "개의 행을 추가했습니다.");
			
			pstmt.close();
			conn.close();
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException se) {
					
				}
			}
		}
		
	}
}
