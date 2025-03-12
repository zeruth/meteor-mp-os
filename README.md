### Meteor - Multiplatform (Desktop/Android)  

# Info
The project was entirely re-written using a shared code concept using Compose with no reflection / injection  
This unified code concept is still very new so a lot of things have regressed (input on android)  
most things have been moved to common already but there is always room for improvement!
  
# Requirements  
Android 11 R device  
JDK 21 (replace jdk at C:\Program Files\Android\Android Studio\jbr with this jdk if you want to package desktop)  
Latest Android Studio Canary / NDK  
https://www.techpowerup.com/download/visual-c-redistributable-runtime-package-all-in-one/  
(These all MUST BE INSTALLED)  
  
# Building  
you can build either platform with gradlew using android:build or desktop:build  
you can build/run either platform via Android Studio or IntelliJ (with the Android plugin) with the included run configurations  
or you can use the output apk at android/build/outputs/apk  
or you can create a distributable using the included run configuration if you replace the jbr runtime with an oracle 21 runtime  

# Modules  
android-awt: java.swing native port allowing java.awt access for android  
android: android specific client code / main  
api-rs: deob level injected interfaces  
api: client level injected interfaces  
buildSrc: injector gradle plugin  
common: platform agnostic client code  
desktop: desktop specific client code / main  
eventbus: KEvent bases eventbus  
mixins: client injected code  
rs2-mapview: modified mapview applet  
rs2: injection target  
