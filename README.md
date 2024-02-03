# Turizm Acentesi Projesi

Bu proje, Patika Turizm acentesinin dijital ortamda işlerini yürütebilmesini sağlamak için geliştirilmiş bir Java uygulamasıdır.

## Kullanılan Teknolojiler

- Java
- Swing (GUI)
- PostgreSQL (veritabanı)
- JDBC (Java Database Connectivity)

## Proje Yapısı

- **Business (İş Mantığı)**: Bu paket, uygulamanızın iş mantığıyla ilgili olan iş sınıflarını içerir. Genellikle iş süreçleri, yöneticiler ve servis sınıflarını içerir. Projede uygulamanın temel işlevselliğini yöneten sınıfları barındırır.
- **DAO (Data Access Object)**: Veri erişim işlemleriyle ilgili sınıfları içeren pakettir. Veritabanı ile etkileşim, sorgular ve veri kayıtlarının işlenmesi gibi görevleri gerçekleştiren veri erişim katmanını içerir. Bu sınıflar genellikle veritabanı işlemlerini soyutlar ve uygulama katmanının veri tabanına erişimini yönetir.
- **Core (Çekirdek)** :Bu paket, uygulamanızın temel yapı taşlarını içerir. Genellikle genel işlevselliği, yardımcı sınıfları ve çok sık kullanılan temel öğeleri içerir. Bu sınıflar, uygulamanızın farklı bölümlerinde kullanılan temel fonksiyonları sağlar.
- **Entity (Varlık)**: Bu paket, uygulamanızın varlık sınıflarını içerir. Genellikle veritabanı tablolarını temsil eden sınıfları içerir. Bu sınıflar genellikle nesne ilişkisel haritalama (ORM) teknikleri ile ilişkilendirilmiştir ve veritabanı tablolarındaki verileri temsil eder.
- **Enums (Sabitler)**: Bu paket, uygulamanızın farklı öğelerini temsil etmek için kullanılan sabitlerin ve enum sınıflarının bulunduğu alandır. Bu sınıflar genellikle belirli bir öğenin farklı durumlarını temsil eder.
- **View (Görünüm):** Bu paket, kullanıcı arayüzü (UI) bileşenlerini ve sınıflarını içerir. Genellikle Swing, JavaFX veya başka bir UI framework'ü kullanılarak oluşturulan pencere, form ve diğer arayüz öğelerini içerir. Kullanıcı ile etkileşimi yönetir ve UI'nın işlevselliğini içerir.

## Nasıl Çalıştırılır

1. Projeyi bilgisayarınıza klonlayın.
2. PostgreSQL'de tourism_agency.sql uzantılı dosyayı çalıştırın .
3. Proje dosyalarını bir Java IDE'sinde açın.
4. `App` sınıfını çalıştırarak uygulamayı başlatın.

## Notlar

- Kullanılan metodlar benzer olduğu için hepsinde aynı şekilde detaylı yorum satırları eklenmedi.
- Örneğin, bir çok özellik `User` class'ında detaylı açıklandı. Bu sebeple diğer class'larda daha az açıklamaya yer verildi.
- GUI form boyutları laptop boyutlarında görünmeyebilir. Büyük ekranlarda kontrol ediniz.
- Proje değerlendirme sonrası düzenlemeler yapılacak.
- Proje değerlendirme kriterleri karşılanıyor; proje çalışıyor fakat eklemek istediğim özellikler için daha sonra proje üzerinde çalışmaya devam edeceğim.
