
package com.nu.blog.web.controller.site.user;

import com.nu.blog.base.lang.BlogException;
import com.nu.blog.modules.data.AccountProfile;
import com.nu.blog.modules.service.MessageService;
import com.nu.blog.modules.service.UserService;
import com.nu.blog.web.controller.BaseController;
import com.nu.blog.web.controller.site.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户主页
 *
 *
 */
@Controller
@RequestMapping("/users")
public class UsersController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    /**
     * 用户文章
     * @param userId 用户ID
     * @param model  ModelMap
     * @return template name
     */
    @GetMapping(value = "/{userId}")
    public String posts(@PathVariable(value = "userId") Long userId,
                        ModelMap model, HttpServletRequest request) {
        return method(userId, Views.METHOD_POSTS, model, request);
    }

    /**
     * 通用方法, 访问 users 目录下的页面
     * @param userId 用户ID
     * @param method 调用方法
     * @param model  ModelMap
     * @return template name
     */
    @GetMapping(value = "/{userId}/{method}")
    public String method(@PathVariable(value = "userId") Long userId,
                         @PathVariable(value = "method") String method,
                         ModelMap model, HttpServletRequest request) {
        model.put("pageNo", ServletRequestUtils.getIntParameter(request, "pageNo", 1));

        // 访问消息页, 判断登录
        if (Views.METHOD_MESSAGES.equals(method)) {
            // 标记已读
            AccountProfile profile = getProfile();
            if (null == profile || profile.getId() != userId) {
                throw new BlogException("您没有权限访问该页面");
            }
            messageService.readed4Me(profile.getId());
        }

        initUser(userId, model);
        return view(String.format(Views.USER_METHOD_TEMPLATE, method));
    }

    private void initUser(long userId, ModelMap model) {
        model.put("user", userService.get(userId));
        boolean owner = false;

        AccountProfile profile = getProfile();
        if (null != profile && profile.getId() == userId) {
            owner = true;
            putProfile(userService.findProfile(profile.getId()));
        }
        model.put("owner", owner);
    }

}
