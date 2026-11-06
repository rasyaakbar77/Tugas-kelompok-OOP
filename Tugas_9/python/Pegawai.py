'''
Nama Program : Pegawai.py
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 28-10-2026
Deskripsi Program : Sebuah program untuk menghitung gaji karyawan dengan input NIP, nama, gol, waktu datang, waktu pulang.
                    Menggunakan konsep inheritance dengan membuat superclass Person yang akan di inherit oleh subclass Pegawai,
                    dengan total 5 class secara OOP.
'''

class Util:
    def __init__(self):
        pass

    @staticmethod
    def bacaInt(pesan):
        while True:
            try:
                val = int(input(pesan))
                return val
            except ValueError:
                print("Masukkan angka yang valid!")

    @staticmethod
    def rupiah(nilai):
        sb = str(nilai)
        for i in range(len(sb) - 3, 0, -3):
            sb = sb[:i] + "." + sb[i:]
        return sb


class Waktu:
    def __init__(self, jam=0, menit=0, detik=0):
        self.__jam = jam
        self.__menit = menit
        self.__detik = detik

    def setWaktu(self, jam, menit, detik):
        self.__jam = jam
        self.__menit = menit
        self.__detik = detik

    def setJam(self, jam):
        self.__jam = jam

    def setMenit(self, menit):
        self.__menit = menit

    def setDetik(self, detik):
        self.__detik = detik

    def getJam(self):
        return self.__jam

    def getMenit(self):
        return self.__menit

    def getDetik(self):
        return self.__detik

    def inputDalam(self):
        while True:
            self.__jam = Util.bacaInt("  Jam   (0-23) : ")
            if not (self.__jam < 0 or self.__jam > 23):
                break

        while True:
            self.__menit = Util.bacaInt("  Menit (0-59): ")
            if not (self.__menit < 0 or self.__menit > 59):
                break

        while True:
            self.__detik = Util.bacaInt("  Detik (0-59): ")
            if not (self.__detik < 0 or self.__detik > 59):
                break

    def totalDetik(self):
        return (self.__jam * 3600) + (self.__menit * 60) + self.__detik

    def hitungSelisih(self, w2):
        selisihTotal = abs(self.totalDetik() - w2.totalDetik())
        hasil = Waktu()
        hasil.__jam = selisihTotal // 3600
        hasil.__menit = (selisihTotal % 3600) // 60
        hasil.__detik = selisihTotal % 60
        return hasil

    def hitungSelisihVoid(self, w1, w2):
        selisihTotal = abs(w1.totalDetik() - w2.totalDetik())
        self.__jam = selisihTotal // 3600
        self.__menit = (selisihTotal % 3600) // 60
        self.__detik = selisihTotal % 60

    def toString(self):
        return f"{self.__jam:02d}:{self.__menit:02d}:{self.__detik:02d}"

    def __str__(self):
        return self.toString()

    def outputDalam(self):
        print("  Waktu: " + self.toString())


class Person:
    def __init__(self, nama="", alamat=""):
        self.__nama = nama
        self.__alamat = alamat

    def inputDalam(self):
        self.__nama = input("Nama: ")
        self.__alamat = input("Alamat: ")

    def setNama(self, nama):
        self.__nama = nama

    def setAlamat(self, alamat):
        self.__alamat = alamat

    def getNama(self):
        return self.__nama

    def getAlamat(self):
        return self.__alamat

class Pegawai(Person):
    def __init__(self, nip="", nama="", alamat="", gol=1):
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
            if not (self.__gol < 1 or self.__gol > 4):
                break

        while True:
            print("Waktu Datang :")
            self.__datang.inputDalam()
            print("Waktu Pulang :")
            self.__pulang.inputDalam()
            if self.__pulang.totalDetik() <= self.__datang.totalDetik():
                print("Waktu pulang harus setelah waktu datang, ulangi!")
            else:
                break

    def setNip(self, n):
        self.__nip = n

    def setGol(self, g):
        self.__gol = g

    def setDatang(self, d):
        self.__datang = d

    def setPulang(self, p):
        self.__pulang = p

    def getNip(self):
        return self.__nip

    def getGol(self):
        return self.__gol

    def getStatusPeringatan(self):
        return self.__statusPeringatan
    
    def getGajiHarian(self):
        match self.__gol:
            case 1:
                return 150000
            case 2:
                return 200000
            case 3:
                return 400000
            case 4:
                return 500000
            case _:
                return 0
            
    def getTarifLembur(self):
        match self.__gol:
            case 1:
                return 50000
            case 2:
                return 75000
            case 3:
                return 150000
            case 4:
                return 200000
            case _:
                return 0
            
    def getLembur(self):
        return self.__jamLembur.getJam() * self.getTarifLembur()
    
    def getTotal(self):
        return self.getGajiHarian() + self.getLembur()

    def prosesGaji(self):
        self.__lamaKerja = self.__pulang.hitungSelisih(self.__datang)

        batas = Waktu(8, 0, 0)
        if self.__lamaKerja.totalDetik() >= batas.totalDetik():
            self.__jamLembur.hitungSelisihVoid(self.__lamaKerja, batas)
            self.__statusPeringatan = "ok"
        else:
            self.__jamLembur = Waktu()
            self.__statusPeringatan = "Peringatan"

    def getBaris(self, no):
        return [
            str(no),
            self.getNama(),
            self.getAlamat(),
            self.__nip,
            str(self.__gol),
            self.__datang.toString(),
            self.__pulang.toString(),
            self.__lamaKerja.toString(),
            self.__jamLembur.toString(),
            Util.rupiah(self.getGajiHarian()),
            Util.rupiah(self.getLembur()),
            Util.rupiah(self.getTotal()),
            self.__statusPeringatan
        ]

