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
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import team.dev.bm.sktsmobile.Config;
import team.dev.bm.sktsmobile.LihatProfile;
import team.dev.bm.sktsmobile.R;
import team.dev.bm.sktsmobile.RequestHandler;
import team.dev.bm.sktsmobile.SessionManager;
import team.dev.bm.sktsmobile.SkpActivity;
import team.dev.bm.sktsmobile.SktsActivity;

public class ImportFragmentHome extends Fragment {

    private SessionManager session;
    Button btnSKTS,btnSKP;
    private static final String ISIPROFIL_URL = "http://www.fathuriady.esy.es/skts/getData.php?username=";
    private String username;
    TextView txtNama;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.activity_home,container,false);
        btnSKTS = (Button)view.findViewById(R.id.btnSKTS);
        btnSKP = (Button)view.findViewById(R.id.btnSKP);
        txtNama = (TextView)view.findViewById(R.id.txtNama);

        session = new SessionManager(view.getContext());
        session.checkLogin();

        btnSKTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), SktsActivity.class));
            }
        });

        btnSKP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), SkpActivity.class));
            }
        });
        getData();
        return view;
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

            txtNama.setText("Hai " + nama);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
