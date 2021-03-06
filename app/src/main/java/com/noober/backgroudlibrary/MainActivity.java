package com.noober.backgroudlibrary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.noober.background.BackgroundFactory;
import com.noober.background.PPInject;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    PPInject pp = new PPInject(new BackgroundFactory());

    @Override
    public Object getSystemService(@NonNull String name) {
        if (LAYOUT_INFLATER_SERVICE.equals(name)) {
            return pp.getInflater(this);
        } else {
            return super.getSystemService(name);
        }
    }
}
