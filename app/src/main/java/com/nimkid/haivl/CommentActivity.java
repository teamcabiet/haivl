package com.nimkid.haivl;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class CommentActivity extends AppCompatActivity {
        WebView mWebViewComments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        mWebViewComments = (WebView) findViewById(R.id.wvComment);
        MyWebChromeClient myWebChromeClient = new MyWebChromeClient();
        mWebViewComments.setWebChromeClient(myWebChromeClient);
        mWebViewComments.getSettings().getJavaScriptEnabled();
        mWebViewComments.getSettings().setJavaScriptEnabled(true);
        mWebViewComments.getSettings().setAppCacheEnabled(true);
        mWebViewComments.getSettings().setDomStorageEnabled(true);
        mWebViewComments.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebViewComments.getSettings().setSupportMultipleWindows(true);
        CookieManager.getInstance().setAcceptCookie(true);

        Bundle extras = getIntent().getExtras();
        String postID = extras.getString("postID");
        String url = "http://nguyenhuuthang.com/"+postID;

        // facebook comment widget including the article url
        String html = "<!doctype html> <html lang=\"en\"> <head></head> <body> " +
                "<div id=\"fb-root\"></div> <script>(function(d, s, id) { var js, fjs = d.getElementsByTagName(s)[0]; if (d.getElementById(id)) return; js = d.createElement(s); js.id = id; js.src = \"//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.6\"; fjs.parentNode.insertBefore(js, fjs); }(document, 'script', 'facebook-jssdk'));</script> " +
                "<div class=\"fb-comments\" data-href=\"" + url + "\" " +
                "data-numposts=\"" + 5 + "\" data-order-by=\"reverse_time\">" +
                "</div> </body> </html>";

        mWebViewComments.loadDataWithBaseURL("http://nguyenhuuthang.com/", html, "text/html", "UTF-8", null);
        mWebViewComments.setMinimumHeight(200);
    }



    public class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult jsResult) {
            // you can create your own dialog here or just return true for no pop up.
            return true;
        }
    }


}


