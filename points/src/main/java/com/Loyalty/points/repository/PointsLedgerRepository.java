package com.Loyalty.points.repository;

import com.Loyalty.points.entity.PointsLedger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointsLedgerRepository extends JpaRepository<PointsLedger, Long>
{
}