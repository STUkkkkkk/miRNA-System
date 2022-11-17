package stukk.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import stukk.Pojo.Mirna;
import stukk.config.Result;
import stukk.mapper.MirnaMapper;
import stukk.service.MirnaService;

import java.util.List;

@Service
public class MirnaServiceImpl extends ServiceImpl<MirnaMapper,Mirna> implements MirnaService {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public Result getAll() {
        String allMirna = stringRedisTemplate.opsForValue().get("AllMirna");
        if(allMirna == null){
            List<Mirna> list = query().list();
            String s = JSON.toJSONString(list);
            stringRedisTemplate.opsForValue().set("AllMirna",s);
            return Result.success(list);
        }
        else{
            List<Mirna> mirnas = (List<Mirna>) JSON.parse(allMirna);
            return Result.success(mirnas);
        }
    }

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
