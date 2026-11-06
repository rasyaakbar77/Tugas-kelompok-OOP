/*
Nama Program : Pegawai.cpp
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 28-10-2026
Deskripsi Program : Sebuah program untuk menghitung gaji karyawan dengan input NIP, nama, gol, waktu datang, waktu pulang.
                    Menggunakan konsep inheritance dengan membuat superclass Person yang akan di inherit oleh subclass Pegawai,
                    dengan total 5 class secara OOP.
*/


#include <iostream>
#include <vector>
#include <string>
#include <cmath>
#include <iomanip>
#include <sstream>
#include <limits>

class Util {
public:
    static int bacaInt(const std::string& pesan) {
        int val;
        while (true) {
            std::cout << pesan;
            if (std::cin >> val) {
                std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
                return val;
            } else {
                std::cout << "Masukkan angka yang valid!\n";
                std::cin.clear();
                std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
            }
        }
    }
 
    static std::string rupiah(int nilai) {
        std::string s = std::to_string(nilai);
        int n = s.length();
        for (int i = n - 3; i > 0; i -= 3) {
            s.insert(i, ".");
        }
        return s;
    }

    static std::string pad(const std::string& s, int w, char rata) {
        std::string t = s.substr(0, w);
        int sisa = w - static_cast<int>(t.length());
        if (rata == '<') return t + std::string(sisa, ' ');
        if (rata == '>') return std::string(sisa, ' ') + t;
        int kiri = sisa / 2;
        return std::string(kiri, ' ') + t + std::string(sisa - kiri, ' ');
    }
};

class Waktu {
public:
    int jam;
    int menit;
    int detik;
public:
    Waktu() : jam(0), menit(0), detik(0) {}

    Waktu(int jam, int menit, int detik) {
        this->jam = jam;
        this->menit = menit;
        this->detik = detik;
    }

    void setWaktu(int j, int m, int d) { 
        jam = j; 
        menit = m; 
        detik = d; 
    }
    void setJam(int j) { 
        jam = j; 
    }

    void setMenit(int m) { 
        menit = m; 
    }

    void setDetik(int d) { 
        detik = d; 
    }

    int getJam() const { 
        return jam; 
    }

    int getMenit() const { 
        return menit; 
    }

    int getDetik() const { 
        return detik; 
    }

    void inputDalam() {
        do { 
            jam = Util::bacaInt("   Jam   (0-23) : "); 
        } while (jam < 0 || jam > 23);

        do { 
            menit = Util::bacaInt("   Menit (0-59) : "); 
        } while (menit < 0 || menit > 59);

        do { 
            detik = Util::bacaInt("   Detik (0-59) : "); 
        } while (detik < 0 || detik > 59);
    }

    int totalDetik() const {
        return (jam * 3600) + (menit * 60) + detik;
    }

    Waktu hitungSelisihFungsi(const Waktu& w2) const {
        int selisihTotal = abs(this->totalDetik() - w2.totalDetik());
        Waktu hasil;
        hasil.jam = selisihTotal / 3600;
        hasil.menit = (selisihTotal % 3600) / 60;
        hasil.detik = selisihTotal % 60;
        return hasil;
    }

    void hitungSelisihVoid(const Waktu& w1, const Waktu& w2) {
        int selisihTotal = abs(w1.totalDetik() - w2.totalDetik());
        this->jam = selisihTotal / 3600;
        this->menit = (selisihTotal % 3600) / 60;
        this->detik = selisihTotal % 60;
    }

    std::string toString() const {
        std::stringstream ss;
        ss << std::setfill('0') << std::setw(2) << jam << ":"
           << std::setfill('0') << std::setw(2) << menit << ":"
           << std::setfill('0') << std::setw(2) << detik;
        return ss.str();
    }

    void outputDalam() {
        std::cout << "Waktu = " << toString() << "\n";
    }
};

class Person {
protected:
    std::string nama;
    std::string alamat;
public:
    Person() : nama(""), alamat("") {}

    Person(std::string nama, std::string alamat) {
        this->nama = nama;
        this->alamat = alamat;
    }
    
    void inputDalam() {
        std::cout << "Nama   : ";
        std::getline(std::cin, nama);
        std::cout << "Alamat : ";
        std::getline(std::cin, alamat);
    }

    void setNama(std::string name) {
        nama = name;
    }

