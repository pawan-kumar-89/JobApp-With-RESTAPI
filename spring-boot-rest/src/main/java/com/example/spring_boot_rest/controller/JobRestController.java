package com.example.spring_boot_rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.spring_boot_rest.model.JobPost;
import com.example.spring_boot_rest.service.JobService;

//  ,@RestController = @Controller + @ResponseBody
//@Controller
@RestController
public class JobRestController {

    @Autowired
    private JobService service;


    @GetMapping("jobPosts")
   // @ResponseBody
    public List<JobPost> getAllJobs() {
        return service.returnAllJobPosts();

    }/*
    //hard coding
    @GetMapping("jobPost/5")
    public JobPost getjob(){
        return service.returnJob(5);
    }*/
    //@GetMapping("jobPost/{postId}/{company}") for multiple
    @GetMapping("jobPost/{postId}")
    public JobPost getjob(@PathVariable("postId") int postID){
        return service.returnJob(postID);
    }

    @PostMapping("jobPost")
    public JobPost addJob(@RequestBody JobPost jobPost) {
        service.addJobPost(jobPost);
        return service.returnJob(jobPost.getPostId());
    }

    @PutMapping("jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost) {
        service.updateJob(jobPost);
        return service.returnJob(jobPost.getPostId());
    }

    @DeleteMapping("jobPost/{postId}")
    public String deleteJob(@PathVariable("postId") int postId){
        service.deleteJob(postId);
        return "Deleted";
    }
    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
        return service.search(keyword);
    }
    @GetMapping("load")
    public String loadData(){
        service.load();
        return "success";
    }

}