package com.libraryApp.steps;

import com.libraryApp.pages.UsersPage;
import com.libraryApp.utility.BrowserUtil;
import com.libraryApp.utility.DB_Util;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LiveSessionStepDefs {

    UsersPage usersPage= new UsersPage();

    String actualUserCount;

    @When("the user gets {string} user count")
    public void the_user_gets_user_count(String status) {

        BrowserUtil.selectByVisibleText(usersPage.userStatusDropdown,status);

        BrowserUtil.waitFor(2);

        String userDetails = usersPage.userCount.getText();
        actualUserCount = usersPage.getCount(userDetails);

        System.out.println("actualUserCount = " + actualUserCount);
    }
    @When("the {string} user count should be equal database")
    public void the_user_count_should_be_equal_database(String status) {

        String query= "select count(*) from users " +
                "where status='"+ status +"' and user_group_id <> 1";

        DB_Util.runQuery(query);
        String expectedCount = DB_Util.getFirstRowFirstColumn();


        Assert.assertEquals(expectedCount,actualUserCount);
    }


}
