package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SendKeysUtils {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);
    private static final Duration SHORT_TIMEOUT = Duration.ofSeconds(5);
    private static final Duration HIGHLIGHT_DURATION = Duration.ofMillis(300);

    // ============ ANA METHODLAR ============

    /**
     * WebElement'e text gönderir (EN ÇOK KULLANILACAK METHOD)
     * @param driver WebDriver
     * @param element Text gönderilecek WebElement
     * @param text Gönderilecek metin
     */
    public static void sendKeysToElement(WebDriver driver, WebElement element, String text) {
        sendKeysToElement(driver, element, text, DEFAULT_TIMEOUT);
    }

    /**
     * WebElement'e timeout belirterek text gönderir
     * @param driver WebDriver
     * @param element Text gönderilecek WebElement
     * @param text Gönderilecek metin
     * @param timeout Bekleme süresi
     */
    public static void sendKeysToElement(WebDriver driver, WebElement element, String text, Duration timeout) {
        try {
            log("🔍 Elemente text gönderilmeye hazırlanıyor...");

            // 1. Element görünür mü?
            waitForElementVisible(driver, element, timeout);

            // 2. Scroll to element
            scrollToElement(driver, element);

            // 3. Element ile etkileşim kurulabilir mi?
            if (!isInteractable(driver, element, SHORT_TIMEOUT)) {
                log("⚠️ Element normal şartlarda etkileşime uygun değil, zorlayıcı methodlar deneniyor...");
            }

            // 4. Elementi highlight et
            highlightElement(driver, element);

            // 5. Input alanını temizle
            clearFieldWithFallback(driver, element);

            // 6. Text gönderme işlemini dene
            boolean sent = performSendKeysWithFallback(driver, element, text);

            // 7. Highlight'ı kaldır
            unhighlightElement(driver, element);

            if (sent) {
                log("✅ TEXT GÖNDERİLDİ - '" + text + "'");
                verifyTextEntered(driver, element, text);
            } else {
                throw new RuntimeException("❌ Tüm text gönderme methodları başarısız oldu");
            }

        } catch (Exception e) {
            log("❌ HATA: " + e.getMessage());
            throw new RuntimeException("Text gönderme başarısız", e);
        }
    }

    /**
     * By (xpath, id, cssSelector vb.) ile elemente text gönderir
     * @param driver WebDriver
     * @param by By locator
     * @param text Gönderilecek metin
     */
    public static void sendKeysToElement(WebDriver driver, By by, String text) {
        sendKeysToElement(driver, by, text, DEFAULT_TIMEOUT);
    }

    /**
     * By ile timeout belirterek text gönderir
     * @param driver WebDriver
     * @param by By locator
     * @param text Gönderilecek metin
     * @param timeout Bekleme süresi
     */
    public static void sendKeysToElement(WebDriver driver, By by, String text, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sendKeysToElement(driver, element, text, timeout);
    }

    /**
     * XPath ile elemente text gönderir (eski methodlarla uyumluluk için)
     * @param driver WebDriver
     * @param xpath Elementin xpath'i
     * @param text Gönderilecek metin
     */
    public static void sendByXpath(WebDriver driver, String xpath, String text) {
        sendKeysToElement(driver, By.xpath(xpath), text, DEFAULT_TIMEOUT);
    }

    /**
     * XPath ile timeout belirterek text gönderir (eski methodlarla uyumluluk için)
     */
    public static void sendByXpath(WebDriver driver, String xpath, String text, Duration timeout) {
        sendKeysToElement(driver, By.xpath(xpath), text, timeout);
    }

    /**
     * Label veya placeholder metnine göre input alanını bulur ve text gönderir
     * @param driver WebDriver
     * @param boxName Kutunun görünen etiketi veya placeholder metni
     * @param text Gönderilecek metin
     */
    public static void sendKeysByText(WebDriver driver, String boxName, String text) {
        String xpath = "(//label[normalize-space(.)='" + boxName + "']//following::input)[1] | " +
                "//input[@placeholder='" + boxName + "'] | " +
                "(//*[normalize-space(.)='" + boxName + "']/following-sibling::input)[1]";
        log("⌨️ '" + boxName + "' isimli kutuya metin gönderiliyor: " + text);
        sendKeysToElement(driver, By.xpath(xpath), text);
    }

    /**
     * Input alanına text ekler (temizlemeden)
     * @param driver WebDriver
     * @param element WebElement
     * @param text Eklenecek metin
     */
    public static void appendToElement(WebDriver driver, WebElement element, String text) {
        try {
            scrollToElement(driver, element);
            highlightElement(driver, element);
            element.sendKeys(text);
            log("✅ TEXT EKLENDİ - '" + text + "'");
            unhighlightElement(driver, element);
        } catch (Exception e) {
            log("❌ Text ekleme başarısız: " + e.getMessage());
            throw new RuntimeException("Text ekleme başarısız", e);
        }
    }

    /**
     * By ile elemente text ekler (temizlemeden)
     */
    public static void appendToElement(WebDriver driver, By by, String text) {
        WebElement element = driver.findElement(by);
        appendToElement(driver, element, text);
    }

    // ============ YARDIMCI METHODLAR ============

    /**
     * Elementin görünür olmasını bekler
     */
    private static void waitForElementVisible(WebDriver driver, WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        log("✅ Element görünür hale geldi");
    }

    /**
     * Element ile etkileşim kurulabilir mi kontrolü
     */
    private static boolean isInteractable(WebDriver driver, WebElement element, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ============ SCROLL METHODLARI ============

    /**
     * Akıllı scroll - Element görünürdeyse scroll yapmaz, değilse tam ortaya scroll yapar
     * @param driver WebDriver
     * @param element Scroll yapılacak element
     */
    private static void scrollToElement(WebDriver driver, WebElement element) {
        scrollToElement(driver, element, true);
    }

    /**
     * Akıllı scroll - Element görünürdeyse scroll yapmaz
     * @param driver WebDriver
     * @param element Scroll yapılacak element
     * @param centerToView Elementi sayfa ortasına almak için (true=ortala, false=sadece görünür yap)
     */
    private static void scrollToElement(WebDriver driver, WebElement element, boolean centerToView) {
        try {
            // Elementin görünür olup olmadığını kontrol et
            boolean isElementInViewport = isElementInViewport(driver, element);

            if (isElementInViewport) {
                log("   ✓ Element zaten görünür alanda, scroll yapılmadı");
                return;
            }

            log("   📜 Element görünür değil, scroll yapılıyor...");

            if (centerToView) {
                // Elementi tam ortaya getir
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});",
                        element
                );
                log("   ✓ Element sayfa ortasına scroll yapıldı");
            } else {
                // Sadece görünür yap
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                log("   ✓ Element görünür hale getirildi");
            }

            Thread.sleep(300);

        } catch (Exception e) {
            log("   ⚠️ Scroll başarısız: " + e.getMessage());
            tryAlternativeScroll(driver, element, centerToView);
        }
    }

    /**
     * Elementin viewport'ta (görünür alanda) olup olmadığını kontrol eder
     */
    public static boolean isElementInViewport(WebDriver driver, WebElement element) {
        try {
            return (boolean) ((JavascriptExecutor) driver).executeScript(
                    "var rect = arguments[0].getBoundingClientRect();" +
                            "var windowHeight = (window.innerHeight || document.documentElement.clientHeight);" +
                            "var windowWidth = (window.innerWidth || document.documentElement.clientWidth);" +
                            "return (rect.top >= 0 && rect.bottom <= windowHeight && rect.left >= 0 && rect.right <= windowWidth);",
                    element
            );
        } catch (Exception e) {
            return false;
        }
    }

    private static void tryAlternativeScroll(WebDriver driver, WebElement element, boolean centerToView) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            Thread.sleep(300);
            log("   ✓ Actions ile scroll yapıldı");
        } catch (Exception e2) {
            try {
                String script = centerToView
                        ? "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});"
                        : "arguments[0].scrollIntoView(true);";
                ((JavascriptExecutor) driver).executeScript(script, element);
                Thread.sleep(500);
                log("   ✓ Smooth scroll yapıldı");
            } catch (Exception e3) {
                log("   ✗ Tüm scroll methodları başarısız");
            }
        }
    }

    // ============ TEMİZLEME METHODLARI ============

    /**
     * Input alanını temizleme (aşamalı methodlar)
     */
    private static void clearFieldWithFallback(WebDriver driver, WebElement element) {
        log("   🧹 Input alanı temizleniyor...");

        // METHOD 1: Normal clear
        try {
            element.clear();
            log("   ✓ Normal clear başarılı");
            return;
        } catch (Exception e) {
            log("   ✗ Normal clear başarısız: " + e.getMessage());
        }

        // METHOD 2: CTRL+A + DELETE
        try {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.DELETE);
            log("   ✓ CTRL+A + DELETE ile temizleme başarılı");
            return;
        } catch (Exception e) {
            log("   ✗ CTRL+A + DELETE başarısız: " + e.getMessage());
        }

        // METHOD 3: JavaScript ile temizleme
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);
            log("   ✓ JavaScript ile temizleme başarılı");
            return;
        } catch (Exception e) {
            log("   ✗ JavaScript temizleme başarısız: " + e.getMessage());
        }

        // METHOD 4: Backspace ile temizleme
        try {
            String value = element.getAttribute("value");
            if (value != null && !value.isEmpty()) {
                for (int i = 0; i < value.length(); i++) {
                    element.sendKeys(Keys.BACK_SPACE);
                }
                log("   ✓ Backspace ile temizleme başarılı");
            }
        } catch (Exception e) {
            log("   ✗ Backspace temizleme başarısız: " + e.getMessage());
        }
    }

    /**
     * Input alanını temizler (public version)
     */
    public static void clearField(WebDriver driver, WebElement element) {
        clearFieldWithFallback(driver, element);
    }

    public static void clearField(WebDriver driver, By by) {
        WebElement element = driver.findElement(by);
        clearFieldWithFallback(driver, element);
    }

    // ============ TEXT GÖNDERME METHODLARI (8 AŞAMALI) ============

    private static boolean performSendKeysWithFallback(WebDriver driver, WebElement element, String text) {

        // METHOD 1: Normal sendKeys
        try {
            log("   [1/8] Normal sendKeys deneniyor...");
            element.sendKeys(text);
            log("   ✓ Normal sendKeys başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Normal sendKeys başarısız: " + e.getMessage());
        }

        // METHOD 2: Actions ile sendKeys
        try {
            log("   [2/8] Actions sendKeys deneniyor...");
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().sendKeys(text).perform();
            log("   ✓ Actions sendKeys başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Actions sendKeys başarısız: " + e.getMessage());
        }

        // METHOD 3: JavaScript ile value atama
        try {
            log("   [3/8] JavaScript value atama deneniyor...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", element, text);
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                    element
            );
            log("   ✓ JavaScript value atama başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ JavaScript value atama başarısız: " + e.getMessage());
        }

        // METHOD 4: Scroll + sendKeys
        try {
            log("   [4/8] Scroll + sendKeys deneniyor...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(300);
            element.click();
            element.sendKeys(text);
            log("   ✓ Scroll + sendKeys başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Scroll + sendKeys başarısız: " + e.getMessage());
        }

        // METHOD 5: Click + sendKeys
        try {
            log("   [5/8] Click + sendKeys deneniyor...");
            element.click();
            Thread.sleep(200);
            element.sendKeys(text);
            log("   ✓ Click + sendKeys başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Click + sendKeys başarısız: " + e.getMessage());
        }

        // METHOD 6: Karakter karakter gönderme (yavaş ama garantili)
        try {
            log("   [6/8] Karakter karakter gönderme deneniyor...");
            element.click();
            for (char c : text.toCharArray()) {
                element.sendKeys(String.valueOf(c));
                Thread.sleep(50);
            }
            log("   ✓ Karakter karakter gönderme başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Karakter karakter gönderme başarısız: " + e.getMessage());
        }

        // METHOD 7: JavaScript ile focus + sendKeys
        try {
            log("   [7/8] JavaScript focus + sendKeys deneniyor...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", element);
            Thread.sleep(200);
            element.sendKeys(text);
            log("   ✓ JavaScript focus + sendKeys başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ JavaScript focus + sendKeys başarısız: " + e.getMessage());
        }

        // METHOD 8: Paste ile yapıştırma
        try {
            log("   [8/8] Paste ile yapıştırma deneniyor...");
            element.click();
            ((JavascriptExecutor) driver).executeScript(
                    "var text = arguments[1];" +
                            "arguments[0].value = text;" +
                            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                    element, text
            );
            log("   ✓ Paste ile yapıştırma başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Paste ile yapıştırma başarısız: " + e.getMessage());
        }

        return false;
    }

    // ============ DOĞRULAMA METHODLARI ============

    /**
     * Text'in gerçekten elemente yazıldığını doğrular
     */
    private static void verifyTextEntered(WebDriver driver, WebElement element, String expectedText) {
        try {
            String actualText = getElementValue(driver, element);

            if (expectedText.equals(actualText)) {
                log("   ✓ Text doğrulaması başarılı: '" + actualText + "'");
            } else {
                log("   ⚠️ Text doğrulaması: Beklenen='" + expectedText + "', Gerçekleşen='" + actualText + "'");
            }
        } catch (Exception e) {
            log("   ⚠️ Text doğrulaması yapılamadı: " + e.getMessage());
        }
    }

    /**
     * Elementin değerini alır
     */
    public static String getElementValue(WebDriver driver, WebElement element) {
        try {
            String value = element.getAttribute("value");
            if (value == null || value.isEmpty()) {
                value = element.getText();
            }
            if (value == null || value.isEmpty()) {
                value = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;", element);
            }
            return value != null ? value : "";
        } catch (Exception e) {
            return "";
        }
    }

    public static String getElementValue(WebDriver driver, By by) {
        WebElement element = driver.findElement(by);
        return getElementValue(driver, element);
    }

    public static String getText(WebDriver driver, String xpath) {
        return getElementValue(driver, By.xpath(xpath));
    }

    // ============ HIGHLIGHT METHODLARI ============

    private static void highlightElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String originalStyle = element.getAttribute("style");

            String tagName = element.getTagName();
            String highlightColor = "input".equalsIgnoreCase(tagName) || "textarea".equalsIgnoreCase(tagName)
                    ? "border: 3px solid blue; background-color: lightblue; transition: all 0.2s;"
                    : "border: 3px solid orange; background-color: yellow; transition: all 0.2s;";

            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, highlightColor);
            log("   ✨ Element highlight edildi");
            Thread.sleep(HIGHLIGHT_DURATION.toMillis());

            if (originalStyle != null && !originalStyle.isEmpty()) {
                js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
            } else {
                js.executeScript("arguments[0].removeAttribute('style');", element);
            }
        } catch (Exception e) {
            log("   ⚠️ Highlight işlemi başarısız: " + e.getMessage());
        }
    }

    private static void unhighlightElement(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('style');", element);
        } catch (Exception e) {
            // Sessiz geç
        }
    }

    // ============ KEYBOARD METHODLARI ============

    /**
     * Enter tuşuna basar
     */
    public static void pressEnter(WebDriver driver, WebElement element) {
        try {
            element.sendKeys(Keys.ENTER);
            log("⏎ ENTER tuşuna basıldı");
        } catch (Exception e) {
            log("❌ ENTER basılamadı: " + e.getMessage());
        }
    }

    public static void pressEnter(WebDriver driver, By by) {
        pressEnter(driver, driver.findElement(by));
    }

    public static void pressEnter(WebDriver driver, String xpath) {
        pressEnter(driver, By.xpath(xpath));
    }

    /**
     * Tab tuşuna basar
     */
    public static void pressTab(WebDriver driver, WebElement element) {
        try {
            element.sendKeys(Keys.TAB);
            log("⇆ TAB tuşuna basıldı");
        } catch (Exception e) {
            log("❌ TAB basılamadı: " + e.getMessage());
        }
    }

    public static void pressTab(WebDriver driver, By by) {
        pressTab(driver, driver.findElement(by));
    }

    public static void pressTab(WebDriver driver, String xpath) {
        pressTab(driver, By.xpath(xpath));
    }

    /**
     * Arrow Down tuşuna basar
     */
    public static void pressArrowDown(WebDriver driver, WebElement element) {
        try {
            element.sendKeys(Keys.ARROW_DOWN);
            log("⬇️ ARROW_DOWN tuşuna basıldı");
        } catch (Exception e) {
            log("❌ ARROW_DOWN basılamadı: " + e.getMessage());
        }
    }

    public static void pressArrowDown(WebDriver driver, By by) {
        pressArrowDown(driver, driver.findElement(by));
    }

    public static void pressArrowDown(WebDriver driver, String xpath) {
        pressArrowDown(driver, By.xpath(xpath));
    }

    /**
     * Arrow Up tuşuna basar
     */
    public static void pressArrowUp(WebDriver driver, WebElement element) {
        try {
            element.sendKeys(Keys.ARROW_UP);
            log("⬆️ ARROW_UP tuşuna basıldı");
        } catch (Exception e) {
            log("❌ ARROW_UP basılamadı: " + e.getMessage());
        }
    }

    /**
     * Escape tuşuna basar
     */
    public static void pressEscape(WebDriver driver, WebElement element) {
        try {
            element.sendKeys(Keys.ESCAPE);
            log("⎋ ESCAPE tuşuna basıldı");
        } catch (Exception e) {
            log("❌ ESCAPE basılamadı: " + e.getMessage());
        }
    }

    // ============ EKSTRA METHODLAR ============

    private static void log(String message) {
        System.out.println("[SendKeysUtils] " + message);
    }

    public static void smartSendKeys(WebDriver driver, String boxName, String text, java.util.function.Supplier<WebElement> elementSupplier) {
        try {
            WebElement element = elementSupplier.get();
            if (element != null) {
                log("🎯 Input elementi listede tanımlı bulundu: " + boxName);
                sendKeysToElement(driver, element, text);
                return;
            }
        } catch (Exception e) {
            log("⚠️ Listedeki element alınırken hata oluştu veya yok. Label/Placeholder ismiyle yazma deneniyor...");
        }

        // Listede bulamazsa doğrudan Label, Placeholder veya Sibling üzerinden arayıp yazar
        sendKeysByText(driver, boxName, text);
    }
}