package ra.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.model.entity.Role;
import ra.model.entity.RoleName;

import javax.transaction.Transactional;

@Repository
@Transactional
public class RoleDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Role findByRoleName(RoleName roleName) {
        Session session = sessionFactory.getCurrentSession();
        try {
//            session.beginTransaction()
            return session.createQuery("from Role where roleName= :roleName", Role.class)
                    .setParameter("roleName", roleName).getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
