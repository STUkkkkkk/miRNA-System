package stukk.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stukk.Pojo.Disease;
import stukk.mapper.DiseaseMapper;
import stukk.service.DiseaseService;
@Service
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    DiseaseMapper diseaseMapper;

    @Override
    public Page<Disease> selectPage(Page page, Wrapper<Disease> wrapper) {
        return diseaseMapper.selectPage(page,wrapper);
    }

    @Override
    public boolean deleteById(Long id) {
        return diseaseMapper.deleteById(id) == 1;
    }

    @Override
    public boolean save(Disease disease) {
        return diseaseMapper.insert(disease) == 1;
    }

    @Override
    public boolean update(Disease disease) {
        return diseaseMapper.updateById(disease) == 1;
    }
}
