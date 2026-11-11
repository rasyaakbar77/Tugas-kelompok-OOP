/*
Nama Program : Pegawai.cpp
Anggota Kelompok - NPM:
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 28-10-2026 (Tugas 11 ditambahkan: 10-11-2026)
Deskripsi Program : Sebuah program untuk menghitung gaji karyawan dengan input NIP, nama, gol, waktu datang, waktu pulang.
                    Menggunakan konsep inheritance dengan membuat superclass Person yang akan di inherit oleh subclass Pegawai.
                    Tugas 11: ditambah program parkir kendaraan dengan superclass Kendaraan yang di-inherit oleh
                    Mobil, Truk, dan Motor (inheritance dan polimorfisme). Data parkir disimpan dalam array.

SPESIFIKASI DAN ATURAN PARKIR
1. Jenis kendaraan : Mobil, Truk, Motor. Tiap jenis meng-override hitungBiaya() (polimorfisme).
2. Tarif umum (person atau tanpa identitas, per jam) : Mobil 3.000, Truk 5.000, Motor 2.000.
3. Tarif pegawai (per hari, <= 24 jam) : Mobil 6.000, Motor 3.000. Truk milik pegawai tetap tarif umum.
4. Pegawai dikenali dari NIP yang sudah terdaftar di data pegawai. NIP kosong atau tidak ditemukan = umum.
5. Tarif per jam : durasi dibulatkan ke atas per jam, minimal 1 jam.
6. Tarif per hari : tiap 24 jam dihitung 1 hari (dibulatkan ke atas), minimal 1 hari.
7. Plat nomor tidak boleh sama dengan kendaraan yang masih terparkir.
8. NIP pegawai harus unik.
9. Waktu keluar harus setelah waktu masuk. Lama menginap (0 - 30 hari) diisi saat keluar.
10. Data yang sudah diinput tidak ditimpa. Input pegawai selalu menambah data di belakang data lama.
11. Kapasitas : 100 pegawai dan 100 catatan parkir. Jika penuh, input ditolak.
12. Biaya baru dihitung dan dikunci saat kendaraan keluar. Pendapatan hanya dari kendaraan yang sudah keluar.
*/


#include <iostream>
#include <vector>
#include <string>
#include <iomanip>
#include <sstream>
#include <limits>
#include <cstdlib>
#include <cctype>

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

    static std::string bacaString(const std::string& pesan, bool wajib = true) {
        std::string s;
        while (true) {
            std::cout << pesan;
            std::getline(std::cin, s);
            size_t awal = s.find_first_not_of(" \t");
            if (awal == std::string::npos) {
                s = "";
            } else {
                s = s.substr(awal, s.find_last_not_of(" \t") - awal + 1);
            }
            if (!wajib || !s.empty()) return s;
            std::cout << "Tidak boleh kosong!\n";
        }
    }

    static std::string kapital(std::string s) {
        for (size_t i = 0; i < s.length(); i++) {
            s[i] = static_cast<char>(std::toupper(static_cast<unsigned char>(s[i])));
        }
        return s;
    }

    static std::string rupiah(int nilai) {
        std::string s = std::to_string(nilai);
        int n = s.length();
        for (int i = n - 3; i > 0; i -= 3) {
            s.insert(i, ".");
        }
        return s;
    }

    // rata: '<' kiri, '>' kanan, '^' tengah. Teks yang kepanjangan dipotong.
    static std::string pad(const std::string& s, int w, char rata) {
        std::string t = s.substr(0, w);
        int sisa = w - static_cast<int>(t.length());
        if (rata == '<') return t + std::string(sisa, ' ');
        if (rata == '>') return std::string(sisa, ' ') + t;
        int kiri = sisa / 2;
        return std::string(kiri, ' ') + t + std::string(sisa - kiri, ' ');
    }

    static std::string garis(const std::vector<int>& lebar) {
        std::string g = "+";
        for (size_t i = 0; i < lebar.size(); i++) {
            g += std::string(lebar[i] + 2, '-') + "+";
        }
        return g;
    }

    static std::string baris(const std::vector<std::string>& cells, const std::vector<int>& lebar, const std::string& rata) {
        std::string b = "|";
        for (size_t i = 0; i < lebar.size(); i++) {
            b += " " + pad(cells[i], lebar[i], rata[i]) + " |";
        }
        return b;
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

    void outputDalam() const {
        std::cout << "Waktu = " << toString() << "\n";
    }
};

class Person {
protected:
    std::string nama;
    std::string alamat;
public:
    Person() : nama(""), alamat("") {}

