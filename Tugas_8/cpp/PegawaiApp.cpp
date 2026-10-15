/*
Nama Program : PegawaiApp.cpp
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 15-10-2026
Deskripsi Program : Sebuah program untukmenghitung gaji karyawan dengan input NIP, nama, gol, waktu datang, waktu pulang.
                    Dengan perhitungan Gaji Lembur = >= 8 jam (minimal kelebihan 1 jam / pembulatan ke bawah) dan
                    untuk pegawai yg kurang dari 8 jam diberi status peringatan. Ddengan aturan Gaji = gapok + lembur secara OOP.
*/

#include <iostream>
#include <string>
#include <vector>
#include <iomanip>
#include <cmath>

using namespace std;

class PegawaiAppHelper {
public:
    static int bacaInt(string pesan) {
        int val;
        while (true) {
            cout << pesan;
            if (cin >> val) {
                string s;
                getline(cin, s); 
                return val;
            } else {
                cout << "Masukkan angka yang valid!\n";
                cin.clear();
                string dummy;
                getline(cin, dummy);
            }
        }
    }
};

class Waktu {
private:
    int jam;
    int menit;
    int detik;

public:
    Waktu() {
        this->jam = 0;
        this->menit = 0;
        this->detik = 0;
    }

    Waktu(int jam, int menit, int detik) {
        this->jam = jam;
        this->menit = menit;
        this->detik = detik;
    }

    void setWaktu(int jam, int menit, int detik) {
        this->jam = jam;
        this->menit = menit;
        this->detik = detik;
    }

    int getJam() { 
        return this->jam; 
    }

    int getMenit() { 
        return this->menit; 
    }

    int getDetik() { 
        return this->detik; 
    }

    void inputDalam() {
        do {
            jam = PegawaiAppHelper::bacaInt("   Jam   (0-23) : ");
        } while (jam < 0 || jam > 23);

        do {
            menit = PegawaiAppHelper::bacaInt("   Menit (0-59) : ");
        } while (menit < 0 || menit > 59);

        do {
            detik = PegawaiAppHelper::bacaInt("   Detik (0-59) : ");
        } while (detik < 0 || detik > 59);
    }

    int totalDetik() {
        return (this->jam * 3600) + (this->menit * 60) + this->detik;
    }

    Waktu hitungSelisihFungsi(Waktu w2) {
        int selisihTotal = abs(this->totalDetik() - w2.totalDetik());

        Waktu hasil;
        hasil.jam = selisihTotal / 3600;
        hasil.menit = (selisihTotal % 3600) / 60;
        hasil.detik = selisihTotal % 60;

        return hasil;
    }

    void hitungSelisihVoid(Waktu w1, Waktu w2) {
        int selisihTotal = abs(w1.totalDetik() - w2.totalDetik());

        this->jam = selisihTotal / 3600;
        this->menit = (selisihTotal % 3600) / 60;
        this->detik = selisihTotal % 60;
    }

