/*
Nama Program : PegawaiApp.java
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 15-10-2026
Deskripsi Program : Sebuah program untuk menghitung gaji karyawan dengan input NIP, nama, gol, waktu datang, waktu pulang,
                    menggunakan struktur OOP dengan kelas pengendali utama (MainApp) yang disesuaikan seperti Larik.java.
*/

import java.util.Scanner;
import java.util.Vector;

class PegawaiAppHelper {
    public static int bacaInt(String pesan) {
        Scanner sc = new Scanner(System.in);
        int val;
        while (true) {
            System.out.print(pesan);
            if (sc.hasNextInt()) {
                val = sc.nextInt();
                sc.nextLine(); 
                return val;
            } else {
                System.out.println("Masukkan angka yang valid!");
                sc.next(); 
            }
        }
    }
}

class Waktu {
    private int jam;
    private int menit;
    private int detik;

    public Waktu() {
        this.jam = 0;
        this.menit = 0;
        this.detik = 0;
    }

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

    public int getJam() { return this.jam; }
    public int getMenit() { return this.menit; }
    public int getDetik() { return this.detik; }

    public void inputDalam() {
        do {
            jam = PegawaiAppHelper.bacaInt("   Jam   (0-23) : ");
        } while (jam < 0 || jam > 23);

        do {
            menit = PegawaiAppHelper.bacaInt("   Menit (0-59) : ");
        } while (menit < 0 || menit > 59);

        do {
            detik = PegawaiAppHelper.bacaInt("   Detik (0-59) : ");
        } while (detik < 0 || detik > 59);
    }

    public int totalDetik() {
        return (this.jam * 3600) + (this.menit * 60) + this.detik;
    }

    public Waktu hitungSelisihFungsi(Waktu w2) {
        int selisihTotal = Math.abs(this.totalDetik() - w2.totalDetik());
        Waktu hasil = new Waktu();
        hasil.jam = selisihTotal / 3600;
        hasil.menit = (selisihTotal % 3600) / 60;
        hasil.detik = selisihTotal % 60;
        return hasil;
    }

    public void hitungSelisihVoid(Waktu w1, Waktu w2) {
        int selisihTotal = Math.abs(w1.totalDetik() - w2.totalDetik());
        this.jam = selisihTotal / 3600;
        this.menit = (selisihTotal % 3600) / 60;
        this.detik = selisihTotal % 60;
    }

    public String toStringFormat() {
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
        this.nip = nip;
        this.nama = nama;
        this.gol = gol;
        this.datang = datang;
        this.pulang = pulang;
        this.lamaKerja = new Waktu();
        this.jamLembur = new Waktu();
        gajiHarian = 0;
        lembur = 0;
        total = 0;
        statusPeringatan = "";
    }

