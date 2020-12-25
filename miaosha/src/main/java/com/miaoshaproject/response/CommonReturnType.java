package com.miaoshaproject.response;

/**
 * 定义的通用的返回类型，包含一个字符串类型的 status ,和一个 Object 对象数据。
 * 方便前端解析数据和处理异常。
 * 服务器中，如果说有业务异常或错误，则
 *
 * @author: Peter
 * @date: 2020/12/25 23:03
 */
public class CommonReturnType {

    /**
     * 表明对应请求的处理结果，有 success 或 fail.
     */
    private String status;

    /**
     * 返回的数据
     * 若 status = success ,则返回前端需要的 JSON 数据。
     * 若 status = fail , 则返回通用的错误码格式。
     */
    private Object data;

    /**
     * 定义通用的创建方法
     * @param result
     * @return
     */
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result, "success");
    }

    /**
     * 定义通用的创建方法
     * @param result
     * @param status
     * @return
     */
    public static CommonReturnType create(Object result, String status){
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
