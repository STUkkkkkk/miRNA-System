package stukk.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
public class Disease {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String disname;
    private String description;


}
