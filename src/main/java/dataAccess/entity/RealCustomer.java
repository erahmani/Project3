package dataAccess.entity;

public class RealCustomer extends Customer {
    private String firstName;
    private String lastName;
    private String fatherName;
    private String birthDay;
    private String nationalId;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getNationalId() {
        return nationalId;
    }
}
