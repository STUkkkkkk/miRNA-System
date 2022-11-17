package stukk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import stukk.Pojo.Blog;
import stukk.config.Result;

public interface BlogService extends IService<Blog> {
    Result getAll(String Search);

    Result add(Blog blog);

    Result GetCount(Long id);

    Result GetByUserId(Long userId);


    Result DeleteById(Long id);
}
