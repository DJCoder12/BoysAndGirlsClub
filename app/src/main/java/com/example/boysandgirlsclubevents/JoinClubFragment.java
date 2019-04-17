package com.example.boysandgirlsclubevents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.boysandgirlsclubevents.Util.WebUtils;

public class JoinClubFragment extends Fragment
{
    public static final String TAG = "JoinClubFragment";
    private static final String joinURL = "http://bgclanc.org/join/";
    private WebView mWebView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_join_club, container, false);
        initWebView(view);
        return view;
    }

    private void initWebView(View view)
    {
        mWebView = view.findViewById(R.id.wv_joinPage_joinClub);

        //Turn on javascript
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }
        });

        mWebView.loadUrl(joinURL);
    }
}
