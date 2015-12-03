# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Dev\Android\androidSdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#squareup 的所有开源项目
-dontwarn com.squareup.**
-keep class com.squareup.** { *;}
-dontwarn okio.**

#rxandroid  rxjava
-dontwarn rx.internal.**
-keep class rx.internal.** { *;}

## nineoldandroids-2.4.0.jar
-dontwarn rx.internal.**
-keep  class com.nineoldandroids.** {*;}

## android support
-dontwarn android.support.**
-keep class android.support.** { *; }

#gson
-dontwarn com.google.**
-keep class com.google.**{*;}
# 自己的实体类
-keep class org.sunger.net.entity.** { *; }

