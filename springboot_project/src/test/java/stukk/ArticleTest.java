package stukk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import stukk.Pojo.Article;
import stukk.config.Result;
import stukk.mapper.ArticleMapper;
import stukk.service.ArticleService;

import java.util.List;

@SpringBootTest
public class ArticleTest {

    @Autowired
    ArticleService articleService;

    @Test
    public void Test_Article_Controller(){//测试管理文章显示
        Result all = articleService.getAll(2, 10, "DNA");
        System.out.println(all);
    }

    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void Test_Sum(){//测试管理文章显示
        Integer all = articleMapper.getSum("%DNA%");
        System.out.println(all);
    }
    @Test
    void adsas(){
//        List<Object> all = articleMapper.getAll(1, 10, "%DNA%");
//        List<Article> as = (List<Article>) all.get(0);
//        Object o = all.get(1);
//        List<Integer> list = (List<Integer >)o;
//        System.out.println(list.get(0));
//        System.out.println(as);
//        Integer ass = (Integer)all.get(1);
//        System.out.println(as);
//        System.out.println(ass);
    }


}
