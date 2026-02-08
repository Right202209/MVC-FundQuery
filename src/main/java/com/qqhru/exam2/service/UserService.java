package com.qqhru.exam2.service;

import com.qqhru.exam2.Dao.UserDAO;
import com.qqhru.exam2.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {
    private UserDAO userDAO;

    public UserService(Connection connection) {
        this.userDAO = new UserDAO(connection);
    }

    public User getUserByIdCard(String idCard) throws SQLException {
        return userDAO.getUserByIdCard(idCard);
    }

    public boolean updatePassword(String selectMethod, String account, String oldPassword, String newPassword) throws SQLException {
        User user = null;
        if ("idCard".equals(selectMethod)) {
            user = userDAO.getUserByIdCard(account);
        } else if ("fundAccount".equals(selectMethod)) {
            user = userDAO.getUserByFundAccount(account);
        }

        if (user != null && user.getPassword().equals(oldPassword)) {
            return userDAO.updatePassword(user.getIdCard(), newPassword);
        }
        return false;
    }

    public User getUserByFundAccount(String fundAccount) throws SQLException {
        return userDAO.getUserByFundAccount(fundAccount);
    }
}

