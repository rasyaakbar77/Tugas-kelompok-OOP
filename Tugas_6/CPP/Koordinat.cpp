/*
Nama Program : Koordinat.cpp
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
    double getAbsis() {
        return absis;
    }

    // Getter Ordinat
    double getOrdinat() {
        return ordinat;
    }

    // Input Dalam Class
    void inputDalam() {
        std::cout << "Masukkan Nilai Absis (input dalam): ";
        std::cin >> absis;
        std::cout << "Masukkan Nilai Ordinat (input dalam): ";
        std::cin >> ordinat;
    }

    // Method Mencari titik Tengah (void) 
    void titikTengahVoid(Koordinat a, Koordinat b) {
        this->absis = (a.absis + b.absis) / 2;
        this->ordinat = (a.ordinat + b.ordinat) / 2;
    }

    // Method Mencari titik Tengah (Fungsi)
    Koordinat titikTengahFungsi(Koordinat p) {
        Koordinat tengah;
        tengah.absis = (p.absis + this->absis) / 2;
        tengah.ordinat = (p.ordinat + this->ordinat) / 2;
        return tengah;
    }

    // Method Pencerminan terhadap sumbu X (void)
    void pencerminanXVoid(Koordinat &pHasil) {
        pHasil.absis = this->absis;
        pHasil.ordinat = -1 * this->ordinat;
    }

    // Method Pencerminan terhadap sumbu x (fungsi)
    Koordinat pencerminanXFungsi() {
        Koordinat pCermin;
        pCermin.absis = this->absis;
        pCermin.ordinat = -1 * this->ordinat;
        return pCermin;
    }

    // Method Pencerminan terhadap sumbu y (void)
    void pencerminanYVoid(Koordinat &pHasil) {
        pHasil.absis = -1 * this->absis;
        pHasil.ordinat = this->ordinat;
    }

    // Method Pencerminan terhadap sumbu y (Fungsi)
    Koordinat pencerminanYFungsi() {
        Koordinat pCermin;
        pCermin.absis = -1 * this->absis;
        pCermin.ordinat = this->ordinat;
        return pCermin;
    }

    // Method Mencari Jarak antar 2 titik (void)
    void jarakDuaTitikVoid(Koordinat b, double &jarak) {
        jarak = sqrt(pow(b.absis - this->absis, 2) + pow(b.ordinat - this->ordinat, 2));
    }

    // Method Mencari Jarak antar 2 titik (Fungsi)
    double jarakDuaTitikFungsi(Koordinat p) {
        double jarak = sqrt(pow(p.absis - this->absis, 2) + pow(p.ordinat - this->ordinat, 2));
        return jarak;
    }

    // Output Dalam
    void outputDalam(Koordinat k2, Koordinat k3, Koordinat k4) {
        std::cout << "\n=========================================\n";
        std::cout << "          OUTPUT DALAM (SEMUA OBJEK)     \n";
        std::cout << "=========================================\n";
        std::cout << "Objek Koordinat ke-1 : (" << this->absis << ", " << this->ordinat << ")\n";
        std::cout << "Objek Koordinat ke-2 : (" << k2.getAbsis() << ", " << k2.getOrdinat() << ")\n";
        std::cout << "Objek Koordinat ke-3 : (" << k3.getAbsis() << ", " << k3.getOrdinat() << ")\n";
        std::cout << "Objek Koordinat ke-4 : (" << k4.getAbsis() << ", " << k4.getOrdinat() << ")\n";
        std::cout << "=========================================\n";
    }
};

// Input Luar Class 
void inputLuar(Koordinat &k) {
    double a, o;
    std::cout << "Masukkan Nilai Absis (Input Luar): ";
    std::cin >> a;
    std::cout << "Masukkan Nilai Ordinat (Input Luar): ";
    std::cin >> o;
    k.setAbsis(a);
    k.setOrdinat(o);
}

// Output Luar 
void outputLuar(Koordinat k1, Koordinat k2, Koordinat k3, Koordinat k4) {
    std::cout << "\n=========================================\n";
    std::cout << "               OUTPUT LUAR                \n";
    std::cout << "=========================================\n";
    
    Koordinat daftarObjek[4] = {k1, k2, k3, k4};
    
    for (int i = 0; i < 4; i++) {
        std::cout << "Objek Koordinat ke-" << i + 1 << " : (" 
                  << daftarObjek[i].getAbsis() << ", " << daftarObjek[i].getOrdinat() << ")\n";
    }
}

//validasi pilihan (angka harus di antara min dan max)
int bacaPilihan(std::string pesan, int min, int max) {
    int nilai;
    while (true) {
        std::cout << pesan;
        std::cin >> nilai;
        if (std::cin.fail()) {
            std::cin.clear();                // reset error state
            std::cin.ignore(10000, '\n');    // buang input invalid
            std::cout << "Masukkan angka yang valid!\n";
        } else {
            std::cin.ignore(10000, '\n');    // bersihkan sisa newline
            if (nilai >= min && nilai <= max) return nilai;
            std::cout << "Pilihan harus antara " << min << " sampai " << max << "!\n";
        }
    }
}

int main() {
    Koordinat koor1; // Input Melalui Setter
    koor1.setAbsis(3);
    koor1.setOrdinat(4);
    
    Koordinat koor2(5, 6); // Input melalui Constructor Parameter
    Koordinat koor3; // Input Melalui method inputDalam
    Koordinat koor4; // Input Melalui method inputLuar

    int menuUtama;
    do {
        std::cout << "\n=========================================\n";
        std::cout << "     MENU APLIKASI KOORDINAT KARTESIUS     \n";
        std::cout << "=========================================\n";
        std::cout << "1. Input Koordinat 3 dan 4\n";
        std::cout << "2. Pencerminan\n";
        std::cout << "3. Titik Tengah\n";
        std::cout << "4. Jarak 2 Titik\n";
        std::cout << "5. Tampilkan Seluruh Objek\n";
        std::cout << "0. Keluar\n";
        std::cout << "=========================================\n";
        std::cout << ">> Masukkan Pilihan Menu : ";
        std::cin >> menuUtama;

        switch (menuUtama) {
            case 1: {
                int pilihObjek;
                std::cout << "=========================================\n";
                std::cout << "PILIH OBJEK YANG MAU DI-INPUT\n";
                std::cout << "1. Koordinat 3 (via Input Dalam)\n";
                std::cout << "2. Koordinat 4 (via Input Luar)\n";
                std::cout << "=========================================\n";
                std::cout << ">> Pilih Objek (1-2): ";
                std::cin >> pilihObjek;

                if (pilihObjek == 1) {
                    std::cout << "Input untuk Koordinat 3:\n";
                    koor3.inputDalam();
                } else if (pilihObjek == 2) {
                    std::cout << "Input untuk Koordinat 4:\n";
                    inputLuar(koor4);
                } 
                break;
            }
            case 2: {
                int sumbu, pilihObjek;
                std::cout << "=========================================\n";
                std::cout << "PENCERMINAN\n";
                std::cout << "1. Terhadap Sumbu X\n";
                std::cout << "2. Terhadap Sumbu Y\n";
                std::cout << "=========================================\n";
                //std::cout << ">> Pilih Sumbu (1/2): ";
                //std::cin >> sumbu;

                sumbu = bacaPilihan(">> Pilih Sumbu (1/2): ", 1, 2);
                
               
                //std::cout << ">> Pilih Objek yang dicerminkan (1-4): ";
                //std::cin >> pilihObjek;

                pilihObjek = bacaPilihan(">> Pilih Objek yang dicerminkan (1-4): ", 1, 4);

                Koordinat arr[4] = {koor1, koor2, koor3, koor4};
                Koordinat target = arr[pilihObjek - 1];

                if (sumbu == 1) {
                    Koordinat hasilFungsi = target.pencerminanXFungsi();
                    Koordinat hasilVoid;
                    target.pencerminanXVoid(hasilVoid);
                    
                    std::cout << "Hasil Pencerminan terhadap Sumbu X:\n";
                    std::cout << "   > Versi Fungsi : (" << hasilFungsi.getAbsis() << ", " << hasilFungsi.getOrdinat() << ")\n";
                    std::cout << "   > Versi Void   : (" << hasilVoid.getAbsis() << ", " << hasilVoid.getOrdinat() << ")\n";
                } else {
                    Koordinat hasilFungsi = target.pencerminanYFungsi();
                    Koordinat hasilVoid;
                    target.pencerminanYVoid(hasilVoid);

                    std::cout << "Hasil Pencerminan terhadap Sumbu Y:\n";
                    std::cout << "   > Versi Fungsi : (" << hasilFungsi.getAbsis() << ", " << hasilFungsi.getOrdinat() << ")\n";
                    std::cout << "   > Versi Void   : (" << hasilVoid.getAbsis() << ", " << hasilVoid.getOrdinat() << ")\n";
                }
                break;
            }
            case 3: {
                int o1, o2;
                std::cout << "=========================================\n";
                std::cout << "       TITIK TENGAH ANTARA 2 OBJEK       \n";
                std::cout << "=========================================\n";
                //std::cout << "Pilih Objek Pertama (1-4): "; std::cin >> o1;
                //std::cout << "Pilih Objek Kedua (1-4): "; std::cin >> o2;

                o1 = bacaPilihan("Pilih Objek Pertama (1-4): ", 1, 4);
                o2 = bacaPilihan("Pilih Objek Kedua (1-4): ", 1, 4);

                Koordinat arr[4] = {koor1, koor2, koor3, koor4};
                
                Koordinat hasilFungsi = arr[o1 - 1].titikTengahFungsi(arr[o2 - 1]);
                Koordinat hasilVoid;
                hasilVoid.titikTengahVoid(arr[o1 - 1], arr[o2 - 1]);

                std::cout << "Titik Tengah antara Objek " << o1 << " dan " << o2 << " :\n";
                std::cout << "   > Versi Fungsi : (" << hasilFungsi.getAbsis() << ", " << hasilFungsi.getOrdinat() << ")\n";
                std::cout << "   > Versi Void   : (" << hasilVoid.getAbsis() << ", " << hasilVoid.getOrdinat() << ")\n";
                break;
            }
            case 4: {
                int o1, o2;
                std::cout << "=========================================\n";
                std::cout << "          JARAK ANTARA 2 TITIK           \n";
                std::cout << "=========================================\n";
                //std::cout << "Pilih Objek Pertama (1-4): "; std::cin >> o1;
                //std::cout << "Pilih Objek Kedua (1-4): "; std::cin >> o2;

                o1 = bacaPilihan("Pilih Objek Pertama (1-4): ", 1, 4);
                o2 = bacaPilihan("Pilih Objek Kedua (1-4): ", 1, 4);

                Koordinat arr[4] = {koor1, koor2, koor3, koor4};

                double jarakFungsi = arr[o1 - 1].jarakDuaTitikFungsi(arr[o2 - 1]);
                double jarakVoid;
                arr[o1 - 1].jarakDuaTitikVoid(arr[o2 - 1], jarakVoid);

                std::cout << "Jarak antara Objek " << o1 << " dan " << o2 << " :\n";
                std::cout << "   - Versi Fungsi : " << jarakFungsi << "\n";
                std::cout << "   - Versi Void   : " << jarakVoid << "\n";
                break;
            }
            case 5: {
                int pilihOut;
                std::cout << "=========================================\n";
                std::cout << "Pilih Menu Output\n";
                std::cout << "1. Output Dalam (Menampilkan semua objek via Method Class)\n";
                std::cout << "2. Output Luar (Menampilkan semua objek via Fungsi Luar)\n";
                std::cout << "=========================================\n";
                std::cout << ">> Pilih Menu Output : "; std::cin >> pilihOut;
                
                if (pilihOut == 1) {
                    koor1.outputDalam(koor2, koor3, koor4);
                }
                else if (pilihOut == 2) {
                    outputLuar(koor1, koor2, koor3, koor4);
                }
                break;
            }
            case 0:
                std::cout << "\nBye-bye!.\n";
                break;
            default:
                std::cout << "Pilihan tidak valid! Silakan coba lagi.\n";
        }
    } while (menuUtama != 0);

    return 0;
}