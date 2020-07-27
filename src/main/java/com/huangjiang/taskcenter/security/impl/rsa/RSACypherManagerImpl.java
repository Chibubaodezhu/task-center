package com.huangjiang.taskcenter.security.impl.rsa;

import com.huangjiang.taskcenter.security.CypherManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by liming on 2017/4/6.
 */
public class RSACypherManagerImpl implements CypherManager {
    private static Logger logger = LoggerFactory.getLogger(RSACypherManagerImpl.class);

    protected Resource privatePemKeyResource;
    protected Resource publicPemKeyResource;

    @Override
    public String encrypt(String plainText) throws Exception {
        String contentFromPEM = RSAKeyUtils.extractBase64ContentFromPEM(publicPemKeyResource.getInputStream());
        logger.debug("加载到公钥pem：{}",contentFromPEM);
        byte[] key = RSAKeyUtils.decodeBase64(contentFromPEM);
        logger.debug("加载到公钥der：{}",key);
        PublicKey publicKey = RSAKeyUtils.loadPublicKey(key);
        return RSAKeyUtils.encodeBase64(encrypt(publicKey, plainText.getBytes()));
    }

    @Override
    public String decrypt(String cipherText) throws Exception {
        String contentFromPEM = RSAKeyUtils.extractBase64ContentFromPEM(privatePemKeyResource.getInputStream());
        logger.debug("加载到私钥pem：{}", contentFromPEM);
        byte[] key = RSAKeyUtils.decodeBase64(contentFromPEM);
        logger.debug("加载到私钥der：{}", key);
        PrivateKey privateKey = RSAKeyUtils.loadPrivateKey(key);
        return new String(decrypt(privateKey, RSAKeyUtils.decodeBase64(cipherText)));
    }

    /**
     * 加密过程
     * @param publicKey     公钥
     * @param plainTextData 明文数据
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    private byte[] encrypt(PublicKey publicKey, byte[] plainTextData)
            throws Exception {
        if (publicKey == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        //加密实例需要缓存，避免每次都加载影响性能
        Cipher encryptCipher = Cipher.getInstance("RSA");// , new BouncyCastleProvider());
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return encryptCipher.doFinal(plainTextData);
    }

    /**
     * 解密过程
     *
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    private byte[] decrypt(PrivateKey privateKey, byte[] cipherData)
            throws Exception {
        if (privateKey == null) {
            throw new Exception("解密私钥为空, 请设置");
        }
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        return decryptCipher.doFinal(cipherData);
    }

    public void setPrivatePemKeyResource(Resource privatePemKeyResource) {
        this.privatePemKeyResource = privatePemKeyResource;
    }

    public void setPublicPemKeyResource(Resource publicPemKeyResource) {
        this.publicPemKeyResource = publicPemKeyResource;
    }
}
