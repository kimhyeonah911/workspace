package com.kh.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
		public static Connection getConnection() {
			Connection conn = null;
			Properties prop = new Properties();
			
			//JDBCTemplate클래스 위치를 시준으로 클래스패스에서 "/db/driver/driver.properties" 리소스의 물리적 경로를 가져온다.
			String filePath = JDBCTemplate.class.getResource("/db/driver/driver.properties").getPath();
			
			try {
				prop.load(new FileInputStream(filePath));
				
				Class.forName(prop.getProperty("driver"));
				
				conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
				conn.setAutoCommit(false);
				//System.out.println("연결성공");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return conn;
		}
		
		public static void commit(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.commit();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		public static void rollback(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.rollback();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
			
		public static void close(Statement stmt) {
			try {
				if(stmt != null && !stmt.isClosed())
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		public static void close(Connection conn) {
			try {
				if(conn != null && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
			public static void close(ResultSet rset) {
				try {
					if(rset != null && !rset.isClosed())
						rset.close();
				} catch (SQLException e) {
						e.printStackTrace();
					}
				}
}
