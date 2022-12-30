package com.example.sitechangedetector.repository;

import com.example.sitechangedetector.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
    List<Page> findByWebsite_id(Long website_id);
}
