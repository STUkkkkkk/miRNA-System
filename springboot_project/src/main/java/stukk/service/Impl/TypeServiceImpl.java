package stukk.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import stukk.Pojo.Type;
import stukk.mapper.TypeMapper;
import stukk.service.TypeService;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
}
