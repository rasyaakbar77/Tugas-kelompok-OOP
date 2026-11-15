/*
Nama Program : Pegawai.cpp
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 28-10-2026
Deskripsi Program : Program perhitungan gaji pegawai dan sistem parkir sederhana 
                    menggunakan inheritance OOP C++.
*/

#include <iostream>
#include <string>
#include <iomanip>
#include <sstream>
#include <limits>
#include <cmath>

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
};

class Waktu {
private:
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

    void setWaktu(int j, int m, int d) { jam = j; menit = m; detik = d; }
    int getJam() const { return jam; }
    int getMenit() const { return menit; }
    int getDetik() const { return detik; }

    void inputDalam() {
        do { jam = Util::bacaInt("   Jam   (0-23) : "); } while (jam < 0 || jam > 23);
        do { menit = Util::bacaInt("   Menit (0-59) : "); } while (menit < 0 || menit > 59);
        do { detik = Util::bacaInt("   Detik (0-59) : "); } while (detik < 0 || detik > 59);
    }

    int totalDetik() const {
        return (jam * 3600) + (menit * 60) + detik;
    }

    Waktu hitungSelisihFungsi(const Waktu& w2) const {
        int selisihTotal = std::abs(this->totalDetik() - w2.totalDetik());
        Waktu hasil;
        hasil.jam = selisihTotal / 3600;
        hasil.menit = (selisihTotal % 3600) / 60;
        hasil.detik = selisihTotal % 60;
        return hasil;
    }

    void hitungSelisihVoid(const Waktu& w1, const Waktu& w2) {
        int selisihTotal = std::abs(w1.totalDetik() - w2.totalDetik());
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
};

// Superclass Person
class Person {
private:
    std::string nama;
    std::string alamat;
public:
    Person() : nama(""), alamat("") {}
    Person(std::string nama, std::string alamat) { this->nama = nama; this->alamat = alamat; }
    
    void inputDalam() {
        std::cout << "Nama   : ";
        std::getline(std::cin, nama);
        std::cout << "Alamat : ";
        std::getline(std::cin, alamat);
    }

    void setNama(std::string name) { nama = name; }
    void setAlamat(std::string address) { alamat = address; }
    std::string getNama() const { return this->nama; }
    std::string getAlamat() const { return this->alamat; }

    void printPerson(int no) const {
        std::cout << std::left << std::setw(3)  << no
                  << std::left << std::setw(13) << nama
                  << std::left << std::setw(17) << alamat;
    }
};

// Subclass Pegawai
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

    void inputPegawai() {
        std::cout << "NIP     : ";
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

    void setNip(const std::string& n) { nip = n; }
    std::string getNip() const { return nip; }
    int getGol() const { return gol; }

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
        return getTarifLembur() * jamLembur.totalDetik() / 3600;
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

    void printPegawai(int no) const {
        printPerson(no); 
        std::cout << std::left << std::setw(12) << nip
                  << std::right << std::setw(5)  << gol
                  << std::setw(10) << datang.toString()
                  << std::setw(10) << pulang.toString()
                  << std::setw(12) << lamaKerja.toString()
                  << std::setw(12) << jamLembur.toString()
                  << std::setw(15) << Util::rupiah(getGajiHarian())
                  << std::setw(15) << Util::rupiah(getLembur())
                  << std::setw(15) << Util::rupiah(getTotal())
                  << std::left  << "  " << std::setw(12) << statusPeringatan 
                  << "\n";
    }
};

class ArrayPegawai {
private:
    static const int max = 100;
    Pegawai arr[max];
    int jumlah;
public:
    ArrayPegawai() : jumlah(0) {}

    bool isNipExist(const std::string& nip) const {
        for (int i = 0; i < jumlah; i++) {
            if (arr[i].getNip() == nip) return true;
        }
        return false;
    }

    int getJumlah() const { return jumlah; }

