package com.example.sitechangedetector.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="website_form")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Website {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String link;
    private boolean active;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @OneToMany(mappedBy = "website")
    private Set<Page> pages;
}
