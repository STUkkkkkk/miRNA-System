package stukk.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("tb_mirna")
@AllArgsConstructor
@NoArgsConstructor
public class Mirna {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String mirnaName;
}
