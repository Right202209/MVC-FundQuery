package com.qqhru.exam2.controller;

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

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectMethod = request.getParameter("selectMethod");
        String account = request.getParameter("account");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        try (Connection connection = DBUtils.getConnection()) {
            UserService userService = new UserService(connection);
            boolean passwordChanged = userService.updatePassword(selectMethod, account, oldPassword, newPassword);

            if (passwordChanged) {
                // 密码修改成功的提示
                response.sendRedirect("success.jsp?message=" + URLEncoder.encode("密码修改成功", StandardCharsets.UTF_8.toString()));
            } else {
                // 密码修改失败的提示
                response.sendRedirect("error.jsp?message=" + URLEncoder.encode("密码修改失败", StandardCharsets.UTF_8.toString()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理数据库异常的逻辑
            response.sendRedirect("error.jsp?message=" + URLEncoder.encode("数据库异常", StandardCharsets.UTF_8.toString()));
        }
    }
}
