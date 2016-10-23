package dataAccess;

import dataAccess.entity.LegalCustomer;

import java.sql.*;
import java.util.LinkedList;

public class LegalCustomerCRUD extends CustomerCRUD {

    public static void insertLegalCustomer(LegalCustomer legalCustomer) {
        Connection conn = DB.connectDB();
        try {
            insertCustomer(conn, legalCustomer);
            String queryStr = "insert into legalcustomer(customerId, companyName, registrationDate, economicCode) values(?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(queryStr);
            preparedStatement.setString(1, legalCustomer.getCustomerId());
            preparedStatement.setString(2, legalCustomer.getCompanyName());
            preparedStatement.setString(3, legalCustomer.getRegistrationDate());
            preparedStatement.setString(4, legalCustomer.getEconomicCode());
            System.out.println(legalCustomer);
            preparedStatement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static LinkedList<LegalCustomer> prepareLegalCustomerSelectResult(ResultSet resultSet) {
        LinkedList<LegalCustomer> legalCustomerList = new LinkedList<LegalCustomer>();
        try {
            while (resultSet.next()) {
                LegalCustomer legalCustomer = new LegalCustomer();
                legalCustomer.setCompanyName(resultSet.getString("companyName"));
                legalCustomer.setEconomicCode(resultSet.getString("economicCode"));
                legalCustomer.setRegistrationDate(resultSet.getString("registrationDate"));
                legalCustomer.setCustomerId(resultSet.getString("customerId"));
                legalCustomerList.add(legalCustomer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return legalCustomerList;
    }

    public static LinkedList<LegalCustomer> selectLegalCustomer(String searchOption, String searchValue) {
        Connection conn = DB.connectDB();
        LinkedList<LegalCustomer> legalCustomer = null;
        try {
//            String queryStr = "SELECT * FROM legalcustomer WHERE " + searchOption + " = '" + searchValue + "'";
            String queryStr = "SELECT * FROM legalcustomer WHERE " + searchOption + " = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(queryStr);
            preparedStatement.setString(1, searchValue);
            ResultSet resultSet = preparedStatement.executeQuery(queryStr);
            if (resultSet != null) {
                legalCustomer = prepareLegalCustomerSelectResult(resultSet);
            }
            resultSet.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return legalCustomer;
    }

    public static void updateLegalCustomer(LegalCustomer legalCustomer) {
        try {
            Connection conn = DB.connectDB();
            String queryStr = "UPDATE LegalCustomer SET companyName=?, registrationDate=?, economicCode=? WHERE customerId = " + legalCustomer.getCustomerId();

            PreparedStatement stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, legalCustomer.getCompanyName());
            stmt.setString(2, legalCustomer.getRegistrationDate());
            stmt.setString(3, legalCustomer.getEconomicCode());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
