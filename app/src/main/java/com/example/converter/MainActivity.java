package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import api.Client;
import api.CurrencyService;
import api.WrapperCurrencyObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Math.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    private EditText edt;
    private TextView txt;
    private static Button btn1, btn2;
    private ListView listView;
    private String[] s = {
            "RUB", "USD", "EUR", "GBP", "CNY", "SEK"
    };
    private int[] flags = {
            R.drawable.rus,
            R.drawable.usd,
            R.drawable.eur,
            R.drawable.gbp,
            R.drawable.cny,
            R.drawable.sek
    };

    private float[] values = {1, 0.0143513334f, 0.012695801f, 0.0113821665f, 0.1015537121f, 0.133436677f};
    private static boolean b;
    private ArrayList<HashMap<String, String>> list;

    private CurrencyService service;

    public static Button getBtn1() {
        return btn1;
    }

    public static Button getBtn2() {
        return btn2;
    }

    public static boolean isB() {
        return b;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt = findViewById(R.id.edt);
        edt.setOnEditorActionListener(this);
        txt = findViewById(R.id.txt);
        btn1 = findViewById(R.id.btn1);
        btn1.requestFocus();
        btn2 = findViewById(R.id.btn2);
        list = new ArrayList<>();
        listView = findViewById(R.id.lv);
        service = Client.getClient().create(CurrencyService.class);
        updateCurrency();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateValue();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.btn1):
                Intent intent = new Intent(this, FragmentActivity.class);
                startActivity(intent);
                b = true;
                break;
            case (R.id.btn2):
                Intent intent1 = new Intent(this, FragmentActivity.class);
                startActivity(intent1);
                b = false;
                break;
            case (R.id.imgBtn):
                String tmp = btn1.getText().toString();
                btn1.setText(btn2.getText());
                btn2.setText(tmp);
                updateList(Float.parseFloat(txt.getText().toString()));
                updateValue();
                break;
        }
    }

    public void updateCurrency() {
        for (int i = 0; i < 6; i++) {
            final int finalI = i;
            service.getRates(s[i])
                    .enqueue(new Callback<WrapperCurrencyObject>() {
                        @Override
                        public void onResponse(Call<WrapperCurrencyObject> call, Response<WrapperCurrencyObject> response) {
                            WrapperCurrencyObject object = response.body();
                            values[finalI] = object.getCurrencyObject().getRateFor(s[finalI]);
                        }

                        @Override
                        public void onFailure(Call<WrapperCurrencyObject> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Нет подключения к Интернету!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void updateValue() {
        float v, v1 = 1;
        try {
            v = Float.parseFloat(edt.getText().toString());
        } catch (Exception e) {
            v = 0;
        }
        CharSequence text = btn1.getText(), btn2Text = btn2.getText();
        if (text.equals(btn2Text)) {
            txt.setText(edt.getText());
            Toast toast = Toast.makeText(this, "Вы выбрали одинаковые валюты!", Toast.LENGTH_SHORT);
            toast.show();
        }
        if ("USD".contentEquals(text)) {
            v1 = values[1];
        } else if ("EUR".contentEquals(text)) {
            v1 = values[2];
        } else if ("GBP".contentEquals(text)) {
            v1 = values[3];
        } else if ("CNY".contentEquals(text)) {
            v1 = values[4];
        } else if ("SEK".contentEquals(text)) {
            v1 = values[5];
        }
        v /= v1;
        updateList(v);
        v1 = 1;
        if ("USD".contentEquals(btn2Text)) {
            v1 = values[1];
        } else if ("EUR".contentEquals(btn2Text)) {
            v1 = values[2];
        } else if ("GBP".contentEquals(btn2Text)) {
            v1 = values[3];
        } else if ("CNY".contentEquals(btn2Text)) {
            v1 = values[4];
        } else if ("SEK".contentEquals(btn2Text)) {
            v1 = values[5];
        }
        v *= v1;
        txt.setText(round(v * 100) / 100f + "");
    }

    public void updateList(float value) {
        list.clear();
        for (int i = 0; i < 6; i++) {
            HashMap<String, String> hm = new HashMap<>();
            hm.put("txt", s[i] + ": " + round(value * 100 * values[i]) / 100f);
            hm.put("flag", Integer.toString(flags[i]));
            list.add(hm);
            String[] from = {"flag", "txt"};
            int[] to = {R.id.photo, R.id.txtV};
            SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.view, from, to);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        try {
            updateValue();
        } catch (Exception e) {
            txt.setText(R.string._0_0);
        }
        return false;
    }
}