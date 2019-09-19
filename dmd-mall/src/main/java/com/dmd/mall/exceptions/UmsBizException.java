package com.dmd.mall.exceptions;

import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * The class Ums biz exception.
 *
 * @author paascloud.net @gmail.com
 */
@Slf4j
public class UmsBizException extends BusinessException {

    private static final long serialVersionUID = -6552248511084911254L;

    /**
     * Instantiates a new Ums rpc exception.
     */
    public UmsBizException() {
    }

    /**
     * Instantiates a new Ums rpc exception.
     *
     * @param code      the code
     * @param msgFormat the msg format
     * @param args      the args
     */
    public UmsBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
        log.info("<== UmsRpcException, code:" + this.code + ", message:" + super.getMessage());

    }

    /**
     * Instantiates a new Ums rpc exception.
     *
     * @param code the code
     * @param msg  the msg
     */
    public UmsBizException(int code, String msg) {
        super(code, msg);
        log.info("<== UmsRpcException, code:" + this.code + ", message:" + super.getMessage());
    }

    /**
     * Instantiates a new Ums rpc exception.
     *
     * @param codeEnum the code enum
     */
    public UmsBizException(ErrorCodeEnum codeEnum) {
        super(codeEnum.code(), codeEnum.msg());
        log.info("<== UmsRpcException, code:" + this.code + ", message:" + super.getMessage());
    }

    /**
     * Instantiates a new Ums rpc exception.
     *
     * @param codeEnum the code enum
     * @param args     the args
     */
    public UmsBizException(ErrorCodeEnum codeEnum, Object... args) {
        super(codeEnum, args);
        log.info("<== OpcRpcException, code:" + this.code + ", message:" + super.getMessage());
    }
}
