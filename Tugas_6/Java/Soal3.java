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

    public Waktu() {
        jam = 0;
        menit = 0;
        detik = 0;
    }

    // Constructor Parameter dengan Validasi
    public Waktu(int jam, int menit, int detik) {
        this.jam = (jam >= 0 && jam <= 23) ? jam : 0;
        this.menit = (menit >= 0 && menit <= 59) ? menit : 0;
        this.detik = (detik >= 0 && detik <= 59) ? detik : 0;
    }

    // Input Dalam Class (dengan validasi range yang benar)
    public void inputWaktu() {
        Scanner input = new Scanner(System.in);
        do {
            this.jam = Soal3.bacaInt(input, "   Jam   (0-23) : ");
        } while (this.jam < 0 || this.jam > 23);

        do {
            this.menit = Soal3.bacaInt(input, "   Menit (0-59) : ");
        } while (this.menit < 0 || this.menit > 59);

        do {
            this.detik = Soal3.bacaInt(input, "   Detik (0-59) : ");
        } while (this.detik < 0 || this.detik > 59); 
    }

    // Setter
    public void setWaktu(int jam, int menit, int detik) {
        this.jam = (jam >= 0 && jam <= 23) ? jam : 0;
        this.menit = (menit >= 0 && menit <= 59) ? menit : 0;
        this.detik = (detik >= 0 && detik <= 59) ? detik : 0;
    }

    public void setJam(int jam) { 
        this.jam = (jam >= 0 && jam <= 23) ? jam : 0; 
    }
    public void setMenit(int menit) { 
        this.menit = (menit >= 0 && menit <= 59) ? menit : 0; 

    }
    public void setDetik(int detik) { 
        this.detik = (detik >= 0 && detik <= 59) ? detik : 0; 
    }

    // Getter
    public int getJam() { 
        return jam; 
    }
    public int getMenit() { 
        return menit; 
    }
    public int getDetik() { 
        return detik; 
    }

    // Proses
    public int totalDetik() {
        return jam * 3600 + menit * 60 + detik;
    }

    public Waktu selisih(Waktu P) {
        Waktu pHasil = new Waktu();
        int sel = this.totalDetik() - P.totalDetik();
        if (sel < 0) sel = 0; // Mencegah nilai minus
        pHasil.jam = sel / 3600;
        pHasil.menit = (sel % 3600) / 60;
        pHasil.detik = sel % 60;
        return pHasil;
    }

    // Output
    public String toString() {
        return String.format("%02d:%02d:%02d", jam, menit, detik);
    }

    public void printWaktu() {
        System.out.println(" Waktu = " + toString());
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

    // Input Dalam Class
    public void inputPegawai() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan NIP  : ");
        nip = input.nextLine();
        System.out.print("Masukkan Nama : ");
        nama = input.nextLine();
        do {
            gol = Soal3.bacaInt(input, "Masukkan Gol (1-4) : ");
        } while (gol < 1 || gol > 4);

        do {
            System.out.println("Waktu Datang :");
            datang.inputWaktu();
            System.out.println("Waktu Pulang :");
            pulang.inputWaktu();
            if (pulang.totalDetik() <= datang.totalDetik())
                System.out.println(" [!] Waktu pulang harus setelah waktu datang, ulangi!");
        } while (pulang.totalDetik() <= datang.totalDetik());
    }

    // Setter & Getter
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

    // Proses
    public void prosesGaji() {
        lamaKerja = pulang.selisih(datang);

        Waktu batas = new Waktu(8, 0, 0);
        if (lamaKerja.totalDetik() >= batas.totalDetik()) {
            jamLembur = lamaKerja.selisih(batas);
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

    // Output
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

public class Soal3 {
    public static int bacaInt(Scanner input, String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(" [!] Masukkan angka yang valid!");
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilih;

        Pegawai p1 = null, p2 = null, p3 = null, p4 = null;

        do {
            System.out.println();
            System.out.println("MENU GAJI HARIAN PT INFORMATIKA");
            System.out.println(" 1. Pegawai 1 : via setter(hardcode)");
            System.out.println(" 2. Pegawai 2 : via constructor berparameter(hardcode)");
            System.out.println(" 3. Pegawai 3 : input Scanner di dalam class");
            System.out.println(" 4. Pegawai 4 : input Scanner di luar class (Main)");
            System.out.println(" 5. Tampilkan daftar gaji harian");
            System.out.println(" 0. Keluar");
            pilih = bacaInt(input, "Pilih menu: ");

            switch (pilih) {
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

                case 2:
                    p2 = new Pegawai("250002", "Budi", 1, new Waktu(8, 0, 0), new Waktu(15, 30, 0));
                    p2.prosesGaji();
                    System.out.println("Pegawai 2 berhasil diisi (constructor).");
                    break;

                case 3:
                    System.out.println("\nPegawai 3");
                    p3 = new Pegawai();
                    p3.inputPegawai();
                    p3.prosesGaji();
                    System.out.println("Pegawai 3 berhasil diisi (Scanner dalam class).");
                    break;

                case 4:
                    System.out.println("\nPegawai 4");
                    System.out.print("Masukkan NIP  : ");
                    String nip = input.nextLine();
                    System.out.print("Masukkan Nama : ");
                    String nama = input.nextLine();
                    
                    int gol;
                    do {
                        gol = Soal3.bacaInt(input, "Masukkan Gol (1-4) : ");   
                    } while (gol < 1 || gol > 4);

                    Waktu datang4 = new Waktu();
                    Waktu pulang4 = new Waktu();

                    // Loop validasi input luar agar pulang > datang
                    do {
                        System.out.println("Waktu Datang :");
                        int j, m, d;
                        do { j = bacaInt(input, "   Jam   (0-23) : "); } while (j < 0 || j > 23);
                        do { m = bacaInt(input, "   Menit (0-59) : "); } while (m < 0 || m > 59);
                        do { d = bacaInt(input, "   Detik (0-59) : "); } while (d < 0 || d > 59);
                        datang4.setWaktu(j, m, d);

                        System.out.println("Waktu Pulang :");
                        do { j = bacaInt(input, "   Jam   (0-23) : "); } while (j < 0 || j > 23);
                        do { m = bacaInt(input, "   Menit (0-59) : "); } while (m < 0 || m > 59);
                        do { d = bacaInt(input, "   Detik (0-59) : "); } while (d < 0 || d > 59);
                        pulang4.setWaktu(j, m, d);

                        if (pulang4.totalDetik() <= datang4.totalDetik()) {
                            System.out.println(" [!] Waktu pulang harus setelah waktu datang, ulangi!");
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
        input.close();
    }
}