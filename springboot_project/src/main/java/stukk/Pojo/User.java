package stukk.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class User {
   @TableId(value = "id", type = IdType.AUTO)
   private Long id ;

   private String username;
   private String password;
   private String nickname;
   private Integer age;
   private String sex ;
   private String address ;
}
