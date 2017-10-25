package ua.leshchenko.rc4;

import java.io.UnsupportedEncodingException;

/**
 * Created by Vitalii Leshchenko on 25.10.2017.
 */
public class Runner {

    public static void main(String[] args) {
        byte[] key = "KPI".getBytes();
        RC4 encoder = new RC4(key);
        String test = "I love Salesforce";
        byte[] testBytes = test.getBytes();
        byte[] result = encoder.encode(testBytes, testBytes.length);

        RC4 decoder = new RC4(key);
        byte[] decryptedBytes = decoder.decode(result, result.length);
        try {
            String decryptedTestString = new String(decryptedBytes, "UTF-8");
            System.out.println("Result value >>> " + decryptedTestString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
