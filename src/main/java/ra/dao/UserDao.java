package ra.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.model.entity.User;

import javax.transaction.Transactional;

@Component
//@Transactional
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;


    public boolean signUp(User user) {// why we should create a new object request?
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            // meet all validate of input -> true
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    public User getAuthUser(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        // check email exist
        try {
            User user = session.createQuery("from User where email= :email and password= :password", User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }

    }


}
