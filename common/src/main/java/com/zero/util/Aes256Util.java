package com.zero.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class Aes256Util {
    private static final String ALG = "AES/CBC/PKCS5Padding";
    private static final String KEY = "12345678901234567890123456789012";
    private static final String KEY_256 = KEY.substring(0, 16);

    public static String encrypt(String text) {
        try {
            Cipher cipher = Cipher.getInstance(ALG);
            cipher.init(Cipher.ENCRYPT_MODE,  new SecretKeySpec(KEY.getBytes(), "AES"),
                    new IvParameterSpec(KEY_256.getBytes()));
            byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return e.toString();
        }
    }

    public static String decrypt(String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance(ALG);
            cipher.init(Cipher.DECRYPT_MODE,  new SecretKeySpec(KEY.getBytes(), "AES"),
                    new IvParameterSpec(KEY_256.getBytes()));
            byte[] decodeBytes = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decodeBytes);

            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return e.toString();
        }
    }
}
