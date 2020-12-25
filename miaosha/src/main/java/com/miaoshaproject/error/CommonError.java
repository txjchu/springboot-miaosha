package com.miaoshaproject.error;

/**
 * @author: Peter
 * @date: 2020/12/26 0:12
 */
public interface CommonError {

    public int getErrorCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);
}
