Feature: Akakçe Uygulaması Üzerinde Laptop Arama

  Scenario: Laptop arama, filtreleme ve en yüksek fiyata göre sıralama
    Given uygulamayı konuk kullanıcı olarak açtım
    When Laptop yazar ve klavyeden enter basarım
    And filtre butonuna tıklarım
    And bilgisayar donanım alt kategorisini seçerim
    And ürünleri gör butonuna tıklarım
    And sıralama butonuna tıklarım
    And en yüksek fiyatı seçerim
    And 10. ürüne tıklarım
    And ürüne git butonuna tıklarım
    Then satıcıya git butonunu görürüm
