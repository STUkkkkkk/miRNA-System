package stukk.Pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_comment")
public class Comment {

//    create table tb_comment(
//            id int primary key auto_increment comment '评论id',
//            user_id int comment '发评论的人的id',
//            blog_id int comment '评论在哪个blog上',
//            liked int comment '点赞数量',
//            user_name varchar(110) comment '评论人名字',
//    user_icon varchar(225) comment '图片',
//    content text comment '评论内容',
//    create_time timestamp comment '评论发表时间'
//            );

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long blogId;
    private int liked;
    private String userName;
    private String userIcon;
    private String content;

    @TableField(exist = false)
    @Value("false")
    private Boolean okComment;

    @TableField(exist = false)
    private Boolean isLiked;

    @TableField(exist = false)
    private List<Reply> children;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    private String Time;//距离现在的时间

}
