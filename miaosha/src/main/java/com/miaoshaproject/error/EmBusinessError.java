package com.miaoshaproject.error;

/**
 * 企业级的项目中，需要有一个统一的错误状态码流转。因此需要在同一个文件里定义错误状态码。
 * 此处，模仿这种做法。
 *
 *
 * @author: Peter
 * @date: 2020/12/26 0:14
 */
public enum EmBusinessError implements CommonError {

    // 定义一个通用的错误类型00001
    PARAMETER_VALIDATION_ERROR(00001, "参数不合法"), // 入参校验时使用，但是不同的业务中需要将 errMsg 修改为更为准确的描述。此时就需要setErrMsg()接口方法去修改错误描述。

    // 10000开头为用户信息相关错误定义
    USER_NOT_EXIST(10001, "用户不存在"),     // 以后要往对应的错误信息中添值，则在在此处无限添加错误信息的枚举值即可。
    ;

    /**
     * 需要定义构造方法，否则上面的常量会报错
     */
    private EmBusinessError(int errCode, String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    // 枚举中是可以有成员变量属性的，因为枚举本质上就是一个面向对象的类。
    private int errCode;
    private String errMsg;

    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    /**
     * 定制化方式去改动 errMsg ，
     * @param errMsg
     * @return
     */
    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
