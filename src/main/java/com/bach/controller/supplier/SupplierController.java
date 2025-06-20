package com.bach.controller.supplier;

import com.bach.model.Supplier;
import com.bach.patterns.supplierstate.ActiveSupplierState;
import com.bach.patterns.supplierstate.InActiveSupplierState;
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
        supplierView.setActivateButtonListener(e -> handleActivateSupplier());
        supplierView.setAddSupplierButtonListener(e -> addSupplier());
    }

    public void addSupplier(){
        supplierView.dispose();
        new CreateSupplierController();
    }

    public void editSupplier() {

        Supplier supplier = supplierView.getSelectedSupplierFromForm();
        if (supplier != null) {
            if(!supplier.canEdit()){
                supplierView.showError("Nhà cung cấp không thể sửa do đang ở trạng thái không hoạt động.");
                return;
            }
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

    public void handleActivateSupplier() {
        Supplier supplier = supplierView.getSelectedSupplierFromForm();
        if (supplier != null) {
            if (supplier.getState().equals("active")) {
                supplier.changeState(new InActiveSupplierState(supplier));
            } else {
                supplier.changeState(new ActiveSupplierState(supplier));
            }
            supplierService.updateSupplier(supplier);
            supplierView.refreshTable(supplierService.getAllSuppliers());
        } else {
            supplierView.showError("Vui lòng chọn nhà cung cấp để kích hoạt hoặc vô hiệu hoá.");
        }
    }
}
