package stukk.service;

import stukk.Pojo.SearchDisease;

import java.util.List;

public interface SearchService {

    List<SearchDisease> selectByDisease(String disease);

}
