/*
Nama Program : Pegawai.cpp
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 29-09-2026
Deskripsi Program : Sebuah program untukmenghitung gaji karyawan dengan input NIP, nama, gol, waktu datang, waktu pulang.
                    Dengan perhitungan Gaji Lembur = >= 8 jam (minimal kelebihan 1 jam / pembulatan ke bawah) dan
                    untuk pegawai yg kurang dari 8 jam diberi status peringatan. Ddengan aturan Gaji = gapok + lembur secara OOP.
*/

#include <iostream>
#include <iomanip>
#include <string>
#include <sstream>
#include <cstdlib>

//validasi
int bacaInt(std::string pesan) {
    std::string s;
    while (true) {
        std::cout << pesan;
        if (!std::getline(std::cin, s)) {   // input habis (Ctrl+D / Ctrl+Z)
            std::cout << "\nInput berakhir.\n";
            std::exit(0);
        }
        try {
            size_t pos;
            int nilai = std::stoi(s, &pos);
            // sisa karakter setelah angka hanya boleh spasi
            if (s.find_first_not_of(" \t", pos) == std::string::npos) return nilai;
        } catch (...) {}
        std::cout << "Masukkan angka yang valid!\n";
    }
}

class Waktu {
private:
    int jam;
    int menit;
    int detik;

public:
    Waktu() {
        jam = 0;
        menit = 0;
        detik = 0;
    }

    // constructor + validasi
    Waktu(int jam, int menit, int detik) {
        this->jam = (jam >= 0 && jam <= 23) ? jam : 0;
        this->menit = (menit >= 0 && menit <= 59) ? menit : 0;
        this->detik = (detik >= 0 && detik <= 59) ? detik : 0;
    }

    // input dalam + validasi
    void inputWaktu() {
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

    // Setter + validasi
    void setWaktu(int jam, int menit, int detik) {
        this->jam = (jam >= 0 && jam <= 23) ? jam : 0;
        this->menit = (menit >= 0 && menit <= 59) ? menit : 0;
        this->detik = (detik >= 0 && detik <= 59) ? detik : 0;
    }

    void setJam(int jam) {
        this->jam = (jam >= 0 && jam <= 23) ? jam : 0;
    }
    void setMenit(int menit) {
        this->menit = (menit >= 0 && menit <= 59) ? menit : 0;
    }
    void setDetik(int detik) {
        this->detik = (detik >= 0 && detik <= 59) ? detik : 0;
    }

    // getter
    int getJam() {
        return jam;
    }
    int getMenit() {
        return menit;
    }
    int getDetik() {
        return detik;
    }

    // proses
    int totalDetik() {
        return jam * 3600 + menit * 60 + detik;
    }

    // cara 2 fungsi
    Waktu selisihFungsi(Waktu P) {
        Waktu pHasil;
        int sel = this->totalDetik() - P.totalDetik();
        if (sel < 0) sel = 0; // mencegah nilai minus
        pHasil.jam = sel / 3600;
        pHasil.menit = (sel % 3600) / 60;
        pHasil.detik = sel % 60;
        return pHasil;
    }

    //  cara 1 void
    void selisihVoid(Waktu P1, Waktu P2) {
        int sel = P1.totalDetik() - P2.totalDetik();
        if (sel < 0) sel = 0;
        this->jam = sel / 3600;
        this->menit = (sel % 3600) / 60;
        this->detik = sel % 60;
    }

    // output
    std::string toString() {
        std::ostringstream oss;
        oss << std::setfill('0')
            << std::setw(2) << jam << ":"
            << std::setw(2) << menit << ":"
            << std::setw(2) << detik;
        return oss.str();
    }

    void printWaktu() {
        std::cout << " Waktu = " << toString() << "\n";
    }
};

class Pegawai {
private:
    std::string nip;
    std::string nama;
    int gol;
    Waktu datang;
    Waktu pulang;
    Waktu lamaKerja;
    Waktu jamLembur;
    int gajiHarian;
    int lembur;
    int total;
    std::string statusPeringatan;

