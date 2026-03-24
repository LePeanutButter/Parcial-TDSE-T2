package co.escuelaing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class Proxy {
    private static final String USER_AGENT = "Mozilla/5.0";

    String SERVER_1 = "http://ec2-44-205-245-36.compute-1.amazonaws.com:8080";
    String SERVER_2 = "http://ec2-98-92-95-121.compute-1.amazonaws.com:8080";

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("/pell")
    public String pellseq(@RequestParam(value = "n") int n) {
        String param = "/pell?n=" + n;
        return forward(SERVER_1, SERVER_2, param);
    }

    private String forward(String server_1, String server_2, String param){
        String value;
        try {
            value = call(SERVER_1 + param);
        } catch (IOException e1) {
            try {
                value = call(SERVER_2 + param);
            } catch (IOException e2) {
                throw new RuntimeException();
            }
        }
        return value;
    }

    private String call(String request) throws IOException {
        URL obj = new URL(request);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        if (responseCode != HttpURLConnection.HTTP_OK) {
            System.out.println("GET request not worked");
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
