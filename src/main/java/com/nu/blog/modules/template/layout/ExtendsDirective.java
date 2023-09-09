package com.nu.blog.modules.template.layout;

import com.nu.blog.config.SiteOptions;
import com.nu.blog.modules.template.DirectiveHandler;
import com.nu.blog.modules.template.TemplateDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ExtendsDirective extends TemplateDirective {
    @Autowired
    private SiteOptions siteOptions;

    @Override
    public String getName() {
        return "layout.extends";
    }

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        String theme = siteOptions.getValue("theme");
        String layoutName =  handler.getString("name");
        layoutName = layoutName.startsWith("/") ? theme + layoutName : theme + "/" + layoutName;
        handler.bodyResult();
        handler.getEnv().include(layoutName, null, true);
    }

}
