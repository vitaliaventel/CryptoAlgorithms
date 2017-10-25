package ua.leshchenko.rc4;

/**
 * Created by Vitalii Leshchenko on 25.10.2017.
 */
public class RC4 {

    private byte[] sBox = new byte[256];
    private int counterX;
    private int counterY;

    public RC4() {
        this.counterX = 0;
        this.counterY = 0;
    }

    public RC4(byte[] key) {
        init(key);
        this.counterX = 0;
        this.counterY = 0;
    }

    public byte[] encode(byte[] data, int size) {
        byte[] tempData = data;
        byte[] cipher = new byte[tempData.length];
        for (int m = 0; m < tempData.length; m++) {
            cipher[m] = (byte) (tempData[m] ^ keyItem());
        }

        return cipher;
    }

    public byte[] decode(byte[] data, int size) {
        return encode(data, size);
    }

    // Key-scheduling algorithm (KSA)
    private void init(byte[] key) {
        int keyLength = key.length;
        for (int i = 0; i < 256; i++) {
            sBox[i] = (byte) i;
        }
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + unsignedToBytes(sBox[i]) + key[i % keyLength]) % 256;
            sBox = swap(sBox, i, j);
        }
    }

    private byte[] swap(byte[] input, int firstIndex, int secondIndex) {
        byte temporary = input[firstIndex];
        input[firstIndex] = input[secondIndex];
        input[secondIndex] = temporary;
        return input;
    }

    // Pseudo-random generation algorithm (PRGA)
    private byte keyItem() {
        counterX = (counterX + 1) % 256;
        counterY = (counterY + unsignedToBytes(sBox[counterX])) % 256;
        sBox = swap(sBox, counterX, counterY);
        return sBox[(unsignedToBytes(sBox[counterX]) + unsignedToBytes(sBox[counterY])) % 256];
    }

    // Signed byte to unsigned byte method
    private int unsignedToBytes(byte b) {
        return b & 0xFF;
    }
}
