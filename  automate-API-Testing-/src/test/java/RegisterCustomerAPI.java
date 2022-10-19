import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterCustomerAPI {

    @Test

    public void registerCustomer() {

        RestAssured.baseURI = "http://restapi.demoqa.com/customer";
        RequestSpecification httpRequest = RestAssured.given();

        //requested parameters that are sent with body

        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "Marija123drg");
        requestParams.put("LastName", "Agatonovic");
        requestParams.put("UserName", "Agat");
        requestParams.put("Password", "1234567899");
        requestParams.put("Email", "agatonovicmara@gmail.com");

        httpRequest.header("Content-type", "application/json");
        httpRequest.body(requestParams.toJSONString());

        Response response = httpRequest.request(Method.POST, "/register");

        String responseBody = response.getBody().asString();
        System.out.println("response body is: " +responseBody);

        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " +statusCode);

        Assert.assertEquals(
                statusCode,
                201
        );

        //validate success message

        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals(
                successCode,
                "blahblahblah"
        );




    }

}
