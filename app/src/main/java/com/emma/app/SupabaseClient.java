package com.emma.app;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public final class SupabaseClient {
    public static final String BASE_URL = "https://oxscuoilizohrxiuhwpz.supabase.co";
    public static final String PUBLISHABLE_KEY = "sb_publishable_JVRIBD67vEWz1ElAY4LpJQ_nowiK7oJ";
    private SupabaseClient() {}
    public interface Callback { void onSuccess(JSONObject json); void onError(String message); }
    public static void signIn(String email, String password, Callback callback) {
        new Thread(() -> {
            try {
                JSONObject body = new JSONObject();
                body.put("email", email);
                body.put("password", password);
                callback.onSuccess(request("POST", "/auth/v1/token?grant_type=password", body));
            } catch (Exception e) {
                callback.onError(e.getMessage() == null ? "No fue posible iniciar sesión." : e.getMessage());
            }
        }).start();
    }
    private static JSONObject request(String method, String path, JSONObject body) throws Exception {
        HttpURLConnection c = (HttpURLConnection) new URL(BASE_URL + path).openConnection();
        c.setRequestMethod(method); c.setConnectTimeout(15000); c.setReadTimeout(20000);
        c.setRequestProperty("apikey", PUBLISHABLE_KEY); c.setRequestProperty("Content-Type", "application/json"); c.setRequestProperty("Accept", "application/json");
        if (body != null) { c.setDoOutput(true); try (OutputStream o = c.getOutputStream()) { o.write(body.toString().getBytes(StandardCharsets.UTF_8)); } }
        int status = c.getResponseCode();
        InputStream s = status >= 200 && status < 300 ? c.getInputStream() : c.getErrorStream();
        String response = read(s);
        if (status < 200 || status >= 300) {
            try { JSONObject e = new JSONObject(response); throw new Exception(e.optString("error_description", e.optString("msg", e.optString("message", "Error de autenticación.")))); }
            catch (org.json.JSONException ignored) { throw new Exception("Error HTTP " + status); }
        }
        return new JSONObject(response);
    }
    private static String read(InputStream stream) throws Exception {
        if (stream == null) return ""; StringBuilder r = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) { String line; while ((line = br.readLine()) != null) r.append(line); }
        return r.toString();
    }
}
