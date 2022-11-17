package stukk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import stukk.Pojo.Blog;
import stukk.Pojo.Collect;
import stukk.config.Result;

public interface CollectService extends IService<Collect> {
    Result collected(Blog blog);
}
