package com.Loyalty.points.controller;

import com.Loyalty.points.entity.Customer;
import com.Loyalty.points.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController
{
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer)
    {
        return customerService.saveCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id)
    {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id)
    {
        customerService.deleteCustomer(id);
    }
}