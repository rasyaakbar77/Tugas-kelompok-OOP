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

ATURAN PARKIR
1. Jenis kendaraan : Mobil, Truk, Motor. 
2. Tarif umum : Mobil 3.000, Truk 5.000, Motor 2.000. diberlakukan per jam
3. Tarif pegawai : Mobil 6.000, Motor 3.000 perhari. Truk milik pegawai menggunakan tarif umum. 
4. Tarif per jam : durasi dibulatkan ke atas per jam, minimal 1 jam.
5. Tarif per hari : tiap 24 jam dihitung 1 hari (dibulatkan ke atas), minimal 1 hari.
6. Plat nomor tidak boleh sama dengan kendaraan yang masih terparkir.
7. Waktu keluar harus setelah waktu masuk. Lama menginap (0 - 30 hari) diisi saat keluar.
8. Data yang sudah diinput tidak ditimpa. Input pegawai selalu menambah data di belakang data lama.
9. Biaya baru dihitung dan dikunci saat kendaraan keluar.
*/

#include <iostream>
#include <string>
#include <iomanip>
#include <sstream>
#include <limits>

class Util {
public:
    // Validasi integer
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
 
    // Format uang rupiah
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
    int getJam() { 
        return jam; 
    }
    int getMenit() { 
        return menit; 
    }
    int getDetik() { 
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

// Superclass Person
class Person {
private:
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

    void printPerson(int no) const {
        std::cout << std::left << std::setw(3)  << no
               << std::left << std::setw(13) << nama
               << std::left << std::setw(17) << alamat;

    }
};

//Subclass Pegawai
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
        return getTarifLembur() * jamLembur.totalDetik() / 3600;
    }

    int getTotal() const {
        return getGajiHarian() + getLembur();
    }

    void prosesGaji() {
        int gajiHarian = 0;
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
        if (jumlah >= max) {
            std::cout << "\nKapasitas data pegawai sudah penuh (maksimal 100)!\n";
            return;
        }

        int tambahan;
        int sisaKapasitas = max - jumlah;
        do {
            std::string pesan = "Masukkan jumlah pegawai yang ingin ditambahkan (1-" + std::to_string(sisaKapasitas) + "): ";
            tambahan = Util::bacaInt(pesan);
            if (tambahan < 1 || tambahan > sisaKapasitas) {
                std::cout << "Jumlah tidak valid!\n";
            }
        } while (tambahan < 1 || tambahan > sisaKapasitas);

        for (int i = 0; i < tambahan; i++) {
            std::cout << "\nData Pegawai ke-" << (jumlah + 1) << "\n";
            arr[jumlah].inputPegawai();
            jumlah++; 
        }
        std::cout << "\nBerhasil menambahkan " << tambahan << " data pegawai baru!\n";
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
        std::cout << "\n\t\t\t\t\tDaftar Gaji Harian PT Informatika\n";
        std::cout << std::string(150, '=') << "\n";
        std::cout << std::left  << std::setw(3)  << "No"
                  << std::left  << std::setw(13) << "Nama"
                  << std::left  << std::setw(17) << "Alamat"
                  << std::left  << std::setw(12) << "NIP"
                  << std::right << std::setw(5)  << "Gol"
                  << std::setw(10) << "Datang"
                  << std::setw(10) << "Pulang"
                  << std::setw(12) << "Lama"
                  << std::setw(12) << "Jam Lembur"
                  << std::setw(15) << "Gaji Harian"
                  << std::setw(15) << "Lembur"
                  << std::setw(15) << "Total"
                  << "  Status" << "\n";
        std::cout << std::string(150, '=') << "\n";

        double totalSemua = 0;
        int maxGaji = (jumlah > 0) ? arr[0].getTotal() : 0;
        int minGaji = (jumlah > 0) ? arr[0].getTotal() : 0;

        for (int i = 0; i < jumlah; i++) {
            arr[i].printPegawai(i + 1);
            int t = arr[i].getTotal();
            totalSemua += t;
            if (t > maxGaji) maxGaji = t;
            if (t < minGaji) minGaji = t;
        }
        std::cout << std::string(150, '=') << "\n";

        double rataRata = (jumlah > 0) ? (totalSemua / jumlah) : 0;

        std::cout << "Rata-rata Gaji : " << (jumlah > 0 ? Util::rupiah(static_cast<int>(rataRata)) : "---") << "\n";
        std::cout << "Gaji Tertinggi : " << (jumlah > 0 ? Util::rupiah(maxGaji) : "---") << "\n";
        std::cout << "Gaji Terendah  : " << (jumlah > 0 ? Util::rupiah(minGaji) : "---") << "\n";
    }
};

