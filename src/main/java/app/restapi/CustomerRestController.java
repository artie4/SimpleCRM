package app.restapi;

import app.entity.Customer;
import app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
public class CustomerRestController {

    @RequestMapping(value = "/customers",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method=RequestMethod.POST)
    public Customer addCustomer(@RequestBody Customer customer) {

        customerService.saveCustomer(customer);

        return customer;

    }

    // autowire the CustomerService
    @Autowired
    private CustomerService customerService;

    // add mapping for GET /customers
    @RequestMapping(value = "/customers",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public List<Customer> getCustomers() {

        return customerService.getCustomers();

    }

    @RequestMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable UUID customerId) {

        Customer customer = customerService.getCustomer(customerId);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer id not found " + customerId);
        }

        return customer;

    }



}
