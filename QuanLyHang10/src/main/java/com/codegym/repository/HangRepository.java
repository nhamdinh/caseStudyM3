package com.codegym.repository;

import com.codegym.model.Hang;
import com.codegym.model.LoaiHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HangRepository extends PagingAndSortingRepository<Hang,Long> {
    Iterable<Hang> findAllByCategory(LoaiHang category);
    Page<Hang> findAllByNameContaining(String name, Pageable pageable);

    Page<Hang> findAllByCategory(Pageable pageable, LoaiHang category);
    Page<Hang> findAllByOrderByPriceAsc(Pageable pageable);
    Page<Hang> findAllByOrderByPriceDesc(Pageable pageable);

    Page<Hang> findAllByOrderByDateOfPurchase(Pageable pageable);

}
