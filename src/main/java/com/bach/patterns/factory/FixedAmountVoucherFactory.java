package com.bach.patterns.factory;

import com.bach.model.IVoucher;
import com.bach.model.FixedAmountVoucher;

public class FixedAmountVoucherFactory implements VoucherFactory {
    @Override
    public IVoucher createVoucher(int id, String code, String name, double startValue, double endValue, double value) {
        return new FixedAmountVoucher(id, code, name, startValue, endValue, true, value);
    }
}


