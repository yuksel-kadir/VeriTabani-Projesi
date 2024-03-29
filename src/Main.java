import java.sql.*;

public class Main {

    public static void update() throws SQLException {

        String sqlUpdate = "UPDATE candidates "
                + "SET last_name = ? "
                + "WHERE id = ?";

        Connection conn = MySQLJDBCUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);

        // prepare data for update
        String lastName = "BLEAGH";
        int id = 100;
        pstmt.setString(1, lastName);
        pstmt.setInt(2, id);

        int rowAffected = pstmt.executeUpdate();
        System.out.println(String.format("Row affected %d", rowAffected));

        // reuse the prepared statement
        lastName = "BLEAGH";
        id = 101;
        pstmt.setString(1, lastName);
        pstmt.setInt(2, id);

        rowAffected = pstmt.executeUpdate();
        System.out.println(String.format("Row affected %d", rowAffected));

        if(conn != null){
            conn.close();
        }


    }

    public static void printAll() throws SQLException {

        Connection conn = MySQLJDBCUtil.getConnection();
        String sql = "SELECT first_name, last_name, email " + "FROM candidates";
        String url       = "jdbc:mysql://localhost:3306/mysqljdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user      = "root";
        String password  = "1234";
        int i = 0;
        //Create a Statement object.
        Statement stmt  = conn.createStatement();
        //Get query results
        ResultSet rs    = stmt.executeQuery(sql);
        //Print query results
        while (rs.next()) {
            System.out.print(i + ".");
            System.out.println(rs.getString("first_name") + "\t" +
                    rs.getString("last_name")  + "\t" +
                    rs.getString("email"));
            i++;
        }
        rs.close();
        stmt.close();
        if(conn != null){
            conn.close();
        }
    }


    public static void main(String[] args) throws SQLException {
        update();
        printAll();

    }
}
