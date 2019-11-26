package com.codegym.service;

import com.codegym.model.Hang;
import com.codegym.model.LoaiHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HangService {
    Page<Hang> findAll(Pageable pageable);
    Hang findById(Long id);

    void save(Hang book);

    void remove(Long id);
    Page<Hang> findAllByNameContaining(String name, Pageable pageable);
    Iterable<Hang> findAllByCategory(LoaiHang category);

    Page<Hang> findAllByCategory(Pageable pageable, LoaiHang category);
    Page<Hang> findAllByOrderByPriceAsc(Pageable pageable);
    Page<Hang> findAllByOrderByPriceDesc(Pageable pageable);

    Page<Hang> findAllByOrderByDateOfPurchase(Pageable pageable);

}
