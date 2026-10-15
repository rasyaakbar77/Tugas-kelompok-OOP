'''
Nama Program : PegawaiApp.py
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 08-10-2026
Deskripsi Program : Sebuah program untukmenghitung gaji karyawan dengan input NIP, nama, gol, waktu datang, waktu pulang.
                    Dengan perhitungan Gaji Lembur = >= 8 jam (minimal kelebihan 1 jam / pembulatan ke bawah) dan
                    untuk pegawai yg kurang dari 8 jam diberi status peringatan. Ddengan aturan Gaji = gapok + lembur secara OOP.
'''

class Waktu:
    def __init__(self, jam=0, menit=0, detik=0):
        self.jam = jam
        self.menit = menit
        self.detik = detik

    def setWaktu(self, jam, menit, detik):
        self.jam = jam
        self.menit = menit
        self.detik = detik

    def setJam(self, jam):
        self.jam = jam

    def setMenit(self, menit):
        self.menit = menit

    def setDetik(self, detik):
        self.detik = detik

    def getJam(self):
        return self.jam

    def getMenit(self):
        return self.menit

    def getDetik(self):
        return self.detik

    def inputDalam(self):
        while True:
            self.jam = PegawaiApp.bacaInt("   Jam   (0-23) : ")
            if 0 <= self.jam <= 23:
                break
            print("Jam harus antara 0 dan 23.")

        while True:
            self.menit = PegawaiApp.bacaInt("   Menit (0-59) : ")
            if 0 <= self.menit <= 59:
                break
            print("Menit harus antara 0 dan 59.")

        while True:
            self.detik = PegawaiApp.bacaInt("   Detik (0-59) : ")
            if 0 <= self.detik <= 59:
                break
            print("Detik harus antara 0 dan 59.")

    def totalDetik(self):
        return (self.jam * 3600) + (self.menit * 60) + self.detik

    def hitungSelisihFungsi(self, w2):
        selisihTotal = abs(self.totalDetik() - w2.totalDetik())
        hasil = Waktu()
        hasil.jam = selisihTotal // 3600
        hasil.menit = (selisihTotal % 3600) // 60
        hasil.detik = selisihTotal % 60
        return hasil

    def hitungSelisihVoid(self, w1, w2):
        selisihTotal = abs(w1.totalDetik() - w2.totalDetik())
        self.jam = selisihTotal // 3600
        self.menit = (selisihTotal % 3600) // 60
        self.detik = selisihTotal % 60

    def __str__(self):
        return f"{self.jam:02d}:{self.menit:02d}:{self.detik:02d}"