    void inputDalam() {
        if (jumlah >= max) {
            std::cout << "\nKapasitas pegawai penuh!\n";
            return;
        }
        int tambahan;
        int sisa = max - jumlah;
        do {
            tambahan = Util::bacaInt("Jumlah pegawai yang ditambahkan (1-" + std::to_string(sisa) + "): ");
        } while (tambahan < 1 || tambahan > sisa);

        for (int i = 0; i < tambahan; i++) {
            std::cout << "\nPegawai ke-" << (jumlah + 1) << "\n";
            Pegawai pTemp;
            pTemp.inputPegawai();
            if (isNipExist(pTemp.getNip())) {
                std::cout << "NIP sudah terdaftar! Diulang.\n";
                i--; continue;
            }
            arr[jumlah] = pTemp;
            jumlah++;
        }
    }

    void prosesData() {
        for (int i = 0; i < jumlah; i++) arr[i].prosesGaji();
    }

    Pegawai getPegawaiAt(int index) const {
        if (index >= 0 && index < jumlah) return arr[index];
        return Pegawai();
    }

    void outputData() const {
        if (jumlah == 0) { std::cout << "\nBelum ada data pegawai.\n"; return; }
        std::cout << "\n\t\t\t\t\tDaftar Gaji Pegawai\n";
        std::cout << std::string(150, '=') << "\n";
        for (int i = 0; i < jumlah; i++) {
            arr[i].printPegawai(i + 1);
        }
        std::cout << std::string(150, '=') << "\n";
    }
};

// ==========================================
// SUPERCLASS KENDARAAN
// ==========================================
class Kendaraan {
protected:
    std::string jenis; // "Motor", "Mobil", atau "Truk"
    std::string merk;
    std::string plat;
    std::string warna;
    std::string nipPemilik;
    Waktu masuk;
    Waktu keluar;
    int tambahHari;
    int biaya;
    bool sudahKeluar;
public:
    Kendaraan() : jenis(""), merk(""), plat(""), warna(""), nipPemilik(""), tambahHari(0), biaya(0), sudahKeluar(false) {}

    void setJenis(const std::string& j) { jenis = j; }
    void setMerk(const std::string& m) { merk = m; }
    void setPlat(const std::string& p) { plat = p; }
    void setWarna(const std::string& w) { warna = w; }
    void setNipPemilik(const std::string& nip) { nipPemilik = nip; }
    void setMasuk(const Waktu& w) { masuk = w; }

    std::string getJenis() const { return jenis; }
    std::string getMerk() const { return merk; }
    std::string getPlat() const { return plat; }
    std::string getWarna() const { return warna; }
    std::string getNipPemilik() const { return nipPemilik; }
    Waktu getMasuk() const { return masuk; }
    Waktu getKeluar() const { return keluar; }
    bool isSudahKeluar() const { return sudahKeluar; }
    int getBiaya() const { return biaya; }

    void inputKendaraanUmum() {
        std::cout << "Merk      : ";
        std::getline(std::cin, merk);
        std::cout << "No. Plat  : ";
        std::getline(std::cin, plat);
        std::cout << "Warna     : ";
        std::getline(std::cin, warna);
        std::cout << "Waktu Masuk :\n";
        masuk.inputDalam();
    }

