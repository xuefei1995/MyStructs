package com.xuefei.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.xuefei.entity.User;
import com.xuefei.util.JdbcUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public void addUser(User u) {
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		String sql="INSERT INTO USER VALUES(?,?)";
		Object[] obj={u.getName(),u.getPassword()};
		try {
			qr.update(sql, obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User findUser(User u) {
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		String sql="SELECT * FROM USER WHERE NAME=? AND PASSWORD=?";
		Object[] obj={u.getName(),u.getPassword()};
		User user=null;
		try {
			user = (User)qr.query(sql, new BeanHandler(User.class), obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
