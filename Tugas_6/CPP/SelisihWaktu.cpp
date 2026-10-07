/*
Nama Program : Main.cpp
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 29-09-2026
Deskripsi Program : Sebuah program untuk mencari selisih antara waktu datang dan waktu keluar dengan metode OOP, 
                    menggunakan class SelisihWaktu dan class Menu di dalam satu file.
*/

#include <iostream>
#include <cmath>
#include <iomanip>
#include <limits>

using namespace std;

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

    // Validasi input integer
    static int bacaInt(const string& pesan) {
        int nilai;
        while (true) {
            cout << pesan;
            if (cin >> nilai) {
                cin.ignore(numeric_limits<streamsize>::max(), '\n');
                break;
            } else {
                cout << "Masukkan angka yang valid!\n";
                cin.clear();
                cin.ignore(numeric_limits<streamsize>::max(), '\n');
            }
        }
        return nilai;
    }

    // Setter
    void setJam(int jam) { 
        this->jam = jam; 
    }
    void setMenit(int menit) { 
        this->menit = menit; 
    }
    void setDetik(int detik) { 
        this->detik = detik; 
    }

    // Getter
    int getJam() const { 
        return this->jam; 
    }
    int getMenit() const { 
        return this->menit; 
    }
    int getDetik() const { 
        return this->detik; 
    }

    // Input Dalam
    void inputDalam() {
        do {
            jam = bacaInt("   Jam   (0-23) : ");
        } while (jam < 0 || jam > 23);

        do {
            menit = bacaInt("   Menit (0-59) : ");
        } while (menit < 0 || menit > 59);

        do {
            detik = bacaInt("   Detik (0-59) : ");
        } while (detik < 0 || detik > 59);
    }

    // Output Dalam
    void outputDalam() const {
        cout << "Waktu = " 
             << setfill('0') << setw(2) << jam << ":"
             << setfill('0') << setw(2) << menit << ":"
             << setfill('0') << setw(2) << detik << "\n";
    }

    // Method Proses Cara 1: Fungsi Return
    SelisihWaktu hitungSelisihFungsi(const SelisihWaktu& w2) const {
        int totalDetik1 = (this->jam * 3600) + (this->menit * 60) + this->detik;
        int totalDetik2 = (w2.jam * 3600) + (w2.menit * 60) + w2.detik;
        int selisihTotal = abs(totalDetik1 - totalDetik2);

        SelisihWaktu hasil;
        hasil.jam = selisihTotal / 3600;
        hasil.menit = (selisihTotal % 3600) / 60;
        hasil.detik = selisihTotal % 60;

        return hasil;
    }

    // Method Proses Cara 2: Void
    void hitungSelisihVoid(const SelisihWaktu& w1, const SelisihWaktu& w2) {
        int totalDetik1 = (w1.jam * 3600) + (w1.menit * 60) + w1.detik;
        int totalDetik2 = (w2.jam * 3600) + (w2.menit * 60) + w2.detik;
        int selisihTotal = abs(totalDetik1 - totalDetik2);

        this->jam = selisihTotal / 3600;
        this->menit = (selisihTotal % 3600) / 60;
        this->detik = selisihTotal % 60;
    }
};