    void setAlamat(std::string address) {
        alamat = address;
    }

    std::string getNama() {
        return this->nama;
    }

    std::string getAlamat() {
        return this->alamat;
    }
};

class Pegawai : public Person {
private:
    int gol;
    std::string nip;
    Waktu datang;
    Waktu pulang;
    Waktu lamaKerja;
    Waktu jamLembur;
    std::string statusPeringatan;
public:
    Pegawai() : Person(), gol(1), nip(""), statusPeringatan("") {}

    Pegawai(std::string nip, std::string nama, std::string alamat, int umur, int gol) : Person(nama, alamat) {
        this->nip = nip;
        this->gol = gol;
        statusPeringatan = "";
    }

    void inputPegawai() {
        std::cout<< "NIP     : ";
        std::getline(std::cin, nip);
        Person::inputDalam();

        do {
            gol = Util::bacaInt("Masukkan Gol (1-4) : ");
        } while (gol < 1 || gol > 4);

        do {
            std::cout << "Waktu Datang :\n";
            datang.inputDalam();
            std::cout << "Waktu Pulang :\n";
            pulang.inputDalam();
            if (pulang.totalDetik() <= datang.totalDetik())
                std::cout << "Waktu pulang harus setelah waktu datang, ulangi!\n";
        } while (pulang.totalDetik() <= datang.totalDetik());
    }

    void setNip(const std::string& n) { 
        nip = n; 
    }

    void setGol(int g) {
        gol = g; 
    }

    void setDatang(const Waktu& d) { 
        datang = d; 
    }

    void setPulang(const Waktu& p) { 
        pulang = p; 
    }

    std::string getNip() const { 
        return nip; 
    }

    int getGol() const { 
        return gol; 
    }

    std::string getStatusPeringatan() const { 
        return statusPeringatan; 
    }

    int getGajiHarian() const {
        switch(gol) {
            case 1: return 150000;
            case 2: return 200000;
            case 3: return 400000;
            case 4: return 500000;
            default: return 0;
        }
    }

    int getTarifLembur() const {
        switch(gol) {
            case 1: return 50000;
            case 2: return 75000;
            case 3: return 150000;
            case 4: return 200000;
            default: return 0;
        }
    }

    int getLembur() const {
        return jamLembur.getJam() * getTarifLembur();
    }

    int getTotal() const {
        return getGajiHarian() + getLembur();
    }

    void prosesGaji() {
        lamaKerja = pulang.hitungSelisihFungsi(datang);

        Waktu batas(8, 0, 0);
        if (lamaKerja.totalDetik() >= batas.totalDetik()) {
            jamLembur.hitungSelisihVoid(lamaKerja, batas);
            statusPeringatan = "ok";
        } else {
            jamLembur = Waktu();
            statusPeringatan = "Peringatan";
        }

    }

    std::vector<std::string> getBaris(int no) const {
        return {
            std::to_string(no), nama, alamat, nip, std::to_string(gol),
            datang.toString(), pulang.toString(),
            lamaKerja.toString(), jamLembur.toString(),
            Util::rupiah(getGajiHarian()), Util::rupiah(getLembur()), Util::rupiah(getTotal()),
            statusPeringatan
        };
    }     
};


const int KOLOM = 13;
const std::string HEADER[KOLOM] = {"No", "Nama", "Alamat", "NIP", "Gol", "Datang", "Pulang",
                                   "Lama", "Jam Lembur", "Gaji Harian", "Lembur", "Total", "Status"};
const int  LEBAR[KOLOM] = {3, 10, 14, 8, 3, 8, 8, 8, 10, 11, 11, 11, 10};
const char RATA[KOLOM]  = {'^', '<', '<', '<', '^', '^', '^', '^', '^', '>', '>', '>', '^'};

class ArrayPegawai {
private:
    static const int max = 100;
    Pegawai arr[max];
    int jumlah;

public:
    ArrayPegawai() : jumlah(0) {}

    ArrayPegawai(Pegawai dataPegawai[], int jml) {
        jumlah = (jml > max) ? max : jml;
        for (int i = 0; i < jumlah; i++) {
            arr[i] = dataPegawai[i];
        }
    }

