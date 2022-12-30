package com.example.sitechangedetector.controller;

import com.example.sitechangedetector.entity.Page;
import com.example.sitechangedetector.entity.Website;
import com.example.sitechangedetector.repository.PageRepository;
import com.example.sitechangedetector.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WebsiteController {
    @Autowired
    private WebsiteRepository websiteRepository;

    @GetMapping({"/showWebsites", "/", "/list"})
    ModelAndView showWebsites() {
        ModelAndView modelAndView = new ModelAndView("list-websites");
        List<Website> list = websiteRepository.findAll();
        modelAndView.addObject("websites", list);
        return modelAndView;
    }

    @GetMapping("/addWebsiteForm")
    public ModelAndView addWebsiteForm() {
        ModelAndView modelAndView = new ModelAndView("add-website-form");
        Website newWebsite = new Website();
        modelAndView.addObject("website", newWebsite);
        return modelAndView;
    }

    @PostMapping("/saveWebsite")
    public String saveWebsite(@ModelAttribute("website") Website website) {
        websiteRepository.save(website);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long websiteId) {
        ModelAndView modelAndView = new ModelAndView("add-website-form");
        Website website = websiteRepository.findById(websiteId).get();
        modelAndView.addObject("website", website);
        return modelAndView;
    }

    @GetMapping("/deleteWebsite")
    public String deleteWebsite(@RequestParam Long websiteId) {
        websiteRepository.deleteById(websiteId);
        return "redirect:/list";
    }
}
