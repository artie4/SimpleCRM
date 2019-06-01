package app.dao;

import app.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByUserName(String userName) {

        Session currentSession = sessionFactory.getCurrentSession();

        NativeQuery<User> theQuery = currentSession.createNativeQuery(
                "select u.* from Users u where userName=:userName", User.class);
        theQuery.setParameter("userName", userName);

        return theQuery.getSingleResult();
    }


    @Override
    public void save(User user) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(user);

    }
}
