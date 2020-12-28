package com.miaoshaproject.error;

/**
 * 通用的错误形式接口
 *
 * @author: Peter
 * @date: 2020/12/26 0:12
 */
public interface CommonError {

    int getErrCode();
    String getErrMsg();
    CommonError setErrMsg(String errMsg);
}
