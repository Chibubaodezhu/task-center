package com.huangjiang.taskcenter.security.impl.rsa;

import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAKeyUtils {

    public static String extractBase64ContentFromPEM(InputStream in) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String readLine = null;
        StringBuilder sb = new StringBuilder();
        while ((readLine = br.readLine()) != null) {
            if (readLine.charAt(0) != '-') {
                sb.append(readLine);
            }
        }
        return sb.toString();
    }

    public static byte[] decodeBase64(String content){
        return Base64.decodeBase64(content);
    }

    public static String encodeBase64(byte[] content){
        return Base64.encodeBase64String(content);
    }

    /**
     * @param privateKeyBytes
     * @throws Exception
     */
    public static PrivateKey loadPrivateKey(byte[] privateKeyBytes) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyBytes
     *            公钥数据字符串
     * @throws Exception
     *             加载公钥时产生的异常
     */
    public static PublicKey loadPublicKey(byte[]  publicKeyBytes) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        return keyFactory.generatePublic(keySpec);
    }

}
