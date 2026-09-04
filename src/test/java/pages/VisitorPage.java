package pages;

import config.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VisitorPage extends BasePage {


    private static final Logger logger = LogManager.getLogger(VisitorPage.class);

    public VisitorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href=\"/login\"]")
    public WebElement loginButton;

    @FindBy(xpath = "(//button[@type=\"submit\"])[2]")
    public WebElement submitButton;

    @FindBy(xpath = "//input[@id=\"email\"]")
    public WebElement emailBox;

    @FindBy(xpath = "//input[@id=\"password\"]")
    public WebElement passwordBox;


    // todo enumList
    public WebElement getElementByName(String elementName){
        return switch (elementName.toLowerCase().trim()){
            case "login" -> loginButton;
            case "submit" -> submitButton;
            case "email" -> emailBox;
            case "password" -> passwordBox;
            default -> {
                logger.error("Geçersiz element ismi girildi: " + elementName);
                throw new IllegalArgumentException("Geçersiz element ismi:"+ elementName);

            }
        };
    }


       public WebElement getButtonByName(String elementName){
                     return switch (elementName.toLowerCase().trim()){
                         case "login" -> loginButton;
                         case "submit" -> submitButton;
                         case "email" -> emailBox;
                         case "password" -> passwordBox;


                         default -> {
                             logger.error("Geçersiz element ismi girildi: " + elementName);
                             throw new IllegalArgumentException("Geçersiz element ismi:"+ elementName);

                         }
                     };
                 }

    public boolean verifyUrl(String expectedTargetParam) {
        // 1. Null-Safe Değer Atama
        String expectedTarget = expectedTargetParam;

        // Eğer parametre tam URL değilse, önce Config'e bakılır
        if (expectedTargetParam != null && !expectedTargetParam.startsWith("http://") && !expectedTargetParam.startsWith("https://")) {
            try {
                String configValue = ConfigReader.getProperty(expectedTargetParam, "keyOrText");
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

}
