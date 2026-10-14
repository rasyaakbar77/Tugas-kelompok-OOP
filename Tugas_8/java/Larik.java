/*
Nama Program : Larik.java
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 08-10-2026
Deskripsi Program : Sebuah program untuk megimplementasikan array 1 dimensi dengan method untuk mencari :
                    1. Niai Max
                    2. Nilai Min
                    3. Nilai rata rata
                    4. Jumlah setiap elemen array
                    dengan 4 objek dan 3 class (kelas array, kelas menu dan kelas main)
*/
import java.util.Scanner;

class MyLarik {
    // atribut
    private int ukuran;   // ukuran array 
    private int[] elemen = new int[10];  // elemen array

    // constructor
    public MyLarik() {
        ukuran = 10;
        elemen = new int[ukuran];
    }

    public MyLarik(int ukuran) {
        this.ukuran = ukuran;
        elemen = new int[ukuran];
    }

    // constructor parameter
    public MyLarik(int[] data) {
        this.ukuran = data.length;
        elemen = new int[ukuran];
        for (int i = 0; i < ukuran; i++) {
            elemen[i] = data[i];
        }
    }

    // Setter dan getter
    public void setUkuran(int ukuran) {
        this.ukuran = ukuran;
        elemen = new int[ukuran];
    }

    public void setNilai(int indeks, int isi) {
        elemen[indeks] = isi;
    }

    public int getUkuran() {
        return ukuran;
    }

    // Input Dalam
    public void inputDalam() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < ukuran; i++) {
            System.out.print("Masukkan nilai ke-" + (i + 1) + " = ");
            elemen[i] = sc.nextInt();
        }
    }

    // Method Helper untuk mencari Jumlah
    public int cariJumlah() {
        int jumlah = 0;
        for (int i = 0; i < ukuran; i++) {
            jumlah = jumlah + elemen[i];
        }
        return (jumlah);
    }

    // Method mencari rata rata
    public float cariRata() {
        return ((float) cariJumlah() / ukuran);
    }

    // Method mencari nilai tertinggi
    public int nilaiMax() {
        int tertinggi = elemen[0];
        for (int i = 1; i < ukuran; i++) {
            if (elemen[i] > tertinggi) {
                tertinggi = elemen[i];
            }
        }
        return (tertinggi);
    }

    // Methode mencari nilai terendah
    public int nilaiMin() {
        int terendah = elemen[0];
        for (int i = 1; i < ukuran; i++) {
            if (elemen[i] < terendah) {
                terendah = elemen[i];
            }
        }
        return (terendah);
    }

    // Method Sorting Ascending menggunakan Bubble Sort
    public void bubbleAsc() {
        for (int i = 0; i < ukuran - 1; i++) {
            for (int j = 0; j < ukuran - 1 - i; j++) {
                if (elemen[j] > elemen[j + 1]) {
                    int temp = elemen[j];
                    elemen[j] = elemen[j + 1];
                    elemen[j + 1] = temp;
                }
            }
        }
    }

    // Method Sorting Descending menggunakan Bubble Sort 
    public void bubbleDesc() {
        for (int i = 0; i < ukuran - 1; i++) {
            for (int j = 0; j < ukuran - 1 - i; j++) {
                if (elemen[j] < elemen[j + 1]) {
                    int temp = elemen[j];
                    elemen[j] = elemen[j + 1];
                    elemen[j + 1] = temp;
                }
            }
        }
    }

    // Output
    public void cetakLarik() {
        System.out.println("Pencetakan Larik");
        for (int i = 0; i < ukuran; i++) {
            System.out.println("Nilai ke-" + (i + 1) + " = " + elemen[i]);
        }
    }

    public void cetakProses() {
        System.out.println("Jumlah          = " + cariJumlah());
        System.out.println("Rata-rata       = " + cariRata());
        System.out.println("Nilai Tertinggi = " + nilaiMax());
        System.out.println("Nilai Terendah  = " + nilaiMin());
    }
}

class MenuLarik {
    private MyLarik[] array = new MyLarik[4]; // 4 objek: A, B, C, D

    public void deklarasiObjek() {
        // Objek A : input lewat constructor parameter
        array[0] = new MyLarik(new int[]{40, 55, 63, 17, 22, 68, 89, 97, 89});

        // Objek B : input lewat Setter
        array[1] = new MyLarik();
        array[1].setUkuran(5);
        array[1].setNilai(0, 12);
        array[1].setNilai(1, 7);
        array[1].setNilai(2, 30);
        array[1].setNilai(3, 4);
        array[1].setNilai(4, 19);

        // Objek C : input melalui fungsi dalam class
        System.out.println("=== Input Larik C (input dalam) ===");
        int nC = inputInt("Masukkan ukuran Larik C : ");
        array[2] = new MyLarik(nC);
        array[2].inputDalam();

        // Objek D : input melalui fungsi luar class
        System.out.println("=== Input Larik D (input luar) ===");
        int nD = inputInt("Masukkan ukuran Larik D : ");
        array[3] = new MyLarik(nD);
        for (int i = 0; i < nD; i++) {
            int isi = inputInt("Masukkan nilai ke-" + (i + 1) + " = ");
            array[3].setNilai(i, isi);
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
        MyLarik obj = array[indeks];
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
                    obj.bubbleAsc();
                    System.out.println("Larik " + nama + " diurutkan naik.");
                    obj.cetakLarik();
                    break;
                case 4:
                    obj.bubbleDesc();
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
        deklarasiObjek();
        int pilihan;
        do {
            tampilMenuUtama();
            pilihan = inputInt("Pilih menu : ");
            if (pilihan >= 1 && pilihan <= 4) {
                menuProses(pilihan - 1);
            } else if (pilihan == 5) {
                for (int i = 0; i < array.length; i++) {
                    System.out.println();
                    System.out.println("Larik " + (char) ('A' + i));
                    array[i].cetakLarik();
                }
            } else if (pilihan == 0) {
                System.out.println("Program selesai.");
            } else {
                System.out.println("Input Menu tidak valid.");
            }
        } while (pilihan != 0);
    }

    // validasi Input Integer
    static int inputInt(String pesan) {
        Scanner input = new Scanner(System.in);
        System.out.print(pesan);
        int hasilInput = input.nextInt();
        return (hasilInput);
    }
}

// Class Main
public class Larik {
    public static void main(String[] args) {
        MenuLarik menu = new MenuLarik();
        menu.jalankan();
    }
}