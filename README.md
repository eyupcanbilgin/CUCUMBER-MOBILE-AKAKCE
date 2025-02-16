# AKAKCE Mobile Automation Framework

Bu proje, **Akakçe mobil uygulaması** üzerinde otomasyon testleri gerçekleştirmek için geliştirilmiş bir framework’tür. Proje, **Java, Appium, Selenium ve Cucumber** kullanılarak oluşturulmuş olup, **Page Object Model (POM)** mimarisiyle yapılandırılmıştır.

## 📌 Özellikler

- **Mobil Otomasyon**: Android cihazlarda uygulama testleri gerçekleştirir.
- **Cucumber BDD**: Test senaryoları **Gherkin** dilinde yazılarak iş birliği ve okunabilirlik sağlanır.
- **Page Object Model**: Sayfa etkileşimleri ayrı sınıflarda toplanarak kod bakımı kolaylaştırılır.
- **Singleton Driver Yönetimi**: `DriverFactory` ile tek bir `AppiumDriver` örneği yönetilir.
- **Swipe/Scroll İşlemleri**: Özel swipe ve scroll yöntemleriyle liste elemanları arasında gezinme sağlanır.
- **Logging**: Test adımları **Log4j** kullanılarak loglanır.

---

## 📂 Proje Yapısı

```
DIAS-MOBILE/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── drivers/DriverFactory.java   # AppiumDriver yönetimi
│   │   │   ├── hooks/MobileHooks.java       # Test öncesi & sonrası işlemler
│   │   │   ├── pages/HomePage.java          # Ana sayfa Page Object
│   │   │   ├── steps/HomeSteps.java         # Cucumber step tanımları
│   │   │   ├── runners/MobileTestRunner.java # Testleri çalıştırma
│── src/
│   ├── test/
│   │   ├── resources/
│   │   │   ├── features/                    # Gherkin formatında feature dosyaları
│── pom.xml                                  # Proje bağımlılıkları
│── README.md                                # Dokümantasyon
```

---

## 🛠 Gereksinimler

- **Java JDK**: 21 veya daha üst bir sürüm.
- **Maven**: Proje bağımlılıkları ve derleme yönetimi için.
- **Appium Server**: Yüklü ve çalışır durumda olmalıdır (**Varsayılan URL:** `http://127.0.0.1:4723`).
- **Android Cihaz/Emülatör**: Hedef uygulama (`com.akakce.akakce`) yüklü ve hazır durumda.
- **Android SDK ve Cihaz Sürücüleri**: Bağlantı ve testler için gereklidir.

---

## 📌 Bağımlılıklar

Proje **Maven** kullanılarak yönetilmektedir. Ana bağımlılıklar:

- **Appium Java Client**
- **Selenium Java**
- **Cucumber Java & JUnit**
- **Log4j**
- **PicoContainer** (*Cucumber default DI*)

Tüm bağımlılıklar `pom.xml` dosyasında belirtilmiştir.

---

## 🚀 Kurulum

### Depoyu Klonlayın:
```bash
git clone <repository-url>
cd DIAS-MOBILE
```

### Bağımlılıkları Yükleyin:
```bash
mvn clean install
```

### Appium Server'ı Başlatın:
```bash
appium
```

### Android Cihaz/Emülatörü Bağlayın:
- **Fiziksel cihaz** kullanıyorsanız **USB hata ayıklamasını etkinleştirin**.
- Veya uygun bir **emülatörü başlatın**.

---

## 🏃‍♂️ Testleri Çalıştırma

Testleri çalıştırmak için iki yöntem kullanabilirsiniz:

### **Maven Komutu:**
```bash
mvn test
```

### **IDE (Örneğin IntelliJ IDEA):**
1. **Projeyi açın**.
2. `runners.MobileTestRunner` sınıfını **çalıştırın**.

---

## 🔄 Swipe ve Scroll İşlemleri

`HomePage.java` dosyasında, **Appium’un `mobile: scrollGesture`** komutu kullanılarak **swipe işlemleri** uygulanmıştır. Swipe bölgesi, cihazın ortasından başlayıp, **alt-yukarı** yönde kaydıracak şekilde ayarlanmıştır. **(Gerekirse parametrelerde değişiklik yapabilirsiniz.)**

---

## 📜 Logging

Test senaryoları sırasında **detaylı loglama** yapılır. **Log4j** kullanılarak;

- **Senaryo başlangıç & bitişleri**,
- **Hata durumları**,
- **Önemli adımlar**

📌 **Konsola ve dosyalara loglanmaktadır.**

---

## 💡 Katkıda Bulunma

Her türlü **katkı** ve **iyileştirme önerileri** memnuniyetle karşılanır. Lütfen **projeyi fork'layın** ve **pull request açın**.

---

📧 **İletişim için:** `your-email@example.com`

---

**📌 Lisans:** MIT License
