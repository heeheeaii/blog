package com.nu.blog.modules.template.directive;

import com.nu.blog.modules.service.LinksService;
import com.nu.blog.modules.template.DirectiveHandler;
import com.nu.blog.modules.template.TemplateDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Eg:
 * <@links>
 *     <#list results as row>
 * 		<li><a href="${row.url}">${row.name}</a></li>
 * 	   </#list>
 * </@links>
 *
 *
 */
@Component
public class LinksDirective extends TemplateDirective {
    @Autowired
    private LinksService linksService;

    @Override
    public String getName() {
        return "links";
    }

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        handler.put(RESULTS, linksService.findAll()).render();
    }
}
