package com.Loyalty.points.service;

import com.Loyalty.points.entity.Customer;
import com.Loyalty.points.entity.PointsLedger;
import com.Loyalty.points.entity.Purchase;
import com.Loyalty.points.entity.Tier;
import com.Loyalty.points.repository.CustomerRepository;
import com.Loyalty.points.repository.PointsLedgerRepository;
import com.Loyalty.points.repository.PurchaseRepository;
import com.Loyalty.points.repository.TierRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PurchaseService
{
    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;
    private final TierRepository tierRepository;
    private final PointsLedgerRepository pointsLedgerRepository;

    // Change this value if your use case specifies another rate
    private static final int POINTS_PER_RUPEE = 1;

    public PurchaseService(
            PurchaseRepository purchaseRepository,
            CustomerRepository customerRepository,
            TierRepository tierRepository,
            PointsLedgerRepository pointsLedgerRepository)
    {
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
        this.tierRepository = tierRepository;
        this.pointsLedgerRepository = pointsLedgerRepository;
    }

    public Purchase savePurchase(Purchase purchase)
    {
        Customer customer = customerRepository
                .findById(purchase.getCustomer().getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Apply current tier discount
        if (customer.getTier() != null)
        {
            purchase.setDiscountPercentage(
                    customer.getTier().getDiscountPercentage()
            );
        }
        else
        {
            purchase.setDiscountPercentage(0);
        }

        // Calculate points earned
        int pointsEarned =
                purchase.getPurchaseAmount() * POINTS_PER_RUPEE;

        purchase.setPointsEarned(pointsEarned);
        purchase.setCustomer(customer);

        // Update customer points
        int currentAvailable = customer.getAvailablePoints() == null
                ? 0
                : customer.getAvailablePoints();

        int currentLifetime = customer.getLifetimePoints() == null
                ? 0
                : customer.getLifetimePoints();

        customer.setAvailablePoints(currentAvailable + pointsEarned);
        customer.setLifetimePoints(currentLifetime + pointsEarned);

        // Check for tier upgrade
        updateCustomerTier(customer);

        customerRepository.save(customer);

        // Save purchase
        Purchase savedPurchase = purchaseRepository.save(purchase);

        // Add transaction to ledger
        PointsLedger ledger = new PointsLedger();
        ledger.setCustomer(customer);
        ledger.setPoints(pointsEarned);
        ledger.setTransactionType("AWARD");

        pointsLedgerRepository.save(ledger);

        return savedPurchase;
    }

    private void updateCustomerTier(Customer customer)
    {
        List<Tier> tiers = tierRepository.findAll();

        tiers.sort(
                Comparator.comparing(Tier::getThreshold).reversed()
        );

        for (Tier tier : tiers)
        {
            if (customer.getLifetimePoints() >= tier.getThreshold())
            {
                customer.setTier(tier);
                break;
            }
        }
    }

    public List<Purchase> getAllPurchases()
    {
        return purchaseRepository.findAll();
    }

    public Purchase getPurchaseById(Long id)
    {
        return purchaseRepository.findById(id).orElse(null);
    }

    public void deletePurchase(Long id)
    {
        purchaseRepository.deleteById(id);
    }
}