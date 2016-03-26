package com.neiljaywarner.androidjoketellinglibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeDialogActivity extends AppCompatActivity {


    private static final String EXTRA_JOKE = "extra_joke";

    public static Intent newIntent(Context context, String joke) {
        Intent intent = new Intent(context, JokeDialogActivity.class);
        intent.putExtra(EXTRA_JOKE, joke);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_dialog);
        TextView textview = (TextView) findViewById(R.id.textViewJoke);

        //Since it's a library activity  with a newIntent it shouldn't get called without the extra
        textview.setText(getIntent().getExtras().getString(EXTRA_JOKE));
    }
}
