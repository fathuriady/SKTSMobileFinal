package team.dev.bm.sktsmobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class kosan extends AppCompatActivity {

    private RadioGroup rg;
    private RadioButton rbKosan, rbApart;
    private TextView txtNamaW,txtAlamatW,txtNIKW;
    private EditText edttxtNamaW,edttxtAlamatW,edttxtNIKW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kosan);

        txtNamaW = (TextView)findViewById(R.id.txtNamaW);
        txtAlamatW = (TextView)findViewById(R.id.txtAlamatW);
        txtNIKW = (TextView)findViewById(R.id.txtNIKW);

        edttxtNamaW = (EditText)findViewById(R.id.edttxtNamaW);
        edttxtAlamatW = (EditText)findViewById(R.id.edttxtAlamatW);
        edttxtNIKW = (EditText)findViewById(R.id.edttxtNIKW);




        /*
        rg = (RadioGroup)findViewById(R.id.TT);
        int selectedId = rg.getCheckedRadioButtonId();
        rbKosan = (RadioButton)findViewById(selectedId);
        int index = rg.indexOfChild(findViewById(rg.getCheckedRadioButtonId()));
        String jk;
        if(index == 0){
            txtNamaW.setVisibility(View.GONE);
            txtAlamatW.setVisibility(View.GONE);
            txtNIKW.setVisibility(View.GONE);
            edttxtNamaW.setVisibility(View.GONE);
            edttxtAlamatW.setVisibility(View.GONE);
            edttxtNIKW.setVisibility(View.GONE);
        }else{
            txtNamaW.setVisibility(View.VISIBLE);
            txtAlamatW.setVisibility(View.VISIBLE);
            txtNIKW.setVisibility(View.VISIBLE);
            edttxtNamaW.setVisibility(View.VISIBLE);
            edttxtAlamatW.setVisibility(View.VISIBLE);
            edttxtNIKW.setVisibility(View.VISIBLE);
        }*/


    }
}