    void prosesKeluarKendaraan(const Waktu& wKel, int hari, const ArrayPegawai& db) {
        keluar = wKel;
        tambahHari = hari;

        long totalDetik = (static_cast<long>(tambahHari) * 86400) + keluar.totalDetik() - masuk.totalDetik();
        if (totalDetik < 0) totalDetik = 0;

        // Cek pegawai valid
        bool validPegawai = false;
        if (!nipPemilik.empty()) {
            for (int i = 0; i < db.getJumlah(); i++) {
                if (db.getPegawaiAt(i).getNip() == nipPemilik) {
                    validPegawai = true;
                    break;
                }
            }
        }

        // Perhitungan biaya sesuai aturan
        if (jenis == "Motor") {
            if (validPegawai) {
                long totalHari = (totalDetik + 86399) / 86400;
                if (totalHari < 1) totalHari = 1;
                biaya = totalHari * 3000;
            } else {
                long totalJam = (totalDetik + 3599) / 3600;
                if (totalJam < 1) totalJam = 1;
                biaya = totalJam * 2000;
            }
        } else if (jenis == "Mobil") {
            if (validPegawai) {
                long totalHari = (totalDetik + 86399) / 86400;
                if (totalHari < 1) totalHari = 1;
                biaya = totalHari * 6000;
            } else {
                long totalJam = (totalDetik + 3599) / 3600;
                if (totalJam < 1) totalJam = 1;
                biaya = totalJam * 3000;
            }
        } else if (jenis == "Truk") {
            // Truk pegawai tetap tarif umum
            long totalJam = (totalDetik + 3599) / 3600;
            if (totalJam < 1) totalJam = 1;
            biaya = totalJam * 5000;
        }
        sudahKeluar = true;
    }
};

// ==========================================
// SUBCLASS MOTOR, MOBIL, TRUK
// ==========================================
class Motor : public Kendaraan {
private:
    int kapasitasMesinCC;
public:
    Motor() : Kendaraan(), kapasitasMesinCC(110) { jenis = "Motor"; }
    void inputSubclass() { kapasitasMesinCC = Util::bacaInt("Kapasitas Mesin (CC) : "); }
    int getCC() const { return kapasitasMesinCC; }
};

class Mobil : public Kendaraan {
private:
    int jumlahPintu;
    bool adaSunroof;
public:
    Mobil() : Kendaraan(), jumlahPintu(4), adaSunroof(false) { jenis = "Mobil"; }
    void inputSubclass() {
        jumlahPintu = Util::bacaInt("Jumlah Pintu : ");
        adaSunroof = Util::bacaInt("Ada Sunroof? (1: Ya, 0: Tidak) : ") == 1;
    }
    int getPintu() const { return jumlahPintu; }
    bool isSunroof() const { return adaSunroof; }
};

class Truk : public Kendaraan {
private:
    double muatanTon;
    bool adaRemAngin;
public:
    Truk() : Kendaraan(), muatanTon(5.0), adaRemAngin(true) { jenis = "Truk"; }
    void inputSubclass() {
        std::cout << "Kapasitas Muatan (Ton) : ";
        std::cin >> muatanTon;
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        adaRemAngin = Util::bacaInt("Ada Rem Angin? (1: Ya, 0: Tidak) : ") == 1;
    }
    double getMuatan() const { return muatanTon; }
    bool isRemAngin() const { return adaRemAngin; }
};

// ==========================================
// ARRAY PARKIR (Menyimpan Objek Langsung, Tanpa Pointer)
// ==========================================
class ArrayParkir {
private:
    static const int max = 100;
    Motor arrMotor[max];
    Mobil arrMobil[max];
    Truk arrTruk[max];
    int jmlMotor, jmlMobil, jmlTruk;
public:
    ArrayParkir() : jmlMotor(0), jmlMobil(0), jmlTruk(0) {}

    bool isPlatAktif(const std::string& plat) const {
        for(int i=0; i<jmlMotor; i++) if(!arrMotor[i].isSudahKeluar() && arrMotor[i].getPlat() == plat) return true;
        for(int i=0; i<jmlMobil; i++) if(!arrMobil[i].isSudahKeluar() && arrMobil[i].getPlat() == plat) return true;
        for(int i=0; i<jmlTruk; i++) if(!arrTruk[i].isSudahKeluar() && arrTruk[i].getPlat() == plat) return true;
        return false;
    }

