package com.nu.blog.modules.service;

import com.nu.blog.modules.entity.Links;

import java.util.List;


public interface LinksService {
    List<Links> findAll();
    void update(Links links);
    void delete(long id);
}
