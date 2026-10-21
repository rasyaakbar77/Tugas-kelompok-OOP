'''
Nama Program : PegawaiApp.py
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 08-10-2026
Deskripsi Program : Sebuah program untuk menghitung gaji karyawan dengan input NIP, nama, gol, waktu datang, waktu pulang.
                    Dengan perhitungan Gaji Lembur = >= 8 jam (minimal kelebihan 1 jam / pembulatan ke bawah) dan
                    untuk pegawai yg kurang dari 8 jam diberi status peringatan. Dengan aturan Gaji = gapok + lembur secara OOP.
'''

class PegawaiAppHelper:
    @staticmethod
    def baca_int(pesan):
        while True:
            try:
                return int(input(pesan).strip())
            except ValueError:
                print("Masukkan angka yang valid!")


class Waktu:
    def __init__(self, jam=0, menit=0, detik=0):
        self.jam = jam
        self.menit = menit
        self.detik = detik

    def set_waktu(self, jam, menit, detik):
        self.jam = jam
        self.menit = menit
        self.detik = detik

    def get_jam(self):
        return self.jam

    def get_menit(self):
        return self.menit

    def get_detik(self):
        return self.detik

    def input_dalam(self):
        while True:
            self.jam = PegawaiAppHelper.baca_int("   Jam   (0-23) : ")
            if 0 <= self.jam <= 23:
                break
            print("Jam harus antara 0 dan 23.")

        while True:
            self.menit = PegawaiAppHelper.baca_int("   Menit (0-59) : ")
            if 0 <= self.menit <= 59:
                break
            print("Menit harus antara 0 dan 59.")

        while True:
            self.detik = PegawaiAppHelper.baca_int("   Detik (0-59) : ")
            if 0 <= self.detik <= 59:
                break
            print("Detik harus antara 0 dan 59.")

    def total_detik(self):
        return (self.jam * 3600) + (self.menit * 60) + self.detik

    def hitung_selisih_fungsi(self, w2):
        selisih_total = abs(self.total_detik() - w2.total_detik())
        hasil = Waktu()
        hasil.jam = selisih_total // 3600
        hasil.menit = (selisih_total % 3600) // 60
        hasil.detik = selisih_total % 60
        return hasil

    def hitung_selisih_void(self, w1, w2):
        selisih_total = abs(w1.total_detik() - w2.total_detik())
        self.jam = selisih_total // 3600
        self.menit = (selisih_total % 3600) // 60
        self.detik = selisih_total % 60

    def to_string_format(self):
        return f"{self.jam:02d}:{self.menit:02d}:{self.detik:02d}"


