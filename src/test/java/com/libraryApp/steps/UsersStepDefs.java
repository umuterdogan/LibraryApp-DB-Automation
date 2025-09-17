package com.libraryApp.steps;

import com.libraryApp.pages.UsersPage;
import com.libraryApp.utility.BrowserUtil;
import com.libraryApp.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class UsersStepDefs {
    UsersPage usersPage = new UsersPage();
    String expectedStatus;
    String email;

    @When("the user clicks Edit User button")
    public void the_user_clicks_edit_user_button() {
        BrowserUtil.waitFor(1);
        usersPage.editUser.click();
    }
    @When("the user changes user status {string} to {string}")
    public void the_user_changes_user_status_to(String active, String inactive) {
        BrowserUtil.waitFor(1);
        Select select = new Select(usersPage.statusDropdown);
        select.selectByVisibleText(inactive);

        email = usersPage.email.getAttribute("value");
        System.out.println(email);

        expectedStatus=inactive;
    }
    @When("the user clicks save changes button")
    public void the_user_clicks_save_changes_button() {
        BrowserUtil.waitFor(1);
        usersPage.saveChanges.click();
        System.out.println(email +" is deactivated.");

    }
    @Then("{string} message should appear")
    public void message_should_appear(String expectedMessage) {

        BrowserUtil.waitFor(1);
        String actualMessage = usersPage.toastMessage.getText();
        Assert.assertEquals(expectedMessage,actualMessage);

    }
    @Then("the users should see same status for related user in database")
    public void the_users_should_see_same_status_for_related_user_in_database() {

        String query ="select status from users where email='"+email+"'";

        DB_Util.runQuery(query);

        String actualStatus = DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(expectedStatus,actualStatus);
    }
    @Then("the user changes current user status {string} to {string}")
    public void the_user_changes_current_user_status_to(String inactive, String active) {
        // Reset user status to DEFAULT to keep QA environment stable for next tests
        BrowserUtil.waitFor(4);
        BrowserUtil.selectByVisibleText(usersPage.userStatusDropdown, inactive);

        usersPage.searchField.sendKeys(email);
        BrowserUtil.waitFor(3);

        BrowserUtil.waitFor(3);
        usersPage.editUser(email).click();

        BrowserUtil.waitFor(3);
        BrowserUtil.selectByVisibleText(usersPage.statusDropdown, active);

        BrowserUtil.waitFor(3);
        usersPage.saveChanges.click();
        System.out.println(email + " is activated.");
    }


}
