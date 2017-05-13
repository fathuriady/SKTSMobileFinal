package team.dev.bm.sktsmobile.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import team.dev.bm.sktsmobile.Config;
import team.dev.bm.sktsmobile.Navigation;
import team.dev.bm.sktsmobile.ProfileActivity;
import team.dev.bm.sktsmobile.R;
import team.dev.bm.sktsmobile.RegisterUserClass;
import team.dev.bm.sktsmobile.RequestHandler;
import team.dev.bm.sktsmobile.SessionManager;
import team.dev.bm.sktsmobile.SkpActivity;
import team.dev.bm.sktsmobile.SktsActivity;

/**
 * Created by User on 5/13/2017.
 */

public class ImportFragmentEditProfile extends Fragment {

    private EditText edttxtNamaL,edttxtKelamin,edttxtNIK,edttxtTTL,edttxtPekerjaan,edttxtKwn,edttxtAgama,
            edttxtStatus,edttxtAlamat,edttxtRTRW,edttxtKelurahan,edttxtKecamatan;
    private Button btnSubmit;
    private SessionManager session;
    private RadioButton rb,rbPria, rbPerempuan;
    private RadioGroup rg;
    private String username;

    private static final String ISIPROFIL_URL= "http://www.fathuriady.esy.es/skts/IsiProfile.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_editprofile,container,false);

        session = new SessionManager(view.getContext());
        session.checkLogin();

        edttxtNamaL = (EditText)view.findViewById(R.id.edttxtNamaL);
        edttxtTTL = (EditText)view.findViewById(R.id.edttxtTTL);
        edttxtNIK = (EditText)view.findViewById(R.id.edttxtNIK);
        edttxtPekerjaan = (EditText)view.findViewById(R.id.edttxtPekerjaan);
        edttxtKwn = (EditText)view.findViewById(R.id.edttxtKwn);
        edttxtStatus = (EditText)view.findViewById(R.id.edttxtStatus);
        edttxtAgama = (EditText)view.findViewById(R.id.edttxtAgama);
        edttxtAlamat = (EditText)view.findViewById(R.id.edttxtAlamat);
        edttxtRTRW = (EditText)view.findViewById(R.id.edttxtRTRW);
        edttxtKelurahan = (EditText)view.findViewById(R.id.edttxtKel);
        edttxtKecamatan = (EditText)view.findViewById(R.id.edttxtKec);

        rg = (RadioGroup)view.findViewById(R.id.rgJK);
        rbPria = (RadioButton)view.findViewById(R.id.rbPria);
        rbPerempuan = (RadioButton)view.findViewById(R.id.rbPerempuan);

        btnSubmit = (Button)view.findViewById(R.id.btnSimpan);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IsiProfil();
            }
        });
        getData();

        return view;
    }

    private void IsiProfil(){
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();



        int selectedId = rg.getCheckedRadioButtonId();
        rb = (RadioButton)getView().findViewById(selectedId);
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
                     String status, String agama, String alamat,String rtrw,String kelurahan,String kecamatan,
                     String username) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getView().getContext(), "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("Sukses")){
                    Intent intent = new Intent(getView().getContext(),Navigation.class);
                    //Intent intent = new Intent(Login.this,LihatProfile.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getView().getContext(),s,Toast.LENGTH_LONG).show();
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
                data.put("agama",params[6]);
                data.put("status",params[7]);
                data.put("alamat",params[8]);
                data.put("username",params[9]);
                data.put("rtrw",params[10]);
                data.put("kelurahan",params[11]);
                data.put("kecamatan",params[12]);

                String result = ruc.sendPostRequest(ISIPROFIL_URL,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(nama,jk,ttl,nik,pekerjaan,kwn,status,agama,alamat,rtrw,kecamatan,kelurahan,username);


    }

    private void getData(){
        class GetData extends AsyncTask<Void,Void,String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                showData(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> user = session.getUserDetails();
                String username = user.get(SessionManager.KEY_USERNAME);
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
            String username = c.getString("username");
            String rtrw = c.getString("rtrw");
            String kelurahan = c.getString("kelurahan");
            String kecamatan = c.getString("kecamatan");


            edttxtNamaL.setText(nama);
            edttxtTTL.setText(ttl);
            edttxtNIK.setText(nik);
            edttxtPekerjaan.setText(pekerjaan);
            edttxtKwn.setText(kwn);
            edttxtAgama.setText(agama);
            edttxtStatus.setText(status);
            edttxtAlamat.setText(alamat);
            edttxtRTRW.setText(rtrw);
            edttxtKecamatan.setText(kecamatan);
            edttxtKelurahan.setText(kelurahan);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
