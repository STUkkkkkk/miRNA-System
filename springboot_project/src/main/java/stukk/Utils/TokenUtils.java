package stukk.Utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import stukk.Pojo.User;

import java.util.Date;

public class TokenUtils {

    public static String getToken(String userId, String password){
        return JWT.create().withAudience(userId)  //将userId保存在 token中
        .withExpiresAt(DateUtil.offsetHour(new Date(), 24))   // 两小时有效,之后过期
        .sign(Algorithm.HMAC256(password)); //以password作为密钥
    }
}
