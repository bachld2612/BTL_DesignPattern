package com.bach.service;

import com.bach.dao.SupplierDAO;
import com.bach.model.Supplier;
import com.bach.patterns.sessionsingleton.Session;

public class SupplierService {

    private final SupplierDAO supplierDAO;

    public SupplierService() {
        this.supplierDAO = new SupplierDAO();
    }

    public void creatSupplier(Supplier supplier){

        if (!Session.getInstance().getRole().equals("admin")){
            throw new RuntimeException("You are not authorized to create a supplier");
        }
        supplier.setAdminId(Session.getInstance().getId());
        supplierDAO.createSupplier(supplier);

    }
}
