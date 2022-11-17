package stukk.Pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_collect")
public class Collect { //收藏表
    @TableId(value = "id" , type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long blogId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
