package org.seckill.dto;

/**
 * @Author: wq57fan
 * @Date: 2018/8/12 18:12
 * @Description: 封装json结果。所有的ajax请求返回类型都是他
 */
public class SeckillResult<T> {

    private boolean success;

    //返回成功且success为true时返回数据
    private T data;

    //success为false则报错
    private String error;

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
