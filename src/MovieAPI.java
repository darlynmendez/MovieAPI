
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.*;


public class MovieAPI {
    public static void main(String[] args) {
        // Create a HTTP Connection.
        String baseUrl = "https://api.themoviedb.org";
        String callAction = "/3/movie/";
        String movieId = "260513";
        String apiKey = "e0f59dbc98a92174f42ab5209480f8dc";
        String urlString = baseUrl + callAction + movieId + "?api_key=" + apiKey + "&language=en-US";
        URL url;

        try {
            // Make the connection.
            url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Examine the response code.
            int status = con.getResponseCode();
            if (status != 200) {
                System.out.println("Error: Could not load movie: " + status);
            }
            else {
                // Parsing input stream into a text string.
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                // Close the connections.
                in.close();
                con.disconnect();
                // Print out our complete JSON string.
                System.out.println("Output: " + content.toString());
                // Parse that object into a usable Java JSON object.
                JSONObject obj = new JSONObject(content.toString());
                // Print out the movie name.
                String movieName = obj.getString("original_title");
                System.out.println("Movie Name: " + movieName);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return;
        }
    }
}