//Supeclass Kendaraan
class Kendaraan {
protected:
    std::string merk;
    std::string plat;
    std::string warna;
    std::string nipPemilik;    
    std::string namaPemilik;
    Waktu masuk;
    Waktu keluar;
public:
    // Constructor default dan berparameter
    Kendaraan() : merk(""), plat(""), warna(""), nipPemilik(""), namaPemilik("") {}

    Kendaraan(const std::string& merk, const std::string& plat, const std::string& warna) 
        : merk(merk), plat(plat), warna(warna), nipPemilik(""), namaPemilik("") {}

    // Virtual Destructor
    virtual ~Kendaraan() {}

    // Setter dan getter
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

    void setPemilik(const std::string& nip, const std::string& nama) { // setter pemilik kendaraaan
        nipPemilik = nip;
        namaPemilik = nama;
    }

    std::string getNipPemilik() const { // getter nip pemilik kendaraan
        return nipPemilik;
    }

    std::string getNamaPemilik() const { // getter nama pemillik kendaraan
        return namaPemilik;
    }

    void setMasuk(const Waktu& w) { // set jam masuk kendaraan
        masuk = w;
    }

    Waktu getMasuk() const { // get jam masuk kendaraan
        return masuk;
    }

    bool isPegawai() const { // method untuk mengecek apakah kendaraan punya karyawan pt atau bukan
        return !nipPemilik.empty();
    }

    bool isSudahKeluar() const { // method untuk mengecek apakah kendaraan sudah keluar atau belum
        return keluar.totalDetik() > 0;
    }

    bool setKeluar(const Waktu& w) { 
        if (w.totalDetik() <= masuk.totalDetik()) { // mengecek apakah waktu keluar lebih dari waktu masuk
            return false; 
        } 
        keluar = w;
        return true;
    }

    Waktu getKeluar() const { // getter jam keluar 
        return keluar;
    }
 
    long durasiDetik() const { // menghitung durasi parkir kendaraan dalam detik
        return static_cast<long>(keluar.totalDetik() - masuk.totalDetik());
    }

    Waktu getDurasi() const { // getter durasi parkir dalam format jam:menit:detik
        long dur = durasiDetik();
        return Waktu(static_cast<int>(dur / 3600), static_cast<int>(dur % 3600 / 60), static_cast<int>(dur % 60));
    }

    virtual void inputSubclass() = 0; // method input atribut spesifik tiap tipe kendaraan
    virtual long hitungBiaya(const ArrayPegawai& db) = 0; // method menghitung biaya parkir tiap tipe kendaraan
    virtual std::string getJenisKendaraan() const = 0; // method untuk endapatkan jenis kendaraan

    // Method input kendaraan umum
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
};

// Subclass Motor
class Motor : public Kendaraan {
private:
bool adaFootStep;
public:
    // Constructor default dan berparameter
    Motor() : Kendaraan(), adaFootStep(false) {}

    Motor(const std::string& merk, const std::string& plat, const std::string& warna, bool adaFootstep) 
        : Kendaraan(merk, plat, warna), adaFootStep(adaFootStep) {}
    
    // Setter dan Getter
    void setAdaFootStep(bool ada) {
        adaFootStep = ada;
    }

    bool getAdaFootStep() const {
        return adaFootStep;
    }

    // getter jenis kendaraan (override)
    std::string getJenisKendaraan() const override {
        return "Motor";
    }

