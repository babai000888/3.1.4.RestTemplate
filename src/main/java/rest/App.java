package rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import rest.entity.User;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class App {
    public static void main(String[] args) throws URISyntaxException {

        final RestTemplate restTemplate = new RestTemplate();
        final URI uri = new URI("http://91.241.64.178:7081/api/users");
        //Get All
        ResponseEntity<List<User>> allUsersEntity = restTemplate.exchange(uri,
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        //Set cookie
        String responseCookies = allUsersEntity.getHeaders().getFirst("Set-Cookie");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", responseCookies);
        //Add user
        final User user = new User(3L, "James", "Brown", (byte) 50);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> addUserEntity = restTemplate.exchange(uri,
                HttpMethod.POST, entity, String.class);
        //Edit user
        user.setName("Thomas");
        user.setLastName("Shelby");
        ResponseEntity<String> editUserEntity = restTemplate.exchange(uri,
                HttpMethod.PUT, entity, String.class);
        //Delete user
        ResponseEntity<String> deleteUserEntity = restTemplate.exchange(uri + "/3",
                HttpMethod.DELETE, entity, String.class);

        System.out.println("List of users:" + allUsersEntity.getBody());
        System.out.println("Result: " + addUserEntity.getBody() + editUserEntity.getBody() + deleteUserEntity.getBody());
    }
}
