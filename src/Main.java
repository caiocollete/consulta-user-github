import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scan = new Scanner(System.in);

        System.out.print("User: ");
        String userSearch = scan.nextLine();
        scan.close();

        String address = "https://api.github.com/users/";
        address = address + userSearch.replace(" ","").toLowerCase();


        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(address))
                    .build();
            HttpResponse<String> response= client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            ApiInfoCatcher c = gson.fromJson(response.body(), ApiInfoCatcher.class);
            if(c.id()==null){
                throw new ErroConsultaGitHubException("User not found");
            }
            else{
                User user = new User(c);
                System.out.println("Name: "+ user.getName());
                System.out.println("ID: "+ user.getId());
            }

        }
        catch (ErroConsultaGitHubException e){
            System.out.println(e.getMessage());
        }
    }
}