    string toStringFormat() {
        char buf[10];
        snprintf(buf, sizeof(buf), "%02d:%02d:%02d", jam, menit, detik);
        return string(buf);
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

public:
    Pegawai() {
        nip = "";
        nama = "";
        gol = 0;
        gajiHarian = 0;
        lembur = 0;
        total = 0;
        statusPeringatan = "";
    }

    Pegawai(string nip, string nama, int gol, Waktu datang, Waktu pulang) {
        this->nip = nip;
        this->nama = nama;
        this->gol = gol;
        this->datang = datang;
        this->pulang = pulang;
        gajiHarian = 0;
        lembur = 0;
        total = 0;
    }

    void inputPegawai() {
        cout << "Masukkan NIP  : ";
        getline(cin, nip);
        cout << "Masukkan Nama : ";
        getline(cin, nama);
        do {
            gol = PegawaiAppHelper::bacaInt("Masukkan Gol (1-4) : ");
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

    void setPegawai(string nip, string nama, int gol, Waktu datang, Waktu pulang) {
        this->nip = nip;
        this->nama = nama;
        this->gol = gol;
        this->datang = datang;
        this->pulang = pulang;
    }

    void setNip(string nip) { 
        this->nip = nip; 
    }

    void setNama(string nama) { 
        this->nama = nama; 
    }

    void setGol(int gol) { 
        this->gol = gol; 
    }

    void setDatang(Waktu datang) { 
        this->datang = datang; 
    }

    void setPulang(Waktu pulang) { 
        this->pulang = pulang; 
    }

    string getNip() { 
        return nip; 
    }

    string getNama() { 
        return nama; 
    }

    int getGol() { 
        return gol; 
    }

    int getTotal() { 
        return total; 
    }

    string getStatusPeringatan() { 
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

    void printPegawai(int no) {
        cout << left << setw(3) << no 
             << setw(7) << nip 
             << setw(13) << nama 
             << setw(5) << gol 
             << setw(10) << datang.toStringFormat() 
             << setw(10) << pulang.toStringFormat() 
             << setw(10) << lamaKerja.toStringFormat() 
             << setw(12) << jamLembur.toStringFormat() 
             << setw(13) << rupiah(gajiHarian) 
             << setw(10) << rupiah(lembur) 
             << setw(10) << rupiah(total) 
             << statusPeringatan << "\n";
    }

private:
    string rupiah(int nilai) {
        string s = to_string(nilai);
        int n = s.length();
        for (int i = n - 3; i > 0; i -= 3) {
            s.insert(i, ".");
        }
        return s;
    }
};

class ArrayPegawai {
private:
    vector<Pegawai> pegawai;
    int ukuran;
    int jumlah;

public:
    ArrayPegawai(int ukuran = 10) {
        this->ukuran = ukuran;
        this->jumlah = 0;
        pegawai.resize(ukuran);
    }

    int getUkuran() { 
        return ukuran; 
    }

    int getJumlah() { 
        return jumlah; 
    }

    bool penuh() {
        if (jumlah >= ukuran) {
            cout << "Data penuh! Maksimal " << ukuran << " pegawai.\n";
            return true;
        }
        return false;
    }

    bool nipSudahAda(string nip) {
        for (int i = 0; i < jumlah; i++) {
            if (pegawai[i].getNip() == nip) {
                return true;
            }
        }
        return false;
    }

    void simpan(string cara) {
        string nip = pegawai[jumlah].getNip();
        if (nipSudahAda(nip)) {
            cout << "NIP " << nip << " sudah terdaftar, data tidak disimpan!\n";
            pegawai[jumlah] = Pegawai();
            return;
        }
        jumlah++;
        cout << "Pegawai ke-" << jumlah << " berhasil ditambahkan (" << cara << ").\n";
    }

    void tambahViaSetter() {
        if (penuh()) return;

        Waktu datang(8, 0, 0);
        Waktu pulang(17, 15, 10);
        pegawai[jumlah].setNip("001");
        pegawai[jumlah].setNama("Ali");
        pegawai[jumlah].setGol(3);
        pegawai[jumlah].setDatang(datang);
        pegawai[jumlah].setPulang(pulang);
        simpan("setter");
    }

    void tambahViaConstructor() {
        if (penuh()) return;

        pegawai[jumlah] = Pegawai("004", "ida", 2, Waktu(7, 30, 0), Waktu(18, 0, 0));
        simpan("constructor");
    }

    void tambahInputDalam() {
        if (penuh()) return;

        cout << "\nPegawai ke-" << (jumlah + 1) << "\n";
        pegawai[jumlah].inputPegawai();
        simpan("Scanner dalam class");
    }

    void tambahInputLuar() {
        if (penuh()) return;

        cout << "\nPegawai ke-" << (jumlah + 1) << "\n";
        cout << "Masukkan NIP  : ";
        string nip;
        getline(cin, nip);
        if (nipSudahAda(nip)) {
            cout << "NIP " << nip << " sudah terdaftar, data tidak disimpan!\n";
            return;
        }
        cout << "Masukkan Nama : ";
        string nama;
        getline(cin, nama);

        int gol;
        do {
            gol = PegawaiAppHelper::bacaInt("Masukkan Gol (1-4) : ");
        } while (gol < 1 || gol > 4);

        Waktu datang;
        Waktu pulang;

        do {
            cout << "Waktu Datang :\n";
            int j, m, d;
            do { j = PegawaiAppHelper::bacaInt("   Jam   (0-23) : "); } while (j < 0 || j > 23);
            do { m = PegawaiAppHelper::bacaInt("   Menit (0-59) : "); } while (m < 0 || m > 59);
            do { d = PegawaiAppHelper::bacaInt("   Detik (0-59) : "); } while (d < 0 || d > 59);
            datang.setWaktu(j, m, d);

            cout << "Waktu Pulang :\n";
            do { j = PegawaiAppHelper::bacaInt("   Jam   (0-23) : "); } while (j < 0 || j > 23);
            do { m = PegawaiAppHelper::bacaInt("   Menit (0-59) : "); } while (m < 0 || m > 59);
            do { d = PegawaiAppHelper::bacaInt("   Detik (0-59) : "); } while (d < 0 || d > 59);
            pulang.setWaktu(j, m, d);

            if (pulang.totalDetik() <= datang.totalDetik()) {
                cout << "Waktu pulang harus setelah waktu datang, ulangi!\n";
            }
        } while (pulang.totalDetik() <= datang.totalDetik());

        pegawai[jumlah].setNip(nip);
        pegawai[jumlah].setNama(nama);
        pegawai[jumlah].setGol(gol);
        pegawai[jumlah].setDatang(datang);
        pegawai[jumlah].setPulang(pulang);
        simpan("Scanner luar class");
    }

    void prosesSemua() {
        for (int i = 0; i < jumlah; i++) {
            pegawai[i].prosesGaji();
        }
    }

    void tampilDaftar() {
        if (jumlah == 0) {
            cout << "Belum ada data pegawai!\n";
            return;
        }
        string garis(127, '-');
        cout << "\n                              Daftar Gaji Harian PT Informatika\n";
        cout << garis << "\n";
        cout << left << setw(3) << "No" 
             << setw(7) << "NIP" 
             << setw(13) << "Nama" 
             << setw(5) << "Gol" 
             << setw(10) << "Datang" 
             << setw(10) << "Pulang" 
             << setw(10) << "Lama" 
             << setw(12) << "Jam Lembur" 
             << setw(13) << "Gaji Harian" 
             << setw(10) << "Lembur" 
             << setw(10) << "Total" 
             << "Status\n";
        cout << garis << "\n";
        for (int i = 0; i < jumlah; i++) {
            pegawai[i].printPegawai(i + 1);
        }
        cout << garis << "\n";
    }
};

class Menu {
private:
    ArrayPegawai *larik;
    int pilih;

public:
    Menu(ArrayPegawai *larik) {
        this->larik = larik;
        this->pilih = -1;
    }

    void tampilMenu() {
        cout << "\nMENU GAJI HARIAN PT INFORMATIKA\n";
        cout << "Jumlah pegawai : " << larik->getJumlah() << "/" << larik->getUkuran() << "\n";
        cout << " 1. Tambah pegawai : via setter(hardcode)\n";
        cout << " 2. Tambah pegawai : via constructor berparameter(hardcode)\n";
        cout << " 3. Tambah pegawai : input dalam class\n";
        cout << " 4. Tambah pegawai : input luar class\n";
        cout << " 5. Tampilkan daftar gaji harian\n";
        cout << " 0. Keluar\n";
    }

    void inputPilihan() {
        pilih = PegawaiAppHelper::bacaInt("Pilih menu: ");
    }

    void prosesPilihan() {
        switch (pilih) {
            case 1:
                larik->tambahViaSetter();
                break;
            case 2:
                larik->tambahViaConstructor();
                break;
            case 3:
                larik->tambahInputDalam();
                break;
            case 4:
                larik->tambahInputLuar();
                break;
            case 5:
                larik->prosesSemua();
                larik->tampilDaftar();
                break;
            case 0:
                cout << "Terima kasih!\n";
                break;
            default:
                cout << "Menu tidak tersedia!\n";
        }
    }

    void jalankan() {
        do {
            tampilMenu();
            inputPilihan();
            prosesPilihan();
        } while (pilih != 0);
    }
};

int main() {
    const int MAKS = 10;
    ArrayPegawai larik(MAKS);
    Menu menu(&larik);
    menu.jalankan();
    return 0;
}