    // Method Input subclass Motor menggunakan override
    void inputSubclass() override {
        std::string input;
        do {
            std::cout << "Apakah ada footstep? (y/n): ";
            std::getline(std::cin, input);
            if (input == "y" || input == "Y") {
                adaFootStep = true;
                break;
            } else if (input == "n" || input == "N") {
                adaFootStep = false;
                break;
            } else {
                std::cout << "Input tidak valid. Silakan masukkan 'y' atau 'n'.\n";
            }
        } while (true);
    }

    // Method hitung biaya khusus mototr
    long hitungBiaya(const ArrayPegawai& db) override {
        long totalDetik = keluar.totalDetik() - masuk.totalDetik();
        if (totalDetik < 0) totalDetik = 0;
        
        // Dibulatkan ke atas per jam, minimal 1 jam
        long totalJam = (totalDetik + 3599) / 3600;
        if (totalJam < 1) totalJam = 1;

        // Cek apakah pemilik kendaraan adalah karyawan
        bool validPegawai = false;
        if (!nipPemilik.empty()) {
            for (int i = 0; i < db.getJumlah(); i++) {
                if (db.getPegawaiAt(i).getNip() == nipPemilik) {
                    validPegawai = true;
                    break;
                }
            }
        }

        // Tarif Pegawai: Rp 3000
        // Tarif Umum: Rp 2000 / jam
        if (validPegawai) {
            long totalHari = (totalDetik + 86399) / 86400; // ditambah 86399 untuk pembulatan keatas
            if (totalHari < 1) totalHari = 1;
            return totalHari * 3000;
        } else {
            return totalJam * 2000;
        }
    }
};

// Subclass Mobil
class Mobil : public Kendaraan {
private:
    int jumlahPintu;
    bool adaSunroof;
public:
    // Constructor default dan berparameter
    Mobil() : Kendaraan(), jumlahPintu(4), adaSunroof(false) {}

    Mobil(const std::string& merk, const std::string& plat, const std::string& warna, int jumlahPintu, bool adaSunroof) 
        : Kendaraan(merk, plat, warna), jumlahPintu(jumlahPintu), adaSunroof(adaSunroof) {}
    
    // Setter dan Getter
    void setJumlahPintu(int pintu) {
        jumlahPintu = pintu;
    }

    int getJumlahPintu() const {
        return jumlahPintu;
    }

    void setAdaSunroof(bool ada) {
        adaSunroof = ada;
    }

    bool getAdaSunroof() const {
        return adaSunroof;
    }

    // Getter jenis kendaraan (override)
    std::string getJenisKendaraan() const override {
        return "Mobil";
    }

    // Method Input subclass Mobil menggunakan override
    void inputSubclass() override {
        jumlahPintu = Util::bacaInt("Jumlah Pintu : ");
        
        std::string input;
        do {
            std::cout << "Apakah ada sunroof? (y/n): ";
            std::getline(std::cin, input);
            if (input == "y" || input == "Y") {
                adaSunroof = true;
                break;
            } else if (input == "n" || input == "N") {
                adaSunroof = false;
                break;
            } else {
                std::cout << "Input tidak valid. Silakan masukkan 'y' atau 'n'.\n";
            }
        } while (true);
    }

    // Method hitung biaya khusus Mobil 
    long hitungBiaya(const ArrayPegawai& db) override {
        long totalDetik = keluar.totalDetik() - masuk.totalDetik();
        if (totalDetik < 0) totalDetik = 0;
        
        long totalJam = (totalDetik + 3599) / 3600;
        if (totalJam < 1) totalJam = 1;

        bool validPegawai = false;
        if (!nipPemilik.empty()) {
            for (int i = 0; i < db.getJumlah(); i++) {
                if (db.getPegawaiAt(i).getNip() == nipPemilik) {
                    validPegawai = true;
                    break;
                }
            }
        }

        if (validPegawai) {
            long totalHari = (totalDetik + 86399) / 86400;
            if (totalHari < 1) totalHari = 1;
            return totalHari * 6000; // Tarif pegawai mobil
        } else {
            return totalJam * 3000;  // Tarif umum mobil
        }
    }
};

