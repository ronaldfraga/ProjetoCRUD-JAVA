package model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.DB;
import db.DbException;
import model.dao.UserDao;
import model.entities.User;

public class UserDaoJDBC implements UserDao {

    private Connection conn;

    public UserDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(User obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO user (name, email) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());

            int rows = st.executeUpdate();
            if (rows > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                rs.close();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            try { if (st != null) st.close(); } catch (SQLException e) { throw new DbException(e.getMessage()); }
        }
    }

    @Override
    public void update(User obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE user SET name = ?, email = ? WHERE id = ?");
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setInt(3, obj.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            try { if (st != null) st.close(); } catch (SQLException e) { throw new DbException(e.getMessage()); }
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM user WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            try { if (st != null) st.close(); } catch (SQLException e) { throw new DbException(e.getMessage()); }
        }
    }

    @Override
    public User findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM user WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                User obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setEmail(rs.getString("email"));
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); if (st != null) st.close(); } 
            catch (SQLException e) { throw new DbException(e.getMessage()); }
        }
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM user ORDER BY name");

            while (rs.next()) {
                User obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setEmail(rs.getString("email"));
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); if (st != null) st.close(); } 
            catch (SQLException e) { throw new DbException(e.getMessage()); }
        }
    }
}
