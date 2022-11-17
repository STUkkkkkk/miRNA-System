package stukk.Pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_comment_like")
public class CommentLike {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long commentId; //被点赞的评论id

    private Long userId; //用户id

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
