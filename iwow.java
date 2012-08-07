import java.io.*;
import java.net.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class iWOW
{
    public static void main(String args[])
    {
        try
        {
            String Host = new String("http://eu.battle.net");
            String Realm = new String("Rexxar");
            String Character = new String("Mon");
            String Parameters = new String("fields=items");
            URL RequestURL = new URL(Host + "/api/wow/character/" + Realm + "/" + Character + "?" + Parameters);

            HttpURLConnection httpCon = (HttpURLConnection) RequestURL.openConnection();
            httpCon.setRequestMethod("GET");

            httpCon.setReadTimeout(15*1000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;

            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

            JSONParser parser = new JSONParser();
            JSONObject myJSON = (JSONObject) parser.parse(stringBuilder.toString());
            myJSON = (JSONObject) myJSON.get("items");
            System.out.println(myJSON.get("averageItemLevel").toString());

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
