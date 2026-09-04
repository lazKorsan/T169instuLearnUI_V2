# 📚 InstuLearn – User Stories

> Proje kapsamındaki tüm kullanıcı hikayeleri, kabul kriterleri ve öncelik bilgileri.

---

## Öncelik Göstergesi

| Sembol | Öncelik |
|--------|---------|
| 🔴 | Highest |
| 🟠 | High |
| 🟡 | Medium |
| 🟢 | Low |

---

## 🌐 Visitor (Ziyaretçi)

### US_001 – Ana Sayfaya Erişim
**Rol:** Visitor | **Öncelik:** 🔴 Highest | **Puan:** 1 | **Atanan:**

> Bir kullanıcı olarak site ana sayfasına erişim sağlayabilmek istiyorum.

**Kabul Kriterleri:**
- URL girildiğinde ana sayfa erişilebilir olmalıdır.
- Siteye girerken title'ın `Home | InstuLearn` olduğu doğrulanmalıdır.

---

### US_002 – Üst Navigasyon Barı
**Rol:** Visitor | **Öncelik:** 🟡 Medium | **Puan:** 3 | **Atanan:** Ahmet

> Bir kullanıcı olarak site içi işlemlerini kolaylıkla yapabilmek için ana sayfa üst barında kolay linklerin bulunmasını istiyorum.

**Kabul Kriterleri:**
- Üst barda kolay linkler (Categories, Home, Courses, Instructors, Store, Blog) görüntülenmeli.
- Linkler ilgili sayfaya yönlendirmeli; açılan sayfalarda filtreleme ikonları görünür ve aktif olmalı.
- Site logosu sayfanın sol üstünde bulunmalı ve tıklanınca ana sayfa yenilenmeli.
- Cart ve Notifications butonları görünür ve aktif olmalı.
- Search Text Box görüntülenmeli ve arama yapılabilmeli.
- Start learning ikonu görünür olmalı ve tıklandığında ilgili sayfaya yönlendirmeli.
- Login | Register linkleri görünür olmalı ve ilgili sayfaya yönlendirmeli.

---

### US_003 – Ana Sayfa Arama Alanı
**Rol:** Visitor | **Öncelik:** 🟠 High | **Puan:** 1 | **Atanan:** Ahmet

> Bir kullanıcı olarak anasayfada body bölümünde kendime uygun eğitimleri arayabileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- Anasayfa body bölümünde `Transform Your Future with InstuLearn...` başlığı görünür olmalı.
- Search box görünür ve aktif olmalı.
- Search butonu görünür ve aktif olmalı.

---

### US_004 – Kayıt Olma
**Rol:** Visitor | **Öncelik:** 🔴 Highest | **Puan:** 3 | **Atanan:** Ahmet

> Bir kullanıcı olarak sitenin fonksiyonlarından faydalanabilmek için siteye kayıt olabilmek istiyorum.

**Kabul Kriterleri:**
- Üst barda Register linki görünür ve aktif olmalı.
- Register sayfasında sol bölümde ilgili resim, sağ bölümde Signup formu görünür olmalı.
- Signup formu içinde Account type butonları (Student, Instructor, Organization) seçilebilir olmalı.
- Zorunlu alanlar: Email, Full Name, Password, Retype Password.
- Terms & Rules checkbox'ı seçilmeden kayıt tamamlanamamalı.
- Email geçerli format kriterlerini sağlamalı.
- Şifre minimum 8 karakter olmalı.
- Signup sayfasından Login sayfasına geçiş yapılabilmeli.

---

### US_005 – Giriş Yapma (Login)
**Rol:** Register | **Öncelik:** 🔴 Highest | **Puan:** 3 | **Atanan:** Ahmet

> Bir kullanıcı olarak siteye kayıtlı email ve password bilgilerimle login olabilmek istiyorum.

**Kabul Kriterleri:**
- Üst barda Login linki görünür ve aktif olmalı.
- Login sayfasında sol bölümde resim, sağ bölümde Login formu görünür olmalı.
- Email ve password text box'lar görünür ve aktif olmalı.
- Login butonuna tıklandığında sisteme giriş yapılabilmeli.
- Geçersiz bilgi girildiğinde hata mesajı görüntülenmeli.
- Forgot your password? ve Register sayfalarına geçiş yapılabilmeli.

---

### US_006 – Ana Sayfa Kurs Alanı
**Rol:** Visitor | **Öncelik:** 🟡 Medium | **Puan:** 3 | **Atanan:**

