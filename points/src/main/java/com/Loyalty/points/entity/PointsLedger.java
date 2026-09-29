package com.Loyalty.points.entity;
import jakarta.persistence.*;

@Entity 
@Table(name = "pointsLedger")

public class PointsLedger 
{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long pointsLedgerId;
    private Integer points;
    private String transactionType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public PointsLedger()
    {

    }
    
    public Long getPointsLedgerId()
   {
    return pointsLedgerId;
   }

    public void setPointsLedgerId(Long pointsLedgerId)
    {
        this.pointsLedgerId = pointsLedgerId;
    }

    public Customer getCustomer()
   {
    return customer;
   }

    public void setCustomer(Customer customer)
   {
    this.customer = customer;
   }

    public Integer getPoints()
    {
        return points;
    }

    public void setPoints(Integer points)
    {
        this.points = points;
    }

    public String getTransactionType()
    {
        return transactionType;
    }

    public void setTransactionType(String transactionType)
    {
        this.transactionType = transactionType;
    }
}
