import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

// Temel Sınıf (BaseEntity)
class BaseEntity {
    private int id;
    private String name;

    public BaseEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Bilgi gösterme metodu (Polymorphism için kullanılacak)
    public void BilgiGoster() {
        System.out.println("ID: " + id + ", Name: " + name);
    }
}

// Film Sınıfı
class Film {
    private String ad;
    private int sure;  // süre dakika cinsinden
    private String tur;
    private Salon salon; // Film hangi salonda gösteriliyorsa o salon ile ilişkilendirilecek
    private Map<String, Integer> seanslar; // Seans saatleri

    public Film(String ad, int sure, String tur, Salon salon) {
        this.ad = ad;
        this.sure = sure;
        this.tur = tur;
        this.salon = salon;
        this.seanslar = new HashMap<>();
    }

    public String getAd() {
        return ad;
    }

    public int getSure() {
        return sure;
    }

    public String getTur() {
        return tur;
    }

    public Salon getSalon() {
        return salon;
    }

    public Map<String, Integer> getSeanslar() {
        return seanslar;
    }

    public void seansEkle(String saat, int fiyat) {
        seanslar.put(saat, fiyat);
    }

    public void bilgiGoster() {
        System.out.println("Film Adı: " + ad + ", Süre: " + sure + " dk, Tür: " + tur);
        System.out.println("Seans Saatleri:");
        for (String saat : seanslar.keySet()) {
            System.out.println(saat + " - Fiyat: " + seanslar.get(saat) + " TL");
        }
    }
}

// Müşteri Sınıfı (BaseEntity'den türemekte)
class Musteri extends BaseEntity {
    private String soyad;
    private String telefonNumarasi;
    private String yiyecek;
    private String icecek;
    int koltukNumarasi;  // Koltuk numarası
    private Film izlenenFilm;  // Hangi film izlendiği
    private String seansSaat;  // Seans saati
    private boolean odenmisMi;  // Ödeme durumu

    public Musteri(int id, String name, String soyad, String telefonNumarasi, String yiyecek, String icecek, int koltukNumarasi, Film izlenenFilm, String seansSaat) {
        super(id, name);
        this.soyad = soyad;
        this.telefonNumarasi = telefonNumarasi;
        this.yiyecek = yiyecek;
        this.icecek = icecek;
        this.koltukNumarasi = koltukNumarasi;
        this.izlenenFilm = izlenenFilm;
        this.seansSaat = seansSaat;
        this.odenmisMi = false;  // Başlangıçta ödeme yapılmadı
    }

    public void BilgiGoster() {
        super.BilgiGoster();
        System.out.println("Soyad: " + soyad);
        System.out.println("Telefon Numarası: " + telefonNumarasi);
        System.out.println("Yediği Yiyecek: " + yiyecek);
        System.out.println("İçtiği İçecek: " + icecek);
        System.out.println("Koltuk Numarası: " + koltukNumarasi);
        System.out.println("İzlenen Film: " + izlenenFilm.getAd());
        System.out.println("Seans Saati: " + seansSaat);
        System.out.println("Salonda İzlenen Film: " + izlenenFilm.getSalon().getName());
        System.out.println("Ödeme Durumu: " + (odenmisMi ? "Ödendi" : "Ödenmedi"));
    }

    public void filmIzle() {
        System.out.println(getName() + " " + izlenenFilm.getAd() + " filmini izledi.");
    }

    public void odemeYap() {
        int fiyat = izlenenFilm.getSeanslar().get(seansSaat);
        odenmisMi = true;
        System.out.println(getName() + " " + izlenenFilm.getAd() + " filmi için " + fiyat + " TL ödendi.");
    }

    public void odemeyiKontrolEt() {
        if (odenmisMi) {
            System.out.println("Ödeme başarılı.");
        } else {
            System.out.println("Ödeme yapılmamış.");
        }
    }

    public void koltukGuncelle(int yeniKoltukNumarasi) {
        this.koltukNumarasi = yeniKoltukNumarasi;
    }

    public void soyadGuncelle(String yeniSoyad) {
        this.soyad = yeniSoyad;
    }

    public void telefonGuncelle(String yeniTelefon) {
        this.telefonNumarasi = yeniTelefon;
    }

    public boolean isOdenmisMi() {
        return odenmisMi;
    }
}

// Salon Sınıfı (BaseEntity'den türemekte)
class Salon extends BaseEntity {
    private List<Film> filmler;
    List<Musteri> musteriler;
    private int koltukSayisi;  // Salonun koltuk sayısı

