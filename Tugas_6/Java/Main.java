/*
Nama Program : Main.java
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 29-09-2026
Deskripsi Program : Sebuah program untuk mencari selisih antara waktu datang dan waktu keluar dengan metode OOP, 
                    menggunakan class SelisihWaktu dan class Menu di dalam satu file.
*/

import java.util.Scanner;
import java.lang.Math;

class SelisihWaktu {
    private int jam;
    private int menit;
    private int detik;

    // Scanner static 
    private static Scanner scanner = new Scanner(System.in);

    // Constructor Default
    public SelisihWaktu() {
        this.jam = 0;
        this.menit = 0;
        this.detik = 0;
    }

    // Constructor Parameter
    public SelisihWaktu(int jam, int menit, int detik) {
        this.jam = jam;
        this.menit = menit;
        this.detik = detik;
    }

    // Validasi input integer
    public static int bacaInt(String pesan) {
        int nilai;
        while (true) {
            System.out.print(pesan);
            if (scanner.hasNextInt()) {
                nilai = scanner.nextInt();
                scanner.nextLine(); 
                break;
            } else {
                System.out.println("Masukkan angka yang valid!");
                scanner.next(); // membuang input invalid 
            }
        }
        return nilai;
    }

    // Setter Jam
    public void setJam(int jam) {
        this.jam = jam;
    }

    // Setter Menit
    public void setMenit(int menit) {
        this.menit = menit;
    }

    // Setter Detik
    public void setDetik(int detik) {
        this.detik = detik;
    }

    // Getter Jam
    public int getJam() {
        return this.jam;
    }

    // Getter Menit
    public int getMenit() {
        return this.menit;
    }

    // Getter Detik
    public int getDetik() {
        return this.detik;
    }

    // Input Dalam
    public void inputDalam() {
       do {
            jam = bacaInt("   Jam   (0-23) : ");
       } while (jam < 0 || jam > 23);

       do {
            menit = bacaInt("   Menit (0-59) : ");
       } while (menit < 0 || menit > 59);

       do {
            detik = bacaInt("   Detik (0-59) : ");
       } while (detik < 0 || detik > 59); 
    }

    // Output Dalam
    public void outputDalam() {
        System.out.printf("Waktu = %02d:%02d:%02d\n", jam, menit, detik);
    }

    // Method Proses Cara 1: Fungsi Return
    public SelisihWaktu hitungSelisihFungsi(SelisihWaktu w2) {
        int totalDetik1 = (this.jam * 3600) + (this.menit * 60) + this.detik;
        int totalDetik2 = (w2.jam * 3600) + (w2.menit * 60) + w2.detik;
        int selisihTotal = Math.abs(totalDetik1 - totalDetik2);

        SelisihWaktu hasil = new SelisihWaktu();
        hasil.jam = selisihTotal / 3600;
        hasil.menit = (selisihTotal % 3600) / 60;
        hasil.detik = selisihTotal % 60;

        return hasil;
    }

    // Method Proses Cara 2: Void
    public void hitungSelisihVoid(SelisihWaktu w1, SelisihWaktu w2) {
        int totalDetik1 = (w1.jam * 3600) + (w1.menit * 60) + w1.detik;
        int totalDetik2 = (w2.jam * 3600) + (w2.menit * 60) + w2.detik;
        int selisihTotal = Math.abs(totalDetik1 - totalDetik2);

        this.jam = selisihTotal / 3600;
        this.menit = (selisihTotal % 3600) / 60;
        this.detik = selisihTotal % 60;
    }
}

class Menu {
    private int pilihan;

