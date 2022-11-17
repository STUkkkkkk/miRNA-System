package stukk.Pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_like")
public class Like {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long UserId;
    private Long BlogId;
    @TableField(fill = FieldFill.INSERT)
    private Date likeTime;
}
