package com.qqhru.exam2.controller;

import com.qqhru.exam2.model.User;
import com.qqhru.exam2.service.UserService;
import com.qqhru.exam2.utils.DBUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/query")
public class QueryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectMethod = request.getParameter("selectMethod");
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        try (Connection connection = DBUtils.getConnection()) {
            UserService userService = new UserService(connection);
            User user = null;
            if ("idCard".equals(selectMethod)) {
                user = userService.getUserByIdCard(account);
            } else if ("fundAccount".equals(selectMethod)) {
                user = userService.getUserByFundAccount(account);
            }

            if (user != null && user.getPassword().equals(password)) {
                // 用户存在且密码正确，将用户信息存入session以便其他页面使用
                request.getSession().setAttribute("currentUser", user);
                response.sendRedirect("showBalance.jsp");
            } else {
                // 用户不存在或密码错误，重定向到错误页面
                response.sendRedirect("error.jsp?message=" + URLEncoder.encode("用户不存在或密码错误", StandardCharsets.UTF_8.toString()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理数据库异常的逻辑，重定向到错误页面
            response.sendRedirect("error.jsp?message=" + URLEncoder.encode("数据库异常", StandardCharsets.UTF_8.toString()));
        }
    }
}
