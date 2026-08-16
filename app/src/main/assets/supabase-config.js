// TOP Fitness — Supabase client configuration.
// Publishable key only. RLS remains the security boundary.
window.TOP_FITNESS_SUPABASE = Object.freeze({
  url: 'https://oxscuoilizohrxiuhwpz.supabase.co',
  publishableKey: 'sb_publishable_JVRIBD67vEWz1ElAY4LpJQ_nowiK7oJ'
});

window.TOP_FITNESS_BACKEND = {
  async healthCheck() {
    try {
      const r = await fetch(window.TOP_FITNESS_SUPABASE.url + '/rest/v1/', {
        headers: { apikey: window.TOP_FITNESS_SUPABASE.publishableKey }
      });
      return { ok: r.ok, status: r.status };
    } catch (e) {
      return { ok: false, status: 0, error: String(e) };
    }
  }
};
