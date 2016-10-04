package businessLogic;

import businessLogic.exception.DuplicateUniqueCodeException;
import businessLogic.exception.EmptyFieldException;
import dataAccess.LegalCustomerCRUD;
import dataAccess.entity.LegalCustomer;

import java.util.LinkedList;

public class LegalCustomerBusinessLogic extends CustomerBusinessLogic {

    private static void legalCustomerFieldValidation(LegalCustomer legalCustomer) {
        if(legalCustomer.getCompanyName().isEmpty()){
            throw new EmptyFieldException("Company Name Is Empty!");
        }
        if(legalCustomer.getEconomicCode().isEmpty()){
            throw new EmptyFieldException("Economic Code Is Empty!");
        }
        if(legalCustomer.getRegistrationDate().isEmpty()){
            throw new EmptyFieldException("Registration Date Is Empty!");
        }
    }

    public static void createNewCustomer(LegalCustomer legalCustomer) {
        legalCustomerFieldValidation(legalCustomer);
        LinkedList<LegalCustomer> legalCustomerList = LegalCustomerCRUD.selectLegalCustomer("economicCode", legalCustomer.getEconomicCode());
        if (legalCustomerList.size() == 0) {
            LegalCustomerCRUD.insertLegalCustomer(legalCustomer);
        } else {
            throw new DuplicateUniqueCodeException("This Economic Code Is Inserted !!!");
        }
    }

    public static LinkedList<LegalCustomer> searchLegalCustomer(String searchOption, String searchValue) {
        return LegalCustomerCRUD.selectLegalCustomer(searchOption, searchValue);
    }

    public static void editCustomer(LegalCustomer legalCustomer) {
        legalCustomerFieldValidation(legalCustomer);
        LinkedList<LegalCustomer> legalCustomerList = LegalCustomerCRUD.selectLegalCustomer("economicCode", legalCustomer.getEconomicCode());
        if (legalCustomerList.size() == 1) {
            LegalCustomerCRUD.updateLegalCustomer(legalCustomer);
        } else {
            throw new DuplicateUniqueCodeException("This Economic Code Is Inserted !!!");
        }
    }
}
