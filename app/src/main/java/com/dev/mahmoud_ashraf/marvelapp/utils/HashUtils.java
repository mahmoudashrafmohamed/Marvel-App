package com.dev.mahmoud_ashraf.marvelapp.utils;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import timber.log.Timber;

/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */
public class HashUtils {
    public static String getHash(String ts, String private_key,String public_key){
        String hash = ts+private_key+public_key;
        return md5(hash);
    }

    private static String md5(String s) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            Timber.e("hashed value is: %s", hexString.toString());
            return hexString.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
