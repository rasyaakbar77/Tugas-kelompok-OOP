/*
Nama Program : Pegawai.java
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 28-10-2026
Deskripsi Program : Sebuah program untuk menghitung gaji karyawan dengan input NIP, nama, gol, waktu datang, waktu pulang.
                    Menggunakan konsep inheritance dengan membuat superclass Person yang akan di inherit oleh subclass Pegawai,
                    dengan total 5 class secara OOP.
*/

import java.util.Scanner;

class Util {
    private Util() {

    }

    public static int bacaInt(Scanner sc, String pesan) {
        while (true) {
            System.out.print(pesan);
            if (sc.hasNextInt()) {
                int val = sc.nextInt();
                sc.nextLine(); 
                return val;
            }
            System.out.println("Masukkan angka yang valid!");
            sc.nextLine(); 
        }
    }

    public static String rupiah(int nilai) {
        StringBuilder sb = new StringBuilder(String.valueOf(nilai));
        for (int i = sb.length() - 3; i > 0; i -= 3) {
            sb.insert(i, ".");
        }
        return sb.toString();
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
        return jam;
    }

    public int getMenit() {
        return menit;
    }

    public int getDetik() {
        return detik;
    }

    public void inputDalam(Scanner sc) {
        do {
            jam = Util.bacaInt(sc, "  Jam   (0-23) : ");
        } while (jam < 0 || jam > 23);

        do {
            menit = Util.bacaInt(sc, "  Menit (0-59): ");
        } while (menit < 0 || menit > 59);

        do {
            detik = Util.bacaInt(sc, "  Detik (0-59): ");
        } while (detik < 0 || detik > 59);
    }

    public int totalDetik() {
        return (jam * 3600) + (menit * 60) + detik;
    }

    public Waktu hitungSelisih(Waktu w2) {
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

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", jam, menit, detik);
    }

    public void outputDalam() {
        System.out.println("  Waktu: " + this.toString());
    }
}

class Person {
    private String nama;
    private String alamat;

    public Person() {
        this.nama = "";
        this.alamat = "";
    }

    public Person(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
    }

