package com.blog.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.blog.modal.User;

@Repository
public class LoginRepository {
	private JdbcTemplate jdbc;
	@Autowired
	public LoginRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	public User findByUsername(String username) {
		String query = "select * from user where username = ?";
		User user = new User();
		try {
			user = jdbc.queryForObject(query, this::mapToUser, username);
		} catch (Exception e) {
			user = null;
		}
		return user;
	}
	
	private User mapToUser(ResultSet rs, int rowNum) throws SQLException{
		return new User(rs.getInt("id"),rs.getString("username"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"));
	}
}
