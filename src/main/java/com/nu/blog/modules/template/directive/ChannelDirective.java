package com.nu.blog.modules.template.directive;

import com.nu.blog.modules.service.ChannelService;
import com.nu.blog.modules.template.DirectiveHandler;
import com.nu.blog.modules.template.TemplateDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChannelDirective extends TemplateDirective {
    @Autowired
    private ChannelService channelService;

    @Override
    public String getName() {
        return "channel";
    }

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        Integer id = handler.getInteger("id", 0);
        handler.put(RESULT, channelService.getById(id)).render();
    }
}
