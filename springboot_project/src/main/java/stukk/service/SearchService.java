package stukk.service;

import stukk.Pojo.SearchDisease;
import stukk.config.Result;

import java.util.List;

public interface SearchService {

    Result<?> selectByDisease(String disease, Integer pageNum, Integer pageSize);


    Result<?> selectByMirna(String mirnaName, Integer pageNum, Integer pageSize);
}
