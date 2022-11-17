package stukk.Pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_blog")
public class Blog {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id ; // '帖子id',
    private Long userId ; // '发帖子的用户id',
    private Long typeId ; // '帖子类型id',
    private String title ;// '标题',
    private String abs ; // '帖子的简介',
    private String icon; //'帖子的贴图',
    private String content; //'帖子正文',
    private Long view; //'浏览量',
    @TableField(exist = false)
    private Long comments; // '评论数',
    private Long liked ; //'点赞数',
    private int isadd ;// '当前是否可以点赞数++',
    private int isdes ; //'当前是否可以点赞数--',
    private Long collection; //被收藏数
    private String userName; //用户名
    private String userIcon;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime; //'创建时间'// ,


    @TableField(fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime; //'修改时间'

    @TableField(exist = false)
    private Boolean isLiked; //是否点赞

    @TableField(exist = false)
    private Boolean isCollected; //是否收藏

    @TableField(exist = false)
    private Boolean isConcern; //是否关注

    @TableField(exist = false)
    private Boolean isMyself; // 这个帖子是不是咱自己写的
}