    void setJumlah(int jml) {
        if (jml > max || jml < 0) {
            std::cout<<"Jumlah Array Tidak Valid.\n";
        }
        jumlah = (jml > max) ? max : (jml < 0 ? 0 : jml);
    }

    int getJumlah() const {
        return jumlah;
    }

    void inputDalam() {
        do {
            jumlah = Util::bacaInt("Masukkan jumlah pegawai (1-100): ");
        } while (jumlah < 1 || jumlah > max);

        for (int i = 0; i < jumlah; i++) {
            std::cout << "\nData Pegawai ke-" << (i + 1) << "\n";
            arr[i].inputPegawai();
        }
    }

    void prosesData() {
        for (int i = 0; i < jumlah; i++) {
            arr[i].prosesGaji();
        }
    }

    void setPegawaiAt(int index, const Pegawai& p) {
        if (index >= 0 && index < max) {
            arr[index] = p;
        }
    }

    Pegawai getPegawaiAt(int index) const {
        if (index >= 0 && index < max) {
            return arr[index];
        }
        return Pegawai(); 
    }

private:
    std::string garis() const {
        std::string g = "+";
        for (int i = 0; i < KOLOM; i++) g += std::string(LEBAR[i] + 2, '-') + "+";
        return g;
    }

    std::string baris(const std::vector<std::string>& cells) const {
        std::string b = "|";
        for (int i = 0; i < KOLOM; i++) {
            b += " " + Util::pad(cells[i], LEBAR[i], RATA[i]) + " |";
        }
        return b;
    }

public:
    void outputData() const {
        std::string g = garis();
        std::cout << "\n" << Util::pad("Daftar Gaji Harian PT Informatika", g.length(), '^') << "\n";
        std::cout << g << "\n";
        std::cout << baris(std::vector<std::string>(HEADER, HEADER + KOLOM)) << "\n";
        std::cout << g << "\n";

        double totalSemua = 0;
        int maxGaji = (jumlah > 0) ? arr[0].getTotal() : 0;
        int minGaji = (jumlah > 0) ? arr[0].getTotal() : 0;

        for (int i = 0; i < jumlah; i++) {
            std::cout << baris(arr[i].getBaris(i + 1)) << "\n";
            int t = arr[i].getTotal();
            totalSemua += t;
            if (t > maxGaji) maxGaji = t;
            if (t < minGaji) minGaji = t;
        }
        std::cout << g << "\n";

        double rataRata = (jumlah > 0) ? (totalSemua / jumlah) : 0;

        std::cout << "Rata-rata Gaji : " << (jumlah > 0 ? Util::rupiah(static_cast<int>(rataRata)) : "---") << "\n";
        std::cout << "Gaji Tertinggi : " << (jumlah > 0 ? Util::rupiah(maxGaji) : "---") << "\n";
        std::cout << "Gaji Terendah  : " << (jumlah > 0 ? Util::rupiah(minGaji) : "---") << "\n";
    }
};

class Menu {
private:
    ArrayPegawai dataPegawai;

public:
    Menu() {}
    
    void tampilkanMenu() const {
        std::cout << "\n========================================\n";
        std::cout << "         MENU GAJI PT INFORMATIKA         \n";
        std::cout << "========================================\n";
        std::cout << "1. Input Data Pegawai\n";
        std::cout << "2. Tampilkan Daftar Gaji\n";
        std::cout << "3. Keluar\n";
        std::cout << "----------------------------------------\n";
    }

    void jalankan() {
        int pilihan;
        do {
            tampilkanMenu();
            pilihan = Util::bacaInt("Pilih menu (1-3): ");
            switch (pilihan) {
                case 1:
                    dataPegawai.inputDalam();
                    break;
                case 2:
                    dataPegawai.prosesData();
                    dataPegawai.outputData();
                    break;
                case 3:
                    std::cout << "Keluar dari program!\n";
                    break;
                default:
                    std::cout << "Input tidak valid, silahkan coba lagi!\n";
            }
        } while (pilihan != 3);
    }
};

int main() {
    Menu pegawaiApp;
    pegawaiApp.jalankan();
    return 0;
}
// class Pegawai {
// private:    
//     std::string nip;
//     std::string nama;
//     int gol;
//     Waktu datang;
//     Waktu pulang;
//     Waktu lamaKerja;
//     Waktu jamLembur;
//     int gajiHarian;
//     int lembur;
//     int total;
//     std::string statusPeringatan;

