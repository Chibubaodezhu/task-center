package com.huangjiang.taskcenter.security;

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
