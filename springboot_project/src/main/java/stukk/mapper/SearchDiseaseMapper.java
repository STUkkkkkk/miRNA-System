package stukk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import stukk.Pojo.SearchDisease;

import java.util.List;

@Mapper
public interface SearchDiseaseMapper{
    List<Object> selectByDisease(String diseaseName,Integer num1,Integer num2);

}
