"""
Nama Program : PegawaiApp.py
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
"""
#Library abstract class python
from abc import ABC, abstractmethod


class Util:
    @staticmethod
    def bacaInt(pesan: str) -> int:
        while True:
            try:
                val_str = input(pesan)
                val = int(val_str)
                return val
            except ValueError:
                print("Masukkan angka yang valid!")

    @staticmethod
    def bacaString(pesan: str, wajib: bool = True) -> str:
        while True:
            s = input(pesan).strip()
            if not wajib or s != "":
                return s
            print("Tidak boleh kosong!")

    @staticmethod
    def kapital(s: str) -> str:
        return s.upper()

    @staticmethod
    def rupiah(nilai: int) -> str:
        s = str(nilai)
        res = []
        for i, char in enumerate(reversed(s)):
            if i > 0 and i % 3 == 0:
                res.append('.')
            res.append(char)
        return "".join(reversed(res))

    @staticmethod
    def pad(s: str, w: int, rata: str) -> str:
        t = s[:w]
        sisa = w - len(t)
        if rata == '<':
            return t + (' ' * sisa)
        if rata == '>':
            return (' ' * sisa) + t
        kiri = sisa // 2
        return (' ' * kiri) + t + (' ' * (sisa - kiri))

    @staticmethod
    def garis(lebar: list) -> str:
        g = "+"
        for l in lebar:
            g += ("-" * (l + 2)) + "+"
        return g

    @staticmethod
    def baris(cells: list, lebar: list, rata: str) -> str:
        b = "|"
        for i in range(len(lebar)):
            b += " " + Util.pad(cells[i], lebar[i], rata[i]) + " |"
        return b


class Waktu:
    def __init__(self, jam: int = 0, menit: int = 0, detik: int = 0):
        self.jam = jam
        self.menit = menit
        self.detik = detik

    def setWaktu(self, j: int, m: int, d: int):
        self.jam = j
        self.menit = m
        self.detik = d

    def setJam(self, j: int):
        self.jam = j

    def setMenit(self, m: int):
        self.menit = m

    def setDetik(self, d: int):
        self.detik = d

    def getJam(self) -> int:
        return self.jam

    def getMenit(self) -> int:
        return self.menit

    def getDetik(self) -> int:
        return self.detik

    def inputDalam(self):
        while True:
            self.jam = Util.bacaInt("   Jam   (0-23) : ")
            if 0 <= self.jam <= 23:
                break

        while True:
            self.menit = Util.bacaInt("   Menit (0-59) : ")
            if 0 <= self.menit <= 59:
                break

        while True:
            self.detik = Util.bacaInt("   Detik (0-59) : ")
            if 0 <= self.detik <= 59:
                break

    def totalDetik(self) -> int:
        return (self.jam * 3600) + (self.menit * 60) + self.detik

    def hitungSelisihFungsi(self, w2: 'Waktu') -> 'Waktu':
        selisihTotal = abs(self.totalDetik() - w2.totalDetik())
        hasil = Waktu()
        hasil.jam = selisihTotal // 3600
        hasil.menit = (selisihTotal % 3600) // 60
        hasil.detik = selisihTotal % 60
        return hasil

    def hitungSelisihVoid(self, w1: 'Waktu', w2: 'Waktu'):
        selisihTotal = abs(w1.totalDetik() - w2.totalDetik())
        self.jam = selisihTotal // 3600
        self.menit = (selisihTotal % 3600) // 60
        self.detik = selisihTotal % 60

    def toString(self) -> str:
        return f"{self.jam:02d}:{self.menit:02d}:{self.detik:02d}"

    def outputDalam(self):
        print(f"Waktu = {self.toString()}")


#Superclass Person
class Person:
    def __init__(self, nama: str = "", alamat: str = ""):
        self._nama = nama
        self._alamat = alamat

    def inputDalam(self):
        self._nama = input("Nama   : ")
        self._alamat = input("Alamat : ")

    def setNama(self, name: str):
        self._nama = name

    def setAlamat(self, address: str):
        self._alamat = address

    def getNama(self) -> str:
        return self._nama

    def getAlamat(self) -> str:
        return self._alamat


