package com.bach.factory.invoicefactorymethod;

import java.sql.SQLException;

public interface Invoice {
    void displayInvoice();
    void saveToDatabase() throws SQLException;
    void loadFromDatabase(int id) throws SQLException;
}
