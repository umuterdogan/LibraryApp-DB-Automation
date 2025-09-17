package com.libraryApp.steps;

import com.libraryApp.pages.BookPage;
import com.libraryApp.utility.BrowserUtil;
import com.libraryApp.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.logging.Logger;

public class LiveLabsStepDefs {

    // US_02_category.feature:

    String actualCategory;

    @Given("Establish the database connection")
    public void establish_the_database_connection() {

        // Connection is already handled by Hooks with @db annotations
        Logger.getLogger(getClass().getName()).info("DB connection established via Hooks.");

    }
    @When("I execute query to find the most popular book category")
    public void i_execute_query_to_find_the_most_popular_book_category() {

        String query="select bc.name, count(*)\n" +
                "from book_categories bc\n" +
                "        join books b on bc.id=b.book_category_id\n" +
                "        join book_borrow bb on b.id = bb.book_id\n" +
                "group by bc.name\n" +
                "order by count(*) desc;";

        DB_Util.runQuery(query);
        actualCategory = DB_Util.getFirstRowFirstColumn();
        System.out.println("actualCategory = " + actualCategory);


    }
    @Then("verify {string} is the most popular book category.")
    public void verify_is_the_most_popular_book_category(String expectedCategory) {

        Assert.assertEquals(expectedCategory,actualCategory);

        // Database connection is already handled by Hooks with @db annotations
        Logger.getLogger(getClass().getName()).info("Database connection closed via Hooks.");
    }


    // US_03_counting.feature:

    BookPage bookPage=new BookPage();
    String actualBookCount;

    @When("the user gets {string} book count")
    public void the_user_gets_book_count(String categoryName) {

        BrowserUtil.selectByVisibleText(bookPage.mainCategoryElement, categoryName);
        BrowserUtil.waitFor(2);

        String bookDetails = bookPage.bookCount.getText();
        actualBookCount = bookPage.getCount(bookDetails);
        System.out.println("actualBookCount = " + actualBookCount);

    }
    @Then("the {string} book count should be equal with database")
    public void the_book_count_should_be_equal_with_database(String categoryName) {

        String query="select count(*) from books b\n" +
                "                join book_categories bc on bc.id=b.book_category_id\n" +
                "where bc.name= '" + categoryName + "'";

        DB_Util.runQuery(query);

        String expectedBookCount = DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(expectedBookCount,actualBookCount);

    }


}