    void inputMasuk(const ArrayPegawai& dbPegawai) {
        int pilihan;
        do {
            pilihan = Util::bacaInt("\nPilih Jenis Kendaraan (1. Motor, 2. Mobil, 3. Truk): ");
        } while(pilihan < 1 || pilihan > 3);

        std::string platTemp;
        std::cout << "No. Plat : ";
        std::getline(std::cin, platTemp);
        if(isPlatAktif(platTemp)) {
            std::cout << "Error: Plat nomor tersebut masih aktif terparkir!\n";
            return;
        }

        std::string nip;
        std::cout << "NIP Pemilik (kosongkan jika umum): ";
        std::getline(std::cin, nip);

        if(pilihan == 1 && jmlMotor < max) {
            arrMotor[jmlMotor].setPlat(platTemp);
            arrMotor[jmlMotor].setNipPemilik(nip);
            std::cout << "Merk : "; std::string m; std::getline(std::cin, m); arrMotor[jmlMotor].setMerk(m);
            std::cout << "Warna : "; std::string w; std::getline(std::cin, w); arrMotor[jmlMotor].setWarna(w);
            arrMotor[jmlMotor].inputSubclass();
            std::cout << "Waktu Masuk:\n"; Waktu wm; wm.inputDalam(); arrMotor[jmlMotor].setMasuk(wm);
            jmlMotor++;
            std::cout << "Motor berhasil masuk!\n";
        } else if(pilihan == 2 && jmlMobil < max) {
            arrMobil[jmlMobil].setPlat(platTemp);
            arrMobil[jmlMobil].setNipPemilik(nip);
            std::cout << "Merk : "; std::string m; std::getline(std::cin, m); arrMobil[jmlMobil].setMerk(m);
            std::cout << "Warna : "; std::string w; std::getline(std::cin, w); arrMobil[jmlMobil].setWarna(w);
            arrMobil[jmlMobil].inputSubclass();
            std::cout << "Waktu Masuk:\n"; Waktu wm; wm.inputDalam(); arrMobil[jmlMobil].setMasuk(wm);
            jmlMobil++;
            std::cout << "Mobil berhasil masuk!\n";
        } else if(pilihan == 3 && jmlTruk < max) {
            arrTruk[jmlTruk].setPlat(platTemp);
            arrTruk[jmlTruk].setNipPemilik(nip);
            std::cout << "Merk : "; std::string m; std::getline(std::cin, m); arrTruk[jmlTruk].setMerk(m);
            std::cout << "Warna : "; std::string w; std::getline(std::cin, w); arrTruk[jmlTruk].setWarna(w);
            arrTruk[jmlTruk].inputSubclass();
            std::cout << "Waktu Masuk:\n"; Waktu wm; wm.inputDalam(); arrTruk[jmlTruk].setMasuk(wm);
            jmlTruk++;
            std::cout << "Truk berhasil masuk!\n";
        } else {
            std::cout << "Kapasitas parkir penuh!\n";
        }
    }

    void prosesKeluar(const ArrayPegawai& dbPegawai) {
        // Tampilkan yang aktif
        int no = 1;
        std::cout << "\n--- KENDARAAN TERPARKIR ---\n";
        for(int i=0; i<jmlMotor; i++) {
            if(!arrMotor[i].isSudahKeluar()) std::cout << no++ << ". [Motor] Plat: " << arrMotor[i].getPlat() << " (Masuk: " << arrMotor[i].getMasuk().toString() << ")\n";
        }
        for(int i=0; i<jmlMobil; i++) {
            if(!arrMobil[i].isSudahKeluar()) std::cout << no++ << ". [Mobil] Plat: " << arrMobil[i].getPlat() << " (Masuk: " << arrMobil[i].getMasuk().toString() << ")\n";
        }
        for(int i=0; i<jmlTruk; i++) {
            if(!arrTruk[i].isSudahKeluar()) std::cout << no++ << ". [Truk] Plat: " << arrTruk[i].getPlat() << " (Masuk: " << arrTruk[i].getMasuk().toString() << ")\n";
        }

        if(no == 1) {
            std::cout << "Tidak ada kendaraan terparkir.\n";
            return;
        }

        int pilih = Util::bacaInt("Pilih nomor kendaraan keluar: ");
        int curr = 1;

        // Cari dan proses keluar berdasarkan pilihan nomor
        for(int i=0; i<jmlMotor; i++) {
            if(!arrMotor[i].isSudahKeluar()) {
                if(curr == pilih) {
                    Waktu wKel; std::cout << "Waktu Keluar:\n"; wKel.inputDalam();
                    int hari = Util::bacaInt("Lama Menginap Tambahan (Hari 0-30): ");
                    arrMotor[i].prosesKeluarKendaraan(wKel, hari, dbPegawai);
                    std::cout << "Biaya Parkir: Rp " << Util::rupiah(arrMotor[i].getBiaya()) << "\n";
                    return;
                }
                curr++;
            }
        }
        for(int i=0; i<jmlMobil; i++) {
            if(!arrMobil[i].isSudahKeluar()) {
                if(curr == pilih) {
                    Waktu wKel; std::cout << "Waktu Keluar:\n"; wKel.inputDalam();
                    int hari = Util::bacaInt("Lama Menginap Tambahan (Hari 0-30): ");
                    arrMobil[i].prosesKeluarKendaraan(wKel, hari, dbPegawai);
                    std::cout << "Biaya Parkir: Rp " << Util::rupiah(arrMobil[i].getBiaya()) << "\n";
                    return;
                }
                curr++;
            }
        }
        for(int i=0; i<jmlTruk; i++) {
            if(!arrTruk[i].isSudahKeluar()) {
                if(curr == pilih) {
                    Waktu wKel; std::cout << "Waktu Keluar:\n"; wKel.inputDalam();
                    int hari = Util::bacaInt("Lama Menginap Tambahan (Hari 0-30): ");
                    arrTruk[i].prosesKeluarKendaraan(wKel, hari, dbPegawai);
                    std::cout << "Biaya Parkir: Rp " << Util::rupiah(arrTruk[i].getBiaya()) << "\n";
                    return;
                }
                curr++;
            }
        }
        std::cout << "Pilihan tidak valid.\n";
    }

