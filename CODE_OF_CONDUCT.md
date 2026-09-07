# 📋 Test Automation Project Rules & Guidelines

Bu doküman, test otomasyon projesinde kod kalitesini korumak, Git çakışmalarını (conflict) önlemek ve ekip içi düzeni sağlamak amacıyla oluşturulmuştur. Tüm ekip üyelerinin aşağıdaki kurallara uyması zorunludur.

---

## 1. 🔀 Versiyon Kontrolü & Git Akışı (Git Workflow)

* **1.1. `.gitignore` Değiştirilmez:** `.gitignore` dosyası kesinlikle değiştirilemez ve commit edilemez.
* **1.2. Main Branch Koruması:** `main` (veya `master`) branch'ine doğrudan `git push` işlemi yapılamaz.
* **1.3. Feature Branch Kullanımı:** Tüm geliştirmeler kişiye veya göreve özel branch'lerde yapılmalıdır.
  * *Örnek Format:* `feature/US025_student_dashboard`
* **1.4. Code Review & Merge:** Merge ve Pull Request (PR) işlemleri yalnızca **Team Lead / QA Lead** tarafından incelendikten sonra onaylanıp birleştirilir.
* **1.5. Standardize Commit Mesajları:** Commit mesajları net ve anlaşılır olmalıdır.
  * *Örnek Format:* `US025 - Student step definitions eklendi`
* **1.6. Sync & Conflict Yönetimi:** Pull Request açılmadan önce, yerel (local) branch'in güncel `main` branch ile senkronize edildiğinden (`pull/rebase`) ve çakışma (conflict) olmadığından emin olunmalıdır.

---

## 2. 🏗️ Proje Mimarisi & Sınıf Yönetimi

* **2.1. Çekirdek Sınıf Koruması:** Başlangıçta verilen çekirdek `Utils` (Driver, ConfigReader vb.) sınıfları kesinlikle değiştirilemez. Ortak bir ihtiyaç veya hata durumunda Team Lead ile iletişime geçilmelidir.
* **2.2. Esnek Sınıf Oluşturma:** Ekip üyeleri ihtiyaç duydukları kadar yeni `Page`, `StepDefinition` ve yardımcı `Utils` sınıfı oluşturabilirler.
* **2.3. Bireysel Runner Sınıfları:** Her tester arkadaşımız kendisine özel bir `Runner` sınıfı oluşturmalıdır (`Runner_Ahmet`, `Runner_Mehmet` vb.). Ortak `Runner` sınıfına müdahale edilmemeli ve kişisel test koşum ayarları commit edilmemelidir.

---

## 3. 🥒 Cucumber & BDD Standartları

* **3.1. Rol Bazlı Adım (Step) Yazımı:** Cucumber adımlarında (Steps) sprintte verilen rol ile başlanmalıdır:
  * `Given Student kullanıcısı hesabına giriş yapar`
  * `When Customer kullanıcısı ürünü sepete ekler`
  * `Then Admin kullanıcısı paneli görüntüler`
* **3.2. Etiketleme (Tagging):** Feature dosyalarındaki her senaryoya ilgili User Story numarası ve roller eklenmelidir (`@US025`, `@student`, `@smoke` vb.).
* **3.3. Reusable Steps:** Mükerrer (duplicate) step tanımlarından kaçınılmalı, mevcut tekrar kullanılabilir adımlar tercih edilmelidir.
* **3.4. Kendi localinizde gecmeyen testleri push yapmayınız.

---

## 4. 🧹 Kod Kalitesi & Temizlik

* **4.1. Hardcode Yasağı:** Kod içerisinde sabit veri (hardcoded data) kullanılmamalıdır. Test verileri `configuration.properties` veya test data dosyalarından çekilmelidir.
* **4.2. Clean Code:** Commit öncesinde gereksiz yorum satırları, `System.out.println()` ifadeleri ve kullanılmayan `import`lar temizlenmelidir.

---
> 💡 *Not: Proje akışında karşılaşılan tüm teknik aksaklıklar ve öneriler için Team Lead ile iletişime geçebilirsiniz.*