    public void inputPegawai() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan NIP  : ");
        nip = sc.nextLine();
        System.out.print("Masukkan Nama : ");
        nama = sc.nextLine();
        do {
            gol = PegawaiAppHelper.bacaInt("Masukkan Gol (1-4) : ");
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

    public void setNip(String nip) { this.nip = nip; }
    public void setNama(String nama) { this.nama = nama; }
    public void setGol(int gol) { this.gol = gol; }
    public void setDatang(Waktu datang) { this.datang = datang; }
    public void setPulang(Waktu pulang) { this.pulang = pulang; }

    public String getNip() { return nip; }
    public String getNama() { return nama; }
    public int getGol() { return gol; }
    public int getTotal() { return total; }
    public String getStatusPeringatan() { return statusPeringatan; }

    public void prosesGaji() {
        lamaKerja = pulang.hitungSelisihFungsi(datang);

        Waktu batas = new Waktu(8, 0, 0);
        if (lamaKerja.totalDetik() >= batas.totalDetik()) {
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

    public void printPegawai(int no) {
        System.out.printf("%-3d %-7s %-13s %-5d %-10s %-10s %-10s %-12s %-13s %-10s %-10s %s\n",
                no, nip, nama, gol, datang.toStringFormat(), pulang.toStringFormat(),
                lamaKerja.toStringFormat(), jamLembur.toStringFormat(),
                rupiah(gajiHarian), rupiah(lembur), rupiah(total), statusPeringatan);
    }

    private String rupiah(int nilai) {
        String s = String.valueOf(nilai);
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        for (int i = n - 3; i > 0; i -= 3) {
            sb.insert(i, ".");
        }
        return sb.toString();
    }
}

class ArrayPegawai {
    private Vector<Pegawai> pegawai;
    private int ukuran;
    private int jumlah;

    public ArrayPegawai(int ukuran) {
        this.ukuran = ukuran;
        this.jumlah = 0;
        this.pegawai = new Vector<>();
        for(int i = 0; i < ukuran; i++) {
            pegawai.add(new Pegawai());
        }
    }

    public int getUkuran() { return ukuran; }
    public int getJumlah() { return jumlah; }

    public boolean penuh() {
        if (jumlah >= ukuran) {
            System.out.println("Data penuh! Maksimal " + ukuran + " pegawai.");
            return true;
        }
        return false;
    }

    public boolean nipSudahAda(String nip) {
        for (int i = 0; i < jumlah; i++) {
            if (pegawai.get(i).getNip().equals(nip)) {
                return true;
            }
        }
        return false;
    }

    public void simpan(String cara) {
        String nip = pegawai.get(jumlah).getNip();
        if (nipSudahAda(nip)) {
            System.out.println("NIP " + nip + " sudah terdaftar, data tidak disimpan!");
            pegawai.set(jumlah, new Pegawai());
            return;
        }
        jumlah++;
        System.out.println("Pegawai ke-" + jumlah + " berhasil ditambahkan (" + cara + ").");
    }

    public void tambahViaSetter() {
        if (penuh()) return;

        Waktu datang = new Waktu(8, 0, 0);
        Waktu pulang = new Waktu(17, 15, 10);
        pegawai.get(jumlah).setNip("001");
        pegawai.get(jumlah).setNama("Ali");
        pegawai.get(jumlah).setGol(3);
        pegawai.get(jumlah).setDatang(datang);
        pegawai.get(jumlah).setPulang(pulang);
        simpan("setter");
    }

    public void tambahViaConstructor() {
        if (penuh()) return;

        pegawai.set(jumlah, new Pegawai("004", "ida", 2, new Waktu(7, 30, 0), new Waktu(18, 0, 0)));
        simpan("constructor");
    }

    public void tambahInputDalam() {
        if (penuh()) return;

        System.out.println("\nPegawai ke-" + (jumlah + 1));
        pegawai.get(jumlah).inputPegawai();
        simpan("Scanner dalam class");
    }

    public void tambahInputLuar() {
        if (penuh()) return;

        Scanner sc = new Scanner(System.in);
        System.out.println("\nPegawai ke-" + (jumlah + 1));
        System.out.print("Masukkan NIP  : ");
        String nip = sc.nextLine();
        if (nipSudahAda(nip)) {
            System.out.println("NIP " + nip + " sudah terdaftar, data tidak disimpan!");
            return;
        }
        System.out.print("Masukkan Nama : ");
        String nama = sc.nextLine();

        int gol;
        do {
            gol = PegawaiAppHelper.bacaInt("Masukkan Gol (1-4) : ");
        } while (gol < 1 || gol > 4);

        Waktu datang = new Waktu();
        Waktu pulang = new Waktu();

        do {
            System.out.println("Waktu Datang :");
            int j, m, d;
            do { j = PegawaiAppHelper.bacaInt("   Jam   (0-23) : "); } while (j < 0 || j > 23);
            do { m = PegawaiAppHelper.bacaInt("   Menit (0-59) : "); } while (m < 0 || m > 59);
            do { d = PegawaiAppHelper.bacaInt("   Detik (0-59) : "); } while (d < 0 || d > 59);
            datang.setWaktu(j, m, d);

            System.out.println("Waktu Pulang :");
            do { j = PegawaiAppHelper.bacaInt("   Jam   (0-23) : "); } while (j < 0 || j > 23);
            do { m = PegawaiAppHelper.bacaInt("   Menit (0-59) : "); } while (m < 0 || m > 59);
            do { d = PegawaiAppHelper.bacaInt("   Detik (0-59) : "); } while (d < 0 || d > 59);
            pulang.setWaktu(j, m, d);

            if (pulang.totalDetik() <= datang.totalDetik()) {
                System.out.println("Waktu pulang harus setelah waktu datang, ulangi!");
            }
        } while (pulang.totalDetik() <= datang.totalDetik());

        pegawai.get(jumlah).setNip(nip);
        pegawai.get(jumlah).setNama(nama);
        pegawai.get(jumlah).setGol(gol);
        pegawai.get(jumlah).setDatang(datang);
        pegawai.get(jumlah).setPulang(pulang);
        simpan("Scanner luar class");
    }

    public void prosesSemua() {
        for (int i = 0; i < jumlah; i++) {
            pegawai.get(i).prosesGaji();
        }
    }

    public void tampilDaftar() {
        if (jumlah == 0) {
            System.out.println("Belum ada data pegawai!");
            return;
        }
        String garis = new String(new char[127]).replace('\0', '-');
        System.out.println("\n                              Daftar Gaji Harian PT Informatika");
        System.out.println(garis);
        System.out.printf("%-3s %-7s %-13s %-5s %-10s %-10s %-10s %-12s %-13s %-10s %-10s %s\n",
                "No", "NIP", "Nama", "Gol", "Datang", "Pulang", "Lama", "Jam Lembur", "Gaji Harian", "Lembur", "Total", "Status");
        System.out.println(garis);
        for (int i = 0; i < jumlah; i++) {
            pegawai.get(i).printPegawai(i + 1);
        }
        System.out.println(garis);
    }
}

class Menu {
    private ArrayPegawai larik;
    private int pilih;

    public Menu(ArrayPegawai larik) {
        this.larik = larik;
        this.pilih = -1;
    }

    public void tampilMenu() {
        System.out.println("\nMENU GAJI HARIAN PT INFORMATIKA");
        System.out.println("Jumlah pegawai : " + larik.getJumlah() + "/" + larik.getUkuran());
        System.out.println(" 1. Tambah pegawai : via setter(hardcode)");
        System.out.println(" 2. Tambah pegawai : via constructor berparameter(hardcode)");
        System.out.println(" 3. Tambah pegawai : input dalam class");
        System.out.println(" 4. Tambah pegawai : input luar class");
        System.out.println(" 5. Tampilkan daftar gaji harian");
        System.out.println(" 0. Keluar");
    }

    public void inputPilihan() {
        pilih = PegawaiAppHelper.bacaInt("Pilih menu: ");
    }

    public void prosesPilihan() {
        switch (pilih) {
            case 1:
                larik.tambahViaSetter();
                break;
            case 2:
                larik.tambahViaConstructor();
                break;
            case 3:
                larik.tambahInputDalam();
                break;
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

    public void jalankan() {
        do {
            tampilMenu();
            inputPilihan();
            prosesPilihan();
        } while (pilih != 0);
    }
}

class MainApp {
    private ArrayPegawai larik;
    private Menu menuUtama;

    public MainApp() {
        larik = new ArrayPegawai(10);
        menuUtama = new Menu(larik);
    }

    public void jalankanAplikasi() {
        menuUtama.jalankan();
    }
}

public class PegawaiApp {
    public static void main(String[] args) {
        MainApp programUtama = new MainApp();
        programUtama.jalankanAplikasi();
    }
}