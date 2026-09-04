Feature: Bir kullanici olarak siteye kaydoldugum email ve password bilgilerimle login olabilmek istiyorum.

  @US005LoginWithMethod
  Scenario: Login formuna gecerli bilgiler girilip Login butonuna tiklandiginda sisteme giris yapilabilmeli.
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "studentMail" ve "password" ile siteye giris yapar
    * Student kullanicisi "Log out" butonuna tiklar
    * Student kullanicisi "Start Learning" butonunun gorunurlugu ile cikis yapildigini dogrular

  @US005PozitiveLogin
  Scenario: Login formuna gecerli bilgiler girilip Login butonuna tiklandiginda sisteme giris yapilabilmeli.
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "Login" butonuna tiklar
    * Student kullanicisi "Email" kutusuna "studentMail" yazar
    * Student kullanicisi "Password" kutusuna "password" yazar
    * Student kullanicisi "submit" butonuna tiklar
    * Student kullanicisi "panel" sayfasinda oldugunu dogrular
    * Student kullanicisi profil isminin "ahmet" oldugunu dogrular
    * Student kullanicisi "Log out" butonuna tiklar
    * Student kullanicisi "Start Learning" butonunun gorunurlugu ile cikis yapildigini dogrular