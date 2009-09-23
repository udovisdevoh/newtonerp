package newtonERP.test.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SqLiteTest2 {
	private static Connection conn;

	public static void main(String args[]) {
			initializeConnection();
			insert10000Rows();
	}

	public static void initializeConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void insert10000Rows() {
		System.out.println("Timer started");
		long time = System.currentTimeMillis();
		
		try {
			PreparedStatement prep = conn
					.prepareStatement("insert into people values (?, ?);");

			for (int i = 0; i < 10000; i++) {

				prep.setString(1, String.valueOf(i));
				prep.setString(2, "Toto");
				prep.addBatch();
			}

			conn.setAutoCommit(false);
			prep.executeBatch();
			conn.setAutoCommit(true);
		
		System.out.println("Commit done in " + String.valueOf((System.currentTimeMillis() - time) / 1000) + " sec");
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
