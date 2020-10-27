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
--SYS_REFCURSOR 커서타입의 객체를 전달한다
--V_MEMBER_CURSOR 변수가 전달하는것은 커서타입 객체이다(이해할수있다) 
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

public class PRE_CALL_SelectExam {
//저장 프로시저 호출 
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
	      //이미 저정되어 있는 프로스저를 호출하는 프리페어콜
	      stmt.registerOutParameter(1, OracleTypes.CURSOR);
	      //전달받는 파라메터타입을 registerOutParameter통해서 정의한다 cursor타입으로 설정/전달 받을떄 커서객체를 전달받을 수 있다
	      
	      stmt.executeQuery(); //ResultSet 객체 반환
	      
	      rs = (ResultSet)stmt.getObject(1);
	      //커서는 결봐물을 오브젝트로 받아서 ResultSet으로 받아와서(테이블이니까) rs에 저장
	      
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
	      System.out.println("데이터베이스 연결 실패!");
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