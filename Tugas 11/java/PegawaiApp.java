import java.util.Scanner;

// Kelas utilitas untuk validasi input dan pemformatan
class Util {
    private static final Scanner scanner = new Scanner(System.in);

    public static int bacaInt(String pesan) {
        int val = 0;
        while (true) {
            System.out.print(pesan);
            if (scanner.hasNextInt()) {
                val = scanner.nextInt();
                scanner.nextLine(); 
                return val;
            } else {
                System.out.println("Masukkan angka yang valid!");
                scanner.next(); // buang input invalid
            }
        }
    }

    public static String bacaString(String pesan) {
        System.out.print(pesan);
        return scanner.nextLine();
    }

    public static String rupiah(int nilai) {
        String s = String.valueOf(nilai);
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        for (int i = n - 3; i > 0; i -= 3) {
            sb.insert(i, ".");
        }
        return sb.toString();
    }
}

// Kelas Waktu
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

    public void setWaktu(int j, int m, int d) {
        this.jam = j;
        this.menit = m;
        this.detik = d;
    }

    public int getJam() { return jam; }
    public int getMenit() { return menit; }
    public int getDetik() { return detik; }

    public void inputDalam() {
        do {
            jam = Util.bacaInt("   Jam   (0-23) : ");
        } while (jam < 0 || jam > 23);

        do {
            menit = Util.bacaInt("   Menit (0-59) : ");
        } while (menit < 0 || menit > 59);

        do {
            detik = Util.bacaInt("   Detik (0-59) : ");
        } while (detik < 0 || detik > 59);
    }

    public int totalDetik() {
        return (jam * 3600) + (menit * 60) + detik;
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

    public String toString() {
        return String.format("%02d:%02d:%02d", jam, menit, detik);
    }
}

// Superclass Person
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

    public void inputDalam() {
        nama = Util.bacaString("Nama   : ");
        alamat = Util.bacaString("Alamat : ");
    }

    public void setNama(String nama) { this.nama = nama; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public String getNama() { return nama; }
    public String getAlamat() { return alamat; }

    public void printPerson(int no) {
        System.out.printf("%-3d %-13s %-17s", no, nama, alamat);
    }
}

// Subclass Pegawai
class Pegawai extends Person {
    private int gol;
    private String nip;
    private Waktu datang;
    private Waktu pulang;
    private Waktu lamaKerja;
    private Waktu jamLembur;
    private String statusPeringatan;

    public Pegawai() {
        super();
        this.gol = 1;
        this.nip = "";
        this.datang = new Waktu();
        this.pulang = new Waktu();
        this.lamaKerja = new Waktu();
        this.jamLembur = new Waktu();
        this.statusPeringatan = "";
    }

    public void inputPegawai() {
        nip = Util.bacaString("NIP     : ");
        super.inputDalam();

        do {
            gol = Util.bacaInt("Masukkan Gol (1-4) : ");
        } while (gol < 1 || gol > 4);

        do {
            System.out.println("Waktu Datang :");
            datang.inputDalam();
            System.out.println("Waktu Pulang :");
            pulang.inputDalam();
            if (pulang.totalDetik() <= datang.totalDetik()) {
                System.out.println("Waktu pulang harus setelah waktu datang, ulangi!");
            }
        } while (pulang.totalDetik() <= datang.totalDetik());
    }

    public void setNip(String nip) { this.nip = nip; }
    public void setGol(int gol) { this.gol = gol; }
    public String getNip() { return nip; }
    public int getGol() { return gol; }
    public String getStatusPeringatan() { return statusPeringatan; }

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
        return getTarifLembur() * jamLembur.totalDetik() / 3600;
    }

    public int getTotal() {
        return getGajiHarian() + getLembur();
    }

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
    }

    public void printPegawai(int no) {
        printPerson(no);
        System.out.printf("%-12s %5d %10s %10s %12s %12s %15s %15s %15s  %-12s%n",
                nip, gol, datang.toString(), pulang.toString(),
                lamaKerja.toString(), jamLembur.toString(),
                Util.rupiah(getGajiHarian()), Util.rupiah(getLembur()),
                Util.rupiah(getTotal()), statusPeringatan);
    }
}

