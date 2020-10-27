import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PRE_InsertExam {
	
	/*
	 CREATE TABLE MEMBER(
    HAKBUN CHAR(4) PRIMARY KEY,
    NAME VARCHAR2(10),
    ADDR VARCHAR2(10),
    PHONE CHAR(13)
    );


select*from member;
	 */
	 

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver"; // ������
	    String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl"; 
	    Connection con = null; //
	    Statement stmt = null; //
	    
	    String sql = "Insert Into member (hakbun, name, addr, phone)" + " Values";
	    //���� �������� �������彺���� ���Ұ��̴�
	    
	    String hakbun, name, addr, phone;
	    BufferedReader br = null;
	    try {
	      br = new BufferedReader(new InputStreamReader(System.in));
	      System.out.println("Member ���̺� �� �߰��ϱ�.....");
	      System.out.println("�й� �Է� : ");
	      hakbun = br.readLine();
	      System.out.println("�̸� �Է� : ");
	      name = br.readLine();
	      System.out.println("�ּ� �Է� : ");
	      addr = br.readLine();
	      System.out.println("�ڵ�����ȣ �Է� : ");
	      phone = br.readLine();
	      
	      sql += "('" + hakbun + "', '" + name +"', '"+ addr +"', '"+phone+"')";
	      System.out.println(sql);
	      Class.forName(driver);
	      con = DriverManager.getConnection(url, "scott","123456");
	      stmt = con.createStatement();
	      int res = stmt.executeUpdate(sql); //executeUpdate �� ���ڵ� ������ ��ȯ�Ѵ�(������)
	      if(res ==1) //���� sql 1�� �����߱� ������ ���� 1�� ���Ե�
	        System.out.println("������ �߰� ����!");
	    }
	    catch(Exception e) {
	        System.out.println("�����ͺ��̽� ���� ����! = "+ e.getMessage());
	    }
	    finally {
	      try {
	        if(con!=null) con.close();
	      }
	      catch(Exception e) {
	        System.out.println(e.getMessage());
	      }
	      try {
	        if(stmt != null) stmt.close();
	      }
	      catch(Exception e) {
	        System.out.println(e.getMessage());
	      }
	      try {
	        if(br != null) br.close();
	      }
	      catch(Exception e) {
	        System.out.println(e.getMessage());
	      }
	    
	    }

	  }

	}