#Subclass Pegawai : Person
class Pegawai(Person):
    def __init__(self, nip: str = "", nama: str = "", alamat: str = "", gol: int = 1):
        super().__init__(nama, alamat)
        self.__gol = gol
        self.__nip = nip
        self.__datang = Waktu()
        self.__pulang = Waktu()
        self.__lamaKerja = Waktu()
        self.__jamLembur = Waktu()
        self.__statusPeringatan = ""

    def inputPegawai(self):
        self.__nip = input("NIP     : ")
        super().inputDalam()

        while True:
            self.__gol = Util.bacaInt("Masukkan Gol (1-4) : ")
            if 1 <= self.__gol <= 4:
                break

        while True:
            print("Waktu Datang :")
            self.__datang.inputDalam()
            print("Waktu Pulang :")
            self.__pulang.inputDalam()
            if self.__pulang.totalDetik() > self.__datang.totalDetik():
                break
            print("Waktu pulang harus setelah waktu datang, ulangi!")

    def setNip(self, n: str):
        self.__nip = n

    def setGol(self, g: int):
        self.__gol = g

    def setDatang(self, d: Waktu):
        self.__datang = d

    def setPulang(self, p: Waktu):
        self.__pulang = p

    def getNip(self) -> str:
        return self.__nip

    def getGol(self) -> int:
        return self.__gol

    def getStatusPeringatan(self) -> str:
        return self.__statusPeringatan

    def getGajiHarian(self) -> int:
        if self.__gol == 1:
            return 150000
        elif self.__gol == 2:
            return 200000
        elif self.__gol == 3:
            return 400000
        elif self.__gol == 4:
            return 500000
        return 0

    def getTarifLembur(self) -> int:
        if self.__gol == 1:
            return 50000
        elif self.__gol == 2:
            return 75000
        elif self.__gol == 3:
            return 150000
        elif self.__gol == 4:
            return 200000
        return 0

    def getLembur(self) -> int:
        return self.__jamLembur.getJam() * self.getTarifLembur()

    def getTotal(self) -> int:
        return self.getGajiHarian() + self.getLembur()

    def prosesGaji(self):
        self.__lamaKerja = self.__pulang.hitungSelisihFungsi(self.__datang)

        batas = Waktu(8, 0, 0)
        if self.__lamaKerja.totalDetik() >= batas.totalDetik():
            self.__jamLembur.hitungSelisihVoid(self.__lamaKerja, batas)
            self.__statusPeringatan = "ok"
        else:
            self.__jamLembur = Waktu()
            self.__statusPeringatan = "Peringatan"

    def getBaris(self, no: int) -> list:
        return [
            str(no), self.getNama(), self.getAlamat(), self.__nip, str(self.__gol),
            self.__datang.toString(), self.__pulang.toString(),
            self.__lamaKerja.toString(), self.__jamLembur.toString(),
            Util.rupiah(self.getGajiHarian()), Util.rupiah(self.getLembur()), Util.rupiah(self.getTotal()),
            self.__statusPeringatan
        ]


HEADER_GAJI = ["No", "Nama", "Alamat", "NIP", "Gol", "Datang", "Pulang",
               "Lama", "Jam Lembur", "Gaji Harian", "Lembur", "Total", "Status"]
LEBAR_GAJI = [3, 10, 14, 8, 3, 8, 8, 8, 10, 11, 11, 11, 10]
RATA_GAJI = "^<<<^^^^^>>>^"


