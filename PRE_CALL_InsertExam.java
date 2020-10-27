/*
CREATE OR REPLACE PROCEDURE CALL_INSERT (
HAKBUN MEMBER.HAKBUN%TYPE,
NAME MEMBER.NAME%TYPE, ADDR MEMBER.ADDR%TYPE, PHONE MEMBER.PHONE%TYPE)
IS BEGIN
INSERT INTO MEMBER VALUES(HAKBUN, NAME, ADDR, PHONE);
END;
/
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class PRE_CALL_InsertExam {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con = null;
		CallableStatement cstmt = null;
		String hakbun, name, addr, phone;

		try {
			BufferedReader br = new BufferedReader
					(new InputStreamReader(System.in));
			System.out.println("Member 테이블에 값 추가하기.....");
			System.out.println("학번 입력 : ");
			hakbun = br.readLine();
			System.out.println("이름 입력 : ");
			name = br.readLine();
			System.out.println("주소 입력 : ");
			addr = br.readLine();
			System.out.println("핸드폰번호 입력 : ");
			phone = br.readLine();

			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "123456");

			cstmt = con.prepareCall("{call call_insert(?,?,?,?)}");
											//프로시저이름
			cstmt.setString(1, hakbun);// 밸류의 파라메터 값을 셋메소드로 연결
			cstmt.setString(2, name);
			cstmt.setString(3, addr);
			cstmt.setString(4, phone);
			cstmt.executeUpdate();
			// int res = cstmt.executeUpdate();   //잘했는지 확인하고 싶을땐
			// System.out.println(res);

			System.out.println("데이터베이스 연결 성공");

		} catch (Exception e) {
			System.out.println("데이터베이스 연결 실패!=" + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
				if (cstmt != null)
					cstmt.close();

			} catch (Exception e) {
				System.out.println("데이터베이스 연결 실패!=" + e.getMessage());
			}
		}

	}

}
