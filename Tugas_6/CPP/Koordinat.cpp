/*
Nama Program : KoordinatApp.cpp
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 29-09-2026
Deskripsi Program : Sebuah program untuk melakukan operasi perhitungan koordinat kartesius berupa 
                    1. mencari titik tengah
                    2. mencari jarak antara 2 titik
                    3. hasil pencerminan terhadap sumbu x atau sumbu y
                    dengan input dalam, input luar, output dalam dan output luar berbasis OOP.
*/

#include <iostream>
#include <cmath>
#include <string>
#include <limits>
#include <iomanip>
#include <sstream>

using namespace std;

class Koordinat {
private:
    double absis;
    double ordinat;

public:
    // Default Constructor
    Koordinat() {
        absis = 0;
        ordinat = 0;
    }

    // Constructor Parameter
    Koordinat(double absis, double ordinat) {
        this->absis = absis;
        this->ordinat = ordinat;
    }

    // Setter Absis
    void setAbsis(double absis) {
        this->absis = absis;
    }

    // Setter Ordinat
    void setOrdinat(double ordinat) {
        this->ordinat = ordinat;
    }

    // Getter Absis
    double getAbsis() const {
        return absis;
    }

    // Getter Ordinat
    double getOrdinat() const {
        return ordinat;
    }

    // Input Dalam Class
    void inputDalam() {
        cout << "Masukkan Nilai Absis (input dalam): ";
        cin >> absis;
        cout << "Masukkan Nilai Ordinat (input dalam): ";
        cin >> ordinat;
    }

    // Method Mencari titik Tengah (void) 
    void titikTengahVoid(const Koordinat& a, const Koordinat& b) {
        this->absis = (a.absis + b.absis) / 2;
        this->ordinat = (a.ordinat + b.ordinat) / 2;
    }

    // Method Mencari titik Tengah (Fungsi)
    Koordinat titikTengahFungsi(const Koordinat& p) const {
        Koordinat tengah;
        tengah.absis = (p.absis + this->absis) / 2;
        tengah.ordinat = (p.ordinat + this->ordinat) / 2;
        return tengah;
    }

    // Method Pencerminan terhadap sumbu X (void)
    void pencerminanXVoid(Koordinat& pHasil) const {
        pHasil.absis = this->absis;
        pHasil.ordinat = -1 * this->ordinat;
    }

    // Method Pencerminan terhadap sumbu x (fungsi)
    Koordinat pencerminanXFungsi() const {
        Koordinat pCermin;
        pCermin.absis = this->absis;
        pCermin.ordinat = -1 * this->ordinat;
        return pCermin;
    }

    // Method Pencerminan terhadap sumbu y (void)
    void pencerminanYVoid(Koordinat& pHasil) const {
        pHasil.absis = -1 * this->absis;
        pHasil.ordinat = this->ordinat;
    }

    // Method Pencerminan terhadap sumbu y (Fungsi)
    Koordinat pencerminanYFungsi() const {
        Koordinat pCermin;
        pCermin.absis = -1 * this->absis;
        pCermin.ordinat = this->ordinat;
        return pCermin;
    }

    // Method Mencari Jarak antar 2 titik (void)
    void jarakDuaTitikVoid(const Koordinat& b, double jarak[]) const {
        jarak[0] = sqrt(pow(b.absis - this->absis, 2) + pow(b.ordinat - this->ordinat, 2));
    }

    // Method Mencari Jarak antar 2 titik (Fungsi)
    double jarakDuaTitikFungsi(const Koordinat& p) const {
        return sqrt(pow(p.absis - this->absis, 2) + pow(p.ordinat - this->ordinat, 2));
    }

    // Deklarasi Output Dalam (definisi di luar class setelah helper format)
    void outputDalam(const Koordinat& k2, const Koordinat& k3, const Koordinat& k4) const;
};

// Helper Format Angka (Setara `format` di Java)
string format(double nilai) {
    stringstream ss;
    ss << setprecision(6) << nilai;
    return ss.str();
}

