package com.bikash.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Facebook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_facebook);

        getSupportActionBar().setTitle("Facebook");

        //Webview
        WebView webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().getJavaScriptEnabled();
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.facebook.com/groups/1045359685480601/files/");
    }
}
