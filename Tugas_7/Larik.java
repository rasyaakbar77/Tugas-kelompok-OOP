import java.util.Scanner;

// ================== CLASS 1: DATA & PROSES ==================
class MyLarik {
    // atribut
    private int ukuran;   // ukuran array / larik
    private int[] nilai;  // elemen array

    // constructor
    public MyLarik() {
        ukuran = 10;
        nilai = new int[ukuran];
    }

    public MyLarik(int ukuran) {
        this.ukuran = ukuran;
        nilai = new int[ukuran];
    }

    // constructor hardcode: data langsung dikirim saat objek dibuat
    public MyLarik(int[] data) {
        this.ukuran = data.length;
        nilai = new int[ukuran];
        for (int i = 0; i < ukuran; i++) {
            nilai[i] = data[i];
        }
    }

    // ===== SETTER & GETTER =====
    public void setUkuran(int ukuran) {
        this.ukuran = ukuran;
        nilai = new int[ukuran];
    }

    public void setNilai(int indeks, int isi) {
        nilai[indeks] = isi;
    }

    public int getUkuran() {
        return ukuran;
    }

    // ===== INPUT DALAM (input dilakukan di dalam class) =====
    public void isiLarik() {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < ukuran; i++) {
            System.out.print("Masukkan nilai ke-" + (i + 1) + " = ");
            nilai[i] = input.nextInt();
        }
    }

    // ===== PROSES =====
    public int cariJumlah() {
        int jumlah = 0;
        for (int i = 0; i < ukuran; i++) {
            jumlah = jumlah + nilai[i];
        }
        return (jumlah);
    }

    public float cariRata() {
        return ((float) cariJumlah() / ukuran);
    }

    public int nilaiMax() {
        int tertinggi = nilai[0];
        for (int i = 1; i < ukuran; i++) {
            if (nilai[i] > tertinggi) {
                tertinggi = nilai[i];
            }
        }
        return (tertinggi);
    }

    public int nilaiMin() {
        int terendah = nilai[0];
        for (int i = 1; i < ukuran; i++) {
            if (nilai[i] < terendah) {
                terendah = nilai[i];
            }
        }
        return (terendah);
    }

    // sorting ascending (bubble sort)
    public void urutNaik() {
        for (int i = 0; i < ukuran - 1; i++) {
            for (int j = 0; j < ukuran - 1 - i; j++) {
                if (nilai[j] > nilai[j + 1]) {
                    int temp = nilai[j];
                    nilai[j] = nilai[j + 1];
                    nilai[j + 1] = temp;
                }
            }
        }
    }

    // sorting descending (bubble sort)
    public void urutTurun() {
        for (int i = 0; i < ukuran - 1; i++) {
            for (int j = 0; j < ukuran - 1 - i; j++) {
                if (nilai[j] < nilai[j + 1]) {
                    int temp = nilai[j];
                    nilai[j] = nilai[j + 1];
                    nilai[j + 1] = temp;
                }
            }
        }
    }

    // ===== OUTPUT =====
    public void cetakLarik() {
        System.out.println("Pencetakan Larik");
        for (int i = 0; i < ukuran; i++) {
            System.out.println("Nilai ke-" + (i + 1) + " = " + nilai[i]);
        }
    }

    public void cetakProses() {
        System.out.println("Jumlah          = " + cariJumlah());
        System.out.println("Rata-rata       = " + cariRata());
        System.out.println("Nilai Tertinggi = " + nilaiMax());
        System.out.println("Nilai Terendah  = " + nilaiMin());
    }
}

// ================== CLASS 2: MENU ==================
class MenuLarik {
    private MyLarik[] daftar = new MyLarik[4]; // 4 objek: A, B, C, D