> Bir kullanıcı olarak sitenin body bölümünde kurslar ile ilgili bilgi alabileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- **Featured Courses** başlığı altında öne çıkan kurslar görüntülenebilmeli.
- **Newest Courses** başlığı altında yeni çıkan kurslar (fiyat, saat, tarih, instructor bilgileri) görüntülenebilmeli.
- **Latest Bundles** başlığı altında son çıkan paketler görüntülenebilmeli.
- **Upcoming Courses** başlığı altında yaklaşan kurslar görüntülenebilmeli.
- **Trending Categories** başlığı altında trend kategoriler görüntülenebilmeli.
- **Bestselling Courses**, **Free Courses**, **Discounted Courses** başlıkları altındaki kurslar ilgili sayfaya yönlendirmeli.
- Tüm bölümlerde "View All" ikonu ve "Go to Card" ikonları aktif olmalı.

---

### US_007 – Mağaza Alanı
**Rol:** Visitor | **Öncelik:** 🟡 Medium | **Puan:** 2 | **Atanan:**

> Bir kullanıcı olarak anasayfada eğitimlerimle ilgili ürünleri inceleyip satın alabileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- Store Products başlığı altında ürünlerin aktığı slider görüntülenebilmeli (fiyat, beğeni puanı, sepete ekle ikonu).
- All Products butonu görünür ve aktif olmalı; tıklandığında tüm ürünler listelenmeli.

---

### US_008 – Üyelik Planları
**Rol:** Visitor | **Öncelik:** 🟡 Medium | **Puan:** 1 | **Atanan:**

> Bir kullanıcı olarak anasayfada üyelik işlemlerimi gerçekleştirebileceğim bir bölüm olmasını istiyorum.

**Kabul Kriterleri:**
- `Subscribe Now!` başlığı görünür olmalı.
- Bronze, Gold, Silver üyelik kartları görünür olmalı; tıklandığında Login sayfasına yönlendirmeli.

---

### US_009 – Eğitmen / Kuruluş Alanı
**Rol:** Visitor | **Öncelik:** 🟡 Medium | **Puan:** 1 | **Atanan:**

> Bir kullanıcı olarak (eğitmen ya da kuruluş olarak) anasayfada işlemlerimi yönetebileceğim bir bölüm olmasını istiyorum.

**Kabul Kriterleri:**
- `Become an Instructor` başlığı görünür ve işlevsel olmalı.
- "Become an Instructor" ve "Registration Packages" butonları aktif olmalı, ilgili sayfaya yönlendirmeli.

---

### US_010 – Instructor Olarak Giriş
**Rol:** Visitor | **Öncelik:** 🟠 High | **Puan:** 1 | **Atanan:** Huseyin

> Bir kullanıcı olarak sitede instructor olarak giriş yapabileceğim bir bölüm olmasını istiyorum.

**Kabul Kriterleri:**
- `Home - Join as Instructor` başlığı görünür ve aktif olmalı; tıklandığında Login sayfasına yönlendirmeli.

---

### US_011 – Kulüp Puanları Alanı
**Rol:** Visitor | **Öncelik:** 🟡 Medium | **Puan:** 1 | **Atanan:**

> Bir kullanıcı olarak anasayfada avantajların olduğu bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- `Earn Club Points` başlığı görünür olmalı.
- Rewards ve Points Club butonları aktif olmalı, ilgili sayfaya yönlendirmeli.
- İndirim kuponu görünür ve aktif olmalı.

---

### US_012 – Instructor Listesi ve Randevu
**Rol:** Visitor | **Öncelik:** 🟠 High | **Puan:** 2 | **Atanan:**

> Bir kullanıcı olarak sitede instructorları inceleyip randevu oluşturabileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- `Instructors` başlığı görünür olmalı.
- All Instructors butonu aktif olmalı.
- Instructor bilgi kartlarının olduğu slider görünür ve aktif olmalı.
- Kartlarda isim, beğeni puanı görünür olmalı; Reserve a live meeting butonu tıklandığında rezervasyon yapılabilmeli.

---

### US_013 – Sertifika & Randevu Widget'ları
**Rol:** Visitor | **Öncelik:** 🟠 High | **Puan:** 1 | **Atanan:**

