/*
Nama Program : PegawaiApp.java
Anggota Kelompok - NPM:
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Deskripsi Program : Program gaji karyawan (inheritance Person -> Pegawai) dan parkir kendaraan
                    (inheritance + polimorfisme Kendaraan -> Mobil, Truk, Motor). Data disimpan dalam array.

SPESIFIKASI DAN ATURAN PARKIR
1. Jenis kendaraan : Mobil, Truk, Motor. Tiap jenis meng-override hitungBiaya() (polimorfisme).
2. Tarif umum (per jam) : Mobil 3.000, Truk 5.000, Motor 2.000.
3. Tarif pegawai (per hari, <= 24 jam) : Mobil 6.000, Motor 3.000. Truk milik pegawai tetap tarif umum.
4. Pegawai dikenali dari NIP yang sudah terdaftar. NIP kosong / tidak ditemukan = umum.
5. Tarif per jam : durasi dibulatkan ke atas per jam, minimal 1 jam.
6. Tarif per hari : tiap 24 jam dihitung 1 hari (dibulatkan ke atas), minimal 1 hari.
7. Plat nomor tidak boleh sama dengan kendaraan yang masih terparkir.
8. NIP pegawai harus unik.
9. Waktu keluar harus setelah waktu masuk. Lama menginap (0 - 30 hari) diisi saat keluar.
10. Data yang sudah diinput tidak ditimpa. Input pegawai selalu menambah data di belakang data lama.
11. Kapasitas : 100 pegawai dan 100 catatan parkir. Jika penuh, input ditolak.
12. Biaya baru dihitung dan dikunci saat kendaraan keluar. Pendapatan hanya dari kendaraan yang sudah keluar.
*/

import java.util.Scanner;

class Util {
    static final Scanner sc = new Scanner(System.in);

    static int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid!");
            }
        }
    }

    static String bacaString(String pesan, boolean wajib) {
        while (true) {
            System.out.print(pesan);
            String s = sc.nextLine().trim();
            if (!wajib || !s.isEmpty()) return s;
            System.out.println("Tidak boleh kosong!");
        }
    }

    static String bacaString(String pesan) {
        return bacaString(pesan, true);
    }

    static String kapital(String s) {
        return s.toUpperCase();
    }

    static String rupiah(long nilai) {
        StringBuilder s = new StringBuilder(Long.toString(nilai));
        for (int i = s.length() - 3; i > 0; i -= 3) {
            s.insert(i, ".");
        }
        return s.toString();
    }

    static String pad(String s, int w, char rata) {
        String t = s.length() > w ? s.substring(0, w) : s;
        int sisa = w - t.length();
        if (rata == '<') return t + " ".repeat(sisa);
        if (rata == '>') return " ".repeat(sisa) + t;
        int kiri = sisa / 2;
        return " ".repeat(kiri) + t + " ".repeat(sisa - kiri);
    }

    static String garis(int[] lebar) {
        StringBuilder g = new StringBuilder("+");
        for (int l : lebar) {
            g.append("-".repeat(l + 2)).append("+");
        }
        return g.toString();
    }

    static String baris(String[] cells, int[] lebar, String rata) {
        StringBuilder b = new StringBuilder("|");
        for (int i = 0; i < lebar.length; i++) {
            b.append(" ").append(pad(cells[i], lebar[i], rata.charAt(i))).append(" |");
        }
        return b.toString();
    }
}

class Waktu {
    private int jam;
    private int menit;
    private int detik;

    public Waktu() {
        this(0, 0, 0);
    }

    public Waktu(int jam, int menit, int detik) {
        this.jam = jam;
        this.menit = menit;
        this.detik = detik;
    }

    public void setWaktu(int j, int m, int d) {
        jam = j;
        menit = m;
        detik = d;
    }

