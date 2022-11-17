package stukk.service;

import stukk.Pojo.Article;
import stukk.config.Result;

import java.util.List;

public interface ArticleService {
    Result getAll(Integer pageNum,Integer pageSize,String search);

    Result getById(Long pmid);
}
