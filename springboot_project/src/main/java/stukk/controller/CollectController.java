package stukk.controller;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import stukk.Pojo.Blog;
import stukk.config.Result;
import stukk.service.CollectService;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    CollectService collectService;

    @PutMapping
    public Result collected(@RequestBody Blog blog){
        return collectService.collected(blog);
    }

}
