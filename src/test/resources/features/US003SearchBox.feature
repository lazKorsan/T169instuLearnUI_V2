
Feature: Bir kullanici olarak anasayfa da body bölümünde kendime uygun eğitimleri arastırabilecegim bir alan olmasını istiyorum

  @US003TC01YaziElementi
  Scenario: Anasayfa body bölümünde 'Transform Your Future with InstuLearn...' baslıgı görünür olmalı
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "Transform Your Future with InstuLearn..." yazi elementinin gorunur oldugunu test eder

  @US003TC02SearchBox
  Scenario: Search Text Box goruntulenmeli ve arama yapilabilmeli.
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi "Search..." kutusuna "Math" yazar
    * Student kullanicisi "Search" butonuna tiklar
    * Student kullanicisi "Math" sayfasinda oldugunu dogrular
    * Student kullanicisi "Math" arama sonucu yazisini consola yazdirir
    * Student kullanicisi "Math" arama sonucu yazi ile course sayisini dogrular