> Bir kullanıcı olarak anasayfada sertifika ve randevu işlemlerimi yönetebileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- `Validate Certificates` kartı görünür olmalı; tıklandığında Certificate Validation sayfasına yönlendirmeli.
- `Reserve a Meeting` kartı görünür olmalı; tıklandığında Instructor sayfasına yönlendirmeli.

---

### US_014 – Testimonials
**Rol:** Visitor | **Öncelik:** 🟠 High | **Puan:** 1 | **Atanan:**

> Bir kullanıcı olarak anasayfada sitede çalışılan şirketler hakkında bilgi edinebileceğim bir bölüm olmasını istiyorum.

**Kabul Kriterleri:**
- `Testimonials` başlığı altında şirket yorumlarının paylaşıldığı slider görünür ve aktif olmalı.
- Yorum kartlarında şirket adı, puanlaması ve yorumu görünür olmalı.

---

### US_015 – Organizasyonlar Alanı
**Rol:** Visitor | **Öncelik:** 🟠 High | **Puan:** 1 | **Atanan:**

> Bir kullanıcı olarak anasayfada organizasyonlar tarafından açılan kursları görüp seçebilmek istiyorum.

**Kabul Kriterleri:**
- `Organizations` başlığı görünür olmalı.
- All Organizations butonu aktif olmalı.
- Organizasyonların açtığı kursların bulunduğu slider görünür ve aktif olmalı.

---

### US_016 – Blog Bölümü
**Rol:** Visitor | **Öncelik:** 🟡 Medium | **Puan:** 1 | **Atanan:**

> Bir kullanıcı olarak siteyle bilgi edinebilmek için anasayfada Blog bölümü olmasını istiyorum.

**Kabul Kriterleri:**
- `Blog` başlığı görüntülenebilmeli.
- Blog Posts butonu aktif olmalı.
- Blog yazılarının bulunduğu slider görünür ve aktif olmalı.

---

### US_017 – Şifre Sıfırlama
**Rol:** Instructor / Student | **Öncelik:** 🟠 High | **Puan:** 2 | **Atanan:**

> Kayıtlı bir kullanıcı olarak şifremi unuttuğumda işlem yapabileceğim bir sayfa istiyorum.

**Kabul Kriterleri:**
- Login sayfasında `Forgot your password?` linki aktif olmalı.
- Password Recovery sayfasında email text box görünür ve aktif olmalı.
- Kayıtlı mail girildiğinde reset bağlantısı gönderildiğine dair başarı mesajı görüntülenmeli.
- Reset Password butonu görünür ve aktif olmalı.
- Sayfadan Login sayfasına geçiş yapılabilmeli.

---

### US_018 – Footer
**Rol:** Visitor | **Öncelik:** 🟠 High | **Puan:** 2 | **Atanan:**

> Bir kullanıcı olarak faydalı linklere ve sosyal medya hesaplarına erişebilmek için footer bölümünün olmasını istiyorum.

**Kabul Kriterleri:**
- Footer bölümünde faydalı linkler (About Us, Contact Us, Certificate Validation, Terms & Rules) görünür ve aktif olmalı.
- Site mail ve telefon bilgileri görünür olmalı.
- Bülten aboneliği yapılabilmeli.
- Sosyal medya ikonları (Twitter, WhatsApp, Instagram, Facebook) aktif olmalı.
- `All rights are reserved for learning management system platform` text'i görünür olmalı.
- Footer'dan ana sayfanın en üstüne hızlı erişim sağlanabilmeli.

---

## 🏢 Organization (Organizasyon)

### US_019 – Organization Dashboard
**Rol:** Organization | **Öncelik:** 🟠 High | **Puan:** 3

> Kayıtlı bir kullanıcı (organizasyon) olarak kendime ait bir dashboard sayfası olmasını istiyorum.

**Kabul Kriterleri:**
- Ana sayfada dashboard'a giden link olmalı.
- Çevrimdışı durumu etkinleştir butonu görünür ve aktif olmalı.
- Sidebar'da Instructors, Students, Courses, Bundles, Meetings, Quizzes, Certificates, Financial, Support, Marketing, Noticeboard, Notifications, Settings, My Profile, Log Out linkleri aktif olmalı.
- Dashboard'da View All Events, Account Balance, Pending Meetings, Support Messages, Monthly Sales, Comments linkleri aktif olmalı.
- Aylık satışlar tablosu ve ilan boardu görüntülenebilmeli.

---

### US_020 – Instructor Yönetimi
**Rol:** Organization | **Öncelik:** 🟠 High | **Puan:** 3