class ArrayPegawai:
    max = 100
    HEADER = ["No", "Nama", "Alamat", "NIP", "Gol", "Datang", "Pulang",
              "Lama", "Jam Lembur", "Gaji Harian", "Lembur", "Total", "Status"]
    LEBAR = [3, 10, 14, 8, 3, 8, 8, 8, 10, 11, 11, 11, 10]
    RATA   = ["^", "<", "<", "<", "^", "^", "^", "^", "^", ">", ">", ">", "^"]

    def __init__(self):
        self.__arr = [Pegawai() for _ in range(ArrayPegawai.max)]
        self.__jumlah = 0

    def setJumlah(self, jml):
        if jml > ArrayPegawai.max or jml < 0:
            print("Jumlah Array Tidak Valid.\n")
        self.__jumlah = ArrayPegawai.max if jml > ArrayPegawai.max else (0 if jml < 0 else jml)

    def getJumlah(self):
        return self.__jumlah

    def inputDalam(self):
        while True:
            self.__jumlah = Util.bacaInt("Masukkan jumlah pegawai (1-100): ")
            if not (self.__jumlah < 1 or self.__jumlah > ArrayPegawai.max):
                break

        for i in range(self.__jumlah):
            print("\nData Pegawai ke-" + str(i + 1))
            self.__arr[i].inputPegawai()

    def prosesData(self):
        for i in range(self.__jumlah):
            self.__arr[i].prosesGaji()

    def __garis(self):
        return "+-" + "-+-".join("-" * w for w in ArrayPegawai.LEBAR) + "-+"

    def __baris(self, cells):
        isi = [f"{c:{a}{w}}" for c, a, w in zip(cells, ArrayPegawai.RATA, ArrayPegawai.LEBAR)]
        return "| " + " | ".join(isi) + " |"

    def outputData(self):
        garis = self.__garis()
        print("\n" + "Daftar Gaji Harian PT Informatika".center(len(garis)))
        print(garis)
        print(self.__baris(ArrayPegawai.HEADER))
        print(garis)

        totalSemua = 0
        maxGaji = self.__arr[0].getTotal() if self.__jumlah > 0 else 0
        minGaji = self.__arr[0].getTotal() if self.__jumlah > 0 else 0

        for i in range(self.__jumlah):
            print(self.__baris(self.__arr[i].getBaris(i + 1)))
            t = self.__arr[i].getTotal()
            totalSemua += t
            if t > maxGaji:
                maxGaji = t
            if t < minGaji:
                minGaji = t
        print(garis)

        rataRata = (totalSemua / self.__jumlah) if self.__jumlah > 0 else 0

        print("Rata-rata Gaji : " + (Util.rupiah(int(rataRata)) if self.__jumlah > 0 else "---"))
        print("Gaji Tertinggi : " + (Util.rupiah(maxGaji) if self.__jumlah > 0 else "---"))
        print("Gaji Terendah  : " + (Util.rupiah(minGaji) if self.__jumlah > 0 else "---"))

class Menu:
    def __init__(self):
        self.__dataPegawai = ArrayPegawai()

    def tampilkanMenu(self):
        print("\n========================================")
        print("         MENU GAJI PT INFORMATIKA         ")
        print("========================================")
        print("1. Input Data Pegawai")
        print("2. Tampilkan Daftar Gaji")
        print("3. Keluar")
        print("----------------------------------------")

    def jalankan(self):
        while True:
            self.tampilkanMenu()
            pilihan = Util.bacaInt("Pilih menu (1-3): ")
            if pilihan == 1:
                self.__dataPegawai.inputDalam()
            elif pilihan == 2:
                self.__dataPegawai.prosesData()
                self.__dataPegawai.outputData()
            elif pilihan == 3:
                print("Keluar dari program!")
            else:
                print("Input tidak valid, silahkan coba lagi!")
            if pilihan == 3:
                break


class PegawaiApp:
    @staticmethod
    def main():
        pegawaiApp = Menu()
        pegawaiApp.jalankan()


if __name__ == "__main__":
    PegawaiApp.main()