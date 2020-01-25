package services;

import model.User;
import java.util.Set;

public interface UserManagementService {

    void saveUser(User user);

    void deleteUser(Long userId);

    void follow(String currentUserLogin, String userLoginToFollow);

    void stopFollowing(String currentUserLogin, String userLoginToUnfollow);

    Set<User> getNotFollowedUsers(String login);

    boolean isUserValid(String login, String password);

    boolean isUserLoginExists(String login);

    boolean isUserEmailExists(String email);
}