    public void setJam(int j) { 
        jam = j; 
    }
    public void setMenit(int m) { 
        menit = m; 
    }
    public void setDetik(int d) { 
        detik = d; 
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
        return new Waktu(selisihTotal / 3600, (selisihTotal % 3600) / 60, selisihTotal % 60);
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
        System.out.println("Waktu = " + toString());
    }
}

class Person {
    protected String nama;
    protected String alamat;

    public Person() {
        this("", "");
    }

    public Person(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
    }

    public void inputDalam() {
        System.out.print("Nama   : ");
        nama = Util.sc.nextLine();
        System.out.print("Alamat : ");
        alamat = Util.sc.nextLine();
    }

    public void setNama(String nama) { 
        this.nama = nama; 
    }
    public void setAlamat(String alamat) { 
        this.alamat = alamat; 
    }
    public String getNama() { 
        return nama; 
    }
    public String getAlamat() { 
        return alamat; 
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
        gol = 1;
        nip = "";
        statusPeringatan = "";
    }

    public Pegawai(String nip, String nama, String alamat, int gol) {
        super(nama, alamat);
        this.nip = nip;
        this.gol = gol;
        this.statusPeringatan = "";
    }

    public void inputPegawai() {
        System.out.print("NIP     : ");
        nip = Util.sc.nextLine().trim();
        super.inputDalam();

        do {
            gol = Util.bacaInt("Masukkan Gol (1-4) : ");
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
        pulang = p; }

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
        lamaKerja = pulang.hitungSelisihFungsi(datang);

        Waktu batas = new Waktu(8, 0, 0);
        if (lamaKerja.totalDetik() >= batas.totalDetik()) {
            jamLembur = new Waktu();
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
    static final String[] HEADER_GAJI = {"No", "Nama", "Alamat", "NIP", "Gol", "Datang", "Pulang",
                                         "Lama", "Jam Lembur", "Gaji Harian", "Lembur", "Total", "Status"};
    static final int[] LEBAR_GAJI = {3, 10, 14, 8, 3, 8, 8, 8, 10, 11, 11, 11, 10};
    static final String RATA_GAJI = "^<<<^^^^^>>>^";

    private static final int MAX = 100;
    private Pegawai[] arr = new Pegawai[MAX];
    private int jumlah;

    public ArrayPegawai() {
        jumlah = 0;
        for (int i = 0; i < MAX; i++) arr[i] = new Pegawai();
    }

    private int cariNip(String nip, int batas) {
        for (int i = 0; i < batas; i++) {
            if (arr[i].getNip().equals(nip)) return i;
        }
        return -1;
    }

    public void setJumlah(int jml) {
        if (jml > MAX || jml < 0) {
            System.out.println("Jumlah Array Tidak Valid.");
        }
        jumlah = (jml > MAX) ? MAX : (jml < 0 ? 0 : jml);
    }

    public int getJumlah() {
        return jumlah;
    }

    //cari index pegawai berdasarkan NIP, -1 kalau tidak ada.
    public int cariNip(String nip) {
        return cariNip(nip, jumlah);
    }

    //data baru ditambahkan di belakang data lama, data lama tidak ditimpa.
    public void inputDalam() {
        int sisa = MAX - jumlah;
        if (sisa <= 0) {
            System.out.println("Data pegawai penuh (maksimal " + MAX + ")!");
            return;
        }

        int tambah;
        do {
            tambah = Util.bacaInt("Masukkan jumlah pegawai yang ditambahkan (1-" + sisa + "): ");
        } while (tambah < 1 || tambah > sisa);

        for (int i = 0; i < tambah; i++) {
            int idx = jumlah;
            System.out.println("\nData Pegawai ke-" + (idx + 1));
            boolean kembar;
            do {
                arr[idx].inputPegawai();
                kembar = (cariNip(arr[idx].getNip(), idx) != -1);
                if (kembar) System.out.println("NIP sudah dipakai pegawai lain, ulangi!");
            } while (kembar);
            jumlah++;
        }
    }

    public void prosesData() {
        for (int i = 0; i < jumlah; i++) {
            arr[i].prosesGaji();
        }
    }

    public void setPegawaiAt(int index, Pegawai p) {
        if (index >= 0 && index < MAX) {
            arr[index] = p;
        }
    }

    public Pegawai getPegawaiAt(int index) {
        if (index >= 0 && index < MAX) {
            return arr[index];
        }
        return new Pegawai();
    }

    public void outputData() {
        String g = Util.garis(LEBAR_GAJI);
        System.out.println("\n" + Util.pad("Daftar Gaji Harian PT Informatika", g.length(), '^'));
        System.out.println(g);
        System.out.println(Util.baris(HEADER_GAJI, LEBAR_GAJI, RATA_GAJI));
        System.out.println(g);

        double totalSemua = 0;
        int maxGaji = (jumlah > 0) ? arr[0].getTotal() : 0;
        int minGaji = (jumlah > 0) ? arr[0].getTotal() : 0;

        for (int i = 0; i < jumlah; i++) {
            System.out.println(Util.baris(arr[i].getBaris(i + 1), LEBAR_GAJI, RATA_GAJI));
            int t = arr[i].getTotal();
            totalSemua += t;
            if (t > maxGaji) maxGaji = t;
            if (t < minGaji) minGaji = t;
        }
        System.out.println(g);

        double rataRata = (jumlah > 0) ? (totalSemua / jumlah) : 0;

        System.out.println("Rata-rata Gaji : " + (jumlah > 0 ? Util.rupiah((long) rataRata) : "---"));
        System.out.println("Gaji Tertinggi : " + (jumlah > 0 ? Util.rupiah(maxGaji) : "---"));
        System.out.println("Gaji Terendah  : " + (jumlah > 0 ? Util.rupiah(minGaji) : "---"));
    }
}

// Superclass Kendaraan (abstract)
abstract class Kendaraan {
    protected String merk;
    protected String plat;
    protected String warna;
    protected String nipPemilik;
    protected String namaPemilik;
    protected Waktu masuk = new Waktu();
    protected Waktu keluar = new Waktu();
    protected int hariInap;
    protected boolean sudahKeluar;

    protected long durasiDetik() {
        return (long) hariInap * 86400L + keluar.totalDetik() - masuk.totalDetik();
    }

    public Kendaraan() {
        this("", "", "");
    }

    public Kendaraan(String merk, String plat, String warna) {
        this.merk = merk;
        this.plat = plat;
        this.warna = warna;
        this.nipPemilik = "";
        this.namaPemilik = "";
        this.hariInap = 0;
        this.sudahKeluar = false;
    }

    public void setMerk(String m) { 
        merk = m; 
    }
    public void setPlat(String p) { 
        plat = p; 
    }
    public void setWarna(String w) { 
        warna = w; 
    }

    public String getMerk() { 
        return merk; 
    }
    public String getPlat() { 
        return plat; 
    }
    public String getWarna() { 
        return warna; 
    }

    public void setPemilik(String nip, String nama) {
        nipPemilik = nip;
        namaPemilik = nama;
    }

    public String getNipPemilik() { 
        return nipPemilik; 
    }
    public String getNamaPemilik() { 
        return namaPemilik; 
    }

    public boolean isPegawai() {
        return !nipPemilik.isEmpty();
    }

    public void setMasuk(Waktu w) { 
        masuk = w; 
    }
    public Waktu getMasuk() { 
        return masuk; 
    }

    //mengembalikan false kalau waktu keluar tidak setelah waktu masuk.
    public boolean setKeluar(Waktu w, int tambahHari) {
        long d = (long) tambahHari * 86400L + w.totalDetik() - masuk.totalDetik();
        if (d <= 0) return false;
        keluar = w;
        hariInap = tambahHari;
        sudahKeluar = true;
        return true;
    }

    public boolean getSudahKeluar() { 
        return sudahKeluar; 
    }

    public Waktu getDurasi() {
        long d = durasiDetik();
        return new Waktu((int) (d / 3600), (int) ((d % 3600) / 60), (int) (d % 60));
    }

    public int jamTerbayar() {
        long j = (durasiDetik() + 3599) / 3600;
        return (int) (j < 1 ? 1 : j);
    }

    public int hariTerbayar() {
        long h = (durasiDetik() + 86399) / 86400;
        return (int) (h < 1 ? 1 : h);
    }

    //true kalau kendaraan ini dikenai tarif per hari
    public boolean perHari() {
        return isPegawai();
    }

    public abstract String getJenis();
    public abstract String getTarif();
    public abstract int hitungBiaya();

    public String getDitagih() {
        if (perHari()) return hariTerbayar() + " hari";
        return jamTerbayar() + " jam";
    }

    public String[] getBaris(int no) {
        String pemilik = isPegawai() ? namaPemilik : "Umum";
        String wKeluar = "-";
        String durasi = "-";
        String ditagih = "-";
        String biaya = "-";
        if (sudahKeluar) {
            wKeluar = keluar.toString() + (hariInap > 0 ? "+" + hariInap + "hr" : "");
            durasi = getDurasi().toString();
            ditagih = getDitagih();
            biaya = Util.rupiah(hitungBiaya());
        }
        return new String[] {
            String.valueOf(no), plat, getJenis(), merk, pemilik,
            masuk.toString(), wKeluar, durasi, ditagih,
            getTarif(), biaya, sudahKeluar ? "Selesai" : "Parkir"
        };
    }
}

class Mobil extends Kendaraan {
    private static final int TARIF_UMUM = 3000;     // per jam
    private static final int TARIF_PEGAWAI = 6000;  // per hari

    public Mobil() {
        super();
    }

    public Mobil(String merk, String plat, String warna) {
        super(merk, plat, warna);
    }

    @Override
    public String getJenis() {
        return "Mobil";
    }

    @Override
    public String getTarif() {
        return perHari() ? Util.rupiah(TARIF_PEGAWAI) + "/hari" : Util.rupiah(TARIF_UMUM) + "/jam";
    }

    @Override
    public int hitungBiaya() {
        return perHari() ? hariTerbayar() * TARIF_PEGAWAI : jamTerbayar() * TARIF_UMUM;
    }
}

class Truk extends Kendaraan {
    private static final int TARIF_UMUM = 5000;     // per jam, pegawai sama

    public Truk() {
        super();
    }

    public Truk(String merk, String plat, String warna) {
        super(merk, plat, warna);
    }

    @Override
    public String getJenis() {
        return "Truk";
    }

    @Override
    public boolean perHari() {
        return false;
    }

    @Override
    public String getTarif() {
        return Util.rupiah(TARIF_UMUM) + "/jam";
    }

    @Override
    public int hitungBiaya() {
        return jamTerbayar() * TARIF_UMUM;
    }
}

class Motor extends Kendaraan {
    private static final int TARIF_UMUM = 2000;     // per jam
    private static final int TARIF_PEGAWAI = 3000;  // per hari

    public Motor() {
        super();
    }

    public Motor(String merk, String plat, String warna) {
        super(merk, plat, warna);
    }

    @Override
    public String getJenis() {
        return "Motor";
    }

    @Override
    public String getTarif() {
        return perHari() ? Util.rupiah(TARIF_PEGAWAI) + "/hari" : Util.rupiah(TARIF_UMUM) + "/jam";
    }

    @Override
    public int hitungBiaya() {
        return perHari() ? hariTerbayar() * TARIF_PEGAWAI : jamTerbayar() * TARIF_UMUM;
    }
}

// Array of Kendaraan
class Parkir {
    static final String[] HEADER_PARKIR = {"No", "Plat", "Jenis", "Merk", "Pemilik", "Masuk",
                                           "Keluar", "Durasi", "Ditagih", "Tarif", "Biaya", "Status"};
    static final int[] LEBAR_PARKIR = {3, 10, 6, 10, 12, 8, 13, 9, 8, 10, 9, 7};
    static final String RATA_PARKIR = "^<<<<^^^^>>^";

    private static final int MAX = 100;
    private Kendaraan[] arr = new Kendaraan[MAX];
    private int jumlah = 0;

    public int getJumlah() {
        return jumlah;
    }

    public int cariAktif(String plat) {
        for (int i = 0; i < jumlah; i++) {
            if (!arr[i].getSudahKeluar() && arr[i].getPlat().equals(plat)) return i;
        }
        return -1;
    }

    public int hitungAktif() {
        int n = 0;
        for (int i = 0; i < jumlah; i++) {
            if (!arr[i].getSudahKeluar()) n++;
        }
        return n;
    }

    //menambah di belakang, data lama tidak ditimpa.
    public void inputMasuk(ArrayPegawai dataPegawai) {
        if (jumlah >= MAX) {
            System.out.println("Data parkir penuh (maksimal " + MAX + ")!");
            return;
        }

        System.out.println("Jenis kendaraan:");
        System.out.println("  1. Mobil\n  2. Truk\n  3. Motor");
        int jenis;
        do {
            jenis = Util.bacaInt("Pilih jenis (1-3): ");
        } while (jenis < 1 || jenis > 3);

        String plat = Util.kapital(Util.bacaString("Plat nomor : "));
        if (cariAktif(plat) != -1) {
            System.out.println("Plat " + plat + " masih terparkir, tidak bisa masuk dua kali!");
            return;
        }
        String merk = Util.bacaString("Merk        : ");
        String warna = Util.bacaString("Warna       : ");

        Kendaraan k;
        switch (jenis) {
            case 1:  k = new Mobil(merk, plat, warna); break;
            case 2:  k = new Truk(merk, plat, warna);  break;
            default: k = new Motor(merk, plat, warna); break;
        }

        String nip = Util.bacaString("NIP pegawai (kosongkan jika umum) : ", false);
        if (!nip.isEmpty()) {
            int idx = dataPegawai.cariNip(nip);
            if (idx >= 0) {
                k.setPemilik(nip, dataPegawai.getPegawaiAt(idx).getNama());
                System.out.println("Pemilik terdaftar sebagai pegawai: " + k.getNamaPemilik());
                if (jenis == 2) System.out.println("Catatan: truk tetap dikenai tarif umum.");
            } else {
                System.out.println("NIP tidak ditemukan, dicatat sebagai umum.");
            }
        }

        Waktu w = new Waktu();
        System.out.println("Waktu Masuk :");
        w.inputDalam();
        k.setMasuk(w);

        arr[jumlah++] = k;
        System.out.println(k.getJenis() + " " + plat + " berhasil dicatat masuk.");
    }

    public void inputKeluar() {
        if (hitungAktif() == 0) {
            System.out.println("Tidak ada kendaraan yang sedang parkir.");
            return;
        }

        String plat = Util.kapital(Util.bacaString("Plat nomor yang keluar : "));
        int idx = cariAktif(plat);
        if (idx == -1) {
            System.out.println("Plat " + plat + " tidak ditemukan di kendaraan yang sedang parkir.");
            return;
        }
        Kendaraan k = arr[idx];

        boolean ok;
        do {
            Waktu w = new Waktu();
            System.out.println("Waktu Keluar :");
            w.inputDalam();
            int hari;
            do {
                hari = Util.bacaInt("Lama menginap (0-30 hari, 0 jika keluar di hari yang sama) : ");
            } while (hari < 0 || hari > 30);
            ok = k.setKeluar(w, hari);
            if (!ok) System.out.println("Waktu keluar harus setelah waktu masuk, ulangi!");
        } while (!ok);

        //hitungBiaya() dipanggil lewat referensi Kendaraan (polimorfisme): versi Mobil/Truk/Motor yang jalan
        System.out.println("\n========== STRUK PARKIR ==========");
        System.out.println("Plat    : " + k.getPlat());
        System.out.println("Jenis   : " + k.getJenis() + " (" + k.getMerk() + ", " + k.getWarna() + ")");
        System.out.println("Pemilik : " + (k.isPegawai() ? k.getNamaPemilik() + " (pegawai)" : "Umum"));
        System.out.println("Masuk   : " + k.getMasuk().toString());
        System.out.println("Durasi  : " + k.getDurasi().toString());
        System.out.println("Ditagih : " + k.getDitagih() + " x " + k.getTarif());
        System.out.println("BIAYA   : Rp " + Util.rupiah(k.hitungBiaya()));
        System.out.println("==================================");
    }

    public void outputLaporan() {
        String g = Util.garis(LEBAR_PARKIR);
        System.out.println("\n" + Util.pad("Laporan Parkir Kendaraan PT Informatika", g.length(), '^'));
        System.out.println(g);
        System.out.println(Util.baris(HEADER_PARKIR, LEBAR_PARKIR, RATA_PARKIR));
        System.out.println(g);

        long totalPendapatan = 0;
        int nMobil = 0, nTruk = 0, nMotor = 0, nSelesai = 0;

        for (int i = 0; i < jumlah; i++) {
            System.out.println(Util.baris(arr[i].getBaris(i + 1), LEBAR_PARKIR, RATA_PARKIR));
            String jenis = arr[i].getJenis();
            if (jenis.equals("Mobil")) nMobil++;
            else if (jenis.equals("Truk")) nTruk++;
            else nMotor++;
            if (arr[i].getSudahKeluar()) {
                nSelesai++;
                totalPendapatan += arr[i].hitungBiaya();
            }
        }
        System.out.println(g);

        System.out.println("Total Kendaraan   : " + jumlah + " (Mobil " + nMobil + ", Truk " + nTruk + ", Motor " + nMotor + ")");
        System.out.println("Sedang Parkir     : " + (jumlah - nSelesai));
        System.out.println("Sudah Keluar      : " + nSelesai);
        System.out.println("Total Pendapatan  : " + Util.rupiah(totalPendapatan));
    }
}

class Menu {
    private ArrayPegawai dataPegawai = new ArrayPegawai();
    private Parkir dataParkir = new Parkir();

    public void tampilkanMenu() {
        System.out.println("\n========================================");
        System.out.println("         MENU GAJI & PARKIR PT INFORMATIKA");
        System.out.println("========================================");
        System.out.println("1. Input Data Pegawai (tambah)");
        System.out.println("2. Tampilkan Daftar Gaji");
        System.out.println("3. Kendaraan Masuk");
        System.out.println("4. Kendaraan Keluar (hitung biaya)");
        System.out.println("5. Laporan Parkir");
        System.out.println("6. Keluar");
        System.out.println("----------------------------------------");
    }

    public void jalankan() {
        int pilihan;
        do {
            tampilkanMenu();
            pilihan = Util.bacaInt("Pilih menu (1-6): ");
            switch (pilihan) {
                case 1:
                    dataPegawai.inputDalam();
                    break;
                case 2:
                    dataPegawai.prosesData();
                    dataPegawai.outputData();
                    break;
                case 3:
                    dataParkir.inputMasuk(dataPegawai);
                    break;
                case 4:
                    dataParkir.inputKeluar();
                    break;
                case 5:
                    dataParkir.outputLaporan();
                    break;
                case 6:
                    System.out.println("Keluar dari program!");
                    break;
                default:
                    System.out.println("Input tidak valid, silahkan coba lagi!");
            }
        } while (pilihan != 6);
    }
}

public class PegawaiApp {
    public static void main(String[] args) {
        Menu pegawaiApp = new Menu();
        pegawaiApp.jalankan();
    }
}