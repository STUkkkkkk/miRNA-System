package stukk.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stukk.Pojo.SearchDisease;
import stukk.mapper.SearchDiseaseMapper;
import stukk.service.SearchService;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchDiseaseMapper searchDiseaseMapper;

    @Override
    public List<SearchDisease> selectByDisease(String disease) {
        return searchDiseaseMapper.selectByDisease(disease);
    }
}
