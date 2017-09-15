package com.hmmes.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
	
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	
	static PreparedStatement ps = null;

	private final static String deriver = "com.mysql.jdbc.Driver";
	private final static String url = "jdbc:mysql://59.53.66.227/diyStartext?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
	private final static String user = "jony";
	private final static String password = "123456";

	public static Connection getConnection() {

		try {
			Class.forName(deriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (conn == null) {
				conn = DriverManager.getConnection(url, user, password);
				System.out.println("sussess");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 执行 SQL
	 * 
	 * @param sql
	 * @return
	 */
	public static ResultSet getResultSet(String sql) {
		try {
			BaseDao.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 执行 SQL
	 * 只取出第一条数据
	 * @param sql
	 * @return
	 */
	public static ResultSet getFirstResultSet(String sql) {
		try {
			BaseDao.getConnection();
			ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	/**
	 * 获取表达式
	 * 
	 * @return
	 */
	public static Statement Statement() {

		try {
			if (conn == null) {
				stmt = conn.createStatement();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stmt;
	}

	/**
	 * 释放资源
	 * 
	 */
	public static void closeDBConnection() {

		try {

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {

				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public static void main(String[] args) {
		
		BaseDao.getConnection();
		ResultSet r = BaseDao.getResultSet("select * from babyinfo");
		try {
			while(r.next()){
				System.out.println(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
