
package com.nu.blog.modules.template.directive;

import com.nu.blog.modules.template.DirectiveHandler;
import com.nu.blog.modules.template.TemplateDirective;
import org.springframework.stereotype.Component;

/**
 * 资源路径处理
 *
 */
@Component
public class ResourceDirective extends TemplateDirective {
    @Override
    public String getName() {
        return "resource";
    }

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        String src = handler.getString("src", "#");
        if (src.startsWith("/storage") || src.startsWith("/theme")) {
            String base = handler.getContextPath();
            handler.renderString(base + src);
        } else {
            handler.renderString(src);
        }
    }

}
