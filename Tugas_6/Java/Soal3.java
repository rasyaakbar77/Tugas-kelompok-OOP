/*
Nama Program : Soal3.java
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 29-09-2026
Deskripsi Program : Sebuah program untukmenghitung gaji karyawan dengan input NIP, nama, gol, waktu datang, waktu pulang.
                    Dengan perhitungan Gaji Lembur = >= 8 jam (minimal kelebihan 1 jam / pembulatan ke bawah) dan
                    untuk pegawai yg kurang dari 8 jam diberi status peringatan. Ddengan aturan Gaji = gapok + lembur secara OOP.
*/

import java.util.Scanner;

class Waktu {
    private int jam;
    private int menit;
    private int detik;

    // Constructor Default
    public Waktu() {
        this.jam = 0;
        this.menit = 0;
        this.detik = 0;
    }

    // Constructor Parameter
    public Waktu(int jam, int menit, int detik) {
        this.jam = jam;
        this.menit = menit;
        this.detik = detik;
    }

    // Setter Waktu
    public void setWaktu(int jam, int menit, int detik) {
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
            jam = Soal3.bacaInt("   Jam   (0-23) : ");
        } while (jam < 0 || jam > 23);

        do {
            menit = Soal3.bacaInt("   Menit (0-59) : ");
        } while (menit < 0 || menit > 59);

        do {
            detik = Soal3.bacaInt("   Detik (0-59) : ");
        } while (detik < 0 || detik > 59);
    }

    // Output Dalam
    public void outputDalam() {
        System.out.println("Waktu = " + this);
    }

    // Proses
    public int totalDetik() {
        return (this.jam * 3600) + (this.menit * 60) + this.detik;
    }

    // Method Proses Fungsi Return
    public Waktu hitungSelisihFungsi(Waktu w2) {
        int selisihTotal = Math.abs(this.totalDetik() - w2.totalDetik());

        Waktu hasil = new Waktu();
        hasil.jam = selisihTotal / 3600;
        hasil.menit = (selisihTotal % 3600) / 60;
        hasil.detik = selisihTotal % 60;

        return hasil;
    }

    // Method Proses Void
    public void hitungSelisihVoid(Waktu w1, Waktu w2) {
        int selisihTotal = Math.abs(w1.totalDetik() - w2.totalDetik());

        this.jam = selisihTotal / 3600;
        this.menit = (selisihTotal % 3600) / 60;
        this.detik = selisihTotal % 60;
    }

    // Format Jam:Menit:Detik 
    public String toString() {
        return String.format("%02d:%02d:%02d", jam, menit, detik);
    }
}

class Pegawai {
    private String nip;
    private String nama;
    private int gol;
    private Waktu datang;
    private Waktu pulang;
    private Waktu lamaKerja;
    private Waktu jamLembur;
    private int gajiHarian;
    private int lembur;
    private int total;
    private String statusPeringatan;

    public Pegawai() {
        nip = "";
        nama = "";
        gol = 0;
        datang = new Waktu();
        pulang = new Waktu();
        lamaKerja = new Waktu();
        jamLembur = new Waktu();
        gajiHarian = 0;
        lembur = 0;
        total = 0;
        statusPeringatan = "";
    }

    public Pegawai(String nip, String nama, int gol, Waktu datang, Waktu pulang) {
        this();
        this.nip = nip;
        this.nama = nama;
        this.gol = gol;
        this.datang = datang;
        this.pulang = pulang;
    }

    // input dalam
    public void inputPegawai() {
        System.out.print("Masukkan NIP  : ");
        nip = Soal3.input.nextLine();
        System.out.print("Masukkan Nama : ");
        nama = Soal3.input.nextLine();
        do {
            gol = Soal3.bacaInt("Masukkan Gol (1-4) : ");
        } while (gol < 1 || gol > 4);

        do {
            System.out.println("Waktu Datang :");
            datang.inputDalam();
            System.out.println("Waktu Pulang :");
            pulang.inputDalam();
            if (pulang.totalDetik() <= datang.totalDetik())
                System.out.println("Waktu pulang harus setelah waktu datang, ulangi!");
        } while (pulang.totalDetik() <= datang.totalDetik());
    }

