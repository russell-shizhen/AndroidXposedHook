package com.arophix.xposedmodule;


import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Created by shizhen on 06/10/18.
 */

public class XposedModuleExample implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        
        XposedBridge.log("Arophix XposedModule handleLoadPackage: " + lpparam.packageName);
        
        // The `applicationId` configured inside `app/build.gradle`
        if (!lpparam.packageName.equals("com.arophix.xposedhooking")) {
            XposedBridge.log("1 findAndHookMethod for " + lpparam.packageName);
            return;
        }
    
        XposedBridge.log("2 findAndHookMethod for " + lpparam.packageName);
    
        findAndHookMethod("com.arophix.xposedhooking.DummyPerson", lpparam.classLoader, "getFullName", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                // this will be called before the clock was updated by the original method
                XposedBridge.log("Before ===>>> DummyPerson$getFullName is hooked: " + param.getClass().getSimpleName());
            }
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("After ===>>>  DummyPerson$getFullName is hooked: " + param.getClass().getSimpleName());
            }
        });
    
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
        
//        findAndHookMethod("android.widget.TextView", lpparam.classLoader, "setText", "java.lang.String", new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
//                // this will be called before the clock was updated by the original method
//                XposedBridge.log("Before ===>>> android.widget.TextView$setText is hooked: " + param.getClass().getSimpleName());
//                XposedBridge.log("Before ===>>> param.args[0]:  " + param.args[0]);
//                param.args[0] = "I am from Xposedmodule :) ";
//            }
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                XposedBridge.log("After ===>>>  android.widget.TextView$setText is hooked: " + param.getClass().getSimpleName());
//                XposedBridge.log("After ===>>>  param.args[0]:  " + param.args[0]);
//            }
//        });
        
    }
    
}
