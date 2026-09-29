/*
Nama Program : SelisihWaktu.cpp
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 29-09-2026
Deskripsi Program : Sebuah program untuk mencari selisih antara waktu datang dan waktu keluar dengan metode OOP, 
                    menggunakan 3 objek denga 3 cara input berbeda da 2 method proses (selisih waktu) dengan 
                    rerturn value yang berbeda (fungsi dan void).
*/

#include <iostream>
#include <cmath> 

class SelisihWaktu {
    private:
        int jam;
        int menit;
        int detik;

    public:
        // Constructor Default
        SelisihWaktu() {
            this->jam = 0;
            this->menit = 0;
            this->detik = 0;
        }

        // Constructor Parameter
        SelisihWaktu(int jam, int menit, int detik) {
            this->jam = jam;
            this->menit = menit;
            this->detik = detik;
        }

        // Setter Jam
        void setJam(int jam) {
            this->jam = jam;
        }

        // Setter Menit
        void setMenit(int menit) {
            this->menit = menit;
        }

        // Setter Detik
        void setDetik(int detik) {
            this->detik = detik;
        }

        // Input Dalam
        void inputDalam() {
            std::cout << "Masukkan Waktu : " << std::endl;
            std::cout << "Jam   : "; std::cin >> this->jam;
            std::cout << "Menit : "; std::cin >> this->menit;
            std::cout << "Detik : "; std::cin >> this->detik;
        }

        // Output Dalam
        void OutputDalam() {
            std::cout << jam << " jam, " << menit << " menit, " << detik << " detik" << std::endl;
        }

        // Method Proses Cara 1: Fungsi Return
        SelisihWaktu hitungSelisihReturn(SelisihWaktu w2) {
            int totalDetik1 = (this->jam * 3600) + (this->menit * 60) + this->detik;
            int totalDetik2 = (w2.jam * 3600) + (w2.menit * 60) + w2.detik;
            int selisihTotal = std::abs(totalDetik1 - totalDetik2);

            SelisihWaktu hasil;
            hasil.jam = selisihTotal / 3600;
            selisihTotal %= 3600;
            hasil.menit = selisihTotal / 60;
            hasil.detik = selisihTotal % 60;

            return hasil;
        }

        // Method Proses Cara 2: Void
        void hitungSelisihVoid(SelisihWaktu w1, SelisihWaktu w2) {
            int totalDetik1 = (w1.jam * 3600) + (w1.menit * 60) + w1.detik;
            int totalDetik2 = (w2.jam * 3600) + (w2.menit * 60) + w2.detik;
            int selisihTotal = std::abs(totalDetik1 - totalDetik2);

            this->jam = selisihTotal / 3600;
            selisihTotal %= 3600;
            this->menit = selisihTotal / 60;
            this->detik = selisihTotal % 60;
        }
};

int main() {
    // 1. Objek 1: Input Menggunakan Setter 
    SelisihWaktu waktu1;
    waktu1.setJam(8);
    waktu1.setMenit(30);
    waktu1.setDetik(0);

    // 2. Objek 2: Input Menggunakan Constructor Parameter
    SelisihWaktu waktu2(10, 15, 45);

    // 3. Objek 3: Input Menggunakan fungsi Input di Dalam Class
    SelisihWaktu waktu3;
    waktu3.inputDalam();

    std::cout << "\n--- Data Waktu ---" << std::endl;
    std::cout << "Waktu 1 (Setter): "; waktu1.OutputDalam();
    std::cout << "Waktu 2 (Constructor): "; waktu2.OutputDalam();
    std::cout << "Waktu 3 (Input Dalam): "; waktu3.OutputDalam();

    // Contoh Penggunaan Method Proses : Fungsi Return
    SelisihWaktu selisih;
    selisih = waktu1.hitungSelisihReturn(waktu2);
    std::cout << "\nSelisih Waktu 1 dan Waktu 2 (Cara 1 - Return): ";
    selisih.OutputDalam();

    // Contoh Penggunaan Method Proses : Fungsi Void
    SelisihWaktu selisihVoid;
    selisihVoid.hitungSelisihVoid(waktu2, waktu3);
    std::cout << "Selisih Waktu 2 dan Waktu 3 (Cara 2 - Void): ";
    selisihVoid.OutputDalam();

    return 0;
}