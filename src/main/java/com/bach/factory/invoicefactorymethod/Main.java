package com.bach.factory.invoicefactorymethod;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== CREATE SALES INVOICE ===");
//        System.out.print("Invoice ID: "); int salesId = scanner.nextInt();
        System.out.print("Order ID: "); int orderId = scanner.nextInt();
        System.out.print("Quantity: "); int quantity = scanner.nextInt(); scanner.nextLine();
        System.out.print("Booking Date: "); String bookingDate = scanner.nextLine();
        System.out.print("Status: "); String salesStatus = scanner.nextLine();

        InvoiceFactory salesFactory = new SalesInvoiceFactory();
        Invoice salesInvoice = salesFactory.createInvoice(orderId, quantity, bookingDate, salesStatus);
        try {
            salesInvoice.saveToDatabase();
            salesInvoice.displayInvoice();
        } catch (SQLException e) {
            System.out.println("Error saving sales invoice: " + e.getMessage());
        }

        System.out.println("\n=== CREATE PURCHASE INVOICE ===");
        System.out.print("Invoice ID: "); int purchaseId = scanner.nextInt();
        System.out.print("Admin ID: "); int adminId = scanner.nextInt();
        System.out.print("Amount: "); double amount = scanner.nextDouble(); scanner.nextLine();
        System.out.print("Purchase Date: "); String purchaseDate = scanner.nextLine();
        System.out.print("Status: "); String purchaseStatus = scanner.nextLine();

        InvoiceFactory purchaseFactory = new PurchaseInvoiceFactory();
        Invoice purchaseInvoice = purchaseFactory.createInvoice(purchaseId, adminId, amount, purchaseDate, purchaseStatus);
        try {
            purchaseInvoice.saveToDatabase();
            purchaseInvoice.displayInvoice();
        } catch (SQLException e) {
            System.out.println("Error saving purchase invoice: " + e.getMessage());
        }

        System.out.println("\n=== RETRIEVE SALES INVOICE ===");
        System.out.print("Enter Sales Invoice ID to retrieve: ");
        int retrieveSalesId = scanner.nextInt();
        try {
            Invoice retrievedSales = salesFactory.retrieveInvoice(retrieveSalesId);
            retrievedSales.displayInvoice();
        } catch (SQLException e) {
            System.out.println("Error retrieving sales invoice: " + e.getMessage());
        }

// === RETRIEVE PURCHASE INVOICE ===
        System.out.println("\n=== RETRIEVE PURCHASE INVOICE ===");
        System.out.print("Enter Purchase Invoice ID to retrieve: ");
        int retrievePurchaseId = scanner.nextInt();
        try {
            Invoice retrievedPurchase = purchaseFactory.retrieveInvoice(retrievePurchaseId);
            retrievedPurchase.displayInvoice();
        } catch (SQLException e) {
            System.out.println("Error retrieving purchase invoice: " + e.getMessage());
        }
        scanner.close();
    }
}
