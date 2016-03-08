package com.example.taras.homeworklesson7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().setTitle(R.string.actionbar_title_SA);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        TextView vTitle,vContent;
        vTitle = (TextView) findViewById(R.id.title_as);
        vContent = (TextView) findViewById(R.id.content_as);
        vTitle.setText(intent.getStringExtra(Values.titleKey));
        vContent.setText(intent.getStringExtra(Values.contentKey));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