    // siapkan 4 objek dengan 4 cara input yang berbeda
    public void siapkanData() {
        // Objek A : input via CONSTRUCTOR (hardcode)
        daftar[0] = new MyLarik(new int[]{40, 55, 63, 17, 22, 68, 89, 97, 89});

        // Objek B : input via SETTER (hardcode)
        daftar[1] = new MyLarik();
        daftar[1].setUkuran(5);
        daftar[1].setNilai(0, 12);
        daftar[1].setNilai(1, 7);
        daftar[1].setNilai(2, 30);
        daftar[1].setNilai(3, 4);
        daftar[1].setNilai(4, 19);

        // Objek C : input DALAM (Scanner ada di dalam class MyLarik)
        System.out.println("=== Input Larik C (input dalam) ===");
        int nC = inputInt("Masukkan ukuran Larik C : ");
        daftar[2] = new MyLarik(nC);
        daftar[2].isiLarik();

        // Objek D : input LUAR (Scanner ada di luar class MyLarik, nilai dikirim lewat setter)
        System.out.println("=== Input Larik D (input luar) ===");
        int nD = inputInt("Masukkan ukuran Larik D : ");
        daftar[3] = new MyLarik(nD);
        for (int i = 0; i < nD; i++) {
            int isi = inputInt("Masukkan nilai ke-" + (i + 1) + " = ");
            daftar[3].setNilai(i, isi);
        }
    }

    public void tampilMenuUtama() {
        System.out.println();
        System.out.println("===== MENU UTAMA =====");
        System.out.println("1. Larik A (input constructor)");
        System.out.println("2. Larik B (input setter)");
        System.out.println("3. Larik C (input dalam)");
        System.out.println("4. Larik D (input luar)");
        System.out.println("5. Tampilkan semua larik");
        System.out.println("0. Keluar");
    }

    public void tampilMenuProses(char nama) {
        System.out.println();
        System.out.println("--- Larik " + nama + " ---");
        System.out.println("1. Tampilkan larik");
        System.out.println("2. Hitung jumlah, rata-rata, max, min");
        System.out.println("3. Urutkan naik (ascending)");
        System.out.println("4. Urutkan turun (descending)");
        System.out.println("0. Kembali");
    }

    public void menuProses(int indeks) {
        char nama = (char) ('A' + indeks);
        MyLarik obj = daftar[indeks];
        int pilihan;
        do {
            tampilMenuProses(nama);
            pilihan = inputInt("Pilih menu : ");
            System.out.println();
            switch (pilihan) {
                case 1:
                    obj.cetakLarik();
                    break;
                case 2:
                    obj.cetakProses();
                    break;
                case 3:
                    obj.urutNaik();
                    System.out.println("Larik " + nama + " diurutkan naik.");
                    obj.cetakLarik();
                    break;
                case 4:
                    obj.urutTurun();
                    System.out.println("Larik " + nama + " diurutkan turun.");
                    obj.cetakLarik();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Menu tidak valid.");
            }
        } while (pilihan != 0);
    }

    public void jalankan() {
        siapkanData();
        int pilihan;
        do {
            tampilMenuUtama();
            pilihan = inputInt("Pilih menu : ");
            if (pilihan >= 1 && pilihan <= 4) {
                menuProses(pilihan - 1);
            } else if (pilihan == 5) {
                for (int i = 0; i < daftar.length; i++) {
                    System.out.println();
                    System.out.println("Larik " + (char) ('A' + i));
                    daftar[i].cetakLarik();
                }
            } else if (pilihan == 0) {
                System.out.println("Program selesai.");
            } else {
                System.out.println("Menu tidak valid.");
            }
        } while (pilihan != 0);
    }

    static int inputInt(String pesan) {
        Scanner input = new Scanner(System.in);
        System.out.print(pesan);
        int hasilInput = input.nextInt();
        return (hasilInput);
    }
}

// ================== CLASS 3: UTAMA ==================
public class Larik {
    public static void main(String[] args) {
        MenuLarik menu = new MenuLarik();
        menu.jalankan();
    }
}