package com.project.myproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;


    // Create a Customer
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return ResponseEntity.ok("Customer created successfully");
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get Customer by ID
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }


    // Update Customer
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));

        existingCustomer.setName(customerDetails.getName());
        existingCustomer.setEmail(customerDetails.getEmail());
        return customerRepository.save(existingCustomer);
    }

    // Delete Customer
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));

        customerRepository.delete(customer);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