// Kelas ArrayPegawai 
class ArrayPegawai {
    private static final int MAX = 100;
    private Pegawai[] arr = new Pegawai[MAX];
    private int jumlah = 0;

    public ArrayPegawai() {
        for (int i = 0; i < MAX; i++) {
            arr[i] = new Pegawai();
        }
    }

    public int getJumlah() { return jumlah; }

    public void inputDalam() {
        if (jumlah >= MAX) {
            System.out.println("\nKapasitas data pegawai sudah penuh (maksimal 100)!");
            return;
        }

        int sisaKapasitas = MAX - jumlah;
        int tambahan = Util.bacaInt("Masukkan jumlah pegawai yang ingin ditambahkan (1-" + sisaKapasitas + "): ");
        while (tambahan < 1 || tambahan > sisaKapasitas) {
            System.out.println("Jumlah tidak valid!");
            tambahan = Util.bacaInt("Masukkan jumlah pegawai yang ingin ditambahkan (1-" + sisaKapasitas + "): ");
        }

        for (int i = 0; i < tambahan; i++) {
            System.out.println("\nData Pegawai ke-" + (jumlah + 1));
            arr[jumlah].inputPegawai();
            jumlah++;
        }
        System.out.println("\nBerhasil menambahkan " + tambahan + " data pegawai baru!");
    }

    public void prosesData() {
        for (int i = 0; i < jumlah; i++) {
            arr[i].prosesGaji();
        }
    }

    public Pegawai getPegawaiAt(int index) {
        if (index >= 0 && index < MAX) {
            return arr[index];
        }
        return null;
    }

    public void outputData() {
        System.out.println("\n\t\t\t\t\tDaftar Gaji Harian PT Informatika");
        System.out.println("=" .repeat(150));
        System.out.printf("%-3s %-13s %-17s %-12s %5s %10s %10s %12s %12s %15s %15s %15s  %-12s%n",
                "No", "Nama", "Alamat", "NIP", "Gol", "Datang", "Pulang", "Lama", "Jam Lembur",
                "Gaji Harian", "Lembur", "Total", "Status");
        System.out.println("=" .repeat(150));

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
        System.out.println("=" .repeat(150));

        double rataRata = (jumlah > 0) ? (totalSemua / jumlah) : 0;
        System.out.println("Rata-rata Gaji : " + (jumlah > 0 ? Util.rupiah((int)rataRata) : "---"));
        System.out.println("Gaji Tertinggi : " + (jumlah > 0 ? Util.rupiah(maxGaji) : "---"));
        System.out.println("Gaji Terendah  : " + (jumlah > 0 ? Util.rupiah(minGaji) : "---"));
    }
}

// Abstract Superclass Kendaraan
abstract class Kendaraan {
    protected String merk;
    protected String plat;
    protected String warna;
    protected String nipPemilik;
    protected String namaPemilik;
    protected Waktu masuk;
    protected Waktu keluar;

    public Kendaraan() {
        this.merk = "";
        this.plat = "";
        this.warna = "";
        this.nipPemilik = "";
        this.namaPemilik = "";
        this.masuk = new Waktu();
        this.keluar = new Waktu();
    }

    public void setMerk(String merk) { this.merk = merk; }
    public void setPlat(String plat) { this.plat = plat; }
    public void setWarna(String warna) { this.warna = warna; }
    
    public String getMerk() { return merk; }
    public String getPlat() { return plat; }
    public String getWarna() { return warna; }

    public void setPemilik(String nip, String nama) {
        this.nipPemilik = nip;
        this.namaPemilik = nama;
    }

    public String getNipPemilik() { return nipPemilik; }
    public String getNamaPemilik() { return namaPemilik; }

    public void setMasuk(Waktu w) { this.masuk = w; }
    public Waktu getMasuk() { return masuk; }

    public boolean isPegawai() {
        return !nipPemilik.isEmpty();
    }

    public boolean isSudahKeluar() {
        return keluar.totalDetik() > 0;
    }

    public boolean setKeluar(Waktu w) {
        if (w.totalDetik() <= masuk.totalDetik()) {
            return false;
        }
        keluar = w;
        return true;
    }

    public Waktu getKeluar() { return keluar; }

