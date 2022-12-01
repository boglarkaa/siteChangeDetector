package com.example.sitechangedetector.controller;

import com.example.sitechangedetector.entity.Page;
import com.example.sitechangedetector.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    @Autowired
    private PageRepository pageRepository;

    @GetMapping("/addPage")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView("add-page-form");
        Page newPage = new Page();
        modelAndView.addObject("page", newPage);
        return modelAndView;
    }

    @PostMapping("/savePage")
    public String savePage(@ModelAttribute("page") Page page) {
        pageRepository.save(page);
        return "redirect:/list";
    }

}
