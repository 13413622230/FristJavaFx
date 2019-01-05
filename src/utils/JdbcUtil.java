/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 
 * @author wuzhuhao
 */
public class JdbcUtil {
	// ��������������ݿ�����
	final static String driverName = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/pharmacy";
	private static final String USER = "root";
	private static final String PASSWORD = "123123";

	// ��̬����飨�������������������ݿ���뾲̬���У�
	static {

	}

	// �����ṩһ����������ȡ���ݿ�����
	public static Connection getConnection() {
		Connection con = null;
		try {
			// 1.������������
			Class.forName(driverName);
			// System.out.println("���������ɹ���");
			// 2.������ݿ������
			con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			// System.out.println("�������ݿ�ɹ���");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void closeJdbc(Connection c, ResultSet rs, Statement statement) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (c != null) {
				c.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�ͷ���Դ����");
		}
	}
}
