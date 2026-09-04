Feature: Bir kullanici olarak siteye kaydoldugum email ve password bilgilerimle login olabilmek istiyorum.
  @US047TC09PozitiveLogin
  Scenario: Login formuna gecerli bilgiler girilip Login butonuna tiklandiginda sisteme giris yapilabilmeli.
    * Visitor kullanicisi "url" sayfasina gider
    * Visitor kullanicisi "Login" butonuna tiklar
    * Visitor kullanicisi "login" sayfasinda oldugunu dogrular
    * Visitor kullanicisi "Email" kutusuna "studentMail" yazar
    * Visitor kullanicisi "Password" kutusuna "password" yazar

    * Visitor kullanicisi "Submit" butonuna tiklar
    * Visitor kullanicisi "panel" uzantisi ile sayfaya giris yapildigini dogrular



  @enumKullanimi
  Scenario: getEnum list kullanimi
    * Enum kullanicisi "url" sayfasina gider
    * Enum kullanicisi "Login" buttona basar

  @ConfigReaderKullanimi
  Scenario: ConfigReader kullanimi
    * Customer kullanicisi consola "studentMail" yazdirir
    * Customer kullanicisi consola "Team169 Merhaba Nasilsiniz?" yazdirir
















