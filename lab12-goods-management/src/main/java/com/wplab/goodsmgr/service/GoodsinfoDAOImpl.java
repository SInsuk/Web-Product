package com.wplab.goodsmgr.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wplab.goodsmgr.*;

public class GoodsinfoDAOImpl implements GoodsinfoDAO {
	
	private final String INSERT_SQL = 
		"insert into GOODSINFO(CODE, TITLE, WRITER, PRICE) "
			+ "values (?, ?, ?, ?)";
	private final String UPDATE_SQL = 
			"update GOODSINFO set TITLE=?, WRITER=?, PRICE=? "
				+ "where CODE=?";
	private final String DELETE_SQL = 
			"delete from GOODSINFO where CODE=?";
	private final String GET_SQL = 
			"select * from GOODSINFO where CODE=?";
	private final String LIST_SQL = 
			"select * from GOODSINFO order by CODE asc";

	private DBConnectionInfo dbInfo;
	protected Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	public GoodsinfoDAOImpl() {
	}
	
	public GoodsinfoDAOImpl(DBConnectionInfo dbInfo) {
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

	@Override
	public int insertGoods(GoodsDO goods) {
		int result = 0;
		
		try {
			connect();
			
			stmt = conn.prepareStatement(INSERT_SQL);
			
			stmt.setString(1, goods.getCode());
			stmt.setString(2, goods.getTitle());
			stmt.setString(3, goods.getWriter());
			stmt.setInt(4, goods.getPrice());
			
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public int updateGoods(GoodsDO goods) {
		int result = 0;
		
		try {
			connect();
			
			stmt = conn.prepareStatement(UPDATE_SQL);
			
			stmt.setString(1, goods.getTitle());
			stmt.setString(2, goods.getWriter());
			stmt.setInt(3, goods.getPrice());
			stmt.setString(4, goods.getCode());
			
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public int deleteGoods(GoodsDO goods) {
		int result = 0;
		
		try {
			connect();
			
			stmt = conn.prepareStatement(DELETE_SQL);
			
			stmt.setString(1, goods.getCode());
			
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public GoodsDO getGoods(GoodsDO goods) {
		GoodsDO result = null;
		
		try {
			connect();
			
			stmt = conn.prepareStatement(GET_SQL);
			
			stmt.setString(1, goods.getCode());
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				result = new GoodsDO();
				
				result.setCode(rs.getString("CODE"));
				result.setTitle(rs.getString("TITLE"));
				result.setWriter(rs.getString("WRITER"));
				result.setPrice(rs.getInt("PRICE"));
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public List<GoodsDO> listGoods() {
		List<GoodsDO> list = null;
		
		try {
			connect();
			
			stmt = conn.prepareStatement(LIST_SQL);			
			rs = stmt.executeQuery();
			
			if (rs.isBeforeFirst()) {
				list = new ArrayList<>();
				
				while (rs.next()) {
					GoodsDO goods = new GoodsDO();
					
					goods.setCode(rs.getString("CODE"));
					goods.setTitle(rs.getString("TITLE"));
					goods.setWriter(rs.getString("WRITER"));
					goods.setPrice(rs.getInt("PRICE"));
					
					list.add(goods);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}

}