class Pegawai:
    def __init__(self, nip="", nama="", gol=0, datang=None, pulang=None):
        self.nip = nip
        self.nama = nama
        self.gol = gol
        self.datang = datang if datang else Waktu()
        self.pulang = pulang if pulang else Waktu()
        self.lamaKerja = Waktu()
        self.jamLembur = Waktu()
        self.gajiHarian = 0
        self.lembur = 0
        self.total = 0
        self.statusPeringatan = ""

    def inputPegawai(self):
        self.nip = input("Masukkan NIP  : ")
        self.nama = input("Masukkan Nama : ")
        while True:
            self.gol = PegawaiApp.bacaInt("Masukkan Gol (1-4) : ")
            if 1 <= self.gol <= 4:
                break
            print("Golongan harus antara 1 dan 4.")

        while True:
            print("Waktu Datang :")
            self.datang.inputDalam()
            print("Waktu Pulang :")
            self.pulang.inputDalam()
            if self.pulang.totalDetik() > self.datang.totalDetik():
                break
            print("Waktu pulang harus setelah waktu datang, ulangi!")

    def setPegawai(self, nip, nama, gol, datang, pulang):
        self.nip = nip
        self.nama = nama
        self.gol = gol
        self.datang = datang
        self.pulang = pulang

    def setNip(self, nip):
        self.nip = nip

    def setNama(self, nama):
        self.nama = nama

    def setGol(self, gol):
        self.gol = gol

    def setDatang(self, datang):
        self.datang = datang

    def setPulang(self, pulang):
        self.pulang = pulang

    def getNip(self):
        return self.nip

    def getNama(self):
        return self.nama

    def getGol(self):
        return self.gol

    def getTotal(self):
        return self.total

    def getStatusPeringatan(self):
        return self.statusPeringatan

    def prosesGaji(self):
        self.lamaKerja = self.pulang.hitungSelisihFungsi(self.datang)
        batas = Waktu(8, 0, 0)

        if self.lamaKerja.totalDetik() >= batas.totalDetik():
            self.jamLembur.hitungSelisihVoid(self.lamaKerja, batas)
            self.statusPeringatan = "ok"
        else:
            self.jamLembur = Waktu()
            self.statusPeringatan = "Peringatan"

        tarif = 0
        if self.gol == 1:
            self.gajiHarian = 150000
            tarif = 50000
        elif self.gol == 2:
            self.gajiHarian = 200000
            tarif = 75000
        elif self.gol == 3:
            self.gajiHarian = 400000
            tarif = 150000
        elif self.gol == 4:
            self.gajiHarian = 500000
            tarif = 200000

        self.lembur = self.jamLembur.getJam() * tarif
        self.total = self.gajiHarian + self.lembur

    def printPegawai(self, no):
        print(f"{no:<3} {self.nip:<6} {self.nama:<12} {self.gol:<4} "
              f"{str(self.datang):<9} {str(self.pulang):<9} {str(self.lamaKerja):<9} "
              f"{str(self.jamLembur):<11} {self.rupiah(self.gajiHarian):<12} "
              f"{self.rupiah(self.lembur):<9} {self.rupiah(self.total):<9} {self.statusPeringatan}")

    def rupiah(self, nilai):
        return f"{nilai:,}".replace(',', '.')


class ArrayPegawai:
    def __init__(self, ukuran=10):
        self.ukuran = ukuran
        self.jumlah = 0
        self.pegawai = [Pegawai() for _ in range(ukuran)]

    def getUkuran(self):
        return self.ukuran

    def getJumlah(self):
        return self.jumlah

    def tambahViaSetter(self):
        if self.penuh():
            return

        datang = Waktu(8, 0, 0)
        pulang = Waktu(17, 15, 10)
        self.pegawai[self.jumlah].setNip("001")
        self.pegawai[self.jumlah].setNama("Ali")
        self.pegawai[self.jumlah].setGol(3)
        self.pegawai[self.jumlah].setDatang(datang)
        self.pegawai[self.jumlah].setPulang(pulang)
        self.simpan("setter")

    def tambahViaConstructor(self):
        if self.penuh():
            return

        self.pegawai[self.jumlah] = Pegawai("004", "ida", 2, Waktu(7, 30, 0), Waktu(18, 0, 0))
        self.simpan("constructor")

    def tambahInputDalam(self):
        if self.penuh():
            return

        print(f"\nPegawai ke-{self.jumlah + 1}")
        self.pegawai[self.jumlah].inputPegawai()
        self.simpan("Scanner dalam class")

    def tambahInputLuar(self):
        if self.penuh():
            return

        print(f"\nPegawai ke-{self.jumlah + 1}")
        nip = input("Masukkan NIP  : ")
        if self.nipSudahAda(nip):
            print(f"NIP {nip} sudah terdaftar, data tidak disimpan!")
            return
        nama = input("Masukkan Nama : ")

        while True:
            gol = PegawaiApp.bacaInt("Masukkan Gol (1-4) : ")
            if 1 <= gol <= 4:
                break
            print("Golongan harus antara 1 dan 4.")

        datang = Waktu()
        pulang = Waktu()

        while True:
            print("Waktu Datang :")
            j = m = d = 0
            while True:
                j = PegawaiApp.bacaInt("   Jam   (0-23) : ")
                if 0 <= j <= 23: break
            while True:
                m = PegawaiApp.bacaInt("   Menit (0-59) : ")
                if 0 <= m <= 59: break
            while True:
                d = PegawaiApp.bacaInt("   Detik (0-59) : ")
                if 0 <= d <= 59: break
            datang.setWaktu(j, m, d)

            print("Waktu Pulang :")
            while True:
                j = PegawaiApp.bacaInt("   Jam   (0-23) : ")
                if 0 <= j <= 23: break
            while True:
                m = PegawaiApp.bacaInt("   Menit (0-59) : ")
                if 0 <= m <= 59: break
            while True:
                d = PegawaiApp.bacaInt("   Detik (0-59) : ")
                if 0 <= d <= 59: break
            pulang.setWaktu(j, m, d)

            if pulang.totalDetik() > datang.totalDetik():
                break
            print("Waktu pulang harus setelah waktu datang, ulangi!")

        self.pegawai[self.jumlah].setNip(nip)
        self.pegawai[self.jumlah].setNama(nama)
        self.pegawai[self.jumlah].setGol(gol)
        self.pegawai[self.jumlah].setDatang(datang)
        self.pegawai[self.jumlah].setPulang(pulang)
        self.simpan("Scanner luar class")

    def prosesSemua(self):
        for i in range(self.jumlah):
            self.pegawai[i].prosesGaji()

    def tampilDaftar(self):
        if self.jumlah == 0:
            print("Belum ada data pegawai!")
            return
        garis = "-" * 127
        print()
        print("                              Daftar Gaji Harian PT Informatika")
        print(garis)
        print(f"{'No':<3} {'NIP':<6} {'Nama':<12} {'Gol':<4} {'Datang':<9} {'Pulang':<9} "
              f"{'Lama':<9} {'Jam Lembur':<11} {'Gaji Harian':<12} {'Lembur':<9} {'Total':<9} Status")
        print(garis)
        for i in range(self.jumlah):
            self.pegawai[i].printPegawai(i + 1)
        print(garis)

    def penuh(self):
        if self.jumlah >= self.ukuran:
            print(f"Data penuh! Maksimal {self.ukuran} pegawai.")
            return True
        return False

    def nipSudahAda(self, nip):
        for i in range(self.jumlah):
            if self.pegawai[i].getNip() == nip:
                return True
        return False

    def simpan(self, cara):
        nip = self.pegawai[self.jumlah].getNip()
        if self.nipSudahAda(nip):
            print(f"NIP {nip} sudah terdaftar, data tidak disimpan!")
            self.pegawai[self.jumlah] = Pegawai()
            return
        self.jumlah += 1
        print(f"Pegawai ke-{self.jumlah} berhasil ditambahkan ({cara}).")


