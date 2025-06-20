package com.bach.patterns.supplierstate;

import com.bach.model.Supplier;

public class ActiveSupplierState extends SupplierState {

    public ActiveSupplierState(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean canEdit() {
        return true;
    }
}
