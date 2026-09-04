
@US001VerifyTitle
Feature: Bir kullanici olarak site ana sayfasina erisim saglayabilmek istiyorum.

  @US001TC01VerfiyTitle
  Scenario: URL girildiğinde ana sayfa erişilebilir olmalıdır.
    * Student kullanicisi "instuLearn" sayfasina gider

  @US002TC02VerifyTitle
  Scenario: Siteye girerken title'ın ' Home | InstuLearn ' olduğundan emin olunmalıdır.
    * Student kullanicisi "instuLearn" sayfasina gider
    * Student kullanicisi title in "Home | InstuLearn" oldugunu dogrular

