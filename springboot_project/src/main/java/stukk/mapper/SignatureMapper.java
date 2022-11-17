package stukk.mapper;

import org.apache.ibatis.annotations.Mapper;
import stukk.Pojo.Signature;

@Mapper
public interface SignatureMapper {
    Signature queryById(Integer id);//根据id获取

    int updateById(Signature signature);

    int insertById(Signature signature);


}
