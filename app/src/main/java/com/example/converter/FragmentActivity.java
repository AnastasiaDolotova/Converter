package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        listView = findViewById(R.id.listV);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list, android.R.layout.simple_list_item_single_choice);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(MainActivity.isB()){
                    switch (position){
                        case 0:
                            MainActivity.getBtn1().setText(R.string.rub);
                            break;
                        case 1:
                            MainActivity.getBtn1().setText(R.string.usd);
                            break;
                        case 2:
                            MainActivity.getBtn1().setText(R.string.eur);
                            break;
                        case 3:
                            MainActivity.getBtn1().setText(R.string.gbp);
                            break;
                        case 4:
                            MainActivity.getBtn1().setText(R.string.cny);
                            break;
                        case 5:
                            MainActivity.getBtn1().setText(R.string.sek);
                            break;
                    }
                } else {
                    switch (position){
                        case 0:
                            MainActivity.getBtn2().setText(R.string.rub);
                            break;
                        case 1:
                            MainActivity.getBtn2().setText(R.string.usd);
                            break;
                        case 2:
                            MainActivity.getBtn2().setText(R.string.eur);
                            break;
                        case 3:
                            MainActivity.getBtn2().setText(R.string.gbp);
                            break;
                        case 4:
                            MainActivity.getBtn2().setText(R.string.cny);
                            break;
                        case 5:
                            MainActivity.getBtn2().setText(R.string.sek);
                            break;
                    }
                }
                onBackPressed();
            }
        });
    }
}