> Dashboard panelimde instructorlar ile ilgili işlemlerimi yapabileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Instructors altında New ve List linkleri aktif olmalı.
- New ile yeni instructor kaydı (Email, Name, Password, RetypePassword, Phone) yapılabilmeli.
- List ile instructor listesi görüntülenmeli; filtreleme ve yeni kayıt işlemleri yapılabilmeli.

---

### US_021 – Student Yönetimi
**Rol:** Organization | **Öncelik:** 🟠 High | **Puan:** 3

> Dashboard panelimde students ile ilgili işlemlerimi yapabileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Students altında New ve List linkleri aktif olmalı.
- New ile yeni student kaydı (Email, Name, Password, RetypePassword, Phone) yapılabilmeli.
- List ile student listesi görüntülenmeli; filtreleme ve yeni kayıt işlemleri yapılabilmeli.

---

### US_022 – Kurs Yönetimi
**Rol:** Organization | **Öncelik:** 🟡 Medium | **Puan:** 3

> Dashboard panelimde kursları yönetebileceğim bir sayfa olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Courses altında New linki aktif olmalı.
- New Course sayfasında Basic Info, Extra Information, Pricing, Content, Ön Koşullar, FAQ, Sınav & Sertifikasyon bölümleri doldurulabilmeli.
- Private, Next, Previous, Save as Draft, Delete butonları aktif olmalı.
- My Courses, Invited Courses, My Purchases, My Course Comments, My Comments, Favorites menü başlıkları ilgili içerikleri listelenmeli.

---

### US_023 – Kurs Paketi Yönetimi
**Rol:** Organization / Instructor | **Öncelik:** 🟠 High | **Puan:** 3 | **Atanan:**

> Kayıtlı bir kullanıcı olarak kurs paketi oluşturabileceğim ve yönetebileceğim bir sayfa olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Course Bundles altında New ve My Bundles linkleri aktif olmalı.
- New ile Basic Info, Extra Information, Pricing, Content, FAQ, Message to Reviewer doldurulabilmeli.
- Terms & Rules checkbox'ı görünür ve aktif olmalı.
- Oluşturulan paket My Bundles listesinde görüntülenebilmeli ve düzenlenebilmeli.

---

### US_024 – Toplantı Yönetimi
**Rol:** Organization / Instructor | **Öncelik:** 🔴 Highest | **Puan:** 3 | **Atanan:**

> Kayıtlı bir kullanıcı olarak toplantılarımı yönetebileceğim bir sayfa olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Meetings altında Rezervasyonlarım, Taleplerim, Ayarlar linkleri aktif olmalı.
- Rezervasyonlarım: toplantı istatistikleri ve toplantı listesinde filtreleme, düzenleme, silme işlemleri yapılabilmeli.
- Talepler: toplantı talep listesinde Add to Calendar, Contact Instructor, Finish Meeting işlemleri yapılabilmeli.
- Ayarlar: günlük zaman dilimi, yüz yüze/grup toplantı saatlik ücret ve katılımcı sayısı ayarlanabilmeli.

---

### US_025 – Sınav Yönetimi
**Rol:** Organization / Instructor | **Öncelik:** 🔴 Highest | **Puan:** 3 | **Atanan:**

> Kayıtlı bir kullanıcı olarak sınav oluşturup sınavları yönetebileceğim bir sayfa olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Quizzes altında New Quiz, List, Results, My Results, Not Participated linkleri aktif olmalı.
- New Quiz: Çoktan seçmeli ve açıklamalı sorular eklenebilmeli.
- List: Filtreleme, düzenleme, silme işlemleri yapılabilmeli.
- Results: Öğrenci sonuçları filtrelenebilmeli ve silinebilmeli.
- My Results: Kendi sonuçlarım görüntülenebilmeli, view answer ve course işlemleri yapılabilmeli.
- Not Participated: Girilmemiş sınavlar listesinde start ve course page işlemleri yapılabilmeli.

---

### US_026 – Sertifika Yönetimi
**Rol:** Organization / Instructor | **Öncelik:** 🟡 Medium | **Puan:** 3

