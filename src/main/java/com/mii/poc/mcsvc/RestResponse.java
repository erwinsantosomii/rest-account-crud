package com.mii.poc.mcsvc;

/**
 *
 * @author ErwinSn
 */
public class RestResponse<T> {
    
    private boolean success;
    private int code;
    private String message;
    private T result;
    
    public RestResponse(T result) {
        this.result = result;
    }
    
    public static RestResponse success(String message, Object o) {
        RestResponse rr = new RestResponse(message);
        rr.setSuccess(true);
        rr.message = message;
        rr.code = 0;
        rr.setResult(o);
        return rr;
    }
    
    public static RestResponse success(Object o) {
        return RestResponse.success("OK", o);
    }
    
    public static RestResponse failed(String message, int code) {
        RestResponse rr = new RestResponse(message);
        rr.setSuccess(false);
        rr.message = message;
        rr.code = code;
        return rr;
    }
    
    public static RestResponse failed(int code) {
        return RestResponse.failed(code);
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the result
     */
    public T getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(T result) {
        this.result = result;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
}