class Pegawai:
    def __init__(self, nip="", nama="", gol=0, datang=None, pulang=None):
        self.nip = nip
        self.nama = nama
        self.gol = gol
        self.datang = datang if datang else Waktu()
        self.pulang = pulang if pulang else Waktu()
        self.lama_kerja = Waktu()
        self.jam_lembur = Waktu()
        self.gaji_harian = 0
        self.lembur = 0
        self.total = 0
        self.status_peringatan = ""

    def input_pegawai(self):
        self.nip = input("Masukkan NIP  : ")
        self.nama = input("Masukkan Nama : ")
        while True:
            self.gol = PegawaiAppHelper.baca_int("Masukkan Gol (1-4) : ")
            if 1 <= self.gol <= 4:
                break
            print("Golongan harus antara 1 dan 4.")

        while True:
            print("Waktu Datang :")
            self.datang.input_dalam()
            print("Waktu Pulang :")
            self.pulang.input_dalam()
            if self.pulang.total_detik() > self.datang.total_detik():
                break
            print("Waktu pulang harus setelah waktu datang, ulangi!")

    def set_pegawai(self, nip, nama, gol, datang, pulang):
        self.nip = nip
        self.nama = nama
        self.gol = gol
        self.datang = datang
        self.pulang = pulang

    def set_nip(self, nip):
        self.nip = nip

    def set_nama(self, nama):
        self.nama = nama

    def set_gol(self, gol):
        self.gol = gol

    def set_datang(self, datang):
        self.datang = datang

    def set_pulang(self, pulang):
        self.pulang = pulang

    def get_nip(self):
        return self.nip

    def get_nama(self):
        return self.nama

    def get_gol(self):
        return self.gol

    def get_total(self):
        return self.total

    def get_status_peringatan(self):
        return self.status_peringatan

    def proses_gaji(self):
        self.lama_kerja = self.pulang.hitung_selisih_fungsi(self.datang)
        batas = Waktu(8, 0, 0)

        if self.lama_kerja.total_detik() >= batas.total_detik():
            self.jam_lembur.hitung_selisih_void(self.lama_kerja, batas)
            self.status_peringatan = "ok"
        else:
            self.jam_lembur = Waktu()
            self.status_peringatan = "Peringatan"

        tarif = 0
        if self.gol == 1:
            self.gaji_harian = 150000
            tarif = 50000
        elif self.gol == 2:
            self.gaji_harian = 200000
            tarif = 75000
        elif self.gol == 3:
            self.gaji_harian = 400000
            tarif = 150000
        elif self.gol == 4:
            self.gaji_harian = 500000
            tarif = 200000

        self.lembur = self.jam_lembur.get_jam() * tarif
        self.total = self.gaji_harian + self.lembur

    def print_pegawai(self, no):
        print(f"{no:<3} {self.nip:<6} {self.nama:<12} {self.gol:<4} "
              f"{self.datang.to_string_format():<9} {self.pulang.to_string_format():<9} {self.lama_kerja.to_string_format():<9} "
              f"{self.jam_lembur.to_string_format():<11} {self.rupiah(self.gaji_harian):<12} "
              f"{self.rupiah(self.lembur):<9} {self.rupiah(self.total):<9} {self.status_peringatan}")

    def rupiah(self, nilai):
        return f"{nilai:,}".replace(',', '.')


