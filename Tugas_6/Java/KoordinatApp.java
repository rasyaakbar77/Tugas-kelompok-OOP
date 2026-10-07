/*
Nama Program : KoordinatApp.java
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 29-09-2026
Deskripsi Program : Sebuah program untuk melakukan operasi perhitungan koordinat kartesius berupa 
                    1. mencari titik tengah
                    2. mencari jarak antara 2 titik
                    3. hasil pencerminan terhadap sumbu x atau sumbu y
                    dengan input dalam, input luar, output dalam dan output luar berbasis OOP.
*/

import java.util.Locale;
import java.util.Scanner;

class Koordinat {
    private double absis;
    private double ordinat;

    // Default Constructor
    public Koordinat() {
        absis = 0;
        ordinat = 0;
    }

    // Constructor Parameter
    public Koordinat(double absis, double ordinat) {
        this.absis = absis;
        this.ordinat = ordinat;
    }

    // Setter Absis
    public void setAbsis(double absis) {
        this.absis = absis;
    }

    // Setter Ordinat
    public void setOrdinat(double ordinat) {
        this.ordinat = ordinat;
    }
          
    // Getter Absis
    public double getAbsis() {
        return absis;
    }

    // Getter Ordinat
    public double getOrdinat() {
        return ordinat;
    }

    // Input Dalam Class
    public void inputDalam() {
        System.out.print("Masukkan Nilai Absis (input dalam): ");
        absis = KoordinatApp.input.nextDouble();
        System.out.print("Masukkan Nilai Ordinat (input dalam): ");
        ordinat = KoordinatApp.input.nextDouble();
    }

    // Method Mencari titik Tengah (void) 
    public void titikTengahVoid(Koordinat a, Koordinat b) {
        this.absis = (a.absis + b.absis) / 2;
        this.ordinat = (a.ordinat + b.ordinat) / 2;
    }

    // Method Mencari titik Tengah (Fungsi)
    public Koordinat titikTengahFungsi(Koordinat p) {
        Koordinat tengah = new Koordinat();
        tengah.absis = (p.absis + this.absis) / 2;
        tengah.ordinat = (p.ordinat + this.ordinat) / 2;
        return tengah;
    }

    // Method Pencerminan terhadap sumbu X (void)
    public void pencerminanXVoid(Koordinat pHasil) {
        pHasil.absis = this.absis;
        pHasil.ordinat = -1 * this.ordinat;
    }

    // Method Pencerminan terhadap sumbu x (fungsi)
    public Koordinat pencerminanXFungsi() {
        Koordinat pCermin = new Koordinat();
        pCermin.absis = this.absis;
        pCermin.ordinat = -1 * this.ordinat;
        return pCermin;
    }

    // Method Pencerminan terhadap sumbu y (void)
    public void pencerminanYVoid(Koordinat pHasil) {
        pHasil.absis = -1 * this.absis;
        pHasil.ordinat = this.ordinat;
    }

    // Method Pencerminan terhadap sumbu y (Fungsi)
    public Koordinat pencerminanYFungsi() {
        Koordinat pCermin = new Koordinat();
        pCermin.absis = -1 * this.absis;
        pCermin.ordinat = this.ordinat;
        return pCermin;
    }

    // Method Mencari Jarak antar 2 titik (void)
    public void jarakDuaTitikVoid(Koordinat b, double[] jarak) {
        jarak[0] = Math.sqrt(Math.pow(b.absis - this.absis, 2) + Math.pow(b.ordinat - this.ordinat, 2));
    }

    // Method Mencari Jarak antar 2 titik (Fungsi)
    public double jarakDuaTitikFungsi(Koordinat p) {
        double jarak = Math.sqrt(Math.pow(p.absis - this.absis, 2) + Math.pow(p.ordinat - this.ordinat, 2));
        return jarak;
    }

    // Output Dalam
    public void outputDalam(Koordinat k2, Koordinat k3, Koordinat k4) {
        System.out.println("\n=========================================");
        System.out.println("          OUTPUT DALAM (SEMUA OBJEK)     ");
        System.out.println("=========================================");
        System.out.println("Objek Koordinat ke-1 : (" + KoordinatApp.format(this.absis) + ", " + KoordinatApp.format(this.ordinat) + ")");
        System.out.println("Objek Koordinat ke-2 : (" + KoordinatApp.format(k2.getAbsis()) + ", " + KoordinatApp.format(k2.getOrdinat()) + ")");
        System.out.println("Objek Koordinat ke-3 : (" + KoordinatApp.format(k3.getAbsis()) + ", " + KoordinatApp.format(k3.getOrdinat()) + ")");
        System.out.println("Objek Koordinat ke-4 : (" + KoordinatApp.format(k4.getAbsis()) + ", " + KoordinatApp.format(k4.getOrdinat()) + ")");
        System.out.println("=========================================");
    }
}

