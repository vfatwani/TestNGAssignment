package com.wiki.tests;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class WikiPageTest extends BaseTests {
    List<String> contentList, headerList;
    @Test(priority = 1)
    public void verifyAllTheHeadingNamesListedInContentBox(){
        // Given
        reportUtils.createATestCase("Tc) the headings listed in the `Contents` box are used as headings on the page");
        reportUtils.addTestLogs(Status.INFO,"Start verifying the list in contents box are used as heading on the page ");

        // When
        contentList = wikipediaPage.getAllTheHeading();
        headerList = wikipediaPage.getAllHeaderList();
        reportUtils.addTestLogs(Status.INFO,"Comparing both list");

             // Then
        assertThat(contentList).isEqualTo(headerList);
    }

    @Test(priority = 2)
    public void headingListedInContentBoxHaveFunctioningHyperlinks(){
        // Given
        reportUtils.createATestCase("Tc) The headings listed in the `Contents` box have functioning hyperlinks");
        reportUtils.addTestLogs(Status.INFO,"Start verifying the headings listed in the `Contents` box have functioning hyperlinks");
        int expectedResponseResult = Integer.parseInt(configProperty.getProperty("responseCode"));

        // when
       List<Integer> actualResponseResult = wikipediaPage.checkFunctioningHyperLink();

       //Then
        reportUtils.addTestLogs(Status.INFO,"Veirify all the response code of all the hyper link are equal to 200");
       for(Integer rescode:actualResponseResult){
           reportUtils.addTestLogs(Status.FAIL.INFO,"Hyperlink status "+rescode);
           assertTrue(rescode.equals(expectedResponseResult),"link is broken");
       }

    }

    @Test(priority = 3)
    public void verifyPersonifiedzconceptsNikeHasAPopup(){
        // Given
        reportUtils.createATestCase("Tc) in the _Personified concepts_, `Nike` has a popup that contains text");
        reportUtils.addTestLogs(Status.INFO,"in the _Personified concepts_, `Nike` has a popup that contains text");
        String expectedText = configProperty.getProperty("popupNikeText");

        // When
        String actualText = wikipediaPage.switchToPopUp();

        // Then
        reportUtils.addTestLogs(Status.INFO,"Comparing the text");
        assertThat(actualText).isEqualTo(expectedText);
    }

    @Test(priority = 4)
    public void verifyInThePersonifiedConceptsClickOnNikeDisplaysAFamilyTree(){
        // Given
        reportUtils.createATestCase("Tc) In the _Personified concepts_, if you click on `Nike`, it takes you to a page that displays a family\n" +
                "tree");
        reportUtils.addTestLogs(Status.INFO,"Start verifying click on Nike takes you to a page that displays Family tree");
        String actualText =configProperty.getProperty("displayFamilyTreeText");

        //When
        wikipediaPage.clickOnNike();
        String expectedText=wikipediaPage.dispalysFamilyTree();

        //Then
        reportUtils.addTestLogs(Status.INFO,"Family tree display.");
        assertThat(wikipediaPage.displayOfFamilyTree()).isTrue();
        assertThat(actualText).isEqualTo(expectedText);
    }
}