//Subclass Truk
class Truk : public Kendaraan {
private:
    double kapasitasMuatanTon;
    bool adaRemAngin;
public:
    // Constructor default dan berparameter
    Truk() : Kendaraan(), kapasitasMuatanTon(5.0), adaRemAngin(true) {}

    Truk(const std::string& merk, const std::string& plat, const std::string& warna, double muatan, bool remAngin) 
        : Kendaraan(merk, plat, warna), kapasitasMuatanTon(muatan), adaRemAngin(remAngin) {}
    
    // Setter dan Getter
    void setKapasitasMuatanTon(double muatan) {
        kapasitasMuatanTon = muatan;
    }

    double getKapasitasMuatanTon() const {
        return kapasitasMuatanTon;
    }

    void setAdaRemAngin(bool ada) {
        adaRemAngin = ada;
    }

    bool getAdaRemAngin() const {
        return adaRemAngin;
    }

    // Getter jenis kendaraan (override)
    std::string getJenisKendaraan() const override {
        return "Truk";
    }

    // Method Input subclass Truk menggunakan override
    void inputSubclass() override {
        std::cout << "Kapasitas Muatan (Ton) : ";
        while (!(std::cin >> kapasitasMuatanTon)) {
            std::cout << "Masukkan angka yang valid!\n";
            std::cin.clear();
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
            std::cout << "Kapasitas Muatan (Ton) : ";
        }
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

        std::string input;
        do {
            std::cout << "Apakah ada rem angin? (y/n): ";
            std::getline(std::cin, input);
            if (input == "y" || input == "Y") {
                adaRemAngin = true;
                break;
            } else if (input == "n" || input == "N") {
                adaRemAngin = false;
                break;
            } else {
                std::cout << "Input tidak valid. Silakan masukkan 'y' atau 'n'.\n";
            }
        } while (true);
    }

    // Method hitung biaya khusus Truk 
    long hitungBiaya(const ArrayPegawai& db) override {
        long totalDetik = keluar.totalDetik() - masuk.totalDetik();
        if (totalDetik < 0) totalDetik = 0;
        
        long totalJam = (totalDetik + 3599) / 3600;
        if (totalJam < 1) totalJam = 1;

        return totalJam * 5000; // Tarif umum truk = tarif pegawai truk
    }
};

class Parkir {
private:
    static const int maxKendaraan = 100;
    Kendaraan* arr[maxKendaraan];
    int jumlahKendaraan;
public:
    // Constructor default dan parameter 
    Parkir() : jumlahKendaraan(0) {
        for (int i = 0; i < maxKendaraan; i++) {
            arr[i] = nullptr;
        }
    } 

    Parkir(int jml) : jumlahKendaraan(jml) {
        for (int i = 0; i < maxKendaraan; i++) {
            arr[i] = nullptr;
        }
    }

    ~Parkir() {
        for (int i = 0; i < jumlahKendaraan; i++) {
            delete arr[i];
        }
    }

    // Setter dan Getter
    void setJumlahKendaraan(int jml) {
        if (jml > maxKendaraan || jml < 0) {
            std::cout << "Jumlah Kendaraan Tidak Valid.\n";
        } else {
            jumlahKendaraan = jml;
        }
    }

    int getJumlahKendaraan() const {
        return jumlahKendaraan;
    }

    void setKendaraanAt(int index, Kendaraan* k) {
        if (index >= 0 && index < maxKendaraan) {
            arr[index] = k;
        }
    }

    Kendaraan* getKendaraanAt(int index) const {
        if (index >= 0 && index < maxKendaraan) {
            return arr[index];
        }
        return nullptr;
    }

    // Method untuk mengecek plat nomor aktif
    bool isPlatAktif(const std::string& plat) const {
        for (int i = 0; i < jumlahKendaraan; i++) {
            if (!arr[i]->isSudahKeluar() && arr[i]->getPlat() == plat) {
                return true;
            }
        }
        return false;
    }

