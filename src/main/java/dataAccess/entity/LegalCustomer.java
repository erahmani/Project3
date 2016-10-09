package dataAccess.entity;

public class LegalCustomer extends Customer {
    private String companyName;
    private String registrationDate;
    private String economicCode;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEconomicCode() {
        return economicCode;
    }

    public void setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
    }

    @Override
    public String toString() {
        return "CustomerId " + getCustomerId() + " CompanyName " + companyName + " restrationDAte " + registrationDate + " EconomicCode " + economicCode;
    }
}