    // setter & getter
    public void setPegawai(String nip, String nama, int gol, Waktu datang, Waktu pulang) {
        this.nip = nip;
        this.nama = nama;
        this.gol = gol;
        this.datang = datang;
        this.pulang = pulang;
    }

    public void setNip(String nip) { 
        this.nip = nip; 
    }
    public void setNama(String nama) { 
        this.nama = nama; 
    }
    public void setGol(int gol) { 
        this.gol = gol; 
    }
    public void setDatang(Waktu datang) { 
        this.datang = datang; 
    }
    public void setPulang(Waktu pulang) { 
        this.pulang = pulang; 
    }

    public String getNip() { 
        return nip; 
    }
    public String getNama() { 
        return nama; 
    }
    public int getGol() { 
        return gol; 
    }
    public int getTotal() { 
        return total; 
    }
    public String getStatusPeringatan() { 
        return statusPeringatan; 
    }


    // proses
    public void prosesGaji() {
        // menggunakan fungsi untuk menghitung lama kerja
        lamaKerja = pulang.hitungSelisihFungsi(datang);

        Waktu batas = new Waktu(8, 0, 0);
        if (lamaKerja.totalDetik() >= batas.totalDetik()) {
            // menggunakan void untuk menghitung jam lembur
            jamLembur.hitungSelisihVoid(lamaKerja, batas);
            statusPeringatan = "ok";
        } else {
            jamLembur = new Waktu();
            statusPeringatan = "Peringatan";
        }

        int tarif = 0;
        switch (gol) {
            case 1: gajiHarian = 150000; tarif = 50000;  break;
            case 2: gajiHarian = 200000; tarif = 75000;  break;
            case 3: gajiHarian = 400000; tarif = 150000; break;
            case 4: gajiHarian = 500000; tarif = 200000; break;
        }

        lembur = jamLembur.getJam() * tarif;
        total = gajiHarian + lembur;
    }

    // output
    public void printPegawai(int no) {
        System.out.printf("%-3d %-6s %-12s %-4d %-9s %-9s %-9s %-11s %-12s %-9s %-9s %s%n",
                no, nip, nama, gol,
                datang, pulang, lamaKerja, jamLembur,
                rupiah(gajiHarian), rupiah(lembur), rupiah(total),
                statusPeringatan);
    }

    private String rupiah(int nilai) {
        return String.format("%,d", nilai).replace(',', '.');
    }
}

class Menu {
    private int pilih;

