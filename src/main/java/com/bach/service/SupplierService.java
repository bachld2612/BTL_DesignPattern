package com.bach.service;

import com.bach.dao.SupplierDAO;
import com.bach.model.Supplier;
import com.bach.patterns.sessionsingleton.Session;

import java.util.List;

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

    public void updateSupplier(Supplier supplier){

        if (!Session.getInstance().getRole().equals("admin")){
            throw new RuntimeException("You are not authorized to update a supplier");
        }
        supplierDAO.updateSupplier(supplier);

    }

    public void deleteSupplier(int supplierId){

        if(!Session.getInstance().getRole().equals("admin")){
            throw new RuntimeException("You are not authorized to delete a supplier");
        }
        supplierDAO.deleteSupplier(supplierId);

    }

    public List<Supplier> getAllSuppliers() {

        if (!Session.getInstance().getRole().equals("admin")){
            throw new RuntimeException("You are not authorized to view suppliers");
        }
        return supplierDAO.getAllSuppliers();

    }
}
