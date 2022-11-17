package stukk.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import stukk.Pojo.Concern;
import stukk.mapper.ConcernMapper;
import stukk.service.ConcernService;
@Service
public class ConcernServiceImpl extends ServiceImpl<ConcernMapper, Concern> implements ConcernService {
}
