package com.fuelordering;

public class Order {
    private String customerName;
    private String fuelType;
    private double quantity;
    private double totalPrice;

    public Order(String customerName, String fuelType, double quantity, double totalPrice) {
        this.customerName = customerName;
        this.fuelType = fuelType;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() { return customerName; }
    public String getFuelType() { return fuelType; }
    public double getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }

    @Override
    public String toString() {
        return "Customer: " + customerName + ", Fuel: " + fuelType +
               ", Quantity: " + quantity + "L, Total: ₹" + totalPrice;
    }
}
