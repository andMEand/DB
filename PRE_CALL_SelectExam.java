/*
CREATE OR REPLACE PROCEDURE CALL_SELECT(
    V_MEMBER_CURSOR OUT SYS_REFCURSOR
    )
IS
BEGIN
    OPEN V_MEMBER_CURSOR
    FOR
    SELECT*FROM MEMBER
    ORDER BY HAKBUN;
END;
/
--IN OUT INOUT
--SYS_REFCURSOR Ŀ��Ÿ���� ��ü�� �����Ѵ�
--V_MEMBER_CURSOR ������ �����ϴ°��� Ŀ��Ÿ�� ��ü�̴�(�����Ҽ��ִ�) 
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

public class PRE_CALL_SelectExam {
//���� ���ν��� ȣ�� 
	public static void main(String[] args) {
	    String driver = "oracle.jdbc.driver.OracleDriver";
	    String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	    Connection con =null;
	    CallableStatement stmt = null;
	    ResultSet rs = null;
	    
	    try {
	      Class.forName(driver);
	      con = DriverManager.getConnection(url,"scott","123456");
	      
	      stmt = con.prepareCall("{call call_select(?)}");
	      //�̹� �����Ǿ� �ִ� ���ν����� ȣ���ϴ� ���������
	      stmt.registerOutParameter(1, OracleTypes.CURSOR);
	      //���޹޴� �Ķ����Ÿ���� registerOutParameter���ؼ� �����Ѵ� cursorŸ������ ����/���� ������ Ŀ����ü�� ���޹��� �� �ִ�
	      
	      stmt.executeQuery(); //ResultSet ��ü ��ȯ
	      
	      rs = (ResultSet)stmt.getObject(1);
	      //Ŀ���� ������� ������Ʈ�� �޾Ƽ� ResultSet���� �޾ƿͼ�(���̺��̴ϱ�) rs�� ����
	      
	      System.out.println("hakbun\tname\taddr\tphone");
	      System.out.println("-----------------------------------------------");
	      while(rs.next()) {
	        System.out.print(rs.getString("hakbun")+"\t");
	        System.out.print(rs.getString("name")+"\t");
	        System.out.print(rs.getString("addr")+"\t");
	        System.out.print(rs.getString("phone")+"\n");
	      }
	    }
	    catch(Exception e) {
	      System.out.println("�����ͺ��̽� ���� ����!");
	      e.printStackTrace();
	    }
	    finally {
	      try {
	        if(rs!=null) rs.close();
	        if(stmt !=null) stmt.close();
	        if(con != null) con.close();
	      }
	      catch(Exception e) {
	        System.out.println(e.getMessage());
	      }
	    }
	  }
	}