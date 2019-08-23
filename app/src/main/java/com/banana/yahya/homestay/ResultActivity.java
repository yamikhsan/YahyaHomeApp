package com.banana.yahya.homestay;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ResultActivity extends AppCompatActivity {

    ImageView gambar;
    TextView type, harga, jumlah, total;
    int iHarga, iGambar, jmh;
    String strType;

    private SharedPreferences mPreferences;

    private String sharedPrefFile = "com.banana.yahya.homestay.hellosharedprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        gambar = (ImageView) findViewById(R.id.iv_gambar_result);
        type = (TextView) findViewById(R.id.tv_type_result);
        harga = (TextView) findViewById(R.id.tv_harga_result);
        jumlah = (TextView) findViewById(R.id.tv_jumlah_result);
        total = (TextView) findViewById(R.id.tv_total_result);

        strType = getIntent().getStringExtra("TYPE");
        iHarga = getIntent().getIntExtra("HARGA", 0);
        iGambar = getIntent().getIntExtra("GAMBAR", 0);

        gambar.setImageResource(iGambar);
        type.setText("Type = "+strType);
        harga.setText("Harga = " + "Rp " + toMoney(iHarga));

        jmh = 0;

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
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

    public String toMoney(int i){
        NumberFormat formatter = new DecimalFormat("#,###");
        return formatter.format(i);
    }

    public void handelPesan(View view) {

        if(jmh < 2 - mPreferences.getInt(strType, 0)){
            jmh++;
            jumlah.setText("Jumlah Kamar = " + String.valueOf(jmh));
            total.setText("Total Harga = " + "Rp " +toMoney(jmh * iHarga));
        }else {
            Toast.makeText(this, "Kamar Sudah Tidak Tersedia", Toast.LENGTH_SHORT).show();
        }

    }

    public void handleBatal(View view) {

        jmh = 0;
        jumlah.setText("Jumlah Kamar = " + String.valueOf(jmh));
        total.setText("Total Harga = " + "Rp " +toMoney(jmh * iHarga));

    }

    public void checkIn(View view) {
        if(jmh == 0){
            Toast.makeText(this, "Pesan Dulu Jumlah Kamarnya", Toast.LENGTH_SHORT).show();
        }else {
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putInt(strType, mPreferences.getInt(strType, 0) + jmh);
            preferencesEditor.apply();
            Toast.makeText(this, "Terima Kasih Sudah Memesan Kamar di Sini", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
