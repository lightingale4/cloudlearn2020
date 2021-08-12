package com.atguigu.springcloud.exception;

/**
 * @author ding.li
 */
public class DatabaseOperateException extends RuntimeException {

    private static final long serialVersionUID = -123765430L;

    public DatabaseOperateException() {
            super("数据库操作异常");
    }

}
