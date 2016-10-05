package dataAccess;

import dataAccess.entity.RealCustomer;

import java.sql.*;
import java.util.LinkedList;


public class RealCustomerCRUD extends CustomerCRUD {
    public static void insertRealCustomer(RealCustomer realCustomer) {
        Connection conn = DB.connectDB();
        try {
            insertCustomer(conn, realCustomer);
            String queryStr = "insert into realcustomer(customerId, firstName, lastName, fatherName, birthDay, nationalId) values(?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(queryStr);
            preparedStatement.setString(1, realCustomer.getCustomerId());
            preparedStatement.setString(2, realCustomer.getFirstName());
            preparedStatement.setString(3, realCustomer.getLastName());
            preparedStatement.setString(4, realCustomer.getFatherName());
            preparedStatement.setString(5, realCustomer.getBirthDay());
            preparedStatement.setString(6, realCustomer.getNationalId());
            preparedStatement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static LinkedList<RealCustomer> prepareRealCustomerSelectResult(ResultSet resultSet) {
        LinkedList<RealCustomer> realCustomerList = new LinkedList();
        try {
            while (resultSet.next()) {
                RealCustomer realCustomer = new RealCustomer();
                realCustomer.setFirstName(resultSet.getString("firstName"));
                realCustomer.setLastName(resultSet.getString("lastName"));
                realCustomer.setFatherName(resultSet.getString("fatherName"));
                realCustomer.setBirthDay(resultSet.getString("birthDay"));
                realCustomer.setNationalId(resultSet.getString("nationalId"));
                realCustomer.setCustomerId(resultSet.getString("customerId"));
                realCustomerList.add(realCustomer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return realCustomerList;
    }

    public static LinkedList<RealCustomer> selectRealCustomer(String searchOption, String searchValue) {
        Connection conn = DB.connectDB();
        LinkedList<RealCustomer> realCustomerList = null;
        try {
            String queryStr = "SELECT * FROM realcustomer WHERE " + searchOption + " = '" + searchValue + "'";
            PreparedStatement preparedStatement = conn.prepareStatement(queryStr);
            ResultSet resultSet = preparedStatement.executeQuery(queryStr);
            if (resultSet != null) {
                realCustomerList = prepareRealCustomerSelectResult(resultSet);
            }
            resultSet.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return realCustomerList;
    }

    public static void updateRealCustomer(RealCustomer realCustomer) {
        try {
            Connection conn = DB.connectDB();
            String queryStr = "UPDATE RealCustomer SET firstName=?, lastName=?, fatherName=?, birthDay=?, nationalId=? WHERE customerId = " + realCustomer.getCustomerId();
            PreparedStatement stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, realCustomer.getFirstName());
            stmt.setString(2, realCustomer.getLastName());
            stmt.setString(3, realCustomer.getFatherName());
            stmt.setString(4, realCustomer.getBirthDay());
            stmt.setString(5, realCustomer.getNationalId());

            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
