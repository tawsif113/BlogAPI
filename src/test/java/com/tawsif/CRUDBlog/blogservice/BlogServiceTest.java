package com.tawsif.CRUDBlog.blogservice;

import com.tawsif.CRUDBlog.execptions.DuplicateItemException;
import com.tawsif.CRUDBlog.execptions.NotFoundException;
import com.tawsif.CRUDBlog.models.Blog;
import com.tawsif.CRUDBlog.repositories.BlogRepository;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(BlogService.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class BlogServiceTest {

    @Autowired private BlogRepository blogRepository;
    private AutoCloseable autoCloseable;

    @Autowired
    private BlogService blogService;

    @BeforeEach
    void setUp() {
        autoCloseable=MockitoAnnotations.openMocks(this);
        blogRepository.deleteAll();
    }
    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void testToGetAllBlogsSuccess() {
        Blog blog = new Blog();
        blog.setTitle("Test Title");
        blog.setContent("Test Content");
        Blog savedBlog = blogRepository.save(blog);

        assertNotNull(savedBlog);
        assertEquals(blog.getTitle(), savedBlog.getTitle());
    }

    @Test
    void testToGetBlogByIdSuccess() {
        Blog blog = new Blog();
        blog.setTitle("Test Title");
        blog.setContent("Test Content");
        Blog savedBlog = blogRepository.save(blog);

        assertNotNull(savedBlog);
        assertNotNull(savedBlog.getId());
        assertEquals(blog.getTitle(), savedBlog.getTitle());
    }
    @Test
    void testSaveBlogDuplicateTitle() {
        Blog blog = new Blog();
        blog.setTitle("Duplicate Blog");
        blog.setContent("Content for duplicate blog");

        blogService.saveBlog(blog);

        Blog duplicateBlog = new Blog();
        duplicateBlog.setTitle("Duplicate Blog");
        duplicateBlog.setContent("Another content");

        assertThrows(DuplicateItemException.class, () -> blogService.saveBlog(duplicateBlog));
    }

    @Test
    void testGetAllBlogs() {
        Blog blog1 = new Blog();
        blog1.setTitle("Blog 1");
        blog1.setContent("Content 1");

        Blog blog2 = new Blog();
        blog2.setTitle("Blog 2");
        blog2.setContent("Content 2");

        blogService.saveBlog(blog1);
        blogService.saveBlog(blog2);

        List<Blog> blogs = blogService.getAllBlogs();

        assertEquals(2, blogs.size());
    }

    @Test
    void testGetBlogByIdNotFound() {
        assertThrows(NotFoundException.class, () -> blogService.getBlogById(999L));
    }

    @Test
    void testDeleteBlogSuccess() {
        Blog blog = new Blog();
        blog.setTitle("Blog to Delete");
        blog.setContent("Content to delete");

        Blog savedBlog = blogService.saveBlog(blog);

        blogService.deleteBlog(savedBlog.getId());

        Optional<Blog> deletedBlog = blogRepository.findById(savedBlog.getId());
        assertTrue(deletedBlog.isEmpty());
    }
}