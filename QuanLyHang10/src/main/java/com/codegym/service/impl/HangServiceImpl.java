package com.codegym.service.impl;

import com.codegym.model.Hang;
import com.codegym.model.LoaiHang;
import com.codegym.repository.HangRepository;
import com.codegym.service.HangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class HangServiceImpl implements HangService {
    @Autowired
    HangRepository bookRepository;
    @Override
    public Page<Hang> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Hang findById(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public void save(Hang book) {
         bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
          bookRepository.delete(id);
    }

    @Override
    public Page<Hang> findAllByNameContaining(String name, Pageable pageable) {
        return bookRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public Iterable<Hang> findAllByCategory(LoaiHang category) {
        return bookRepository.findAllByCategory(category);
    }

    @Override
    public Page<Hang> findAllByCategory(Pageable pageable, LoaiHang category) {
        return bookRepository.findAllByCategory(pageable,category);
    }

    @Override
    public Page<Hang> findAllByOrderByPriceAsc(Pageable pageable) {
        return bookRepository.findAllByOrderByPriceAsc(pageable);
    }

    @Override
    public Page<Hang> findAllByOrderByPriceDesc(Pageable pageable) {
        return bookRepository.findAllByOrderByPriceDesc(pageable);
    }

    @Override
    public Page<Hang> findAllByOrderByDateOfPurchase(Pageable pageable) {
        return bookRepository.findAllByOrderByDateOfPurchase(pageable);
    }
}
