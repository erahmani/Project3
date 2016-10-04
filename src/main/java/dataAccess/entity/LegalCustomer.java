package dataAccess.entity;

public class LegalCustomer extends Customer {
    private String companyName;
    private String registrationDate;
    private String economicCode;

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getEconomicCode() {
        return economicCode;
    }
}
