package com.example.boysandgirlsclubevents.Util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class WebUtils
{
    public static void openWebURL(String URL, Context context)
    {
        Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        context.startActivity(browse);
    }
}
