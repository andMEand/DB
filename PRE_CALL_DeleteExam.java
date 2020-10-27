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
			System.out.println("member ���̺� �� �����ϱ�....");
			System.out.println("������ �й� �Է�: ");
			hakbun = br.readLine();
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "123456");

			cstmt = con.prepareCall("{call call_delete(?)}");
			cstmt.setString(1, hakbun);
			cstmt.executeUpdate();// ������ ���ڵ� ������ ��ȯ������ ������� �ʴ´�/��������
			
			System.out.println("������ ���̽� ���� ���� ����!");
			

		} catch (Exception e) {
			System.out.println("������ ���̽� ���� ����!" + e.getMessage());
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