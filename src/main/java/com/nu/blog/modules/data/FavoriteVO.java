package com.nu.blog.modules.data;

import com.nu.blog.modules.entity.Favorite;


public class FavoriteVO extends Favorite {
    // extend
    private PostVO post;

    public PostVO getPost() {
        return post;
    }

    public void setPost(PostVO post) {
        this.post = post;
    }
}