    public void inputDalam(Scanner sc) {
        System.out.print("Nama: ");
        this.nama = sc.nextLine();
        System.out.print("Alamat: ");
        this.alamat = sc.nextLine();
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNama() {
        return this.nama;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void printPerson(int no) {
        System.out.printf("%-3d%-13s%-17s", no, nama, alamat);
    }
}

class Pegawai extends Person {
    private int gol;
    private String nip;
    private Waktu datang = new Waktu();
    private Waktu pulang = new Waktu();
    private Waktu lamaKerja = new Waktu();
    private Waktu jamLembur = new Waktu();
    private int gajiHarian;
    private int lembur;
    private int total;
    private String statusPeringatan;

    public Pegawai() {
        super();
        this.gol = 1;
        this.nip = "";
        this.gajiHarian = 0;
        this.lembur = 0;
        this.total = 0;
        this.statusPeringatan = "";
    }

    public Pegawai(String nip, String nama, String alamat, int umur, int gol) {
        super(nama, alamat);
        this.nip = nip;
        this.gol = gol;
        this.gajiHarian = 0;
        this.lembur = 0;
        this.total = 0;
        this.statusPeringatan = "";
    }

    public void inputPegawai(Scanner scanner) {
        System.out.print("NIP     : ");
        nip = scanner.nextLine();
        super.inputDalam(scanner);

        do {
            gol = Util.bacaInt(scanner, "Masukkan Gol (1-4) : ");
        } while (gol < 1 || gol > 4);

        do {
            System.out.println("Waktu Datang :");
            datang.inputDalam(scanner);
            System.out.println("Waktu Pulang :");
            pulang.inputDalam(scanner);
            if (pulang.totalDetik() <= datang.totalDetik())
                System.out.println("Waktu pulang harus setelah waktu datang, ulangi!");
        } while (pulang.totalDetik() <= datang.totalDetik());
    }

    public void setNip(String n) {
        nip = n;
    }

    public void setGol(int g) {
        gol = g;
    }

    public void setDatang(Waktu d) {
        datang = d;
    }

    public void setPulang(Waktu p) {
        pulang = p;
    }

    public String getNip() {
        return nip;
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

    public void prosesGaji() {
        lamaKerja = pulang.hitungSelisih(datang);

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
        printPerson(no);
        System.out.printf("%-12s%5d%10s%10s%12s%12s%15s%15s%15s  %-12s%n",
                nip, gol,
                datang.toString(), pulang.toString(),
                lamaKerja.toString(), jamLembur.toString(),
                Util.rupiah(gajiHarian), Util.rupiah(lembur), Util.rupiah(total),
                statusPeringatan);
    }
}

class ArrayPegawai {
    private static final int max = 100;
    private Pegawai [] arr;
    private int jumlah;

     public ArrayPegawai() {
        arr = new Pegawai[max];
        for (int i = 0; i < max; i++) {
            arr[i] = new Pegawai();
        }
        jumlah = 0;
    }

    public void setJumlah(int jml) {
        if (jml > max || jml < 0) {
            System.out.println("Jumlah Array Tidak Valid.\n");
        }
        jumlah = (jml > max) ? max : (jml < 0 ? 0 : jml);
    }

    public int getJumlah() { 
        return jumlah;
    }

    public void inputDalam(Scanner sc) {
        do {
            jumlah = Util.bacaInt(sc, "Masukkan jumlah pegawai (1-100): ");
        } while (jumlah < 1 || jumlah > max);

        for (int i = 0; i < jumlah; i++) {
            System.out.println("\nData Pegawai ke-" + (i + 1));
            arr[i].inputPegawai(sc);
        }
    }

    public void prosesData() {
        for (int i = 0; i < jumlah; i++) {
            arr[i].prosesGaji();
        }
    }

    public void outputData() {
        System.out.println("\n\t\t\t\t\tDaftar Gaji Harian PT Informatika");
        System.out.println("=".repeat(150));
        
        System.out.printf("%-3s%-13s%-17s%-12s%5s%10s%10s%12s%12s%15s%15s%15s  %-12s%n",
                "No", "Nama", "Alamat", "NIP", "Gol", 
                "Datang", "Pulang", "Lama", "Jam Lembur", 
                "Gaji Harian", "Lembur", "Total", "Status");
        
        System.out.println("=".repeat(150));

        double totalSemua = 0;
        int maxGaji = (jumlah > 0) ? arr[0].getTotal() : 0;
        int minGaji = (jumlah > 0) ? arr[0].getTotal() : 0;

        for (int i = 0; i < jumlah; i++) {
            arr[i].printPegawai(i + 1);
            int t = arr[i].getTotal();
            totalSemua += t;
            if (t > maxGaji) maxGaji = t;
            if (t < minGaji) minGaji = t;
        }
        System.out.println("=".repeat(150));

        double rataRata = (jumlah > 0) ? (totalSemua / jumlah) : 0;

        System.out.println("Rata-rata Gaji : " + (jumlah > 0 ? Util.rupiah((int) rataRata) : "---"));
        System.out.println("Gaji Tertinggi : " + (jumlah > 0 ? Util.rupiah(maxGaji) : "---"));
        System.out.println("Gaji Terendah  : " + (jumlah > 0 ? Util.rupiah(minGaji) : "---"));
    }
}

class Menu {
    private ArrayPegawai dataPegawai;
    private Scanner sc;

    public Menu() {
        dataPegawai = new ArrayPegawai();
        sc = new Scanner(System.in);
    }

    public void tampilkanMenu() {
        System.out.println("\n========================================");
        System.out.println("         MENU GAJI PT INFORMATIKA         ");
        System.out.println("========================================");
        System.out.println("1. Input Data Pegawai");
        System.out.println("2. Tampilkan Daftar Gaji");
        System.out.println("3. Keluar");
        System.out.println("----------------------------------------");
    }

    public void jalankan() {
        int pilihan;
        do {
            tampilkanMenu();
            pilihan = Util.bacaInt(sc, "Pilih menu (1-3): ");
            switch (pilihan) {
                case 1:
                    dataPegawai.inputDalam(sc);
                    break;
                case 2:
                    dataPegawai.prosesData();
                    dataPegawai.outputData();
                    break;
                case 3:
                    System.out.println("Keluar dari program!");
                    break;
                default:
                    System.out.println("Input tidak valid, silahkan coba lagi!");
            }
        } while (pilihan != 3);
    }
}

public class PegawaiApp {
    public static void main(String[] args) {
        Menu pegawaiApp = new Menu();
        pegawaiApp.jalankan();
    }
}