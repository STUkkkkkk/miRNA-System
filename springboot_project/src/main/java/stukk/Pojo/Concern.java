package stukk.Pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_concern")
public class Concern {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId; //关注的发起人

    private Long concernedId; //被关注的人

    @TableField(fill = FieldFill.INSERT)
    private Date createTime; // 建立时间

}
