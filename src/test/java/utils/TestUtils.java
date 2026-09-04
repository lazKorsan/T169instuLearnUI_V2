package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestUtils {

    private static final Logger logger = LogManager.getLogger(TestUtils.class);


    private void fail(String message) {
        logger.error(message);
        throw new AssertionError(message);
    }

    private void assertTrue(boolean condition, String message) {
        if (!condition) {
            fail(message);
        }
    }

    private void assertEquals(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            fail(message + " Beklenen: " + expected + " | Gerçek: " + actual);
        }
    }


    public void verifyEndpoint(WebDriver driver, String expectedEndPoint) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains(expectedEndPoint.toLowerCase()));
        } catch (TimeoutException e) {
            logger.info("Timeout: URL beklenen sürede güncellenmedi");
        }

        String actualUrl = driver.getCurrentUrl();

        if (actualUrl.toLowerCase().contains(expectedEndPoint.toLowerCase())) {
            logger.info("Url doğrulandı: " + actualUrl);
        } else {
            logger.error("URL uyuşmuyor! Beklenen: " + expectedEndPoint + " | Gerçek: " + actualUrl);
            fail("URL uyuşmuyor! Beklenen: " + expectedEndPoint + " | Gerçek: " + actualUrl);
        }
    }


    public void verifyButton(WebDriver driver, WebElement element, String buttonName) {
        try {
            logger.info("=== {} görünürlük ve tıklanabilirlik testi başlıyor ===", buttonName);

            boolean isDisplayed = element.isDisplayed();
            if (isDisplayed) {
                logger.info("✓ {} sayfada GÖRÜNÜYOR", buttonName);
                System.out.println("✓ " + buttonName + " sayfada görünüyor");
            } else {
                logger.error("✗ {} sayfada GÖRÜNMÜYOR", buttonName);
                fail(buttonName + " sayfada görünmüyor!");
            }

            boolean isEnabled = element.isEnabled();
            if (isEnabled) {
                logger.info("✓ {} TIKLANABİLİR durumda", buttonName);
                System.out.println("✓ " + buttonName + " tıklanabilir durumda");
            } else {
                logger.error("✗ {} TIKLANAMAZ durumda (disabled)", buttonName);
                fail(buttonName + " tıklanamaz durumda!");
            }

            int buttonWidth = element.getSize().getWidth();
            int buttonHeight = element.getSize().getHeight();

            if (buttonWidth > 0 && buttonHeight > 0) {
                logger.info("✓ {} boyutları: {}x{} piksel", buttonName, buttonWidth, buttonHeight);
            } else {
                logger.warn("⚠ {} boyutları sıfır veya negatif: {}x{}", buttonName, buttonWidth, buttonHeight);
            }

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.info("✓ {} WebDriverWait ile de tıklanabilir olarak doğrulandı", buttonName);

            String buttonText = element.getText();
            if (buttonText != null && !buttonText.isEmpty()) {
                logger.info("✓ {} üzerindeki metin: '{}'", buttonName, buttonText);
            }

            logger.info("=== {} testi BAŞARIYLA tamamlandı ===", buttonName);
            System.out.println("=== " + buttonName + " testi BAŞARIYLA tamamlandı ===");

        } catch (Exception e) {
            logger.error("!!! {} testi sırasında HATA oluştu: {}", buttonName, e.getMessage());
            logger.error("Hata detayı: ", e);
            fail(buttonName + " testi başarısız: " + e.getMessage());
        }
    }


    public void verifyInputBox(WebDriver driver, WebElement element, String boxName) {
        logger.info("=== {} temel fonksiyon testleri başlıyor ===", boxName);

        try {
            // Görünürlük testi
            assertTrue(element.isDisplayed(), boxName + " görünür olmalı");
            logger.info("✓ {} görünür durumda", boxName);

            // Etkin (enabled) testi
            assertTrue(element.isEnabled(), boxName + " aktif olmalı");
            logger.info("✓ {} aktif (enabled) durumda", boxName);

            // Tıklanabilirlik testi
            element.click();
            logger.info("✓ {}'a tıklanabildi", boxName);

            // Focus testi (tıklayınca imlecin geldiğini kontrol et)
            boolean isFocused = element.equals(driver.switchTo().activeElement());
            if (isFocused) {
                logger.info("✓ {} focus oldu (imleç alanda)", boxName);
            } else {
                logger.warn("⚠ {} focus olmadı", boxName);
            }

            // Boş değer girip temizleme testi
            String testValue = "test@test.com";
            element.clear();
            element.sendKeys(testValue);
            String enteredText = element.getAttribute("value");
            assertEquals(testValue, enteredText, "Girilen değer eşleşmeli");
            logger.info("✓ {}'a yazı yazılabildi: {}", boxName, enteredText);

            // Temizleme testi
            element.clear();
            String clearedText = element.getAttribute("value");
            assertTrue(clearedText == null || clearedText.isEmpty(), "Temizleme işlemi başarılı olmalı");
            logger.info("✓ {} temizlenebildi", boxName);

            logger.info("=== {} temel fonksiyon testleri BAŞARILI ===", boxName);

        } catch (Exception e) {
            logger.error("{} testi başarısız: {}", boxName, e.getMessage());
            fail(boxName + " testi başarısız: " + e.getMessage());
        }
    }


    public void verifyInputBox(WebDriver driver, WebElement element, String boxName, String testValue) {
        logger.info("=== {} temel fonksiyon testleri başlıyor ===", boxName);

        try {
            // Görünürlük testi
            assertTrue(element.isDisplayed(), boxName + " görünür olmalı");
            logger.info("✓ {} görünür durumda", boxName);

            // Etkin (enabled) testi
            assertTrue(element.isEnabled(), boxName + " aktif olmalı");
            logger.info("✓ {} aktif (enabled) durumda", boxName);

            // Tıklanabilirlik testi
            element.click();
            logger.info("✓ {}'a tıklanabildi", boxName);

            // Focus testi
            boolean isFocused = element.equals(driver.switchTo().activeElement());
            if (isFocused) {
                logger.info("✓ {} focus oldu (imleç alanda)", boxName);
            } else {
                logger.warn("⚠ {} focus olmadı", boxName);
            }

            // Değer girme testi
            element.clear();
            element.sendKeys(testValue);
            String enteredText = element.getAttribute("value");
            assertEquals(testValue, enteredText, "Girilen değer eşleşmeli");
            logger.info("✓ {}'a yazı yazılabildi: {}", boxName, enteredText);

            // Temizleme testi
            element.clear();
            String clearedText = element.getAttribute("value");
            assertTrue(clearedText == null || clearedText.isEmpty(), "Temizleme işlemi başarılı olmalı");
            logger.info("✓ {} temizlenebildi", boxName);

            logger.info("=== {} temel fonksiyon testleri BAŞARILI ===", boxName);

        } catch (Exception e) {
            logger.error("{} testi başarısız: {}", boxName, e.getMessage());
            fail(boxName + " testi başarısız: " + e.getMessage());
        }
    }
}