class Menu {
private:
    int pilihan;

public:
    void jalankanMenu() {
        // 1. Objek 1: Input Setter 
        SelisihWaktu waktu1; 
        waktu1.setJam(2);
        waktu1.setMenit(3);
        waktu1.setDetik(4);

        // 2. Objek 2: Input Constructor Parameter
        SelisihWaktu waktu2(4, 5, 6);

        // 3. Objek 3: Input Fungsi dalam class
        SelisihWaktu waktu3;
        cout << "Masukkan Waktu 3 (Input Dalam):\n";
        waktu3.inputDalam();

        do {
            cout << "\n===============================================================\n";
            cout << "                    MENU UTAMA SELISIH WAKTU                   \n";
            cout << "===============================================================\n";
            cout << "1. Tampilkan Semua Data Waktu\n";
            cout << "2. Ubah Data Waktu\n";
            cout << "3. Hitung Selisih Waktu (Fungsi Return)\n";
            cout << "4. Hitung Selisih Waktu (Void)\n";
            cout << "5. Keluar Program\n";
            cout << "===============================================================\n";
            
            pilihan = SelisihWaktu::bacaInt("Pilih menu (1-5): ");
            cout << "===============================================================\n";

            switch (pilihan) {
                case 1: {
                    cout << "\n==============================================================\n";
                    cout << "                       DATA WAKTU SAAT INI                    \n";
                    cout << "==============================================================\n";
                    cout << "Waktu 1 (Setter)      : "; waktu1.outputDalam();
                    cout << "Waktu 2 (Constructor) : "; waktu2.outputDalam();
                    cout << "Waktu 3 (Input Dalam) : "; waktu3.outputDalam();
                    break;
                }
                case 2: {
                    cout << "\n==============================================================\n";
                    int pilihWaktu = SelisihWaktu::bacaInt("Pilih waktu yang ingin diubah (1/2/3): ");
                    cout << "==============================================================\n";
                    if (pilihWaktu == 1) {
                        cout << "Edit Waktu 1:\n";
                        waktu1.inputDalam();
                    } else if (pilihWaktu == 2) {
                        cout << "Edit Waktu 2:\n";
                        waktu2.inputDalam();
                    } else if (pilihWaktu == 3) {
                        cout << "Edit Waktu 3:\n";
                        waktu3.inputDalam();
                    } else {
                        cout << "Pilihan waktu tidak valid!\n";
                    }
                    cout << "==============================================================\n\n";
                    break;
                }
                case 3: {
                    cout << "\n==============================================================\n";
                    cout << " HITUNG SELISIH (FUNGSI RETURN) \n";
                    cout << "Pilih objek yang akan diselisihkan:\n";
                    cout << "1. Waktu 1 dan Waktu 2\n2. Waktu 1 dan Waktu 3\n3. Waktu 2 dan Waktu 3\n";
                    cout << "==============================================================\n\n";
                    int subPilih = SelisihWaktu::bacaInt("Pilihan (1-3): ");
                    
                    SelisihWaktu hasilSelisih;
                    if (subPilih == 1) {
                        hasilSelisih = waktu1.hitungSelisihFungsi(waktu2);
                        cout << "Selisih Waktu 1 dan Waktu 2 = ";
                    } else if (subPilih == 2) {
                        hasilSelisih = waktu1.hitungSelisihFungsi(waktu3);
                        cout << "Selisih Waktu 1 dan Waktu 3 = ";
                    } else if (subPilih == 3) {
                        hasilSelisih = waktu2.hitungSelisihFungsi(waktu3);
                        cout << "Selisih Waktu 2 dan Waktu 3 = ";
                    } else {
                        cout << "Pilihan tidak valid!\n\n";
                        cout << "==============================================================\n\n";
                        break;
                    }
                    hasilSelisih.outputDalam();
                    cout << "==============================================================\n\n";
                    break;
                }
                case 4: {
                    cout << "\n==============================================================\n";
                    cout << "                HITUNG SELISIH (METHOD VOID)                  \n";
                    cout << "==============================================================\n";
                    cout << "Pilih objek yang akan diselisihkan:\n";
                    cout << "1. Waktu 1 & Waktu 2\n2. Waktu 1 & Waktu 3\n3. Waktu 2 & Waktu 3\n";
                    cout << "==============================================================\n\n";
                    int subPilih = SelisihWaktu::bacaInt("Pilihan (1-3): ");
                    
                    SelisihWaktu hasilVoid;
                    if (subPilih == 1) {
                        hasilVoid.hitungSelisihVoid(waktu1, waktu2);
                        cout << "Hasil Selisih (diobjek baru via Void) Waktu 1 & 2 = ";
                    } else if (subPilih == 2) {
                        hasilVoid.hitungSelisihVoid(waktu1, waktu3);
                        cout << "Hasil Selisih (diobjek baru via Void) Waktu 1 & 3 = ";
                    } else if (subPilih == 3) {
                        hasilVoid.hitungSelisihVoid(waktu2, waktu3);
                        cout << "Hasil Selisih (diobjek baru via Void) Waktu 2 & 3 = ";
                    } else {
                        cout << "Pilihan tidak valid!\n\n";
                        cout << "==============================================================\n\n";
                        break;
                    }
                    hasilVoid.outputDalam();
                    cout << "==============================================================\n\n";
                    break;
                }
                case 5:
                    cout << "Terima kasih telah menggunakan program ini!\n\n";
                    break;
                default:
                    cout << "Pilihan tidak valid, silakan coba lagi.\n\n";
            }
        } while (pilihan != 5);
    }
};

int main() {
    Menu menu;
    menu.jalankanMenu();
    return 0;
}