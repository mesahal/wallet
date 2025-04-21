package com.wallet.edgeserver.util;

import lombok.experimental.UtilityClass;

import java.util.Base64;

@UtilityClass
public class SerializationUtils {

    public static String toBase64(byte[] byteArray) {
        return Base64.getUrlEncoder().encodeToString(byteArray);
    }

}
