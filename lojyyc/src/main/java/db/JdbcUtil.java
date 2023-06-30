package db;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {
	
	public static Connection getConnection() {
		Connection con = null;
		try {
				Context initCtx = new InitialContext();
				DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/OracleDB");
				
				con = ds.getConnection();
				con.setAutoCommit(false);
		} catch(Exception e) {
			System.out.println("db연결에러발생");
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con) {
		try {
			if(con!=null) con.close();
		} catch(Exception e) { System.out.println("con연결해제 오류 :"); e.printStackTrace(); }
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt!=null) stmt.close();
		} catch(Exception e) { System.out.println("stmt연결해제 오류 :"); e.printStackTrace(); }
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs!=null) rs.close();
		}catch(Exception e) { System.out.println("rs연결해제 오류 :"); e.printStackTrace(); }
	}
	
	public static void commit(Connection con) {
		try {con.commit();System.out.println("commit 완료");} catch(Exception e) {System.out.println("commit 오류");}
	}
	
	public static void rollback(Connection con) {
		try {con.rollback();System.out.println("rollback 완료");} catch(Exception e) {System.out.println("rollback 오류");}
	}

}
