package com.sise.educenter.controller;

import com.google.gson.Gson;
import com.sise.commonutils.JwtUtils;
import com.sise.educenter.entity.UcenterMember;
import com.sise.educenter.service.UcenterMemberService;
import com.sise.educenter.utils.ConstantWxUtils;
import com.sise.educenter.utils.HttpClientUtils;
import com.sise.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.util.HashMap;

@CrossOrigin
@Controller  //只是请求地址，不需要返回数据
@RequestMapping("/api/ucenter/wx")
public class WxApiController {
    @Autowired
    private UcenterMemberService memberService;

    //2 获取扫描人信息，添加数据
    @GetMapping("callback")
    public String callback(String code, String state) {

        try {
            //        System.out.println("code:" + code);
//        System.out.println("state:" + state);
            //1 获取code值，临时票据，类似于验证码
            //2 拿着code请求 微信固定的地址，得到两个值 accsess_token 和 openid
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            //拼接三个参数 ：id  秘钥 和 code值
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    ConstantWxUtils.WX_OPEN_APP_ID,
                    ConstantWxUtils.WX_OPEN_APP_SECRET,
                    code
            );
            //请求这个拼接好的地址，得到返回两个值 accsess_token 和 openid
            //使用httpclient发送请求，得到返回结果
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);

            /*
             * System.out.println("accessTokenInfo:"+accessTokenInfo);
             * accessTokenInfo:{
             * "access_token":"56_mWKJzfmzdf-AIdMlj7AL1JgLIAfLhohnRPKWdtL4geRZZWYbXFVNYTYzKm3oNIxwpXFNHo93XNEArhvOVNemGDFsXXpQCR32nw3PBHTx7vM",
             * "expires_in":7200,
             * "refresh_token":"56_mW35kYPYBRqrGySzC_LHEgr8w2nHRS0wD09tThjf36yHUR2KWjWD0n6hmVUU7fenSjM6ODUc3HCRvQqfZKILUapdTnfxlhXuYlyxw-siq34",
             * "openid":"o3_SC5ylVpsY4NQbdym6CDI1BTv4",
             * "scope":"snsapi_login",
             * "unionid":"oWgGz1DYXi1QjPOjZNPAmPiibqCs"}
             * */
            //从accessTokenInfo字符串获取出来两个值 accsess_token 和 openid
            //把accessTokenInfo字符串转换map集合，根据map里面key获取对应值
            //使用json转换工具 Gson
            Gson gson = new Gson();
            HashMap mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);
            String access_token = (String) mapAccessToken.get("access_token");
            String openid = (String) mapAccessToken.get("openid");

            UcenterMember member = memberService.getOpenIdMember(openid);
            if (member == null){
                //3 拿着得到accsess_token 和 openid，再去请求微信提供固定的地址，获取到扫描人信息
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                //拼接两个参数
                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        access_token,
                        openid
                );
                //发送请求
                String userInfo = HttpClientUtils.get(userInfoUrl);

                /*
                 * System.out.println("userInfo:" + userInfo);
                 * userInfo:{
                 * "openid":"o3_SC5ylVpsY4NQbdym6CDI1BTv4",
                 * "nickname":"myth",
                 * "sex":0,
                 * "language":"",
                 * "city":"",
                 * "province":"",
                 * "country":"",
                 * "headimgurl":"https:\/\/thirdwx.qlogo.cn\/mmopen\/vi_32\/A4Dgzwibc1aUG3iaQcH9zNX6gicibvnR192xxlzEAreumDnakEY1ZslT9iby8ibPSDTIw8PwSuk0ZO0T74krZO1WdT3g\/132",
                 * "privilege":[],
                 * "unionid":"oWgGz1DYXi1QjPOjZNPAmPiibqCs"}
                 * */
                //获取返回userinfo字符串扫描人信息
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String) userInfoMap.get("nickname");
                String headimgurl = (String) userInfoMap.get("headimgurl");
                member = new UcenterMember();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimgurl);
                memberService.save(member);
            }

            return "redirect:http://localhost:3000";

        } catch (Exception e) {
            throw new GuliException(20001,"登录失败");
        }

    }

    //1 生成微信扫描二维码
    @GetMapping("login")
    public String getWxCode() {
        //固定地址，后面拼接参数
//        String url = "https://open.weixin.qq.com/" +
//                "connect/qrconnect?appid="+ ConstantWxUtils.WX_OPEN_APP_ID+"&response_type=code";

        // 微信开放平台授权baseUrl  %s相当于?代表占位符
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        //对redirect_url进行URLEncoder编码
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        } catch (Exception e) {
        }

        //设置%s里面值
        String url = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                "atguigu"
        );

        //重定向到请求微信地址里面
        return "redirect:" + url;
    }
}
