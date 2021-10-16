package com.guigu.educms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.guigu.commonutils.JwtUtils;
import com.guigu.commonutils.exception.GuLiEception;
import com.guigu.educms.entity.UcenterMember;
import com.guigu.educms.service.UcenterMemberService;
import com.guigu.educms.utils.ConstantWxUtils;
import com.guigu.educms.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.net.URLEncoder;
import java.util.HashMap;

//@CrossOrigin
@RequestMapping("/api/ucenter/wx")
@Controller
public class WeXinApiController {
    @Autowired
    private UcenterMemberService ucenterMemberService;
    @GetMapping("qrcode")
    public String getQRCode(){  //生成二维码
        //1 获取code值，临时票据，类似于验证码
        //2 拿着code请求 微信固定的地址，得到两个值 accsess_token 和 openid
        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
        "?appid=%s" +
        "&redirect_uri=%s" +
        "&response_type=code" +
        "&scope=snsapi_login" +
        "&state=%s" +
        "#wechat_redirect";
        String redirectUrl=ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        String state = "guigu";//为了让大家能够使用我搭建的外网的微信回调跳转服务器，这里填写你在ngrok的前置域名
        try {
            redirectUrl= URLEncoder.encode(redirectUrl,"utf-8");
            //拼接三个参数 ：id  秘钥 和 code值

        }catch (Exception e){
                e.printStackTrace();
        }
        String url=String.format(baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                state
        );
        System.out.println(url.toString());
        return "redirect:"+url;
    }
    @GetMapping("callback")
    public String callBack(@RequestParam String code,String state){
        //从redis中将state获取出来，和当前传入的state作比较
        //如果一致则放行，如果不一致则抛出异常：非法访问

        //向认证服务器发送请求换取access_token
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        String accessTokenUrl = String.format(baseAccessTokenUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                ConstantWxUtils.WX_OPEN_APP_SECRET,
                code);
        String result = "";
        String token="";
        try {
            result = HttpClientUtils.get(accessTokenUrl);
            //解析json字符串
            Gson gson = new Gson();
            HashMap map = gson.fromJson(result, HashMap.class);
            String accessToken = (String)map.get("access_token");
            String openid = (String)map.get("openid");
            //查询此微信是否注册
            UcenterMember ucenterMember = ucenterMemberService.getById(openid);
            if(ucenterMember==null){
                //获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);
                String resultUserInfo = null;
                resultUserInfo = HttpClientUtils.get(userInfoUrl);
                //解析json
                HashMap<String, Object> mapUserInfo = gson.fromJson(resultUserInfo, HashMap.class);
                String nickname = (String)mapUserInfo.get("nickname");
                String headimgurl = (String)mapUserInfo.get("headimgurl");
                ucenterMember=new UcenterMember();
                ucenterMember.setId(openid);
                ucenterMember.setNickname(nickname);
                ucenterMember.setAvatar(headimgurl);
                ucenterMemberService.save(ucenterMember);
                token= JwtUtils.getJwtToken(openid,nickname);
            }else{
                token= JwtUtils.getJwtToken(openid,ucenterMember.getNickname());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuLiEception(20001, "登录失败");
        }
        return "redirect:http://localhost:3000?token="+token;
    }
}