> Kayıtlı bir kullanıcı olarak sitede sertifikalarımı görüp yönetebileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Certificates altında List, Achievements, Certificate Validation, Completion Certificates linkleri aktif olmalı.
- List: Sertifika istatistikleri ve filtrelenmiş aktif sertifikalar görüntülenebilmeli.
- Achievements: Başarı istatistikleri ve sertifika işlemleri yapılabilmeli.
- Certificate Validation: Geçerli sertifika ID ve captcha ile doğrulama yapılabilmeli.
- Completion Certificates: Tamamlanmış sertifikalar listelenebilmeli.

---

### US_027 – Finansal Yönetim
**Rol:** Organization / Instructor | **Öncelik:** 🟠 High | **Puan:** 3 | **Atanan:**

> Kayıtlı bir kullanıcı olarak sitede finansal işlemlerimi görüp yönetebileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Financial altında Sales Report, Financial Summary, Payout, Charge Account, Subscribe linkleri aktif olmalı.
- Sales Report: Satış istatistikleri, liste filtreleme, düzenleme, silme işlemleri yapılabilmeli.
- Financial Summary: Finansal belgeler listesi görüntülenebilmeli.
- Payout: Hesap tanımlanabilmeli ve ödeme isteği oluşturulabilmeli.
- Charge Account: Ödeme yöntemi ve tutar girilerek cüzdana yükleme yapılabilmeli.
- Subscribe: Aktif plan görüntülenebilmeli ve yeni plan oluşturulabilmeli.

---

### US_028 – Teknik Destek
**Rol:** Organization / Instructor | **Öncelik:** 🟡 Medium | **Puan:** 3 | **Atanan:**

> Kayıtlı bir kullanıcı olarak sitede teknik destek alabileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Support altında New, Courses Support, Tickets linkleri aktif olmalı.
- New: Konu ve tür bilgisi girilerek destek isteği gönderilebilmeli.
- Courses Support: Destek özet boardları ve mesaj geçmişi filtrelenebilmeli, düzenlenebilmeli, silinebilmeli.
- Tickets: Açık ticket'lar listede görüntülenmeli; mesaj gönderilebilmeli, ticket kapatılabilmeli.

---

### US_029 – Pazarlama
**Rol:** Organization / Instructor | **Öncelik:** 🟠 High | **Puan:** 2 | **Atanan:**

> Kayıtlı bir kullanıcı olarak güncel indirim ve promosyonlarla ilgili işlem yapabileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Marketing altında Discounts ve Promotions linkleri aktif olmalı.
- Discounts: Yeni kurs indirimleri oluşturulabilmeli.
- Promotions: Promosyon planları görüntülenebilmeli ve kayıt yapılabilmeli.

---

### US_030 – Bildirim Yönetimi
**Rol:** Organization / Instructor | **Öncelik:** 🟡 Medium | **Puan:** 3

> Kayıtlı bir kullanıcı olarak bildirim geçmişimi görüp yönetebileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Noticeboard altında History, New, Course Notices, New Course Notice linkleri aktif olmalı.
- History: Bildirim istatistikleri ve liste görüntülenebilmeli; filtreleme, düzenleme, silme yapılabilmeli.
- New: Yeni bildirim oluşturulabilmeli.
- Course Notice & New Course Notice: Kurs bildirimleri görüntülenebilmeli ve yeni bildirim oluşturulabilmeli.

---

### US_031 – Profil Ayarları
**Rol:** Organization / Instructor | **Öncelik:** 🟠 High | **Puan:** 3

> Kayıtlı bir kullanıcı olarak profil bilgilerimi görüp yönetebileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Settings linki aktif olmalı.
- Basic Information, Images, About, Education, Experience, Skills bölümleri doldurulabilmeli / düzenlenebilmeli.
- Identity & Financial, Zoom API, Extra Information bölümleri yapılandırılabilmeli.
- Hesap silinebilmeli.

---

### US_032 – Profil Görüntüleme
**Rol:** Organization / Instructor | **Öncelik:** 🟡 Medium | **Puan:** 2 | **Atanan:**

> Kayıtlı bir kullanıcı olarak dashboardumda profil bilgilerimi görüntüleyebileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da My Profile linki aktif olmalı.
- Profil kartı görüntülenmeli; takibe eklenebilmeli.
- About, Courses, Instructors, Articles, Badges, Reserve a Meeting bilgileri görüntülenebilmeli.

---

## 🎓 Ortak Sayfalar (Tüm Kullanıcılar)

### US_033 – Kurslar Sayfası
**Rol:** Organization / Instructor | **Öncelik:** 🔴 Highest | **Puan:** 3 | **Atanan:**

