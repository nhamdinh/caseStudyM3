package com.codegym.controller;

import com.codegym.model.Hang;
import com.codegym.model.LoaiHang;
import com.codegym.service.HangService;
import com.codegym.service.LoaiHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class HangController {
    @Autowired
    HangService bookService;
    @Autowired
    LoaiHangService categoryService;

    @ModelAttribute("categories")
    public Iterable<LoaiHang> categories(){
        return categoryService.findAll();
    }

    @GetMapping("/book")
    public ModelAndView listEmplyee(@RequestParam("s") Optional<String> s,@PageableDefault(size = 10) Pageable pageable){
        Page<Hang> books;;
        if (s.isPresent()){
            books=bookService.findAllByNameContaining(s.get(),pageable);
        }else {
            books=bookService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/hang/list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }
    @GetMapping("/create-book")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView=new ModelAndView("/hang/create");
        modelAndView.addObject("book", new Hang());
        return modelAndView;
    }
    @PostMapping("/create-book")
    public ModelAndView saveCustomer(@ModelAttribute("book") Hang book){
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("/hang/create");
        modelAndView.addObject("book", new Hang());
        modelAndView.addObject("message", "New book created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-book/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Hang book=bookService.findById(id);
        if (book!=null){
            ModelAndView modelAndView=new ModelAndView("/hang/edit");
            modelAndView.addObject("book",book);
            return modelAndView;
        }else{
            ModelAndView modelAndView=new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/edit-book")
    public ModelAndView updateEm(@ModelAttribute("book") Hang book){
        bookService.save(book);
        ModelAndView modelAndView=new ModelAndView("/hang/edit");
        modelAndView.addObject("book",book);
        modelAndView.addObject("message","Book Update succeddful");
        return modelAndView;
    }
    @GetMapping("/delete-book/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Hang book = bookService.findById(id);
        if(book != null) {
            ModelAndView modelAndView = new ModelAndView("/hang/delete");
            modelAndView.addObject("book", book);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-book")
    public String deleteEm(@ModelAttribute("book") Hang book){
        bookService.remove(book.getId());
        return "redirect:book";
    }
    @GetMapping("/searchByCategory")
    public ModelAndView getBookByCategory(@RequestParam("search") String search, Pageable pageable){
        LoaiHang category = categoryService.findById(Long.parseLong(search));
        Page<Hang> books = bookService.findAllByCategory(pageable,category);
        ModelAndView modelAndView = new ModelAndView("/hang/list");
        modelAndView.addObject("books",books);
        modelAndView.addObject("search",search);
        return modelAndView;
    }

    @GetMapping("/sortByPriceAsc")
    public ModelAndView getBookSortByPriceAsc(Pageable pageable){
        Page<Hang> books = bookService.findAllByOrderByPriceAsc(pageable);
        ModelAndView modelAndView = new ModelAndView("/hang/list");
        modelAndView.addObject("books",books);
        return modelAndView;
    }

    @GetMapping("/sortByPriceDesc")
    public ModelAndView getBookSortByPriceDesc(Pageable pageable){
        Page<Hang> books = bookService.findAllByOrderByPriceDesc(pageable);
        ModelAndView modelAndView = new ModelAndView("/hang/list");
        modelAndView.addObject("books",books);
        return modelAndView;
    }

    @GetMapping("/sortByDate")
    public ModelAndView getBookSortByDate(Pageable pageable){
        Page<Hang> books = bookService.findAllByOrderByDateOfPurchase(pageable);
        ModelAndView modelAndView = new ModelAndView("/hang/list");
        modelAndView.addObject("books",books);
        return modelAndView;
    }




    @GetMapping("/x")
    public String checkValidation(@Valid @ModelAttribute("book") Hang book, BindingResult bindingResult, Model model){
        new Hang().validate(book,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "index";
        }else {
            model.addAttribute("book",book.getName());
            return "error.404";
        }
    }

}
