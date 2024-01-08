package es.albarregas.models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    /**
     * Codifica una cadena de texto en formato MD5.
     *
     * @param password la cadena a codificar
     * @return devuelce la cadena codificada en MD5
     * @throws NoSuchAlgorithmException si el algoritmo de codificación no está
     * disponible
     */
    public static String Codificar(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder stringBuilder = new StringBuilder();
        for (byte i : hashInBytes) {
            stringBuilder.append(String.format("%02x", i));
        }

        return stringBuilder.toString();
    }
}
