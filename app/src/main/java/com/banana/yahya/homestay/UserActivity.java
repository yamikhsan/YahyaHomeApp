package com.banana.yahya.homestay;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class UserActivity extends AppCompatActivity {

    List<User> dataList;

    EditText etUser, etPass;
    ViewModelCenter modelCenter;

    String strType;
    int iHarga, iGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        etUser = (EditText) findViewById(R.id.et_user);
        etPass = (EditText) findViewById(R.id.et_pass);

        modelCenter = ViewModelProviders.of(this).get(ViewModelCenter.class);

        modelCenter.readAll().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> barangs) {

                dataList = barangs;

            }
        });

        strType = getIntent().getStringExtra("TYPE");
        iHarga = getIntent().getIntExtra("HARGA", 0);
        iGambar = getIntent().getIntExtra("GAMBAR", 0);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void handleUser(View view) {

        String user = etUser.getText().toString();
        String pass = etPass.getText().toString();

        if(user.matches("")||pass.matches("")){
            Toast.makeText(this, "User atau Password belum diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (view.getId()){
            case R.id.bt_login:

                for (int i=0; i<dataList.size(); i++){
                    if(user.matches(dataList.get(i).getUser()) && pass.matches(dataList.get(i).getPass())){
                        Intent intent = new Intent(this, ResultActivity.class);
                        intent.putExtra("TYPE", strType);
                        intent.putExtra("HARGA", iHarga);
                        intent.putExtra("GAMBAR", iGambar);
                        startActivity(intent);
                        finish();
                        return;
                    }
                }

                Toast.makeText(this, "Daftar Terlebih dahulu", Toast.LENGTH_SHORT).show();

                break;
            case R.id.bt_regis:
                modelCenter.insert(new User(user, pass));
                etUser.setText("");
                etPass.setText("");
                break;
        }

    }
}
