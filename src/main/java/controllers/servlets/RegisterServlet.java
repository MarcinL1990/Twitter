package controllers.servlets;

import controllers.error.ValidationError;
import model.User;
import services.UserManagementService;
import services.impl.UserManagementServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static controllers.utils.ServletUtils.*;

@WebServlet(name = "RegisterServlet", value = "/register")

public class RegisterServlet extends HttpServlet {

    private UserManagementService service;
    private List<ValidationError> errors;

    @Override
    public void init() throws ServletException {
        service = new UserManagementServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errors = new ArrayList<>();
        String login = req.getParameter(USER_LOGIN);
        String email = req.getParameter(USER_EMAIL);

        if (service.isUserLoginExists(login)) {
            ValidationError error = new ValidationError(LOGIN_ERROR_HEADER, LOGIN_IN_USE_ERROR_MESSAGE);
            errors.add(error);
        }

        if (service.isUserEmailExists(email)) {
            ValidationError error = new ValidationError(EMAIL_ERROR_HEADER, EMAIL_ERROR_MESSAGE);
            errors.add(error);
        }

        if (errors.size() > 0) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        String password = req.getParameter(USER_PASSWORD);
        String name = req.getParameter(USER_NAME);
        String surname = req.getParameter(USER_SURNAME);

        User user = User.UserBuilder.getBuilder()
                .login(login)
                .email(email)
                .surname(surname)
                .password(password)
                .name(name)
                .build();
        service.saveUser(user);
        req.getRequestDispatcher("/login.jsp").forward(req, resp);

//        req.setAttribute("hasError", true);
//        req.setAttribute("error", "sdfsdfssdfsdfsdfsdfsdfs");
//        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }
}