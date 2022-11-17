package stukk.controller;

import io.lettuce.core.ConnectionEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stukk.Pojo.Article;
import stukk.config.Result;
import stukk.mapper.SearchMirnaMapper;
import stukk.service.SearchService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/searchMirna")
public class SearchMirnaController {

    @Autowired
    SearchService searchService;

    @GetMapping
    public Result GetByMirna(@RequestParam String mirnaName,@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return searchService.selectByMirna(mirnaName,pageNum,pageSize);
    }
}
