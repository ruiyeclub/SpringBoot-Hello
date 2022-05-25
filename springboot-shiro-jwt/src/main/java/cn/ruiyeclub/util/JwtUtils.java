package cn.ruiyeclub.util;

import cn.ruiyeclub.entity.SysUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * jwt工具类
 */
@Slf4j
@Data
public class JwtUtils {

    //发行者
    public static final String SUBJECT = "raychen";
    //过期时间 一天
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    //密钥
    public static final String APPSECRET = "raychen11";

    /**
     * 生成jwt
     *
     * @param user
     * @return
     */
    public static String generateToken(SysUser user) {
        if (user == null || user.getId() == null || user.getUsername() == null || user.getPassword() == null) {
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", user.getId())
                .claim("name", user.getUsername())
                .claim("password", user.getPassword())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();

        return token;
    }

    /**
     * 校验token 用户信息可在claims中获取
     *
     * @param token
     * @return
     */
    public static Claims checkToken(String token) {
        //仿造的token或者已过期就会报错
        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            System.out.println("catch...");
        }
        return null;
    }

}
