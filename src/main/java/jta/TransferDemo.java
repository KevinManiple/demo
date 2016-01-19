package jta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TransferDemo {

	public static void main(String[] args) throws Exception {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("");
			connection = DriverManager.getConnection("");
			connection.setAutoCommit(false);
			ps = connection.prepareStatement("");
			ps.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (connection != null) connection.close();
		}
	}
}
