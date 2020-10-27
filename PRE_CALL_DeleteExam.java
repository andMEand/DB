/*
CREATE OR REPLACE PROCEDURE CALL_DELETE(P_HAKBUN MEMBER.HAKBUN%TYPE)
IS


BEGIN
    DELETE FROM MEMBER WHERE HAKBUN =P_HAKBUN;
END;
/
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class PRE_CALL_DeleteExam {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con = null;
		CallableStatement cstmt = null;

		String hakbun = null;

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("member 테이블 값 삭제하기....");
			System.out.println("삭제할 학번 입력: ");
			hakbun = br.readLine();
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "123456");

			cstmt = con.prepareCall("{call call_delete(?)}");
			cstmt.setString(1, hakbun);
			cstmt.executeUpdate();// 삭제한 레코드 개수가 반환되지만 사용하지 않는다/버려진다
			
			System.out.println("데이터 베이스 내용 삭제 성공!");
			

		} catch (Exception e) {
			System.out.println("데이터 베이스 연결 실패!" + e.getMessage());
		} finally {
			try {
				/// con.rollback();
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}