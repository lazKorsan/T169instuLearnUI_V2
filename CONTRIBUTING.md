# Contributing

Teşekkürler — projeye katkıda bulunmak istiyorsunuz, çok memnunuz. Aşağıda katkı sürecini, iletişim kanallarını ve pratik adımları bulabilirsiniz.

---

## Öncelikler / Kısa Özet
- Kendi dalınızı (branch) açın: `feature/`, `fix/`, `docs/`
- Değişiklikleri küçük tutun, tek bir amaç olsun.
- Açık ve anlaşılır bir PR açıklaması yazın; hangi sorunu çözdüğünü ve nasıl test edildiğini belirtin.
- PR'ınız en az bir ekip üyesi tarafından incelenmeden birleştirilmemelidir.

---

## Nasıl Başlanır

1. Depoyu fork edin veya doğrudan klonlayın:
   ```bash
   git clone <repository-url>
   cd <repo-dir>
   ```
2. Yeni branch oluşturun:
   ```bash
   git checkout -b feature/isim
   ```
3. Kod stiline uyun (Java, Maven, JDK21). Kod düzeni ve import kurallarını IDE ayarlarına göre kullanın.
4. Değişiklikleri test edin:
   ```bash
   mvn clean install
   mvn test
   ```

---

## Issue & PR Süreci

- Yeni bir hata veya geliştirme öneriniz varsa önce GitHub Issues'da tartışın.
- PR açıklamasında şunları ekleyin:
  - Kısa özet
  - Hangi issue kapatılıyor (örn. closes #123)
  - Test adımları / nasıl doğrulandığı
  - Gerekliyse ekran görüntü veya loglar

### PR Checklist (kopyalanıp PR açıklamasına ekleyin)
- [ ] Açıklama eklendi
- [ ] Testler çalıştırıldı
- [ ] Kod formatı ve importlar kontrol edildi
- [ ] Gerekli dokümanlar güncellendi

---

## Kod Stili ve Testler
- Java kodlama standartlarına uyun (anlaşılır isimlendirme, tek sorumluluk prensibi).
- Yeni özellik ekliyorsanız ilgili birim testleri ekleyin (JUnit5).
- Testleri CI işliyor; geçmesi gerekiyor.

---

## Dosya ve İçerik Değişiklikleri
- Dokümantasyon güncellemeleri için README.md veya docs/ altını tercih edin.
- Yeni örnekler veya eğitim materyali eklerken `docs/` veya `.github/` içinde uygun yerlere koyun.

---

## Video ve Medya İçerikleri
- Videoları GitHub sayfasına gömmek yerine thumbnail + link kullanıyoruz (offline görünürlük istiyorsanız `assets/` içine küçük görüntü ekleyin).
- Mevcut kurulum videoları için örnek (README'e/CONTRIBUTING'e eklenmiş):
  - Team 169 UI project Kurulum Videosu — https://www.youtube.com/watch?v=PJFdHEnhCP0
  - Diğer videolar repo içindeki CONTRIBUTING.md dosyasında galeri olarak bulunur.

---

## İletişim ve Destek
- Küçük sorular için GitHub Discussions veya Issues kullanın.
- Acil/özel konular için: `maintainers@example.com` (bu adresi gerçek irtibat ile değiştirin).

---

## Davranış Kuralları
Katkılarınız, proje genelinde geçerli olan CODE_OF_CONDUCT.md kurallarına uygun olmalıdır. Lütfen saygılı ve yapıcı olun.

---

## Hızlı Commit & PR Adımları
```bash
git add .
git commit -m "feat: kısa açıklama"
git push origin feature/isim
# GitHub üzerinde PR açın
```
