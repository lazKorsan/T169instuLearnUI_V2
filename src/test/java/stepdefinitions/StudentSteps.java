package stepdefinitions;

import config.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import pages.StudentPage;
import utils.ClickUtils;
import utils.DescriptionUtils;
import utils.ReusableMethods;
import utils.SendKeysUtils;


import java.sql.Driver;
import java.util.List;

import static pages.BasePage.*;

public class StudentSteps {

    WebDriver driver = Hooks.getDriver();
    StudentPage studentPage = new StudentPage(driver);
    private static final Logger logger = LogManager.getLogger(StudentSteps.class);
    public static String aramaSonucuYazisiText = "";
    public static String registeredMail;
    public static String registeredRole;


    @Given("Student kullanicisi {string} sayfasina gider")
    public void student_kullanicisi_sayfasina_gider(String url) {

        url = ConfigReader.getProperty("instuLearn", "chrome");

        driver.get(url);

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = url;

        if (!actualUrl.equals(expectedUrl)) {
            logger.debug("🐞Hata: Beklenen url: " + expectedUrl + " ile actual: " + actualUrl + " aynı değil");
            throw new AssertionError("Hata anaSayfa Yuklenmedi");
        }
        logger.debug("👍Beklenen url: " + expectedUrl + " ile actual: " + actualUrl + " aynı");

    }

    @When("Student kullanicisi title in {string} oldugunu dogrular")
    public void studentKullanicisiTitleInOldugunuDogrular(String expectedTitle) {

        // sayfa basligini alma
        String actualTitle = driver.getTitle();

        // sayfa basligini dogrulama
        Assert.assertEquals("Sayfa başlığı beklenen değerle eşleşmedi!", expectedTitle, actualTitle);

    }

    @When("Student kullanicisi {string} butonunun gorunebilir ve tiklanabilir oldugunu test eder")
    public void studentKullanicisiButonununGorunebilirVeTiklanabilirOldugunuTestEder(String buttonName) {

        //logStepDetails(buttonName);

        studentPage.verifyButtonByName(driver, buttonName);

    }

    @When("Student kullanicisi {string} butonuna tiklar")
    public void studentKullanicisiButonunaTiklar(String buttonName) {

        ClickUtils.smartClick(driver, buttonName, () -> studentPage.getButtonByName(buttonName));

        // studentPage.clickButtonByName(driver, buttonName); buttona page classdan alternatif tıklama

    }

    @When("Student kullanicisi {string} kutusuna {string} yazar")
    public void student_kullanicisi_kutusuna_yazar(String boxName, String keyOrText) {



        // 1. Config'den veya direkt parametreden yazılacak değeri çözümlüyoruz
        String valueToSend = ConfigReader.getProperty(keyOrText, keyOrText);

        // 2. Elementi 'boxName' ile bulup, içine 'valueToSend' değerini gönderiyoruz
        SendKeysUtils.smartSendKeys(driver, boxName, valueToSend, () -> studentPage.getInputByName(boxName));



        // Page listesinde varsa WebElement ile, yoksa Label/Placeholder ismiyle yazar
        //SendKeysUtils.smartSendKeys(driver, boxName, keyOrText, () -> studentPage.getInputByName(boxName));

        // page input enum listesindeki elemente tıklamak
        // studentPage.sendKeysByName(driver, boxName, text);

    }

    @When("Student kullanicisi {string} sayfasinda oldugunu dogrular")
    public void studentKullanicisiSayfasindaOldugunuDogrular(String expectedUrl) {

        //logStepDetails(expectedUrl);

        studentPage.verifyUrl(expectedUrl);

    }

    @When("Student kullanicisi profil isminin {string} oldugunu dogrular")
    public void studentKullanicisiProfilIsmininOldugunuDogrular(String expectedName) {

        //logStepDetails(expectedName);

        String actualName=studentPage.studentName.getText();

        Assert.assertEquals("ExpectedName ile actualName aynı değil", expectedName, actualName);

    }

    @When("Student kullanicisi {string} butonunun gorunurlugu ile cikis yapildigini dogrular")
    public void studentKullanicisiButonununGorunurluguIleCikisYapildiginiDogrular(String buttonName) {

        //logStepDetails(buttonName);

        studentPage.verifyButtonByName(driver, buttonName);

    }

    @When("Student kullanicisi {string} ve {string} ile siteye giris yapar")
    public void studentKullanicisiVeIleSiteyeGirisYapar(String mail, String password) {

        //logStepDetails(mail, password);

        studentPage.loginMethod(driver, mail, password);

        logger.error("Student kullanicisi {} ve {} ile siteye giris yapar", mail, password);

    }

    @When("Student kullanicisi blog sayisini consola yazdirir")
    public void studentKullanicisiBlogSayisiniConsolaYazdirir() {

        //logStepDetails();
        String actualProductCaount= studentPage.productCountButton.getText();
        logger.info("Bulunan toplam Blog sayisi : " + actualProductCaount);
        //logStepDetails(actualProductCaount);
    }

    @When("bekle {int} saniye")
    public void bekleSaniye(int bekleme ) {

        ReusableMethods.bekle(bekleme);
    }

