import dao.UserDAO;
import dao.impl.UserDAOImpl;
import hibernate.util.HibernateUtil;
import model.User;

public class Test {
    public static void main(String[] args) {

        User user = User.UserBuilder.getBuilder().surname("as0").email("as0").password("as0").login("as0").name("as0").build();
        UserDAO userDAO = new UserDAOImpl();
//        userDAO.saveUser(user);
//        HibernateUtil.getInstance().saveByHibermateSession(user);
    }
}
