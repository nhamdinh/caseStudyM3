package com.codegym.controller;

import com.codegym.model.Hang;
import com.codegym.model.LoaiHang;
import com.codegym.service.HangService;
import com.codegym.service.LoaiHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoaiHangController {
    @Autowired
    LoaiHangService categoryService;
    @Autowired
    HangService bookService;

    @GetMapping("/categories")
    public ModelAndView listCategory(){
        Iterable<LoaiHang> categories=categoryService.findAll();
        ModelAndView modelAndView=new ModelAndView("/loaihang/list");
        modelAndView.addObject("categories",categories);
        return modelAndView;
    }
    @GetMapping("/create-category")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView=new ModelAndView("/loaihang/create","category",new LoaiHang());
        return modelAndView;
    }
    @PostMapping("/create-category")
    public ModelAndView saveCategory(@ModelAttribute("category") LoaiHang category ){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/loaihang/create");
        modelAndView.addObject("category", new LoaiHang());
        modelAndView.addObject("message", "Tao moi thanh cong");
        return modelAndView;
    }
    @GetMapping("/edit-category/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        LoaiHang category = categoryService.findById(id);
        if(category != null) {
            ModelAndView modelAndView = new ModelAndView("/loaihang/edit");
            modelAndView.addObject("category", category);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-category")
    public ModelAndView updateCategory(@ModelAttribute("category") LoaiHang category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/loaihang/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "Sua thanh cong!!!!");
        return modelAndView;
    }
    @GetMapping("/delete-category/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        LoaiHang category = categoryService.findById(id);
        if(category != null) {
            ModelAndView modelAndView = new ModelAndView("/loaihang/delete");
            modelAndView.addObject("category", category);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-category")
    public String deleteCategory(@ModelAttribute("category") LoaiHang category){
        categoryService.remove(category.getId());
        return "redirect:categories";
    }

    @GetMapping("/view-category/{id}")
    public ModelAndView viewCategory(@PathVariable("id") Long id){
        LoaiHang category = categoryService.findById(id);
        if(category == null){
            return new ModelAndView("/error.404");
        }

        Iterable<Hang> books = bookService.findAllByCategory(category);

        ModelAndView modelAndView = new ModelAndView("/loaihang/view");
        modelAndView.addObject("category", category);
        modelAndView.addObject("books", books);
        return modelAndView;
    }
}
