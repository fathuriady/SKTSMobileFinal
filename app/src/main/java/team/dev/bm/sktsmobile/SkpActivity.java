package team.dev.bm.sktsmobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by User on 4/29/2017.
 */

public class SkpActivity extends AppCompatActivity {
    private Button btnlanjut;
    private Spinner tujuan;
    private String[] list = {"- pilih -","Masuk Depok", "Keluar Depok",};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skp);



        tujuan = (Spinner) findViewById(R.id.tujuan);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tujuan.setAdapter(adapter);

        btnlanjut = (Button)findViewById(R.id.btnlanjut);
        btnlanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SkpActivity.this, pindah.class);
                startActivity(i);
            }
        });

    }
}
