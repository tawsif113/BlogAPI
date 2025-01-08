package com.tawsif.CRUDBlog.blogservice;

import com.tawsif.CRUDBlog.execptions.DuplicateItemException;
import com.tawsif.CRUDBlog.execptions.NotFoundException;
import com.tawsif.CRUDBlog.models.Blog;
import com.tawsif.CRUDBlog.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    public Blog getBlogById(Long Id){
        return blogRepository.findById(Id).orElseThrow(()->new NotFoundException("Blog not found"));
    }

    public Blog saveBlog(Blog blog){
        if(blogRepository.existsByTitle(blog.getTitle())){
            throw new DuplicateItemException("Blog Already Exists");
        }
        return blogRepository.save(blog);
    }
    public Blog updateBlog(Blog blog){
        return blogRepository.findById(blog.getId())
                .map(blog1->{
                    blog1.setTitle(blog.getTitle());
                    blog1.setContent(blog.getContent());
                    return blogRepository.save(blog1);
                }).orElseThrow(()->new NotFoundException("Blog Not Found"));
    }
    public void deleteBlog(Long id){
        if(blogRepository.existsById(id)){
            blogRepository.deleteById(id);
        }
        else throw new NotFoundException("Blog Not Found");
    }



}