class Menu {
    private int menuUtama;

    public void tampilMenu() {
        Scanner input = KoordinatApp.input;

        Koordinat koor1 = new Koordinat(); // Input Melalui Setter
        koor1.setAbsis(3);
        koor1.setOrdinat(4);
        
        Koordinat koor2 = new Koordinat(5, 6); // Input melalui Constructor Parameter
        Koordinat koor3 = new Koordinat(); // Input Melalui method inputDalam
        Koordinat koor4 = new Koordinat(); // Input Melalui method inputLuar

        do {
            System.out.println("\n=========================================");
            System.out.println("     MENU APLIKASI KOORDINAT KARTESIUS     ");
            System.out.println("=========================================");
            System.out.println("1. Input Koordinat 3 dan 4");
            System.out.println("2. Pencerminan");
            System.out.println("3. Titik Tengah");
            System.out.println("4. Jarak 2 Titik");
            System.out.println("5. Tampilkan Seluruh Objek");
            System.out.println("0. Keluar");
            System.out.println("=========================================");
            System.out.print(">> Masukkan Pilihan Menu : ");
            menuUtama = input.nextInt();

            switch (menuUtama) {
                case 1: {
                    int pilihObjek;
                    System.out.println("=========================================");
                    System.out.println("PILIH OBJEK YANG MAU DI-INPUT");
                    System.out.println("1. Koordinat 3 (via Input Dalam)");
                    System.out.println("2. Koordinat 4 (via Input Luar)");
                    System.out.println("=========================================");
                    System.out.print(">> Pilih Objek (1-2): ");
                    pilihObjek = input.nextInt();

                    if (pilihObjek == 1) {
                        System.out.println("Input untuk Koordinat 3:");
                        koor3.inputDalam();
                    } else if (pilihObjek == 2) {
                        System.out.println("Input untuk Koordinat 4:");
                        KoordinatApp.inputLuar(koor4);
                    } 
                    break;
                }
                case 2: {
                    int sumbu, pilihObjek;
                    System.out.println("=========================================");
                    System.out.println("PENCERMINAN");
                    System.out.println("1. Terhadap Sumbu X");
                    System.out.println("2. Terhadap Sumbu Y");
                    System.out.println("=========================================");

                    sumbu = KoordinatApp.bacaPilihan(">> Pilih Sumbu (1/2): ", 1, 2);
                    pilihObjek = KoordinatApp.bacaPilihan(">> Pilih Objek yang dicerminkan (1-4): ", 1, 4);

                    Koordinat[] arr = {koor1, koor2, koor3, koor4};
                    Koordinat target = arr[pilihObjek - 1];

                    if (sumbu == 1) {
                        Koordinat hasilFungsi = target.pencerminanXFungsi();
                        Koordinat hasilVoid = new Koordinat();
                        target.pencerminanXVoid(hasilVoid);
                        
                        System.out.println("Hasil Pencerminan terhadap Sumbu X:");
                        System.out.println("   > Versi Fungsi : (" + KoordinatApp.format(hasilFungsi.getAbsis()) + ", " + KoordinatApp.format(hasilFungsi.getOrdinat()) + ")");
                        System.out.println("   > Versi Void   : (" + KoordinatApp.format(hasilVoid.getAbsis()) + ", " + KoordinatApp.format(hasilVoid.getOrdinat()) + ")");
                    } else {
                        Koordinat hasilFungsi = target.pencerminanYFungsi();
                        Koordinat hasilVoid = new Koordinat();
                        target.pencerminanYVoid(hasilVoid);

                        System.out.println("Hasil Pencerminan terhadap Sumbu Y:");
                        System.out.println("   > Versi Fungsi : (" + KoordinatApp.format(hasilFungsi.getAbsis()) + ", " + KoordinatApp.format(hasilFungsi.getOrdinat()) + ")");
                        System.out.println("   > Versi Void   : (" + KoordinatApp.format(hasilVoid.getAbsis()) + ", " + KoordinatApp.format(hasilVoid.getOrdinat()) + ")");
                    }
                    break;
                }
                case 3: {
                    int o1, o2;
                    System.out.println("=========================================");
                    System.out.println("       TITIK TENGAH ANTARA 2 OBJEK       ");
                    System.out.println("=========================================");

                    o1 = KoordinatApp.bacaPilihan("Pilih Objek Pertama (1-4): ", 1, 4);
                    o2 = KoordinatApp.bacaPilihan("Pilih Objek Kedua (1-4): ", 1, 4);

                    Koordinat[] arr = {koor1, koor2, koor3, koor4};
                    
                    Koordinat hasilFungsi = arr[o1 - 1].titikTengahFungsi(arr[o2 - 1]);
                    Koordinat hasilVoid = new Koordinat();
                    hasilVoid.titikTengahVoid(arr[o1 - 1], arr[o2 - 1]);

                    System.out.println("Titik Tengah antara Objek " + o1 + " dan " + o2 + " :");
                    System.out.println("   > Versi Fungsi : (" + KoordinatApp.format(hasilFungsi.getAbsis()) + ", " + KoordinatApp.format(hasilFungsi.getOrdinat()) + ")");
                    System.out.println("   > Versi Void   : (" + KoordinatApp.format(hasilVoid.getAbsis()) + ", " + KoordinatApp.format(hasilVoid.getOrdinat()) + ")");
                    break;
                }
                case 4: {
                    int o1, o2;
                    System.out.println("=========================================");
                    System.out.println("          JARAK ANTARA 2 TITIK           ");
                    System.out.println("=========================================");

                    o1 = KoordinatApp.bacaPilihan("Pilih Objek Pertama (1-4): ", 1, 4);
                    o2 = KoordinatApp.bacaPilihan("Pilih Objek Kedua (1-4): ", 1, 4);

                    Koordinat[] arr = {koor1, koor2, koor3, koor4};

                    double jarakFungsi = arr[o1 - 1].jarakDuaTitikFungsi(arr[o2 - 1]);
                    double[] jarakVoid = new double[1];
                    arr[o1 - 1].jarakDuaTitikVoid(arr[o2 - 1], jarakVoid);

                    System.out.println("Jarak antara Objek " + o1 + " dan " + o2 + " :");
                    System.out.println("   - Versi Fungsi : " + KoordinatApp.format(jarakFungsi));
                    System.out.println("   - Versi Void   : " + KoordinatApp.format(jarakVoid[0]));
                    break;
                }
                case 5: {
                    int pilihOut;
                    System.out.println("=========================================");
                    System.out.println("Pilih Menu Output");
                    System.out.println("1. Output Dalam (Menampilkan semua objek via Method Class)");
                    System.out.println("2. Output Luar (Menampilkan semua objek via Fungsi Luar)");
                    System.out.println("=========================================");
                    System.out.print(">> Pilih Menu Output : "); pilihOut = input.nextInt();
                    
                    if (pilihOut == 1) {
                        koor1.outputDalam(koor2, koor3, koor4);
                    }
                    else if (pilihOut == 2) {
                        KoordinatApp.outputLuar(koor1, koor2, koor3, koor4);
                    }
                    break;
                }
                case 0:
                    System.out.println("\nBye-bye!.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid! Silakan coba lagi.");
            }
        } while (menuUtama != 0);
    }
}