class ArrayPegawai:
    MAX = 100

    def __init__(self, dataPegawai: list = None, jml: int = 0):
        self.__arr = [Pegawai() for _ in range(ArrayPegawai.MAX)]
        self.__jumlah = 0
        if dataPegawai is not None:
            self.__jumlah = ArrayPegawai.MAX if jml > ArrayPegawai.MAX else jml
            for i in range(self.__jumlah):
                self.__arr[i] = dataPegawai[i]

    def _cariNipInternal(self, nip: str, batas: int) -> int:
        for i in range(batas):
            if self.__arr[i].getNip() == nip:
                return i
        return -1

    def setJumlah(self, jml: int):
        if jml > ArrayPegawai.MAX or jml < 0:
            print("Jumlah Array Tidak Valid.")
        if jml > ArrayPegawai.MAX:
            self.__jumlah = ArrayPegawai.MAX
        elif jml < 0:
            self.__jumlah = 0
        else:
            self.__jumlah = jml

    def getJumlah(self) -> int:
        return self.__jumlah

    def cariNip(self, nip: str) -> int:
        return self._cariNipInternal(nip, self.__jumlah)

    def inputDalam(self):
        sisa = ArrayPegawai.MAX - self.__jumlah
        if sisa <= 0:
            print(f"Data pegawai penuh (maksimal {ArrayPegawai.MAX})!")
            return

        while True:
            tambah = Util.bacaInt(f"Masukkan jumlah pegawai yang ditambahkan (1-{sisa}): ")
            if 1 <= tambah <= sisa:
                break

        for i in range(tambah):
            idx = self.__jumlah
            print(f"\nData Pegawai ke-{idx + 1}")
            while True:
                self.__arr[idx].inputPegawai()
                kembar = (self._cariNipInternal(self.__arr[idx].getNip(), idx) != -1)
                if kembar:
                    print("NIP sudah dipakai pegawai lain, ulangi!")
                else:
                    break
            self.__jumlah += 1

    def prosesData(self):
        for i in range(self.__jumlah):
            self.__arr[i].prosesGaji()

    def setPegawaiAt(self, index: int, p: Pegawai):
        if 0 <= index < ArrayPegawai.MAX:
            self.__arr[index] = p

    def getPegawaiAt(self, index: int) -> Pegawai:
        if 0 <= index < ArrayPegawai.MAX:
            return self.__arr[index]
        return Pegawai()

    def outputData(self):
        g = Util.garis(LEBAR_GAJI)
        print("\n" + Util.pad("Daftar Gaji Harian PT Informatika", len(g), '^'))
        print(g)
        print(Util.baris(HEADER_GAJI, LEBAR_GAJI, RATA_GAJI))
        print(g)

        totalSemua = 0.0
        maxGaji = self.__arr[0].getTotal() if self.__jumlah > 0 else 0
        minGaji = self.__arr[0].getTotal() if self.__jumlah > 0 else 0

        for i in range(self.__jumlah):
            print(Util.baris(self.__arr[i].getBaris(i + 1), LEBAR_GAJI, RATA_GAJI))
            t = self.__arr[i].getTotal()
            totalSemua += t
            if t > maxGaji:
                maxGaji = t
            if t < minGaji:
                minGaji = t
        print(g)

        rataRata = (totalSemua / self.__jumlah) if self.__jumlah > 0 else 0.0

        print("Rata-rata Gaji : " + (Util.rupiah(int(rataRata)) if self.__jumlah > 0 else "---"))
        print("Gaji Tertinggi : " + (Util.rupiah(maxGaji) if self.__jumlah > 0 else "---"))
        print("Gaji Terendah  : " + (Util.rupiah(minGaji) if self.__jumlah > 0 else "---"))


