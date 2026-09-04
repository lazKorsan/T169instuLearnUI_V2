package utils;

public class TestExcelReader {
    public static void main(String[] args) {
        String excelPath = "src/main/resources/TestData.xlsx";

        ExcelReader reader = new ExcelReader(excelPath);

        // 1. Rastgele hesap seç ve konsola yazdır
        reader.selectAndPrintRandomAccount("Sheet1");

        // 2. Sadece hesap sayısını öğren
        int count = reader.getAccountCount("Sheet1");
        System.out.println("Toplam hesap sayısı: " + count);

        // 3. Belirli bir roldeki hesap sayısı
        int studentCount = reader.getAccountCountByRole("Sheet1", "student");
        System.out.println("Öğrenci sayısı: " + studentCount);

        // 4. Rastgele hesap seç (veriyi al, yazdırma)
        ExcelReader.AccountData account = reader.selectRandomAccount("Sheet1");
        if (account != null) {
            System.out.println("Seçilen hesap: " + account);
            // Testte kullanmak için:
            System.out.println("Login: " + account.mail + " / " + account.password);
        }

        reader.close();
    }
}