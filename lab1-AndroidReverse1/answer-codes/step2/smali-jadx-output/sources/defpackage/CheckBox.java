package defpackage;

import java.util.Scanner;

/* JADX WARN: Classes with same name are omitted:
  /home/sakana/myrealhome/workshop/secsys-homework/lab1-AndroidReverse1/hw/Step2_smali_code_files/smali/Checkbox.dex
 */
/* loaded from: /tmp/jadx-7337982182538952104.dex */
public class CheckBox {
    private String rawInfo;
    Encoder encoder = new Encoder();
    Checker checker = new Checker();

    public boolean checkEncP(String str) {
        return this.encoder.check(this.rawInfo, str);
    }

    public String getEncP() {
        return this.encoder.encoding(this.rawInfo);
    }

    private boolean checkInput(String str) {
        return this.checker.check(str);
    }

    public static void main(String[] strArr) {
        String str;
        CheckBox checkBox = new CheckBox();
        System.out.print("input: ");
        String nextLine = new Scanner(System.in).nextLine();
        checkBox.rawInfo = nextLine;
        if (strArr.length == 0 && nextLine.length() == 11) {
            str = "Task 2: (Encoded msg) " + checkBox.getEncP();
        } else if (strArr.length != 1) {
            str = "Task 1: " + checkBox.checkInput(nextLine);
        } else {
            str = "Task 2: " + checkBox.checkEncP(strArr[0]);
        }
        System.out.println(str);
    }
}
