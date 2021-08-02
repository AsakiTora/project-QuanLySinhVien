package fpoly.andoid.ass_canhpdph12975;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        webView = findViewById(R.id.webview);
        Intent intent = getIntent();
        String link = intent.getStringExtra("linkTT");
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
    }
}
