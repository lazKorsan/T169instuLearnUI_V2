package utils;

import config.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CreateTestDataExcel {

    private static final Logger logger = LogManager.getLogger(CreateTestDataExcel.class);
    private static final String EXCEL_PATH = "src/main/resources/TestData.xlsx";

    public static void main(String[] args) {

        // 1. Random sayı üret (1-100 arası)
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; // 1-100 arası
        logger.info("Üretilen random sayı: " + randomNumber);

        // 2. Random sayıya göre rol belirle
        String role;
        if (randomNumber <= 30) {
            role = "student";
        } else if (randomNumber <= 90) {
            role = "teacher";
        } else {
            role = "organizator";
        }
        logger.info("Atanan rol: " + role);

        // 3. Config.properties'den isim al
        String name = ConfigReader.getProperty("name", "keyOrText");
        logger.info("İsim: " + name);

        // 4. Mail oluştur (isim + rol + timestamp)
        String mail = name + "." + role + "." + LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss")) + "@instuLearn.com";
        logger.info("Mail: " + mail);

        // 5. Config.properties'den şifre al
        String password = ConfigReader.getProperty("password", "keyOrText");
        logger.info("Şifre: " + password);

        // 6. Excel işlemleri
        Workbook workbook;
        Sheet sheet;

        // Dosya varsa aç, yoksa yeni oluştur
        File excelFile = new File(EXCEL_PATH);
        if (excelFile.exists()) {
            try (FileInputStream fis = new FileInputStream(excelFile)) {
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheet("Sheet1");
                if (sheet == null) {
                    sheet = workbook.createSheet("Sheet1");
                    // Sheet yoksa başlık satırını ekle
                    createHeaderRow(sheet);
                }
            } catch (IOException e) {
                logger.error("Excel dosyası okunurken hata: " + e.getMessage());
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Sheet1");
                createHeaderRow(sheet);
            }
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Sheet1");
            // Başlık satırını ekle
            createHeaderRow(sheet);
        }

        // 7. Yeni veriyi bir sonraki boş satıra ekle
        int lastRowNum = sheet.getLastRowNum();
        int newRowNum = lastRowNum + 1;
        Row newRow = sheet.createRow(newRowNum);

        // Verileri sırasıyla yaz (Mail, Name, Password, Role)
        String[] data = {mail, name, password, role};
        for (int i = 0; i < data.length; i++) {
            Cell cell = newRow.createCell(i);
            cell.setCellValue(data[i]);
        }

        // 8. Sütun genişliklerini otomatik ayarla (tüm sütunlar için)
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        // 9. Excel dosyasını kaydet
        try (FileOutputStream fos = new FileOutputStream(EXCEL_PATH)) {
            workbook.write(fos);
            workbook.close();
            logger.info("✅ Excel dosyası başarıyla güncellendi: " + EXCEL_PATH);
            System.out.println("═══════════════════════════════════════════");
            System.out.println("✅ Excel dosyası başarıyla güncellendi!");
            System.out.println("📊 Yeni satır: " + newRowNum);
            System.out.println("📧 Mail     : " + mail);
            System.out.println("👤 İsim     : " + name);
            System.out.println("🔑 Şifre    : " + password);
            System.out.println("🎭 Rol      : " + role.toUpperCase());
            System.out.println("📁 Dosya    : " + EXCEL_PATH);
            System.out.println("═══════════════════════════════════════════");
        } catch (IOException e) {
            logger.error("Excel dosyası kaydedilirken hata: " + e.getMessage());
            System.err.println("❌ Excel dosyası kaydedilirken hata: " + e.getMessage());
        }
    }

    // Başlık satırını oluşturan yardımcı metot
    private static void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Mail", "Name", "Password", "Role"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);

            // Başlık hücrelerine stil ekle (isteğe bağlı)
            CellStyle style = sheet.getWorkbook().createCellStyle();
            Font font = sheet.getWorkbook().createFont();
            font.setBold(true);
            style.setFont(font);
            cell.setCellStyle(style);
        }
        logger.info("📋 Başlık satırı oluşturuldu: " + String.join(", ", headers));
    }
}