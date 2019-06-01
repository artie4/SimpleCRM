package app.dao;

import app.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class RoleDaoImpl implements RoleDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role findRoleByName(String theRoleName) {

        Session currentSession = sessionFactory.getCurrentSession();

        NativeQuery<Role> theQuery = currentSession.createNativeQuery("select r.* from Roles r where name=:roleName", Role.class);
        theQuery.setParameter("roleName", theRoleName);

        Role theRole;

        try {
            theRole = theQuery.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return theRole;
    }
}
