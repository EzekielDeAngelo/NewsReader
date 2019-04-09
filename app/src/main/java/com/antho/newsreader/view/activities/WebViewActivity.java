package com.antho.newsreader.view.activities;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.antho.newsreader.R;
import com.antho.newsreader.base.BaseActivity;

import androidx.annotation.Nullable;
import butterknife.BindView;
/** Activity to display news article content as a web page **/
public class WebViewActivity extends BaseActivity
{
    @BindView(R.id.webview) WebView webView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    // Load web view with article url
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initWebView();
        if(getIntent().hasExtra("url"))
        {
            String section = getIntent().getStringExtra("section");
            String url = getIntent().getStringExtra("url");
            setTitle(section);
            webView.loadUrl(url);
        }
    }
    // Initialize web view
    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView()
    {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                super.onPageStarted(view, url, favicon);
                if (progressBar != null && progressBar.getVisibility() == View.GONE)
                {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
                if (progressBar != null && progressBar.getVisibility() == View.VISIBLE)
                {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
    // Return activity layout
    @Override
    protected int layoutRes()
    {
        return R.layout.webview_layout;
    }
}
