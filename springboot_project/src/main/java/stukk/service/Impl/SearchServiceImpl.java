package stukk.service.Impl;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stukk.Pojo.Article;
import stukk.Pojo.Blog;
import stukk.Pojo.SearchDisease;
import stukk.config.Result;
import stukk.mapper.SearchDiseaseMapper;
import stukk.mapper.SearchMirnaMapper;
import stukk.service.SearchService;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchDiseaseMapper searchDiseaseMapper;

    @Override
    public Result<?> selectByDisease(String disease, Integer pageNum, Integer pageSize) {
        Integer num1 = (pageNum - 1)*pageSize;
        Integer num2 = pageSize;
        List<Object> objects = searchDiseaseMapper.selectByDisease(disease, num1, num2);
        if(objects==null || objects.size() < 1){
            return Result.success();
        }
        System.out.println(objects);
        Integer num = ((List<Integer>)objects.get(1)).get(0);
        List<Article> articles = (List<Article>) objects.get(0);
        SearchDisease searchDisease =  new SearchDisease(num,articles);
        return Result.success(searchDisease);
    }

    @Autowired
    SearchMirnaMapper searchMirnaMapper;

    @Override
    public Result<?> selectByMirna(String mirnaName, Integer pageNum, Integer pageSize) {
        Integer num1 = (pageNum - 1)*pageSize;
        Integer num2 = pageSize;
        List<Object> objects = searchMirnaMapper.GetByMirna(mirnaName, num1, num2);
        if(objects==null || objects.size() < 1L){
            return Result.success();
        }
        Integer num = ((List<Integer>)objects.get(1)).get(0);
        List<Article> articles = (List<Article>) objects.get(0);
        SearchDisease searchDisease =  new SearchDisease(num,articles);
        return Result.success(searchDisease);
    }
}
