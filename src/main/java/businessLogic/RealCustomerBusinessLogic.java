package businessLogic;

import businessLogic.exception.DuplicateUniqueCodeException;
import businessLogic.exception.EmptyFieldException;
import dataAccess.RealCustomerCRUD;
import dataAccess.entity.RealCustomer;

import java.util.LinkedList;

public class RealCustomerBusinessLogic extends CustomerBusinessLogic {

    private static void realCustomerFieldValidation(RealCustomer realCustomer) {
        if (realCustomer.getFirstName().isEmpty()) {
            throw new EmptyFieldException("First Name Is Empty!");
        }
        if (realCustomer.getLastName().isEmpty()) {
            throw new EmptyFieldException("Last Name Is Empty!");
        }
        if (realCustomer.getFatherName().isEmpty()) {
            throw new EmptyFieldException("Father Name Is Empty!");
        }
        if (realCustomer.getBirthDay().isEmpty()) {
            throw new EmptyFieldException("Birth Day Is Empty!");
        }
        if (realCustomer.getNationalId().isEmpty()) {
            throw new EmptyFieldException("National ID Is Empty!");
        }
    }

    public static void createNewCustomer(RealCustomer realCustomer) {
        realCustomerFieldValidation(realCustomer);
        LinkedList<RealCustomer> realCustomerList = RealCustomerCRUD.selectRealCustomer("nationalId", realCustomer.getNationalId());
        if (realCustomerList.size() == 0) {
            RealCustomerCRUD.insertRealCustomer(realCustomer);
        } else {
            throw new DuplicateUniqueCodeException("This National Is Inserted !!!");
        }
    }

    public static LinkedList<RealCustomer> searchRealCustomer(String searchOption, String searchValue) {
        return RealCustomerCRUD.selectRealCustomer(searchOption, searchValue);
    }

    public static void editCustomer(RealCustomer realCustomer) {
        realCustomerFieldValidation(realCustomer);
        LinkedList<RealCustomer> realCustomerList = RealCustomerCRUD.selectRealCustomer("nationalId", realCustomer.getNationalId());
        if (realCustomerList.size() == 1) {
            RealCustomerCRUD.updateRealCustomer(realCustomer);
        } else {
            throw new DuplicateUniqueCodeException("This Economic Code Is Inserted !!!");
        }
    }
}
