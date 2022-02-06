import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StaffRepository implements IStaffRepository{

    private SQLServerDataSource getDataSource() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("127.0.0.1");
        ds.setPortNumber(1434);
        ds.setDatabaseName("BikeStores");
        ds.setUser("kamyk");
        ds.setPassword("1234");
        return ds;
    }


    @Override
    public ArrayList<Staff> getAllStaff() {
        ArrayList<Staff> list = new ArrayList<Staff>();

        try{
            SQLServerDataSource ds = getDataSource();

            try(Connection con = ds.getConnection(); Statement stmt = con.createStatement();){
                String SQL = "SELECT [staff_id],[first_name],[email],[last_name],[phone],[active],[store_id],[manager_id] FROM [BikeStores].[sales].[staffs]";

                try (ResultSet rs = stmt.executeQuery(SQL)) {
                    // Iterate through the data in the result set and display it.
                    Staff staff;
                    while (rs.next()) {
                        staff = new Staff(rs.getInt("staff_id"),
                                rs.getInt("store_id"),
                                rs.getInt("manager_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("email"),
                                rs.getString("phone"));
                        list.add(staff);
                    }
                }

            } catch (SQLServerException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Staff getStaff(int id) {
        //TODO
        return null;
    }

    @Override
    public void create(Staff staff) {
        try {

            SQLServerDataSource ds = getDataSource();

            try (Connection con = ds.getConnection(); Statement stmt = con.createStatement();) {
                String SQL = "INSERT INTO [sales].[staffs]\n" +
                        "           ([first_name]\n" +
                        "           ,[last_name]\n" +
                        "           ,[email]\n" +
                        "           ,[phone]\n" +
                        "           ,[active]\n" +
                        "           ,[store_id]\n" +
                        "           ,[manager_id])\n" +
                        "     VALUES\n" +
                        "           ('" + staff.getFirstName() + "'" +
                        "           ,'" + staff.getLastName() + "'" +
                        "           ,'" + staff.getEmail() + "'" +
                        "           ,'" + staff.getPhone() + "'" +
                        "           ,1" +
                        "           ," + staff.getStoreId() +
                        "           ," + staff.getManagerId() + ")";

                stmt.execute(SQL);
            }
        } // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Staff staff) {
        try {

            SQLServerDataSource ds = getDataSource();

            try (Connection con = ds.getConnection(); Statement stmt = con.createStatement();) {
                String SQL = "DELETE FROM [sales].[staffs]\n" +
                        "     WHERE\n" +
                        "[sales].[staffs].[staff_id] = " + staff.getId();

                stmt.execute(SQL);

                String SQL1 = "INSERT INTO [sales].[staffs]\n" +
                        "           ([first_name]\n" +
                        "           ,[last_name]\n" +
                        "           ,[email]\n" +
                        "           ,[phone]\n" +
                        "           ,[active]\n" +
                        "           ,[store_id]\n" +
                        "           ,[manager_id])\n" +
                        "     VALUES\n" +
                        "           ('" + staff.getFirstName() + "'" +
                        "           ,'" + staff.getLastName() + "'" +
                        "           ,'" + staff.getEmail() + "'" +
                        "           ,'" + staff.getPhone() + "'" +
                        "           ,1" +
                        "           ," + staff.getStoreId() +
                        "           ," + staff.getManagerId() + ")";

                stmt.execute(SQL1);

            }
        } // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try {

            SQLServerDataSource ds = getDataSource();

            try (Connection con = ds.getConnection(); Statement stmt = con.createStatement();) {
                String SQL = "DELETE FROM [sales].[staffs]\n" +
                        "     WHERE\n" +
                        "[sales].[staffs].[staff_id] = " + id;

                stmt.execute(SQL);

            }
        } // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
