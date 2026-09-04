
Feature: Bir kullanici olarak sitenin fonksiyonlarindan faydalanabilmek icin siteye kayit olabilmek istiyorum

  @US005TC01ResimElementi
  Scenario: Register sayfasinin sol bolumunde ilgili resim gorunur olmali.
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "Register" butonuna tiklar
    * Student kullanicisi register sayfasinda resim elementinin gorunur oldugunu test eder

  @US005TC01SignUpForm
  Scenario: Register sayfasinin sol bolumunde ilgili resim gorunur olmali.
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "Register" butonuna tiklar
    * Student kullanicisi register sayfasinda Sign Up formunun gorunur oldugunu test eder

  @US005TC02Register
  Scenario: Bir kullanıcı olarak siteye kayıt olabilmek istiyorum
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "userName" ve "password" ile siteye kayt olur
    * Student kullanicisi profil ismi "userName" ile hesap olusturuldugunu dogrular