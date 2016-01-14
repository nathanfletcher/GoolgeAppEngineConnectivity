//Async Task to pull database
/*
* Put this class below your MainActivity.java class to let it interact with your Activitie's elements
* Or you could make it into it's own class and work on its own. Choose your destiny :P
* HERE'S HOW TO RUN IT: IN YOUR MAIN ACTIVITY (or any activity task), WRITE THESE TWO LINES
        AndroidGAEConnection fetch = new AndroidGAEConnection();
        fetch.execute();
*/

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

    public  class AndroidGAEConnection extends AsyncTask<String,Void,Boolean> {
        String output = "";

        @Override
        protected Boolean doInBackground(String... params) {
            //Open Database link:
            //http://ispace-open-data.appspot.com/election/open-data/publicdata/all/json/

            StringBuffer asyncjson = request("http://ispace-open-data.appspot.com/election/open-data/publicdata/all/json/");
            try {
                //asyncjson.getJSONArray("items");
                output = asyncjson.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //output = asyncjson.toString();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                //jsonoutput is a simple TextView in my app that shows the... well... JSON output.
                jsonoutput.setText(output);
            }
            else{
                jsonoutput.setText("Something went wrong");
            }
        }

        //This method set's up the connection and does the magic.
        
        private StringBuffer request(String urlString) {
        // TODO Auto-generated method stub

        StringBuffer chaine = new StringBuffer("");
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while ((line = rd.readLine()) != null) {
                chaine.append(line);
            }

        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
        }

        return chaine;
    }
    }
