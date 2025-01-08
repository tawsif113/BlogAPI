package com.tawsif.CRUDBlog.controllers;

import com.tawsif.CRUDBlog.blogservice.BlogService;
import com.tawsif.CRUDBlog.models.Blog;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/post")
    public ResponseEntity<Blog> createBlog(@Valid @RequestBody Blog blog){
        Blog savedBlog = blogService.saveBlog(blog);
        return new ResponseEntity<>(savedBlog,HttpStatus.CREATED);
    }

    @GetMapping
    public List<Blog> getAllBlogs(){
        return blogService.getAllBlogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id){
        return new ResponseEntity<>(blogService.getBlogById(id),HttpStatus.FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<Blog> updateBlog(@Valid @RequestBody Blog blog){
        return new ResponseEntity<>(blogService.updateBlog(blog),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id){
        blogService.deleteBlog(id);
        return new ResponseEntity<>("Blog Deleted",HttpStatus.OK);
    }
}
