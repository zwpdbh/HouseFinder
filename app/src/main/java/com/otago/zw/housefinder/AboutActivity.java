package com.otago.zw.housefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(Html.fromHtml("<a href=http://www.otago.ac.nz> About Otago"));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setLinksClickable(true);


        RelativeLayout layout = (RelativeLayout) findViewById(R.id.about_content);
        layout.addView(textView);
    }
}
