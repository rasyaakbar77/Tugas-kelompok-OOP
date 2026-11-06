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

    public static String pad(String s, int w, char rata) {
        if (s.length() > w) s = s.substring(0, w);
        int sisa = w - s.length();
        if (rata == '<') return s + " ".repeat(sisa);
        if (rata == '>') return " ".repeat(sisa) + s;
        int kiri = sisa / 2;
        return " ".repeat(kiri) + s + " ".repeat(sisa - kiri);
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
    protected String nama;
    protected String alamat;

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
}

class Pegawai extends Person {
    private int gol;
    private String nip;
    private Waktu datang = new Waktu();
    private Waktu pulang = new Waktu();
    private Waktu lamaKerja = new Waktu();
    private Waktu jamLembur = new Waktu();
    private String statusPeringatan;

    public Pegawai() {
        super();
        this.gol = 1;
        this.nip = "";
        this.statusPeringatan = "";
    }

    public Pegawai(String nip, String nama, String alamat, int umur, int gol) {
        super(nama, alamat);
        this.nip = nip;
        this.gol = gol;
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

    public String getStatusPeringatan() {
        return statusPeringatan;
    }

    public int getGajiHarian() {
        switch (gol) {
            case 1: return 150000;
            case 2: return 200000;
            case 3: return 400000;
            case 4: return 500000;
            default: return 0;
        }
    }

    public int getTarifLembur() {
        switch (gol) {
            case 1: return 50000;
            case 2: return 75000;
            case 3: return 150000;
            case 4: return 200000;
            default: return 0;
        }
    }

    public int getLembur() {
        return jamLembur.getJam() * getTarifLembur();
    }

    public int getTotal() {
        return getGajiHarian() + getLembur();
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
    }

    public String[] getBaris(int no) {
        return new String[] {
            String.valueOf(no), nama, alamat, nip, String.valueOf(gol),
            datang.toString(), pulang.toString(),
            lamaKerja.toString(), jamLembur.toString(),
            Util.rupiah(getGajiHarian()), Util.rupiah(getLembur()), Util.rupiah(getTotal()),
            statusPeringatan
        };
    }
}

class ArrayPegawai {
    private static final int max = 100;
    private static final String[] HEADER = {"No", "Nama", "Alamat", "NIP", "Gol", "Datang", "Pulang", "Lama", "Jam Lembur", "Gaji Harian", "Lembur", "Total", "Status"};
    private static final int[] LEBAR = {3, 10, 14, 8, 3, 8, 8, 8, 10, 11, 11, 11, 10};
    private static final char[] RATA = {'^', '<', '<', '<', '^', '^', '^', '^', '^', '>', '>', '>', '^'};

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

    private String garis() {
        StringBuilder sb = new StringBuilder("+");
        for (int w : LEBAR) sb.append("-".repeat(w + 2)).append("+");
        return sb.toString();
    }

    private String baris(String[] cells) {
        StringBuilder sb = new StringBuilder("|");
        for (int i = 0; i < cells.length; i++) {
            sb.append(" ").append(Util.pad(cells[i], LEBAR[i], RATA[i])).append(" |");
        }
        return sb.toString();
    }

    public void outputData() {
        String garis = garis();
        System.out.println("\n" + Util.pad("Daftar Gaji Harian PT Informatika", garis.length(), '^'));
        System.out.println(garis);
        System.out.println(baris(HEADER));
        System.out.println(garis);

        double totalSemua = 0;
        int maxGaji = (jumlah > 0) ? arr[0].getTotal() : 0;
        int minGaji = (jumlah > 0) ? arr[0].getTotal() : 0;

        for (int i = 0; i < jumlah; i++) {
            System.out.println(baris(arr[i].getBaris(i + 1)));
            int t = arr[i].getTotal();
            totalSemua += t;
            if (t > maxGaji) maxGaji = t;
            if (t < minGaji) minGaji = t;
        }
        System.out.println(garis);

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