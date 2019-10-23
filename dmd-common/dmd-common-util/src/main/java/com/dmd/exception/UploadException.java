package com.dmd.exception;

import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * The class Oms biz exception.
 *
 * @author paascloud.net @gmail.com
 */
@Slf4j
public class UploadException extends BusinessException {

    private static final long serialVersionUID = -6552248511084911254L;

    /**
     * Instantiates a new Oms rpc exception.
     */
    public UploadException() {
    }

    /**
     * Instantiates a new Oms rpc exception.
     *
     * @param code      the code
     * @param msgFormat the msg format
     * @param args      the args
     */
    public UploadException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
        log.info("<== OmsRpcException, code:" + this.code + ", message:" + super.getMessage());

    }

    /**
     * Instantiates a new Oms rpc exception.
     *
     * @param code the code
     * @param msg  the msg
     */
    public UploadException(int code, String msg) {
        super(code, msg);
        log.info("<== OmsRpcException, code:" + this.code + ", message:" + super.getMessage());
    }

    /**
     * Instantiates a new Oms rpc exception.
     *
     * @param codeEnum the code enum
     */
    public UploadException(ErrorCodeEnum codeEnum) {
        super(codeEnum.code(), codeEnum.msg());
        log.info("<== OmsRpcException, code:" + this.code + ", message:" + super.getMessage());
    }

    /**
     * Instantiates a new Oms rpc exception.
     *
     * @param codeEnum the code enum
     * @param args     the args
     */
    public UploadException(ErrorCodeEnum codeEnum, Object... args) {
        super(codeEnum, args);
        log.info("<== OpcRpcException, code:" + this.code + ", message:" + super.getMessage());
    }
}
