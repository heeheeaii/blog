package com.nu.blog.modules.service;

import com.nu.blog.modules.data.PostTagVO;
import com.nu.blog.modules.data.TagVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TagService {
    Page<TagVO> pagingQueryTags(Pageable pageable);
    Page<PostTagVO> pagingQueryPosts(Pageable pageable, String tagName);
    void batchUpdate(String names, long latestPostId);
    void deteleMappingByPostId(long postId);
}
