package stukk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import stukk.Pojo.SearchDisease;

import java.util.List;

@Mapper
public interface SearchDiseaseMapper{
    List<SearchDisease> selectByDisease(@Param("disname")String disname);


}
