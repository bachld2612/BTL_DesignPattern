package com.bach.patterns.factory;

import com.bach.model.IVoucher;

public interface VoucherFactory {
    IVoucher createVoucher(int id, String code, String name, double startValue, double endValue, double value);
} 