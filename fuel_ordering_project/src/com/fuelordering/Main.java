package com.fuelordering;

import java.sql.*;
import java.util.*;

public class Main {
    private static final double PETROL_PRICE = 105.5;
    private static final double DIESEL_PRICE = 95.2;
    private static ArrayList<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = DatabaseConnection.getConnection();

        while (true) {
            System.out.println("\n===== FUEL ORDERING SYSTEM =====");
            System.out.println("1. Place Order");
            System.out.println("2. View All Orders");
            System.out.println("3. Save to Database");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    placeOrder(sc);
                    break;
                case 2:
                    viewOrders();
                    break;
                case 3:
                    saveToDatabase(conn);
                    break;
                case 4:
                    System.out.println("Thank you for using Fuel Ordering System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void placeOrder(Scanner sc) {
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Fuel Type (Petrol/Diesel): ");
        String type = sc.nextLine();

        System.out.print("Enter Quantity (in liters): ");
        double qty = sc.nextDouble();

        double price = type.equalsIgnoreCase("Petrol") ? PETROL_PRICE : DIESEL_PRICE;
        double total = qty * price;

        Order order = new Order(name, type, qty, total);
        orders.add(order);

        System.out.println("✅ Order Added: " + order);
    }

    private static void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders yet!");
        } else {
            System.out.println("\n--- Current Orders ---");
            for (Order o : orders) {
                System.out.println(o);
            }
        }
    }

    private static void saveToDatabase(Connection conn) {
        if (orders.isEmpty()) {
            System.out.println("No orders to save!");
            return;
        }

        try {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO orders (customer_name, fuel_type, quantity, total_price) VALUES (?, ?, ?, ?)"
            );
            for (Order o : orders) {
                ps.setString(1, o.getCustomerName());
                ps.setString(2, o.getFuelType());
                ps.setDouble(3, o.getQuantity());
                ps.setDouble(4, o.getTotalPrice());
                ps.executeUpdate();
            }
            System.out.println("💾 Orders saved to database!");
            orders.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
