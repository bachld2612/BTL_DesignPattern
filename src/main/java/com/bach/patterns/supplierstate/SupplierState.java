package com.bach.patterns.supplierstate;

import com.bach.model.Supplier;

public abstract class SupplierState {

    protected Supplier supplier;

    public abstract boolean canEdit();

}
