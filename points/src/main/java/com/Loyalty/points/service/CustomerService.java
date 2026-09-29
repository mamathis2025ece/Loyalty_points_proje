package com.Loyalty.points.service;

import com.Loyalty.points.entity.Customer;
import com.Loyalty.points.entity.Tier;
import com.Loyalty.points.repository.CustomerRepository;
import com.Loyalty.points.repository.TierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService
{
    private final CustomerRepository customerRepository;
    private final TierRepository tierRepository;

    public CustomerService(
            CustomerRepository customerRepository,
            TierRepository tierRepository)
    {
        this.customerRepository = customerRepository;
        this.tierRepository = tierRepository;
    }

    public Customer saveCustomer(Customer customer)
    {
        if (customer.getTier() != null)
        {
            Long tierId = customer.getTier().getTierId();

            Tier tier = tierRepository.findById(tierId)
                    .orElseThrow(() -> new RuntimeException("Tier not found"));

            customer.setTier(tier);
        }

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id)
    {
        return customerRepository.findById(id).orElse(null);
    }

    public void deleteCustomer(Long id)
    {
        customerRepository.deleteById(id);
    }
}