package app.dao;

import app.model.Customer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Customer> getCustomers() {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create query
        Query theQuery = currentSession.createQuery("from Customer order by lastName");

        // execute query and get result list
        List<Customer> customers = theQuery.list();

        // return the results
        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {


        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // save the customer ... finally
        currentSession.saveOrUpdate(theCustomer);

    }

    @Override
    @Transactional
    public Customer getCustomer(UUID theId) {

        Session currentSession = sessionFactory.getCurrentSession();

        Customer theCustomer = currentSession.get(Customer.class, theId);

        return theCustomer;
    }
}
