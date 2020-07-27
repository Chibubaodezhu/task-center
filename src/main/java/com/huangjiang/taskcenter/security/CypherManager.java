package com.huangjiang.taskcenter.security;

/**
 * Created by liming on 2017/4/6.
 */
public interface CypherManager {

    /**
     * 加密
     */
    String encrypt(String plainText) throws Exception;

    /**
     * 解密
     */
    String decrypt(String cipherText) throws Exception;
}
