package com.web.project.firstWeb.controllers;

import com.web.project.firstWeb.models.Post;
import com.web.project.firstWeb.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping(value = "/blog")
    public String blog(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog";
    }

    @GetMapping(value = "/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping(value = "/blog/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String announce,
                              @RequestParam String textBody,
                              Model model) {
        Post post = new Post(title, announce, textBody);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping(value = "blog/{id}")
    public String showPost(@PathVariable(value = "id") Long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> resource = new ArrayList<>();
        post.ifPresent(resource::add);
        model.addAttribute("post", resource);
        return "blog-view";
    }

    @GetMapping(value = "/blog/{id}/edit")
    public String postEdit(@PathVariable("id") Long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> resource = new ArrayList<>();
        post.ifPresent(resource::add);
        model.addAttribute("post", resource);
        return "blog-edit";
    }

    @PostMapping(value = "/blog/{id}/edit")
    public String savePost(@PathVariable("id") Long id, @RequestParam String title,
                           @RequestParam String announce,
                           @RequestParam String textBody,
                           Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Post post = postRepository.findById(id).orElseThrow();
        post.updater(title, announce, textBody);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @PostMapping(value = "/blog/{id}/delete")
    public String deletePost(@PathVariable("id") Long id) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }

}
