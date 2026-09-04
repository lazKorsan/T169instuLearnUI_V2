package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClickUtils {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);
    private static final Duration SHORT_TIMEOUT = Duration.ofSeconds(5);
    private static final Duration HIGHLIGHT_DURATION = Duration.ofMillis(200);

    // ============ ANA METHODLAR ============

    /**
     * WebElement'e tıklar (EN ÇOK KULLANILACAK METHOD)
     * @param driver WebDriver
     * @param element Tıklanacak WebElement
     */
    public static void clickElement(WebDriver driver, WebElement element) {
        clickElement(driver, element, DEFAULT_TIMEOUT);
    }

    /**
     * WebElement'e timeout belirterek tıklar
     * @param driver WebDriver
     * @param element Tıklanacak WebElement
     * @param timeout Bekleme süresi
     */
    public static void clickElement(WebDriver driver, WebElement element, Duration timeout) {
        try {
            log("🔍 Element tıklanmaya hazırlanıyor...");

            // 1. Element görünür mü?
            waitForElementVisible(driver, element, timeout);

            // 2. Scroll to element
            scrollToElement(driver, element);

            // 3. Hover
            hoverOverElement(driver, element);

            // 4. Tıklanabilir mi?
            if (!isClickable(driver, element, SHORT_TIMEOUT)) {
                log("⚠️ Element normal şartlarda tıklanabilir değil, zorlayıcı methodlar deneniyor...");
            }

            // 5. Highlight
            highlightElement(driver, element);

            // 6. Tıklamayı dene
            boolean clicked = performClickWithFallback(driver, element);

            // 7. Highlight'ı kaldır
            unhighlightElement(driver, element);

            if (clicked) {
                log("✅ ELEMANA TIKLANDI");
            } else {
                throw new RuntimeException("❌ Tüm tıklama methodları başarısız oldu");
            }

        } catch (Exception e) {
            log("❌ HATA: " + e.getMessage());
            throw new RuntimeException("Tıklama başarısız", e);
        }
    }

    /**
     * By (xpath, id, cssSelector vb.) ile elemente tıklar
     * @param driver WebDriver
     * @param by By locator
     */
    public static void clickElement(WebDriver driver, By by) {
        clickElement(driver, by, DEFAULT_TIMEOUT);
    }

    /**
     * By ile timeout belirterek tıklar
     * @param driver WebDriver
     * @param by By locator
     * @param timeout Bekleme süresi
     */
    public static void clickElement(WebDriver driver, By by, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        clickElement(driver, element, timeout);
    }

    /**
     * XPath ile elemente tıklar (eski methodlarla uyumluluk için)
     * @param driver WebDriver
     * @param xpath Elementin xpath'i
     */
    public static void clickByXpath(WebDriver driver, String xpath) {
        clickElement(driver, By.xpath(xpath), DEFAULT_TIMEOUT);
    }

    /**
     * XPath ile timeout belirterek tıklar (eski methodlarla uyumluluk için)
     */
    public static void clickByXpath(WebDriver driver, String xpath, Duration timeout) {
        clickElement(driver, By.xpath(xpath), timeout);
    }

    /**
     * Element üzerindeki metne göre tıklar (tam eşleşme)
     * @param driver WebDriver
     * @param buttonName Element üzerindeki tam metin
     */
    public static void clickByText(WebDriver driver, String buttonName) {
        String xpath = "//*[normalize-space(text())='" + buttonName + "']";
        log("🏷️ Metin ile element aranıyor: " + buttonName);
        clickElement(driver, By.xpath(xpath));
    }

    /**
     * Element üzerindeki metne göre tıklar (kısmi eşleşme)
     * @param driver WebDriver
     * @param partialText Element içinde geçen kısmi metin
     */
    public static void clickByPartialText(WebDriver driver, String partialText) {
        String xpath = "//*[contains(text(),'" + partialText + "')]";
        log("🏷️ Parçalı metin ile element aranıyor: " + partialText);
        clickElement(driver, By.xpath(xpath));
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
     * Elementin tıklanabilir olup olmadığını kontrol eder
     */
    private static boolean isClickable(WebDriver driver, WebElement element, Duration timeout) {
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
    public static void scrollToElement(WebDriver driver, WebElement element) {
        scrollToElement(driver, element, true); // varsayılan olarak ortala
    }

    /**
     * Akıllı scroll - Element görünürdeyse scroll yapmaz
     * @param driver WebDriver
     * @param element Scroll yapılacak element
     * @param centerToView Elementi sayfa ortasına almak için (true=ortala, false=sadece görünür yap)
     */
    public static void scrollToElement(WebDriver driver, WebElement element, boolean centerToView) {
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

            // Scroll sonrası hala görünür değilse alternatif dene
            if (!isElementInViewport(driver, element)) {
                tryAlternativeScroll(driver, element, centerToView);
            }

        } catch (Exception e) {
            log("   ⚠️ Scroll başarısız: " + e.getMessage());
            tryAlternativeScroll(driver, element, centerToView);
        }
    }

    /**
     * Elementin viewport'ta (görünür alanda) olup olmadığını kontrol eder
     * @param driver WebDriver
     * @param element Kontrol edilecek element
     * @return true görünürde, false görünürde değil
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

    /**
     * Elementin görünür alanda olup olmadığını kontrol eder (kısmi görünüm de dahil)
     * @param driver WebDriver
     * @param element Kontrol edilecek element
     * @return true kısmen de olsa görünürde
     */
    public static boolean isElementPartiallyInViewport(WebDriver driver, WebElement element) {
        try {
            return (boolean) ((JavascriptExecutor) driver).executeScript(
                    "var rect = arguments[0].getBoundingClientRect();" +
                            "var windowHeight = (window.innerHeight || document.documentElement.clientHeight);" +
                            "var windowWidth = (window.innerWidth || document.documentElement.clientWidth);" +
                            "return (rect.bottom > 0 && rect.top < windowHeight && rect.right > 0 && rect.left < windowWidth);",
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

    /**
     * Elementi görünene kadar kademeli scroll yapar (sayfa sonuna kadar gider)
     * @param driver WebDriver
     * @param element Aranan element
     * @param maxAttempts Maksimum deneme sayısı
     * @return Element bulundu mu?
     */
    public static boolean scrollUntilElementVisible(WebDriver driver, WebElement element, int maxAttempts) {
        int attempts = 0;
        while (!isElementInViewport(driver, element) && attempts < maxAttempts) {
            try {
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);");
                Thread.sleep(500);
                attempts++;
                log("   📜 Scroll deneme " + attempts + "/" + maxAttempts);
            } catch (Exception e) {
                log("   ⚠️ Scroll hatası: " + e.getMessage());
                break;
            }
        }

        boolean found = isElementInViewport(driver, element);
        if (found) {
            log("   ✅ Element " + attempts + " denemede bulundu");
        } else {
            log("   ❌ Element " + maxAttempts + " denemede bulunamadı");
        }
        return found;
    }

    /**
     * Elementin bulunduğu konuma kadar scroll yapar (sayfa sonu kontrolü ile)
     * @param driver WebDriver
     * @param element Aranan element
     * @param scrollAmount Her seferinde scroll miktarı (px)
     */
    public static void scrollToElementWithBoundary(WebDriver driver, WebElement element, int scrollAmount) {
        long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
        int attempts = 0;
        int maxAttempts = 50; // Maksimum 50 deneme

        while (!isElementInViewport(driver, element) && attempts < maxAttempts) {
            try {
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + scrollAmount + ");");
                Thread.sleep(300);

                long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    // Sayfa sonuna gelindi, artık yeni içerik yok
                    log("   ⚠️ Sayfa sonuna ulaşıldı, element bulunamadı");
                    break;
                }
                lastHeight = newHeight;
                attempts++;
            } catch (Exception e) {
                log("   ⚠️ Scroll hatası: " + e.getMessage());
                break;
            }
        }

        if (isElementInViewport(driver, element)) {
            log("   ✅ Element bulundu ve görünür hale geldi");
        }
    }
    // ============ HOVER METHODLARI ============

    private static void hoverOverElement(WebDriver driver, WebElement element) {
        try {
            log("   🖱️ Element üzerine hover yapılıyor...");
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            Thread.sleep(300);
            log("   ✓ Actions ile hover başarılı");
        } catch (Exception e) {
            log("   ⚠️ Hover başarısız: " + e.getMessage());
            tryAlternativeHover(driver, element);
        }
    }

    private static void tryAlternativeHover(WebDriver driver, WebElement element) {
        try {
            String mouseOverScript =
                    "var event = new MouseEvent('mouseover', {" +
                            "  view: window," +
                            "  bubbles: true," +
                            "  cancelable: true" +
                            "});" +
                            "arguments[0].dispatchEvent(event);";
            ((JavascriptExecutor) driver).executeScript(mouseOverScript, element);
            Thread.sleep(300);
            log("   ✓ JavaScript mouseover event başarılı");
        } catch (Exception e2) {
            try {
                Actions actions = new Actions(driver);
                actions.moveToElement(element, 5, 5).perform();
                Thread.sleep(300);
                log("   ✓ Offset ile hover başarılı");
            } catch (Exception e3) {
                log("   ✗ Tüm hover methodları başarısız");
            }
        }
    }

    public static void hoverAndWait(WebDriver driver, String xpath, Duration waitTime) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            Thread.sleep(waitTime.toMillis());
            log("🖱️ Hover yapıldı ve " + waitTime.getSeconds() + " saniye beklendi: " + xpath);
        } catch (Exception e) {
            log("⚠️ Hover başarısız: " + e.getMessage());
        }
    }

    // ============ TIKLAMA METHODLARI (8 AŞAMALI) ============

    private static boolean performClickWithFallback(WebDriver driver, WebElement element) {

        // METHOD 1: Normal Click
        try {
            log("   [1/8] Normal click deneniyor...");
            element.click();
            log("   ✓ Normal click başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Normal click başarısız: " + e.getMessage());
        }

        // METHOD 2: Actions ile tıklama
        try {
            log("   [2/8] Actions click deneniyor...");
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();
            log("   ✓ Actions click başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Actions click başarısız: " + e.getMessage());
        }

        // METHOD 3: JavaScript click
        try {
            log("   [3/8] JavaScript click deneniyor...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            log("   ✓ JavaScript click başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ JavaScript click başarısız: " + e.getMessage());
        }

        // METHOD 4: Scroll + Click
        try {
            log("   [4/8] Scroll + click deneniyor...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
            element.click();
            log("   ✓ Scroll + click başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Scroll + click başarısız: " + e.getMessage());
        }

        // METHOD 5: Hover + Click
        try {
            log("   [5/8] Hover + click deneniyor...");
            Actions actions = new Actions(driver);
            actions.moveToElement(element).pause(300).click().perform();
            log("   ✓ Hover + click başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Hover + click başarısız: " + e.getMessage());
        }

        // METHOD 6: Scroll + Hover + Click
        try {
            log("   [6/8] Scroll + hover + click deneniyor...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(300);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).pause(200).click().perform();
            log("   ✓ Scroll + hover + click başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Scroll + hover + click başarısız: " + e.getMessage());
        }

        // METHOD 7: Koordinatlarla tıklama
        try {
            log("   [7/8] Koordinatlarla tıklama deneniyor...");
            Actions actions = new Actions(driver);
            actions.moveToElement(element).moveByOffset(5, 5).click().perform();
            log("   ✓ Koordinatlarla tıklama başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ Koordinatlarla tıklama başarısız: " + e.getMessage());
        }

        // METHOD 8: SendKeys Enter
        try {
            log("   [8/8] SendKeys Enter deneniyor...");
            element.sendKeys(Keys.ENTER);
            log("   ✓ SendKeys Enter başarılı");
            return true;
        } catch (Exception e) {
            log("   ✗ SendKeys Enter başarısız: " + e.getMessage());
        }

        return false;
    }

    // ============ HIGHLIGHT METHODLARI ============

    private static void highlightElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String originalStyle = element.getAttribute("style");

            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element,
                    "border: 3px solid red; background-color: yellow; transition: all 0.2s;"
            );

            log("   ✨ Element highlight edildi");
            Thread.sleep(HIGHLIGHT_DURATION.toMillis());

            if (originalStyle != null && !originalStyle.isEmpty()) {
                js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
            } else {
                js.executeScript("arguments[0].removeAttribute('style');", element);
            }

        } catch (Exception e) {
            log("   ⚠️ Highlight hatası: " + e.getMessage());
        }
    }

    private static void unhighlightElement(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('style');", element);
        } catch (Exception e) {
            // Sessiz geç
        }
    }

    // ============ EKSTRA METHODLAR ============

    public static void waitForPageLoad(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(driver1 -> ((JavascriptExecutor) driver1)
                    .executeScript("return document.readyState").equals("complete"));
            log("📄 Sayfa tamamen yüklendi");
        } catch (Exception e) {
            log("⚠️ Sayfa yüklenme beklemesi başarısız");
        }
    }

    public static WebElement waitForVisibility(WebDriver driver, String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public static void switchToDefaultContent(WebDriver driver) {
        try {
            driver.switchTo().defaultContent();
            log("🖼️ Default content'e dönüldü");
        } catch (Exception e) {
            log("⚠️ Default content switch başarısız");
        }
    }

    private static void log(String message) {
        System.out.println("[ClickUtils] " + message);
    }


    // todo bu methodu page class içinde enumList hazırlamışsanız kullanabilrisiniz.
    public static void smartClick(WebDriver driver, String buttonName, java.util.function.Supplier<WebElement> elementSupplier) {
        try {
            WebElement element = elementSupplier.get();
            if (element != null) {
                log("🎯 Element listede tanımlı bulundu: " + buttonName);
                clickElement(driver, element);
                return;
            }
        } catch (Exception e) {
            log("⚠️ Listedeki element alırken hata oluştu veya yok. Text ile tıklama deneniyor...");
        }

        // Listede bulamazsa doğrudan Text üzerinden arayıp tıklar
        clickByText(driver, buttonName);
    }

    // A function that clicks the specified element using the JavaScriptExecutor.
    public static void clickWithJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}