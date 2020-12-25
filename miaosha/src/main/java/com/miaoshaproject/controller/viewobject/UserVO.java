package com.miaoshaproject.controller.viewobject;

/**
 * 视图模型对象
 * @author: Peter
 * @date: 2020/12/25 22:43
 */
public class UserVO {

    // 该类中的属性的类型需要与原 object 相同，否则使用 spring 提供的 copyObject 方法时会因为类型不同而造成数据丢失，被赋值为 null
    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;
    private String telphone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }
}