    void laporan() const {
        std::cout << "\n================ LAPORAN PARKIR ================\n";
        long totalPendapatan = 0;

        auto cetakList = [&](const std::string& jenis, auto arr, int jml) {
            for(int i=0; i<jml; i++) {
                std::cout << "[" << jenis << "] Plat: " << arr[i].getPlat() 
                          << " | Status: " << (arr[i].isSudahKeluar() ? "Keluar" : "Terparkir");
                if(arr[i].isSudahKeluar()) {
                    std::cout << " | Biaya: Rp " << Util::rupiah(arr[i].getBiaya());
                    totalPendapatan += arr[i].getBiaya();
                }
                std::cout << "\n";
            }
        };

        cetakList("Motor", arrMotor, jmlMotor);
        cetakList("Mobil", arrMobil, jmlMobil);
        cetakList("Truk", arrTruk, jmlTruk);

        std::cout << "------------------------------------------------\n";
        std::cout << "Total Pendapatan Parkir : Rp " << Util::rupiah(static_cast<int>(totalPendapatan)) << "\n";
    }
};

// ==========================================
// MENU UTAMA
// ==========================================
class Menu {
private:
    ArrayPegawai dataPegawai;
    ArrayParkir dataParkir;
public:
    void jalankan() {
        int pilihan;
        do {
            std::cout << "\n================ MENU UTAMA ================\n";
            std::cout << "1. Input Data Pegawai\n";
            std::cout << "2. Tampilkan Gaji Pegawai\n";
            std::cout << "3. Input Kendaraan Masuk\n";
            std::cout << "4. Proses Kendaraan Keluar\n";
            std::cout << "5. Laporan Parkir\n";
            std::cout << "6. Keluar\n";
            std::cout << "--------------------------------------------\n";
            pilihan = Util::bacaInt("Pilih menu (1-6): ");

            switch (pilihan) {
                case 1: dataPegawai.inputDalam(); break;
                case 2: dataPegawai.prosesData(); dataPegawai.outputData(); break;
                case 3: dataParkir.inputMasuk(dataPegawai); break;
                case 4: dataParkir.prosesKeluar(dataPegawai); break;
                case 5: dataParkir.laporan(); break;
                case 6: std::cout << "Keluar program.\n"; break;
                default: std::cout << "Pilihan tidak valid!\n";
            }
        } while (pilihan != 6);
    }
};

int main() {
    Menu app;
    app.jalankan();
    return 0;
}