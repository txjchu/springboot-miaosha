package com.miaoshaproject.service.serviceImpl;

import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.dao.UserPasswordDOMapper;
import com.miaoshaproject.dateobject.UserDO;
import com.miaoshaproject.dateobject.UserPasswordDO;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Peter
 * @date: 2020/12/25 21:43
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public UserModel getUserById(Integer id) {
        // 通过 userDOMapper 获取 userDO 用户 dataObject
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (userDO == null){
            return null;
        }
        // 通过用户ID 获取到用户的加密密码信息
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        if(userPasswordDO == null){
            return null;
        }

        return convertFromDataObject(userDO, userPasswordDO);
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO){
        if(userDO == null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);    // 使用 Spring 提供的复制对象方法，将 user DO对象数据赋值给 Model 对象中

        if (userPasswordDO != null) {
            userModel.setPassword(userPasswordDO.getEncrptPassword());  // 此处不能再使用 copyProperties()方法，因为2个对象都有 id 属性。
        }
        return userModel;
    }
}