class Menu:
    def __init__(self, larik):
        self.larik = larik
        self.pilih = -1

    def tampilMenu(self):
        print()
        print("MENU GAJI HARIAN PT INFORMATIKA")
        print(f"Jumlah pegawai : {self.larik.getJumlah()}/{self.larik.getUkuran()}")
        print(" 1. Tambah pegawai : via setter(hardcode)")
        print(" 2. Tambah pegawai : via constructor berparameter(hardcode)")
        print(" 3. Tambah pegawai : input dalam class")
        print(" 4. Tambah pegawai : input luar class")
        print(" 5. Tampilkan daftar gaji harian")
        print(" 0. Keluar")

    def inputPilihan(self):
        self.pilih = PegawaiApp.bacaInt("Pilih menu: ")

    def prosesPilihan(self):
        if self.pilih == 1:
            self.larik.tambahViaSetter()
        elif self.pilih == 2:
            self.larik.tambahViaConstructor()
        elif self.pilih == 3:
            self.larik.tambahInputDalam()
        elif self.pilih == 4:
            self.larik.tambahInputLuar()
        elif self.pilih == 5:
            self.larik.prosesSemua()
            self.larik.tampilDaftar()
        elif self.pilih == 0:
            print("Terima kasih!")
        else:
            print("Menu tidak tersedia!")

    def jalankan(self):
        while self.pilih != 0:
            self.tampilMenu()
            self.inputPilihan()
            self.prosesPilihan()


class PegawaiApp:
    MAKS = 10

    @staticmethod
    def bacaInt(pesan):
        while True:
            try:
                return int(input(pesan).strip())
            except ValueError:
                print("Masukkan angka yang valid!")


if __name__ == "__main__":
    larik = ArrayPegawai(PegawaiApp.MAKS)
    menu = Menu(larik)
    menu.jalankan()