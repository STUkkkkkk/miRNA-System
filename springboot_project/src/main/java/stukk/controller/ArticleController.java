package stukk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stukk.config.Result;
import stukk.service.ArticleService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;



    @GetMapping("/All")
    public Result GetAll(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value="pageSize") Integer pageSize, @RequestParam(value = "search") String search){
        return articleService.getAll(pageNum,pageSize,search);
    }

    @GetMapping()
    public Result GetByPmid(@RequestParam("pmid") Long pmid){
        return articleService.getById(pmid);
    }

}