    std::string rupiah(int nilai) {
        std::string s = std::to_string(nilai);
        for (int i = (int)s.length() - 3; i > 0; i -= 3) {
            s.insert(i, ".");
        }
        return s;
    }

public:
    Pegawai() {
        nip = "";
        nama = "";
        gol = 0;
        datang = Waktu();
        pulang = Waktu();
        lamaKerja = Waktu();
        jamLembur = Waktu();
        gajiHarian = 0;
        lembur = 0;
        total = 0;
        statusPeringatan = "";
    }

    // memanggil constructor kosong dulu (setara this() di Java)
    Pegawai(std::string nip, std::string nama, int gol, Waktu datang, Waktu pulang) : Pegawai() {
        this->nip = nip;
        this->nama = nama;
        this->gol = gol;
        this->datang = datang;
        this->pulang = pulang;
    }

    // input dalam
    void inputPegawai() {
        std::cout << "Masukkan NIP  : ";
        std::getline(std::cin, nip);
        std::cout << "Masukkan Nama : ";
        std::getline(std::cin, nama);
        do {
            gol = bacaInt("Masukkan Gol (1-4) : ");
        } while (gol < 1 || gol > 4);

        do {
            std::cout << "Waktu Datang :\n";
            datang.inputWaktu();
            std::cout << "Waktu Pulang :\n";
            pulang.inputWaktu();
            if (pulang.totalDetik() <= datang.totalDetik())
                std::cout << "Waktu pulang harus setelah waktu datang, ulangi!\n";
        } while (pulang.totalDetik() <= datang.totalDetik());
    }

    // setter & getter
    void setPegawai(std::string nip, std::string nama, int gol, Waktu datang, Waktu pulang) {
        this->nip = nip;
        this->nama = nama;
        this->gol = gol;
        this->datang = datang;
        this->pulang = pulang;
    }

