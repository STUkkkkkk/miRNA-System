package stukk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import stukk.Pojo.Blog;
import stukk.Pojo.Comment;
import stukk.service.BlogService;
import stukk.service.CommentService;

import java.util.Date;

@SpringBootTest
public class BlogTest {

    @Autowired
    BlogService blogService;
    @Autowired
    private CommentService commentService;


    @Test
    public void addTest(){
        System.out.println(commentService.GetByUserId(1L));
    }


    @Test
    public  void testAdd(){
        Comment comment = new Comment();
        comment.setUserId(11L);
        comment.setBlogId(1L);
        comment.setUserName("陈奕迅");
        comment.setUserIcon("utils/img/1665308433729.webp");
        comment.setLiked(0);
        comment.setContent("测试3测试3侧式3");
        System.out.println(commentService.save(comment));
    }




}
