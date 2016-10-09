package userInterface;

import dataAccess.entity.LegalCustomer;
import dataAccess.entity.RealCustomer;

import javax.servlet.http.HttpServletRequest;

public class Utility {
    public static LegalCustomer initLegalCustomer(HttpServletRequest request) {
        LegalCustomer legalCustomer = new LegalCustomer();
        legalCustomer.setCustomerId(request.getParameter("customerId"));
        legalCustomer.setCompanyName(request.getParameter("companyName"));
        legalCustomer.setEconomicCode(request.getParameter("economicCode"));
        legalCustomer.setRegistrationDate(request.getParameter("registrationDate"));
        return legalCustomer;
    }

    public static RealCustomer initRealCustomer(HttpServletRequest request) {
        RealCustomer realCustomer = new RealCustomer();
        realCustomer.setCustomerId(request.getParameter("customerId"));
        realCustomer.setFirstName(request.getParameter("firstName"));
        realCustomer.setLastName(request.getParameter("lastName"));
        realCustomer.setFatherName(request.getParameter("fatherName"));
        realCustomer.setBirthDay(request.getParameter("birthDay"));
        realCustomer.setNationalId(request.getParameter("nationalId"));
        return realCustomer;
    }
}
