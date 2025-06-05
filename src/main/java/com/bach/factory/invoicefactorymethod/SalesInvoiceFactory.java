package com.bach.factory.invoicefactorymethod;

import java.sql.SQLException;

public class SalesInvoiceFactory extends InvoiceFactory {
    @Override
    public Invoice createInvoice(Object... params) {
        int invoiceId = (int) params[0];
        int orderId = (int) params[1];
        int quantity = (int) params[2];
        String bookingDate = (String) params[3];
        String status = (String) params[4];
        return new SalesInvoice(invoiceId, orderId, quantity, bookingDate, status);
    }

    @Override
    public Invoice retrieveInvoice(int id) throws SQLException {
        SalesInvoice invoice = new SalesInvoice();
        invoice.loadFromDatabase(id);
        return invoice;
    }
}
