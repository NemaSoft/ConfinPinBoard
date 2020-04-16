# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see:
# http://developer.android.com/guide/developing/tools/proguard.html
# https://developer.android.com/studio/build/shrink-code

# Prints a full report of all the rules that R8 applies when building your project
-printconfiguration proguard/full-r8-config.txt

# To remove all logs
# Source: http://stackoverflow.com/a/13327603/2969811
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
    public static int wtf(...);
}

# To enable Crashlytics deobfuscated stack traces
# Source: https://firebase.google.com/docs/crashlytics/get-deobfuscated-reports?platform=android
-keepattributes *Annotation*                       ## Keep Crashlytics annotations
-keepattributes SourceFile,LineNumberTable         ## Keep file names/line numbers
-keep public class * extends java.lang.Exception   ## Keep custom exceptions (opt)

# To keep DataSource "data class" models (needed to work with Firebase Firestore)
-keepclassmembers class com.confinapptilus.confinpinboard.datasources.models.** { *; }
