package stukk.Pojo;

import lombok.Data;

@Data
public class Signature { //个性签名类

    private Integer id;
    private Integer user_id;
    private String signature;

    public Signature() {
    }
}
