package defpackage;

import java.security.MessageDigest;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  /home/sakana/myrealhome/workshop/secsys-homework/lab1-AndroidReverse1/hw/Step2_smali_code_files/smali/Checkbox.dex
 */
/* loaded from: /tmp/jadx-6340797745023241409.dex */
public class Encoder {
    private final String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    private String algorithm = "MD5";
    private String charSet = "utf-8";

    public String encoding(String str) {
        String str2 = null;
        String str3 = "";
        String salt = getSalt();
        try {
            str2 = "" + byteArrayToHexString(MessageDigest.getInstance(this.algorithm).digest((str + salt).getBytes(this.charSet)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            char[] cArr = new char[48];
            for (int i = 0; i < 48; i += 3) {
                int index = i / 3;
                cArr[i] = str2.charAt(index * 2);
                cArr[i + 1] = salt.charAt(index);
                cArr[i + 2] = str2.charAt((index * 2) + 1);
            }
            return new String(cArr);
        } catch (Exception e2) {
            str3 = str2;
            e2.printStackTrace();
            return str3;
        }
    }

    private String getSalt() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            if (random.nextBoolean()) {
                sb.append("1");
            } else {
                sb.append("0");
            }
        }
        return sb.toString();
    }

    private String byteArrayToHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(byteToHexString(b));
        }
        return stringBuffer.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
    
        r5 = r5;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String byteToHexString(byte b) {
        return Integer.toString((b & 0xff) + 0x100, 16).substring(1);
    }

    public boolean check(String str, String str2) {
        char[] cArr = new char[32];
        char[] cArr2 = new char[16];
        if (str2.length() != 48) {
            return false;
        }
        for (int i = 0; i < 48; i += 3) {
            cArr[(i / 3) * 2] = str2.charAt(i);
            cArr[((i / 3) * 2) + 1] = str2.charAt(i + 2);
            cArr2[i / 3] = str2.charAt(i + 1);
        }
        try {
            return ("" + byteArrayToHexString(MessageDigest.getInstance(this.algorithm).digest((str + new String(cArr2)).getBytes(this.charSet)))).equals(new String(cArr));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
