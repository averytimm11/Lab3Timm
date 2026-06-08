import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class MySqlCrud {
        private static final String URL = "jdbc:mysql://localhost:3306/retail_store";
        private static final String USER = "root";
        private static final String PASSWORD = "IST888IST888";

        public void create(Customer customer) {
            String sql = "INSERT INTO Customer (name, email, balance) VALUES (?, ?, ?)";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, customer.getName());
                stmt.setString(2, customer.getEmail());
                stmt.setDouble(3, customer.getBalance());
                stmt.executeUpdate();

                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    customer.setCustomerId(keys.getInt(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public List<Customer> readAll() {
            List<Customer> list = new ArrayList<>();
            String sql = "SELECT * FROM Customer";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    list.add(new Customer(
                            rs.getInt("customer_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getDouble("balance")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }

        public void update(Customer customer) {
            String sql = "UPDATE Customer SET name = ?, email = ?, balance = ? WHERE customer_id = ?";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, customer.getName());
                stmt.setString(2, customer.getEmail());
                stmt.setDouble(3, customer.getBalance());
                stmt.setInt(4, customer.getCustomerId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void delete(int customerId) {
            String sql = "DELETE FROM Customer WHERE customer_id = ?";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, customerId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
