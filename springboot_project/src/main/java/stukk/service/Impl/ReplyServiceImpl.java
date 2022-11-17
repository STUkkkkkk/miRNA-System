package stukk.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import stukk.Pojo.Reply;
import stukk.mapper.ReplyMapper;
import stukk.service.ReplyService;

import javax.xml.ws.ServiceMode;

@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {
}
