package defpackage;

/* JADX WARN: Classes with same name are omitted:
  /home/sakana/myrealhome/workshop/secsys-homework/lab1-AndroidReverse1/hw/Step2_smali_code_files/smali/Checkbox.dex
 */
/* loaded from: /tmp/jadx-6340797745023241409.dex */
public class Checker {
    private String secret = "key";

    private boolean checkStr1(String str) {
        char[] charArray = str.toCharArray();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < charArray.length; i4++) {
            if (charArray[i4] == 'x') {
                i++;
                if (i == 1) {
                    i3 = i4;
                }
                if (i == 2) {
                    i2 = i4;
                }
            }
        }
        return i == 2 && i2 - i3 == 4 && charArray[0] == '0' && charArray[charArray.length + (-1)] == '9' && str.substring(0, i3).contains(this.secret);
    }

    private int func(int i) {
        if (i <= 1) {
            return 1;
        }
        return func(i - 1) * i;
    }

    private int count(String str) {
        int i = 0;
        char[] charArray = str.toCharArray();
        int i2 = 0;
        while (true) {
            int i3 = i;
            if (i2 < str.length()) {
                i = charArray[i2] == '1' ? i3 + 1 : i3;
                i2++;
            } else {
                return i3;
            }
        }
    }

    public boolean check(String str) {
        if (str.length() < 12 || str.length() > 16) {
            return false;
        }
        String substring = str.substring(0, 10);
        int count = count(str.substring(10, str.length()));
        return func(count) == count && checkStr1(substring);
    }
}
