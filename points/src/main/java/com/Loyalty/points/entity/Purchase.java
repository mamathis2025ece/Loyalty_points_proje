package com.Loyalty.points.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "purchase")
public class Purchase
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    private Integer purchaseAmount;
    private Integer pointsEarned;
    private Integer discountPercentage;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Purchase()
    {
    }

    public Long getPurchaseId()
    {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId)
    {
        this.purchaseId = purchaseId;
    }

    public Integer getPurchaseAmount()
    {
        return purchaseAmount;
    }

    public void setPurchaseAmount(Integer purchaseAmount)
    {
        this.purchaseAmount = purchaseAmount;
    }

    public Integer getPointsEarned()
    {
        return pointsEarned;
    }

    public void setPointsEarned(Integer pointsEarned)
    {
        this.pointsEarned = pointsEarned;
    }

    public Integer getDiscountPercentage()
    {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage)
    {
        this.discountPercentage = discountPercentage;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
}