    // Input Kendaraan Masuk
    void inputKendaraanMasuk(const ArrayPegawai& dbPegawai) {
        if (jumlahKendaraan >= maxKendaraan) {
            std::cout << "\nArea parkir sudah penuh (maksimal 100)!\n";
            return;
        }

        int pilihan;
        do {
            std::cout << "\nPilih Jenis Kendaraan:\n";
            std::cout << "1. Motor\n2. Mobil\n3. Truk\nPilihan (1-3): ";
            pilihan = Util::bacaInt("");
        } while (pilihan < 1 || pilihan > 3);

        std::string platTemp;
        std::cout << "No. Plat : ";
        std::getline(std::cin, platTemp);
        
        if (isPlatAktif(platTemp)) {
            std::cout << "Error: Plat nomor tersebut masih aktif terparkir!\n";
            return;
        }

        // Polimorfisme dinamis (membuat objek sesuai pilihan)
        Kendaraan* kBaru = nullptr;
        if (pilihan == 1) kBaru = new Motor();
        else if (pilihan == 2) kBaru = new Mobil();
        else if (pilihan == 3) kBaru = new Truk();

        kBaru->setPlat(platTemp);

        std::string merk, warna;
        std::cout << "Merk  : "; std::getline(std::cin, merk); kBaru->setMerk(merk);
        std::cout << "Warna : "; std::getline(std::cin, warna); kBaru->setWarna(warna);

        // Input atribut spesifik subclass
        kBaru->inputSubclass();

        // Validasi NIP Pemilik
        std::string nip;
        std::cout << "NIP Pemilik (kosongkan jika umum): ";
        std::getline(std::cin, nip);

        std::string namaPemilikStr = "Umum / Tamu";
        if (!nip.empty()) {
            for (int i = 0; i < dbPegawai.getJumlah(); i++) {
                if (dbPegawai.getPegawaiAt(i).getNip() == nip) {
                    namaPemilikStr = dbPegawai.getPegawaiAt(i).getNama();
                    break;
                }
            }
        }
        kBaru->setPemilik(nip, namaPemilikStr);

        std::cout << "Waktu Masuk:\n";
        Waktu wMasuk;
        wMasuk.inputDalam();
        kBaru->setMasuk(wMasuk);

        arr[jumlahKendaraan] = kBaru;
        jumlahKendaraan++;
        std::cout << "Kendaraan berhasil masuk area parkir!\n";
    }

    // Proses Kendaraan Keluar
    void prosesKendaraanKeluar(const ArrayPegawai& dbPegawai) {
        int aktifCount = 0;
        std::cout << "\n--- KENDARAAN YANG MASIH TERPARKIR ---\n";
        for (int i = 0; i < jumlahKendaraan; i++) {
            if (!arr[i]->isSudahKeluar()) {
                std::cout << (i + 1) << ". [" << arr[i]->getJenisKendaraan() << "] Plat: " << arr[i]->getPlat() 
                          << " (Masuk: " << arr[i]->getMasuk().toString() << ")\n";
                aktifCount++;
            }
        }

        if (aktifCount == 0) {
            std::cout << "Tidak ada kendaraan yang sedang terparkir.\n";
            return;
        }

        int pilih = Util::bacaInt("Pilih nomor kendaraan yang akan keluar: ");
        if (pilih < 1 || pilih > jumlahKendaraan || arr[pilih - 1]->isSudahKeluar()) {
            std::cout << "Pilihan tidak valid.\n";
            return;
        }

        Kendaraan* k = arr[pilih - 1];
        Waktu wKeluar;
        do {
            std::cout << "Waktu Keluar:\n";
            wKeluar.inputDalam();
            if (wKeluar.totalDetik() <= k->getMasuk().totalDetik()) {
                std::cout << "Waktu keluar harus setelah waktu masuk, ulangi!\n";
            } else {
                break;
            }
        } while (true);

        k->setKeluar(wKeluar);
        int totalBiaya = k->hitungBiaya(dbPegawai); 

        std::cout << "\nKendaraan berhasil keluar!\n";
        std::cout << "Total Biaya Parkir : Rp " << Util::rupiah(totalBiaya) << "\n";
    }

