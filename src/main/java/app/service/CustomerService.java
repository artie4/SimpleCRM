package app.service;

import app.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<Customer> getCustomers();

    void saveCustomer(Customer theCustomer);

    Customer getCustomer(UUID theId);

    void deleteCustomer(UUID theId);

    List<Customer> searchCustomers(String theSearchName);

}
