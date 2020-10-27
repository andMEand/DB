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
		String driver = "oracle.jdbc.driver.OracleDriver"; // 고정값
	    String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl"; 
	    Connection con = null; //
	    Statement stmt = null; //
	    
	    String sql = "Insert Into member (hakbun, name, addr, phone)" + " Values";
	    //값이 많을때는 프리페어드스텟이 편리할것이다
	    
	    String hakbun, name, addr, phone;
	    BufferedReader br = null;
	    try {
	      br = new BufferedReader(new InputStreamReader(System.in));
	      System.out.println("Member 테이블에 값 추가하기.....");
	      System.out.println("학번 입력 : ");
	      hakbun = br.readLine();
	      System.out.println("이름 입력 : ");
	      name = br.readLine();
	      System.out.println("주소 입력 : ");
	      addr = br.readLine();
	      System.out.println("핸드폰번호 입력 : ");
	      phone = br.readLine();
	      
	      sql += "('" + hakbun + "', '" + name +"', '"+ addr +"', '"+phone+"')";
	      System.out.println(sql);
	      Class.forName(driver);
	      con = DriverManager.getConnection(url, "scott","123456");
	      stmt = con.createStatement();
	      int res = stmt.executeUpdate(sql); //executeUpdate 는 레코드 갯수를 반환한다(정수형)
	      if(res ==1) //현재 sql 1번 삽입했기 때문에 숫자 1이 대입됨
	        System.out.println("데이터 추가 성공!");
	    }
	    catch(Exception e) {
	        System.out.println("데이터베이스 연결 실패! = "+ e.getMessage());
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
