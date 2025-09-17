package com.libraryApp.steps;

import com.libraryApp.pages.BookPage;
import com.libraryApp.pages.DashBoardPage;
import com.libraryApp.utility.BrowserUtil;
import com.libraryApp.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class BooksStepDefs {
    BookPage bookPage=new BookPage();
    List<String> actualCategoryList;


    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        new DashBoardPage().navigateModule(moduleName);

    }


    @When("the user gets all book categories in webpage")
    public void the_user_gets_all_book_categories_in_webpage() {
        //potential wait here if tests fail
        actualCategoryList=BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        actualCategoryList.remove(0);
        System.out.println("actualCategoryList = " + actualCategoryList);
    }


    @Then("user should be able to see following categories")
    public void user_should_be_able_to_see_following_categories(List<String> expectedCategoryList) {

        Assert.assertEquals(expectedCategoryList, actualCategoryList);

    }


    @When("I open book {string}")
    public void i_open_book(String bookName) {

        System.out.println("bookName = " + bookName);
        BrowserUtil.waitForClickablility(bookPage.search, 5).sendKeys(bookName);
        BrowserUtil.waitForClickablility(bookPage.editBook(bookName), 5).click();

    }


    @Then("verify book categories must match book categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {

        String query = "select name from book_categories";
        DB_Util.runQuery(query);

        List<String> expectedCategoryList = DB_Util.getColumnDataAsList(1);
        System.out.println("expectedCategoryList = " + expectedCategoryList);

        Assert.assertEquals("Verify category list is matching",actualCategoryList,expectedCategoryList);

    }


    @Then("book information must match the database for {string}")
    public void book_information_must_match_the_database_for(String bookName) {

        BrowserUtil.waitFor(3);

        System.out.println("UI Bookname: "+bookPage.bookName.getAttribute("value"));

        String actualBookName = bookPage.bookName.getAttribute("value");
        String actualAuthorName = bookPage.author.getAttribute("value");
        String actualISBN = bookPage.isbn.getAttribute("value");
        String actualYear = bookPage.year.getAttribute("value");
        String actualDesc = bookPage.description.getAttribute("value");

        System.out.println("actualISBN = " + actualISBN);

        String query = "select name,isbn,year,author,description from books\n" +
                "where name = '"+bookName+"'";

        DB_Util.runQuery(query);
        Map<String, String> bookInfo = DB_Util.getRowMap(1);

        String expectedBookName = bookInfo.get("name");
        String expectedAuthorName =bookInfo.get("author");
        String expectedISBN = bookInfo.get("isbn");
        String expectedYear = bookInfo.get("year");
        String expectedDesc = bookInfo.get("description");

        System.out.println("expectedAuthorName = " + expectedAuthorName);
        System.out.println("expectedISBN = " + expectedISBN);

        Assert.assertEquals(actualBookName,expectedBookName);
        Assert.assertEquals(actualAuthorName,expectedAuthorName);
        Assert.assertEquals(actualISBN,expectedISBN);
        Assert.assertEquals(actualYear,expectedYear);
        Assert.assertEquals(bookPage.description.getAttribute("value"),bookInfo.get("description"));

    }

}
