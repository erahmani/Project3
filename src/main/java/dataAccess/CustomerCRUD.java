package dataAccess;

import dataAccess.entity.Customer;

import java.sql.*;

public class CustomerCRUD {
    protected static void insertCustomer(Connection conn, Customer customer) {
        String sql = "insert into bank.customer values()";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            customer.setCustomerId(Integer.toString(rs.getInt(1)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteCustomer(String customerId) {
        Connection conn = DB.connectDB();
        String sql = "delete from bank.customer where customerId = " + customerId;
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