    // Laporan Parkir 
    void tampilkanLaporanParkir(const ArrayPegawai& dbPegawai) const {
        if (jumlahKendaraan == 0) {
            std::cout << "\nBelum ada data parkir.\n";
            return;
        }

        std::cout << "\n\t\t\t\tLAPORAN DATA PARKIR KENDARAAN\n";
        std::cout << std::string(110, '=') << "\n";
        std::cout << std::left << std::setw(4) << "No"
                << std::setw(10) << "Jenis"
                << std::setw(12) << "Plat"
                << std::setw(15) << "Merk"
                << std::setw(15) << "Pemilik"
                << std::setw(12) << "Masuk"
                << std::setw(12) << "Keluar"
                << std::setw(12) << "Status" << "\n";
        std::cout << std::string(110, '=') << "\n";

        long totalPendapatan = 0;
        for (int i = 0; i < jumlahKendaraan; i++) {
            std::string status = arr[i]->isSudahKeluar() ? "Keluar" : "Terparkir";
            std::string jamKeluar = arr[i]->isSudahKeluar() ? arr[i]->getKeluar().toString() : "---";

            std::cout << std::left << std::setw(4) << (i + 1)
                    << std::setw(10) << arr[i]->getJenisKendaraan()
                    << std::setw(12) << arr[i]->getPlat()
                    << std::setw(15) << arr[i]->getMerk()
                    << std::setw(15) << arr[i]->getNamaPemilik()
                    << std::setw(12) << arr[i]->getMasuk().toString()
                    << std::setw(12) << jamKeluar
                    << std::setw(12) << status;

            // Kirim dbPegawai ke hitungBiaya()
            if (arr[i]->isSudahKeluar()) {
                long biayaItem = arr[i]->hitungBiaya(dbPegawai); 
                std::cout << " | Biaya: Rp " << Util::rupiah(biayaItem);
                totalPendapatan += biayaItem;
            }
            std::cout << "\n";
        }
        std::cout << std::string(110, '=') << "\n";
        std::cout << "Total Pendapatan Parkir : Rp " << Util::rupiah(totalPendapatan) << "\n";
    }
};

class Menu {
private:
    ArrayPegawai dbPegawai;
    Parkir dbParkir;

public:
    void jalankan() {
        int pilihan;
        do {
            std::cout << "\n==========================================================\n";
            std::cout << "            MENU GAJI & PARKIR PT INFORMATIKA             \n";
            std::cout << "==========================================================\n";
            std::cout << "1. Input Data Pegawai \n";
            std::cout << "2. Tampilkan Daftar Gaji\n";
            std::cout << "3. Kendaraan Masuk\n";
            std::cout << "4. Kendaraan Keluar\n";
            std::cout << "5. Laporan Parkir\n";
            std::cout << "6. Keluar\n";
            std::cout << "----------------------------------------------------------\n";
            
            pilihan = Util::bacaInt("Pilih menu (1-6): ");
            std::cout << "\n";

            switch (pilihan) {
                case 1:
                    dbPegawai.inputDalam(); 
                    break;
                case 2:
                    dbPegawai.outputData(); 
                    break;
                case 3:
                    dbParkir.inputKendaraanMasuk(dbPegawai);
                    break;
                case 4:
                    dbParkir.prosesKendaraanKeluar(dbPegawai);
                    break;
                case 5:
                    dbParkir.tampilkanLaporanParkir(dbPegawai);
                    break;
                case 6:
                    std::cout << "Terima kasih telah menggunakan sistem Gaji & Parkir PT Informatika!\n";
                    break;
                default:
                    std::cout << "Pilihan tidak valid! Silakan masukkan angka 1 sampai 6.\n";
            }

            if (pilihan != 6) {
                std::cout << "\nTekan Enter untuk kembali ke menu...";
                std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
            }

        } while (pilihan != 6);
    }
};

int main() {
    Menu pegawaiApp;
    pegawaiApp.jalankan();
    return 0;
}