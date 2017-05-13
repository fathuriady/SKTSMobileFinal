package team.dev.bm.sktsmobile;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by User on 4/27/2017.
 */

public class LihatProfile extends AppCompatActivity{
    private EditText edttxtNamaL, edttxtKelamin, edttxtNIK, edttxtTTL, edttxtPekerjaan, edttxtKwn, edttxtAgama,
            edttxtStatus, edttxtAlamat;
    private RadioButton rbPria, rbPerempuan;
    private Button btnSubmit;
    private SessionManager session;
    private String username;
    private static final String LIHATPROFIL_URL = "http://www.fathuriady.esy.es/skts/getData.php?username=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        edttxtNamaL = (EditText) findViewById(R.id.edttxtNamaL);

        edttxtTTL = (EditText) findViewById(R.id.edttxtTTL);
        edttxtNIK = (EditText) findViewById(R.id.edttxtNIK);
        edttxtPekerjaan = (EditText) findViewById(R.id.edttxtPekerjaan);
        edttxtKwn = (EditText) findViewById(R.id.edttxtKwn);
        edttxtStatus = (EditText) findViewById(R.id.edttxtStatus);
        edttxtAgama = (EditText) findViewById(R.id.edttxtAgama);
        edttxtAlamat = (EditText) findViewById(R.id.edttxtAlamat);


        getData();
    }

    private void getData(){
        class GetData extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatProfile.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showData(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> user = session.getUserDetails();
                username = user.get(SessionManager.KEY_USERNAME);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GETDATA,username);
                return s;
            }
        }
        GetData ge = new GetData();
        ge.execute();
    }

    private void showData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject c = result.getJSONObject(0);
            String nama = c.getString("nama");
            String jk = c.getString("jk");
            String ttl = c.getString("ttl");
            String nik = c.getString("nik");
            String pekerjaan = c.getString("pekerjaan");
            String kwn = c.getString("kwn");
            String agama = c.getString("agama");
            String status = c.getString("status");
            String alamat = c.getString("alamat");


            edttxtNamaL.setText(nama);
            edttxtAlamat.setText(alamat);
            edttxtKelamin.setText(jk);
            edttxtTTL.setText(ttl);
            edttxtNIK.setText(nik);
            edttxtPekerjaan.setText(pekerjaan);
            edttxtStatus.setText(status);
            edttxtKwn.setText(kwn);
            edttxtAgama.setText(agama);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}