package com.Loyalty.points.service;

import com.Loyalty.points.entity.Customer;
import com.Loyalty.points.entity.PointsLedger;
import com.Loyalty.points.repository.CustomerRepository;
import com.Loyalty.points.repository.PointsLedgerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointsLedgerService
{
    private final PointsLedgerRepository pointsLedgerRepository;
    private final CustomerRepository customerRepository;

    public PointsLedgerService(
            PointsLedgerRepository pointsLedgerRepository,
            CustomerRepository customerRepository)
    {
        this.pointsLedgerRepository = pointsLedgerRepository;
        this.customerRepository = customerRepository;
    }

    public PointsLedger savePointsLedger(PointsLedger pointsLedger)
    {
        return pointsLedgerRepository.save(pointsLedger);
    }

    public List<PointsLedger> getAllPointsLedger()
    {
        return pointsLedgerRepository.findAll();
    }

    public PointsLedger getPointsLedgerById(Long id)
    {
        return pointsLedgerRepository.findById(id).orElse(null);
    }

    public void deletePointsLedger(Long id)
    {
        pointsLedgerRepository.deleteById(id);
    }

    public PointsLedger redeemPoints(Long customerId, Integer points)
    {
        Customer customer = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        int availablePoints = customer.getAvailablePoints() == null
                ? 0
                : customer.getAvailablePoints();

        if (points > availablePoints)
        {
            throw new RuntimeException(
                    "Cannot redeem more points than available balance"
            );
        }

        customer.setAvailablePoints(availablePoints - points);

        customerRepository.save(customer);

        PointsLedger ledger = new PointsLedger();
        ledger.setCustomer(customer);
        ledger.setPoints(-points);
        ledger.setTransactionType("REDEEM");

        return pointsLedgerRepository.save(ledger);
    }
}