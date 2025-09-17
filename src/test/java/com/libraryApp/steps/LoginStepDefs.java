package com.libraryApp.steps;

import com.libraryApp.pages.DashBoardPage;
import com.libraryApp.pages.LoginPage;
import com.libraryApp.utility.BrowserUtil;
import com.libraryApp.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDefs {

    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualUserName;
    String email;

    @Given("the user logged in  {string} and {string}")
    public void the_user_logged_in_and(String email, String password) {
        loginPage.login(email,password);
        BrowserUtil.waitFor(2);
        //get email assign to global
        this.email = email;

    }
    @When("user gets username  from user fields")
    public void user_gets_username_from_user_fields() {
        BrowserUtil.waitFor(2);
         actualUserName = dashBoardPage.accountHolderName.getText();
        System.out.println("actualUserName = " + actualUserName);

    }
    @Then("the username should be same with database")
    public void the_username_should_be_same_with_database() {
        String query = "select full_name from users where email = '"+email+"'";

        DB_Util.runQuery(query);

        String expectedUserName = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedUserName = " + expectedUserName);

        Assert.assertEquals(expectedUserName,actualUserName);


    }

}
