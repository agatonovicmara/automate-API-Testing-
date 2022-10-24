import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GETpetById {

    @Test

    public void findPetById(){

        //specify baseURI
        RestAssured.baseURI="https://petstore.swagger.io/v2/pet";

        //request object

        RequestSpecification httpRequest=RestAssured.given();

        //response object

        Response response=httpRequest.request(Method.GET, "/1");

        String responseBody = response.getBody().asString();

        System.out.println("response body is: " +responseBody);

        //get status code

        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " +statusCode);

        Assert.assertEquals(
                statusCode,
                200
        );

        //status line verification

        String statusLine = response.getStatusLine();
        System.out.println(statusLine);

        Assert.assertEquals(
                statusLine,
                "HTTP/1.1 200 OK"
        );

        //mozemo da verifikujemo sve sto postoji u responsu



    }

    @Test

    public void worldTime(){

        RestAssured.baseURI= "http://worldtimeapi.org/api/timezone/Europe";
        RequestSpecification httpRequest=RestAssured.given();
        Response response=httpRequest.request(Method.GET, "/London");
        String responseBody = response.getBody().asString();

        System.out.println("Response body is: " +responseBody);

        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " +statusCode);

        Assert.assertEquals(
                statusCode,
                200
        );




    }
}
