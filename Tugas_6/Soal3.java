package Matkul.Tugas-kelompok-OOP.Tugas_6;
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


    public Waktu(int jam, int menit, int detik) {
        this.jam = jam;
        this.menit = menit;
        this.detik = detik;
    }

    // input dalam
    public void inputWaktu() {
        Scanner input = new Scanner(System.in);
        do {
            this.jam = Soal3.bacaInt(input, "   Jam   (0-23) : ");
        } while (jam < 0 || jam > 23);

        do {
            this.menit = Soal3.bacaInt(input, "   Menit (0-59) : ");
        } while (menit < 0 || menit > 59);

        do {
            this.detik = Soal3.bacaInt(input, "   Detik (0-59) : ");
        } while (detik < 0 || detik > 59); 
    }

    //setter
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

    //getter
    public int getJam() {
        return jam;
    }

    public int getMenit() {
        return menit;
    }

    public int getDetik() {
        return detik;
    }

    // proses
    public int totalDetik() {
        return jam * 3600 + menit * 60 + detik;
    }

    // cara 2 (fungsi return) : this - P
    // contoh : pulang.selisih(datang) --> lama kerja
    public Waktu selisih(Waktu P) {
        Waktu pHasil = new Waktu(); // temp
        int sel = this.totalDetik() - P.totalDetik();
        pHasil.jam = sel / 3600;
        pHasil.menit = (sel % 3600) / 60;
        pHasil.detik = sel % 60;
        return pHasil;
    }

    // output
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

    // input dalam
    public void inputPegawai() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan NIP  : ");
        nip = input.nextLine();
        System.out.print("Masukkan Nama : ");
        nama = input.nextLine();
        do {
            gol = Soal3.bacaInt(input, "Masukkan Gol (1-4) : ");   // di case 4: bacaInt(input, ...)
        } while (gol < 1 || gol > 4);

        do {
            System.out.println("Waktu Datang :");
            datang.inputWaktu();
            System.out.println("Waktu Pulang :");
            pulang.inputWaktu();
            if (pulang.totalDetik() <= datang.totalDetik())
                System.out.println("Waktu pulang harus setelah waktu datang, ulangi!");
        } while (pulang.totalDetik() <= datang.totalDetik());
    }

    // setter
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

    // getter
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
        // lama kerja (cara 2 : fungsi return)
        lamaKerja = pulang.selisih(datang);

        // jam lembur = lama kerja - 8 jam (kalau >= 8 jam)
        Waktu batas = new Waktu(8, 0, 0);
        if (lamaKerja.totalDetik() >= batas.totalDetik()) {
            jamLembur = lamaKerja.selisih(batas);
            statusPeringatan = "ok";
        } else {
            jamLembur = new Waktu();
            statusPeringatan = "Peringatan";
        }

        // gaji harian & tarif lembur per golongan
        int tarif = 0;
        switch (gol) {
            case 1: gajiHarian = 150000; tarif = 50000;  break;
            case 2: gajiHarian = 200000; tarif = 75000;  break;
            case 3: gajiHarian = 400000; tarif = 150000; break;
            case 4: gajiHarian = 500000; tarif = 200000; break;
        }

        // lembur dibayar per jam penuh (pembulatan ke bawah)
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

public class Soal3 {
    public static int bacaInt(Scanner input, String pesan) { //helper validasi
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
        Scanner input = new Scanner(System.in);
        int pilih;

        // 4 object pegawai (null = belum diisi)
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
                    Waktu datang1 = new Waktu();
                    Waktu pulang1 = new Waktu();
                    datang1.setWaktu(8, 0, 0);
                    pulang1.setWaktu(17, 15, 10);
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
                    System.out.println("Waktu Datang :");
                    int j = bacaInt(input, "-Jam : ");
                    int m = bacaInt(input, "-Menit : ");
                    int d = bacaInt(input, "-Detik : ");
                    datang4.setWaktu(j, m, d);

                    System.out.println("Waktu Pulang :");
                    j = bacaInt(input, "   Jam   (0-23) : ");
                    m = bacaInt(input, "   Menit   (0-59) : ");
                    d = bacaInt(input, "   Detik   (0-59) : ");
                    pulang4.setWaktu(j, m, d);

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
                    System.out.println(" Terima kasih!");
                    break;
                default:
                    System.out.println(" Menu tidak tersedia!");
            }
        } while (pilih != 0);
        input.close();
    }
}