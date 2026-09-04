package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExcelReader {

    private static final Logger logger = LogManager.getLogger(ExcelReader.class);
    private Workbook workbook;
    private String filePath;

    public ExcelReader(String filePath) {
        this.filePath = filePath;
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            workbook = new XSSFWorkbook(fis); // .xlsx dosyaları için
        } catch (IOException e) {
            throw new RuntimeException("Excel dosyası okunurken hata oluştu: " + e.getMessage());
        }
    }

    // 🆕 YENİ METOT: Rastgele hesap seç ve konsola yazdır
    public void selectAndPrintRandomAccount(String sheetName) {
        List<List<String>> allData = readSheet(sheetName);

        // 1. Başlık satırını kontrol et
        if (allData.isEmpty()) {
            logger.error("❌ Excel dosyası boş!");
            System.out.println("❌ Excel dosyası boş!");
            return;
        }

        // 2. Başlık satırını al (ilk satır)
        List<String> headers = allData.get(0);

        // 3. Kaç hesap olduğunu hesapla (başlık satırı hariç)
        int accountCount = allData.size() - 1;

        if (accountCount == 0) {
            logger.warn("⚠️ Excel'de kayıtlı hesap bulunamadı!");
            System.out.println("⚠️ Excel'de kayıtlı hesap bulunamadı!");
            return;
        }

        logger.info("📊 Excel'de toplam " + accountCount + " hesap bulundu.");
        System.out.println("📊 Excel'de toplam " + accountCount + " hesap bulundu.");

        // 4. Rastgele bir hesap seç (1'den başla çünkü 0. satır başlık)
        Random random = new Random();
        int randomRowIndex = random.nextInt(accountCount) + 1; // 1'den accountCount'a kadar

        // 5. Seçilen hesabın verilerini al
        List<String> selectedAccount = allData.get(randomRowIndex);

        // 6. Sütun indekslerini bul (başlıklara göre)
        int mailIndex = headers.indexOf("Mail");
        int nameIndex = headers.indexOf("Name");
        int passwordIndex = headers.indexOf("Password");
        int roleIndex = headers.indexOf("Role");

        // 7. Verileri al (eğer sütun yoksa boş string)
        String mail = (mailIndex != -1 && mailIndex < selectedAccount.size()) ? selectedAccount.get(mailIndex) : "Bilgi yok";
        String name = (nameIndex != -1 && nameIndex < selectedAccount.size()) ? selectedAccount.get(nameIndex) : "Bilgi yok";
        String password = (passwordIndex != -1 && passwordIndex < selectedAccount.size()) ? selectedAccount.get(passwordIndex) : "Bilgi yok";
        String role = (roleIndex != -1 && roleIndex < selectedAccount.size()) ? selectedAccount.get(roleIndex) : "Bilgi yok";

        // 8. Konsola yazdır (ÖNEMLİ: Role bilgisi vurgulanarak)
        System.out.println("═══════════════════════════════════════════");
        System.out.println("🎯 Rastgele Seçilen Hesap (Satır: " + randomRowIndex + ")");
        System.out.println("═══════════════════════════════════════════");
        System.out.println("📧 Mail     : " + mail);
        System.out.println("👤 İsim     : " + name);
        System.out.println("🔑 Şifre    : " + password);
        System.out.println("🎭 Rol      : " + role.toUpperCase() + " ⭐");
        System.out.println("═══════════════════════════════════════════");

        // Log'a da yaz
        logger.info("🎯 Rastgele seçilen hesap -> Rol: " + role + ", Mail: " + mail + ", İsim: " + name);

        // 9. Rol'e göre özel mesaj
        String roleMessage;
        switch (role.toLowerCase()) {
            case "student":
                roleMessage = "📚 Öğrenci hesabı seçildi!";
                break;
            case "teacher":
                roleMessage = "👨‍🏫 Öğretmen hesabı seçildi!";
                break;
            case "organizator":
                roleMessage = "📋 Organizatör hesabı seçildi!";
                break;
            default:
                roleMessage = "❓ Bilinmeyen rol: " + role;
        }
        System.out.println("💬 " + roleMessage);
        System.out.println("═══════════════════════════════════════════\n");
    }

    // 🆕 YENİ METOT: Sadece hesap sayısını döndürür
    public int getAccountCount(String sheetName) {
        List<List<String>> allData = readSheet(sheetName);
        if (allData.isEmpty()) {
            return 0;
        }
        return allData.size() - 1; // Başlık satırı hariç
    }

    // 🆕 YENİ METOT: Belirli bir roldeki hesap sayısını getir
    public int getAccountCountByRole(String sheetName, String targetRole) {
        List<List<String>> allData = readSheet(sheetName);
        if (allData.isEmpty()) {
            return 0;
        }

        List<String> headers = allData.get(0);
        int roleIndex = headers.indexOf("Role");
        if (roleIndex == -1) {
            logger.warn("⚠️ 'Role' sütunu bulunamadı!");
            return 0;
        }

        int count = 0;
        for (int i = 1; i < allData.size(); i++) {
            List<String> row = allData.get(i);
            if (roleIndex < row.size() && row.get(roleIndex).equalsIgnoreCase(targetRole)) {
                count++;
            }
        }
        return count;
    }

    // 🆕 YENİ METOT: Rastgele bir hesap seç (sadece veriyi döndürür, yazdırmaz)
    public AccountData selectRandomAccount(String sheetName) {
        List<List<String>> allData = readSheet(sheetName);
        if (allData.size() <= 1) {
            return null;
        }

        List<String> headers = allData.get(0);
        int accountCount = allData.size() - 1;
        Random random = new Random();
        int randomRowIndex = random.nextInt(accountCount) + 1;
        List<String> selectedAccount = allData.get(randomRowIndex);

        int mailIndex = headers.indexOf("Mail");
        int nameIndex = headers.indexOf("Name");
        int passwordIndex = headers.indexOf("Password");
        int roleIndex = headers.indexOf("Role");

        return new AccountData(
                (mailIndex != -1 && mailIndex < selectedAccount.size()) ? selectedAccount.get(mailIndex) : "",
                (nameIndex != -1 && nameIndex < selectedAccount.size()) ? selectedAccount.get(nameIndex) : "",
                (passwordIndex != -1 && passwordIndex < selectedAccount.size()) ? selectedAccount.get(passwordIndex) : "",
                (roleIndex != -1 && roleIndex < selectedAccount.size()) ? selectedAccount.get(roleIndex) : ""
        );
    }

    // 📦 Hesap verilerini tutmak için inner class
    public static class AccountData {
        public String mail;
        public String name;
        public String password;
        public String role;

        public AccountData(String mail, String name, String password, String role) {
            this.mail = mail;
            this.name = name;
            this.password = password;
            this.role = role;
        }

        @Override
        public String toString() {
            return "AccountData{" +
                    "mail='" + mail + '\'' +
                    ", name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    ", role='" + role + '\'' +
                    '}';
        }
    }

    // Belirli bir sheet'teki tüm verileri okur
    public List<List<String>> readSheet(String sheetName) {
        List<List<String>> data = new ArrayList<>();
        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new RuntimeException("Belirtilen sheet bulunamadı: " + sheetName);
        }

        for (Row row : sheet) {
            List<String> rowData = new ArrayList<>();
            for (Cell cell : row) {
                rowData.add(getCellValueAsString(cell));
            }
            data.add(rowData);
        }
        return data;
    }

    // Belirtilen sheet ve hücreye değer yazar
    public void writeCell(String sheetName, int rowIndex, int colIndex, String value, String outputPath) {
        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }

        Cell cell = row.getCell(colIndex);
        if (cell == null) {
            cell = row.createCell(colIndex);
        }

        cell.setCellValue(value);

        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException("Excel dosyasına yazılırken hata oluştu: " + e.getMessage());
        }
    }

    // Excel dosyasını kapatma
    public void close() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Excel dosyası kapatılırken hata oluştu: " + e.getMessage());
        }
    }

    // Hücredeki veriyi String olarak okuma
    private String getCellValueAsString(Cell cell) {
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }
}