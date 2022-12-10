package com.example.aop2.controller;

import com.example.aop2.annotation.Decode;
import com.example.aop2.annotation.Timer;
import com.example.aop2.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name) {

        return "id = " + id + ", name = " + name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {

        return user;
    }

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {
        System.out.println("Controller delete Method Called");
        Thread.sleep(2000 * 2);
    }

    @Decode
    @PutMapping("/put")
    public User put(@RequestBody User user){
        System.out.println("[Controller Return]\n" + user);
        System.out.println();

        return user;
    }
}
