package ra.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.dao.ICategoryDao;
import ra.model.entity.Category;

import java.util.List;

@Repository
public class CategoryDaoImpl implements ICategoryDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Category ", Category.class).list();
//        Session session = sessionFactory.openSession();
//        return session.createQuery("from Category ").list();
    }

    @Override
    public Category findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Category.class, id);
    }

    @Override
    public void save(Category category) {
        Session session = sessionFactory.getCurrentSession();

        if (category.getId() == null) {
            category.setStatus(true); // default     status = active when added
            session.persist(category);
        } else {
            session.merge(category);
        }
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
//        session.createQuery("delete from Category where id = id ")
        session.delete(findById(id));
    }

    public String getImageByProductId(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return (String) session.createQuery("select c.url from Category c where c.id = :id")
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }


}
