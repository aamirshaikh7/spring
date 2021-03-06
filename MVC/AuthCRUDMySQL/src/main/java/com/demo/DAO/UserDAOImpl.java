package com.demo.DAO;

import com.demo.Model.Login;
import com.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class UserDAOImpl implements UserDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean register(User user) {
        String sql = "INSERT INTO registrations values (?, ?, ?, ?, ?, ?)";

        int rows = jdbcTemplate.update(sql, new Object[] {
                user.getName(),
                user.getId(),
                user.getCollege(),
                user.getStream(),
                user.getEmail(),
                Objects.hash(user.getPassword())
        });

        return rows > 0;
    }

    @Override
    public User validateUser(Login login) {
        String sql = "SELECT * FROM registrations WHERE id = " + login.getId() + " AND password = '" + Objects.hash(login.getPassword()) + "'";

        List<User> users = jdbcTemplate.query(sql, new UserMapper());

        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public boolean doesEmailExist(String email) {
        String sql = "SELECT email FROM registrations";

        List<String> emails = jdbcTemplate.queryForList(sql, String.class);

        for(String retrievedEmail : emails) {
            if(retrievedEmail.toLowerCase().equals(email.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean doesIdExist(Integer id) {
        String sql = "SELECT id FROM registrations";

        List<Integer> ids = jdbcTemplate.queryForList(sql, Integer.class);

        for(Integer retrievedId : ids) {
            if(retrievedId.equals(id)) {
                return true;
            }
        }

        return false;
    }

    private static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();

            user.setName(rs.getString("name"));
            user.setId(rs.getInt("id"));
            user.setCollege(rs.getString("college"));
            user.setStream(rs.getString("stream"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));

            return user;
        }
    }
}
