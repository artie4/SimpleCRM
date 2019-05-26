package app.dao;

import app.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerDAO {

    List<Customer> getCustomers();

    void saveCustomer(Customer theCustomer);

    Customer getCustomer(UUID theId);

    void deleteCustomer(UUID theId);

    List<Customer> searchCustomers(String theSearchName);

}
