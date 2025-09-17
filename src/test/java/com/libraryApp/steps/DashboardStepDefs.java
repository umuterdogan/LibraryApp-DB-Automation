package com.libraryApp.steps;

import com.libraryApp.pages.DashBoardPage;
import com.libraryApp.pages.LoginPage;
import com.libraryApp.utility.BrowserUtil;
import com.libraryApp.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class DashboardStepDefs
{
    String actualUserNumbers;
    String actualBookNumbers;
    String actualBorrowedBookNumbers;
    LoginPage loginPage=new LoginPage();
    DashBoardPage dashBoardPage=new DashBoardPage();


    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String user) {
        loginPage.login(user);
         BrowserUtil.waitFor(4);
    }

    @When("user gets all information from modules")
    public void user_gets_all_information_from_modules() {

        actualUserNumbers = dashBoardPage.usersNumber.getText();
        System.out.println("actualUserNumbers = " + actualUserNumbers);
        actualBookNumbers = dashBoardPage.booksNumber.getText();
        System.out.println("actualBookNumbers = " + actualBookNumbers);
        actualBorrowedBookNumbers = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedBookNumbers = " + actualBorrowedBookNumbers);

    }

    @Then("the information should be same with database")
    public void the_information_should_be_same_with_database() {

        //BOOKS
        DB_Util.runQuery("select count(*) from books");
        String expectedBookNumbers = DB_Util.getFirstRowFirstColumn();

        System.out.println("expectedBookNumbers = " + expectedBookNumbers);
        Assert.assertEquals(actualBookNumbers,expectedBookNumbers);

        //USERS
        DB_Util.runQuery("select count(*) from users");
        String expectedUserNumbers = DB_Util.getFirstRowFirstColumn();

        System.out.println("expectedUserNumbers = " + expectedUserNumbers);
        Assert.assertEquals(actualUserNumbers,expectedUserNumbers);

        //BORROWED BOOKS
        String query = "select count(*) from book_borrow\n" +
                "where is_returned = 0";
        DB_Util.runQuery(query);

        String expectedBorrowedBookNumbers= DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedBorrowedBookNumbers = " + expectedBorrowedBookNumbers);

        Assert.assertEquals(actualBorrowedBookNumbers,expectedBorrowedBookNumbers);

    }


}
