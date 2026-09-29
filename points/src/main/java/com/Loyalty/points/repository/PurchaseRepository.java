package com.Loyalty.points.repository;

import com.Loyalty.points.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>
{
}