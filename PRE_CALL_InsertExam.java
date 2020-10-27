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
			System.out.println("Member ���̺� �� �߰��ϱ�.....");
			System.out.println("�й� �Է� : ");
			hakbun = br.readLine();
			System.out.println("�̸� �Է� : ");
			name = br.readLine();
			System.out.println("�ּ� �Է� : ");
			addr = br.readLine();
			System.out.println("�ڵ�����ȣ �Է� : ");
			phone = br.readLine();

			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "123456");

			cstmt = con.prepareCall("{call call_insert(?,?,?,?)}");
											//���ν����̸�
			cstmt.setString(1, hakbun);// ����� �Ķ���� ���� �¸޼ҵ�� ����
			cstmt.setString(2, name);
			cstmt.setString(3, addr);
			cstmt.setString(4, phone);
			cstmt.executeUpdate();
			// int res = cstmt.executeUpdate();   //���ߴ��� Ȯ���ϰ� ������
			// System.out.println(res);

			System.out.println("�����ͺ��̽� ���� ����");

		} catch (Exception e) {
			System.out.println("�����ͺ��̽� ���� ����!=" + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
				if (cstmt != null)
					cstmt.close();

			} catch (Exception e) {
				System.out.println("�����ͺ��̽� ���� ����!=" + e.getMessage());
			}
		}

	}

}