    public Salon(int id, String name, int koltukSayisi) {
        super(id, name);
        this.filmler = new ArrayList<>();
        this.musteriler = new ArrayList<>();
        this.koltukSayisi = koltukSayisi;
    }

    public void filmEkle(Film film) {
        filmler.add(film);
    }

    public boolean koltukDoluMu(int koltukNumarasi) {
        for (Musteri musteri : musteriler) {
            if (musteri.koltukNumarasi == koltukNumarasi) {
                return true;  // Koltuk dolu
            }
        }
        return false;  // Koltuk boş
    }

    public void musteriKaydet(Musteri musteri) {
        if (koltukDoluMu(musteri.koltukNumarasi)) {
            System.out.println("Bu koltuk dolu. Lütfen başka bir koltuk numarası girin.");
            return;
        }

        if (musteriler.size() < koltukSayisi) {
            musteriler.add(musteri);
            System.out.println("Müşteri başarıyla kaydedildi.");
        } else {
            System.out.println("Bu salonda boş koltuk yok.");
        }
    }

    public void musteriSil(Musteri musteri) {
        if (musteriler.remove(musteri)) {
            System.out.println("Müşteri silindi.");
        } else {
            System.out.println("Müşteri bulunamadı.");
        }
    }

    public void filmListele() {
        System.out.println(getName() + " salonunda gösterilen filmler:");
        for (Film film : filmler) {
            film.bilgiGoster();
        }
    }

    public void musteriListele() {
        System.out.println(getName() + " salonunda kayıtlı müşteriler:");
        for (Musteri musteri : musteriler) {
            musteri.BilgiGoster();
        }
    }
}

