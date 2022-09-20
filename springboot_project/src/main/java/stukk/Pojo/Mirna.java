package stukk.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Mirna {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String rnaname;
    private String description;
}
