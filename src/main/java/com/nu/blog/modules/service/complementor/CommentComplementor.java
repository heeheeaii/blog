package com.nu.blog.modules.service.complementor;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.nu.blog.base.utils.BeanMapUtils;
import com.nu.blog.base.utils.SpringUtils;
import com.nu.blog.modules.data.CommentVO;
import com.nu.blog.modules.data.PostVO;
import com.nu.blog.modules.data.UserVO;
import com.nu.blog.modules.entity.Comment;
import com.nu.blog.modules.service.CommentService;
import com.nu.blog.modules.service.PostService;
import com.nu.blog.modules.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @date : 2022/3/26

 */
public class CommentComplementor {
    private List<CommentVO> comments = Lists.newArrayList();
    private Set<Long> userIds = Sets.newHashSet();
    private Set<Long> postIds = Sets.newHashSet();
    private Set<Long> parentIds = Sets.newHashSet();

    public static CommentComplementor of(List<Comment> entities) {
        CommentComplementor builder = new CommentComplementor();

        entities.forEach(po -> {
            if (po.getPid() > 0) {
                builder.parentIds.add(po.getPid());
            }
            builder.userIds.add(po.getAuthorId());
            builder.postIds.add(po.getPostId());
            builder.comments.add(BeanMapUtils.copy(po));
        });

        return builder;
    }

    public CommentComplementor flutBuildUser() {
        Map<Long, UserVO> map = SpringUtils.getBean(UserService.class).findMapByIds(this.userIds);
        comments.forEach(p -> p.setAuthor(map.get(p.getAuthorId())));
        return this;
    }

    public CommentComplementor flutBuildPost() {
        Map<Long, PostVO> map = SpringUtils.getBean(PostService.class).findMapByIds(this.postIds);
        comments.forEach(p -> p.setPost(map.get(p.getPostId())));
        return this;
    }

    public CommentComplementor flutBuildParent() {
        if (!parentIds.isEmpty()) {
            Map<Long, CommentVO> pm = SpringUtils.getBean(CommentService.class).findByIds(parentIds);

            comments.forEach(c -> {
                if (c.getPid() > 0) {
                    c.setParent(pm.get(c.getPid()));
                }
            });
        }
        return this;
    }

    public List<CommentVO> getComments() {
        return comments;
    }

    public Map<Long, CommentVO> toMap() {
        return comments.stream().collect(Collectors.toMap(CommentVO::getId, n-> n));
    }

}