    // Abstract methods untuk polimorfisme
    public abstract void inputSubclass();
    public abstract long hitungBiaya(ArrayPegawai db);
    public abstract String getJenisKendaraan();
}

// Subclass Motor
class Motor extends Kendaraan {
    private boolean adaFootStep;

    public Motor() {
        super();
        this.adaFootStep = false;
    }

    public void setAdaFootStep(boolean ada) { this.adaFootStep = ada; }
    public boolean getAdaFootStep() { return adaFootStep; }

    @Override
    public String getJenisKendaraan() {
        return "Motor";
    }

    @Override
    public void inputSubclass() {
        while (true) {
            String input = Util.bacaString("Apakah ada footstep? (y/n): ");
            if (input.equalsIgnoreCase("y")) {
                adaFootStep = true;
                break;
            } else if (input.equalsIgnoreCase("n")) {
                adaFootStep = false;
                break;
            } else {
                System.out.println("Input tidak valid. Silakan masukkan 'y' atau 'n'.");
            }
        }
    }

    @Override
    public long hitungBiaya(ArrayPegawai db) {
        long totalDetik = keluar.totalDetik() - masuk.totalDetik();
        if (totalDetik < 0) totalDetik = 0;

        long totalJam = (totalDetik + 3599) / 3600;
        if (totalJam < 1) totalJam = 1;

        boolean validPegawai = false;
        if (!nipPemilik.isEmpty()) {
            for (int i = 0; i < db.getJumlah(); i++) {
                if (db.getPegawaiAt(i).getNip().equals(nipPemilik)) {
                    validPegawai = true;
                    break;
                }
            }
        }

        if (validPegawai) {
            long totalHari = (totalDetik + 86399) / 86400;
            if (totalHari < 1) totalHari = 1;
            return totalHari * 3000; // Tarif pegawai motor
        } else {
            return totalJam * 2000;  // Tarif umum motor
        }
    }
}

// Subclass Mobil
class Mobil extends Kendaraan {
    private int jumlahPintu;
    private boolean adaSunroof;

    public Mobil() {
        super();
        this.jumlahPintu = 4;
        this.adaSunroof = false;
    }

    @Override
    public String getJenisKendaraan() {
        return "Mobil";
    }

    @Override
    public void inputSubclass() {
        jumlahPintu = Util.bacaInt("Jumlah Pintu : ");
        while (true) {
            String input = Util.bacaString("Apakah ada sunroof? (y/n): ");
            if (input.equalsIgnoreCase("y")) {
                adaSunroof = true;
                break;
            } else if (input.equalsIgnoreCase("n")) {
                adaSunroof = false;
                break;
            } else {
                System.out.println("Input tidak valid. Silakan masukkan 'y' atau 'n'.");
            }
        }
    }

    @Override
    public long hitungBiaya(ArrayPegawai db) {
        long totalDetik = keluar.totalDetik() - masuk.totalDetik();
        if (totalDetik < 0) totalDetik = 0;

        long totalJam = (totalDetik + 3599) / 3600;
        if (totalJam < 1) totalJam = 1;

        boolean validPegawai = false;
        if (!nipPemilik.isEmpty()) {
            for (int i = 0; i < db.getJumlah(); i++) {
                if (db.getPegawaiAt(i).getNip().equals(nipPemilik)) {
                    validPegawai = true;
                    break;
                }
            }
        }

        if (validPegawai) {
            long totalHari = (totalDetik + 86399) / 86400;
            if (totalHari < 1) totalHari = 1;
            return totalHari * 6000; // Tarif pegawai mobil
        } else {
            return totalJam * 3000;  // Tarif umum mobil
        }
    }
}

// Subclass Truk
class Truk extends Kendaraan {
    private double kapasitasMuatanTon;
    private boolean adaRemAngin;

    public Truk() {
        super();
        this.kapasitasMuatanTon = 5.0;
        this.adaRemAngin = true;
    }

    @Override
    public String getJenisKendaraan() {
        return "Truk";
    }

