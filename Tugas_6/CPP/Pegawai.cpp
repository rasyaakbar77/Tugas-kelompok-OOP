/*
Nama Program : Soal3.cpp
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 29-09-2026
Deskripsi Program : Sebuah program untuk menghitung gaji karyawan dengan input NIP, nama, gol, waktu datang, waktu pulang.
                    Dengan perhitungan Gaji Lembur = >= 8 jam (minimal kelebihan 1 jam / pembulatan ke bawah) dan
                    untuk pegawai yg kurang dari 8 jam diberi status peringatan. Dengan aturan Gaji = gapok + lembur secara OOP.
*/

#include <iostream>
#include <string>
#include <cmath>
#include <iomanip>
#include <sstream>
#include <limits>

using namespace std;

// Helper global bacaInt
int bacaInt(const string& pesan) {
    int val;
    while (true) {
        cout << pesan;
        if (cin >> val) {
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
            return val;
        } else {
            cout << "Masukkan angka yang valid!\n";
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }
    }
}

class Waktu {
private:
    int jam;
    int menit;
    int detik;

public:
    Waktu() : jam(0), menit(0), detik(0) {}
    Waktu(int jam, int menit, int detik) : jam(jam), menit(menit), detik(detik) {}

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
        do { jam = bacaInt("   Jam   (0-23) : "); } while (jam < 0 || jam > 23);
        do { menit = bacaInt("   Menit (0-59) : "); } while (menit < 0 || menit > 59);
        do { detik = bacaInt("   Detik (0-59) : "); } while (detik < 0 || detik > 59);
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

    string toString() const {
        stringstream ss;
        ss << setfill('0') << setw(2) << jam << ":"
           << setfill('0') << setw(2) << menit << ":"
           << setfill('0') << setw(2) << detik;
        return ss.str();
    }

    void outputDalam() const {
        cout << "Waktu = " << toString() << "\n";
    }
};

class Pegawai {
private:
    string nip;
    string nama;
    int gol;
    Waktu datang;
    Waktu pulang;
    Waktu lamaKerja;
    Waktu jamLembur;
    int gajiHarian;
    int lembur;
    int total;
    string statusPeringatan;

    string rupiah(int nilai) const {
        string s = to_string(nilai);
        int n = s.length();
        for (int i = n - 3; i > 0; i -= 3) {
            s.insert(i, ".");
        }
        return s;
    }

public:
    Pegawai() : nip(""), nama(""), gol(0), gajiHarian(0), lembur(0), total(0), statusPeringatan("") {}

    Pegawai(string nip, string nama, int gol, Waktu datang, Waktu pulang) {
        this->nip = nip;
        this->nama = nama;
        this->gol = gol;
        this->datang = datang;
        this->pulang = pulang;
        this->gajiHarian = 0;
        this->lembur = 0;
        this->total = 0;
        this->statusPeringatan = "";
    }

    void inputPegawai() {
        cout << "Masukkan NIP  : ";
        getline(cin, nip);
        cout << "Masukkan Nama : ";
        getline(cin, nama);
        do {
            gol = bacaInt("Masukkan Gol (1-4) : ");
        } while (gol < 1 || gol > 4);

        do {
            cout << "Waktu Datang :\n";
            datang.inputDalam();
            cout << "Waktu Pulang :\n";
            pulang.inputDalam();
            if (pulang.totalDetik() <= datang.totalDetik())
                cout << "Waktu pulang harus setelah waktu datang, ulangi!\n";
        } while (pulang.totalDetik() <= datang.totalDetik());
    }

