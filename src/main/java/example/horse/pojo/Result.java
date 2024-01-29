package example.horse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by LiuSheng at 2024/1/25 14:18
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;
    private static final Integer SUCCESS = 0;
    private static final Integer ERROR = 1;


    //快速返回操作成功响应结果(带响应数据)
    public static <E> Result<E> success(E data) {
        return new Result<>(SUCCESS, "操作成功", data);
    }

    //快速返回操作成功响应结果
    public static Result<?> success() {
        return new Result<>(SUCCESS, "操作成功", null);
    }

    //快速返回操作成功响应结果
    public static Result<?> success(String message) {
        return new Result<>(SUCCESS, message, null);
    }

    public static Result<?> error(String message) {
        return new Result<>(ERROR, message, null);
    }
}

