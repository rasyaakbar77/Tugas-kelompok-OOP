/*
Nama Program : PegawaiApp.java
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 08-10-2026
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
        this.jam = 0;
        this.menit = 0;
        this.detik = 0;
    }

    // constructor parameter
    public Waktu(int jam, int menit, int detik) {
        this.jam = jam;
        this.menit = menit;
        this.detik = detik;
    }

    public void setWaktu(int jam, int menit, int detik) {
        this.jam = jam;
        this.menit = menit;
        this.detik = detik;
    }

    public void setJam(int jam) {
        this.jam = jam;
    }

    public void setMenit(int menit) {
        this.menit = menit;
    }

    public void setDetik(int detik) {
        this.detik = detik;
    }

    public int getJam() {
        return this.jam;
    }

    public int getMenit() {
        return this.menit;
    }

    public int getDetik() {
        return this.detik;
    }

    // input dalam
    public void inputDalam() {
        do {
            jam = PegawaiApp.bacaInt("   Jam   (0-23) : ");
        } while (jam < 0 || jam > 23);

        do {
            menit = PegawaiApp.bacaInt("   Menit (0-59) : ");
        } while (menit < 0 || menit > 59);

        do {
            detik = PegawaiApp.bacaInt("   Detik (0-59) : ");
        } while (detik < 0 || detik > 59);
    }

    // output dalam
    public void outputDalam() {
        System.out.println("Waktu = " + this);
    }

    // proses
    public int totalDetik() {
        return (this.jam * 3600) + (this.menit * 60) + this.detik;
    }

    // method fungsi
    public Waktu hitungSelisihFungsi(Waktu w2) {
        int selisihTotal = Math.abs(this.totalDetik() - w2.totalDetik());

        Waktu hasil = new Waktu();
        hasil.jam = selisihTotal / 3600;
        hasil.menit = (selisihTotal % 3600) / 60;
        hasil.detik = selisihTotal % 60;

        return hasil;
    }

    // method void
    public void hitungSelisihVoid(Waktu w1, Waktu w2) {
        int selisihTotal = Math.abs(w1.totalDetik() - w2.totalDetik());

        this.jam = selisihTotal / 3600;
        this.menit = (selisihTotal % 3600) / 60;
        this.detik = selisihTotal % 60;
    }

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
        nip = PegawaiApp.input.nextLine();
        System.out.print("Masukkan Nama : ");
        nama = PegawaiApp.input.nextLine();
        do {
            gol = PegawaiApp.bacaInt("Masukkan Gol (1-4) : ");
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

class ArrayPegawai {
    private Pegawai[] pegawai;
    private int ukuran; 
    private int jumlah; 

    // constructor 
    public ArrayPegawai() {
        this(10);
    }

    // constructor parameter 
    public ArrayPegawai(int ukuran) {
        this.ukuran = ukuran;
        this.jumlah = 0;
        pegawai = new Pegawai[ukuran];
        for (int i = 0; i < ukuran; i++) {
            pegawai[i] = new Pegawai();
        }
    }

    public int getUkuran() {
        return ukuran;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void tambahViaSetter() {
        if (penuh()) return;

        Waktu datang = new Waktu(8, 0, 0);
        Waktu pulang = new Waktu(17, 15, 10);
        pegawai[jumlah].setNip("001");
        pegawai[jumlah].setNama("Ali");
        pegawai[jumlah].setGol(3);
        pegawai[jumlah].setDatang(datang);
        pegawai[jumlah].setPulang(pulang);
        simpan("setter");
    }

    public void tambahViaConstructor() {
        if (penuh()) return;

        pegawai[jumlah] = new Pegawai("004", "ida", 2, new Waktu(7, 30, 0), new Waktu(18, 0, 0));
        simpan("constructor");
    }

    // Input dalam class 
    public void tambahInputDalam() {
        if (penuh()) return;

        System.out.println("\nPegawai ke-" + (jumlah + 1));
        pegawai[jumlah].inputPegawai();
        simpan("Scanner dalam class");
    }

    // input luar class 
    public void tambahInputLuar() {
        if (penuh()) return;

        Scanner input = PegawaiApp.input;
        System.out.println("\nPegawai ke-" + (jumlah + 1));
        System.out.print("Masukkan NIP  : ");
        String nip = input.nextLine();
        if (nipSudahAda(nip)) {
            System.out.println("NIP " + nip + " sudah terdaftar, data tidak disimpan!");
            return;
        }
        System.out.print("Masukkan Nama : ");
        String nama = input.nextLine();

        int gol;
        do {
            gol = PegawaiApp.bacaInt("Masukkan Gol (1-4) : ");
        } while (gol < 1 || gol > 4);

        Waktu datang = new Waktu();
        Waktu pulang = new Waktu();

        // loop validasi input luar agar pulang > datang
        do {
            System.out.println("Waktu Datang :");
            int j, m, d;
            do { j = PegawaiApp.bacaInt("   Jam   (0-23) : "); } while (j < 0 || j > 23);
            do { m = PegawaiApp.bacaInt("   Menit (0-59) : "); } while (m < 0 || m > 59);
            do { d = PegawaiApp.bacaInt("   Detik (0-59) : "); } while (d < 0 || d > 59);
            datang.setWaktu(j, m, d);

            System.out.println("Waktu Pulang :");
            do { j = PegawaiApp.bacaInt("   Jam   (0-23) : "); } while (j < 0 || j > 23);
            do { m = PegawaiApp.bacaInt("   Menit (0-59) : "); } while (m < 0 || m > 59);
            do { d = PegawaiApp.bacaInt("   Detik (0-59) : "); } while (d < 0 || d > 59);
            pulang.setWaktu(j, m, d);

            if (pulang.totalDetik() <= datang.totalDetik()) {
                System.out.println("Waktu pulang harus setelah waktu datang, ulangi!");
            }
        } while (pulang.totalDetik() <= datang.totalDetik());

        pegawai[jumlah].setNip(nip);
        pegawai[jumlah].setNama(nama);
        pegawai[jumlah].setGol(gol);
        pegawai[jumlah].setDatang(datang);
        pegawai[jumlah].setPulang(pulang);
        simpan("Scanner luar class");
    }

    // proses (hitung gaji semua pegawai yang sudah terisi)
    public void prosesSemua() {
        for (int i = 0; i < jumlah; i++) {
            pegawai[i].prosesGaji();
        }
    }

    // output
    public void tampilDaftar() {
        if (jumlah == 0) {
            System.out.println("Belum ada data pegawai!");
            return;
        }
        String garis = "-".repeat(127);
        System.out.println();
        System.out.println("                              Daftar Gaji Harian PT Informatika");
        System.out.println(garis);
        System.out.printf("%-3s %-6s %-12s %-4s %-9s %-9s %-9s %-11s %-12s %-9s %-9s %s%n",
                "No", "NIP", "Nama", "Gol", "Datang", "Pulang", "Lama", "Jam Lembur",
                "Gaji Harian", "Lembur", "Total", "Status");
        System.out.println(garis);
        for (int i = 0; i < jumlah; i++) {
            pegawai[i].printPegawai(i + 1);
        }
        System.out.println(garis);
    }

    // cek array sudah penuh atau belum
    private boolean penuh() {
        if (jumlah >= ukuran) {
            System.out.println("Data penuh! Maksimal " + ukuran + " pegawai.");
            return true;
        }
        return false;
    }

    // cek NIP kembar (hanya pada elemen yang sudah terisi)
    private boolean nipSudahAda(String nip) {
        for (int i = 0; i < jumlah; i++) {
            if (pegawai[i].getNip().equals(nip)) {
                return true;
            }
        }
        return false;
    }

    // simpan elemen ke jumlah (jumlah bertambah) kalau NIP nya belum terdaftar
    private void simpan(String cara) {
        String nip = pegawai[jumlah].getNip();
        if (nipSudahAda(nip)) {
            System.out.println("NIP " + nip + " sudah terdaftar, data tidak disimpan!");
            pegawai[jumlah] = new Pegawai(); 
            return;
        }
        jumlah++;
        System.out.println("Pegawai ke-" + jumlah + " berhasil ditambahkan (" + cara + ").");
    }
}

class Menu {
    private ArrayPegawai larik;
    private int pilih;

    // constructor
    public Menu(ArrayPegawai larik) {
        this.larik = larik;
        this.pilih = -1;
    }

    // output
    public void tampilMenu() {
        System.out.println();
        System.out.println("MENU GAJI HARIAN PT INFORMATIKA");
        System.out.println("Jumlah pegawai : " + larik.getJumlah() + "/" + larik.getUkuran());
        System.out.println(" 1. Tambah pegawai : via setter(hardcode)");
        System.out.println(" 2. Tambah pegawai : via constructor berparameter(hardcode)");
        System.out.println(" 3. Tambah pegawai : input dalam class");
        System.out.println(" 4. Tambah pegawai : input luar class");
        System.out.println(" 5. Tampilkan daftar gaji harian");
        System.out.println(" 0. Keluar");
    }

    public void inputPilihan() {
        pilih = PegawaiApp.bacaInt("Pilih menu: ");
    }

    public void prosesPilihan() {
        switch (pilih) {
            //via setter
            case 1:
                larik.tambahViaSetter();
                break;

            //constructor parameter
            case 2:
                larik.tambahViaConstructor();
                break;

            // input dalam class
            case 3:
                larik.tambahInputDalam();
                break;

            // input luar class
            case 4:
                larik.tambahInputLuar();
                break;

            case 5:
                larik.prosesSemua();
                larik.tampilDaftar();
                break;

            case 0:
                System.out.println("Terima kasih!");
                break;

            default:
                System.out.println("Menu tidak tersedia!");
        }
    }

    // menu 
    public void jalankan() {
        do {
            tampilMenu();
            inputPilihan();
            prosesPilihan();
        } while (pilih != 0);
    }
}

public class PegawaiApp {
    // statis Scanner
    static Scanner input = new Scanner(System.in);

    // batas maksimal pegawai
    static final int MAKS = 10;

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
        ArrayPegawai larik = new ArrayPegawai(MAKS);
        Menu menu = new Menu(larik);
        menu.jalankan();
    }
}