    void setNip(std::string nip) {
        this->nip = nip;
    }
    void setNama(std::string nama) {
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

    std::string getNip() {
        return nip;
    }
    std::string getNama() {
        return nama;
    }
    int getGol() {
        return gol;
    }
    int getTotal() {
        return total;
    }
    std::string getStatusPeringatan() {
        return statusPeringatan;
    }


    // proses
    void prosesGaji() {
        // menggunakan cara 2 (fungsi) untuk menghitung lama kerja
        lamaKerja = pulang.selisihFungsi(datang);

        Waktu batas(8, 0, 0);
        if (lamaKerja.totalDetik() >= batas.totalDetik()) {
            // menggunakan cara 1 (void) untuk menghitung jam lembur
            jamLembur.selisihVoid(lamaKerja, batas);
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

    // output
    void printPegawai(int no) {
        std::cout << std::left
                  << std::setw(3) << no << " "
                  << std::setw(6) << nip << " "
                  << std::setw(12) << nama << " "
                  << std::setw(4) << gol << " "
                  << std::setw(9) << datang.toString() << " "
                  << std::setw(9) << pulang.toString() << " "
                  << std::setw(9) << lamaKerja.toString() << " "
                  << std::setw(11) << jamLembur.toString() << " "
                  << std::setw(12) << rupiah(gajiHarian) << " "
                  << std::setw(9) << rupiah(lembur) << " "
                  << std::setw(9) << rupiah(total) << " "
                  << statusPeringatan << "\n";
    }
};

int main() {
    int pilih;

    Pegawai *p1 = nullptr, *p2 = nullptr, *p3 = nullptr, *p4 = nullptr;

    do {
        std::cout << "\n";
        std::cout << "MENU GAJI HARIAN PT INFORMATIKA\n";
        std::cout << " 1. Pegawai 1 : via setter(hardcode)\n";
        std::cout << " 2. Pegawai 2 : via constructor berparameter(hardcode)\n";
        std::cout << " 3. Pegawai 3 : input dalam class\n";
        std::cout << " 4. Pegawai 4 : input luar class (Main)\n";
        std::cout << " 5. Tampilkan daftar gaji harian\n";
        std::cout << " 0. Keluar\n";
        pilih = bacaInt("Pilih menu: ");

        switch (pilih) {
            //via setter
            case 1: {
                delete p1;
                p1 = new Pegawai();
                Waktu datang1(8, 0, 0);
                Waktu pulang1(17, 15, 10);
                p1->setNip("250001");
                p1->setNama("Ali");
                p1->setGol(3);
                p1->setDatang(datang1);
                p1->setPulang(pulang1);
                p1->prosesGaji();
                std::cout << "Pegawai 1 berhasil diisi (setter).\n";
                break;
            }
            //cons parameter
            case 2: {
                delete p2;
                p2 = new Pegawai("250002", "Budi", 1, Waktu(8, 0, 0), Waktu(15, 30, 0));
                p2->prosesGaji();
                std::cout << "Pegawai 2 berhasil diisi (constructor).\n";
                break;
            }
            case 3: {
                std::cout << "\nPegawai 3\n";
                delete p3;
                p3 = new Pegawai();
                p3->inputPegawai();
                p3->prosesGaji();
                std::cout << "Pegawai 3 berhasil diisi (Scanner dalam class).\n";
                break;
            }
            case 4: {
                std::cout << "\nPegawai 4\n";
                std::cout << "Masukkan NIP  : ";
                std::string nip;
                std::getline(std::cin, nip);
                std::cout << "Masukkan Nama : ";
                std::string nama;
                std::getline(std::cin, nama);

                int gol;
                do {
                    gol = bacaInt("Masukkan Gol (1-4) : ");
                } while (gol < 1 || gol > 4);

                Waktu datang4;
                Waktu pulang4;

                // loop validasi input luar agar pulang > datang
                do {
                    std::cout << "Waktu Datang :\n";
                    int j, m, d;
                    do { j = bacaInt("   Jam   (0-23) : "); } while (j < 0 || j > 23);
                    do { m = bacaInt("   Menit (0-59) : "); } while (m < 0 || m > 59);
                    do { d = bacaInt("   Detik (0-59) : "); } while (d < 0 || d > 59);
                    datang4.setWaktu(j, m, d);

                    std::cout << "Waktu Pulang :\n";
                    do { j = bacaInt("   Jam   (0-23) : "); } while (j < 0 || j > 23);
                    do { m = bacaInt("   Menit (0-59) : "); } while (m < 0 || m > 59);
                    do { d = bacaInt("   Detik (0-59) : "); } while (d < 0 || d > 59);
                    pulang4.setWaktu(j, m, d);

                    if (pulang4.totalDetik() <= datang4.totalDetik()) {
                        std::cout << "Waktu pulang harus setelah waktu datang, ulangi!\n";
                    }
                } while (pulang4.totalDetik() <= datang4.totalDetik());

                delete p4;
                p4 = new Pegawai();
                p4->setNip(nip);
                p4->setNama(nama);
                p4->setGol(gol);
                p4->setDatang(datang4);
                p4->setPulang(pulang4);
                p4->prosesGaji();
                std::cout << "Pegawai 4 berhasil diisi (Scanner luar class).\n";
                break;
            }
            case 5: {
                if (p1 == nullptr && p2 == nullptr && p3 == nullptr && p4 == nullptr) {
                    std::cout << "Belum ada data pegawai!\n";
                    break;
                }
                std::string garis(127, '-');
                std::cout << "\n";
                std::cout << "                              Daftar Gaji Harian PT Informatika\n";
                std::cout << garis << "\n";
                std::cout << std::left
                          << std::setw(3) << "No" << " "
                          << std::setw(6) << "NIP" << " "
                          << std::setw(12) << "Nama" << " "
                          << std::setw(4) << "Gol" << " "
                          << std::setw(9) << "Datang" << " "
                          << std::setw(9) << "Pulang" << " "
                          << std::setw(9) << "Lama" << " "
                          << std::setw(11) << "Jam Lembur" << " "
                          << std::setw(12) << "Gaji Harian" << " "
                          << std::setw(9) << "Lembur" << " "
                          << std::setw(9) << "Total" << " "
                          << "Status\n";
                std::cout << garis << "\n";
                if (p1 != nullptr) p1->printPegawai(1);
                if (p2 != nullptr) p2->printPegawai(2);
                if (p3 != nullptr) p3->printPegawai(3);
                if (p4 != nullptr) p4->printPegawai(4);
                std::cout << garis << "\n";
                break;
            }
            case 0:
                std::cout << "Terima kasih!\n";
                break;

            default:
                std::cout << "Menu tidak tersedia!\n";
        }
    } while (pilih != 0);

    // bersihkan memori dinamis
    delete p1;
    delete p2;
    delete p3;
    delete p4;

    return 0;
}