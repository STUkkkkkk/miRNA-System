package stukk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stukk.Pojo.SearchDisease;
import stukk.config.Result;
import stukk.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("/searchDisease")
public class SearchDiseaseController {

    @Autowired
    SearchService searchService;


    @GetMapping
    public Result<?> selectByDisease(@RequestParam String disease,@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return searchService.selectByDisease(disease,pageNum,pageSize);
    }

}