    Person(const std::string& nama, const std::string& alamat) {
        this->nama = nama;
        this->alamat = alamat;
    }

    void inputDalam() {
        std::cout << "Nama   : ";
        std::getline(std::cin, nama);
        std::cout << "Alamat : ";
        std::getline(std::cin, alamat);
    }

    void setNama(const std::string& name) {
        nama = name;
    }

    void setAlamat(const std::string& address) {
        alamat = address;
    }

    std::string getNama() const {
        return this->nama;
    }

    std::string getAlamat() const {
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

    Pegawai(const std::string& nip, const std::string& nama, const std::string& alamat, int gol) : Person(nama, alamat) {
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


const std::vector<std::string> HEADER_GAJI = {"No", "Nama", "Alamat", "NIP", "Gol", "Datang", "Pulang",
                                              "Lama", "Jam Lembur", "Gaji Harian", "Lembur", "Total", "Status"};
const std::vector<int> LEBAR_GAJI = {3, 10, 14, 8, 3, 8, 8, 8, 10, 11, 11, 11, 10};
const std::string RATA_GAJI = "^<<<^^^^^>>>^";

class ArrayPegawai {
private:
    static const int max = 100;
    Pegawai arr[max];
    int jumlah;

    int cariNip(const std::string& nip, int batas) const {
        for (int i = 0; i < batas; i++) {
            if (arr[i].getNip() == nip) return i;
        }
        return -1;
    }

public:
    ArrayPegawai() : jumlah(0) {}

    ArrayPegawai(const Pegawai dataPegawai[], int jml) {
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

    // Cari index pegawai berdasarkan NIP, -1 kalau tidak ada.
    int cariNip(const std::string& nip) const {
        return cariNip(nip, jumlah);
    }

    // Data baru ditambahkan di belakang data lama, data lama tidak ditimpa.
    void inputDalam() {
        int sisa = max - jumlah;
        if (sisa <= 0) {
            std::cout << "Data pegawai penuh (maksimal " << max << ")!\n";
            return;
        }

        int tambah;
        do {
            tambah = Util::bacaInt("Masukkan jumlah pegawai yang ditambahkan (1-" + std::to_string(sisa) + "): ");
        } while (tambah < 1 || tambah > sisa);

        for (int i = 0; i < tambah; i++) {
            int idx = jumlah;
            std::cout << "\nData Pegawai ke-" << (idx + 1) << "\n";
            bool kembar;
            do {
                arr[idx].inputPegawai();
                kembar = (cariNip(arr[idx].getNip(), idx) != -1);
                if (kembar) std::cout << "NIP sudah dipakai pegawai lain, ulangi!\n";
            } while (kembar);
            jumlah++;
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

    void outputData() const {
        std::string g = Util::garis(LEBAR_GAJI);
        std::cout << "\n" << Util::pad("Daftar Gaji Harian PT Informatika", static_cast<int>(g.length()), '^') << "\n";
        std::cout << g << "\n";
        std::cout << Util::baris(HEADER_GAJI, LEBAR_GAJI, RATA_GAJI) << "\n";
        std::cout << g << "\n";

        double totalSemua = 0;
        int maxGaji = (jumlah > 0) ? arr[0].getTotal() : 0;
        int minGaji = (jumlah > 0) ? arr[0].getTotal() : 0;

        for (int i = 0; i < jumlah; i++) {
            std::cout << Util::baris(arr[i].getBaris(i + 1), LEBAR_GAJI, RATA_GAJI) << "\n";
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

//Superclass Kendaraan (abstract)
class Kendaraan {
protected:
    std::string merk;
    std::string plat;
    std::string warna;
    std::string nipPemilik;     // kosong = umum
    std::string namaPemilik;
    Waktu masuk;
    Waktu keluar;
    int hariInap;               // tambahan hari saat keluar
    bool sudahKeluar;

    long durasiDetik() const {
        return static_cast<long>(hariInap) * 86400L + keluar.totalDetik() - masuk.totalDetik();
    }

public:
    Kendaraan() : merk(""), plat(""), warna(""), nipPemilik(""), namaPemilik(""), hariInap(0), sudahKeluar(false) {}

    Kendaraan(const std::string& merk, const std::string& plat, const std::string& warna)
        : merk(merk), plat(plat), warna(warna), nipPemilik(""), namaPemilik(""), hariInap(0), sudahKeluar(false) {}

    virtual ~Kendaraan() {}

    void setMerk(const std::string& m) {
        merk = m;
    }

    void setPlat(const std::string& p) {
        plat = p;
    }

    void setWarna(const std::string& w) {
        warna = w;
    }

    std::string getMerk() const {
        return merk;
    }

    std::string getPlat() const {
        return plat;
    }

    std::string getWarna() const {
        return warna;
    }

    void setPemilik(const std::string& nip, const std::string& nama) {
        nipPemilik = nip;
        namaPemilik = nama;
    }

    std::string getNipPemilik() const {
        return nipPemilik;
    }

    std::string getNamaPemilik() const {
        return namaPemilik;
    }

    bool isPegawai() const {
        return !nipPemilik.empty();
    }

    void setMasuk(const Waktu& w) {
        masuk = w;
    }

    Waktu getMasuk() const {
        return masuk;
    }

    // Mengembalikan false kalau waktu keluar tidak setelah waktu masuk.
    bool setKeluar(const Waktu& w, int tambahHari) {
        long d = static_cast<long>(tambahHari) * 86400L + w.totalDetik() - masuk.totalDetik();
        if (d <= 0) return false;
        keluar = w;
        hariInap = tambahHari;
        sudahKeluar = true;
        return true;
    }

    bool getSudahKeluar() const {
        return sudahKeluar;
    }

    Waktu getDurasi() const {
        long d = durasiDetik();
        return Waktu(static_cast<int>(d / 3600), static_cast<int>((d % 3600) / 60), static_cast<int>(d % 60));
    }

    int jamTerbayar() const {
        long j = (durasiDetik() + 3599) / 3600;
        return static_cast<int>(j < 1 ? 1 : j);
    }

    int hariTerbayar() const {
        long h = (durasiDetik() + 86399) / 86400;
        return static_cast<int>(h < 1 ? 1 : h);
    }

    // true kalau kendaraan ini dikenai tarif per hari
    virtual bool perHari() const {
        return isPegawai();
    }

    virtual std::string getJenis() const = 0;
    virtual std::string getTarif() const = 0;
    virtual int hitungBiaya() const = 0;

    std::string getDitagih() const {
        if (perHari()) return std::to_string(hariTerbayar()) + " hari";
        return std::to_string(jamTerbayar()) + " jam";
    }

    std::vector<std::string> getBaris(int no) const {
        std::string pemilik = isPegawai() ? namaPemilik : "Umum";
        std::string wKeluar = "-";
        std::string durasi = "-";
        std::string ditagih = "-";
        std::string biaya = "-";
        if (sudahKeluar) {
            wKeluar = keluar.toString() + (hariInap > 0 ? "+" + std::to_string(hariInap) + "hr" : "");
            durasi = getDurasi().toString();
            ditagih = getDitagih();
            biaya = Util::rupiah(hitungBiaya());
        }
        return {
            std::to_string(no), plat, getJenis(), merk, pemilik,
            masuk.toString(), wKeluar, durasi, ditagih,
            getTarif(), biaya, sudahKeluar ? "Selesai" : "Parkir"
        };
    }
};

class Mobil : public Kendaraan {
private:
    static const int TARIF_UMUM = 3000;      // per jam
    static const int TARIF_PEGAWAI = 6000;   // per hari
public:
    Mobil() : Kendaraan() {}

    Mobil(const std::string& merk, const std::string& plat, const std::string& warna)
        : Kendaraan(merk, plat, warna) {}

    std::string getJenis() const override {
        return "Mobil";
    }

    std::string getTarif() const override {
        return perHari() ? Util::rupiah(TARIF_PEGAWAI) + "/hari" : Util::rupiah(TARIF_UMUM) + "/jam";
    }

    int hitungBiaya() const override {
        return perHari() ? hariTerbayar() * TARIF_PEGAWAI : jamTerbayar() * TARIF_UMUM;
    }
};

class Truk : public Kendaraan {
private:
    static const int TARIF_UMUM = 5000;      // per jam, pegawai pun sama
public:
    Truk() : Kendaraan() {}

    Truk(const std::string& merk, const std::string& plat, const std::string& warna)
        : Kendaraan(merk, plat, warna) {}

    std::string getJenis() const override {
        return "Truk";
    }

    bool perHari() const override {
        return false;
    }

    std::string getTarif() const override {
        return Util::rupiah(TARIF_UMUM) + "/jam";
    }

    int hitungBiaya() const override {
        return jamTerbayar() * TARIF_UMUM;
    }
};

class Motor : public Kendaraan {
private:
    static const int TARIF_UMUM = 2000;      // per jam
    static const int TARIF_PEGAWAI = 3000;   // per hari
public:
    Motor() : Kendaraan() {}

    Motor(const std::string& merk, const std::string& plat, const std::string& warna)
        : Kendaraan(merk, plat, warna) {}

    std::string getJenis() const override {
        return "Motor";
    }

    std::string getTarif() const override {
        return perHari() ? Util::rupiah(TARIF_PEGAWAI) + "/hari" : Util::rupiah(TARIF_UMUM) + "/jam";
    }

    int hitungBiaya() const override {
        return perHari() ? hariTerbayar() * TARIF_PEGAWAI : jamTerbayar() * TARIF_UMUM;
    }
};


const std::vector<std::string> HEADER_PARKIR = {"No", "Plat", "Jenis", "Merk", "Pemilik", "Masuk",
                                                "Keluar", "Durasi", "Ditagih", "Tarif", "Biaya", "Status"};
const std::vector<int> LEBAR_PARKIR = {3, 10, 6, 10, 12, 8, 13, 9, 8, 10, 9, 7};
const std::string RATA_PARKIR = "^<<<<^^^^>>^";

// Array of Kendaraan* : isinya campur Mobil/Truk/Motor (polimorfisme)
class Parkir {
private:
    static const int max = 100;
    Kendaraan* arr[max];
    int jumlah;

    int cariAktif(const std::string& plat) const {
        for (int i = 0; i < jumlah; i++) {
            if (!arr[i]->getSudahKeluar() && arr[i]->getPlat() == plat) return i;
        }
        return -1;
    }

    int hitungAktif() const {
        int n = 0;
        for (int i = 0; i < jumlah; i++) {
            if (!arr[i]->getSudahKeluar()) n++;
        }
        return n;
    }

public:
    Parkir() : jumlah(0) {
        for (int i = 0; i < max; i++) arr[i] = nullptr;
    }

    ~Parkir() {
        for (int i = 0; i < jumlah; i++) delete arr[i];
    }

    Parkir(const Parkir&) = delete;
    Parkir& operator=(const Parkir&) = delete;

    int getJumlah() const {
        return jumlah;
    }

    // Menambah di belakang, data lama tidak ditimpa.
    void inputMasuk(const ArrayPegawai& dataPegawai) {
        if (jumlah >= max) {
            std::cout << "Data parkir penuh (maksimal " << max << ")!\n";
            return;
        }

        std::cout << "Jenis kendaraan:\n";
        std::cout << "  1. Mobil\n  2. Truk\n  3. Motor\n";
        int jenis;
        do {
            jenis = Util::bacaInt("Pilih jenis (1-3): ");
        } while (jenis < 1 || jenis > 3);

        std::string plat = Util::kapital(Util::bacaString("Plat nomor : "));
        if (cariAktif(plat) != -1) {
            std::cout << "Plat " << plat << " masih terparkir, tidak bisa masuk dua kali!\n";
            return;
        }
        std::string merk = Util::bacaString("Merk        : ");
        std::string warna = Util::bacaString("Warna       : ");

        Kendaraan* k = nullptr;
        switch (jenis) {
            case 1: k = new Mobil(merk, plat, warna); break;
            case 2: k = new Truk(merk, plat, warna);  break;
            default: k = new Motor(merk, plat, warna); break;
        }

        std::string nip = Util::bacaString("NIP pegawai (kosongkan jika umum) : ", false);
        if (!nip.empty()) {
            int idx = dataPegawai.cariNip(nip);
            if (idx >= 0) {
                k->setPemilik(nip, dataPegawai.getPegawaiAt(idx).getNama());
                std::cout << "Pemilik terdaftar sebagai pegawai: " << k->getNamaPemilik() << "\n";
                if (jenis == 2) std::cout << "Catatan: truk tetap dikenai tarif umum.\n";
            } else {
                std::cout << "NIP tidak ditemukan, dicatat sebagai umum.\n";
            }
        }

        Waktu w;
        std::cout << "Waktu Masuk :\n";
        w.inputDalam();
        k->setMasuk(w);

        arr[jumlah++] = k;
        std::cout << k->getJenis() << " " << plat << " berhasil dicatat masuk.\n";
    }

    void inputKeluar() {
        if (hitungAktif() == 0) {
            std::cout << "Tidak ada kendaraan yang sedang parkir.\n";
            return;
        }

        std::string plat = Util::kapital(Util::bacaString("Plat nomor yang keluar : "));
        int idx = cariAktif(plat);
        if (idx == -1) {
            std::cout << "Plat " << plat << " tidak ditemukan di kendaraan yang sedang parkir.\n";
            return;
        }
        Kendaraan* k = arr[idx];

        bool ok;
        do {
            Waktu w;
            std::cout << "Waktu Keluar :\n";
            w.inputDalam();
            int hari;
            do {
                hari = Util::bacaInt("Lama menginap (0-30 hari, 0 jika keluar di hari yang sama) : ");
            } while (hari < 0 || hari > 30);
            ok = k->setKeluar(w, hari);
            if (!ok) std::cout << "Waktu keluar harus setelah waktu masuk, ulangi!\n";
        } while (!ok);

        // hitungBiaya() dipanggil lewat pointer Kendaraan: versi Mobil/Truk/Motor yang jalan
        std::cout << "\n========== STRUK PARKIR ==========\n";
        std::cout << "Plat    : " << k->getPlat() << "\n";
        std::cout << "Jenis   : " << k->getJenis() << " (" << k->getMerk() << ", " << k->getWarna() << ")\n";
        std::cout << "Pemilik : " << (k->isPegawai() ? k->getNamaPemilik() + " (pegawai)" : std::string("Umum")) << "\n";
        std::cout << "Masuk   : " << k->getMasuk().toString() << "\n";
        std::cout << "Durasi  : " << k->getDurasi().toString() << "\n";
        std::cout << "Ditagih : " << k->getDitagih() << " x " << k->getTarif() << "\n";
        std::cout << "BIAYA   : Rp " << Util::rupiah(k->hitungBiaya()) << "\n";
        std::cout << "==================================\n";
    }

    void outputLaporan() const {
        std::string g = Util::garis(LEBAR_PARKIR);
        std::cout << "\n" << Util::pad("Laporan Parkir Kendaraan PT Informatika", static_cast<int>(g.length()), '^') << "\n";
        std::cout << g << "\n";
        std::cout << Util::baris(HEADER_PARKIR, LEBAR_PARKIR, RATA_PARKIR) << "\n";
        std::cout << g << "\n";

        long totalPendapatan = 0;
        int nMobil = 0, nTruk = 0, nMotor = 0, nSelesai = 0;

        for (int i = 0; i < jumlah; i++) {
            std::cout << Util::baris(arr[i]->getBaris(i + 1), LEBAR_PARKIR, RATA_PARKIR) << "\n";
            std::string jenis = arr[i]->getJenis();
            if (jenis == "Mobil") nMobil++;
            else if (jenis == "Truk") nTruk++;
            else nMotor++;
            if (arr[i]->getSudahKeluar()) {
                nSelesai++;
                totalPendapatan += arr[i]->hitungBiaya();
            }
        }
        std::cout << g << "\n";

        std::cout << "Total Kendaraan   : " << jumlah << " (Mobil " << nMobil << ", Truk " << nTruk << ", Motor " << nMotor << ")\n";
        std::cout << "Sedang Parkir     : " << (jumlah - nSelesai) << "\n";
        std::cout << "Sudah Keluar      : " << nSelesai << "\n";
        std::cout << "Total Pendapatan  : " << Util::rupiah(static_cast<int>(totalPendapatan)) << "\n";
    }
};

class Menu {
private:
    ArrayPegawai dataPegawai;
    Parkir dataParkir;

public:
    Menu() {}

    void tampilkanMenu() const {
        std::cout << "\n========================================\n";
        std::cout << "         MENU GAJI & PARKIR PT INFORMATIKA\n";
        std::cout << "========================================\n";
        std::cout << "1. Input Data Pegawai (tambah)\n";
        std::cout << "2. Tampilkan Daftar Gaji\n";
        std::cout << "3. Kendaraan Masuk\n";
        std::cout << "4. Kendaraan Keluar (hitung biaya)\n";
        std::cout << "5. Laporan Parkir\n";
        std::cout << "6. Keluar\n";
        std::cout << "----------------------------------------\n";
    }

    void jalankan() {
        int pilihan;
        do {
            tampilkanMenu();
            pilihan = Util::bacaInt("Pilih menu (1-6): ");
            switch (pilihan) {
                case 1:
                    dataPegawai.inputDalam();
                    break;
                case 2:
                    dataPegawai.prosesData();
                    dataPegawai.outputData();
                    break;
                case 3:
                    dataParkir.inputMasuk(dataPegawai);
                    break;
                case 4:
                    dataParkir.inputKeluar();
                    break;
                case 5:
                    dataParkir.outputLaporan();
                    break;
                case 6:
                    std::cout << "Keluar dari program!\n";
                    break;
                default:
                    std::cout << "Input tidak valid, silahkan coba lagi!\n";
            }
        } while (pilihan != 6);
    }
};

int main() {
    Menu pegawaiApp;
    pegawaiApp.jalankan();
    return 0;
}