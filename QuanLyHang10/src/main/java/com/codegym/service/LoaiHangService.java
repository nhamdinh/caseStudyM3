package com.codegym.service;

import com.codegym.model.LoaiHang;

public interface LoaiHangService {
    Iterable<LoaiHang> findAll();
    LoaiHang findById(Long id);
    void save(LoaiHang category);
    void remove(Long id);
}
