package com.demo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @OneToOne
    @JoinTable(name = "order_invoice",
    joinColumns = { @JoinColumn(name = "order_id", referencedColumnName = "id") },
    inverseJoinColumns = { @JoinColumn(name = "invoice_id", referencedColumnName = "id") })
    private Invoice invoice;

    @OneToMany
    private List<Product> products;

    public Order() {
    }

    public Order(List<Product> products, Date orderDate) {
        this.products = products;
        this.orderDate = orderDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", invoice=" + invoice +
                ", products=" + products +
                '}';
    }
}
