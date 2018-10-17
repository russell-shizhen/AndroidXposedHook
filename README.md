# AndroidXposedHook
A simple XposedHook example on Android. 

## 1. Modify Manifest.xml 

``` xml
<?xml version="1.0" encoding="utf-8"?>  
<manifest xmlns:android="http://schemas.android.com/apk/res/android"  
  package="com.arophix.xposedmodule">  
  
 <application  android:icon="@mipmap/ic_launcher"  
  android:label="@string/app_name">  
 <meta-data  android:name="xposedmodule"  
  android:value="true" />  
 <meta-data  android:name="xposeddescription"  
  android:value="Xposed module hooking example" />  
 <meta-data  android:name="xposedminversion"  
  android:value="53" />  
 </application>  
</manifest> 
```

## 2. Add `xposed_init`
Add an **Xposed** initialisation file under `assets` folder, i.e. `~/XposedHooking/xposedmodule/src/main/assets/xposed_init`

Add the full package name for the hooking module, e.g. 
```
com.arophix.xposedmodule.XposedModuleExample
```
## 3. Hooking module class

Let the hooking module class **_implement_** interface `IXposedHookLoadPackage`. 

``` java
public class XposedModuleExample implements IXposedHookLoadPackage { ... }
```

Example for hooking a method with arguments
``` java
findAndHookMethod("com.arophix.xposedhooking.DummyPerson", lpparam.classLoader, "getFullName", "java.lang.String", "java.lang.String", new XC_MethodHook() {  
    @Override  
  protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {  
        // this will be called before the clock was updated by the original method  
  XposedBridge.log("2 Before ===>>> DummyPerson$getFullName is hooked: " + param.getClass().getSimpleName());  
  XposedBridge.log("2 Before ===>>> param.args[0]:  " + param.args[0]);  
  XposedBridge.log("2 Before ===>>> param.args[1]:  " + param.args[1]);  
  param.args[0] = "I am from Xposedmodule :)";  
  }  
    @Override  
  protected void afterHookedMethod(MethodHookParam param) throws Throwable {  
        XposedBridge.log("2 After ===>>>  DummyPerson$getFullName is hooked: " + param.getClass().getSimpleName());  
  XposedBridge.log("2 After ===>>>  param.args[0]:  " + param.args[0]);  
  XposedBridge.log("2 After ===>>>  param.args[1]:  " + param.args[1]);  
  }  
});
```
**Notes**
1. The argument type need to be a full qualified class name, e.g. `java.lang.String`. 
2. `XC_LoadPackage.LoadPackageParam lpparam` is the wrapper of information about the app being loaded.
3. Using `applicationId` as a filter for hooking process, e.g. 
``` java
    // The `applicationId` is configured inside `app/build.gradle`  
    if (!lpparam.packageName.equals("com.arophix.xposedhooking")) {  
        XposedBridge.log("1 findAndHookMethod for " + lpparam.packageName);  
        return;
    }
```

## 4. Install `XposedModuleExample`
Set the **Launch Options** to **Nothing**, see below screenshot:

![XposedModule project settings](https://github.com/russell-shizhen/AndroidXposedHook/blob/master/xposedmodule/xposedmodule-configurations-nothing.png)

## 5. Soft reboot device and activate your Xposed module
When install the XposedModuleExample, device will pop up a notification as below.
![Soft Reboot Device Notification](https://github.com/russell-shizhen/AndroidXposedHook/blob/master/xposedmodule/SoftReboot.png)

And ensure the tick box is checked.
![Activate Xposed module](https://github.com/russell-shizhen/AndroidXposedHook/blob/master/xposedmodule/ActivateXposedModule.png)

## 6. Example project
Have a copy from my github: https://github.com/russell-shizhen/AndroidXposedHook 
