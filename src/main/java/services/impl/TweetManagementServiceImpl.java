package services.impl;

import dao.TweetDAO;
import dao.UserDAO;
import dao.impl.TweetDAOImpl;
import dao.impl.UserDAOImpl;
import model.Tweet;
import model.User;
import services.TweetManagementService;

import java.util.HashSet;
import java.util.Set;

public class TweetManagementServiceImpl implements TweetManagementService {

    private UserDAO userDAO;
    private TweetDAO tweetDAO;

    public TweetManagementServiceImpl() {
        this.userDAO = new UserDAOImpl();
        this.tweetDAO = new TweetDAOImpl();
    }

    @Override
    public void addTweet(String userLogin, String message) {
        User user = userDAO.getUserByLogin(userLogin);
        tweetDAO.addTweet(user, message);
    }

    @Override
    public void updateTweet(Long tweetId, String message) {
        tweetDAO.updateTweet(tweetId, message);
    }

    @Override
    public void deleteTweet(Long id) {
        tweetDAO.deleteTweet(id);
    }

    @Override
    public Set<Tweet> getFollowedTweets(String userLogin) {
        Set<Tweet> followedTweets = new HashSet<>();
        Set<User> follows = userDAO.getFollows(userLogin);
        followedTweets.addAll(tweetDAO.getUserTweets(userLogin));
        follows.stream().forEach(p -> followedTweets.addAll(tweetDAO.getUserTweets(p.getLogin())));
        return followedTweets;
    }
}