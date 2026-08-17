package com.emma.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputType;
import android.view.Gravity;
import android.widget.*;
import org.json.JSONObject;

public class MainActivity extends Activity {
    private final int gold=Color.rgb(201,162,39), black=Color.rgb(11,11,13), white=Color.WHITE, gray=Color.rgb(107,107,115);
    @Override public void onCreate(Bundle b){super.onCreate(b); showLogin();}
    private TextView text(String s,float z,boolean bold){TextView v=new TextView(this);v.setText(s);v.setTextSize(z);v.setTextColor(white);v.setTypeface(Typeface.DEFAULT,bold?Typeface.BOLD:Typeface.NORMAL);v.setPadding(0,8,0,8);return v;}
    private LinearLayout page(){LinearLayout r=new LinearLayout(this);r.setOrientation(LinearLayout.VERTICAL);r.setPadding(32,36,32,36);r.setBackgroundColor(black);return r;}
    private Button action(String s){Button b=new Button(this);b.setText(s);b.setTextColor(black);b.setTextSize(15);b.setAllCaps(false);b.setBackgroundColor(gold);LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,58);p.setMargins(0,8,0,8);b.setLayoutParams(p);return b;}
    private void showLogin(){
        LinearLayout r=page();r.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView brand=text("EMMA",42,true);brand.setTextColor(gold);brand.setGravity(Gravity.CENTER);r.addView(brand,new LinearLayout.LayoutParams(-1,-2));
        TextView m=text("Educación · Movimiento · Musculación · Aptitud física",14,false);m.setGravity(Gravity.CENTER);m.setTextColor(Color.LTGRAY);r.addView(m);
        EditText email=new EditText(this);email.setHint("Correo electrónico");email.setHintTextColor(Color.GRAY);email.setTextColor(white);email.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);r.addView(email,new LinearLayout.LayoutParams(-1,60));
        EditText pass=new EditText(this);pass.setHint("Contraseña");pass.setHintTextColor(Color.GRAY);pass.setTextColor(white);pass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);r.addView(pass,new LinearLayout.LayoutParams(-1,60));
        Button login=action("Iniciar sesión");r.addView(login);TextView note=text("Plataforma de salud física, fitness, musculación y rendimiento.",13,false);note.setTextColor(gray);r.addView(note);
        login.setOnClickListener(v->{String e=email.getText().toString().trim(),p=pass.getText().toString();if(e.isEmpty()||p.isEmpty()){Toast.makeText(this,"Ingresa correo y contraseña.",Toast.LENGTH_SHORT).show();return;}login.setEnabled(false);login.setText("Verificando...");SupabaseClient.signIn(e,p,new SupabaseClient.Callback(){public void onSuccess(JSONObject j){runOnUiThread(()->showDashboard(j.optString("access_token","")));}public void onError(String msg){runOnUiThread(()->{login.setEnabled(true);login.setText("Iniciar sesión");Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();});}});});
        ScrollView s=new ScrollView(this);s.setBackgroundColor(black);s.addView(r);setContentView(s);
    }
    private void showDashboard(String token){
        LinearLayout r=page();TextView brand=text("EMMA",34,true);brand.setTextColor(gold);r.addView(brand);TextView sub=text("Educación · Movimiento · Musculación · Aptitud física",14,false);sub.setTextColor(Color.LTGRAY);r.addView(sub);r.addView(text("Panel principal",24,true));
        addModule(r,"Valoración física","Anamnesis, medidas corporales, condición física y seguimiento.");
        addModule(r,"Nutrición y dietética","Requerimientos energéticos, composición y orientación nutricional.");
        addModule(r,"Entrenamiento muscular","Ejercicios, series, repeticiones, carga, RIR, RPE y descanso.");
        addModule(r,"Planificación","Programas, macrociclos, mesociclos, microciclos y sesiones.");
        addModule(r,"Biomecánica y movimiento","Patrones de movimiento y análisis del ejercicio.");
        addModule(r,"Rendimiento","Métricas, progresión, adherencia y resultados.");
        addModule(r,"Salud y seguimiento","Check-ins, energía, sueño, estrés y recuperación.");
        Button out=action("Cerrar sesión");r.addView(out);out.setOnClickListener(v->showLogin());ScrollView s=new ScrollView(this);s.setBackgroundColor(black);s.addView(r);setContentView(s);
    }
    private void addModule(LinearLayout r,String t,String d){TextView a=text(t,18,true);a.setTextColor(gold);r.addView(a);TextView b=text(d,14,false);b.setTextColor(Color.LTGRAY);r.addView(b);}
}