class ArrayPegawai:
    def __init__(self, ukuran=10):
        self.ukuran = ukuran
        self.jumlah = 0
        self.pegawai = [Pegawai() for _ in range(ukuran)]

    def get_ukuran(self):
        return self.ukuran

    def get_jumlah(self):
        return self.jumlah

    def tambah_via_setter(self):
        if self.penuh():
            return

        datang = Waktu(8, 0, 0)
        pulang = Waktu(17, 15, 10)
        self.pegawai[self.jumlah].set_nip("001")
        self.pegawai[self.jumlah].set_nama("Ali")
        self.pegawai[self.jumlah].set_gol(3)
        self.pegawai[self.jumlah].set_datang(datang)
        self.pegawai[self.jumlah].set_pulang(pulang)
        self.simpan("setter")

    def tambah_via_constructor(self):
        if self.penuh():
            return

        self.pegawai[self.jumlah] = Pegawai("004", "ida", 2, Waktu(7, 30, 0), Waktu(18, 0, 0))
        self.simpan("constructor")

    def tambah_input_dalam(self):
        if self.penuh():
            return

        print(f"\nPegawai ke-{self.jumlah + 1}")
        self.pegawai[self.jumlah].input_pegawai()
        self.simpan("Scanner dalam class")

    def tambah_input_luar(self):
        if self.penuh():
            return

        print(f"\nPegawai ke-{self.jumlah + 1}")
        nip = input("Masukkan NIP  : ")
        if self.nip_sudah_ada(nip):
            print(f"NIP {nip} sudah terdaftar, data tidak disimpan!")
            return
        nama = input("Masukkan Nama : ")

        while True:
            gol = PegawaiAppHelper.baca_int("Masukkan Gol (1-4) : ")
            if 1 <= gol <= 4:
                break
            print("Golongan harus antara 1 dan 4.")

        datang = Waktu()
        pulang = Waktu()

        while True:
            print("Waktu Datang :")
            j = m = d = 0
            while True:
                j = PegawaiAppHelper.baca_int("   Jam   (0-23) : ")
                if 0 <= j <= 23: break
            while True:
                m = PegawaiAppHelper.baca_int("   Menit (0-59) : ")
                if 0 <= m <= 59: break
            while True:
                d = PegawaiAppHelper.baca_int("   Detik (0-59) : ")
                if 0 <= d <= 59: break
            datang.set_waktu(j, m, d)

            print("Waktu Pulang :")
            while True:
                j = PegawaiAppHelper.baca_int("   Jam   (0-23) : ")
                if 0 <= j <= 23: break
            while True:
                m = PegawaiAppHelper.baca_int("   Menit (0-59) : ")
                if 0 <= m <= 59: break
            while True:
                d = PegawaiAppHelper.baca_int("   Detik (0-59) : ")
                if 0 <= d <= 59: break
            pulang.set_waktu(j, m, d)

            if pulang.total_detik() > datang.total_detik():
                break
            print("Waktu pulang harus setelah waktu datang, ulangi!")

        self.pegawai[self.jumlah].set_nip(nip)
        self.pegawai[self.jumlah].set_nama(nama)
        self.pegawai[self.jumlah].set_gol(gol)
        self.pegawai[self.jumlah].set_datang(datang)
        self.pegawai[self.jumlah].set_pulang(pulang)
        self.simpan("Scanner luar class")

    def proses_semua(self):
        for i in range(self.jumlah):
            self.pegawai[i].proses_gaji()

    def tampil_daftar(self):
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
            self.pegawai[i].print_pegawai(i + 1)
        print(garis)

    def penuh(self):
        if self.jumlah >= self.ukuran:
            print(f"Data penuh! Maksimal {self.ukuran} pegawai.")
            return True
        return False

    def nip_sudah_ada(self, nip):
        for i in range(self.jumlah):
            if self.pegawai[i].get_nip() == nip:
                return True
        return False

    def simpan(self, cara):
        nip = self.pegawai[self.jumlah].get_nip()
        if self.nip_sudah_ada(nip):
            print(f"NIP {nip} sudah terdaftar, data tidak disimpan!")
            self.pegawai[self.jumlah] = Pegawai()
            return
        self.jumlah += 1
        print(f"Pegawai ke-{self.jumlah} berhasil ditambahkan ({cara}).")


class Menu:
    def __init__(self, larik):
        self.larik = larik
        self.pilih = -1

    def tampil_menu(self):
        print()
        print("MENU GAJI HARIAN PT INFORMATIKA")
        print(f"Jumlah pegawai : {self.larik.get_jumlah()}/{self.larik.get_ukuran()}")
        print(" 1. Tambah pegawai : via setter(hardcode)")
        print(" 2. Tambah pegawai : via constructor berparameter(hardcode)")
        print(" 3. Tambah pegawai : input dalam class")
        print(" 4. Tambah pegawai : input luar class")
        print(" 5. Tampilkan daftar gaji harian")
        print(" 0. Keluar")

    def input_pilihan(self):
        self.pilih = PegawaiAppHelper.baca_int("Pilih menu: ")

    def proses_pilihan(self):
        if self.pilih == 1:
            self.larik.tambah_via_setter()
        elif self.pilih == 2:
            self.larik.tambah_via_constructor()
        elif self.pilih == 3:
            self.larik.tambah_input_dalam()
        elif self.pilih == 4:
            self.larik.tambah_input_luar()
        elif self.pilih == 5:
            self.larik.proses_semua()
            self.larik.tampil_daftar()
        elif self.pilih == 0:
            print("Terima kasih!")
        else:
            print("Menu tidak tersedia!")

    def jalankan(self):
        while self.pilih != 0:
            self.tampil_menu()
            self.input_pilihan()
            self.proses_pilihan()


class MainApp:
    def __init__(self):
        self.larik = ArrayPegawai(10)
        self.menu_utama = Menu(self.larik)

    def jalankan_aplikasi(self):
        self.menu_utama.jalankan()


if __name__ == "__main__":
    program_utama = MainApp()
    program_utama.jalankan_aplikasi()