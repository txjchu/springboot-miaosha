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

/**
 * @author: Peter
 * @date: 2020/12/25 21:40
 */
@Controller("user") // 该 Controller 的 name 就是 user
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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
