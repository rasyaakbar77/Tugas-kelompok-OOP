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

        // Validasi input interger
        static int bacaInt(std::string pesan) {
            int nilai;
            while (true) {
                std::cout << pesan;
                std::cin >> nilai;
                if (std::cin.fail()) {
                    std::cin.clear(); // mereset error state
                    std::cin.ignore(10000, '\n'); // membuang input invalid
                    std::cout << "Masukkan angka yang valid!\n";
                } else {
                    std::cin.ignore(10000, '\n'); // bersihkan sisa newline
                    break;
                }
            }
            return nilai;
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

        // Getter Jam
        int getJam() {
            return this->jam;
        }

        // Getter Menit
        int getMenit() {
            return this->menit;
        }

        // Getter Detik
        int getDetik() {
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
        void OutputDalam() {
            std::printf("Waktu = %02d:%02d:%02d\n", jam, menit, detik);
        }

        // Method Proses Cara 1: Fungsi Return
        SelisihWaktu hitungSelisihFungsi(SelisihWaktu w2) {
            int totalDetik1 = (this->jam * 3600) + (this->menit * 60) + this->detik;
            int totalDetik2 = (w2.jam * 3600) + (w2.menit * 60) + w2.detik;
            int selisihTotal = std::abs(totalDetik1 - totalDetik2);

            SelisihWaktu hasil;
            hasil.jam = selisihTotal / 3600;
            hasil.menit = (selisihTotal % 3600) / 60;
            hasil.detik = selisihTotal % 60;

            return hasil;
        }

        // Method Proses Cara 2: Void
        void hitungSelisihVoid(SelisihWaktu w1, SelisihWaktu w2) {
            int totalDetik1 = (w1.jam * 3600) + (w1.menit * 60) + w1.detik;
            int totalDetik2 = (w2.jam * 3600) + (w2.menit * 60) + w2.detik;
            int selisihTotal = std::abs(totalDetik1 - totalDetik2);

            this->jam = selisihTotal / 3600;
            this->menit = (selisihTotal % 3600) / 60;
            this->detik = selisihTotal % 60;
        }
};

int main() {
    // 1. Objek 1: Input Setter 
    SelisihWaktu waktu1; 
    waktu1.setJam(2);
    waktu1.setMenit(3);
    waktu1.setDetik(4);

    // 2. Objek 2: Input Constructor Parameter
    SelisihWaktu waktu2(4, 5, 6);

    // 3. Objek 3: Input Fungsi dalam class
    SelisihWaktu waktu3;
    std::cout << "Masukkan Waktu 3 (Input Dalam):\n";
    waktu3.inputDalam();
    
    int pilihan;
    do {
        std::cout << "\n==============================================================\n";
        std::cout << "                    MENU UTAMA SELISIH WAKTU                    \n";
        std::cout << "================================================================\n";
        std::cout << "1. Tampilkan Semua Data Waktu\n";
        std::cout << "2. Ubah Data Waktu \n";
        std::cout << "3. Hitung Selisih Waktu (Fungsi Return)\n";
        std::cout << "4. Hitung Selisih Waktu (Void)\n";
        std::cout << "5. Keluar Program\n";
        std::cout << "================================================================\n";
        
        pilihan = SelisihWaktu::bacaInt("Pilih menu (1-5): ");
        std::cout << "==============================================================\n";

        switch (pilihan) {
            case 1: {
                std::cout << "\n==============================================================\n";
                std::cout << "                       DATA WAKTU SAAT INI                      \n";
                std::cout << "================================================================\n";
                std::cout << "Waktu 1 (Setter)      : "; waktu1.OutputDalam();
                std::cout << "Waktu 2 (Constructor) : "; waktu2.OutputDalam();
                std::cout << "Waktu 3 (Input Dalam) : "; waktu3.OutputDalam();
                break;
            }

            case 2: {
                
                std::cout << "\n==============================================================\n";
                int pilihWaktu = SelisihWaktu::bacaInt("Pilih waktu yang ingin diubah (1/2/3): ");
                std::cout << "\n================================================================\n";
                if (pilihWaktu == 1) {
                    std::cout << "Edit Waktu 1:\n";
                    waktu1.inputDalam();
                } else if (pilihWaktu == 2) {
                    std::cout << "Edit Waktu 2:\n";
                    waktu2.inputDalam();
                } else if (pilihWaktu == 3) {
                    std::cout << "Edit Waktu 3:\n";
                    waktu3.inputDalam();
                } else {
                    std::cout << "Pilihan waktu tidak valid!\n";
                }
                break;
                std::cout << "================================================================\n";
            }
            case 3: {
                std::cout << "\n==============================================================\n";
                std::cout << "                  HITUNG SELISIH (FUNGSI RETURN)                \n";
                std::cout << "==============================================================\n";
                std::cout << "Pilih objek yang akan diselisihkan:\n";
                std::cout << "1. Waktu 1 dan Waktu 2\n2. Waktu 1 dan Waktu 3\n3. Waktu 2 dan Waktu 3\n";
                int subPilih = SelisihWaktu::bacaInt("Pilihan (1-3): ");
                
                SelisihWaktu hasilSelisih;
                if (subPilih == 1) {
                    hasilSelisih = waktu1.hitungSelisihFungsi(waktu2);
                    std::cout << "Selisih Waktu 1 dan Waktu 2 = ";
                } else if (subPilih == 2) {
                    hasilSelisih = waktu1.hitungSelisihFungsi(waktu3);
                    std::cout << "Selisih Waktu 1 dan Waktu 3 = ";
                } else if (subPilih == 3) {
                    hasilSelisih = waktu2.hitungSelisihFungsi(waktu3);
                    std::cout << "Selisih Waktu 2 dan Waktu 3 = ";
                } else {
                    std::cout << "Pilihan tidak valid!\n";
                }
                hasilSelisih.OutputDalam();
                std::cout << "==============================================================\n";
                break;
            }
            case 4: {
                std::cout << "\n==============================================================\n";
                std::cout << "                HITUNG SELISIH (METHOD VOID)                    \n";
                std::cout << "==============================================================\n";
                std::cout << "Pilih objek yang akan diselisihkan:\n";
                std::cout << "1. Waktu 1 dan Waktu 2\n2. Waktu 1 dan Waktu 3\n3. Waktu 2 dan Waktu 3\n";
                int subPilih = SelisihWaktu::bacaInt("Pilihan (1-3): ");
                
                SelisihWaktu hasilVoid;
                if (subPilih == 1) {
                    hasilVoid.hitungSelisihVoid(waktu1, waktu2);
                    std::cout << "Hasil Selisih (diobjek baru via Void) Waktu 1 dan Waktu 2 = ";
                } else if (subPilih == 2) {
                    hasilVoid.hitungSelisihVoid(waktu1, waktu3);
                    std::cout << "Hasil Selisih (diobjek baru via Void) Waktu 1 dan Waktu 3 = ";
                } else if (subPilih == 3) {
                    hasilVoid.hitungSelisihVoid(waktu2, waktu3);
                    std::cout << "Hasil Selisih (diobjek baru via Void) Waktu 2 dan Waktu 3 = ";
                } else {
                    std::cout << "Pilihan tidak valid!\n";
                    break;
                }
                hasilVoid.OutputDalam();
                std::cout << "==============================================================\n";
                break;
            }
            case 5:
                std::cout << "Terima kasih telah menggunakan program ini!\n";
                break;
            default:
                std::cout << "Pilihan tidak valid, silakan coba lagi.\n";
        }
    } while (pilihan != 5);

    return 0;
}
