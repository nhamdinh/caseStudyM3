package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "book")
public class Hang implements Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
// validate by size
    @Size(min = 1, max = 10)
    private String tenhang;

    private Date ngaytao;
    private String mota;

    private Double gia;
    private Double soluong;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public LoaiHang category;

    public LoaiHang getCategory() {
        return category;
    }

    public void setCategory(LoaiHang category) {
        this.category = category;
    }

    public Hang() {
    }

    public Hang(String tenhang, Date ngaytao, String mota, Double gia, Double soluong, LoaiHang category) {
        this.tenhang = tenhang;
        this.ngaytao = ngaytao;
        this.mota = mota;
        this.gia = gia;
        this.category = category;
        this.soluong = soluong;


    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getName() {
        return tenhang;
    }

    public void setName(String tenhang) {
        this.tenhang = tenhang;
    }

    public Date getDateOfPurchase() {
        return ngaytao;
    }

    public void setDateOfPurchase(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getAuthor() {
        return mota;
    }

    public void setAuthor(String mota) {
        this.mota = mota;
    }

    public Double getPrice() {
        return gia;
    }

    public void setPrice(Double gia) {
        this.gia = gia;
    }

    public Double getSoluong(){
        return soluong;
    }
    public void setSoluong(Double soluong){

    }
//
    @Override
    public boolean supports(Class<?> clazz) {
        return Hang.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Hang book=(Hang) target;
        String tenhang=book.getName();
        ValidationUtils.rejectIfEmpty(errors,"tenhang","tenhang.empty");
        if (tenhang.length()>11 || tenhang.length()<10){
            errors.rejectValue("tenhang", "tenhang.length");
        }
        if (!tenhang.startsWith("0")){
            errors.rejectValue("tenhang", "tenhang.startsWith");
        }
        if (!tenhang.matches("(^$|[0-9]*$)")){
            errors.rejectValue("tenhang", "tenhang.matches");
        }
    }

}
