package com.example.taras.homeworklesson7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MenuItem iItem1, iItem2, iItem3;
    RelativeLayout v1, v2, v3;
    ImageButton vAction1, vAction2, vAction3;
    TextView vTitle1, vTitle2, vTitle3, vContent1, vContent2, vContent3;
    int mCurrentFocusId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.actionbar_title);
        initViews();

        if (mCurrentFocusId == 0) {
            mCurrentFocusId = 1;
        }

        vAction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v, vTitle1.getText().toString(), vContent1.getText().toString());
            }
        });

        vAction2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v, vTitle2.getText().toString(), vContent2.getText().toString());
            }
        });

        vAction3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v, vTitle3.getText().toString(), vContent3.getText().toString());
            }
        });

        updateFocus();
    }

    private void initViews() {
        vContent1 = (TextView) findViewById(R.id.text1);
        vContent2 = (TextView) findViewById(R.id.text2);
        vContent3 = (TextView) findViewById(R.id.text3);
        vTitle1 = (TextView) findViewById(R.id.title_1);
        vTitle2 = (TextView) findViewById(R.id.title_2);
        vTitle3 = (TextView) findViewById(R.id.title_3);
        v1 = (RelativeLayout) findViewById(R.id.rl_1);
        v2 = (RelativeLayout) findViewById(R.id.rl_2);
        v3 = (RelativeLayout) findViewById(R.id.rl_3);
        vAction1 = (ImageButton) findViewById(R.id.btn_1);
        vAction2 = (ImageButton) findViewById(R.id.btn_2);
        vAction3 = (ImageButton) findViewById(R.id.btn_3);
    }

    private void updateMenu() {
        iItem1.setIcon(mCurrentFocusId == 1 ? R.drawable.ic_checked : R.drawable.ic_unchecked);
        iItem2.setIcon(mCurrentFocusId == 2 ? R.drawable.ic_checked : R.drawable.ic_unchecked);
        iItem3.setIcon(mCurrentFocusId == 3 ? R.drawable.ic_checked : R.drawable.ic_unchecked);
        invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        iItem1 = menu.findItem(R.id.item1);
        iItem2 = menu.findItem(R.id.item2);
        iItem3 = menu.findItem(R.id.item3);
        updateMenu();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        updateMenu();

        switch (item.getItemId()) {
            case R.id.item1 : {
                mCurrentFocusId = 1;
                break;
            }

            case R.id.item2 : {
                mCurrentFocusId = 2;
                break;
            }

            case R.id.item3 : {
                mCurrentFocusId = 3;
                break;
            }
        }

        updateFocus();
        return true;
    }

    private void updateFocus() {
        if (getCurrentFocus() != null) {
            getCurrentFocus().clearFocus();
        }

        if (mCurrentFocusId == 1) {
            v1.requestFocus();
        } else if (mCurrentFocusId == 2) {
            v2.requestFocus();
        } else {
            v3.requestFocus();
        }
    }

    void showPopupMenu(View focus, final String title, final String content) {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, focus);
        popupMenu.inflate(R.menu.menu_popup);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action1) {
                    // Second activity
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra(Values.titleKey, title);
                    intent.putExtra(Values.contentKey, content);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.action2) {
                    // TOAST
                    Toast.makeText(MainActivity.this, title + "\n" + content, Toast.LENGTH_LONG).show();
                    return true;
                } else if (item.getItemId() == R.id.action3) {
                    // close
                    finish();
                    return true;
                }

                return false;
            }
        });

        popupMenu.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("focus", mCurrentFocusId);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentFocusId = savedInstanceState.getInt("focus");
    }
}
