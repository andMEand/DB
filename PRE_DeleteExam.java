import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PRE_DeleteExam {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url ="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con = null;
		Statement stmt = null;
		
		String hakbun = null;
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(System.in));
			System.out.println("member ���̺� �� �����ϱ�....");
			System.out.println("������ �й� �Է�: ");
			hakbun = br.readLine();
			
			String sql = "Delete from member where hakbun ='"
					+hakbun+ "'";
			System.out.println(sql);
			Class.forName(driver);
			con =DriverManager.getConnection(url, "scott","123456");
			//con.setAutoCommit(false);
			stmt = con.createStatement();
			stmt.executeUpdate(sql);//������ ���ڵ� ������ ��ȯ������ ������� �ʴ´�/��������
			//int res ==stmt.executeUpdate ����� �����̶�� �̷���~�ۼ��ϸ�ȴ�
			
			System.out.println("������ ���̽� ���� ���� ����!");
			//con.commit();
			
		}
		catch(Exception e) {
			System.out.println("������ ���̽� ���� ����!"+e.getMessage());
		}
		finally {
			try {
				//con.rollback();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
				
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	

}