#Superclass Abstract KEndaraan menggunakan parameter ABC 
class Kendaraan(ABC): 
    def __init__(self, merk: str = "", plat: str = "", warna: str = ""):
        self._merk = merk
        self._plat = plat
        self._warna = warna
        self._nipPemilik = ""
        self._namaPemilik = ""
        self._masuk = Waktu()
        self._keluar = Waktu()
        self._hariInap = 0
        self._sudahKeluar = False

    def _durasiDetik(self) -> int:
        return self._hariInap * 86400 + self._keluar.totalDetik() - self._masuk.totalDetik()

    def setMerk(self, m: str):
        self._merk = m

    def setPlat(self, p: str):
        self._plat = p

    def setWarna(self, w: str):
        self._warna = w

    def getMerk(self) -> str:
        return self._merk

    def getPlat(self) -> str:
        return self._plat

    def getWarna(self) -> str:
        return self._warna

    def setPemilik(self, nip: str, nama: str):
        self._nipPemilik = nip
        self._namaPemilik = nama

    def getNipPemilik(self) -> str:
        return self._nipPemilik

    def getNamaPemilik(self) -> str:
        return self._namaPemilik

    def isPegawai(self) -> bool:
        return self._nipPemilik != ""

    def setMasuk(self, w: Waktu):
        self._masuk = w

    def getMasuk(self) -> Waktu:
        return self._masuk

    def setKeluar(self, w: Waktu, tambahHari: int) -> bool:
        d = tambahHari * 86400 + w.totalDetik() - self._masuk.totalDetik()
        if d <= 0:
            return False
        self._keluar = w
        self._hariInap = tambahHari
        self._sudahKeluar = True
        return True

    def getSudahKeluar(self) -> bool:
        return self._sudahKeluar

    def getDurasi(self) -> Waktu:
        d = self._durasiDetik()
        return Waktu(d // 3600, (d % 3600) // 60, d % 60)

    def jamTerbayar(self) -> int:
        j = (self._durasiDetik() + 3599) // 3600
        return 1 if j < 1 else j

    def hariTerbayar(self) -> int:
        h = (self._durasiDetik() + 86399) // 86400
        return 1 if h < 1 else h

    def perHari(self) -> bool:
        return self.isPegawai()

    #Abstract Method
    @abstractmethod
    def getJenis(self) -> str:
        pass

    @abstractmethod
    def getTarif(self) -> str:
        pass

    @abstractmethod
    def hitungBiaya(self) -> int:
        pass

    def getDitagih(self) -> str:
        if self.perHari():
            return f"{self.hariTerbayar()} hari"
        return f"{self.jamTerbayar()} jam"

    def getBaris(self, no: int) -> list:
        pemilik = self._namaPemilik if self.isPegawai() else "Umum"
        wKeluar = "-"
        durasi = "-"
        ditagih = "-"
        biaya = "-"
        if self._sudahKeluar:
            wKeluar = self._keluar.toString() + (f"+{self._hariInap}hr" if self._hariInap > 0 else "")
            durasi = self.getDurasi().toString()
            ditagih = self.getDitagih()
            biaya = Util.rupiah(self.hitungBiaya())

        return [
            str(no), self._plat, self.getJenis(), self._merk, pemilik,
            self._masuk.toString(), wKeluar, durasi, ditagih,
            self.getTarif(), biaya, "Selesai" if self._sudahKeluar else "Parkir"
        ]


class Mobil(Kendaraan):
    TARIF_UMUM = 3000
    TARIF_PEGAWAI = 6000

    def __init__(self, merk: str = "", plat: str = "", warna: str = ""):
        super().__init__(merk, plat, warna)

    def getJenis(self) -> str:
        return "Mobil"

    def getTarif(self) -> str:
        return f"{Util.rupiah(Mobil.TARIF_PEGAWAI)}/hari" if self.perHari() else f"{Util.rupiah(Mobil.TARIF_UMUM)}/jam"

    def hitungBiaya(self) -> int:
        return self.hariTerbayar() * Mobil.TARIF_PEGAWAI if self.perHari() else self.jamTerbayar() * Mobil.TARIF_UMUM


class Truk(Kendaraan):
    TARIF_UMUM = 5000

    def __init__(self, merk: str = "", plat: str = "", warna: str = ""):
        super().__init__(merk, plat, warna)

    def getJenis(self) -> str:
        return "Truk"

    def perHari(self) -> bool:
        return False

    def getTarif(self) -> str:
        return f"{Util.rupiah(Truk.TARIF_UMUM)}/jam"

    def hitungBiaya(self) -> int:
        return self.jamTerbayar() * Truk.TARIF_UMUM


class Motor(Kendaraan):
    TARIF_UMUM = 2000
    TARIF_PEGAWAI = 3000

    def __init__(self, merk: str = "", plat: str = "", warna: str = ""):
        super().__init__(merk, plat, warna)

    def getJenis(self) -> str:
        return "Motor"

    def getTarif(self) -> str:
        return f"{Util.rupiah(Motor.TARIF_PEGAWAI)}/hari" if self.perHari() else f"{Util.rupiah(Motor.TARIF_UMUM)}/jam"

    def hitungBiaya(self) -> int:
        return self.hariTerbayar() * Motor.TARIF_PEGAWAI if self.perHari() else self.jamTerbayar() * Motor.TARIF_UMUM


HEADER_PARKIR = ["No", "Plat", "Jenis", "Merk", "Pemilik", "Masuk",
                 "Keluar", "Durasi", "Ditagih", "Tarif", "Biaya", "Status"]
LEBAR_PARKIR = [3, 10, 6, 10, 12, 8, 13, 9, 8, 10, 9, 7]
RATA_PARKIR = "^<<<<^^^^>>^"


class Parkir:
    MAX = 100

    def __init__(self):
        self.__arr = [None for _ in range(Parkir.MAX)]
        self.__jumlah = 0

    def __cariAktif(self, plat: str) -> int:
        for i in range(self.__jumlah):
            if not self.__arr[i].getSudahKeluar() and self.__arr[i].getPlat() == plat:
                return i
        return -1

    def __hitungAktif(self) -> int:
        n = 0
        for i in range(self.__jumlah):
            if not self.__arr[i].getSudahKeluar():
                n += 1
        return n

    def getJumlah(self) -> int:
        return self.__jumlah

    def inputMasuk(self, dataPegawai: ArrayPegawai):
        if self.__jumlah >= Parkir.MAX:
            print(f"Data parkir penuh (maksimal {Parkir.MAX})!")
            return

        print("Jenis kendaraan:")
        print("  1. Mobil\n  2. Truk\n  3. Motor")
        while True:
            jenis = Util.bacaInt("Pilih jenis (1-3): ")
            if 1 <= jenis <= 3:
                break

        plat = Util.kapital(Util.bacaString("Plat nomor : "))
        if self.__cariAktif(plat) != -1:
            print(f"Plat {plat} masih terparkir, tidak bisa masuk dua kali!")
            return

        merk = Util.bacaString("Merk        : ")
        warna = Util.bacaString("Warna       : ")

        if jenis == 1:
            k = Mobil(merk, plat, warna)
        elif jenis == 2:
            k = Truk(merk, plat, warna)
        else:
            k = Motor(merk, plat, warna)

        nip = Util.bacaString("NIP pegawai (kosongkan jika umum) : ", False)
        if nip != "":
            idx = dataPegawai.cariNip(nip)
            if idx >= 0:
                k.setPemilik(nip, dataPegawai.getPegawaiAt(idx).getNama())
                print(f"Pemilik terdaftar sebagai pegawai: {k.getNamaPemilik()}")
                if jenis == 2:
                    print("Catatan: truk tetap dikenai tarif umum.")
            else:
                print("NIP tidak ditemukan, dicatat sebagai umum.")

        w = Waktu()
        print("Waktu Masuk :")
        w.inputDalam()
        k.setMasuk(w)

        self.__arr[self.__jumlah] = k
        self.__jumlah += 1
        print(f"{k.getJenis()} {plat} berhasil dicatat masuk.")

    def inputKeluar(self):
        if self.__hitungAktif() == 0:
            print("Tidak ada kendaraan yang sedang parkir.")
            return

        plat = Util.kapital(Util.bacaString("Plat nomor yang keluar : "))
        idx = self.__cariAktif(plat)
        if idx == -1:
            print(f"Plat {plat} tidak ditemukan di kendaraan yang sedang parkir.")
            return

        k = self.__arr[idx]

        while True:
            w = Waktu()
            print("Waktu Keluar :")
            w.inputDalam()
            while True:
                hari = Util.bacaInt("Lama menginap (0-30 hari, 0 jika keluar di hari yang sama) : ")
                if 0 <= hari <= 30:
                    break
            ok = k.setKeluar(w, hari)
            if ok:
                break
            print("Waktu keluar harus setelah waktu datang, ulangi!")

        print("\n========== STRUK PARKIR ==========")
        print(f"Plat    : {k.getPlat()}")
        print(f"Jenis   : {k.getJenis()} ({k.getMerk()}, {k.getWarna()})")
        print(f"Pemilik : {k.getNamaPemilik() + ' (pegawai)' if k.isPegawai() else 'Umum'}")
        print(f"Masuk   : {k.getMasuk().toString()}")
        print(f"Durasi  : {k.getDurasi().toString()}")
        print(f"Ditagih : {k.getDitagih()} x {k.getTarif()}")
        print(f"BIAYA   : Rp {Util.rupiah(k.hitungBiaya())}")
        print("==================================")

    def outputLaporan(self):
        g = Util.garis(LEBAR_PARKIR)
        print("\n" + Util.pad("Laporan Parkir Kendaraan PT Informatika", len(g), '^'))
        print(g)
        print(Util.baris(HEADER_PARKIR, LEBAR_PARKIR, RATA_PARKIR))
        print(g)

        totalPendapatan = 0
        nMobil = 0
        nTruk = 0
        nMotor = 0
        nSelesai = 0

        for i in range(self.__jumlah):
            print(Util.baris(self.__arr[i].getBaris(i + 1), LEBAR_PARKIR, RATA_PARKIR))
            jenis = self.__arr[i].getJenis()
            if jenis == "Mobil":
                nMobil += 1
            elif jenis == "Truk":
                nTruk += 1
            else:
                nMotor += 1

            if self.__arr[i].getSudahKeluar():
                nSelesai += 1
                totalPendapatan += self.__arr[i].hitungBiaya()

        print(g)
        print(f"Total Kendaraan   : {self.__jumlah} (Mobil {nMobil}, Truk {nTruk}, Motor {nMotor})")
        print(f"Sedang Parkir     : {self.__jumlah - nSelesai}")
        print(f"Sudah Keluar      : {nSelesai}")
        print(f"Total Pendapatan  : {Util.rupiah(totalPendapatan)}")


class Menu:
    def __init__(self):
        self.__dataPegawai = ArrayPegawai()
        self.__dataParkir = Parkir()

    def tampilkanMenu(self):
        print("\n========================================")
        print("         MENU GAJI & PARKIR PT INFORMATIKA")
        print("========================================")
        print("1. Input Data Pegawai (tambah)")
        print("2. Tampilkan Daftar Gaji")
        print("3. Kendaraan Masuk")
        print("4. Kendaraan Keluar (hitung biaya)")
        print("5. Laporan Parkir")
        print("6. Keluar")
        print("----------------------------------------")

    def jalankan(self):
        while True:
            self.tampilkanMenu()
            pilihan = Util.bacaInt("Pilih menu (1-6): ")
            if pilihan == 1:
                self.__dataPegawai.inputDalam()
            elif pilihan == 2:
                self.__dataPegawai.prosesData()
                self.__dataPegawai.outputData()
            elif pilihan == 3:
                self.__dataParkir.inputMasuk(self.__dataPegawai)
            elif pilihan == 4:
                self.__dataParkir.inputKeluar()
            elif pilihan == 5:
                self.__dataParkir.outputLaporan()
            elif pilihan == 6:
                print("Keluar dari program!")
                break
            else:
                print("Input tidak valid, silahkan coba lagi!")


if __name__ == "__main__":
    pegawaiApp = Menu()
    pegawaiApp.jalankan()