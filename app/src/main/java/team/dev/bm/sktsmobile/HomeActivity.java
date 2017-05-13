package team.dev.bm.sktsmobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by User on 4/29/2017.
 */

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnSKTS,btnSKP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        btnSKTS = (Button)findViewById(R.id.btnSKTS);
        btnSKP = (Button)findViewById(R.id.btnSKP);

        btnSKTS.setOnClickListener(this);
        btnSKP.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view==btnSKTS){
            startActivity(new Intent(this,SktsActivity.class));
        }
        if(view==btnSKP){
            startActivity(new Intent(this,SkpActivity.class));
        }
    }
}
