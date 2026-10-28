/*
Nama Program : Array2d.cpp
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 09-10-2026
Deskripsi Program : Sebuah program untuk menghitung penjumlahan dan perkalian matriks 2 dimensi
                    dengan proses secara void dan fungsi.
*/

#include <iostream>
#include <initializer_list>

class Matriks2d {
    private :
        int baris;
        int kolom;
        int nilai[10][10];

    public :
        Matriks2d() {
            baris = 0;
            kolom = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    nilai[i][j] = 0;
                }
            }
        }

        Matriks2d(int baris, int kolom) {
            this->baris = baris;
            this->kolom = kolom;
            for (int i = 0; i < baris; i++) {
                for (int j = 0; j < kolom; j++) {
                    this->nilai[i][j] = 0;
                }
            }
        }

        void setBaris(int baris) {
            this->baris = baris;
        }

        void setKolom(int kolom) {
            this->kolom = kolom;
        }

        void setNilai(std::initializer_list<std::initializer_list<int>> nilai) {
            int i = 0;
            for (const auto& barisNilai : nilai) {
                if (i >= baris || i >= 10) break;
                int j = 0;
                for (int nilaiItem : barisNilai) {
                    if (j >= kolom || j >= 10) break;
                    this->nilai[i][j++] = nilaiItem;
                }
                ++i;
            }
        }

        int getBaris() {
            return baris;
        }

        int getKolom(){
            return kolom;
        }

        int getNilai(int i, int j) {
            return nilai[i][j];
        }

        // input
        void inputMatriks() {
            do {
                std::cout << "Masukkan jumlah baris matriks (max 10) : ";
                std::cin >> baris;
                if (baris < 1 || baris > 10) {
                    std::cout << "Jumlah baris harus antara 1 dan 10." << std::endl;
                }
            } while (baris < 1 || baris > 10);
            
            do {
                std::cout << "Masukkan jumlah kolom matriks (max 10) : ";
                std::cin >> kolom;
                if (kolom < 1 || kolom > 10) {
                    std::cout << "Jumlah kolom harus antara 1 dan 10." << std::endl;
                }
            } while (kolom < 1 || kolom > 10);

            std::cout << "Masukkan elemen matriks:\n";
            for (int i = 0; i < baris; i++) {
                for (int j = 0; j < kolom; j++) {
                    std::cout << "Nilai [" << i << "][" << j << "] : ";
                    std::cin >> nilai[i][j];
                }
            }
        }

        // Method penjumlahan matriks (void)
        void penjumlahanMatriksVoid(Matriks2d &a, Matriks2d &b) {
            if (a.baris != b.baris || a.kolom != b.kolom) {
                std::cout << "Tidak dapat melakukan penjumlahan karena ordo matriks berbeda." << std::endl;
                return;
            }
            baris = a.baris;
            kolom = a.kolom;
            for (int i = 0; i < baris; i++) {
                for (int j = 0; j < kolom; j++) {
                    this->nilai[i][j] = a.nilai[i][j] + b.nilai[i][j];
                }
            }
        }

        // Method penjumlahan matriks (fungsi)
        Matriks2d penjumlahanMatriksFungsi(Matriks2d &a, Matriks2d &b) {
            if (a.baris != b.baris || a.kolom != b.kolom) {
                std::cout << "Tidak dapat melakukan penjumlahan karena ordo matriks berbeda." << std::endl;
                return Matriks2d();
            }

            Matriks2d hasil(a.baris, a.kolom);
            for (int i = 0; i < a.baris; i++) {
                for (int j = 0; j < a.kolom; j++) {
                    hasil.nilai[i][j] = a.nilai[i][j] + b.nilai[i][j];
                }
            }
            return hasil;
        }

        // Method perkalian matriks (void) 
        void perkalianMatriksVoid(Matriks2d &a, Matriks2d &b) {
            if (a.kolom != b.baris) {
                std::cout << "Tidak dapat melakukan perkalian karena kolom matriks pertama tidak sama dengan baris matriks kedua." << std::endl;
                return;
            }
            baris = a.baris;
            kolom = b.kolom;
            for (int i = 0; i < a.baris; i++) {
                for (int j = 0; j < b.kolom; j++) {
                    this->nilai[i][j] = 0;
                    for (int k = 0; k < a.kolom; k++) {
                        this->nilai[i][j] += a.nilai[i][k] * b.nilai[k][j];
                    }
                }
            }
        }

        // Method perkalian matriks (fungsi)
        Matriks2d perkalianMatriksFungsi(Matriks2d &a, Matriks2d &b) {
            if (a.kolom != b.baris) {
                std::cout << "Tidak dapat melakukan perkalian karena kolom matriks pertama tidak sama dengan baris matriks kedua." << std::endl;
                return Matriks2d();
            }

            Matriks2d hasil(a.baris, b.kolom);
            for (int i = 0; i < a.baris; i++) {
                for (int j = 0; j < b.kolom; j++) {
                    hasil.nilai[i][j] = 0;
                    for (int k = 0; k < a.kolom; k++) {
                        hasil.nilai[i][j] += a.nilai[i][k] * b.nilai[k][j];
                    }
                }
            }
            return hasil;
        }

        // output
        void outputMatriks() {
            std::cout << "Matriks (" << baris << "x" << kolom << "):" << std::endl;
            for (int i = 0; i < baris; i++) {
                for (int j = 0; j < kolom; j++) {
                    std::cout << nilai[i][j] << " ";
                }
                std::cout << std::endl;
            }
        }
};

