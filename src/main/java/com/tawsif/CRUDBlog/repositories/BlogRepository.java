package com.tawsif.CRUDBlog.repositories;

import com.tawsif.CRUDBlog.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {


    boolean existsByTitle(String title);
}
