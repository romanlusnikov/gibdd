package lab.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseHandler {
    Connection connection;

    public Connection getDBConnection() throws SQLException, ClassNotFoundException {
        String url = "localhost";
        String port = "3306";
        String dbName = "drivers";

        String connectionStr = "jdbc:mysql://" + url + ":" + port + "/" + dbName + "?serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(connectionStr, "root", "root");
        return connection;
    }

/*    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT login, password, type FROM users WHERE login =? AND password =? AND type =?";

        try {
            PreparedStatement prSt = getDBConnection().prepareStatement(select);
            prSt.setString(1, user.getUsername());
            prSt.setString(2, user.getPassword());
            prSt.setInt(3, user.getType());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }
*/
    public ObservableList<Drivers> getDrivers() {
        try {
            PreparedStatement prSt = getDBConnection().prepareStatement("SELECT * FROM drivers");

            ResultSet result = prSt.executeQuery();
            ObservableList<Drivers> list = FXCollections.observableArrayList();
            while (result.next()) { //Получение данных из столбцов базы данных
                Drivers drivers = new Drivers("", "", "", "",
                        "", "", "", "", "", "",
                        "", "", "", "");

                drivers.setId(result.getString("id"));
                drivers.setName(result.getString("name"));
                drivers.setMiddlename(result.getString("middlename"));
                drivers.setPassportSerial(result.getString("passport serial"));
                drivers.setPassportNumber(result.getString("passport number"));
                drivers.setPostcode(result.getString("postcode"));
                drivers.setAddress(result.getString("address"));
                drivers.setAddressLife(result.getString("address life"));
                drivers.setCompany(result.getString("company"));
                drivers.setJobname(result.getString("jobname"));
                drivers.setPhone(result.getString("phone"));
                drivers.setEmail(result.getString("email"));
                drivers.setPhoto(result.getString("photo"));
                drivers.setDescription(result.getString("description"));

                list.add(drivers);
            }
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addDriver(Drivers driver) {
        String insert = "INSERT INTO drivers (id, name, middlename, passport serial, " +
                "passport number, postcode, address, address life, company, jobname, " +
                "phone, email, photo, description)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement prSt = getDBConnection().prepareStatement(insert);
            prSt.setInt(1, Integer.parseInt(driver.getId()));
            prSt.setString(2, driver.getName());
            prSt.setString(3, driver.getMiddlename());
            prSt.setString(4, driver.getPassportSerial());
            prSt.setString(5, driver.getPassportNumber());
            prSt.setInt(6, Integer.parseInt(driver.getPostcode()));
            prSt.setString(7, driver.getAddress());
            prSt.setString(8, driver.getAddressLife());
            prSt.setString(9, driver.getCompany());
            prSt.setString(10, driver.getJobname());
            prSt.setString(11, driver.getPhone());
            prSt.setString(12, driver.getEmail());
            prSt.setString(13, driver.getPhoto());
            prSt.setString(14, driver.getDescription());

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteDriver(String id) {
        String delete = "DELETE FROM drivers WHERE id = ?;";
        int ID = Integer.parseInt(id);

        try {
            PreparedStatement prSt = getDBConnection().prepareStatement(delete);
            prSt.setInt(1, ID);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
