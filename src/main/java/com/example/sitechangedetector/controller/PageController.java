package com.example.sitechangedetector.controller;

import com.example.sitechangedetector.entity.Page;
import com.example.sitechangedetector.entity.Website;
import com.example.sitechangedetector.repository.PageRepository;
import com.example.sitechangedetector.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private WebsiteRepository websiteRepository;

    @GetMapping("/showPages")
    ModelAndView showPages(@RequestParam Long websiteId) {
        ModelAndView modelAndView = new ModelAndView("list-pages");
        List<Page> list = pageRepository.findByWebsite_id(websiteId);
        modelAndView.addObject("pages", list);
        return modelAndView;
    }

    @GetMapping("/addPage")
    public ModelAndView addPage(@RequestParam Long websiteId) {
        ModelAndView modelAndView = new ModelAndView("add-page-form");
        Page newPage = new Page();
        newPage.setWebsite_id(websiteId);
        newPage.setWebsite(websiteRepository.findById(websiteId).get());
        pageRepository.save(newPage);
        modelAndView.addObject("page", newPage);
        return modelAndView;
    }

    @PostMapping("/savePage")
    public String savePage(@ModelAttribute("page") Page page) {
        page.setWebsite(websiteRepository.findById(page.getWebsite_id()).get());
        pageRepository.save(page);
        return "redirect:/list";
    }

}
