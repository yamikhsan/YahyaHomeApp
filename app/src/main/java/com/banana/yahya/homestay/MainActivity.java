package com.banana.yahya.homestay;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] type = {"Mawar", "Melati", "Cempaka"};
    int[] harga = {1500000, 1200000, 1000000};
    TypedArray gambar;

    int mawar, melati, cempaka;

    private SharedPreferences mPreferences;

    TextView statusMawar, statusMelati, statusCempaka;

    private String sharedPrefFile = "com.banana.yahya.homestay.hellosharedprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gambar = getResources().obtainTypedArray(R.array.gambar);

        statusMawar = (TextView) findViewById(R.id.status_mawar);
        statusMelati = (TextView) findViewById(R.id.status_melati);
        statusCempaka = (TextView) findViewById(R.id.status_cempaka);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);



    }

    @Override
    protected void onStart() {
        super.onStart();
        cekStatusDisplay();
    }

    private void cekStatusDisplay(){
        mawar = mPreferences.getInt(type[0], 0);
        melati = mPreferences.getInt(type[1], 0);
        cempaka = mPreferences.getInt(type[2], 0);

        cekStatus(statusMawar, mawar);
        cekStatus(statusMelati, melati);
        cekStatus(statusCempaka, cempaka);
    }

    private void cekStatus(TextView tv, int i){
        if(i == 2){
            tv.setText("Full");
        }else {
            tv.setText("Tersedia");
        }
    }

    @SuppressLint("ResourceType")
    public void handleClick(View view) {

        switch (view.getId()){
            case R.id.cv_mawar:

                Intent intentMawar = new Intent(this, UserActivity.class);
                intentMawar.putExtra("TYPE", type[0]);
                intentMawar.putExtra("HARGA", harga[0]);
                intentMawar.putExtra("GAMBAR", gambar.getResourceId(0, 0));
                if(mawar != 2){
                    startActivity(intentMawar);
                }else {
                    Toast.makeText(this, "Kamar Sudah Full", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.cv_melati:

                Intent intentMelati = new Intent(this, UserActivity.class);
                intentMelati.putExtra("TYPE", type[1]);
                intentMelati.putExtra("HARGA", harga[1]);
                intentMelati.putExtra("GAMBAR", gambar.getResourceId(1, 0));
                if(melati != 2){
                    startActivity(intentMelati);
                }else {
                    Toast.makeText(this, "Kamar Sudah Full", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.cv_cempaka:

                Intent intentCempaka= new Intent(this, UserActivity.class);
                intentCempaka.putExtra("TYPE", type[2]);
                intentCempaka.putExtra("HARGA", harga[2]);
                intentCempaka.putExtra("GAMBAR", gambar.getResourceId(2, 0));
                if(cempaka != 2){
                    startActivity(intentCempaka);
                }else {
                    Toast.makeText(this, "Kamar Sudah Full", Toast.LENGTH_SHORT).show();
                }

                break;
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Reset Semua Data ");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                        preferencesEditor.clear();
                        preferencesEditor.apply();
                        cekStatusDisplay();

                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog build = builder.create();
                build.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
