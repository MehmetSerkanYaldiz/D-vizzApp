package com.example.dvizzapp;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class Home extends AppCompatActivity {
    private static final String url = "https://uzmanpara.milliyet.com.tr/doviz-kurlari/";
    private static final String urll = "https://uzmanpara.milliyet.com.tr/altin-fiyatlari/";
    private static final String urlll = "https://www.doviz.com/";


    Document document, documentt, documenttt;
    String DOLAR1, EURO1, STERLİN1, PETROL1, FAİZ1, ALTIN1, KANADA1, İSVİÇRE1, RİYAL1, JAPON1, AVUSTRALYA1, NORVEÇ1, DANİMARKA1, İSVEÇ1;
    String DOLAR, EURO, STERLİN, PETROL, FAİZ, ALTIN, BİST, KANADA, İSVİÇRE, RİYAL, JAPON, AVUSTRALYA, NORVEÇ, DANİMARKA, İSVEÇ;
    ImageView cevirici;


    String[] paraBirimleri = {"DOLAR", "EURO", "STERLİN", "ALTIN", "KANADA DOLARI", "İSVİÇRE DOLARI", "SUUDİ RİYALİ", "100 JAPON YENİ", "AVUSTRALYA DOLARI",
            "NORVEÇ KRONU", "DANİMARKA KRONU", "İSVEÇ KRONU"};


    ProgressDialog pDialog;
    ArrayAdapter arrayAdapter;
    static final int progresBarTipi = 0;
    TextView dolar, euro, sterlin, petrol, faiz, altın, bist, kanada, isviçre, riyal, japon, avustralya, norveç, danimarka, isveç;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cevirici = findViewById(R.id.cevirici);
        InternetKontrol();
        dönüstürücü();


    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progresBarTipi:
                pDialog = new ProgressDialog(Home.this);
                pDialog.setMessage("Kurlar Güncelleniyor..");

                pDialog.setCancelable(false);
                pDialog.show();
                return pDialog;
            default:
                return null;

        }

    }


    class getirHtml extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (!isveç.getText().equals("")) {
                dismissDialog(progresBarTipi);
                Log.i("denemee", NORVEÇ1);
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            tanımla();
            HtmlParsing();
            setEtme();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progresBarTipi);
        }
    }

    public void tanımla() {

        dolar = findViewById(R.id.dolar);
        euro = findViewById(R.id.euro);
        sterlin = findViewById(R.id.sterlin);
        petrol = findViewById(R.id.petrol);
        faiz = findViewById(R.id.faiz);
        bist = findViewById(R.id.bist);
        altın = findViewById(R.id.altın);
        kanada = findViewById(R.id.kanada);
        isviçre = findViewById(R.id.isviçre);
        riyal = findViewById(R.id.riyal);
        japon = findViewById(R.id.japon);
        avustralya = findViewById(R.id.avustralya);
        norveç = findViewById(R.id.norveç);
        danimarka = findViewById(R.id.danimarka);
        isveç = findViewById(R.id.isveç);


    }

    public void HtmlParsing() {
        try {
            document = Jsoup.connect(url).get();
            documentt = Jsoup.connect(urll).get();
            documenttt = Jsoup.connect(urlll).get();
            DOLAR1 = document.select("td").get(2).text();
            DOLAR = DOLAR1.replaceAll(",", ".");
            EURO1 = document.select("td").get(8).text();
            EURO = EURO1.replaceAll(",", ".");
            STERLİN1 = document.select("td").get(14).text();
            STERLİN = STERLİN1.replaceAll(",", ".");
            BİST = documenttt.select("span.value").get(4).text();
            FAİZ1 = documenttt.select("span.value").get(5).text();
            FAİZ = FAİZ1.replaceAll(",", ".");
            PETROL1 = documenttt.select("span.value").get(6).text();
            PETROL = PETROL1.replaceAll(",", ".");
            ALTIN1 = documentt.select("td").get(2).text();
            ALTIN = ALTIN1.replaceAll(",", ".");
            KANADA1 = document.select("td").get(20).text();
            KANADA = KANADA1.replaceAll(",", ".");
            İSVİÇRE1 = document.select("td").get(26).text();
            İSVİÇRE = İSVİÇRE1.replaceAll(",", ".");
            RİYAL1 = document.select("td").get(32).text();
            RİYAL = RİYAL1.replaceAll(",", ".");
            JAPON1 = document.select("td").get(38).text();
            JAPON = JAPON1.replaceAll(",", ".");
            AVUSTRALYA1 = document.select("td").get(44).text();
            AVUSTRALYA = AVUSTRALYA1.replaceAll(",", ".");
            NORVEÇ1 = document.select("td").get(50).text();
            NORVEÇ = NORVEÇ1.replaceAll(",", ".");
            DANİMARKA1 = document.select("td").get(56).text();
            DANİMARKA = DANİMARKA1.replaceAll(",", ".");
            İSVEÇ1 = document.select("td").get(62).text();
            İSVEÇ = İSVEÇ1.replaceAll(",", ".");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setEtme() {
        dolar.setText(DOLAR);
        euro.setText(EURO);
        sterlin.setText(STERLİN);
        petrol.setText(PETROL);
        faiz.setText(FAİZ);
        altın.setText(ALTIN);
        bist.setText(BİST);
        kanada.setText(KANADA);
        isviçre.setText(İSVİÇRE);
        riyal.setText(RİYAL);
        japon.setText(JAPON);
        avustralya.setText(AVUSTRALYA);
        norveç.setText(NORVEÇ);
        danimarka.setText(DANİMARKA);
        isveç.setText(İSVEÇ);
    }

    public boolean InternetKontrol() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null
                && manager.getActiveNetworkInfo().isAvailable()
                && manager.getActiveNetworkInfo().isConnected()) {
            new getirHtml().execute();
            return true;
        } else {
            ProgressDialog progressDialog = new ProgressDialog(Home.this);
            progressDialog.setMessage("İnternet Bağlantınızı Kontrol Edin ve Uygulamayı Yeniden Başlatın");
            progressDialog.setCancelable(false);
            progressDialog.show();
            return false;
        }
    }

    public String foksiyon(String a, String b) {

        Double e = Double.parseDouble(a);
        try {
            Double s = Double.parseDouble(b);
            int c = (int) (e * s);
            String x = String.valueOf(c);
            return x;
        } catch (NumberFormatException ex) {
            return "0";
        }


    }


    public void dialogAç() {

        LayoutInflater Inflater = getLayoutInflater();
        View view = Inflater.inflate(R.layout.alert, null);
        final Spinner spinner = view.findViewById(R.id.spinner);
        final TextView textView = view.findViewById(R.id.textView);
        final EditText editText = view.findViewById(R.id.editText);
        final Button button = view.findViewById(R.id.button);
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, paraBirimleri);
        spinner.setAdapter(arrayAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (spinner.getSelectedItemPosition()) {
                    case 0:
                        textView.setText(foksiyon(DOLAR, editText.getText().toString()));
                        break;
                    case 1:
                        textView.setText(foksiyon(EURO, editText.getText().toString()));

                        break;
                    case 2:
                        textView.setText(foksiyon(STERLİN, editText.getText().toString()));
                        break;

                    case 3:
                        textView.setText(foksiyon(ALTIN, editText.getText().toString()));
                        break;
                    case 4:
                        textView.setText(foksiyon(KANADA, editText.getText().toString()));
                        break;
                    case 5:
                        textView.setText(foksiyon(İSVİÇRE, editText.getText().toString()));
                        break;
                    case 6:
                        textView.setText(foksiyon(RİYAL, editText.getText().toString()));
                        break;
                    case 7:
                        textView.setText(foksiyon(JAPON, editText.getText().toString()));
                        break;
                    case 8:
                        textView.setText(foksiyon(AVUSTRALYA, editText.getText().toString()));
                        break;
                    case 9:
                        textView.setText(foksiyon(NORVEÇ, editText.getText().toString()));
                        break;
                    case 10:
                        textView.setText(foksiyon(DANİMARKA, editText.getText().toString()));
                        break;
                    case 11:
                        textView.setText(foksiyon(İSVEÇ, editText.getText().toString()));
                }


            }
        });

        final AlertDialog.Builder alert = new AlertDialog.Builder(Home.this);
        alert.setView(view);
        alert.setCancelable(true);
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void dönüstürücü() {
        cevirici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogAç();

            }
        });
    }


}