// Implementasi Output Dalam setelah helper format tersedia
void Koordinat::outputDalam(const Koordinat& k2, const Koordinat& k3, const Koordinat& k4) const {
    cout << "\n=========================================\n";
    cout << "          OUTPUT DALAM (SEMUA OBJEK)     \n";
    cout << "=========================================\n";
    cout << "Objek Koordinat ke-1 : (" << format(this->absis) << ", " << format(this->ordinat) << ")\n";
    cout << "Objek Koordinat ke-2 : (" << format(k2.getAbsis()) << ", " << format(k2.getOrdinat()) << ")\n";
    cout << "Objek Koordinat ke-3 : (" << format(k3.getAbsis()) << ", " << format(k3.getOrdinat()) << ")\n";
    cout << "Objek Koordinat ke-4 : (" << format(k4.getAbsis()) << ", " << format(k4.getOrdinat()) << ")\n";
    cout << "=========================================\n";
}

// Input Luar Class
void inputLuar(Koordinat& k) {
    double a, o;
    cout << "Masukkan Nilai Absis (Input Luar): ";
    cin >> a;
    cout << "Masukkan Nilai Ordinat (Input Luar): ";
    cin >> o;
    k.setAbsis(a);
    k.setOrdinat(o);
}

// Output Luar
void outputLuar(const Koordinat& k1, const Koordinat& k2, const Koordinat& k3, const Koordinat& k4) {
    cout << "\n=========================================\n";
    cout << "               OUTPUT LUAR                \n";
    cout << "=========================================\n";

    Koordinat daftarObjek[] = {k1, k2, k3, k4};

    for (int i = 0; i < 4; i++) {
        cout << "Objek Koordinat ke-" << (i + 1) << " : ("
             << format(daftarObjek[i].getAbsis()) << ", " << format(daftarObjek[i].getOrdinat()) << ")\n";
    }
}

// Validasi Pilihan
int bacaPilihan(const string& pesan, int min, int max) {
    int nilai;
    while (true) {
        cout << pesan;
        if (!(cin >> nilai)) {
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
            cout << "Masukkan angka yang valid!\n";
        } else {
            cin.ignore(numeric_limits<streamsize>::max(), '\n'); // Bersihkan sisa buffer
            if (nilai >= min && nilai <= max) return nilai;
            cout << "Pilihan harus antara " << min << " sampai " << max << "!\n";
        }
    }
}

