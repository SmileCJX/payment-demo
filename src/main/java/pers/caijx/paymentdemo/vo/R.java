package pers.caijx.paymentdemo.vo;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName R
 * @Description: TODO
 * @Author JunXiangCai
 * @Date 2022/12/31
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
public class R {

    private Integer code; // 响应码

    private String message; // 响应消息

    private Map<String, Object> data = new HashMap<>();

    public static R ok() {
        R r = new R();
        r.setCode(0);;
        r.setMessage("成功");
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(-1);;
        r.setMessage("失败");
        return r;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