    @When("Student kullanicisi {string} arama sonucu yazisini consola yazdirir")
    public void studentKullanicisiAramaSonucuYazisiniConsolaYazdirir(String searchText) {

        WebElement aramaSonucuElement = driver.findElement(By.xpath("//h1[@class='text-white font-30 white-space-pre-wrap']"));
        aramaSonucuYazisiText = aramaSonucuElement.getText().trim();


        logger.info("📋 Ekrandaki "+searchText+" Arama Sonucu Yazısı: " + aramaSonucuYazisiText);
        System.out.println("📋 Ekrandaki "+searchText+" Arama Sonucu Yazısı: " + aramaSonucuYazisiText);
    }

    @When("Student kullanicisi {string} arama sonucu yazi ile course sayisini dogrular")
    public void studentKullanicisiAramaSonucuYaziIleCourseSayisiniDogrular(String searchText) {

        logStepDetails(searchText);

        // 1. Ekrandaki kart/kurs sayılarını liste olarak al
        List<WebElement> bulunanUrunListesi = driver.findElements(By.xpath("//div/span[@class='badge badge-primary']"));
        int actualCourseCount = bulunanUrunListesi.size();

        // 2. Metin içerisindeki ilk sayısal değeri RegEx (Regex: \\d+) ile çek
        // Örneğin: "18 Results found for \"Math\"" -> "18"
        String extractedNumberStr = aramaSonucuYazisiText.replaceAll("[^0-9]", "");

        Assert.assertFalse("❌ Arama sonucu yazısında herhangi bir sayı bulunamadı!", extractedNumberStr.isEmpty());

        int expectedCourseCount = Integer.parseInt(extractedNumberStr);

        // 3. Loglama yap
        logger.info("🔢 Başlıkta Yazan Sayı: " + expectedCourseCount + " | Sayfadaki Gerçek Kurs Sayısı: " + actualCourseCount);

        // 4. Doğrulama (Assertion)
        Assert.assertEquals("❌ Başlıktaki sonuç sayısı ile sayfadaki kurs sayısı eşleşmiyor!",
                expectedCourseCount, actualCourseCount);

        logger.info("✅ Arama sonucu sayısı ile listelenen kurs sayısı başarıyla doğrulandı.");
    }

    @When("Student kullanicisi {string} yazi elementinin gorunur oldugunu test eder")
    public void studentKullanicisiYaziElementininGorunurOldugunuTestEder(String textElementName) {

        //logStepDetails(textElementName);

        studentPage.verifyTextElement(driver, textElementName);
    }

    @When("Student kullanicisi register sayfasinda resim elementinin gorunur oldugunu test eder")
    public void studentKullanicisiRegisterSayfasindaResimElementininGorunurOldugunuTestEder() {

        //logStepDetails();

        WebElement resimElementi = driver.findElement(By.xpath("//div/div/img"));
        Assert.assertTrue(resimElementi.isDisplayed());
        logger.info(ANSI_GREEN +"Register sayfasinda resim elementi gorunur"+ ANSI_RESET);

    }


    @When("Student kullanicisi {string} ve {string} ile siteye kayt olur")
    public void studentKullanicisiVeIleSiteyeKaytOlur(String userName, String password) {

        //logStepDetails(userName, password);

        // Page class'tan dönen diziye erişim
        String[] accountData = studentPage.registerMethod(driver, userName, password);

        registeredMail = accountData[0];
        registeredRole = accountData[1];

        System.out.println("==========================================");
        System.out.println("📌 OLUŞTURULAN HESAP BİLGİLERİ:");
        System.out.println("👤 İsim     : " + accountData[2]);
        System.out.println("🎭 Rol      : " + accountData[1]);
        System.out.println("📧 E-Posta  : " + accountData[0]);
        System.out.println("🔑 Şifre    : " + accountData[3]);
        System.out.println("==========================================");
    }

    @When("Student kullanicisi profil ismi {string} ile hesap olusturuldugunu dogrular")
    public void studentKullanicisiProfilIsmiIleHesapOlusturuldugunuDogrular(String expectedName) {

        logStepDetails(expectedName);
        expectedName = ConfigReader.getProperty("userName", "chrome");

        WebElement actualNameField = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div[2]/div[1]/span"));

        Actions actions = new Actions(driver);
        actions.moveToElement(actualNameField).pause(300).perform();
        String actualName = actualNameField.getText();

        Assert.assertEquals(expectedName, actualName);
        logger.info("Test sonucunda olusan hesap bilgisi: "+ expectedName);

    }

    @When("Student kullanicisi register sayfasinda Sign Up formunun gorunur oldugunu test eder")
    public void studentKullanicisiRegisterSayfasindaSignUpFormununGorunurOldugunuTestEder() {

        //logStepDetails();

        WebElement registerForm= driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/form"));

        DescriptionUtils.inspect(driver, "/html/body/div[2]/div[3]/div/div[2]/div/form");

    }

    @When("Student kullanicisi {string} buttonu ile sayfanın yenilendiğini test eder")
    public void studentKullanicisiButtonuIleSayfanınYenilendiginiTestEder(String buttonName) {


        studentPage.verifyPageRefreshMethod(driver, buttonName);


    }



}
