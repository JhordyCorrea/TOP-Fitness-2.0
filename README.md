# TOP Fitness

Aplicación Android de TOP Fitness.

## Identidad del proyecto

- Namespace: `com.topfitness.app`
- Application ID: `com.topfitness.app`
- Min SDK: 24 (Android 7.0)
- Target SDK: 35
- Compilación: Android Gradle Plugin 8.5.2 / Gradle 8.7

## Backend

La aplicación conserva el backend existente de Supabase. El cliente usa únicamente la publishable key; la seguridad de datos depende de RLS y de las funciones protegidas del proyecto Supabase.

## Estructura

- `app/src/main/java/com/topfitness/app/` — aplicación Android.
- `app/src/main/assets/` — interfaz y recursos web de TOP Fitness.
- `app/src/main/res/` — recursos Android.
- `.github/workflows/android.yml` — compilación automática.

## Regla de mantenimiento

No incorporar archivos, nombres, paquetes ni referencias heredadas de JStudio. El repositorio representa exclusivamente TOP Fitness.