    @Override
    public void inputSubclass() {
        kapasitasMuatanTon = (double) Util.bacaInt("Kapasitas Muatan (Ton) : ");
        while (true) {
            String input = Util.bacaString("Apakah ada rem angin? (y/n): ");
            if (input.equalsIgnoreCase("y")) {
                adaRemAngin = true;
                break;
            } else if (input.equalsIgnoreCase("n")) {
                adaRemAngin = false;
                break;
            } else {
                System.out.println("Input tidak valid. Silakan masukkan 'y' atau 'n'.");
            }
        }
    }

    @Override
    public long hitungBiaya(ArrayPegawai db) {
        long totalDetik = keluar.totalDetik() - masuk.totalDetik();
        if (totalDetik < 0) totalDetik = 0;

        long totalJam = (totalDetik + 3599) / 3600;
        if (totalJam < 1) totalJam = 1;

        return totalJam * 5000; // Truk pegawai tetap menggunakan tarif umum
    }
}

// Kelas Parkir
class Parkir {
    private static final int MAX_KENDARAAN = 100;
    private Kendaraan[] arr = new Kendaraan[MAX_KENDARAAN];
    private int jumlahKendaraan = 0;

    public int getjumlahKendaraan() { return jumlahKendaraan; }

    public boolean isPlatAktif(String plat) {
        for (int i = 0; i < jumlahKendaraan; i++) {
            if (!arr[i].isSudahKeluar() && arr[i].getPlat().equalsIgnoreCase(plat)) {
                return true;
            }
        }
        return false;
    }

    public void inputKendaraanMasuk(ArrayPegawai dbPegawai) {
        if (jumlahKendaraan >= MAX_KENDARAAN) {
            System.out.println("\nArea parkir sudah penuh (maksimal 100)!");
            return;
        }

        int pilihan;
        do {
            System.out.println("\nPilih Jenis Kendaraan:");
            System.out.println("1. Motor\n2. Mobil\n3. Truk");
            pilihan = Util.bacaInt("Pilihan (1-3): ");
        } while (pilihan < 1 || pilihan > 3);

        String platTemp = Util.bacaString("No. Plat : ");
        if (isPlatAktif(platTemp)) {
            System.out.println("Error: Plat nomor tersebut masih aktif terparkir!");
            return;
        }

        Kendaraan kBaru = null;
        if (pilihan == 1) kBaru = new Motor();
        else if (pilihan == 2) kBaru = new Mobil();
        else if (pilihan == 3) kBaru = new Truk();

        kBaru.setPlat(platTemp);

        String merk = Util.bacaString("Merk  : ");
        kBaru.setMerk(merk);
        String warna = Util.bacaString("Warna : ");
        kBaru.setWarna(warna);

        kBaru.inputSubclass();

        String nip = Util.bacaString("NIP Pemilik (kosongkan jika umum): ");
        String namaPemilikStr = "Umum / Tamu";
        if (!nip.isEmpty()) {
            for (int i = 0; i < dbPegawai.getJumlah(); i++) {
                if (dbPegawai.getPegawaiAt(i).getNip().equals(nip)) {
                    namaPemilikStr = dbPegawai.getPegawaiAt(i).getNama();
                    break;
                }
            }
        }
        kBaru.setPemilik(nip, namaPemilikStr);

        System.out.println("Waktu Masuk:");
        Waktu wMasuk = new Waktu();
        wMasuk.inputDalam();
        kBaru.setMasuk(wMasuk);

        arr[jumlahKendaraan] = kBaru;
        jumlahKendaraan++;
        System.out.println("Kendaraan berhasil masuk area parkir!");
    }

