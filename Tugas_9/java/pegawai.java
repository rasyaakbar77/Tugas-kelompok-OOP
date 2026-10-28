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

    public void inputDalam() {
        Scanner sc = new Scanner(System.in);
        do {
            jam = bacaInt(sc, "  Jam (0-23): ");
        } while (jam < 0 || jam > 23);

        do {
            menit = bacaInt(sc, "  Menit (0-59): ");
        } while (menit < 0 || menit > 59);

        do {
            detik = bacaInt(sc, "  Detik (0-59): ");
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
        System.out.print("  Nama: ");
        this.nama = sc.nextLine();
        System.out.print("  Alamat: ");
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
            gol = bacaInt(scanner, "Masukkan Gol (1-4) : ");
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

