import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PRE_TestJDBC {

	public static void main(String[] args) throws Exception{
		//driver�ε�
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@127.0.0.1:1521:orcl","scott","123456");
				//�� url�� ����� ������� ������ �ߵǸ� ������ �� �ȵǸ� �ΰ��� ��ȯ�ȴ�
		//Statement��ü����
		Statement stmt = con.createStatement();
		//sql��ɹ��� �����ϱ� ���ؼ� ���ᰴü�� Statement��ü�� �������� sql�����������ų�� �ִ�
		
		
		//sql
		String sql = "select ename, deptno from emp";
		
		//sql ������ resultsset��ü��ȯ
		//���̺������� ��ü��ȯ�ȴ�
		ResultSet rset = stmt.executeQuery(sql);
		int deptno =0;
		String name = null;
		
		while(rset.next()) {
			name=rset.getNString("ename");
			deptno=rset.getInt("deptno");
			//getŸ�� �о�� ������ Ÿ���� �°� �޼ҵ带 �ۼ��ؾ� �Ѵ�
			System.out.println( name +" "+deptno);
			
		}

	}
}
//�޼ҵ�
// ResultSet rset.executeQuery() ����Ʈ �������� ����
// ResultSet rset.executeUpdate() �μ�Ʈ ������Ʈ ����Ʈ

//executeQuery()����Ǹ� ����Ʈ�� ���̺����� ��ȯ�Ѵ� (����Ŭ������ emp���̺��ӵ�����) �̰� ����Ʈ��Ÿ���� ��ü�� �����Ѵ�
//rset.next()�������� ������ ��ġ�� �̵��ϴ� �޼ҵ� ������ ���� �������� ����
//14���߿� �̳��Ӱ� ������ �о�´� ���྿

//
//========================
/*
 1. statement
 
 2. PreparedStatment
 
 3. CallableStatement 
 	�����Լ� , �������ν���������Ҷ� 
 
 2. PreparedStatment ���� ����ϴ� ���
 ===�����ͺ��̽� ���� ����
 1. ���̺귯�� ����
 2. try  catch
 	���������� try  catch�ٱ�
 	����̵� �ε�
 	���ᰴü ����
 	stmt sql������ �����ϱ����� ��ü -�ͽ�ťƮ ����(����Ƽ)-����Ʈ�°�ü��ȯ, ������Ʈ(�μ�Ʈ ����Ʈ ������)-�μ�Ʈ ����Ʈ �������� ���ڵ� ����
 	rset.next() ù��°�� �б� ���� (����) �������� (�÷�)�о���°� 
 	���̳ʸ�_Ŭ����
 	
 */
