package controllers.servlets;

import controllers.utils.ServletUtils;
import services.UserManagementService;
import services.impl.UserManagementServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Unfollow", value = "/unfollow")

public class UnfollowServlet extends HttpServlet {

    private UserManagementService service;

    @Override
    public void init() throws ServletException {
        service = new UserManagementServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentUserLogin = ServletUtils.getUserLoginFromSession(req);
        String userLoginUnfollow = req.getParameter(ServletUtils.USER_LOGIN_TO_UNFOLLOW);
        service.stopFollowing(currentUserLogin, userLoginUnfollow);
        req.getRequestDispatcher("users").forward(req, resp);
    }
}