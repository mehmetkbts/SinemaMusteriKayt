## Sinema Yönetim Sistemi
Bu, bir sinema salonu yönetimini simüle eden basit bir Java programıdır. Film, müşteri ve salon (salon) oluşturma işlemlerini içerir. Sistem, bilet rezervasyonu yapma, film seçme, ödeme işlemleri ve farklı sinema salonlarındaki müşterilerin yönetimi gibi işlemleri gerçekleştirir.

Özellikler
Film Yönetimi:

Film adı, türü, süresi ve mevcut seans saatleri gibi bilgilerle film eklenebilir.
Her film, belirli bir sinema salonu (Salon) ile ilişkilidir.
Müşteri Yönetimi:

Müşteriler, adları, soyadları, telefon numaraları, tercih ettikleri yiyecek ve içecekler, koltuk numaraları gibi bilgilerle kaydedilebilir.
Müşteriler, bir film ve belirli bir seans saati seçebilir.
Sistem, koltukların doluluğunu kontrol ederek çifte rezervasyonu engeller.
Koltuk Rezervasyonu:

Sistem, talep edilen koltuğun mevcut olup olmadığını kontrol eder. Eğer koltuk doluysa, müşteri başka bir koltuk seçmeye yönlendirilir.
Ödeme Sistemi:

Film ve koltuk seçildikten sonra, müşteri bilet ücreti için ödeme yapar.
Sistem, ödemenin başarılı olup olmadığını yönetir.
Bilgi Gösterme:

Filmler, salonlar ve müşteriler hakkında ayrıntılı bilgi kullanıcıya gösterilir.
Sınıf Açıklamaları
1. BaseEntity
Bu, Musteri (Müşteri) ve Salon (Sinema Salonu) gibi varlıkların ortak özelliklerini taşıyan temel bir sınıftır.
Özellikler: id, name
Yöntem: BilgiGoster() - Temel bilgileri gösterir.
2. Film
Sistemdeki bir filmi temsil eder.
Özellikler:
ad (Film adı)
sure (Filmin süresi, dakika cinsinden)
tur (Filmin türü)
seanslar (Mevcut seans saatleri ve fiyatlar)
Yöntemler:
seansEkle() - Yeni seans ekler.
bilgiGoster() - Film hakkında bilgi gösterir.
3. Musteri (Müşteri)
Bilet rezervasyonu yapan müşteriyi temsil eder.
BaseEntity sınıfından türetilmiştir.
Özellikler:
soyad (Soyadı)
telefonNumarasi (Telefon numarası)
yiyecek (Yiyecek tercihi)
icecek (İçecek tercihi)
koltukNumarasi (Koltuk numarası)
izlenenFilm (İzlenen film)
seansSaat (Seans saati)
odenmisMi (Ödeme durumu)
Yöntemler:
filmIzle() - Filmi izleme işlemi.
odemeYap() - Ödeme işlemini gerçekleştirir.
odemeKontrolEt() - Ödemenin yapılıp yapılmadığını kontrol eder.
koltukGuncelle() - Koltuk numarasını günceller.
soyadGuncelle() ve telefonGuncelle() - Müşteri bilgilerini günceller.
4. Salon (Sinema Salonu)
Bir sinema salonunu temsil eder.
BaseEntity sınıfından türetilmiştir.
Özellikler:
filmler (Salonda gösterilen filmlerin listesi)
musteriler (Salonda kayıtlı müşteriler)
koltukSayisi (Salondaki toplam koltuk sayısı)
Yöntemler:
filmEkle() - Bir filmi salona ekler.
koltukDoluMu() - Bir koltuğun dolu olup olmadığını kontrol eder.
musteriKaydet() - Müşteri kaydeder.
musteriSil() - Müşteri siler.
filmListele() - Salonda gösterilen filmleri listeler.
musteriListele() - Salondaki müşterileri listeler.
Program Akışı
Başlangıç Ayarları:

Üç sinema salonu (Salon A, Salon B, Salon C) oluşturulur, her biri 10 koltukla.
Filmler oluşturulup, salonlarla ilişkilendirilir. Her film için seanslar ve fiyatlar belirlenir.
Menü Seçenekleri:

Kullanıcılara aşağıdaki seçenekler sunulur:
1. Film ve Salon Seçimi: Kullanıcı bir film, bir salon ve bir seans saati seçebilir. Müşteriler ayrıca yiyecek ve içecek tercihlerini belirler.
2. Müşteri Silme: Bir müşteri kaydını silmek için seçenek.
3. Çıkış: Programdan çıkış yapar.
Film ve Salon Seçimi:

Kullanıcı, bir film seçer ve ardından bir seans saati belirler.
Kullanıcı, kişisel bilgilerini (ad, soyad, telefon numarası) girer.
Sistem, talep edilen koltuğun mevcut olup olmadığını kontrol eder.
Koltuk müsaitse, kullanıcı ödeme yapar.
Müşteri bilgileri kaydedilir ve onay mesajı gösterilir.
Müşteri Silme:

Kullanıcı, belirli bir salonun müşterilerini listeler ve müşteri ID'sine göre müşteri siler.
Örnek Kullanım
Film Seçimi:

Kullanıcı "Yıldız Savaşları" gibi bir film seçer ve ardından seans saatini belirler.
Kullanıcı, kişisel bilgilerini ve koltuk numarasını girer.
Koltuk mevcutsa, yiyecek ve içecek tercihleri girilir ve ödeme yapılır.
Müşteri Silme:

Kullanıcı, bir salon seçer ve o salondaki müşterileri listeler.
Kullanıcı, bir müşteri ID'si girerek müşteriyi siler

# Lisans
https://github.com/mehmetkbts