    public void prosesKendaraanKeluar(ArrayPegawai dbPegawai) {
        int aktifCount = 0;
        System.out.println("\n--- KENDARAAN YANG MASIH TERPARKIR ---");
        for (int i = 0; i < jumlahKendaraan; i++) {
            if (!arr[i].isSudahKeluar()) {
                System.out.println((i + 1) + ". [" + arr[i].getJenisKendaraan() + "] Plat: " + arr[i].getPlat() 
                        + " (Masuk: " + arr[i].getMasuk().toString() + ")");
                aktifCount++;
            }
        }

        if (aktifCount == 0) {
            System.out.println("Tidak ada kendaraan yang sedang terparkir.");
            return;
        }

        int pilih = Util.bacaInt("Pilih nomor kendaraan yang akan keluar: ");
        if (pilih < 1 || pilih > jumlahKendaraan || arr[pilih - 1].isSudahKeluar()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        Kendaraan k = arr[pilih - 1];
        Waktu wKeluar = new Waktu();
        do {
            System.out.println("Waktu Keluar:");
            wKeluar.inputDalam();
            if (!k.setKeluar(wKeluar)) {
                System.out.println("Waktu keluar harus setelah waktu masuk, ulangi!");
            } else {
                break;
            }
        } while (true);

        long totalBiaya = k.hitungBiaya(dbPegawai);
        System.out.println("\nKendaraan berhasil keluar!");
        System.out.println("Total Biaya Parkir : Rp " + Util.rupiah((int)totalBiaya));
    }

    public void tampilkanLaporanParkir(ArrayPegawai dbPegawai) {
        if (jumlahKendaraan == 0) {
            System.out.println("\nBelum ada data parkir.");
            return;
        }

        System.out.println("\n\t\t\t\tLAPORAN DATA PARKIR KENDARAAN");
        System.out.println("=" .repeat(110));
        System.out.printf("%-4s %-10s %-12s %-15s %-15s %-12s %-12s %-12s%n",
                "No", "Jenis", "Plat", "Merk", "Pemilik", "Masuk", "Keluar", "Status");
        System.out.println("=" .repeat(110));

        long totalPendapatan = 0;
        for (int i = 0; i < jumlahKendaraan; i++) {
            String status = arr[i].isSudahKeluar() ? "Keluar" : "Terparkir";
            String jamKeluar = arr[i].isSudahKeluar() ? arr[i].getKeluar().toString() : "---";

            System.out.printf("%-4d %-10s %-12s %-15s %-15s %-12s %-12s %-12s",
                    (i + 1), arr[i].getJenisKendaraan(), arr[i].getPlat(),
                    arr[i].getMerk(), arr[i].getNamaPemilik(),
                    arr[i].getMasuk().toString(), jamKeluar, status);

            if (arr[i].isSudahKeluar()) {
                long biayaItem = arr[i].hitungBiaya(dbPegawai);
                System.out.print(" | Biaya: Rp " + Util.rupiah((int)biayaItem));
                totalPendapatan += biayaItem;
            }
            System.out.println();
        }
        System.out.println("=" .repeat(110));
        System.out.println("Total Pendapatan Parkir : Rp " + Util.rupiah((int)totalPendapatan));
    }
}

// Kelas Menu
class Menu {
    private ArrayPegawai dbPegawai = new ArrayPegawai();
    private Parkir dbParkir = new Parkir();

    public void jalankan() {
        int pilihan;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\n==========================================================");
            System.out.println("            MENU GAJI & PARKIR PT INFORMATIKA             ");
            System.out.println("==========================================================");
            System.out.println("1. Input Data Pegawai");
            System.out.println("2. Tampilkan Daftar Gaji");
            System.out.println("3. Kendaraan Masuk");
            System.out.println("4. Kendaraan Keluar");
            System.out.println("5. Laporan Parkir");
            System.out.println("6. Keluar");
            System.out.println("----------------------------------------------------------");

            pilihan = Util.bacaInt("Pilih menu (1-6): ");
            System.out.println();

            switch (pilihan) {
                case 1:
                    dbPegawai.inputDalam();
                    break;
                case 2:
                    dbPegawai.prosesData();
                    dbPegawai.outputData();
                    break;
                case 3:
                    dbParkir.inputKendaraanMasuk(dbPegawai);
                    break;
                case 4:
                    dbParkir.prosesKendaraanKeluar(dbPegawai);
                    break;
                case 5:
                    dbParkir.tampilkanLaporanParkir(dbPegawai);
                    break;
                case 6:
                    System.out.println("Terima kasih telah menggunakan sistem Gaji & Parkir PT Informatika!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid! Silakan masukkan angka 1 sampai 6.");
            }

            if (pilihan != 6) {
                System.out.print("\nTekan Enter untuk kembali ke menu...");
                scanner.nextLine();
            }

        } while (pilihan != 6);
    }
}

// Main Class
public class PegawaiApp {
    public static void main(String[] args) {
        Menu menuAplikasi = new Menu();
        menuAplikasi.jalankan();
    }
}