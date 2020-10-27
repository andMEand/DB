/*
create or replace procedure call_update (p_hakbun member.hakbun%type,
		p_addr member.addr%type, p_phone member.phone%type)
is
begin
update member set   addr = p_addr,
phone =p_phone
where hakbun =p_hakbun;
end;
/
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;



public class PRE_CALL_UpdateExam {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con = null;
		CallableStatement cstmt = null;
		
		String hakbun, addr, phone;
		
		try {
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			System.out.println("member ���̺� �� �����ϱ�....");
			System.out.print("������ �й� �Է�: ");
			hakbun = br.readLine();
			System.out.print("�� �ּ� �Է�: ");
			addr = br.readLine();
			System.out.print("����ȭ��ȣ �Է�:: ");
			phone =br.readLine();
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,"scott","123456");
			
			cstmt = con.prepareCall("{call call_update(?,?,?)}");
			//����Ʈ(����)
			cstmt.setString(1, hakbun);
			cstmt.setString(2, addr);
			cstmt.setString(3, phone);
			int res = cstmt.executeUpdate();
			
			System.out.println("�����ͺ��̽� ���� ����!");
		}
		catch(Exception e) {
			System.out.println("�����ͺ��̽� ���� ����!+"+e.getMessage());
			
		}
		finally {
			try {
				if(con != null)con.close();
				if(cstmt != null)cstmt.close();
				
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
