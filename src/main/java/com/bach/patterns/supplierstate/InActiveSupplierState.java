package com.bach.patterns.supplierstate;

import com.bach.model.Supplier;

public class InActiveSupplierState extends SupplierState {

    public InActiveSupplierState(Supplier supplier) {
        this.supplier = supplier;
    }


    @Override
    public boolean canEdit() {
        return false;
    }
}
