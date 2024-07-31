public class User {
    private String name;
    private String password;
    private String personID;
    private String phoneNumber;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User(String name, String password, String personID, String phoneNumber) {
        this.name = name;
        this.password = password;
        this.personID = personID;
        this.phoneNumber = phoneNumber;
    }
}
