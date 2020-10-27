import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PRE_TestJDBC {

	public static void main(String[] args) throws Exception{
		//driver로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@127.0.0.1:1521:orcl","scott","123456");
				//위 url로 스콧과 비번으로 접속이 잘되면 연결이 됙 안되면 널값이 반환된다
		//Statement객체생성
		Statement stmt = con.createStatement();
		//sql명령문을 실행하기 우해선 연결객체로 Statement객체를 만들어줘야 sql구문을실행시킬수 있다
		
		
		//sql
		String sql = "select ename, deptno from emp";
		
		//sql 실행후 resultsset객체반환
		//테이블형태의 객체반환된다
		ResultSet rset = stmt.executeQuery(sql);
		int deptno =0;
		String name = null;
		
		while(rset.next()) {
			name=rset.getNString("ename");
			deptno=rset.getInt("deptno");
			//get타입 읽어올 데이터 타입을 맞게 메소드를 작성해야 한다
			System.out.println( name +" "+deptno);
			
		}

	}
}
//메소드
// ResultSet rset.executeQuery() 셀랙트 구문에만 쓴다
// ResultSet rset.executeUpdate() 인서트 업데이트 델레트

//executeQuery()실행되면 셀랙트한 테이블구조를 반환한다 (오라클에서의 emp테이블모앙데이터) 이걸 리절트셋타입의 객체로 참조한다
//rset.next()실행결과의 다음행 위치로 이동하는 메소드 읽으면 투루 못읽으면 폴스
//14개중에 이네임과 뎁엔오 읽어온다 한행씩

//
//========================
/*
 1. statement
 
 2. PreparedStatment
 
 3. CallableStatement 
 	저장함수 , 저장프로시저를사용할때 
 
 2. PreparedStatment 많이 사용하는 방식
 ===데이터베이스 연동 순서
 1. 라이브러리 연결
 2. try  catch
 	변수선언은 try  catch바깥
 	드라이드 로딩
 	연결객체 생성
 	stmt sql국문을 실행하기위한 객체 -익스큐트 쿼리(셀렉티)-리절트셋객체반환, 업데이트(인서트 델레트 업데잍)-인서트 델레트 업데잍한 레코드 객수
 	rset.next() 첫번째행 읽기 성공 (투루) 열단위로 (컬럼)읽어오는것 
 	파이너리_클로즈
 	
 */
