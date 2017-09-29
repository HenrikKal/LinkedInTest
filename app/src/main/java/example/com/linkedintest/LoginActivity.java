package example.com.linkedintest;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Henrik on 2017-09-29.
 */

public class LoginActivity extends Activity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.login_webview);


        WebView webView = (WebView) findViewById(R.id.login_webview);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.google.se/");


    }
}
