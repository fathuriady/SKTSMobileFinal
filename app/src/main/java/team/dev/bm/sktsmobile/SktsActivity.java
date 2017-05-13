package team.dev.bm.sktsmobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by User on 4/29/2017.
 */

public class SktsActivity extends AppCompatActivity {
    private Button btnlanjut;
    private Spinner tujuan;
    private String[] list = {"- pilih -","Pekerjaan", "Pendidikan",};
    private EditText edttxtNamaL,edttxtKelamin,edttxtNIK,edttxtTTL,edttxtPekerjaan,edttxtKwn,edttxtAgama,
            edttxtStatus,edttxtAlamat,edttxtRTRW,edttxtKelurahan,edttxtKecamatan;
    private Button btnSubmit;
    private SessionManager session;
    private RadioButton rb,rbPria, rbPerempuan;
    private RadioGroup rg;

    private static final String ISIPROFIL_URL = "http://www.fathuriady.esy.es/skts/IsiProfile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skts);




        tujuan = (Spinner) findViewById(R.id.tujuan);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tujuan.setAdapter(adapter);

        btnlanjut = (Button)findViewById(R.id.btnlanjut);
        btnlanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SktsActivity.this,kosan.class);
                startActivity(i);
            }
        });
        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        edttxtNamaL = (EditText)findViewById(R.id.edttxtNamaL);
        edttxtTTL = (EditText)findViewById(R.id.edttxtTTL);
        edttxtNIK = (EditText)findViewById(R.id.edttxtNIK);
        edttxtPekerjaan = (EditText)findViewById(R.id.edttxtPekerjaan);
        edttxtKwn = (EditText)findViewById(R.id.edttxtKwn);
        edttxtStatus = (EditText)findViewById(R.id.edttxtStatus);
        edttxtAgama = (EditText)findViewById(R.id.edttxtAgama);
        edttxtAlamat = (EditText)findViewById(R.id.edttxtAlamat);
        edttxtRTRW = (EditText)findViewById(R.id.edttxtRT);
        edttxtKelurahan = (EditText)findViewById(R.id.edttxtKelurahan);
        edttxtKecamatan = (EditText)findViewById(R.id.edttxtKecamatan);

        rg = (RadioGroup)findViewById(R.id.rgJK);
        rbPria = (RadioButton)findViewById(R.id.rbPria);
        rbPerempuan = (RadioButton)findViewById(R.id.rbPerempuan);

        btnSubmit = (Button)findViewById(R.id.btnSimpan);
        btnSubmit.setOnClickListener(this);
    }

    private void IsiProfil(){
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();



        int selectedId = rg.getCheckedRadioButtonId();
        rb = (RadioButton)findViewById(selectedId);
        String jk;
        if(rb.getText() == null){
            jk="";
        }else{
            jk = rb.getText().toString().trim();
        }




        String nama = edttxtNamaL.getText().toString().trim();
        String ttl = edttxtTTL.getText().toString().trim();
        String nik = edttxtNIK.getText().toString().trim();
        String pekerjaan = edttxtPekerjaan.getText().toString().trim();
        String kwn = edttxtKwn.getText().toString().trim();
        String status = edttxtStatus.getText().toString().trim();
        String agama = edttxtAgama.getText().toString().trim();
        String alamat = edttxtAlamat.getText().toString().trim();
        String rtrw = edttxtRTRW.getText().toString().trim();
        String kelurahan = edttxtKelurahan.getText().toString().trim();
        String kecamatan = edttxtKecamatan.getText().toString().trim();
        String username = user.get(SessionManager.KEY_USERNAME);
        isi(nama,jk,ttl,nik,pekerjaan,kwn,status,agama,alamat,rtrw,kelurahan,kecamatan,username);
    }


    private void isi(String nama, String jk, String ttl, String nik, String pekerjaan, String kwn,
                     String status, String agama, String alamat,String rtrw,String kelurahan,String kecamatan, String username) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ProfileActivity.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("Sukses")){
                    Intent intent = new Intent(ProfileActivity.this,Navigation.class);
                    //Intent intent = new Intent(Login.this,LihatProfile.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(ProfileActivity.this,s,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("nama",params[0]);
                data.put("jk",params[1]);
                data.put("ttl",params[2]);
                data.put("nik",params[3]);
                data.put("pekerjaan",params[4]);
                data.put("kwn",params[5]);
                data.put("status",params[6]);
                data.put("agama",params[7]);
                data.put("alamat",params[8]);
                data.put("rtrw",params[9]);
                data.put("kelurahan",params[10]);
                data.put("kecamatan",params[11]);
                data.put("username",params[12]);

                String result = ruc.sendPostRequest(ISIPROFIL_URL,data);

                return result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(nama,jk,ttl,nik,pekerjaan,kwn,status,agama,alamat,rtrw,kelurahan,kecamatan,username);
    }


    @Override
    public void onClick(View view) {
        if(view == btnSubmit){
            IsiProfil();
        }
    }
}
