# Zoom Toplantı Otomasyonu 

## 1. Projenin Amacı
Bu proje, kullanıcı kaydı ve giriş işlemlerinin ardından Zoom API kullanarak toplantı oluşturma işlemini otomatikleştiren ve sonrasında toplantının ev sahibine toplantının raporunu mail olarak gönderen bir uygulamadır. Kullanıcı önce kaydını oluşturur ve giriş yaptıktan sonra toplantı oluşturma ekranına yönlendirilir. Burada, gerekli bilgiler Zoom API'sine iletilerek toplantı oluşturulur.

## 2. Kullanıcı Kayıt ve Giriş İşlemi
Proje, iki ana HTML formundan oluşur:
- **Kayıt (User Registration)**: Kullanıcı, kendi e-posta ve diğer bilgileriyle kaydını oluşturur. Bu bilgiler backend servisi tarafından işlenir ve veritabanına kaydedilir.
- **Giriş (User Login)**: Kullanıcı, e-posta adresiyle giriş yapar. Backend doğrulama işlemini yapar ve giriş başarılı olursa kullanıcı toplantı oluşturma ekranına yönlendirilir.

Bu iki form backend ile veri alışverişi yaparak kullanıcı bilgilerini işleme alır.

### 2.1. Kullanıcı Kayıt Formu (`register.html`)
Formda yer alan alanlar:
- **Email**: Kullanıcının e-posta adresi
- **AccountId**: Kullanıcının Zoom Account ID'si
- **ClientId**: Kullanıcının Zoom Client ID'si
- **ClientSecret**: Kullanıcının Zoom Client Secret'ı

### 2.2. Kullanıcı Giriş Formu (`login.html`)
Formda yer alan alanlar:
- **Email**: Kullanıcının giriş yapacağı e-posta adresi

---

## 3. Toplantı Oluşturma ve Zoom API Entegrasyonu
Toplantı oluşturma işlemi, kullanıcı giriş yaptıktan sonra gerçekleşir. Kullanıcı, toplantı oluşturma formunu doldurur ve bu veriler Zoom API'ye iletilir.

### 3.1. Toplantı Oluşturma Formu (`meeting.html`)
Formda yer alan alanlar:
- **Email**: Token almak için kullanıcının e-posta adresi
- **Agenda**: Toplantının gündemi
- **Duration**: Toplantının süresi (dakika olarak)
- **Password**: Toplantı şifresi
- **Pre-schedule**: Toplantının önceden planlanıp planlanmayacağı
- **Schedule For**: Toplantının kimin adına planlanacağı

Ek olarak toplantı ayarları:
- **Allow Multiple Devices**: Aynı anda birden fazla cihazın katılabilmesi
- **Alternative Hosts**: Alternatif ev sahipleri (virgülle ayrılmış e-posta listesi)
- **Authentication Domains**: Otomatik katılım sağlayacak domainler
- **Auto Recording**: Toplantı kaydının otomatik yapılması

---

## 4. UML Diyagramı

![alt text](image-5.png)

> UML diyagramı, sınıfların birbirleriyle olan ilişkilerini ve genel işleyişi açıklamaktadır. `ZoomUser`, `ZoomUserRegisterRequest`, `ZoomUserLoginRequest` sınıfları, kullanıcı kayıt ve giriş işlemlerini yönetir. `ZoomUserGenerateMeetingService` sınıfı ise Zoom API ile etkileşime geçerek toplantı oluşturma işlemini gerçekleştirir.

---

### 4.1. Genel Akış
1. **Kullanıcı Kaydı**: Kullanıcı bilgileri alınır ve veritabanına kaydedilir.
2. **Kullanıcı Girişi**: Kullanıcı e-posta ile giriş yapar.
3. **Toplantı Oluşturma**: Kullanıcı gerekli bilgileri girer ve bu veriler Zoom API'sine iletilir.

---

## Kullanılan Teknolojiler
- **Frontend**: HTML, JavaScript (form gönderimi ve kullanıcı etkileşimi)
- **Backend**: Java (Spring Boot) kullanılarak istekler işlenir ve Zoom API'ye bağlanılır
- **Zoom API**: Toplantı oluşturma ve kullanıcı doğrulama işlemleri için kullanılır
- **Lombok**: Getter, Setter, Constructor gibi kodları sadeleştirmek için kullanılır
- **ObjectMapper (Jackson)**: JSON yanıtlarını ve isteklerini işlemek için kullanılır

---

## 5. Ekstra Özellikler
- **Kullanıcı Kimlik Doğrulaması**: Kullanıcılar kaydolmadan önce kayıt olmalıdır ve kimlik doğrulaması yapılır.
- **Zoom Kimlik Doğrulaması**: Zoom OAuth kullanılarak toplantı token'ı oluşturulur ve toplantı başlatılır.

---

