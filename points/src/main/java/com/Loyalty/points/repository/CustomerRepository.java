package com.Loyalty.points.repository;

import com.Loyalty.points.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{
}