    public void tampilMenu() {
        Scanner input = Soal3.input;
        Pegawai p1 = null, p2 = null, p3 = null, p4 = null;

        // Menu 
        do {
            System.out.println();
            System.out.println("MENU GAJI HARIAN PT INFORMATIKA");
            System.out.println(" 1. Pegawai 1 : via setter(hardcode)");
            System.out.println(" 2. Pegawai 2 : via constructor berparameter(hardcode)");
            System.out.println(" 3. Pegawai 3 : input dalam class");
            System.out.println(" 4. Pegawai 4 : input luar class (Main)");
            System.out.println(" 5. Tampilkan daftar gaji harian");
            System.out.println(" 0. Keluar");
            pilih = Soal3.bacaInt("Pilih menu: ");

            switch (pilih) {
                //via setter
                case 1:
                    p1 = new Pegawai();
                    Waktu datang1 = new Waktu(8, 0, 0);
                    Waktu pulang1 = new Waktu(17, 15, 10);
                    p1.setNip("250001");
                    p1.setNama("Ali");
                    p1.setGol(3);
                    p1.setDatang(datang1);
                    p1.setPulang(pulang1);
                    p1.prosesGaji();
                    System.out.println("Pegawai 1 berhasil diisi (setter).");
                    break;

                //constructor parameter
                case 2:
                    p2 = new Pegawai("250002", "Budi", 1, new Waktu(8, 0, 0), new Waktu(15, 30, 0));
                    p2.prosesGaji();
                    System.out.println("Pegawai 2 berhasil diisi (constructor).");
                    break;

                // input dalam class
                case 3:
                    System.out.println("\nPegawai 3");
                    p3 = new Pegawai();
                    p3.inputPegawai();
                    p3.prosesGaji();
                    System.out.println("Pegawai 3 berhasil diisi (Scanner dalam class).");
                    break;

                // input luar class
                case 4:
                    System.out.println("\nPegawai 4");
                    System.out.print("Masukkan NIP  : ");
                    String nip = input.nextLine();
                    System.out.print("Masukkan Nama : ");
                    String nama = input.nextLine();

                    int gol;
                    do {
                        gol = Soal3.bacaInt("Masukkan Gol (1-4) : ");
                    } while (gol < 1 || gol > 4);

                    Waktu datang4 = new Waktu();
                    Waktu pulang4 = new Waktu();

                    // loop validasi input luar agar pulang > datang
                    do {
                        System.out.println("Waktu Datang :");
                        int j, m, d;
                        do { j = Soal3.bacaInt("   Jam   (0-23) : "); } while (j < 0 || j > 23);
                        do { m = Soal3.bacaInt("   Menit (0-59) : "); } while (m < 0 || m > 59);
                        do { d = Soal3.bacaInt("   Detik (0-59) : "); } while (d < 0 || d > 59);
                        datang4.setWaktu(j, m, d);

                        System.out.println("Waktu Pulang :");
                        do { j = Soal3.bacaInt("   Jam   (0-23) : "); } while (j < 0 || j > 23);
                        do { m = Soal3.bacaInt("   Menit (0-59) : "); } while (m < 0 || m > 59);
                        do { d = Soal3.bacaInt("   Detik (0-59) : "); } while (d < 0 || d > 59);
                        pulang4.setWaktu(j, m, d);

                        if (pulang4.totalDetik() <= datang4.totalDetik()) {
                            System.out.println("Waktu pulang harus setelah waktu datang, ulangi!");
                        }
                    } while (pulang4.totalDetik() <= datang4.totalDetik());

                    p4 = new Pegawai();
                    p4.setNip(nip);
                    p4.setNama(nama);
                    p4.setGol(gol);
                    p4.setDatang(datang4);
                    p4.setPulang(pulang4);
                    p4.prosesGaji();
                    System.out.println("Pegawai 4 berhasil diisi (Scanner luar class).");
                    break;

                case 5:
                    if (p1 == null && p2 == null && p3 == null && p4 == null) {
                        System.out.println("Belum ada data pegawai!");
                        break;
                    }
                    String garis = "-".repeat(127);
                    System.out.println();
                    System.out.println("                              Daftar Gaji Harian PT Informatika");
                    System.out.println(garis);
                    System.out.printf("%-3s %-6s %-12s %-4s %-9s %-9s %-9s %-11s %-12s %-9s %-9s %s%n",
                            "No", "NIP", "Nama", "Gol", "Datang", "Pulang", "Lama", "Jam Lembur",
                            "Gaji Harian", "Lembur", "Total", "Status");
                    System.out.println(garis);
                    if (p1 != null) p1.printPegawai(1);
                    if (p2 != null) p2.printPegawai(2);
                    if (p3 != null) p3.printPegawai(3);
                    if (p4 != null) p4.printPegawai(4);
                    System.out.println(garis);
                    break;

                case 0:
                    System.out.println("Terima kasih!");
                    break;

                default:
                    System.out.println("Menu tidak tersedia!");
            }
        } while (pilih != 0);
    }
}

public class Soal3 {
    // Statis Scanner
    static Scanner input = new Scanner(System.in);

    //validasi
    public static int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid!");
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.tampilMenu();
    }
}