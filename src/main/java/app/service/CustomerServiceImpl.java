package app.service;

import app.entity.Customer;
import app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    // inject customer app.repository
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public List<Customer> getCustomers() {

        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer theCustomer) {

        customerRepository.save(theCustomer);
    }

    @Override
    @Transactional
    public Customer getCustomer(UUID theId) {

        return customerRepository.findById(theId).get();
    }

    @Override
    @Transactional
    public void deleteCustomer(UUID theId) {

        customerRepository.deleteById(theId);
    }

    @Override
    @Transactional
    public List<Customer> searchCustomers(String theSearchName) {

        return (List<Customer>) customerRepository.findCustomersByLastName(theSearchName);
    }
}
