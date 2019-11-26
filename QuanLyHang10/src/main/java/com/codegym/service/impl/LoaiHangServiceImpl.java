package com.codegym.service.impl;

import com.codegym.model.LoaiHang;
import com.codegym.repository.LoaiHangRepository;
import com.codegym.service.LoaiHangService;
import org.springframework.beans.factory.annotation.Autowired;

public class LoaiHangServiceImpl implements LoaiHangService {
    @Autowired
    LoaiHangRepository categoryRepository;
    @Override
    public Iterable<LoaiHang> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public LoaiHang findById(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public void save(LoaiHang category) {
         categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
          categoryRepository.delete(id);
    }
}
