package stukk.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stukk.Pojo.Mirna;
import stukk.mapper.MirnaMapper;
import stukk.service.MirnaService;

@Service
public class MirnaServiceImpl implements MirnaService {

    @Autowired
    MirnaMapper mirnaMapper;

    @Override
    public boolean save(Mirna mirna) {
        return mirnaMapper.insert(mirna) == 1;
    }

    @Override
    public boolean delete(Long id) {
        return mirnaMapper.deleteById(id) == 1;
    }

    @Override
    public boolean update(Mirna mirna) {
        return mirnaMapper.updateById(mirna) == 1;
    }

    @Override
    public Page<Mirna> selectPage(Page page, Wrapper<Mirna> wrapper) {
       return mirnaMapper.selectPage(page,wrapper);

    }
}
