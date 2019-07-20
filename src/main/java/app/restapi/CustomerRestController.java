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
    @RequestMapping(
            value = "/customers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Customer> getCustomers() {

        return (List<Customer>) customerService.getCustomers();

    }

    @RequestMapping(value = "/customers/{customerId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomer(@PathVariable UUID customerId) {

        Customer customer = customerService.getCustomer(customerId);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer id not found " + customerId);
        }

        return customer;

    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer) {

        customerService.saveCustomer(customer);

        return customer;
    }

    @RequestMapping(value = "/customers/{customerId}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteCustomerById(@PathVariable UUID customerId) {

        Customer customer = customerService.getCustomer(customerId);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer id not found " + customerId);
        }

        customerService.deleteCustomer(customerId);

        return "Deleted customer id - " + customerId;

    }

}
