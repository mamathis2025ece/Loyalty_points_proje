package com.Loyalty.points.controller;

import com.Loyalty.points.entity.Purchase;
import com.Loyalty.points.service.PurchaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController
{
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService)
    {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public Purchase savePurchase(@RequestBody Purchase purchase)
    {
        return purchaseService.savePurchase(purchase);
    }

    @GetMapping
    public List<Purchase> getAllPurchases()
    {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/{id}")
    public Purchase getPurchaseById(@PathVariable Long id)
    {
        return purchaseService.getPurchaseById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePurchase(@PathVariable Long id)
    {
        purchaseService.deletePurchase(id);
    }
}