package stukk.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiniu.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import stukk.Pojo.Disease;
import stukk.config.Result;
import stukk.mapper.DiseaseMapper;
import stukk.service.DiseaseService;

import java.util.List;

@Service
public class DiseaseServiceImpl extends ServiceImpl<DiseaseMapper,Disease> implements DiseaseService {

    @Autowired
    DiseaseMapper diseaseMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


//    分页查询
    @Override
    public Page<Disease> selectPage(Page page, Wrapper<Disease> wrapper) {
        return diseaseMapper.selectPage(page,wrapper);
    }

//    根据id删除
    @Override
    public boolean deleteById(Long id) {
        return diseaseMapper.deleteById(id) == 1;
    }

//    根据is保存
    @Override
    public boolean save(Disease disease) {
        return diseaseMapper.insert(disease) == 1;
    }

//    根据id更新
    @Override
    public boolean update(Disease disease) {
        return diseaseMapper.updateById(disease) == 1;
    }

    @Override
    public Result getAll() {
        String allDisease = stringRedisTemplate.opsForValue().get("AllDisease");
        if(allDisease == null){
            List<Disease> list = query().list();
            String s = JSON.toJSONString(list);
            stringRedisTemplate.opsForValue().set("AllDisease",s);
            return Result.success(list);
        }
        else{
            List<Disease> diseases = (List<Disease>)JSON.parse(allDisease);
            return Result.success(diseases);
        }
    }
}