> Kayıtlı bir kullanıcı olarak kursları görebileceğim, bilgi alabileceğim ve kurs seçebileceğim bir sayfa olmasını istiyorum.

**Kabul Kriterleri:**
- Anasayfada Courses linki aktif olmalı.
- Kurslar sayfasında search box ve buton görünür olmalı.
- Filtreleme işlemleri yapılabilmeli; kurs kartlarında fiyat, tarih, ders adı, instructor bilgileri görünmeli.
- İstenen kurs seçildiğinde add to cart ve buy now butonları aktif olmalı; kurs satın alma başarıyla gerçekleşmeli.

---

### US_034 – Instructors Sayfası
**Rol:** Organization / Instructor / Student | **Öncelik:** 🔴 Highest | **Puan:** 3 | **Atanan:**

> Kayıtlı bir kullanıcı olarak instructorları görebileceğim, bilgi alabileceğim ve randevu oluşturabileceğim bir sayfa olmasını istiyorum.

**Kabul Kriterleri:**
- Anasayfada Instructors linki aktif olmalı.
- Search box ve kategori seçimi yapılabilmeli.
- Instructor kartlarında fiyat, ders adı, beğeni bilgileri görünmeli.
- Instructor'dan randevu talebi başarılı şekilde oluşturulabilmeli.
- My Panel butonu görünür ve aktif olmalı.

---

### US_035 – Mağaza Sayfası
**Rol:** Organization / Instructor / Student | **Öncelik:** 🟠 High | **Puan:** 3

> Kayıtlı bir kullanıcı olarak ürünleri görebileceğim ve ürün satın alabileceğim bir sayfa olmasını istiyorum.

**Kabul Kriterleri:**
- Anasayfada Store linki aktif olmalı.
- Ürünler sayfasında search box, filtreleme işlemleri yapılabilmeli.
- Ürün kartlarında fiyat, satıcı, ürün adı, açıklama bilgileri görünmeli.
- İstenen ürün seçildiğinde add to cart ve buy now butonları aktif olmalı; ürün satın alma başarıyla gerçekleşmeli.

---

### US_036 – Blog Sayfası
**Rol:** Instructor | **Öncelik:** 🟠 High | **Puan:** 2

> Kayıtlı bir kullanıcı olarak güncel bilgilere ve yorumlara erişebileceğim bir blog sayfası olmasını istiyorum.

**Kabul Kriterleri:**
- Anasayfada Blog linki aktif olmalı.
- Blog sayfasında search box ve kategori araması yapılabilmeli.
- Blog kartlarında tarih, konu, içerik, paylaşan kişi bilgileri görünmeli.
- Seçilen bloga yorum yapılabilmeli.

---

### US_037 – Bildirimler Sayfası
**Rol:** Organization / Instructor | **Öncelik:** 🟠 High | **Puan:** 2

> Kayıtlı bir kullanıcı olarak tüm bildirimleri görebileceğim bir sayfa olmasını istiyorum.

**Kabul Kriterleri:**
- Dashboard'da Notifications linki aktif olmalı.
- Bildirim listesinde View ikonu ile ayrıntılı görüntüleme yapılabilmeli.
- Daha fazla bildirim için sayfa geçişi sağlanabilmeli.
- "Mark all as read" ikonu görünür ve aktif olmalı.

---

### US_038 – Çıkış (Logout)
**Rol:** Organization / Instructor | **Öncelik:** 🔴 Highest | **Puan:** 1 | **Atanan:**

> Kayıtlı bir kullanıcı olarak siteden güvenli şekilde çıkış yapmak istiyorum.

**Kabul Kriterleri:**
- Dashboard'da Logout linki aktif olmalı; tıklandığında başarılı şekilde çıkış yapılabilmeli.
- Ana sayfa profil ikonunda da Logout linki görünür ve aktif olmalı.

---

## 👨‍🎓 Student (Öğrenci)

### US_039 – Instructor Olmak
**Rol:** Student | **Öncelik:** 🟡 Medium | **Puan:** 2

> Kayıtlı bir kullanıcı olarak (Student) bilgilerimi Instructor olarak değiştirebileceğim bir sayfa istiyorum.

**Kabul Kriterleri:**
- Anasayfada Become An Instructor butonu görünür ve aktif olmalı.
- Meslek seçimi yapılabilmeli.
- Gerekli form bilgileri (Account type, Payout account, Identity scan) doldurularak başarılı şekilde instructor olunabilmeli.

