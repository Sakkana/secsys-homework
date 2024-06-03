package defpackage;

import java.security.MessageDigest;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  /home/sakana/myrealhome/workshop/secsys-homework/lab1-AndroidReverse1/hw/Step2_smali_code_files/smali/Checkbox.dex
 */
/* loaded from: /tmp/jadx-7337982182538952104.dex */
public class Encoder {
    private final String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    private String algorithm = "MD5";
    private String charSet = "utf-8";

    public String encoding(String str) {
        String str2;
        String str3 = "";
        String salt = getSalt();
        try {
            str2 = "" + byteArrayToHexString(MessageDigest.getInstance(this.algorithm).digest((str + salt).getBytes(this.charSet)));
        } catch (Exception e) {
            e = e;
        }
        try {
            char[] cArr = new char[48];
            for (int i = 0; i < 48; i += 3) {
                cArr[i] = str2.charAt((i / 3) * 2);
                cArr[i + 1] = salt.charAt(i / 3);
                cArr[i + 2] = str2.charAt(((i / 3) * 2) + 1);
            }
            return new String(cArr);
        } catch (Exception e2) {
            e = e2;
            str3 = str2;
            e.printStackTrace();
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
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String byteToHexString(byte r5) {
        /*
            r4 = this;
            if (r5 >= 0) goto L4
            r5 = r5 & 255(0xff, float:3.57E-43)
        L4:
            int r0 = r5 / 16
            int r1 = r5 % 16
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String[] r3 = r4.hexDigits
            r0 = r3[r0]
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String[] r2 = r4.hexDigits
            r1 = r2[r1]
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Encoder.byteToHexString(byte):java.lang.String");
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
