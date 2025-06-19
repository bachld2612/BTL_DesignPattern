package com.bach.controller.supplier;

import com.bach.model.Supplier;
import com.bach.service.SupplierService;
import com.bach.view.SupplierView;

public class SupplierController {

    private final SupplierService supplierService;
    private final SupplierView supplierView;

    public SupplierController() {
        this.supplierService = new SupplierService();
        this.supplierView = new SupplierView();
        this.supplierView.setSupplierData(supplierService.getAllSuppliers());
        this.supplierView.setVisible(true);
        initListeners();
    }

    private void initListeners() {
        supplierView.setEditButtonListener(e -> editSupplier());
        supplierView.setDeleteButtonListener(e -> deleteSupplier());
    }

    public void editSupplier() {

        Supplier supplier = supplierView.getSelectedSupplierFromForm();
        if (supplier != null) {
            supplierService.updateSupplier(supplier);
            supplierView.refreshTable(supplierService.getAllSuppliers());
        } else {
            supplierView.showError("Vui lòng chọn nhà cung cấp để sửa.");
        }

    }

    public void deleteSupplier() {
        Supplier supplier = supplierView.getSelectedSupplierFromForm();
        if (supplier != null) {
            supplierService.deleteSupplier(supplier.getId());
            supplierView.refreshTable(supplierService.getAllSuppliers());
        } else {
            supplierView.showError("Vui lòng chọn nhà cung cấp để xoá.");
        }
    }
}
