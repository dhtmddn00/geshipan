package com.example.geshipan.controller;

import com.example.geshipan.entity.Post;
import com.example.geshipan.repository.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PostController {


    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String list(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, Model model) {
        Page<Post> postsPage = postService.findAll(page, pageSize);
        List<Post> posts = postsPage.getContent();
        model.addAttribute("posts", posts);
        model.addAttribute("pageSize", pageSize); // pageSize 값을 모델에 추가
        model.addAttribute("page", page); // pageSize 값을 모델에 추가
        model.addAttribute("postsPage", postsPage);


        return "index";
    }

    @GetMapping("/post/form")
    public String form(Model model) {
        model.addAttribute("post", new Post());
        return "form";
    }

    @PostMapping("/post/save")
    public String save(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/";
    }
    @GetMapping("/post/view/{id}")
    public String view(@PathVariable Long id, Model model)
    {
        Post post = postService.findById(id);
        model.addAttribute("post",post);
        return "view";
    }

    @GetMapping("/post/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "edit";
    }
    @PostMapping("/post/update/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post) {
        postService.save(post); // ID가 존재하면 업데이트 처리
        return "redirect:/";
    }

    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/";
    }





}
