package stepdefinitions;

import config.ConfigReader;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import pages.BasePage;
import pages.VisitorPage;

import static java.awt.Color.blue;
import static java.awt.Color.red;
import static pages.BasePage.blue;

public class VisitorSteps {

    WebDriver driver = Hooks.getDriver();
    VisitorPage visitorPage = new VisitorPage(driver);
    private static final Logger logger = LogManager.getLogger(VisitorSteps.class);


    @Given("Visitor kullanicisi {string} sayfasina gider")
    public void visitor_kullanicisi_sayfasina_gider(String homePage) {

        homePage=ConfigReader.getProperty("url", "keyOrText");
        //logger.info("Visitor kullanicisi {} sayfasina gider", homePage);

        driver.get(homePage);

        Assert.assertEquals(homePage,driver.getCurrentUrl());


    }
    @Given("Visitor kullanicisi {string} butonuna tiklar")
    public void visitor_kullanicisi_butonuna_tiklar(String buttonName) {

       // logger.info("Visitor kullanicisi {} butonuna tiklar", buttonName);

        visitorPage.getElementByName(buttonName).click();

    }
    @Given("Visitor kullanicisi {string} sayfasinda oldugunu dogrular")
    public void visitor_kullanicisi_sayfasinda_oldugunu_dogrular(String expectedUrl) {

        //logger.info("Visitor kullanicisi {} sayfasinda oldugunu dogrular", expectedUrl);

        visitorPage.verifyUrl(expectedUrl);

        logger.error("Visitor kullanicisi {} sayfasinda oldugunu dogrular", expectedUrl);

    }
    @Given("Visitor kullanicisi {string} kutusuna {string} yazar")
    public void visitor_kullanicisi_kutusuna_yazar(String boxName, String keyOrText) {

        String valueToSend = ConfigReader.getProperty(keyOrText, keyOrText);
        visitorPage.getElementByName(boxName).sendKeys(valueToSend);
        //  * Visitor kullanicisi "Search" kutusuna "Math" yazar

    }
    @Given("Visitor kullanicisi {string} uzantisi ile sayfaya giris yapildigini dogrular")
    public void visitor_kullanicisi_uzantisi_ile_sayfaya_giris_yapildigini_dogrular(String expecteduRL) {

        //logger.info("Visitor kullanicisi {} uzantisi ile sayfaya giris yapildigini dogrular", expecteduRL);
        visitorPage.verifyUrl(expecteduRL);

    }

    @Given("Enum kullanicisi {string} sayfasina gider")
    public void enum_kullanicisi_sayfasina_gider(String homePage) {

        logger.info("Enum kullanicisi {} sayfasina gider", homePage);
        homePage=ConfigReader.getProperty("url", "chrome");
        driver.get(homePage);
        Assert.assertEquals(homePage,driver.getCurrentUrl());
        logger.error("Enum kullanicisi {} sayfasi acilmadi", homePage);



    }
    @Given("Enum kullanicisi {string} buttona basar")
    public void enum_kullanicisi_buttona_basar(String buttonName) {

        logger.info("Enum kullanicisi {} buttona basar", buttonName);
        visitorPage.getButtonByName(buttonName).click();
        logger.error("Enum kullanicisi {} buttona basar", buttonName);

    }

    @Given("Customer kullanicisi consola {string} yazdirir")
    public void customer_kullanicisi_consola_yazdirir(String keyOrText) {

        String valueToSend = ConfigReader.getProperty(keyOrText, keyOrText);

        System.out.println(BasePage.blue("Customer kullanicisi consola ")+ BasePage.redBold(valueToSend)+BasePage.blue(" yazdirir"));



    }




}
