/*
Nama Program : SelisihWaktu.java
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 29-09-2026
Deskripsi Program : Sebuah program untuk mencari selisih antara waktu datang dan waktu keluar dengan metode OOP, 
                    menggunakan 3 objek dengan 3 cara input berbeda dan 2 method proses (selisih waktu) dengan 
                    return value yang berbeda (fungsi dan void).
*/

import java.util.Scanner;

class SelisihWaktu {
    private int jam;
    private int menit;
    private int detik;

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

    // Input Dalam
    public void inputDalam(Scanner input) {
        System.out.println("Masukkan Waktu : ");
        System.out.print("Jam   : "); this.jam = input.nextInt();
        System.out.print("Menit : "); this.menit = input.nextInt();
        System.out.print("Detik : "); this.detik = input.nextInt();
    }

    // Output Dalam
    public void OutputDalam() {
        System.out.println(jam + " jam, " + menit + " menit, " + detik + " detik");
    }

    // Method Proses Cara 1: Fungsi Return
    public SelisihWaktu hitungSelisihReturn(SelisihWaktu w2) {
        int totalDetik1 = (this.jam * 3600) + (this.menit * 60) + this.detik;
        int totalDetik2 = (w2.jam * 3600) + (w2.menit * 60) + w2.detik;
        int selisihTotal = Math.abs(totalDetik1 - totalDetik2);

        SelisihWaktu hasil = new SelisihWaktu();
        hasil.jam = selisihTotal / 3600;
        selisihTotal %= 3600;
        hasil.menit = selisihTotal / 60;
        hasil.detik = selisihTotal % 60;

        return hasil;
    }

    // Method Proses Cara 2: Void
    public void hitungSelisihVoid(SelisihWaktu w1, SelisihWaktu w2) {
        int totalDetik1 = (w1.jam * 3600) + (w1.menit * 60) + w1.detik;
        int totalDetik2 = (w2.jam * 3600) + (w2.menit * 60) + w2.detik;
        int selisihTotal = Math.abs(totalDetik1 - totalDetik2);

        this.jam = selisihTotal / 3600;
        selisihTotal %= 3600;
        this.menit = selisihTotal / 60;
        this.detik = selisihTotal % 60;
    }
}

class Main {
    // Method inputLuar dijadikan static agar bisa dipanggil langsung di dalam main
    public static void inputLuar(Scanner input, SelisihWaktu waktu) {
        System.out.println("Masukkan Waktu : ");
        System.out.print("Jam   : "); waktu.setJam(input.nextInt());
        System.out.print("Menit : "); waktu.setMenit(input.nextInt());
        System.out.print("Detik : "); waktu.setDetik(input.nextInt());
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 1. Objek 1: Input Menggunakan Setter 
        SelisihWaktu waktu1 = new SelisihWaktu();
        waktu1.setJam(8);
        waktu1.setMenit(30);
        waktu1.setDetik(0);

        // 2. Objek 2: Input Menggunakan Constructor Parameter
        SelisihWaktu waktu2 = new SelisihWaktu(10, 15, 45);

        // 3. Objek 3: Input Menggunakan fungsi Input di Dalam Class
        SelisihWaktu waktu3 = new SelisihWaktu();
        waktu3.inputDalam(input);

        // 4. Objek 4: Input Menggunakan fungsi Input di Luar Class 
        SelisihWaktu waktu4 = new SelisihWaktu();
        inputLuar(input, waktu4);

        System.out.println("\n--- Data Waktu ---");
        System.out.print("Waktu 1 (Setter): "); waktu1.OutputDalam();
        System.out.print("Waktu 2 (Constructor): "); waktu2.OutputDalam();
        System.out.print("Waktu 3 (Input Dalam): "); waktu3.OutputDalam();
        System.out.print("Waktu 4 (Input Luar): "); waktu4.OutputDalam();

        // Contoh Penggunaan Method Proses : Fungsi Return
        SelisihWaktu selisih;
        selisih = waktu1.hitungSelisihReturn(waktu2);
        System.out.print("\nSelisih Waktu 1 dan Waktu 2 (Cara 1 - Return): ");
        selisih.OutputDalam();

        // Contoh Penggunaan Method Proses : Fungsi Void
        SelisihWaktu selisihVoid = new SelisihWaktu();
        selisihVoid.hitungSelisihVoid(waktu2, waktu3);
        System.out.print("Selisih Waktu 2 dan Waktu 3 (Cara 2 - Void): ");
        selisihVoid.OutputDalam();

        input.close();
    }
}