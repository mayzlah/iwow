import java.io.*;
import java.net.*;
import java.lang.*;
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
            String Realm = new String("Пиратская бухта");
            String Character = new String("Вотхернейм");
            if (args[0] != null)
            {
                Character = args[0];
            }
            if (args[1] != null)
            {
                Realm = args[1];
            }
            String Parameters = new String("fields=items,progression,stats");
            String Request = new String(Host + "/api/wow/character/" + Realm + "/" + Character + "?" + Parameters);
            URI requestURI = new URI(Request);
            URL RequestURL = requestURI.toURL();

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
            JSONObject ItemJSON = (JSONObject) myJSON.get("items");
            System.out.println("Averege Item Lvl: " + ItemJSON.get("averageItemLevel").toString());
            System.out.println("Equipped Item Lvl: " + ItemJSON.get("averageItemLevelEquipped").toString());
            JSONObject ResJSON = (JSONObject) myJSON.get("stats");
            System.out.println("Resilience on equipment: " + ResJSON.get("resil"));
            JSONObject ProgJSON = (JSONObject) myJSON.get("stats");
            System.out.println("Resilience on equipment: " + ResJSON.get("resil"));
            JSONObject progJSON = (JSONObject) myJSON.get("progression");
            JSONArray arrProg = (JSONArray) progJSON.get("raids");
            int counter = 0;
            for (counter = 0; counter < arrProg.length(); counter++)
            {
                progJSON = arrProg.getJSONObject(counter);
                if( progJSON.get("name") == "Dragon Soul")
                {
                    break;
                }
            }
            System.out.println("Normal cont progress: " + progJSON.get("normal"));
            System.out.println("Heroic cont progress: " + progJSON.get("heroic"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
