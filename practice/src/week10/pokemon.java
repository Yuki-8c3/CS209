package week10;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class pokemon {

  public static void main(String[] args) {
    String data = restCallerGet();
    HashMap map = new Gson().fromJson(data,HashMap.class);
    System.out.println("Name:" + map.get("name"));
    System.out.println("Height:" + map.get("height"));
    System.out.println("Weight:" + map.get("weight"));
    System.out.println("Abilities:" + map.get("abilities"));
  }

  public static String restCallerGet() {
    String httpurl = "https://pokeapi.co/api/v2/pokemon/pikachu/";

    String data = "";

    String lasturl = httpurl;
    try {
      URL url = new URL(lasturl);
      HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();

      //请求头
      urlConn.setRequestProperty("Accept-Charset", "utf-8");
      urlConn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

      urlConn.setDoOutput(true);
      urlConn.setDoInput(true);
      urlConn.setRequestMethod("GET");
      urlConn.connect();

      int code = urlConn.getResponseCode();
      if (code == 200) {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
        data = reader.readLine();
      }
      urlConn.disconnect();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return data;
  }
}

