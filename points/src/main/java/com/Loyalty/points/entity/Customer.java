package com.Loyalty.points.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String customerName;
    private String email;
    private Integer availablePoints;
    private Integer lifetimePoints;

    @ManyToOne
    @JoinColumn(name = "tier_id")
    private Tier tier;

    public Customer()
    {
    }

    public Long getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Integer getAvailablePoints()
    {
        return availablePoints;
    }

    public void setAvailablePoints(Integer availablePoints)
    {
        this.availablePoints = availablePoints;
    }

    public Integer getLifetimePoints()
    {
        return lifetimePoints;
    }

    public void setLifetimePoints(Integer lifetimePoints)
    {
        this.lifetimePoints = lifetimePoints;
    }

    public Tier getTier()
    {
        return tier;
    }

    public void setTier(Tier tier)
    {
        this.tier = tier;
    }
}