package com.wplab.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
	
	private DBConnectionInfo dbInfo;
	protected Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	public ReservationDAO() {
	}
	
	public ReservationDAO(DBConnectionInfo dbInfo) {
		this.dbInfo = dbInfo;
	}

	public void setDbInfo(DBConnectionInfo dbInfo) {
		this.dbInfo = dbInfo;
	}
	
	protected void connect() throws SQLException {
		// 1. load JDBC driver class
		try {
			Class.forName(dbInfo.getDriverClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 2. create DB connection
		conn = DriverManager.getConnection(
			dbInfo.getUrl(), 
			dbInfo.getUsername(), 
			dbInfo.getPassword());		
	}
	
	private void disconnect() throws SQLException {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (conn != null) conn.close();
	}
	
    public void reserveSeat(Reservation reservation) throws SQLException {
        try {
            connect();
            // 예약 정보 삽입
            String sql = "INSERT INTO Reservation (name, passwd, phone, seat_number, rsv_date) VALUES (?, ?, ?, ?, CURRENT_DATE)";
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, reservation.getName());
            stmt.setString(2, reservation.getPasswd());
            stmt.setString(3, reservation.getPhone());
            stmt.setInt(4, reservation.getSeat_number());
            stmt.executeUpdate();

            // 자동 생성된 RSV_SEQ 값을 가져옴
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int rsvSeq = rs.getInt(1);
                // 좌석 정보 업데이트
                String updateSeatSql = "UPDATE Seat SET rsv_seq = ? WHERE seat_number = ?";
                stmt = conn.prepareStatement(updateSeatSql);
                stmt.setInt(1, rsvSeq);
                stmt.setInt(2, reservation.getSeat_number());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            disconnect();
        }
    }

    public Reservation getLatestReservation() {
        Reservation reservation = null;
        try {
            connect();
            String sql = "SELECT * FROM Reservation ORDER BY RSV_SEQ DESC LIMIT 1";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                reservation = new Reservation();
                reservation.setRsv_seq(rs.getInt("rsv_seq"));
                reservation.setName(rs.getString("name"));
                reservation.setPasswd(rs.getString("passwd"));
                reservation.setPhone(rs.getString("phone"));
                reservation.setSeat_number(rs.getInt("seat_number"));
                reservation.setRsv_date(rs.getDate("rsv_date"));
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
        return reservation;
    }

    public Reservation getReservation(int rsv_seq, String passwd) {
        Reservation reservation = null;
        try {
            connect();
            String sql = "SELECT * FROM Reservation WHERE rsv_seq = ? AND passwd = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rsv_seq);
            stmt.setString(2, passwd);
            rs = stmt.executeQuery();
            if (rs.next()) {
                reservation = new Reservation();
                reservation.setRsv_seq(rs.getInt("rsv_seq"));
                reservation.setName(rs.getString("name"));
                reservation.setPasswd(rs.getString("passwd"));
                reservation.setPhone(rs.getString("phone"));
                reservation.setSeat_number(rs.getInt("seat_number"));
                reservation.setRsv_date(rs.getDate("rsv_date"));
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
        return reservation;
    }

    public boolean cancelReservation(int rsv_seq, String passwd) {
        boolean success = false;
        try {
            connect();
            String sql = "DELETE FROM Reservation WHERE rsv_seq = ? AND passwd = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rsv_seq);
            stmt.setString(2, passwd);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                String updateSeatSql = "UPDATE Seat SET rsv_seq = NULL WHERE rsv_seq = ?";
                stmt = conn.prepareStatement(updateSeatSql);
                stmt.setInt(1, rsv_seq);
                stmt.executeUpdate();
                success = true;
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
        return success;
    }
    
    public List<Reservation> getReservationsBySeatType(String seatType) {
        List<Reservation> reservations = new ArrayList<>();
        try {
            connect();
            String sql = "SELECT r.* FROM Reservation r JOIN Seat s ON r.seat_number = s.seat_number WHERE s.seat_type = ?";
            System.out.println("SQL: " + sql);
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, seatType);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setRsv_seq(rs.getInt("rsv_seq"));
                reservation.setName(rs.getString("name"));
                reservation.setPasswd(rs.getString("passwd"));
                reservation.setPhone(rs.getString("phone"));
                reservation.setSeat_number(rs.getInt("seat_number"));
                reservation.setRsv_date(rs.getDate("rsv_date"));
                reservations.add(reservation);
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
        System.out.println("Found " + reservations.size() + " reservations");
        return reservations;
    }
    
    public boolean deleteReservation(int rsv_seq, int seat_number) {
        boolean success = false;
        try {
            connect();
            String sql = "DELETE FROM Reservation WHERE rsv_seq = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rsv_seq);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                String updateSeatSql = "UPDATE Seat SET rsv_seq = NULL WHERE seat_number = ?";
                stmt = conn.prepareStatement(updateSeatSql);
                stmt.setInt(1, seat_number);
                stmt.executeUpdate();
                success = true;
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
        return success;
    }
}