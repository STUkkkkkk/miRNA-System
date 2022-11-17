package stukk.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
   private String icon;
   private String email;
   private String code;
   private Long blogCount; // 自己发表的文章数量

   @TableField(exist = false)
   private String sort;

   @TableField(exist = false)
   private String token;
}
