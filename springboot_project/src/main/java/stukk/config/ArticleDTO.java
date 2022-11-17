package stukk.config;

import lombok.Data;
import stukk.Pojo.Article;

@Data
public class ArticleDTO<T> {
    private T data;//数据
    private Integer total;

    public static <T>ArticleDTO<T> Build(T data,Integer total){
        ArticleDTO article = new ArticleDTO();
        article.setData(data);
        article.setTotal(total);
        return article;
    }
}
