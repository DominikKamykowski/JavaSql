public class Staff {

    private int id, storeId, managerId;
    private String firstName, lastName, email, phone;

    public Staff(int id, int storeId, int managerId, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.storeId = storeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.managerId = managerId;
    }

    public int getId() {
        return this.id;
    }

    public int getStoreId() {
        return this.storeId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public int getManagerId() {
        return this.managerId;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
