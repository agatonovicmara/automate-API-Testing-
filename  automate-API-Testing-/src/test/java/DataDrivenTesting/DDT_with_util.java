package DataDrivenTesting;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class DDT_with_util {
    public DDT_with_util() throws IOException {
    }

    @Test(dataProvider = "empDataProvider")
    public void addNewEmployees(String ename, String esal, String eage){

        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";

        RequestSpecification httpRequest=RestAssured.given();

        //here we created data which we can send along with the post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", ename);
        requestParams.put("salary", esal);
        requestParams.put("age", eage);

        // add a header stating the request body is json
        httpRequest.header("Content-Type", "application/json");

        //add the  json to the body of the request
        httpRequest.body(requestParams.toJSONString());

        //post request
        Response respone = httpRequest.request(Method.POST, "/create");

        //capture response body to perform validations
        String responseBody = respone.getBody().asString();

        System.out.println("Response body is:"+ responseBody);

        Assert.assertEquals(responseBody.contains(ename), true);
        Assert.assertEquals(responseBody.contains(esal), true);
        Assert.assertEquals(responseBody.contains(eage), true);

        int statusCode = respone.statusCode();

        Assert.assertEquals(statusCode, 200);

    }


    //data provider method

    @DataProvider(name="empDataProvider")

    public Object [][] getEmpData() throws IOException {

    String path = System.getProperty("user.dir")+"/src/test/java/DataDrivenTesting/empdata.xlsx";
    int rownum =  XLUtils.getRowCount(path, "Sheet1");
    int colcount = XLUtils.getCellCount(path, "Sheet1", 1);


    String empdata [][]=new String[rownum][colcount];
    for(int i=1; i<=rownum; i++){
        for(int j=0; j<colcount; j++){
            empdata[i-1][j]= XLUtils.getCellData(path, "Sheet1", i, j);
        }
    }


//        Object [][] empdata ={{"maja", "100", "20"}, {"ana", "200", "30"}, {"eva", "400", "60"}};

        return(empdata);
   }



}