---

### US_040 – Student Dashboard
**Rol:** Student | **Öncelik:** 🟠 High | **Puan:** 3 | **Atanan:**

> Kayıtlı bir kullanıcı (Student) olarak kendime ait dashboard sayfası olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Courses, Meetings, Quizzes, Certificates, Financial, Support, Notifications, Settings, Log Out linkleri aktif olmalı.
- Dashboard body bölümünde Account Balance, Purchased Courses, Meetings, Support Messages, Comments linkleri aktif olmalı.
- İlan boardu ve aylık öğrenme tablosu görüntülenebilmeli.

---

### US_041 – Student Kurs Yönetimi
**Rol:** Student | **Öncelik:** 🔴 Highest | **Puan:** 2 | **Atanan:**

> Kayıtlı bir kullanıcı (Student) olarak kurslarımı görüp yönetebileceğim bir sayfa olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Courses altında My Purchases linki aktif olmalı.
- Seçilen kursun faturası görüntülenebilmeli.
- Kurs videolarına erişim sağlanabilmeli.
- Favorites listesinde eklenen kurslar görüntülenebilmeli ve silinebilmeli.

---

### US_042 – Student Toplantı Yönetimi
**Rol:** Student | **Öncelik:** 🟡 Medium | **Puan:** 2 | **Atanan:**

> Kayıtlı bir kullanıcı (Student) olarak toplantılarımı görüp yönetebileceğim bir sayfa olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Meeting altında My Reservations linki aktif olmalı.
- Seçilen rezervasyon için instructor ile görüşme sağlanabilmeli; meeting takvime eklenebilmeli ya da sonlandırılabilmeli.

---

### US_043 – Student Sınav Yönetimi
**Rol:** Student | **Öncelik:** 🟠 High | **Puan:** 2

> Kayıtlı bir kullanıcı olarak (Student) sınavlarım ile ilgili bir sayfa olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Quizzes altında My Results ve Not Participated linkleri aktif olmalı.
- My Results: İstatistikler ve filtrelenmiş sonuçlar görüntülenebilmeli.
- Not Participated: Girilmemiş sınavlar listesinde işlemler yapılabilmeli.

---

### US_044 – Student Sertifika Yönetimi
**Rol:** Student | **Öncelik:** 🔴 Highest | **Puan:** 3

> Kayıtlı bir kullanıcı olarak (Student) sitede sertifikalarımı görüp yönetebileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Certificates altında Achievements, Certificate Validation, Completion Certificates linkleri aktif olmalı.
- Achievements: Başarı istatistikleri ve filtrelenmiş sertifikalar görüntülenebilmeli.
- Certificate Validation: Geçerli sertifika ID ve captcha ile doğrulama yapılabilmeli.
- Completion Certificates: Tamamlanmış sertifikalar listelenebilmeli ve ilgili işlemler yapılabilmeli.

---

### US_045 – Student Finansal Yönetim
**Rol:** Student | **Öncelik:** 🔴 Highest | **Puan:** 3 | **Atanan:**

> Kayıtlı bir kullanıcı olarak (Student) sitede finansal işlemlerimi görüp yönetebileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Financial altında Financial Summary, Payout, Charge Account, Subscribe linkleri aktif olmalı.
- Payout: Hesap tanımlanabilmeli ve ödeme isteği oluşturulabilmeli.
- Charge Account: Ödeme yöntemi ve tutar girilerek cüzdana yükleme yapılabilmeli.
- Subscribe: Aktif plan görüntülenebilmeli ve yeni plan oluşturulabilmeli.

---

### US_046 – Student Destek
**Rol:** Student | **Öncelik:** 🔴 Highest | **Puan:** 3

> Kayıtlı bir kullanıcı olarak (Student) sitede support işlemlerimi görüp yönetebileceğim bir alan olmasını istiyorum.

**Kabul Kriterleri:**
- Sidebar'da Support altında New, Courses Support, Tickets linkleri aktif olmalı.
- New: Konu ve tür bilgisi girilerek destek isteği gönderilebilmeli.
- Courses Support: Destek boardları ve mesaj geçmişi görüntülenebilmeli, filtrelenebilmeli.
- Tickets: Açık ticket'lar listede görüntülenmeli; mesaj gönderilebilmeli, ticket kapatılabilmeli.

---

*Toplam: 46 User Story*
