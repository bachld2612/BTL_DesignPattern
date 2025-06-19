package com.bach.patterns.factory;

import com.bach.model.IVoucher;
import com.bach.model.PercentageVoucher;

public class PercentageVoucherFactory implements VoucherFactory {
    @Override
    public IVoucher createVoucher(int id, String code, String name, double startValue, double endValue, double value) {
        return new PercentageVoucher(id, code, name, startValue, endValue, true, value);
    }
} 