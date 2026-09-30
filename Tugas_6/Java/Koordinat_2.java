/*
Nama Program : Koordinat_2.java
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
    public void inputDalam(Scanner input) {
        System.out.print("Masukkan Nilai Absis (input dalam): ");
        this.absis = input.nextDouble();
        System.out.print("Masukkan Nilai Ordinat (input dalam): ");
        this.ordinat = input.nextDouble();
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
        System.out.println("Objek Koordinat ke-1 : (" + this.absis + ", " + this.ordinat + ")");
        System.out.println("Objek Koordinat ke-2 : (" + k2.getAbsis() + ", " + k2.getOrdinat() + ")");
        System.out.println("Objek Koordinat ke-3 : (" + k3.getAbsis() + ", " + k3.getOrdinat() + ")");
        System.out.println("Objek Koordinat ke-4 : (" + k4.getAbsis() + ", " + k4.getOrdinat() + ")");
        System.out.println("=========================================");
    }
}

public class Koordinat_2 {

    // Input Luar Class 
    public static void inputLuar(Scanner input, Koordinat k) {
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
                      + daftarObjek[i].getAbsis() + ", " + daftarObjek[i].getOrdinat() + ")");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Koordinat koor1 = new Koordinat(); // Input Melalui Setter
        Koordinat koor2 = new Koordinat(0, 0); // Input melalui Constructor Parameter
        Koordinat koor3 = new Koordinat(); // Input Melalui method inputDalam
        Koordinat koor4 = new Koordinat(); // Input Melalui method inputLuar

        int menuUtama;
        do {
            System.out.println("\n=========================================");
            System.out.println("     MENU APLIKASI KOORDINAT KARTESIUS     ");
            System.out.println("=========================================");
            System.out.println("1. Input Koordinat");
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
                    System.out.println("1. Koordinat 1 (via Setter)");
                    System.out.println("2. Koordinat 2 (via Constructor Parameter)");
                    System.out.println("3. Koordinat 3 (via Input Dalam)");
                    System.out.println("4. Koordinat 4 (via Input Luar)");
                    System.out.println("=========================================");
                    System.out.print(">> Pilih Objek (1-4): ");
                    pilihObjek = input.nextInt();

                    if (pilihObjek == 1) {
                        double x, y;
                        System.out.print("Masukkan Absis: "); x = input.nextDouble();
                        System.out.print("Masukkan Ordinat: "); y = input.nextDouble();
                        koor1.setAbsis(x);
                        koor1.setOrdinat(y);
                    } else if (pilihObjek == 2) {
                        double x, y;
                        System.out.print("Masukkan Absis: "); x = input.nextDouble();
                        System.out.print("Masukkan Ordinat: "); y = input.nextDouble();
                        koor2 = new Koordinat(x, y);
                    } else if (pilihObjek == 3) {
                        System.out.println("Input untuk Koordinat 3:");
                        koor3.inputDalam(input);
                    } else if (pilihObjek == 4) {
                        System.out.println("Input untuk Koordinat 4:");
                        inputLuar(input, koor4);
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
                    System.out.print(">> Pilih Sumbu (1/2): ");
                    sumbu = input.nextInt();

                    System.out.print(">> Pilih Objek yang dicerminkan (1-4): ");
                    pilihObjek = input.nextInt();

                    Koordinat[] arr = {koor1, koor2, koor3, koor4};
                    Koordinat target = arr[pilihObjek - 1];

                    if (sumbu == 1) {
                        Koordinat hasilFungsi = target.pencerminanXFungsi();
                        Koordinat hasilVoid = new Koordinat();
                        target.pencerminanXVoid(hasilVoid);
                        
                        System.out.println("Hasil Pencerminan terhadap Sumbu X:");
                        System.out.println("   > Versi Fungsi : (" + hasilFungsi.getAbsis() + ", " + hasilFungsi.getOrdinat() + ")");
                        System.out.println("   > Versi Void   : (" + hasilVoid.getAbsis() + ", " + hasilVoid.getOrdinat() + ")");
                    } else {
                        Koordinat hasilFungsi = target.pencerminanYFungsi();
                        Koordinat hasilVoid = new Koordinat();
                        target.pencerminanYVoid(hasilVoid);

                        System.out.println("Hasil Pencerminan terhadap Sumbu Y:");
                        System.out.println("   > Versi Fungsi : (" + hasilFungsi.getAbsis() + ", " + hasilFungsi.getOrdinat() + ")");
                        System.out.println("   > Versi Void   : (" + hasilVoid.getAbsis() + ", " + hasilVoid.getOrdinat() + ")");
                    }
                    break;
                }
                case 3: {
                    int o1, o2;
                    System.out.println("=========================================");
                    System.out.println("       TITIK TENGAH ANTARA 2 OBJEK       ");
                    System.out.println("=========================================");
                    System.out.print("Pilih Objek Pertama (1-4): "); o1 = input.nextInt();
                    System.out.print("Pilih Objek Kedua (1-4): "); o2 = input.nextInt();

                    Koordinat[] arr = {koor1, koor2, koor3, koor4};
                    
                    Koordinat hasilFungsi = arr[o1 - 1].titikTengahFungsi(arr[o2 - 1]);
                    Koordinat hasilVoid = new Koordinat();
                    hasilVoid.titikTengahVoid(arr[o1 - 1], arr[o2 - 1]);

                    System.out.println("Titik Tengah antara Objek " + o1 + " dan " + o2 + " :");
                    System.out.println("   > Versi Fungsi : (" + hasilFungsi.getAbsis() + ", " + hasilFungsi.getOrdinat() + ")");
                    System.out.println("   > Versi Void   : (" + hasilVoid.getAbsis() + ", " + hasilVoid.getOrdinat() + ")");
                    break;
                }
                case 4: {
                    int o1, o2;
                    System.out.println("=========================================");
                    System.out.println("          JARAK ANTARA 2 TITIK           ");
                    System.out.println("=========================================");
                    System.out.print("Pilih Objek Pertama (1-4): "); o1 = input.nextInt();
                    System.out.print("Pilih Objek Kedua (1-4): "); o2 = input.nextInt();

                    Koordinat[] arr = {koor1, koor2, koor3, koor4};

                    double jarakFungsi = arr[o1 - 1].jarakDuaTitikFungsi(arr[o2 - 1]);
                    double[] jarakVoid = new double[1];
                    arr[o1 - 1].jarakDuaTitikVoid(arr[o2 - 1], jarakVoid);

                    System.out.println("Jarak antara Objek " + o1 + " dan " + o2 + " :");
                    System.out.println("   - Versi Fungsi : " + jarakFungsi);
                    System.out.println("   - Versi Void   : " + jarakVoid[0]);
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
                        outputLuar(koor1, koor2, koor3, koor4);
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

        input.close();
    }
}