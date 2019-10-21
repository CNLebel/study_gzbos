package com.gyf.bos.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    private T t;  //属性-用于接收参数

    //空参构造方法实现t的实例化
    public BaseAction(){
        //1.获取t的真实类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();

        //获取泛型真实类型
        Type[] types = pt.getActualTypeArguments();

        //把type类型赋值给Class类型
        Class<T> clz = (Class<T>) types[0];

        //2.通过反射创建对象
        try {
            t = clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    public abstract String save();

    public abstract String delete();

    public abstract String update();

    public abstract String list();




    @Override
    public T getModel() {
        return t;
    }
}