public class KoordinatApp {
    static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    // format angka seperti cout di C++ (maks 6 angka signifikan, nol di belakang dibuang)
    public static String format(double nilai) {
        String s = String.format(Locale.US, "%.6g", nilai);
        String eksponen = "";
        int e = s.indexOf('e');
        if (e >= 0) {
            eksponen = s.substring(e);
            s = s.substring(0, e);
        }
        if (s.contains(".")) {
            s = s.replaceAll("0+$", "").replaceAll("\\.$", "");
        }
        return s + eksponen;
    }

    // Input Luar Class 
    public static void inputLuar(Koordinat k) {
        double a, o;
        System.out.print("Masukkan Nilai Absis (Input Luar): ");
        a = input.nextDouble();
        System.out.print("Masukkan Nilai Ordinat (Input Luar): ");
        o = input.nextDouble();
        k.setAbsis(a);
        k.setOrdinat(o);
    }

    // Output Luar 
    public static void outputLuar(Koordinat k1, Koordinat k2, Koordinat k3, Koordinat k4) {
        System.out.println("\n=========================================");
        System.out.println("               OUTPUT LUAR                ");
        System.out.println("=========================================");
        
        Koordinat[] daftarObjek = {k1, k2, k3, k4};
        
        for (int i = 0; i < 4; i++) {
            System.out.println("Objek Koordinat ke-" + (i + 1) + " : (" 
                    + format(daftarObjek[i].getAbsis()) + ", " + format(daftarObjek[i].getOrdinat()) + ")");
        }
    }

    // validasi pilihan (angka harus di antara min dan max)
    public static int bacaPilihan(String pesan, int min, int max) {
        int nilai;
        while (true) {
            System.out.print(pesan);
            if (!input.hasNextInt()) {
                input.next();                    // buang input invalid
                input.nextLine();
                System.out.println("Masukkan angka yang valid!");
            } else {
                nilai = input.nextInt();
                input.nextLine();                // bersihkan sisa newline
                if (nilai >= min && nilai <= max) return nilai;
                System.out.println("Pilihan harus antara " + min + " sampai " + max + "!");
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.tampilMenu();
    }
}