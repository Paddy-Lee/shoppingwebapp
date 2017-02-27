package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import database.GoodsUtil;
import model.Good;

public class GoodsDAO {
	GoodsUtil dbUtil = new GoodsUtil();
	Connection connection = dbUtil.getConnection();

	public List<Good> query() {
		String sql = "SELECT * FROM goods ;";
		Statement statement = null;
		ResultSet resultSet = null;
		Good good = null;
		List<Good> list = new ArrayList<>();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				good = new Good();
				good.setId(resultSet.getInt("id"));
				good.setName(resultSet.getString("name"));
				good.setCity(resultSet.getString("city"));
				good.setPrice(resultSet.getInt("price"));
				good.setNumber(resultSet.getInt("number"));
				good.setPicture(resultSet.getString("picture"));
				list.add(good);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	public Good get(Integer id) {
		String sql = "SELECT * FROM goods WHERE id = ? ;";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Good good = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				good = new Good();
				good.setId(resultSet.getInt("id"));
				good.setName(resultSet.getString("name"));
				good.setCity(resultSet.getString("city"));
				good.setPrice(resultSet.getInt("price"));
				good.setNumber(resultSet.getInt("number"));
				good.setPicture(resultSet.getString("picture"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (preparedStatement != null)
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return good;
	}

	public List<Good> getViewedList(String idString) {
		List<Good> params = new ArrayList<>();
		Good good = null;
		String[] idArr = idString.split("#");
		if (idArr != null && idArr.length > 0) {
			if (idArr.length >= 5) {
				for (int i = idArr.length - 1; i > idArr.length - 5 - 1; i--) {
					good = new Good();
					good = get(Integer.valueOf(idArr[i]));
					params.add(good);
				}
				return params;
			} else {
				for (int i = idArr.length - 1; i >= 0; i--) {
					good = new Good();
					good = get(Integer.valueOf(idArr[i]));
					params.add(good);
				}
				return params;
			}
		} else {

			return null;
		}
	}
}
