package stukk.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("tb_type")
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String typeName;

    private Long count;
}
