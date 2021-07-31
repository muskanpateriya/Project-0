package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.database.ConnectionFactory;

public class Transactions {

	public static void main(String[] args) {
		Connection connection = null;
		

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "select sender,reciever,amount,date from transactions WHERE date > DATE_SUB(NOW(), INTERVAL 24 HOUR)\r\n"
					+ "  AND date <= NOW()";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			System.out.println("Transaction History : \n");
			System.out.println("Transaction in last 24 Hours :\n");
			System.out.println("\tFROM\t |\tTo \t|\tAMOUNT\t|\tDATE");
			System.out.println();
			
			while (rs.next()) {
				System.out.println("\t"+rs.getString(1)+"\t | "+"\t"+rs.getString(2)+"\t|"+"\t"+rs.getString(3)+"\t|"+"\t"+rs.getString(4));
				System.out.println();
			}
			
			String sql2 = "select sender,reciever,amount,date from transactions WHERE date > DATE_SUB(NOW(), INTERVAL 10 DAY)\r\n"
					+ "  AND date <= NOW()";
			PreparedStatement ps2 = connection.prepareStatement(sql2);

			ResultSet rs2 = ps2.executeQuery();
			
			System.out.println("Transaction in last 10 Days :\n");
			System.out.println("\tFROM\t |\tTo \t|\tAMOUNT\t|\tDATE");
			System.out.println();
			
			while (rs2.next()) {
				System.out.println("\t"+rs2.getString(1)+"\t | "+"\t"+rs2.getString(2)+"\t|"+"\t"+rs2.getString(3)+"\t|"+"\t"+rs2.getString(4));
				System.out.println();
			}
			
			String sql3 = "select sender,reciever,amount,date from transactions WHERE date > DATE_SUB(NOW(), INTERVAL 1 MONTH)\r\n"
					+ "  AND date <= NOW()";
			PreparedStatement ps3 = connection.prepareStatement(sql3);

			ResultSet rs3 = ps3.executeQuery();
			System.out.println("Transaction in last 1 Month :\n");
			System.out.println("\tFROM\t |\tTo \t|\tAMOUNT\t|\tDATE");
			System.out.println();
			
			while (rs3.next()) {
				System.out.println("\t"+rs3.getString(1)+"\t | "+"\t"+rs3.getString(2)+"\t|"+"\t"+rs3.getString(3)+"\t|"+"\t"+rs3.getString(4));
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
