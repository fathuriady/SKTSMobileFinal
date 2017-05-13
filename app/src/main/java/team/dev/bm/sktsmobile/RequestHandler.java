package team.dev.bm.sktsmobile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by Fathur on 4/14/2017.
 */

public class RequestHandler {

    public String sendPostRequest(String requestURL, HashMap<String,String> postDataParams){
        URL url;

        StringBuilder sb = new StringBuilder();
        try{
            url = new URL(requestURL);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //settingan property conn
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setConnectTimeout(1500);
            con.setReadTimeout(1500);
            con.setRequestMethod("POST");

            //buat output Stream
            OutputStream os = con.getOutputStream();

            //parameter untuk request menggunakan getPostDataString
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();

            int responseCode = con.getResponseCode();
            if(responseCode == HttpsURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                sb = new StringBuilder();
                String response;
                //Baca response dari server
                while((response = br.readLine()) != null){
                    sb.append(response);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String sendGetRequest(String requestURL){
        StringBuilder sb = new StringBuilder();
        try{
            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String s;
            while((s=br.readLine())!=null){
                sb.append(s+"\n");
            }
        }catch(Exception e){

        }

        return sb.toString();
    }

    private String getPostDataString(HashMap<String,String> params) throws UnsupportedEncodingException {
        StringBuilder result =  new StringBuilder();
        boolean first = true;
        for(Map.Entry<String,String> entry : params.entrySet()){
            if(first)
                first =false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
        }
        return result.toString();
    }

    public String sendGetRequestParam(String requestURL, String nama){
        StringBuilder sb =new StringBuilder();
        try {
            URL url = new URL(requestURL+nama);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while((s=bufferedReader.readLine())!=null){
                sb.append(s+"\n");
            }
        }catch(Exception e){
        }
        return sb.toString();
    }
}
