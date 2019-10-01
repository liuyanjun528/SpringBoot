package com.springboot.utils;

import java.util.Date;

import com.springboot.enty.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



	/**
	 * jwt工具类
	 */
	public class JwtUtils {


	    public static final String SUBJECT = "onehee";

	    public static final long EXPIRE = 1000*60*60*24*7;  //过期时间，毫秒，一周

	    //秘钥
	    public static final  String APPSECRET = "onehee666";

	    /**
	     * 生成jwt
	     * @param user
	     * @return
	     */
	    public static String geneJsonWebToken(User user){

	        if(user == null || user.getId() == null || user.getName() == null
	                || user.getHeadImg()==null){
	            return null;
	        }
	        String token = Jwts.builder().setSubject(SUBJECT)
	        		
	        		.claim("id",user.getId())
	                .claim("name",user.getName())
	                .claim("img",user.getHeadImg())
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))//设置有效时长
	                .signWith(SignatureAlgorithm.HS256,APPSECRET).compact();//设置密钥和算法以及生成token

	        return token;
	    }


	    /**
	     * 校验token
	     * @param token
	     * @return
	     * 如果客户端修改了token字符串。或者密钥和之前生成token的密钥不一样，那么返回的就为null，说明验证失败
	     */
	    public static Claims checkJWT(String token ){

	        try{
	            final Claims claims =  Jwts.parser().setSigningKey(APPSECRET).
	                    parseClaimsJws(token).getBody();
	            return  claims;

	        }catch (Exception e){ }
	        return null;

	    }



	}

