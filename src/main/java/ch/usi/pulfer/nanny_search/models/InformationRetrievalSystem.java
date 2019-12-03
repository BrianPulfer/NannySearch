package ch.usi.pulfer.nanny_search.models;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InformationRetrievalSystem {
    private static boolean initialized = false;

    private final static String SOLR = "./src/main/api/solr-8.2.0/bin/solr";

    private final static int PORT = 8983;
    private final static String LOCALHOST = "http://localhost:"+PORT+"/solr/nutch/query?q=";

    private final static int MAX_ROWS = 1000;

    public static ArrayList<QueryResult> getResults(Query query){
        checkInitialization();

        ArrayList<QueryResult> results = new ArrayList<>();

        String text = query.getText();
        String experience = query.getExperience();
        String minAge = query.getMinAge();
        String maxAge = query.getMaxAge();
        String location = query.getLocation().replace("shire","");


        try {
            String parameters = getQueryParameters(Arrays.asList(text, experience, location, minAge, maxAge), true);
            URL QUERY_URL = new URL(LOCALHOST + parameters + "&rows="+MAX_ROWS);

            HttpURLConnection connection = (HttpURLConnection) QUERY_URL.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in =  new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String currentLine = null;

            while( (currentLine=in.readLine()) != null){
                sb.append(currentLine);
            }

            in.close();

            connection.disconnect();
            results = responseToResults(sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return results;
    }

    private static ArrayList<QueryResult> responseToResults(String response) {
        ArrayList<QueryResult> results = new ArrayList<>();
        String[] contents = response.split("url\":\"");

        for(int i = 1; i<contents.length; i++){
            String c = contents[i];

            String title = c.split("\"content\":\"")[1].split("-")[0];
            String name = c.split("-")[1];
            String text = "";
            if(c.split("About Me").length > 1)
                text = c.split("About Me")[1].split("\nMon\nTue\nWed\n")[0].replace("\\n", " ");
            String link = c.split("\"")[0];

            results.add(new QueryResult(title, name, text, link));
        }

        return results;
    }

    private static String getQueryParameters(List<String> parameters, Boolean last2AreAge) {

        String returnValue = "";
        for (int i = 0; i<parameters.size(); i++){
            if(last2AreAge && i >= parameters.size()-2){
                int minAge = -1;
                int maxAge = -1;

                if(!parameters.get(i).equals("Any")) {
                    minAge = Integer.parseInt(parameters.get(i));
                }
                if(!parameters.get(i+1).equals("Any")) {
                    maxAge = Integer.parseInt(parameters.get(i + 1));
                }

                if(minAge == -1){
                    minAge = maxAge;
                }

                if(maxAge == -1){
                    break;
                }

                for (int age = minAge; age <= maxAge; age++) {
                    returnValue = (returnValue + "%0Acontent:\"" + age + " years old\"")
                            .replace(" ", "%20");
                }
                break;
            } else {
                if (!parameters.get(i).equals("Any")) {
                    if (i == 0) {
                        returnValue = "content:" + parameters.get(i).replace(" ", "%0Acontent:");
                    } else {
                        returnValue = (returnValue + "%0Acontent:\"" + parameters.get(i).replace(" ", "%20") + "\"");
                    }
                }
            }
        }

        return returnValue;
    }

    public static void initialize(){
        checkInitialization();
    }

    private static void checkInitialization(){
        if(!initialized){
            start();
            initialized = true;
        }
    }

    private static void start(){
        stop();

        try {
            // Current working directory is nanny_search/
            String cmd1 = SOLR +" start";

            Runtime.getRuntime().exec(cmd1);
            waitFiveSeconds();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void stop(){
        try {
            // Current working directory is nanny_search/
            String cmd = SOLR+" stop";
            Runtime.getRuntime().exec(cmd);
            waitFiveSeconds();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void waitFiveSeconds(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
