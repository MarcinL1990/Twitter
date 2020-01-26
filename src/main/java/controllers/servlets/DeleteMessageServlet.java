package controllers.servlets;

import controllers.utils.ServletUtils;
import services.TweetManagementService;
import services.impl.TweetManagementServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controllers.utils.ServletUtils.TWEET_MESSAGE_DELETE;

@WebServlet(name = "DeleteMessageServlet", value = "/deleteTweet")

public class DeleteMessageServlet extends HttpServlet {

    private TweetManagementService service;

    @Override
    public void init() throws ServletException {
        service = new TweetManagementServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userLoginFromSession = ServletUtils.getUserLoginFromSession(req);
        String delete = req.getParameter(TWEET_MESSAGE_DELETE);
        if (delete == null) {
            req.getRequestDispatcher("deletes").forward(req, resp);
            return;
        }
        service.addTweet(userLoginFromSession, delete);
        req.getRequestDispatcher("deletes").forward(req, resp);
    }
}
