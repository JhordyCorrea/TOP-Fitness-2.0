# EMMA

**Educación · Movimiento · Musculación · Aptitud física**

Clean Android/Gradle reconstruction of the former TOP Fitness project.

## Backend
Supabase project: `oxscuoilizohrxiuhwpz` in `sa-east-1`.

## Build
JDK 17 + Android SDK 34 + Gradle 8.2.2.

`gradle :app:assembleDebug --no-daemon`

APK: `app/build/outputs/apk/debug/app-debug.apk`

The Supabase database remains the backend and its existing exercise, training, measurement, goal, subscription, payment and security structures are preserved.
