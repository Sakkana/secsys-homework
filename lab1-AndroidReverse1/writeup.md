# AndroidReverse1

## 一、安卓编程

### Task 1 What’s in Background

#### 1. SecretBootReceiver 类

##### 软件开机自启

添加权限

```xml
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>
```



在 Manifest 绑定 Receiver

```xml
<!-- 开机自启 -->
<receiver android:name="com.smali.secretchallenge.SecretBootReceiver"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.BOOT_COMPLETED"/>
        <category android:name="android.intent.category.DEFAULT"/>
    </intent-filter>
</receiver>
```



##### 自动开启一个 service

```java
context.startService(serviceIntent);
```





#### 2. SecretService 类

##### GPS 经纬度获取

LocationListner

LocationManager

getLatitude

getLongitude



##### 间隔 3 秒 Toast 位置信息

Timer

TimerTask

Handler



#### 3. 用户权限请求

ActivityCompat.requestPermissions



### Task 2 Change UI in the thread



在子线程中修改 UI.

```java
buttonShowDialog.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        showDialog(editTextInput.getText().toString());
                    }
                });
            }
        }).start();
    }
});
```





### Task 3 How to call that method?

```java
// 访问 curStr
Field field = Class
                .forName("com.pore.mylibrary.PoRELab")
                .getDeclaredField("curStr");

field.setAccessible(true);

PoRELab poRELab = new PoRELab();
Object strCur = field.get(poRELab);

assert strCur != null;
System.out.println("I get priavte member strCur: " + strCur.toString());

// 访问 privateMethod
Method method = Class
        .forName("com.pore.mylibrary.PoRELab")
        .getDeclaredMethod("privateMethod", new Class[]{String.class, String.class});

method.setAccessible(true);
method.invoke(poRELab, "Sakana", "Here");
```



## 二、Smali2Java 逆向

### Task 1 Checker

这段代码是一个名为 `CheckBox` 的类，它包含了一些字段和方法。以下是对代码的分析：

1. 字段：
   - `checker`：类型为 `LChecker` 的对象。
   - `encoder`：类型为 `LEncoder` 的对象。
   - `rawInfo`：类型为 `Ljava/lang/String` 的私有字符串。
2. 直接方法：
   - 构造方法 `<init>()`：初始化对象。在构造方法中，首先调用了父类 `java/lang/Object` 的构造方法，然后创建了 `LEncoder` 和 `LChecker` 对象，并将它们分别赋值给 `encoder` 和 `checker` 字段。
3. 私有方法：
   - `checkInput(Ljava/lang/String;)`：接受一个 `java/lang/String` 类型参数，并返回一个布尔值。该方法调用 `checker` 对象的 `check` 方法来检查输入字符串。
4. 静态方法：main([Ljava/lang/String;)：程序的入口方法。在 main 方法中，首先创建了一个 CheckBox 对象 v0，然后从标准输入中读取一个字符串作为输入，并将其保存到 rawInfo 字段中。接下来，根据命令行参数的数量执行不同的逻辑判断：
   - 如果命令行参数的数量为 0，执行一些特定的任务，并输出结果。
   - 如果命令行参数的数量为 1，调用 `checkEncP` 方法传入命令行参数进行处理，并输出结果。
   - 如果命令行参数的数量大于 1，调用 `checkInput` 方法传入命令行参数进行处理，并输出结果。
5. 虚拟方法：
   - `checkEncP(Ljava/lang/String;)`：接受一个 `java/lang/String` 类型参数，并返回一个布尔值。该方法调用 `encoder` 对象的 `check` 方法来检查输入字符串和 `rawInfo` 字段的值是否匹配。
   - `getEncP()`：返回一个 `java/lang/String` 类型的结果。该方法调用 `encoder` 对象的 `encoding` 方法对 `rawInfo` 字段的值进行编码处理。

请注意，这段代码是一个部分代码片段，无法独立运行。它可能是从一个更大的代码库中提取出来的一部分。因此，在分析代码时可能会缺少一些上下文信息。

如果您有关于特定部分的问题，或需要更多上下文信息，请提供更多详细信息，我将尽力为您提供帮助。



这段代码是一个名为 `Checker` 的类，它包含了一些字段和方法。以下是对代码的分析：

1. 字段：

   - `secret`：类型为 `Ljava/lang/String` 的私有字符串。

2. 直接方法：

   - 构造方法 `<init>()`：初始化对象。在构造方法中，首先调用了父类 `java/lang/Object` 的构造方法，然后为 `secret` 字段赋值为字符串 "key"。

3. 私有方法：

   - `checkStr1(Ljava/lang/String;)`：接受一个 `java/lang/String` 类型参数，并返回一个布尔值。该方法对输入字符串进行一系列检查和处理操作，并返回一个布尔值表示是否通过检查。
   - `count(Ljava/lang/String;)`：接受一个 `java/lang/String` 类型参数，并返回一个整数。该方法统计输入字符串中数字字符 '1' 的个数，并返回结果。
   - `func(I)`：接受一个整数参数，并返回一个整数。该方法是一个递归方法，根据输入的整数值进行一系列计算操作，并返回最终结果。

4. 虚拟方法：

   check(Ljava/lang/String;)：接受一个 java/lang/String 类型参数，并返回一个布尔值。该方法对输入字符串进行一系列检查操作，并返回一个布尔值表示是否通过检查。具体的逻辑如下：

   - 首先判断输入字符串的长度是否小于 12 或大于等于 16，如果是则返回 false。
   - 将输入字符串分为两部分，分别为前 10 个字符和后面的部分。
   - 对后面的部分调用 `count` 方法统计数字字符 '1' 的个数，并将结果赋值给变量 `v2`。
   - 对 `v2` 调用 `func` 方法进行一系列计算操作，并将结果赋值给变量 `v3`。
   - 如果 `v3` 不等于 `v2`，则返回 false。
   - 对前 10 个字符调用 `checkStr1` 方法进行一系列检查操作，如果不通过则返回 false。
   - 如果通过了所有检查，则返回 true。

请注意，这段代码是一个部分代码片段，无法独立运行。它可能是从一个更大的代码库中提取出来的一部分。因此，在分析代码时可能会缺少一些上下文信息。

如果您有关于特定部分的问题，或需要更多上下文信息，请提供更多详细信息，我将尽力为您提供帮助。



```shell
dalvikvm -cp /storage/emulated/0/Download/Checkbox.dex CheckBox
```





### Task 2 Encoder



### Task 3 