class Menu {
private:
    int menuUtama;

public:
    void tampilMenu() {
        Koordinat koor1; // Input Melalui Setter
        koor1.setAbsis(3);
        koor1.setOrdinat(4);

        Koordinat koor2(5, 6); // Input melalui Constructor Parameter
        Koordinat koor3;       // Input Melalui method inputDalam
        Koordinat koor4;       // Input Melalui method inputLuar

        do {
            cout << "\n=========================================\n";
            cout << "     MENU APLIKASI KOORDINAT KARTESIUS     \n";
            cout << "=========================================\n";
            cout << "1. Input Koordinat 3 dan 4\n";
            cout << "2. Pencerminan\n";
            cout << "3. Titik Tengah\n";
            cout << "4. Jarak 2 Titik\n";
            cout << "5. Tampilkan Seluruh Objek\n";
            cout << "0. Keluar\n";
            cout << "=========================================\n";
            cout << ">> Masukkan Pilihan Menu : ";
            cin >> menuUtama;

            switch (menuUtama) {
                case 1: {
                    int pilihObjek;
                    cout << "=========================================\n";
                    cout << "PILIH OBJEK YANG MAU DI-INPUT\n";
                    cout << "1. Koordinat 3 (via Input Dalam)\n";
                    cout << "2. Koordinat 4 (via Input Luar)\n";
                    cout << "=========================================\n";
                    cout << ">> Pilih Objek (1-2): ";
                    cin >> pilihObjek;

                    if (pilihObjek == 1) {
                        cout << "Input untuk Koordinat 3:\n";
                        koor3.inputDalam();
                    } else if (pilihObjek == 2) {
                        cout << "Input untuk Koordinat 4:\n";
                        inputLuar(koor4);
                    }
                    break;
                }
                case 2: {
                    int sumbu, pilihObjek;
                    cout << "=========================================\n";
                    cout << "PENCERMINAN\n";
                    cout << "1. Terhadap Sumbu X\n";
                    cout << "2. Terhadap Sumbu Y\n";
                    cout << "=========================================\n";

                    sumbu = bacaPilihan(">> Pilih Sumbu (1/2): ", 1, 2);
                    pilihObjek = bacaPilihan(">> Pilih Objek yang dicerminkan (1-4): ", 1, 4);

                    Koordinat arr[] = {koor1, koor2, koor3, koor4};
                    Koordinat target = arr[pilihObjek - 1];

                    if (sumbu == 1) {
                        Koordinat hasilFungsi = target.pencerminanXFungsi();
                        Koordinat hasilVoid;
                        target.pencerminanXVoid(hasilVoid);

                        cout << "Hasil Pencerminan terhadap Sumbu X:\n";
                        cout << "   > Versi Fungsi : (" << format(hasilFungsi.getAbsis()) << ", " << format(hasilFungsi.getOrdinat()) << ")\n";
                        cout << "   > Versi Void   : (" << format(hasilVoid.getAbsis()) << ", " << format(hasilVoid.getOrdinat()) << ")\n";
                    } else {
                        Koordinat hasilFungsi = target.pencerminanYFungsi();
                        Koordinat hasilVoid;
                        target.pencerminanYVoid(hasilVoid);

                        cout << "Hasil Pencerminan terhadap Sumbu Y:\n";
                        cout << "   > Versi Fungsi : (" << format(hasilFungsi.getAbsis()) << ", " << format(hasilFungsi.getOrdinat()) << ")\n";
                        cout << "   > Versi Void   : (" << format(hasilVoid.getAbsis()) << ", " << format(hasilVoid.getOrdinat()) << ")\n";
                    }
                    break;
                }
                case 3: {
                    int o1, o2;
                    cout << "=========================================\n";
                    cout << "       TITIK TENGAH ANTARA 2 OBJEK       \n";
                    cout << "=========================================\n";

                    o1 = bacaPilihan("Pilih Objek Pertama (1-4): ", 1, 4);
                    o2 = bacaPilihan("Pilih Objek Kedua (1-4): ", 1, 4);

                    Koordinat arr[] = {koor1, koor2, koor3, koor4};

                    Koordinat hasilFungsi = arr[o1 - 1].titikTengahFungsi(arr[o2 - 1]);
                    Koordinat hasilVoid;
                    hasilVoid.titikTengahVoid(arr[o1 - 1], arr[o2 - 1]);

                    cout << "Titik Tengah antara Objek " << o1 << " dan " << o2 << " :\n";
                    cout << "   > Versi Fungsi : (" << format(hasilFungsi.getAbsis()) << ", " << format(hasilFungsi.getOrdinat()) << ")\n";
                    cout << "   > Versi Void   : (" << format(hasilVoid.getAbsis()) << ", " << format(hasilVoid.getOrdinat()) << ")\n";
                    break;
                }
                case 4: {
                    int o1, o2;
                    cout << "=========================================\n";
                    cout << "          JARAK ANTARA 2 TITIK           \n";
                    cout << "=========================================\n";

                    o1 = bacaPilihan("Pilih Objek Pertama (1-4): ", 1, 4);
                    o2 = bacaPilihan("Pilih Objek Kedua (1-4): ", 1, 4);

                    Koordinat arr[] = {koor1, koor2, koor3, koor4};

                    double jarakFungsi = arr[o1 - 1].jarakDuaTitikFungsi(arr[o2 - 1]);
                    double jarakVoid[1];
                    arr[o1 - 1].jarakDuaTitikVoid(arr[o2 - 1], jarakVoid);

                    cout << "Jarak antara Objek " << o1 << " dan " << o2 << " :\n";
                    cout << "   - Versi Fungsi : " << format(jarakFungsi) << "\n";
                    cout << "   - Versi Void   : " << format(jarakVoid[0]) << "\n";
                    break;
                }
                case 5: {
                    int pilihOut;
                    cout << "=========================================\n";
                    cout << "Pilih Menu Output\n";
                    cout << "1. Output Dalam (Menampilkan semua objek via Method Class)\n";
                    cout << "2. Output Luar (Menampilkan semua objek via Fungsi Luar)\n";
                    cout << "=========================================\n";
                    cout << ">> Pilih Menu Output : ";
                    cin >> pilihOut;

                    if (pilihOut == 1) {
                        koor1.outputDalam(koor2, koor3, koor4);
                    } else if (pilihOut == 2) {
                        outputLuar(koor1, koor2, koor3, koor4);
                    }
                    break;
                }
                case 0:
                    cout << "\nBye-bye!.\n";
                    break;
                default:
                    cout << "Pilihan tidak valid! Silakan coba lagi.\n";
            }
        } while (menuUtama != 0);
    }
};

int main() {
    Menu menu;
    menu.tampilMenu();
    return 0;
}