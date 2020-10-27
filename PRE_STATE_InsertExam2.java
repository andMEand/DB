

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PRE_STATE_InsertExam2 {

	/*
	 * CREATE TABLE MEMBER( HAKBUN CHAR(4) PRIMARY KEY, NAME VARCHAR2(10), ADDR
	 * VARCHAR2(10), PHONE CHAR(13) );
	 * 
	 * 
	 * select*from member;
	 */

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver"; // 고정값
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con = null; //
		PreparedStatement pstmt = null; //

		String sql = "Insert Into member (hakbun, name, addr, phone)" + " Values(?,?,?,?)";
		String hakbun, name, addr, phone;

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Member 테이블에 값 추가하기.....");
			System.out.println("학번 입력 : ");
			hakbun = br.readLine();
			System.out.println("이름 입력 : ");
			name = br.readLine();
			System.out.println("주소 입력 : ");
			addr = br.readLine();
			System.out.println("핸드폰번호 입력 : ");
			phone = br.readLine();

			System.out.println(sql);
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "123456");

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hakbun);
			pstmt.setString(2, name);
			pstmt.setString(3, addr);
			pstmt.setString(4, phone);
			pstmt.executeUpdate();

			System.out.println("데이터 추가 성공!");
		} catch (Exception e) {
			System.out.println("데이터베이스 연결 실패! = " + e.getMessage());
		} finally {
			try {
				if (con != null) con.close();
				if (pstmt != null)pstmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}

	}

}
