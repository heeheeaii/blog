package com.nu.blog.modules.repository;

import com.nu.blog.BootApplication;
import com.nu.blog.modules.entity.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * pic repo test
 *
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class ResourceRepositoryTest {

    @Autowired
    ResourceRepository resourceRepository;

    @Test
    public void find0Before() {

        LocalDateTime now = LocalDateTime.now();
        String timeStr = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now);
        List<Resource> beforeResources = resourceRepository.find0Before(timeStr);
        System.out.println(beforeResources);
    }
}
