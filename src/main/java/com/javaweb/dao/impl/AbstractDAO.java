package com.javaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.javaweb.dao.GenericDAO;
import com.javaweb.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T>{
	ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
	
	public Connection getConnection() {
		try {
			Class.forName(resourceBundle.getString("driverName"));
			String dbUrl = resourceBundle.getString("dbUrl");
			String username = resourceBundle.getString("username");
			String password = resourceBundle.getString("password");
			return DriverManager.getConnection(dbUrl, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			return null; 
		}
	}
	
	@Override
	public List<T> query(String sql, RowMapper<T> rowMapper, Object... params) {
		List<T> results = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			setParameters(ps, params);
			rs = ps.executeQuery();
			while (rs.next()) {
				results.add(rowMapper.mapRow(rs));
			}
			return results;
		}
		catch (SQLException e) {
			return null;
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			catch (SQLException ex) {
				return null;
			}
		}
	}

	@Override
	public Integer insert(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Integer id = null;
			conn = getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameters(ps, params);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			conn.commit();
			return id;
		}
		catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				}
				catch (SQLException ex) {
					return null;
				}
			}
			return null;
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}

				if (conn != null) {
					conn.setAutoCommit(true);
					conn.close();
				}
			}
			catch (SQLException exc) {
				return null;
			}
		}
	}
		
	private void setParameters(PreparedStatement ps, Object... params) {
		try {
			for (int i = 0; i < params.length; i++) {
				int psIndex = i+1;
				Object obj = params[i];
				if (obj instanceof String) {
					ps.setNString(psIndex, (String) obj);
				} else if (obj instanceof Integer) {
					ps.setInt(psIndex, (Integer) obj);
				} else if (obj instanceof Float) {
					ps.setFloat(psIndex, (Float) obj);
				} else if (obj instanceof Timestamp) {
					ps.setTimestamp(psIndex, (Timestamp) obj);
				} else if (obj == null) {
					ps.setNull(psIndex, Types.NULL);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOrDelete(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			setParameters(ps, params);
			ps.executeUpdate();
			conn.commit();
		}
		catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				}
				catch (SQLException ex) {
					System.out.println(e.getMessage());
				}
			}
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}

				if (conn != null) {
					conn.setAutoCommit(true);
					conn.close();
				}
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
