package sistempenjadwalanotomatisf1motgp;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBRace {

    private Connection connection;

    public DBRace() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_formula1"; 
            String user = "root"; 
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists() {
        try (Statement stmt = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS scheduling ("
                    + "id INT PRIMARY KEY,"
                    + "race_name VARCHAR(255),"
                    + "location VARCHAR(255),"
                    + "race_type VARCHAR(50),"
                    + "race_date DATE,"
                    + "lap_count INT,"
                    + "circuit_length_km DECIMAL(5,2),"
                    + "start_time TIME,"
                    + "end_time TIME"
                    + ")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create: Add new race
    public void createRace(modelrace race) {
        String sql = "INSERT INTO scheduling (id, race_name, location, race_type, race_date, lap_count, circuit_length_km, start_time, end_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, race.getId());
            pstmt.setString(2, race.getRaceName());
            pstmt.setString(3, race.getLocation());
            pstmt.setString(4, race.getRaceType());
            pstmt.setDate(5, race.getRaceDate());
            pstmt.setInt(6, race.getLapCount());
            pstmt.setBigDecimal(7, race.getCircuitLengthKm());
            pstmt.setTime(8, race.getStartTime());
            pstmt.setTime(9, race.getEndTime());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
    public List<modelrace> readRaces() {
        List<modelrace> raceList = new ArrayList<>();
        String sql = "SELECT * FROM scheduling";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                raceList.add(new modelrace(
                        rs.getInt("id"),
                        rs.getString("race_name"),
                        rs.getString("location"),
                        rs.getString("race_type"),
                        rs.getDate("race_date"),
                        rs.getInt("lap_count"),
                        rs.getBigDecimal("circuit_length_km"),
                        rs.getTime("start_time"),
                        rs.getTime("end_time")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return raceList;
    }

  
    public boolean updateRace(modelrace updatedRace) {
        String sql = "UPDATE scheduling SET race_name = ?, location = ?, race_type = ?, race_date = ?, lap_count = ?, circuit_length_km = ?, start_time = ?, end_time = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, updatedRace.getRaceName());
            pstmt.setString(2, updatedRace.getLocation());
            pstmt.setString(3, updatedRace.getRaceType());
            pstmt.setDate(4, updatedRace.getRaceDate());
            pstmt.setInt(5, updatedRace.getLapCount());
            pstmt.setBigDecimal(6, updatedRace.getCircuitLengthKm());
            pstmt.setTime(7, updatedRace.getStartTime());
            pstmt.setTime(8, updatedRace.getEndTime());
            pstmt.setInt(9, updatedRace.getId());
            return pstmt.executeUpdate() > 0; // Return true if update was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 
    public boolean deleteRace(int raceId) {
        String sql = "DELETE FROM scheduling WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, raceId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