    void setNip(const string& n) { 
        nip = n; 
    }
    void setNama(const string& n) {
        nama = n; 
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
    string getNip() const { 
        return nip; 
    }
    string getNama() const { 
        return nama; 
    }
    int getGol() const { 
        return gol; 
    }
    int getTotal() const { 
        return total; 
    }
    string getStatusPeringatan() const { 
        return statusPeringatan; 
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

        int tarif = 0;
        switch (gol) {
            case 1: gajiHarian = 150000; tarif = 50000;  break;
            case 2: gajiHarian = 200000; tarif = 75000;  break;
            case 3: gajiHarian = 400000; tarif = 150000; break;
            case 4: gajiHarian = 500000; tarif = 200000; break;
        }

        lembur = jamLembur.getJam() * tarif;
        total = gajiHarian + lembur;
    }

    void printPegawai(int no) const {
        cout << left << setw(3)  << no
             << left << setw(7)  << nip
             << left << setw(13) << nama
             << left << setw(5)  << gol
             << left << setw(10) << datang.toString()
             << left << setw(10) << pulang.toString()
             << left << setw(10) << lamaKerja.toString()
             << left << setw(12) << jamLembur.toString()
             << left << setw(13) << rupiah(gajiHarian)
             << left << setw(10) << rupiah(lembur)
             << left << setw(10) << rupiah(total)
             << statusPeringatan << "\n";
    }
};

class Menu {
private:
    int pilih;

public:
    void tampilMenu() {
        Pegawai *p1 = nullptr, *p2 = nullptr, *p3 = nullptr, *p4 = nullptr;

        do {
            cout << "\nMENU GAJI HARIAN PT INFORMATIKA\n";
            cout << " 1. Pegawai 1 : via setter(hardcode)\n";
            cout << " 2. Pegawai 2 : via constructor berparameter(hardcode)\n";
            cout << " 3. Pegawai 3 : input dalam class\n";
            cout << " 4. Pegawai 4 : input luar class (Main)\n";
            cout << " 5. Tampilkan daftar gaji harian\n";
            cout << " 0. Keluar\n";
            pilih = bacaInt("Pilih menu: ");

            switch (pilih) {
                case 1: {
                    if (p1) delete p1;
                    p1 = new Pegawai();
                    Waktu datang1(8, 0, 0);
                    Waktu pulang1(17, 15, 10);
                    p1->setNip("250001");
                    p1->setNama("Ali");
                    p1->setGol(3);
                    p1->setDatang(datang1);
                    p1->setPulang(pulang1);
                    p1->prosesGaji();
                    cout << "Pegawai 1 berhasil diisi (setter).\n";
                    break;
                }
                case 2: {
                    if (p2) delete p2;
                    p2 = new Pegawai("250002", "Budi", 1, Waktu(8, 0, 0), Waktu(15, 30, 0));
                    p2->prosesGaji();
                    cout << "Pegawai 2 berhasil diisi (constructor).\n";
                    break;
                }
                case 3: {
                    cout << "\nPegawai 3\n";
                    if (p3) delete p3;
                    p3 = new Pegawai();
                    p3->inputPegawai();
                    p3->prosesGaji();
                    cout << "Pegawai 3 berhasil diisi (Scanner/cin dalam class).\n";
                    break;
                }
                case 4: {
                    cout << "\nPegawai 4\n";
                    string nip, nama;
                    cout << "Masukkan NIP  : ";
                    getline(cin, nip);
                    cout << "Masukkan Nama : ";
                    getline(cin, nama);

                    int gol;
                    do {
                        gol = bacaInt("Masukkan Gol (1-4) : ");
                    } while (gol < 1 || gol > 4);

                    Waktu datang4, pulang4;

                    do {
                        cout << "Waktu Datang :\n";
                        int j, m, d;
                        do { j = bacaInt("   Jam   (0-23) : "); } while (j < 0 || j > 23);
                        do { m = bacaInt("   Menit (0-59) : "); } while (m < 0 || m > 59);
                        do { d = bacaInt("   Detik (0-59) : "); } while (d < 0 || d > 59);
                        datang4.setWaktu(j, m, d);

                        cout << "Waktu Pulang :\n";
                        do { j = bacaInt("   Jam   (0-23) : "); } while (j < 0 || j > 23);
                        do { m = bacaInt("   Menit (0-59) : "); } while (m < 0 || m > 59);
                        do { d = bacaInt("   Detik (0-59) : "); } while (d < 0 || d > 59);
                        pulang4.setWaktu(j, m, d);

                        if (pulang4.totalDetik() <= datang4.totalDetik()) {
                            cout << "Waktu pulang harus setelah waktu datang, ulangi!\n";
                        }
                    } while (pulang4.totalDetik() <= datang4.totalDetik());

                    if (p4) delete p4;
                    p4 = new Pegawai();
                    p4->setNip(nip);
                    p4->setNama(nama);
                    p4->setGol(gol);
                    p4->setDatang(datang4);
                    p4->setPulang(pulang4);
                    p4->prosesGaji();
                    cout << "Pegawai 4 berhasil diisi (Scanner/cin luar class).\n";
                    break;
                }
                case 5: {
                    if (!p1 && !p2 && !p3 && !p4) {
                        cout << "Belum ada data pegawai!\n";
                        break;
                    }
                    string garis(127, '-');
                    cout << "\n                              Daftar Gaji Harian PT Informatika\n";
                    cout << garis << "\n";
                    cout << left << setw(3)  << "No"
                         << left << setw(7)  << "NIP"
                         << left << setw(13) << "Nama"
                         << left << setw(5)  << "Gol"
                         << left << setw(10) << "Datang"
                         << left << setw(10) << "Pulang"
                         << left << setw(10) << "Lama"
                         << left << setw(12) << "Jam Lembur"
                         << left << setw(13) << "Gaji Harian"
                         << left << setw(10) << "Lembur"
                         << left << setw(10) << "Total"
                         << "Status\n";
                    cout << garis << "\n";
                    if (p1) p1->printPegawai(1);
                    if (p2) p2->printPegawai(2);
                    if (p3) p3->printPegawai(3);
                    if (p4) p4->printPegawai(4);
                    cout << garis << "\n";
                    break;
                }
                case 0:
                    cout << "Terima kasih!\n";
                    break;
                default:
                    cout << "Menu tidak tersedia!\n";
            }
        } while (pilih != 0);

        // Memory cleanup
        delete p1; delete p2; delete p3; delete p4;
    }
};

int main() {
    Menu menu;
    menu.tampilMenu();
    return 0;
}