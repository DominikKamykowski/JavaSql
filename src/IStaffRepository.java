import java.util.ArrayList;

public interface IStaffRepository {
    ArrayList<Staff> getAllStaff();
    Staff getStaff(int id);
    void create(Staff staff);
    void update(Staff staff);
    void remove(int id);
}
