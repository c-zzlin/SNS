package com.clim.common.util;

import com.clim.common.enums.ResultCode;
import com.clim.common.enums.ResultEnum;
import com.clim.common.model.Result;

public class ResultUtil {
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
