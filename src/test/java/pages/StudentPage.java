package pages;

import config.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ClickUtils;
import utils.SendKeysUtils;

import java.sql.Driver;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class StudentPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(StudentPage.class);

    public StudentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//a[@href=\"/login\"]")
    public WebElement loginButton;

    @FindBy(xpath = "//a[@href=\"/register\"]")
    public WebElement registerButton;

    @FindBy(xpath = "//div/div/ul/li/div/ul/li")
    public WebElement categoriesButton;

    @FindBy(xpath = "(//a[@href=\"/\"])[2]")
    public WebElement homeButton;

    @FindBy(xpath = "//li[@class=\"nav-item\"][2]")
    public WebElement coursesButton;

    @FindBy(xpath = "//li[@class=\"nav-item\"][3]")
    public WebElement instructorsButton;

    @FindBy(xpath = "//li[@class=\"nav-item\"][4]")
    public WebElement storeButton;

    @FindBy(xpath = "//li[@class=\"nav-item\"][5]")
    public WebElement blogButton;

    @FindBy(xpath = "//h1[@class=\"text-secondary font-weight-bold\"]")
    public WebElement transformYourFutureText;

    @FindBy(xpath = "//div[@class='container']/div/div/div/form/button")
    public WebElement submitButton;

    @FindBy(xpath = "//h3[@class=\"font-16 font-weight-bold text-center\"]")
    public WebElement studentName;

    @FindBy(xpath = "//div/nav/div/div/div/a")
    public WebElement startLearningButton;

    @FindBy(xpath = "//form[@id='filtersForm']//label[@for='upcoming']")
    public WebElement upcomingButton;

    @FindBy(xpath = "//form[@id='filtersForm']//label[@for='free']")
    public WebElement freeButton;

    @FindBy(xpath = "//form[@id='filtersForm']//label[@for='discount']")
    public WebElement disCountButton;

    @FindBy(xpath = "//form[@id='filtersForm']//label[@for='download']")
    public WebElement downloadButton;
    // //form[@id='filtersForm']//label[@for='available_for_meetings']
    @FindBy(xpath = "//form[@id='filtersForm']//label[@for='available_for_meetings']")
    public WebElement availableForMeetingsButton;

    @FindBy(xpath = "//form[@id='filtersForm']//label[@for='free_meetings']")
    public WebElement freeMeetingsButton;

    @FindBy(xpath = "//form[@id='filtersForm']//label[@for='free_shipping']")
    public WebElement freeShippingButton;

    @FindBy(xpath = "//div/section/div/div/div/div/span")
    public WebElement productCountButton;

    @FindBy(xpath = "//div/div/div/div/div/button")
    public WebElement cardButton;

    @FindBy(xpath = "//div[@class='top-navbar d-flex border-bottom']//button[@id='navbarNotification']")
    public WebElement notificationButton;

    @FindBy(xpath = "//img[@src=\"/store/1/default_images/website-logo.png\"]")
    public WebElement logoButton;

    @FindBy(xpath = "(//button[@type=\"submit\"])[1]")
    public WebElement searchButton;

    @FindBy(xpath = "")
    public WebElement transformTextField;


    // =======================================================================

    @FindBy(xpath = "//input[@name='email' or @type='email']")
    public WebElement emailInput;

    @FindBy(xpath = "//input[@name='password' or @type='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//input[@type='search' or @name='search']")
    public WebElement searchInput;

    @FindBy(xpath = "//input[@name='firstName']")
    public WebElement firstNameInput;

    @FindBy(xpath = "//input[@name='lastName']")
    public WebElement lastNameInput;



    public WebElement getInputByName(String boxName) {
        if (boxName == null) {
            throw new IllegalArgumentException("Kutu ismi (boxName) null olamaz!");
        }

        return switch (boxName.toLowerCase().trim()) {
            case "email", "e-posta", "eposta"           -> emailInput;
            case "password", "şifre", "sifre"           -> passwordInput;
            case "search", "ara", "arama"               -> searchInput;
            case "firstname", "first name", "ad", "isim"-> firstNameInput;
            case "lastname", "last name", "soyad"       -> lastNameInput;
            default -> {
                logger.warn("⚠️ Page class içinde '{}' için özel bir WebElement bulunamadı.", boxName);
                throw new IllegalArgumentException("Geçersiz veya tanımlanmamış input ismi: " + boxName);
            }
        };
    }

    // ======================================================================================



    public WebElement getButtonByName(String buttonName) {
        return switch (buttonName.toLowerCase().trim()) {
            case "home"        -> homeButton;
            case "courses"     -> coursesButton;
            case "instructors" -> instructorsButton;
            case "store"       -> storeButton;
            case "blog"        -> blogButton;
            case "categories"  -> categoriesButton;
            case "submit"     -> submitButton;
            case "start learning"-> startLearningButton;
            case "upcoming"-> upcomingButton;
            case "free"-> freeButton;
            case "discount"-> disCountButton;
            case "download"-> downloadButton;
            case "available for meetings"-> availableForMeetingsButton;
            case "free meetings"-> freeMeetingsButton;
            case "free shipping"-> freeShippingButton;
            case "card" -> cardButton;
            case "notification"-> notificationButton;
            case "logo"-> logoButton;
            case "search"-> searchButton;
            case "login"-> loginButton;
            case "register"-> registerButton;
            default -> {
                logger.error("❌ Geçersiz buton ismi girildi: " + buttonName);
                throw new IllegalArgumentException("Geçersiz buton ismi: " + buttonName);
            }
        };
    }



    public void loginMethod(WebDriver driver, String email, String password) {
        email= ConfigReader.getProperty(email, "chrome");
        password= ConfigReader.getProperty(password, "chrome");

        ClickUtils.clickByText(driver, "Login");

        SendKeysUtils.sendKeysByText(driver, "Email:", email);
        SendKeysUtils.sendKeysByText(driver, "Password:", password);

        ClickUtils.clickElement(driver, submitButton);

    }

    // Return tipini String[] olarak değiştiriyoruz: [mail, role, name, password]
    public String[] registerMethod(WebDriver driver, String name, String password) {

        name = ConfigReader.getProperty("userName", "chrome");
        password = ConfigReader.getProperty("password", "chrome");

        ClickUtils.clickByText(driver, "Register");
        verifyUrl("register");

        WebElement studentRoleButton = driver.findElement(By.xpath("//div[2]/div[3]/div/div[2]/div/form/div[1]/div/div[1]/label"));
        WebElement instructorRoleButton = driver.findElement(By.xpath("//div[2]/div[3]/div/div[2]/div/form/div[1]/div/div[2]/label"));
        WebElement organizationRoleButton = driver.findElement(By.xpath("//div[2]/div[3]/div/div[2]/div/form/div[1]/div/div[3]/label"));

        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;

        String role;
        if (randomNumber <= 30) {
            role = "student";
            ClickUtils.clickElement(driver, studentRoleButton);
        } else if (randomNumber <= 90) {
            role = "teacher";
            ClickUtils.clickElement(driver, instructorRoleButton);
        } else {
            role = "organizator";
            ClickUtils.clickElement(driver, organizationRoleButton);
        }

        String mail = name + "." + role + "." + LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss")) + "@instuLearn.com";

        WebElement emailBox = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/form/div[2]/input"));
        WebElement fullNameBox = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/form/div[3]/input"));
        WebElement passwordBox = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/form/div[4]/input"));
        WebElement comfirmPasswordBox = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/form/div[5]/input"));
        WebElement checkBox = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/form/div[8]/label"));
        WebElement submitButton = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/form/button"));

        SendKeysUtils.sendKeysToElement(driver, emailBox, mail);
        SendKeysUtils.sendKeysToElement(driver, fullNameBox, name);
        SendKeysUtils.sendKeysToElement(driver, passwordBox, password);
        SendKeysUtils.sendKeysToElement(driver, comfirmPasswordBox, password);

        ClickUtils.scrollToElement(driver, checkBox);
        ClickUtils.clickWithJS(driver, checkBox);
        ClickUtils.clickElement(driver, submitButton);

        ClickUtils.waitForPageLoad(driver);
        String expectedURL = "https://qa.instulearn.com/";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(expectedURL, actualURL);

        // Üretilen bilgileri dizi olarak donduruyoruz
        return new String[]{mail, role, name, password};
    }

    public void verifyButtonByName(WebDriver driver, String buttonName) {
        WebElement element = getButtonByName(buttonName);
        verifyButton(driver, element, buttonName);
    }

    // todo enum list ile tiklama için kullanıiabilir  ornek kullanimi step içinde var
    public void clickButtonByName(WebDriver driver, String buttonName) {
        try {
            // 1. Önce Page Class içindeki tanımlı (Findby) elementler listesinde var mı bakılır
            WebElement element = getButtonByName(buttonName);
            logger.info("🎯 Element Page Class içinde bulundu, ClickUtils ile tıklanıyor: " + buttonName);
            ClickUtils.clickElement(driver, element);

        } catch (IllegalArgumentException e) {
            // 2. Eğer Page Class içindeki 'switch-case' veya listede yoksa metin (text) ile aranır
            logger.warn("⚠️ '{}' Page Class listesinde bulunamadı! Text ile dinamik tıklama deneniyor...", buttonName);
            ClickUtils.clickByText(driver, buttonName);
        }
    }

    // todo enum input list ile kullanılabilir  ornek kullanimi step içinde var.
    public void sendKeysByName(WebDriver driver, String boxName, String text) {
        try {
            // 1. Önce Page Class içindeki tanımlı (FindBy) input elementleri listesinde var mı bakılır
            WebElement element = getInputByName(boxName);
            logger.info("🎯 Input alanı Page Class içinde bulundu, SendKeysUtils ile metin gönderiliyor: " + boxName);
            SendKeysUtils.sendKeysToElement(driver, element, text);

        } catch (IllegalArgumentException e) {
            // 2. Eğer Page Class içindeki 'switch-case' yapısında yoksa Label/Placeholder ismiyle aranır
            logger.warn("⚠️ '{}' Page Class listesinde bulunamadı! Label/Placeholder ismiyle dinamik metin gönderme deneniyor...", boxName);
            SendKeysUtils.sendKeysByText(driver, boxName, text);
        }
    }

    public void verifyButton(WebDriver driver, WebElement element, String buttonName) {
        try {
            // Butonun görünürlüğünü test et
            logger.info("=== {} görünürlük ve tıklanabilirlik testi başlıyor ===", buttonName);

            boolean isDisplayed = element.isDisplayed();
            if (isDisplayed) {
                logger.info("✓ {} sayfada GÖRÜNÜYOR", buttonName);
                System.out.println("✓ " + buttonName + " sayfada görünüyor");
            } else {
                logger.error("✗ {} sayfada GÖRÜNMÜYOR", buttonName);
                Assert.fail(buttonName + " sayfada görünmüyor!");
            }

            // Butonun tıklanabilirliğini test et (enable mı?)
            boolean isEnabled = element.isEnabled();
            if (isEnabled) {
                logger.info("✓ {} TIKLANABİLİR durumda", buttonName);
                System.out.println("✓ " + buttonName + " tıklanabilir durumda");
            } else {
                logger.error("✗ {} TIKLANAMAZ durumda (disabled)", buttonName);

                Assert.fail(buttonName+" tıklanmaz durumda");
            }

            // Butonun boyutlarının pozitif olduğunu kontrol et (opsiyonel)
            int buttonWidth = element.getSize().getWidth();
            int buttonHeight = element.getSize().getHeight();

            if (buttonWidth > 0 && buttonHeight > 0) {
                logger.info("✓ {} boyutları: {}x{} piksel", buttonName, buttonWidth, buttonHeight);
            } else {
                logger.warn("⚠ {} boyutları sıfır veya negatif: {}x{}", buttonName, buttonWidth, buttonHeight);
            }

            // WebDriverWait ile de tıklanabilirliği doğrula (daha robust test)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.info("✓ {} WebDriverWait ile de tıklanabilir olarak doğrulandı", buttonName);

            // Butonun metnini/logosunu kontrol et (opsiyonel)
            String buttonText = element.getText();
            if (buttonText != null && !buttonText.isEmpty()) {
                logger.info("✓ {} üzerindeki metin: '{}'", buttonName, buttonText);
            }

            logger.info("=== {} testi BAŞARIYLA tamamlandı ===", buttonName);
            System.out.println("=== " + buttonName + " testi BAŞARIYLA tamamlandı ===");

        } catch (Exception e) {
            logger.error("!!! {} testi sırasında HATA oluştu: {}", buttonName, e.getMessage());
            logger.error("Hata detayı: ", e);
            Assert.fail(buttonName + " testi başarısız: " + e.getMessage());
        }

    }

    public boolean verifyUrl(String expectedTargetParam) {
        // 1. Null-Safe Değer Atama
        String expectedTarget = expectedTargetParam;

        // Eğer parametre tam URL değilse, önce Config'e bakılır
        if (expectedTargetParam != null && !expectedTargetParam.startsWith("http://") && !expectedTargetParam.startsWith("https://")) {
            try {
                String configValue = ConfigReader.getProperty(expectedTargetParam, "chrome");
                if (configValue != null && !configValue.isEmpty()) {
                    expectedTarget = configValue;
                }
            } catch (Exception e) {
                expectedTarget = expectedTargetParam;
            }
        }

        if (expectedTarget == null) {
            expectedTarget = expectedTargetParam;
        }

        String normalizedTarget = expectedTarget.toLowerCase().trim();

        // 2. Case-Insensitive (Büyük/Küçük harf duyarsız) Dinamik Bekleme
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(webDriver -> {
                String currentUrl = webDriver.getCurrentUrl();
                return currentUrl != null && currentUrl.toLowerCase().contains(normalizedTarget);
            });
        } catch (TimeoutException e) {
            logger.warn(ANSI_YELLOW_BOLD + "⚠️ Timeout: URL beklenen parçacığı içermiyor! ('" + expectedTarget + "')" + ANSI_RESET);
        }

        // 3. Eşleşme Kontrolü
        String actualUrl = driver.getCurrentUrl();
        boolean isVerified = actualUrl.toLowerCase().contains(normalizedTarget) ||
                actualUrl.equalsIgnoreCase(expectedTarget);

        if (isVerified) {
            logger.info(greenBold("✅ URL başarıyla doğrulandı: ") + cyan(actualUrl));
        } else {
            String errorMsg = String.format("❌ URL uyuşmuyor! Beklenen Değer/Arama/Endpoint: [%s] | Gerçekleşen URL: [%s]", expectedTarget, actualUrl);
            logger.error(redBold(errorMsg));
        }

        return isVerified;
    }


    // todo text dogrulama methodları baslangıcı
    public WebElement getTextFieldByName(String textElement) {
        if (textElement == null) {
            throw new IllegalArgumentException("Yazı elementi ismi null olamaz!");
        }

        String normalizedText = textElement.toLowerCase().trim();

        // Hem kısa key'leri hem de feature dosyasından gelen tam metinleri kapsayacak şekilde switch-case
        return switch (normalizedText) {
            case "transform", "transform your future with instulearn...", "transform your future"
                    -> transformYourFutureText;
            case "email"
                    -> emailInput;
            default -> {
                logger.error("❌ Geçersiz text ismi: " + textElement);
                throw new IllegalArgumentException("Geçersiz text ismi: " + textElement);
            }
        };
    }

    public void verifyTextField(WebDriver driver, WebElement element, String textElementName) {
        try {
            logger.info("=== {} görünürlük testi başlıyor ===", textElementName);

            // 1. Explicit Wait ile elementin görünürlüğünü doğrula
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));

            boolean isDisplayed = element.isDisplayed();
            if (isDisplayed) {
                logger.info("✓ {} sayfada GÖRÜNYOR", textElementName);
                System.out.println("✓ " + textElementName + " sayfada görünüyor");
            } else {
                logger.error("✗ {} sayfada GÖRÜNMÜYOR", textElementName);
                Assert.fail(textElementName + " sayfada görünmüyor!");
            }

            // 2. Elementin boyut kontrolü
            int elementWidth = element.getSize().getWidth();
            int elementHeight = element.getSize().getHeight();

            if (elementWidth > 0 && elementHeight > 0) {
                logger.info("✓ {} boyutları: {}x{} piksel", textElementName, elementWidth, elementHeight);
            } else {
                logger.warn("⚠ {} boyutları sıfır veya negatif: {}x{}", textElementName, elementWidth, elementHeight);
            }

            // 3. Element üzerindeki metin bilgisi (Log için)
            String elementText = element.getText();
            if (elementText != null && !elementText.isEmpty()) {
                logger.info("✓ {} üzerindeki metin: '{}'", textElementName, elementText.trim());
            }

            logger.info("=== {} testi BAŞARIYLA tamamlandı ===", textElementName);
            System.out.println("=== " + textElementName + " testi BAŞARIYLA tamamlandı ===");

        } catch (Exception e) {
            logger.error("!!! {} testi sırasında HATA oluştu: {}", textElementName, e.getMessage());
            logger.error("Hata detayı: ", e);
            Assert.fail(textElementName + " testi başarısız: " + e.getMessage());
        }
    }

    public void verifyTextElement(WebDriver driver, String textElementName) {
        try {
            // 1. Önce Page Class içindeki switch-case listesinde var mı bakılır
            WebElement element = getTextFieldByName(textElementName);
            logger.info("🎯 Yazı elementi Page Class içinde bulundu: " + textElementName);
            verifyTextField(driver, element, textElementName);

        } catch (IllegalArgumentException e) {
            // 2. Eğer Page Class içindeki tanımlarda yoksa, metin (text) üzerinden dinamik XPath ile aranır
            logger.warn("⚠️ '{}' Page Class listesinde bulunamadı! Dinamik XPath ile görünürlük aranıyor...", textElementName);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            String xpathExpression = String.format("//*[contains(normalize-space(text()), '%s')]", textElementName);

            try {
                WebElement dynamicElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
                Assert.assertTrue("Dinamik aranan yazı elementi görünür değil!", dynamicElement.isDisplayed());
                logger.info("✅ '{}' metni dinamik XPath ile sayfada başarıyla doğrulandı.", textElementName);
            } catch (TimeoutException te) {
                logger.error("❌ '{}' metni sayfada bulunamadı veya görünür değil!", textElementName);
                Assert.fail("Yazı elementi sayfada bulunamadı: " + textElementName);
            }
        }
    }

    // todo text elementi dogrulama adımları sonu

    public void verifyPageRefreshMethod( WebDriver driver, String buttonName) {

        // 1. Dinamik veya Home buton locator'ı (Driver nesnenizin projenizdeki Driver class'ından çağrıldığından emin olun)
        By homeButtonLocator = By.xpath("(//a[@href=\"/\"])[2]");

        // Gerekirse bekleme (wait) eklenerek eleman bulunur
        WebElement homeButton = driver.findElement(homeButtonLocator);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 2. Butona belirgin bir highlight stili verin
        js.executeScript(
                "arguments[0].setAttribute('style', 'outline: 3px solid red !important; background-color: yellow !important;');",
                homeButton
        );

        // 3. Highlight yapıldığını doğrulayın
        String styleBefore = homeButton.getAttribute("style");
        System.out.println(buttonName + " Butonuna Tıklama Öncesi Style: " + styleBefore);

        // 4. Butona tıklayın
        homeButton.click();

        // 5. Yenileme sonrası stilin temizlendiğini veya DOM'un yenilendiğini kontrol edin
        try {
            WebElement refreshedButton = driver.findElement(homeButtonLocator);
            String styleAfter = refreshedButton.getAttribute("style");
            System.out.println("Tıklama Sonrası Style: " + styleAfter);

            // Style içerisinde kırmızı eklediğimiz outline yoksa sayfa yenilenmiştir
            boolean isRefreshed = styleAfter == null || !styleAfter.contains("outline: 3px solid red");
            Assert.assertTrue("Sayfa yenilenmedi, highlight stili halen duruyor!", isRefreshed);
            System.out.println("Rapor: " + buttonName + " ile sayfa başarıyla yenilendi.");

        } catch (StaleElementReferenceException e) {
            // Eski eleman referansı yok olduysa sayfa kesinlikle yenilenmiştir (En güvenli doğrulama)
            System.out.println("Rapor: DOM tamamen yenilendi (Stale Element), " + buttonName + " ile sayfa refresh oldu.");
        }
    }


// ========================================================================================
}
