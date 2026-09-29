package com.Loyalty.points.repository;

import com.Loyalty.points.entity.Tier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TierRepository extends JpaRepository<Tier, Long>
{
}