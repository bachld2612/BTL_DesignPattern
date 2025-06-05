package com.bach.factory.invoicefactorymethod;

import java.sql.SQLException;

public class PurchaseInvoiceFactory extends InvoiceFactory {
    @Override
    public Invoice createInvoice(Object... params) {
        int invoiceId = (int) params[0];
        int adminId = (int) params[1];
        double amount = (double) params[2];
        String purchaseDate = (String) params[3];
        String status = (String) params[4];
        return new PurchaseInvoice(invoiceId, adminId, amount, purchaseDate, status);
    }

    @Override
    public Invoice retrieveInvoice(int id) throws SQLException {
        PurchaseInvoice invoice = new PurchaseInvoice();
        invoice.loadFromDatabase(id);
        return invoice;
    }
}
