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
#include <vector>

using namespace std;

class MyLarik {
private:
    int ukuran;
    vector<int> elemen; //pake std::vector cpp gantiin array dinamis java

public:
    MyLarik() {
        ukuran = 10;
        elemen.resize(ukuran, 0);
    }

    MyLarik(int ukuran) {
        this->ukuran = ukuran;
        elemen.resize(ukuran, 0);
    }

    MyLarik(const vector<int>& data) {
        this->ukuran = data.size();
        this->elemen = data;
    }

    void setUkuran(int ukuran) {
        this->ukuran = ukuran;
        elemen.resize(ukuran, 0);
    }

    void setNilai(int indeks, int isi) {
        elemen[indeks] = isi;
    }

    int getUkuran() {
        return ukuran;
    }

    void inputDalam() {
        for (int i = 0; i < ukuran; i++) {
            cout << "Masukkan nilai ke-" << (i + 1) << " = ";
            cin >> elemen[i];
        }
    }

    int cariJumlah() {
        int jumlah = 0;
        for (int i = 0; i < ukuran; i++) {
            jumlah = jumlah + elemen[i];
        }
        return jumlah;
    }

    float cariRata() {
        return ((float) cariJumlah() / ukuran);
    }

    int nilaiMax() {
        int tertinggi = elemen[0];
        for (int i = 1; i < ukuran; i++) {
            if (elemen[i] > tertinggi) {
                tertinggi = elemen[i];
            }
        }
        return tertinggi;
    }

    int nilaiMin() {
        int terendah = elemen[0];
        for (int i = 1; i < ukuran; i++) {
            if (elemen[i] < terendah) {
                terendah = elemen[i];
            }
        }
        return terendah;
    }

    void bubbleAsc() {
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

    void bubbleDesc() {
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

    void cetakLarik() {
        cout << "Pencetakan Larik\n";
        for (int i = 0; i < ukuran; i++) {
            cout << "Nilai ke-" << (i + 1) << " = " << elemen[i] << "\n";
        }
    }

    void cetakProses() {
        cout << "Jumlah          = " << cariJumlah() << "\n";
        cout << "Rata-rata       = " << cariRata() << "\n";
        cout << "Nilai Tertinggi = " << nilaiMax() << "\n";
        cout << "Nilai Terendah  = " << nilaiMin() << "\n";
    }
};

class MenuLarik {
private:
    MyLarik arrayObj[4]; //array objek statis cpp gantiin alokasi array objek Java

public:
    static int inputInt(string pesan) {
        int hasilInput;
        cout << pesan;
        cin >> hasilInput;
        return hasilInput;
    }

    void deklarasiObjek() {
        //inisialisasi vecto data langsung untuk objek A
        arrayObj[0] = MyLarik(vector<int>{40, 55, 63, 17, 22, 68, 89, 97, 89});

        arrayObj[1].setUkuran(5);
        arrayObj[1].setNilai(0, 12);
        arrayObj[1].setNilai(1, 7);
        arrayObj[1].setNilai(2, 30);
        arrayObj[1].setNilai(3, 4);
        arrayObj[1].setNilai(4, 19);

        cout << "=== Input Larik C (input dalam) ===\n";
        int nC = inputInt("Masukkan ukuran Larik C : ");
        arrayObj[2] = MyLarik(nC);
        arrayObj[2].inputDalam();

        cout << "=== Input Larik D (input luar) ===\n";
        int nD = inputInt("Masukkan ukuran Larik D : ");
        arrayObj[3] = MyLarik(nD);
        for (int i = 0; i < nD; i++) {
            int isi = inputInt("Masukkan nilai ke-" + to_string(i + 1) + " = ");
            arrayObj[3].setNilai(i, isi);
        }
    }

    void tampilMenuUtama() {
        cout << "\n===== MENU UTAMA =====\n";
        cout << "1. Larik A (input constructor)\n";
        cout << "2. Larik B (input setter)\n";
        cout << "3. Larik C (input dalam)\n";
        cout << "4. Larik D (input luar)\n";
        cout << "5. Tampilkan semua larik\n";
        cout << "0. Keluar\n";
    }

    void tampilMenuProses(char nama) {
        cout << "\n--- Larik " << nama << " ---\n";
        cout << "1. Tampilkan larik\n";
        cout << "2. Hitung jumlah, rata-rata, max, min\n";
        cout << "3. Urutkan naik (ascending)\n";
        cout << "4. Urutkan turun (descending)\n";
        cout << "0. Kembali\n";
    }

    void menuProses(int indeks) {
        char nama = (char) ('A' + indeks);
        MyLarik &obj = arrayObj[indeks]; 
        int pilihan;
        do {
            tampilMenuProses(nama);
            pilihan = inputInt("Pilih menu : ");
            cout << "\n";
            switch (pilihan) {
                case 1:
                    obj.cetakLarik();
                    break;
                case 2:
                    obj.cetakProses();
                    break;
                case 3:
                    obj.bubbleAsc();
                    cout << "Larik " << nama << " diurutkan naik.\n";
                    obj.cetakLarik();
                    break;
                case 4:
                    obj.bubbleDesc();
                    cout << "Larik " << nama << " diurutkan turun.\n";
                    obj.cetakLarik();
                    break;
                case 0:
                    break;
                default:
                    cout << "Menu tidak valid.\n";
            }
        } while (pilihan != 0);
    }

    void jalankan() {
        deklarasiObjek();
        int pilihan;
        do {
            tampilMenuUtama();
            pilihan = inputInt("Pilih menu : ");
            if (pilihan >= 1 && pilihan <= 4) {
                menuProses(pilihan - 1);
            } else if (pilihan == 5) {
                for (int i = 0; i < 4; i++) {
                    cout << "\nLarik " << (char) ('A' + i) << "\n";
                    arrayObj[i].cetakLarik();
                }
            } else if (pilihan == 0) {
                cout << "Program selesai.\n";
            } else {
                cout << "Input Menu tidak valid.\n";
            }
        } while (pilihan != 0);
    }
};

int main() {
    MenuLarik menu;
    menu.jalankan();
    return 0;
}