package stukk.Pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;

@TableName("tb_reply")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
//    create table tb_reply(
//            id int primary key auto_increment comment '回复id',
//            user_id int comment '发回复的人的id',
//            comment_id int comment '评论在哪个评论上',
//            user_name varchar(110) comment '回复人名字',
//    user_icon varchar(225) comment '回复人的图片',
//    bereply_id int comment '回复人的id',
//    bereply_name varchar(110) comment '被回复人名字',
//    content text comment '回复内容',
//    create_time timestamp comment '评论发表时间'
//            );
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long CommentId;
    private String userName;
    private String userIcon;
    private String content;
    private Long bereplyId;
    private String bereplyName;

    @TableField(exist = false)
    @Value("")
    private String write;

    @TableField(exist = false)
    @Value("false")
    private Boolean okComment;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
