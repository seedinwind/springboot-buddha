package org.buddha.wise.auth.service.impl;

import com.google.gson.Gson;
import org.buddha.wise.JsonResult;
import org.buddha.wise.OkHttpUtil;
import org.buddha.wise.auth.model.AuthUser;
import org.buddha.wise.auth.model.User;
import org.buddha.wise.auth.model.WxTokenResult;
import org.buddha.wise.auth.model.WxUserInfo;
import org.buddha.wise.auth.repo.UserRepository;
import org.buddha.wise.auth.service.WxAuthService;
import org.buddha.wise.config.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
@SuppressWarnings("all")
public class WxAuthServiceImpl implements WxAuthService {

  private final String URL_ACCESSTOKEN= "https://api.weixin.qq.com/sns/oauth2/access_token";

    @Value("${wechat.appId}")
    private String appId;

    @Value("$wechat.secret")
    private String secret;

    @Autowired
    private OkHttpUtil okhttp;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public JsonResult<String> login(String code) {
        //获取accessToken  openId
        //更新保存用户信息
        //返回jwtToken
        try {
            String tokenRes=okhttp.getRequest(getAccessTokenUrl(code));
            Gson gson=new Gson();
            WxTokenResult res=  gson.fromJson(tokenRes,WxTokenResult.class);
            if(res.getAccess_token()==null){
                return new JsonResult<>(502,"获取微信Token失败");
            }else{
                User userToAdd=userRepository.findByName(res.getOpenid());
                if(userToAdd==null){
                    userToAdd=new User();
                    userToAdd.addRole("ROLE_USER");
                    userToAdd.setType(User.UserType.WX.ordinal());
                    userToAdd.setName(res.getOpenid());
                }
                userToAdd.setPassword(res.getAccess_token()+"_weixin_"+res.getRefresh_token());
                userToAdd.setLastPasswordResetDate(new Date());
                userRepository.save(userToAdd);
                return new JsonResult<>(jwtTokenUtil.generateToken(AuthUser.create(userToAdd)));
            }
        }catch (IOException e){
            return new JsonResult<>(501,"获取微信Token失败");
        }
    }

    private String getAccessTokenUrl(String code) {
        return new StringBuilder()
                .append(URL_ACCESSTOKEN)
                .append("?appid=")
                .append(appId)
                .append("&secret=")
                .append(secret)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code")
                .toString();
    }

    @Override
    public void refreshToken(String refresh) {

    }

    @Override
    public WxUserInfo getWxInfo() {
        return null;
    }
}
