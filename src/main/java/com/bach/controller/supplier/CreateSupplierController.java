package com.bach.controller.supplier;

import com.bach.controller.LoginController;
import com.bach.service.SupplierService;
import com.bach.view.CreateSupplierView;

public class CreateSupplierController {

    private final SupplierService supplierService;
    private final CreateSupplierView createSupplierView;

    public CreateSupplierController() {
        this.supplierService = new SupplierService();
        this.createSupplierView = new CreateSupplierView();
        this.createSupplierView.setVisible(true);
        initListeners();
    }

    private void initListeners() {
        createSupplierView.addAddButtonListener(e -> createSupplier());
        createSupplierView.addBackButtonListener(e -> backToMain());
    }

    public void createSupplier(){
        if (createSupplierView.getName().isEmpty() ||
            createSupplierView.getAddressValue().isEmpty() ||
            createSupplierView.getPhoneValue().isEmpty()) {
            createSupplierView.showError("Không được để trống thông tin nhà cung cấp");
            return;
        }

        try {
            supplierService.creatSupplier(createSupplierView.getSupplier());
            createSupplierView.showMessage("Tạo nhà cung cấp thành công");
            createSupplierView.dispose();
            new SupplierController();
        } catch (RuntimeException e) {
            createSupplierView.showError(e.getMessage());
        }
    }

    public void backToMain() {
        createSupplierView.dispose();
        new LoginController();
    }


}
