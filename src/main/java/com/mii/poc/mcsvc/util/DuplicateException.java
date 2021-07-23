package com.mii.poc.mcsvc.util;

/**
 *
 * @author ErwinSn
 */
public class DuplicateException extends RuntimeException {
    
    public DuplicateException(String accountNumber) {
        super("Account Number '" + accountNumber + "' already exist");
    }
    
}
