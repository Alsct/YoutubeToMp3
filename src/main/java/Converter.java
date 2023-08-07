import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLEncoder;
import java.util.Scanner;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
public class Converter {
    public static void main( String[] args ) throws Exception
    {
        howto();


        // Host url
        String host = "https://youtube-mp3-download1.p.rapidapi.com/";

        String charset = "UTF-8";

        // Headers for a request
        String x_rapidapi_host = "youtube-mp3-download1.p.rapidapi.com";
        String x_rapidapi_key = "af6dee4f19msha4125e9f8840531p1df127jsnc70f48efd7e7";//Type here your key


        // Get the YouTube video ID from the user
        String videoId = inputID("Type in the ID of the YouTube video: ");
        // Format query for preventing encoding problems
        String query = String.format("id=%s", URLEncoder.encode(videoId, charset));



        HttpResponse<String> response = Unirest.get(host+ "dl?" + query)
                .header("X-RapidAPI-Key", x_rapidapi_key)
                .header("X-RapidAPI-Host", x_rapidapi_host)
                .asString();

        // Parse the HTTP response body into a JsonNode
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response.getBody());

        // Extract and print the "link" value
        String link = jsonNode.get("link").asText();
        System.out.println(link);

        // Extract and print the "title" value
        String title = jsonNode.get("title").asText();
        System.out.println("Video Title: " + title);

    }

    public static void howto() {
        System.out.println("The video ID will be located in the URL of the video page, right after the v= URL parameter. \n" + "In this case, the URL of the video is: https://www.youtube.com/watch?v=aqz-KE-bpKQ. \n" + "Therefore, the ID of the video is aqz-KE-bpKQ.\n");
    }


    public static String inputID(String message){
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();

    }
}
