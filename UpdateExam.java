import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpdateExam {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url ="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con = null;
		Statement stmt = null;
		
		String hakbun , addr,phone;
		
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(System.in));
			System.out.println("member ���̺� �� �����ϱ�....");
			System.out.print("������ �й� �Է�: ");
			hakbun = br.readLine();
			System.out.print("�� �ּ� �Է�: ");
			addr = br.readLine();
			System.out.print("����ȭ��ȣ �Է�:: ");
			phone =br.readLine();
			
			String sql ="Update member Set addr ='"+ addr;
			sql += "',phone ='"+phone+"'Where hakbun='";
			sql += hakbun +"'";
			System.out.println(sql);
			Class.forName(driver);
			con =DriverManager.getConnection(url, "scott","123456");
			stmt = con.createStatement( );
			stmt.executeUpdate(sql);
			System.out.println("�����ͺ��̽� ���� ���Ť� �ä���!");
		}
		catch(Exception e) {
			System.out.println("������ ���̽� ���� ����!"+e.getMessage());
		}
		finally {
			try {
				if(stmt != null)stmt.close();
				if(con != null) con.close();
				
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

}
