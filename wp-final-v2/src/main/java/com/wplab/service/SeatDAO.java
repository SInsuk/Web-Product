package com.wplab.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.wplab.service.*;

public class SeatDAO {
    private DBConnectionInfo dbInfo;
    protected Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    public SeatDAO(DBConnectionInfo dbInfo) {
        this.dbInfo = dbInfo;
    }

    protected void connect() throws SQLException {
        try {
            Class.forName(dbInfo.getDriverClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        conn = DriverManager.getConnection(
            dbInfo.getUrl(), 
            dbInfo.getUsername(), 
            dbInfo.getPassword()
        );
    }

    private void disconnect() throws SQLException {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    }

    public List<Seat> getAllSeats() {
        List<Seat> seats = new ArrayList<>();
        try {
            connect();
            String sql = "SELECT seat_number, seat_type, rsv_seq FROM Seat";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Seat seat = new Seat();
                seat.setSeat_number(rs.getInt("seat_number"));
                seat.setSeat_type(rs.getString("seat_type"));
                seat.setRsv_seq(rs.getObject("rsv_seq") != null ? rs.getInt("rsv_seq") : null);
                seats.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return seats;
    }
}
