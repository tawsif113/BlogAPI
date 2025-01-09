package com.tawsif.CRUDBlog.controllers;

import com.tawsif.CRUDBlog.blogservice.BlogService;
import com.tawsif.CRUDBlog.models.Blog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new blog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Blog created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Blog already exists")
    })
    @PostMapping("/post")
    public ResponseEntity<Blog> createBlog(@Valid @RequestBody Blog blog){
        Blog savedBlog = blogService.saveBlog(blog);
        return new ResponseEntity<>(savedBlog,HttpStatus.CREATED);
    }

    @Operation(summary = "Get all blogs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blogs found"),
            @ApiResponse(responseCode = "404", description = "No blogs found")
    })
    @GetMapping
    public List<Blog> getAllBlogs(){
        return blogService.getAllBlogs();
    }

    @Operation(summary = "Get a blog by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Blog found"),
            @ApiResponse(responseCode = "404", description = "Blog not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id){
        return new ResponseEntity<>(blogService.getBlogById(id),HttpStatus.FOUND);
    }

    @Operation(summary = "Update a blog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Blog not found")
    })
    @PutMapping("/update")
    public ResponseEntity<Blog> updateBlog(@Valid @RequestBody Blog blog){
        return new ResponseEntity<>(blogService.updateBlog(blog),HttpStatus.OK);
    }

    @Operation(summary = "Delete a blog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog deleted"),
            @ApiResponse(responseCode = "404", description = "Blog not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id){
        blogService.deleteBlog(id);
        return new ResponseEntity<>("Blog Deleted",HttpStatus.OK);
    }
}