// Ana Program (Main)
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Salonlar
        Salon salon1 = new Salon(1, "Salon A", 10); // Salon A 10 koltuk
        Salon salon2 = new Salon(2, "Salon B", 10); // Salon B 10 koltuk
        Salon salon3 = new Salon(3, "Salon C", 10); // Salon C 10 koltuk

        // Filmler
        Film film1 = new Film("Yıldız Savaşları", 120, "Bilim Kurgu", salon1);
        Film film2 = new Film("Titanik", 180, "Romantik", salon1);
        Film film3 = new Film("Deadpool2", 120, "Aksiyon, Macera", salon2);
        Film film4 = new Film("Venom: Son Dans", 60, "Aksiyon, Macera", salon2);
        Film film5 = new Film("Avengers: Endgame", 150, "Aksiyon, Bilim Kurgu", salon3);

        // Seanslar
        film1.seansEkle("10:00", 50);
        film1.seansEkle("13:00", 50);
        film2.seansEkle("14:00", 40);
        film2.seansEkle("17:00", 40);
        film3.seansEkle("11:00", 60);
        film3.seansEkle("16:00", 60);
        film4.seansEkle("12:00", 55);
        film4.seansEkle("19:00", 55);
        film5.seansEkle("15:00", 70);

        // Film ekleme
        salon1.filmEkle(film1);
        salon1.filmEkle(film2);
        salon2.filmEkle(film3);
        salon2.filmEkle(film4);
        salon3.filmEkle(film5);

        // Salonları listele
        List<Salon> salonlar = new ArrayList<>();
        salonlar.add(salon1);
        salonlar.add(salon2);
        salonlar.add(salon3);

        boolean devamEt = true;
        while (devamEt) {
            System.out.println("Seçim yapın:");
            System.out.println("1. Film ve Salon Seçme Müşteri Ekleme");
            System.out.println("2. Müşteri Silme");
            System.out.println("3. Çıkış");
            int secim = scanner.nextInt();
            scanner.nextLine(); // Dummy read to consume newline after nextInt()

            if (secim == 1) {
                // Film ve salon seçimi
                System.out.println("Film ve salon seçin (1: Yıldız Savaşları, 2: Titanik, 3: Deadpool2, 4: Venom: Son Dans, 5: Avengers: Endgame)");
                int filmSecim = scanner.nextInt();
                scanner.nextLine();  // Dummy read to consume newline after nextInt()

                Film selectedFilm = null;
                if (filmSecim == 1) selectedFilm = film1;
                else if (filmSecim == 2) selectedFilm = film2;
                else if (filmSecim == 3) selectedFilm = film3;
                else if (filmSecim == 4) selectedFilm = film4;
                else if (filmSecim == 5) selectedFilm = film5;

                // Seans saati seçimi
                System.out.println("Seans saatlerini seçin:");
                for (String seans : selectedFilm.getSeanslar().keySet()) {
                    System.out.println(seans);
                }
                String seansSaat = scanner.nextLine();

                // Müşteri bilgileri al
                System.out.println("Müşteri Adı girin:");
                String musteriAd = scanner.nextLine();
                System.out.println("Müşteri Soyadını girin:");
                String musteriSoyad = scanner.nextLine();
                System.out.println("Telefon numarasını girin:");
                String telefon = scanner.nextLine();

                // Yiyecek tercihi
                String yiyecek = null;
                System.out.println("Yiyecek ister misiniz? (evet/hayır)");
                String yiyecekCevap = scanner.nextLine();
                if (yiyecekCevap.equalsIgnoreCase("evet")) {
                    System.out.println("Yiyecek seçenekleri:");
                    String[] yiyecekler = {"Popcorn", "Cips", "Patates"};
                    for (int i = 0; i < yiyecekler.length; i++) {
                        System.out.println((i + 1) + ". " + yiyecekler[i]);
                    }
                    System.out.println("Yiyecek numarasını girin:");
                    int yiyecekNumarasi = scanner.nextInt();
                    yiyecek = yiyecekler[yiyecekNumarasi - 1];
                    scanner.nextLine();  // Dummy read to consume newline after nextInt()
                }

                // İçecek tercihi
                String icecek = null;
                System.out.println("İçecek ister misiniz? (evet/hayır)");
                String icecekCevap = scanner.nextLine();
                if (icecekCevap.equalsIgnoreCase("evet")) {
                    String[] icecekler = {"Su", "Kola", "Çay"};
                    System.out.println("İçecek seçenekleri:");
                    for (int i = 0; i < icecekler.length; i++) {
                        System.out.println((i + 1) + ". " + icecekler[i]);
                    }
                    System.out.println("İçecek numarasını girin:");
                    int icecekNumarasi = scanner.nextInt();
                    icecek = icecekler[icecekNumarasi - 1];
                    scanner.nextLine();  // Dummy read to consume newline after nextInt()
                }

                // Koltuk numarasını al
                int koltukNumarasi;
                while (true) {
                    System.out.println("Koltuk numarasını girin:");
                    koltukNumarasi = scanner.nextInt();
                    scanner.nextLine();  // Dummy read to consume newline after nextInt()

                    // Koltuk dolu mu kontrol et
                    if (selectedFilm.getSalon().koltukDoluMu(koltukNumarasi)) {
                        System.out.println("Bu koltuk dolu. Lütfen başka bir koltuk numarası girin.");
                    } else {
                        break; // Koltuk boş, çık
                    }
                }

                // Müşteri oluştur
                Musteri musteri = new Musteri(1, musteriAd, musteriSoyad, telefon, yiyecek, icecek, koltukNumarasi, selectedFilm, seansSaat);
                
                // Müşteri kaydet
                selectedFilm.getSalon().musteriKaydet(musteri);

                // Ödeme işlemi
                musteri.odemeYap();

                // Film izle
                musteri.filmIzle();

                // Müşteri bilgilerini göster
                musteri.BilgiGoster();

            } else if (secim == 2) {
                // Müşteri silme
                System.out.println("Hangi salonun müşterisini silmek istersiniz?");
                System.out.println("1. Salon A");
                System.out.println("2. Salon B");
                System.out.println("3. Salon C");
                int salonSecim = scanner.nextInt();
                scanner.nextLine();  // Dummy read to consume newline after nextInt()

                Salon selectedSalon = null;
                if (salonSecim == 1) selectedSalon = salon1;
                else if (salonSecim == 2) selectedSalon = salon2;
                else if (salonSecim == 3) selectedSalon = salon3;

                selectedSalon.musteriListele();
                System.out.println("Silmek istediğiniz müşteri ID'sini girin:");
                int musteriId = scanner.nextInt();
                scanner.nextLine();  // Dummy read to consume newline after nextInt()

                Musteri musteriSil = null;
                for (Musteri musteri : selectedSalon.musteriler) {
                    if (musteri.getId() == musteriId) {
                        musteriSil = musteri;
                        break;
                    }
                }

                if (musteriSil != null) {
                    selectedSalon.musteriSil(musteriSil);
                } else {
                    System.out.println("Müşteri bulunamadı.");
                }

            } else if (secim == 3) {
                devamEt = false;
            }
        }

        scanner.close();
    }
}