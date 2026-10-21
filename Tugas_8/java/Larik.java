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
    private int ukuran;
    private int[] arr = new int[100];

    public MyLarik() {
        ukuran = 0;
        for (int i = 0; i < 100; i++) {
            arr[i] = 0;
        }
    }
    
    public MyLarik(int sizeArr, int[] array) {
        ukuran = (sizeArr > 100) ? 100 : sizeArr;
        for (int i = 0; i < ukuran; i++) {
            arr[i] = array[i];
        }
    }

    public void setUkuran(int sizeArr) {
        if (sizeArr > 100 || sizeArr < 0) {
            System.out.println("Ukuran Array Tidak Valid!.\n");
        } else {
            ukuran = sizeArr;
        }
    }

    public boolean setNilai(int indeks, int nilai) {
        if (indeks >= 0 && indeks < 100) {
            arr[indeks] = nilai;
            return true;
        }
        return false;
    }

    public int getUkuran() {
        return ukuran;
    }

    public int getNilai(int indeks) {
        if (indeks >= 0 && indeks < 100) {
            return arr[indeks];
        } else {
            System.out.println("Indeks " + indeks + " tidak valid/kosong!\n");
            return -1;
        }
    }

    public double averageValue() {
        if (ukuran == 0) return 0;
        double sum = 0;
        for (int i = 0; i < ukuran; i++) {
            sum = sum + arr[i];
        }
        return sum / ukuran;
    }

    public int maxValue() {
        if (ukuran == 0) return 0;
        int max = arr[0];
        for (int i = 0; i < ukuran; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }

    public int minValue() {
        if (ukuran == 0) return 0;
        int min = arr[0];
        for (int i = 0; i < ukuran; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        return min;
    }

    public void ascSort() {
        for (int i = 0; i < ukuran - 1; i++) {
            for (int j = 0; j < ukuran - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public void descSort() {
        for (int i = 0; i < ukuran - 1; i++) {
            for (int j = 0; j < ukuran - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public void inputDalam() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan ukuran array : ");
        ukuran = scanner.nextInt();
        if (ukuran > 100) ukuran = 100;
        if (ukuran < 0) ukuran = 0;
        for (int i = 0; i < ukuran; i++) {
            System.out.print("Masukkan nilai ke-" + (i + 1) + " = ");
            arr[i] = scanner.nextInt();
        }
    }

    public void cetakArray() {
        System.out.println("Print Array:");
        if (ukuran == 0) {
            System.out.println("(Array masih kosong)");
            return;
        }
        for (int i = 0; i < ukuran; i++) {
            System.out.println("Nilai ke-" + (i + 1) + " = " + arr[i]);
        }
    }

    public void cetakProses() { 
        System.out.println("\nArray :");
        cetakArray();
        System.out.println("Rata-rata         = " + averageValue());
        System.out.println("Nilai Tertinggi = " + maxValue());
        System.out.println("Nilai Terendah  = " + minValue());
    }
}

class Menu {
    private MyLarik obj1 = new MyLarik();
    private MyLarik obj2 = new MyLarik();
    private MyLarik obj3 = new MyLarik();
    private MyLarik obj4 = new MyLarik();

    public void tampilkanMenu() {
        Scanner scanner = new Scanner(System.in);
        char pilihan;
        do {
            System.out.println("\n====================================");
            System.out.println("        MENU APLIKASI MYLARIK       ");
            System.out.println("====================================");
            System.out.println("1. Input Data Array");
            System.out.println("2. Tampilkan Array");
            System.out.println("3. Lihat Analisis ");
            System.out.println("4. Sorting Array");
            System.out.println("0. Keluar");
            System.out.println("------------------------------------");
            System.out.print("Pilih menu: ");
            String inputStr = scanner.next();
            pilihan = inputStr.charAt(0);

            switch (pilihan) {
                case '1': {
                    System.out.println("\n====================================");
                    System.out.println("          PILIH OBJEK INPUT         ");
                    System.out.println("====================================");
                    System.out.println("1. Objek 1 (Via Setter)");
                    System.out.println("2. Objek 2 (Via Cons Parameter)");
                    System.out.println("3. Objek 3 (Via Input Dalam Class)");
                    System.out.println("4. Objek 4 (Via Input Luar Class)");
                    System.out.println("0. Kembali");
                    System.out.println("------------------------------------\n");
                    System.out.print("Pilih menu: ");
                    int pilihMenu1 = scanner.nextInt();

                    if (pilihMenu1 == 1) {
                        obj1.setUkuran(3);
                        obj1.setNilai(0, 10);
                        obj1.setNilai(1, 20);
                        obj1.setNilai(2, 30);
                        System.out.println("\n[Sukses] Data Objek 1 berhasil diisi via Setter!\n");
                    } 
                    else if (pilihMenu1 == 2) {
                        int[] dataAwal = {55, 75, 90, 40};
                        obj2 = new MyLarik(4, dataAwal);
                        System.out.println("\n[Sukses] Data Objek 2 berhasil diisi via Constructor Parameter!\n");
                    } 
                    else if (pilihMenu1 == 3) {
                        System.out.println("\n--- Input untuk Objek 3 ---\n");
                        obj3.inputDalam();
                        System.out.println("\n[Sukses] Data Objek 3 berhasil disimpan!\n");
                    } 
                    else if (pilihMenu1 == 4) {
                        int nLuar;
                        int[] arrLuar = new int[100];
                        System.out.println("\n--- Input Luar Class untuk Objek 4 ---\n");
                        System.out.print("Masukkan ukuran array: ");
                        nLuar = scanner.nextInt();
                        for (int i = 0; i < nLuar; i++) {
                            System.out.print("Masukkan nilai ke-" + (i + 1) + " = ");
                            arrLuar[i] = scanner.nextInt();
                        }
                        obj4 = new MyLarik(nLuar, arrLuar);
                        System.out.println("\n[Sukses] Data Objek 4 berhasil disimpan!\n");
                    }
                    break;
                }
                case '2': {
                    System.out.println("\n====================================");
                    System.out.println("         PILIH OBJEK OUTPUT         ");
                    System.out.println("====================================");
                    System.out.println("1. Objek 1 ");
                    System.out.println("2. Objek 2 ");
                    System.out.println("3. Objek 3 ");
                    System.out.println("4. Objek 4 ");
                    System.out.println("0. Kembali");
                    System.out.println("------------------------------------");
                    System.out.print("Pilih menu: ");
                    int pilihMenu2 = scanner.nextInt();

                    if (pilihMenu2 == 1) obj1.cetakArray();
                    else if (pilihMenu2 == 2) obj2.cetakArray();
                    else if (pilihMenu2 == 3) obj3.cetakArray();
                    else if (pilihMenu2 == 4) obj4.cetakArray();
                    break;
                }
                case '3': {
                    System.out.println("\n====================================");
                    System.out.println("         PILIH OBJEK ANALISIS       ");
                    System.out.println("====================================");
                    System.out.println("1. Objek 1 "); 
                    System.out.println("2. Objek 2 ");
                    System.out.println("3. Objek 3 ");
                    System.out.println("4. Objek 4 ");
                    System.out.println("0. Kembali");
                    System.out.println("------------------------------------");
                    System.out.print("Pilih menu: ");
                    int pilihMenu3 = scanner.nextInt();

                    if (pilihMenu3 == 1) obj1.cetakProses();
                    else if (pilihMenu3 == 2) obj2.cetakProses();
                    else if (pilihMenu3 == 3) obj3.cetakProses();
                    else if (pilihMenu3 == 4) obj4.cetakProses();
                    break;
                }
                case '4': {
                    System.out.println("\n====================================");
                    System.out.println("          PILIH OBJEK SORT          ");
                    System.out.println("====================================");
                    System.out.println("1. Objek 1 ");
                    System.out.println("2. Objek 2 ");
                    System.out.println("3. Objek 3 ");
                    System.out.println("4. Objek 4 ");
                    System.out.println("0. Kembali");
                    System.out.println("------------------------------------");
                    System.out.print("Pilih menu: ");
                    int pilihMenu4 = scanner.nextInt();

                    if (pilihMenu4 >= 1 && pilihMenu4 <= 4) {
                        System.out.println("Pilih Jenis Sorting:\n");
                        System.out.println("1. Ascending (Kecil ke Besar)\n");
                        System.out.println("2. Descending (Besar ke Kecil)\n");
                        System.out.print("Pilihan: ");
                        int jenisSort = scanner.nextInt();

                        if (pilihMenu4 == 1) {
                            if (jenisSort == 1) obj1.ascSort();
                            else obj1.descSort();
                            System.out.println("\nHasil setelah sorting:\n");
                            obj1.cetakArray();
                        } else if (pilihMenu4 == 2) {
                            if (jenisSort == 1) obj2.ascSort();
                            else obj2.descSort();
                            System.out.println("\nHasil setelah sorting:\n");
                            obj2.cetakArray();
                        } else if (pilihMenu4 == 3) {
                            if (jenisSort == 1) obj3.ascSort();
                            else obj3.descSort();
                            System.out.println("\nHasil setelah sorting:\n");
                            obj3.cetakArray();
                        } else if (pilihMenu4 == 4) {
                            if (jenisSort == 1) obj4.ascSort();
                            else obj4.descSort();
                            System.out.println("\nHasil setelah sorting:\n");
                            obj4.cetakArray();
                        }
                    }
                    break;
                }
                case '0':
                    System.out.println("\nTerima kasih telah menggunakan program ini!\n");
                    break;
                default:
                    System.out.println("\nPilihan tidak valid! Silakan masukkan angka yang benar.\n");
            }
        } while (pilihan != '0');
    }
}

class MainApp {
    private Menu menuUtama = new Menu(); 

    public void jalankanAplikasi() {
        menuUtama.tampilkanMenu();
    }
}

public class Larik {
    public static void main(String[] args) {
        MainApp programUtama = new MainApp();
        programUtama.jalankanAplikasi();
    }
}