    public void jalankanMenu() {
        // 1. Objek 1: Input Setter 
        SelisihWaktu waktu1 = new SelisihWaktu(); 
        waktu1.setJam(2);
        waktu1.setMenit(3);
        waktu1.setDetik(4);

        // 2. Objek 2: Input Constructor Parameter
        SelisihWaktu waktu2 = new SelisihWaktu(4, 5, 6);

        // 3. Objek 3: Input Fungsi dalam class
        SelisihWaktu waktu3 = new SelisihWaktu();
        System.out.println("Masukkan Waktu 3 (Input Dalam):");
        waktu3.inputDalam();

        do {
            System.out.println("\n===============================================================");
            System.out.println("                    MENU UTAMA SELISIH WAKTU                   ");
            System.out.println("===============================================================");
            System.out.println("1. Tampilkan Semua Data Waktu");
            System.out.println("2. Ubah Data Waktu");
            System.out.println("3. Hitung Selisih Waktu (Fungsi Return)");
            System.out.println("4. Hitung Selisih Waktu (Void)");
            System.out.println("5. Keluar Program");
            System.out.println("===============================================================");
            
            pilihan = SelisihWaktu.bacaInt("Pilih menu (1-5): ");
            System.out.println("===============================================================");

            switch (pilihan) {
                case 1: {
                    System.out.println("\n==============================================================");
                    System.out.println("                       DATA WAKTU SAAT INI                    ");
                    System.out.println("================================================================");
                    System.out.print("Waktu 1 (Setter)      : "); waktu1.outputDalam();
                    System.out.print("Waktu 2 (Constructor) : "); waktu2.outputDalam();
                    System.out.print("Waktu 3 (Input Dalam) : "); waktu3.outputDalam();
                    break;
                }

                case 2: {
                    System.out.println("\n==============================================================");
                    int pilihWaktu = SelisihWaktu.bacaInt("Pilih waktu yang ingin diubah (1/2/3): ");
                    System.out.println("================================================================");
                    if (pilihWaktu == 1) {
                        System.out.println("Edit Waktu 1:");
                        waktu1.inputDalam();
                    } else if (pilihWaktu == 2) {
                        System.out.println("Edit Waktu 2:");
                        waktu2.inputDalam();
                    } else if (pilihWaktu == 3) {
                        System.out.println("Edit Waktu 3:");
                        waktu3.inputDalam();
                    } else {
                        System.out.println("Pilihan waktu tidak valid!");
                    }
                    System.out.println("================================================================\n");
                    break;
                }
                case 3: {
                    System.out.println("\n==============================================================");
                    System.out.println(" HITUNG SELISIH (FUNGSI RETURN) ");
                    System.out.println("Pilih objek yang akan diselisihkan:");
                    System.out.println("1. Waktu 1 dan Waktu 2\n2. Waktu 1 dan Waktu 3\n3. Waktu 2 dan Waktu 3");
                    System.out.println("==============================================================\n");
                    int subPilih = SelisihWaktu.bacaInt("Pilihan (1-3): ");
                    
                    SelisihWaktu hasilSelisih;
                    if (subPilih == 1) {
                        hasilSelisih = waktu1.hitungSelisihFungsi(waktu2);
                        System.out.print("Selisih Waktu 1 dan Waktu 2 = ");
                    } else if (subPilih == 2) {
                        hasilSelisih = waktu1.hitungSelisihFungsi(waktu3);
                        System.out.print("Selisih Waktu 1 dan Waktu 3 = ");
                    } else if (subPilih == 3) {
                        hasilSelisih = waktu2.hitungSelisihFungsi(waktu3);
                        System.out.print("Selisih Waktu 2 dan Waktu 3 = ");
                    } else {
                        System.out.println("Pilihan tidak valid!\n");
                        System.out.println("==============================================================\n");
                        break;
                    }
                    hasilSelisih.outputDalam();
                    System.out.println("==============================================================\n");
                    break;
                }
                case 4: {
                    System.out.println("\n==============================================================");
                    System.out.println("                HITUNG SELISIH (METHOD VOID)                  ");
                    System.out.println("==============================================================");
                    System.out.println("Pilih objek yang akan diselisihkan:");
                    System.out.println("1. Waktu 1 & Waktu 2\n2. Waktu 1 & Waktu 3\n3. Waktu 2 & Waktu 3");
                    System.out.println("==============================================================\n");
                    int subPilih = SelisihWaktu.bacaInt("Pilihan (1-3): ");
                    
                    SelisihWaktu hasilVoid = new SelisihWaktu();
                    if (subPilih == 1) {
                        hasilVoid.hitungSelisihVoid(waktu1, waktu2);
                        System.out.print("Hasil Selisih (diobjek baru via Void) Waktu 1 & 2 = ");
                    } else if (subPilih == 2) {
                        hasilVoid.hitungSelisihVoid(waktu1, waktu3);
                        System.out.print("Hasil Selisih (diobjek baru via Void) Waktu 1 & 3 = ");
                    } else if (subPilih == 3) {
                        hasilVoid.hitungSelisihVoid(waktu2, waktu3);
                        System.out.print("Hasil Selisih (diobjek baru via Void) Waktu 2 & 3 = ");
                    } else {
                        System.out.println("Pilihan tidak valid!\n");
                        System.out.println("==============================================================\n");
                        break;
                    }
                    hasilVoid.outputDalam();
                    System.out.println("==============================================================\n");
                    break;
                }
                case 5:
                    System.out.println("Terima kasih telah menggunakan program ini!\n");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.\n");
            }
        } while (pilihan != 5);
    }
}

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.jalankanMenu();
    }
}