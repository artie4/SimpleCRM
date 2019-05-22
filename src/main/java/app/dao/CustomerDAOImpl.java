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

    @Override
    public void deleteCustomer(UUID theId) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery =
                currentSession.createQuery("delete from Customer where id=:customerId");
        theQuery.setParameter("customerId", theId);

        theQuery.executeUpdate();

    }

    @Override
    public List<Customer> searchCustomers(String theSearchName) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = null;

        //
        // only search by name if theSearchName is not empty
        //

        if(theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery = currentSession.createQuery("from Customer where lower(firstName) like :theName or" +
                    " lower(lastName) like :theName");

            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }

        else {

            // theSearchName is empty ... so just get all customers
            theQuery = currentSession.createQuery("from Customer");
        }

        List<Customer> customers = theQuery.list();

        return customers;
    }
}
