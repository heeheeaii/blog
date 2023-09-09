
package com.nu.blog.modules.repository;

import com.nu.blog.modules.entity.Links;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface LinksRepository extends JpaRepository<Links, Long>, JpaSpecificationExecutor<Links> {
}
