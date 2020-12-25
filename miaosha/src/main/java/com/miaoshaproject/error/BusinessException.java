package com.miaoshaproject.error;

/**
 * 笔记：包装器业务异常类的实现
 *
 * 当程序出现任何 exception ，会抛出一个统一的 exception，该异常会被最后的 springboot 的 handle 捕获并做处理。
 *
 * @author: Peter
 * @date: 2020/12/26 0:28
 */
public class BusinessException extends Exception  implements CommonError {

    // 需要强关联一个 CommonError
    private CommonError commonError;    // 此处该成员变量其实是实现了该接口的枚举 EmBusinessError 对象

    // 并且需要有对应的构造函数，方便我们使用
    // 意义：直接接受枚举 EmBusinessError 的传参，用于构造具体业务异常
    public BusinessException(CommonError commonError){
        super();        // 注意，必须调用 Exception 的构造方法，因为 Exception 的构造方法会有一些异常自身的初始化机制。
        this.commonError = commonError;
    }

    // 接受自定义 errMsg 的方式构造更加准确描述的具体业务异常
    public BusinessException(CommonError commonError, String errMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg); // 注意
    }


    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
