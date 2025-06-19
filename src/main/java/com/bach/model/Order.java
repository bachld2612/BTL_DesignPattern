package com.bach.model;

import java.util.Date;

public class Order {
    private int id;
    private int bookingId;
    private Date orderDate;
    private double totalAmount;
    private String status;
    private String paymentMethod;
    private String note;
    private IVoucher appliedVoucher;
    private double finalAmount;

    public Order(int id, int bookingId, Date orderDate, double totalAmount, 
                String status, String paymentMethod, String note) {
        this.id = id;
        this.bookingId = bookingId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.note = note;
        this.finalAmount = totalAmount;
    }

    public void applyVoucher(IVoucher voucher) {
        this.appliedVoucher = voucher;
        if (voucher != null) {
            double discount = voucher.calculateDiscount(totalAmount);
            this.finalAmount = totalAmount - discount;
        } else {
            this.finalAmount = totalAmount;
        }
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    
    public IVoucher getAppliedVoucher() { return appliedVoucher; }
    public void setAppliedVoucher(IVoucher appliedVoucher) { this.appliedVoucher = appliedVoucher; }
    
    public double getFinalAmount() { return finalAmount; }
    public void setFinalAmount(double finalAmount) { this.finalAmount = finalAmount; }
} 