package ua.leshchenko.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vitalii Leshchenko on 26.10.2017.
 */
public class Utils {

    public static List<byte[]> chunk(byte[] tBytes) {
        List<byte[]> result = new ArrayList<>();
        for (int i = 0; i < tBytes.length; i += 16) {
            byte[] current = Arrays.copyOfRange(tBytes, i, Math.min(tBytes.length, i + 16));
            byte[] testBytes;
            if (current.length <= 16) {
                testBytes = new byte[16];
                for (int j = 0; j < current.length; j++) {
                    testBytes[j] = current[j];
                }
            } else {
                testBytes = tBytes;
            }
            result.add(testBytes);
        }
        return result;
    }
}
