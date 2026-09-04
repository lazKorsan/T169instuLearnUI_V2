
Feature: Bir kullanici olarak site ici islemlerini kolaylikla yapabilmek icin
  ana sayfa ust barinda kolay linklerin bulunmasini istiyorum.

  @US002TC01VerifyHomeButton
  Scenario: Site ust barinda kolay linkler (Categories, Home, Courses, Instructors, Store,Blog) goruntulenmeli.
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "Categories" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Home" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Courses" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "İnstructors" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Store" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Blog" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Categories" butonunun gorunebilir ve tiklanabilir oldugunu test eder

  @US002TC02CoursesLink
  Scenario: Kolay linkler   Courses ilgili sayfaya yonlendirme yapmalı upcoming,free,discount,download butonları aktif olmalı
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "Courses" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Courses" butonuna tiklar
    * Student kullanicisi "newest" sayfasinda oldugunu dogrular
    * Student kullanicisi "Upcoming" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Free" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Discount" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Download" butonunun gorunebilir ve tiklanabilir oldugunu test eder

  @US002TC03instructorsLink
  Scenario: Kolay linkler   Instructors ilgili sayfaya yonlendirme yapmalı Available for Meetings, Free Meetings, Discount butonları aktif olmalı
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "instructors" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "instructors" butonuna tiklar
    * Student kullanicisi "instructors" sayfasinda oldugunu dogrular
    * Student kullanicisi "Discount" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Available for Meetings" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Free Meetings" butonunun gorunebilir ve tiklanabilir oldugunu test eder

  @US002TC04StoreLink
  Scenario: Kolay linkler   Store ilgili sayfaya yonlendirme yapmalı Free,Free Shipping,Discount butonları aktif olmalı
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "Store" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Store" butonuna tiklar
    * Student kullanicisi "products" sayfasinda oldugunu dogrular
    * Student kullanicisi "Discount" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Free" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Free Shipping" butonunun gorunebilir ve tiklanabilir oldugunu test eder

  @US002TC05BlogLink
  Scenario: Kolay linkler   Store ilgili sayfaya yonlendirme yapmalı Free,Free Shipping,Discount butonları aktif olmalı
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "Blog" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Blog" butonuna tiklar
    * Student kullanicisi "blog" sayfasinda oldugunu dogrular
    * Student kullanicisi blog sayisini consola yazdirir



  @US002TC06NotificationCartButton
  Scenario: Card ve Notifications buttonları görünür ve aktif olmalı.
  ( Bu testin dogru bir şekilde geçmesi için siteye giris yapılmıs olamli)
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "studentMail" ve "password" ile siteye giris yapar
    * Student kullanicisi "Logo" butonuna tiklar
    * Student kullanicisi "Card" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Notification" butonunun gorunebilir ve tiklanabilir oldugunu test eder


  @US002TC07SearchBox
  Scenario: Search Text Box goruntulenmeli ve arama yapilabilmeli.
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "Search..." kutusuna "Math" yazar
    * Student kullanicisi "Search" butonuna tiklar
    * Student kullanicisi "Math" sayfasinda oldugunu dogrular
    * Student kullanicisi "Math" arama sonucu yazisini consola yazdirir
    * Student kullanicisi "Math" arama sonucu yazi ile course sayisini dogrular

  @US002TC08VerifyStartLearningButton
  Scenario: Start learning ikonu görünür olmalı ve tıklandıgında ilgili sayfaya yonlendirmeli
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "Start learning" butonunun gorunebilir ve tiklanabilir oldugunu test eder

  @US002TC09VerifyLoginButton
  Scenario: Login  linkleri görünür olmalı ve tıklandıgında ilgili sayfaya yönlendirilmeli
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "Login" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Login" butonuna tiklar
    * Student kullanicisi "login" sayfasinda oldugunu dogrular

  @US002TC10VerifyRegisterButton
  Scenario:  Register linkleri görünür olmalı ve tıklandıgında ilgili sayfaya yönlendirilmeli
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "Register" butonunun gorunebilir ve tiklanabilir oldugunu test eder
    * Student kullanicisi "Register" butonuna tiklar
    * Student kullanicisi "register" sayfasinda oldugunu dogrular







