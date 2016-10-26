package com.lakeel.altla.sample.selectimage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public final class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ACTION_OPEN_DOCUMENT = 0;

    private static final int REQUEST_CODE_ACTION_PICK = 1;

    @BindView(R.id.text_view_action_open_document)
    TextView mTextViewActionOpenDocument;

    @BindView(R.id.text_view_action_pick)
    TextView mTextViewActionPick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_action_open_document)
    void onClickButtonActionOpenDocument() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");

        startActivityForResult(intent, REQUEST_CODE_ACTION_OPEN_DOCUMENT);
    }

    @OnClick(R.id.button_action_pick)
    void onClickButtonActionPick() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");

        startActivityForResult(intent, REQUEST_CODE_ACTION_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE_ACTION_OPEN_DOCUMENT == requestCode) {
            if (RESULT_OK == resultCode) {
                Uri uri = (data != null) ? data.getData() : null;
                if (uri != null) {
                    mTextViewActionOpenDocument.setText(uri.toString());
                }
            }
        } else if (REQUEST_CODE_ACTION_PICK == requestCode) {
            if (RESULT_OK == resultCode) {
                Uri uri = (data != null) ? data.getData() : null;
                if (uri != null) {
                    mTextViewActionPick.setText(uri.toString());
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
