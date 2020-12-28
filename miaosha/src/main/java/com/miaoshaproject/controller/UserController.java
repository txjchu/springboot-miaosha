package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;


/**
 * @author: Peter
 * @date: 2020/12/25 21:40
 */
@Controller("user") // 该 Controller 的 name 就是 user
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    //
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 用户获取 otp 短信接口
     * @return
     */
    @ResponseBody
    @RequestMapping("/getotp")
    public CommonReturnType getOtp(@RequestParam(name = "telephone")String telephone){
        // 需要按照一定的规则生产 OTP 验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String otpCode = String.valueOf(randomInt);

//        String otpCode = RandomStringUtils.randomNumeric(6);  // 用apache的commons工具包也可以获取随机数

        // 将 OTP 验证码同对应用户的手机号关联 (一般使用 Redis, 此处使用 session 模仿)
        // 使用 httpsession 方式，将用户手机号与验证码绑定
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute(telephone, otpCode);

        // 将OTP 验证码通过短信通道发送给用户，完成用户注册 （省略）
        System.out.println("telephone:"+ telephone +" & otpCode: "+ otpCode);   // 实际项目中不能将其输出到日志中，会暴露用户敏感信息

        return CommonReturnType.create(null);
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        // 调用 service 服务获取对应id的用户对象并返回给前端
        UserModel userModel = userService.getUserById(id);

        // 使用业务异常类的包装器捕获异常，定义异常，并抛出异常
        if (userModel == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);    // 使用枚举中定义的异常来构造一个 BusinessException 异常对象。
        }

        // 将核心领域模型对象转化为可供前端使用的 viewObject 对象
        // 返回通用对象
        return CommonReturnType.create(convertFromUserModel(userModel));    // {"status":"success","data":{"id":6,"name":"zhongchao","gender":1,"age":11,"telphone":"17682310538"}}
    }



    private UserVO convertFromUserModel(UserModel userModel){
        if (userModel == null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
}