//     //format penulisan uang rupiah
//     std::string rupiah(int nilai) const {
//         std::string s = std::to_string(nilai);
//         int n = s.length();
//         for (int i = n - 3; i > 0; i -= 3) {
//             s.insert(i, ".");
//         }
//         return s;
//     }

//     //helper validasi integer
//     int bacaInt(const std::string& pesan) {
//         int val;
//         while (true) {
//             std::cout << pesan;
//             if (std::cin >> val) {
//                 std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
//                 return val;
//             } else {
//                 std::cout << "Masukkan angka yang valid!\n";
//                 std::cin.clear();
//                 std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
//             }
//         }
//     }

// public:
//     Pegawai() : nip(""), nama(""), gol(0), gajiHarian(0), lembur(0), total(0), statusPeringatan("") {}

//     Pegawai(std::string nip, std::string nama, int gol, Waktu datang, Waktu pulang) {
//         this->nip = nip;
//         this->nama = nama;
//         this->gol = gol;
//         this->datang = datang;
//         this->pulang = pulang;
//         this->gajiHarian = 0;
//         this->lembur = 0;
//         this->total = 0;
//         this->statusPeringatan = "";
//     }

//     void inputPegawai() {
//         std::cout << "Masukkan NIP  : ";
//         std::getline(std::cin, nip);
//         std::cout << "Masukkan Nama : ";
//         std::getline(std::cin, nama);
//         do {
//             gol = bacaInt("Masukkan Gol (1-4) : ");
//         } while (gol < 1 || gol > 4);

//         do {
//             std::cout << "Waktu Datang :\n";
//             datang.inputDalam();
//             std::cout << "Waktu Pulang :\n";
//             pulang.inputDalam();
//             if (pulang.totalDetik() <= datang.totalDetik())
//                 std::cout << "Waktu pulang harus setelah waktu datang, ulangi!\n";
//         } while (pulang.totalDetik() <= datang.totalDetik());
//     }

//     void setNip(const std::string& n) { 
//         nip = n; 
//     }
//     void setNama(const std::string& n) {
//         nama = n; 
//     }
//     void setGol(int g) {
//         gol = g; 
//     }
//     void setDatang(const Waktu& d) { 
//         datang = d; 
//     }
//     void setPulang(const Waktu& p) { 
//         pulang = p; 
//     }
//     std::string getNip() const { 
//         return nip; 
//     }
//     std::string getNama() const { 
//         return nama; 
//     }
//     int getGol() const { 
//         return gol; 
//     }
//     int getTotal() const { 
//         return total; 
//     }
//     std::string getStatusPeringatan() const { 
//         return statusPeringatan; 
//     }

//     void prosesGaji() {
//         lamaKerja = pulang.hitungSelisihFungsi(datang);

//         Waktu batas(8, 0, 0);
//         if (lamaKerja.totalDetik() >= batas.totalDetik()) {
//             jamLembur.hitungSelisihVoid(lamaKerja, batas);
//             statusPeringatan = "ok";
//         } else {
//             jamLembur = Waktu();
//             statusPeringatan = "Peringatan";
//         }

//         int tarif = 0;
//         switch (gol) {
//             case 1: gajiHarian = 150000; tarif = 50000;  break;
//             case 2: gajiHarian = 200000; tarif = 75000;  break;
//             case 3: gajiHarian = 400000; tarif = 150000; break;
//             case 4: gajiHarian = 500000; tarif = 200000; break;
//         }

//         lembur = jamLembur.getJam() * tarif;
//         total = gajiHarian + lembur;
//     }

//     void printPegawai(int no) const {
//         std::cout << std::left << std::setw(3)  << no
//                << std::left << std::setw(7)  << nip
//                << std::left << std::setw(13) << nama
//                << std::left << std::setw(5)  << gol
//                << std::left << std::setw(10) << datang.toString()
//                << std::left << std::setw(10) << pulang.toString()
//                << std::left << std::setw(10) << lamaKerja.toString()
//                << std::left << std::setw(12) << jamLembur.toString()
//                << std::left << std::setw(13) << rupiah(gajiHarian)
//                << std::left << std::setw(10) << rupiah(lembur)
//                << std::left << std::setw(10) << rupiah(total)
//              << statusPeringatan << "\n";
//     }
// 