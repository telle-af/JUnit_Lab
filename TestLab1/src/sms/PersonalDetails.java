package sms;

public class PersonalDetails {

    String fullName;
    String birthDate;
    String city;

    public PersonalDetails(String fullName, String birthDate, String city) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.city = city;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
