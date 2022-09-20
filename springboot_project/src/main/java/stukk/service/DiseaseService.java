package stukk.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import stukk.Pojo.Disease;
import stukk.Pojo.User;
import stukk.mapper.DiseaseMapper;

public interface DiseaseService {


    public Page<Disease> selectPage(Page page, Wrapper<Disease> wrapper);//分页查询

    public boolean deleteById(Long id);

    public boolean save(Disease disease);

    public boolean update(Disease disease);

}
