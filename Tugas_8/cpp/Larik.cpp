/*
Nama Program : Larik.cpp
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

#include <iostream>

class MyLarik {
    private:
        int ukuran;
        int arr[100];

    public:
        //cons default
        MyLarik() : ukuran(0) {
            for (int i = 0; i < 100; i++) {
                arr[i] = 0;
            }
        }
        
        //cons parameter
        MyLarik(int sizeArr, int array[]) {
            ukuran = (sizeArr > 100) ? 100 : sizeArr;
            for (int i = 0; i < ukuran; i++) {
                arr[i] = array[i];
            }
        }

        //setter
        void setUkuran(int sizeArr) {
            if (sizeArr > 100 || sizeArr < 0) {
                std::cout << "Ukuran Array Tidak Valid!.\n";
            }
            else {
                ukuran = sizeArr;
            }
        }

        bool setNilai(int indeks, int nilai) {
            if (indeks >= 0 && indeks < 100) {
                arr[indeks] = nilai;
                return true;  // Berhasil
            }
            return false; // Gagal 
        }

        //getter
        int getUkuran() {
            return ukuran;
        }

        int getNilai(int indeks) {
            if (indeks >= 0 && indeks < 100) {
                return arr[indeks] ;
            }
            else {
                std::cout << "Indeks " << indeks << " tidak valid/kosong!\n";
                return -1;
            }
        }

        // method rata rata 
        double averageValue() {
            if (ukuran == 0) return 0;
            double sum = 0;
            for (int i = 0; i < ukuran; i++) {
                sum = sum + arr[i];
            }
            return sum / ukuran;
        }

        // method max value
        int maxValue() {
            if (ukuran == 0) return 0;
            int max = arr[0];
            for (int i = 0; i < ukuran; i++) {
                if (max < arr[i]) {
                    max = arr[i];
                }
            }
            return max;
        }

        // method min value
        int minValue() {
            if (ukuran == 0) return 0;
            int min = arr[0];
            for (int i = 0; i < ukuran; i++) {
                if (min > arr[i]) {
                    min = arr[i];
                }
            }
            return min;
        }

        // Sorting Ascending 
        void ascSort() {
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

        // Sorting Descending
        void descSort() {
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

        // input dalam 
        void inputDalam() {
            std::cout << "Masukkan ukuran array : "; 
            std::cin >> ukuran;
            if (ukuran > 100) ukuran = 100;
            if (ukuran < 0) ukuran = 0;
            for (int i = 0; i < ukuran; i++) {
                std::cout << "Masukkan nilai ke-" << (i + 1) << " = ";
                std::cin >> arr[i];
            }
        }

        // print array
        void cetakArray() {
            std::cout << "Print Array:\n";
            if (ukuran == 0) {
                std::cout << "(Array masih kosong)\n";
                return;
            }
            for (int i = 0; i < ukuran; i++) {
                std::cout << "Nilai ke-" << (i + 1) << " = " << arr[i] << "\n";
            }
        }

        // print proses
        void cetakProses() { 
            std::cout << "\nArray :\n";
            cetakArray();
            std::cout << "Rata-rata         = " << averageValue() << "\n";
            std::cout << "Nilai Tertinggi = " << maxValue() << "\n";
            std::cout << "Nilai Terendah  = " << minValue() << "\n";
        }
};

class Menu {
    private:
        MyLarik obj1, obj2, obj3, obj4;

    public:
        void tampilkanMenu() {
            char pilihan;
            do {
                std::cout << "\n====================================\n";
                std::cout << "        MENU APLIKASI MYLARIK       \n";
                std::cout << "====================================\n";
                std::cout << "1. Input Data Array\n";
                std::cout << "2. Tampilkan Array\n";
                std::cout << "3. Lihat Analisis \n";
                std::cout << "4. Sorting Array\n";
                std::cout << "0. Keluar\n";
                std::cout << "------------------------------------\n";
                std::cout << "Pilih menu: ";
                std::cin >> pilihan;

                switch (pilihan) {
                    case '1': { // MENU INPUT
                        int pilihMenu1;
                        std::cout << "\n====================================\n";
                        std::cout << "          PILIH OBJEK INPUT         \n";
                        std::cout << "====================================\n";
                        std::cout << "1. Objek 1 (Via Setter)\n";
                        std::cout << "2. Objek 2 (Via Cons Parameter)\n";
                        std::cout << "3. Objek 3 (Via Input Dalam Class)\n";
                        std::cout << "4. Objek 4 (Via Input Luar Class)\n";
                        std::cout << "0. Kembali\n";
                        std::cout << "------------------------------------\n";
                        std::cout << "Pilih menu: ";
                        std::cin >> pilihMenu1;

                        if (pilihMenu1 == 1) {
                            // Objek 1 input lewat Setter
                            obj1.setUkuran(3);
                            obj1.setNilai(0, 10);
                            obj1.setNilai(1, 20);
                            obj1.setNilai(2, 30);
                            std::cout << "\n[Sukses] Data Objek 1 berhasil diisi via Setter!\n";
                        } 
                        else if (pilihMenu1 == 2) {
                            // Objek 2 input lewat Constructor Parameter
                            int dataAwal[] = {55, 75, 90, 40};
                            obj2 = MyLarik(4, dataAwal);
                            std::cout << "\n[Sukses] Data Objek 2 berhasil diisi via Constructor Parameter!\n";
                        } 
                        else if (pilihMenu1 == 3) {
                            // Objek 3 lewat Method Input Dalam Class
                            std::cout << "\n--- Input untuk Objek 3 ---\n";
                            obj3.inputDalam();
                            std::cout << "\n[Sukses] Data Objek 3 berhasil disimpan!\n";
                        } 
                        else if (pilihMenu1 == 4) {
                            // Objek 4 lewat Input dari Luar Class
                            int nLuar;
                            int arrLuar[100];
                            std::cout << "\n--- Input Luar Class untuk Objek 4 ---\n";
                            std::cout << "Masukkan ukuran array: ";
                            std::cin >> nLuar;
                            for (int i = 0; i < nLuar; i++) {
                                std::cout << "Masukkan nilai ke-" << (i + 1) << " = ";
                                std::cin >> arrLuar[i];
                            }
                            obj4 = MyLarik(nLuar, arrLuar);
                            std::cout << "\n[Sukses] Data Objek 4 berhasil disimpan!\n";
                        }
                        break;
                    }
                    case '2': { // MENU OUTPUT 
                        int pilihMenu2;
                        std::cout << "\n====================================\n";
                        std::cout << "         PILIH OBJEK OUTPUT         \n";
                        std::cout << "====================================\n";
                        std::cout << "1. Objek 1\n";
                        std::cout << "2. Objek 2\n";
                        std::cout << "3. Objek 3\n";
                        std::cout << "4. Objek 4\n";
                        std::cout << "0. Kembali\n";
                        std::cout << "------------------------------------\n";
                        std::cout << "Pilih menu: ";
                        std::cin >> pilihMenu2;

                        if (pilihMenu2 == 1) obj1.cetakArray();
                        else if (pilihMenu2 == 2) obj2.cetakArray();
                        else if (pilihMenu2 == 3) obj3.cetakArray();
                        else if (pilihMenu2 == 4) obj4.cetakArray();
                        break;
                    }
                    case '3': { // MENU ANALISIS
                        int pilihMenu3;
                        std::cout << "\n====================================\n";
                        std::cout << "         PILIH OBJEK ANALISIS       \n";
                        std::cout << "====================================\n";
                        std::cout << "1. Objek 1\n";
                        std::cout << "2. Objek 2\n";
                        std::cout << "3. Objek 3\n";
                        std::cout << "4. Objek 4\n";
                        std::cout << "0. Kembali\n";
                        std::cout << "------------------------------------\n";
                        std::cout << "Pilih menu: ";
                        std::cin >> pilihMenu3;

                        if (pilihMenu3 == 1) obj1.cetakProses();
                        else if (pilihMenu3 == 2) obj2.cetakProses();
                        else if (pilihMenu3 == 3) obj3.cetakProses();
                        else if (pilihMenu3 == 4) obj4.cetakProses();
                        break;
                    }
                    case '4': { // MENU SORTING
                        int pilihMenu4, jenisSort;
                        std::cout << "\n====================================\n";
                        std::cout << "          PILIH OBJEK SORT          \n";
                        std::cout << "====================================\n";
                        std::cout << "1. Objek 1\n";
                        std::cout << "2. Objek 2\n";
                        std::cout << "3. Objek 3\n";
                        std::cout << "4. Objek 4\n";
                        std::cout << "0. Kembali\n";
                        std::cout << "------------------------------------\n";
                        std::cout << "Pilih menu: ";
                        std::cin >> pilihMenu4;

                        if (pilihMenu4 >= 1 && pilihMenu4 <= 4) {
                            std::cout << "Pilih Jenis Sorting:\n";
                            std::cout << "1. Ascending (Kecil ke Besar)\n";
                            std::cout << "2. Descending (Besar ke Kecil)\n";
                            std::cout << "Pilihan: ";
                            std::cin >> jenisSort;

                            if (pilihMenu4 == 1) {
                                if (jenisSort == 1) obj1.ascSort();
                                else obj1.descSort();
                                std::cout << "\nHasil setelah sorting:\n";
                                obj1.cetakArray();
                            } else if (pilihMenu4 == 2) {
                                if (jenisSort == 1) obj2.ascSort();
                                else obj2.descSort();
                                std::cout << "\nHasil setelah sorting:\n";
                                obj2.cetakArray();
                            } else if (pilihMenu4 == 3) {
                                if (jenisSort == 1) obj3.ascSort();
                                else obj3.descSort();
                                std::cout << "\nHasil setelah sorting:\n";
                                obj3.cetakArray();
                            } else if (pilihMenu4 == 4) {
                                if (jenisSort == 1) obj4.ascSort();
                                else obj4.descSort();
                                std::cout << "\nHasil setelah sorting:\n";
                                obj4.cetakArray();
                            }
                        }
                        break;
                    }
                    case '0':
                        std::cout << "\nTerima kasih telah menggunakan program ini!\n";
                        break;
                    default:
                        std::cout << "\nPilihan tidak valid! Silakan masukkan angka yang benar.\n";
                }
            } while (pilihan != '0');
        }
}; 

class Main {
    private:
        Menu menuUtama; 

    public:
        void jalankanAplikasi() {
            menuUtama.tampilkanMenu();
        }
};

int main() {
    Main programUtama;
    programUtama.jalankanAplikasi();

    return 0;
}