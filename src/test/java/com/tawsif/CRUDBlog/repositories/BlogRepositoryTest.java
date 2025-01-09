package com.tawsif.CRUDBlog.repositories;

import com.tawsif.CRUDBlog.models.Blog;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class BlogRepositoryTest {

    @Autowired
    private BlogRepository underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void testSaveBlog() {
        Blog blog = new Blog();
        blog.setTitle("Test Title");
        blog.setContent("Test Content");
        Blog savedBlog = underTest.save(blog);
        assertNotNull(savedBlog.getId());
        assertEquals(blog.getTitle(), savedBlog.getTitle());
        assertEquals(blog.getContent(), savedBlog.getContent());
    }

    @Test
    void testFindById() {
        Blog blog = new Blog();
        blog.setTitle("Find Me");
        blog.setContent("Find me by ID.");
        Blog savedBlog = underTest.save(blog);

        Optional<Blog> foundBlog = underTest.findById(savedBlog.getId());
        assertTrue(foundBlog.isPresent());
        assertEquals("Find Me", foundBlog.get().getTitle());
    }
    @Test
    void testExistsByTitle() {
        Blog blog = new Blog();
        blog.setTitle("Unique Title");
        blog.setContent("Some content.");
        underTest.save(blog);

        boolean exists = underTest.existsByTitle("Unique Title");
        assertTrue(exists);

        boolean doesNotExist = underTest.existsByTitle("Nonexistent Title");
        assertFalse(doesNotExist);
    }
}