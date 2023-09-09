package com.nu.blog.modules.service;

import com.nu.blog.modules.data.FavoriteVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 收藏记录
 *
 */
public interface FavoriteService {
    /**
     * 查询用户收藏记录
     * @param pageable
     * @param userId
     * @return
     */
    Page<FavoriteVO> pagingByUserId(Pageable pageable, long userId);

    void add(long userId, long postId);
    void delete(long userId, long postId);
    void deleteByPostId(long postId);
}