class Menu {
    private :
        int pilihan;
    public :
        Menu() {
            pilihan = 0;
        }

        void tampilanMenu() {

            Matriks2d matriks1; // setter
            matriks1.setBaris(3);
            matriks1.setKolom(3);
            matriks1.setNilai({{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});

            Matriks2d matriks2(3, 3); // constructor parameter
            matriks2.setNilai({{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}); 

            Matriks2d matriks3; // input dalam
            matriks3.inputMatriks();

            do {
                std::cout << "=========================================" << std::endl;
                std::cout << "           PROGRAM 2D ARRAY              " << std::endl;
                std::cout << "=========================================" << std::endl;
                std::cout << "1. Input Matriks 3" << std::endl;
                std::cout << "2. Lihat Semua Matriks" << std::endl;
                std::cout << "3. Penjumlahan Matriks" << std::endl;
                std::cout << "4. Perkalian Matriks" << std::endl;
                std::cout << "0. Keluar" << std::endl;
                std::cout << "=========================================" << std::endl;
                std::cout << ">> Masukkan Pilihan Menu : ";
                std::cin >> pilihan;

                switch (pilihan) {
                    case 1 : {
                        std::cout << "Input Matriks 3 (via Input Dalam):" << std::endl;
                        matriks3.inputMatriks();
                        break;
                    }
                    case 2 : {
                        std::cout << "Matriks 1 (via Setter):" << std::endl;
                        matriks1.outputMatriks();

                        std::cout << "Matriks 2 (via Constructor Parameter):" << std::endl;
                        matriks2.outputMatriks();

                        std::cout << "Matriks 3 (via Input Dalam):" << std::endl;
                        matriks3.outputMatriks();
                        break;
                    }
                    case 3: {
                        int o1, o2;
                        std::cout << "=========================================\n";
                        std::cout << "        PENJUMLAHAN 2 MATRIKS            \n";
                        std::cout << "=========================================\n";
                        std::cout << "Pilihan Matriks (1: Matriks 1, 2: Matriks 2, 3: Matriks 3)\n";
                        std::cout << "Pilih Matriks pertama : ";
                        std::cin >> o1;
                        std::cout << "Pilih Matriks kedua : ";
                        std::cin >> o2;

                        Matriks2d m1 = (o1 == 1) ? matriks1 : (o1 == 2 ? matriks2 : matriks3);
                        Matriks2d m2 = (o2 == 1) ? matriks1 : (o2 == 2 ? matriks2 : matriks3);

                        if (m1.getBaris() != m2.getBaris() || m1.getKolom() != m2.getKolom()) {
                            std::cout << "Tidak dapat melakukan penjumlahan karena ordo matriks berbeda.\n";
                            break;
                        }

                        Matriks2d hasilPenjumlahanVoid;
                        Matriks2d hasilPenjumlahanFungsi;

                        hasilPenjumlahanVoid.penjumlahanMatriksVoid(m1, m2);
                        hasilPenjumlahanFungsi = hasilPenjumlahanFungsi.penjumlahanMatriksFungsi(m1, m2);

                        std::cout << "Hasil Penjumlahan Matriks (via Void):\n";
                        hasilPenjumlahanVoid.outputMatriks();

                        std::cout << "Hasil Penjumlahan Matriks (via Fungsi):\n";
                        hasilPenjumlahanFungsi.outputMatriks();
                        break;
                    }
                    case 4: {
                        int o1, o2;
                        std::cout << "=========================================\n";
                        std::cout << "         PERKALIAN 2 MATRIKS             \n";
                        std::cout << "=========================================\n";
                        std::cout << "Pilihan Matriks (1: Matriks 1, 2: Matriks 2, 3: Matriks 3)\n";
                        std::cout << "Pilih Matriks pertama : ";
                        std::cin >> o1;
                        std::cout << "Pilih Matriks kedua : ";
                        std::cin >> o2;

                        Matriks2d m1 = (o1 == 1) ? matriks1 : (o1 == 2 ? matriks2 : matriks3);
                        Matriks2d m2 = (o2 == 1) ? matriks1 : (o2 == 2 ? matriks2 : matriks3);

                        if (m1.getKolom() != m2.getBaris()) {
                            std::cout << "Tidak dapat melakukan perkalian karena kolom matriks pertama tidak sama dengan baris matriks kedua.\n";
                            break;
                        }

                        Matriks2d hasilPerkalianVoid;
                        Matriks2d hasilPerkalianFungsi;

                        hasilPerkalianVoid.perkalianMatriksVoid(m1, m2);
                        hasilPerkalianFungsi = hasilPerkalianFungsi.perkalianMatriksFungsi(m1, m2);

                        std::cout << "Hasil Perkalian Matriks (via Void):\n";
                        hasilPerkalianVoid.outputMatriks();

                        std::cout << "Hasil Perkalian Matriks (via Fungsi):\n";
                        hasilPerkalianFungsi.outputMatriks();
                        break;
                    }
                } 
            } while (pilihan != 0);
        }
};  

class Main {
    private:
        Menu menuUtama;

    public:
        void jalankanAplikasi() {
            menuUtama.tampilanMenu();
        }
};

int main() {
    Main programUtama;
    programUtama.jalankanAplikasi();
    return 0;
}