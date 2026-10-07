"""
Nama Program : Soal3.py
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 29-09-2026
Deskripsi Program : Sebuah program untukmenghitung gaji karyawan dengan input NIP, nama, gol, waktu datang, waktu pulang.
                    Dengan perhitungan Gaji Lembur = >= 8 jam (minimal kelebihan 1 jam / pembulatan ke bawah) dan
                    untuk pegawai yg kurang dari 8 jam diberi status peringatan. Ddengan aturan Gaji = gapok + lembur secara OOP.
"""

class Waktu:
    # constructor + validasi (sekaligus constructor kosong dan berparameter)
    def __init__(self, jam=0, menit=0, detik=0):
        self.__jam = jam if 0 <= jam <= 23 else 0
        self.__menit = menit if 0 <= menit <= 59 else 0
        self.__detik = detik if 0 <= detik <= 59 else 0

    # input dalam + validasi
    def input_waktu(self):
        while True:
            self.__jam = baca_int("   Jam   (0-23) : ")
            if 0 <= self.__jam <= 23:
                break

        while True:
            self.__menit = baca_int("   Menit (0-59) : ")
            if 0 <= self.__menit <= 59:
                break

        while True:
            self.__detik = baca_int("   Detik (0-59) : ")
            if 0 <= self.__detik <= 59:
                break

    # Setter + validasi
    def set_waktu(self, jam, menit, detik):
        self.__jam = jam if 0 <= jam <= 23 else 0
        self.__menit = menit if 0 <= menit <= 59 else 0
        self.__detik = detik if 0 <= detik <= 59 else 0

    def set_jam(self, jam):
        self.__jam = jam if 0 <= jam <= 23 else 0

    def set_menit(self, menit):
        self.__menit = menit if 0 <= menit <= 59 else 0

    def set_detik(self, detik):
        self.__detik = detik if 0 <= detik <= 59 else 0

    # getter
    def get_jam(self):
        return self.__jam

    def get_menit(self):
        return self.__menit

    def get_detik(self):
        return self.__detik

    # proses
    def total_detik(self):
        return self.__jam * 3600 + self.__menit * 60 + self.__detik

    # Selisih fungsi
    def selisih_fungsi(self, p):
        p_hasil = Waktu()
        sel = self.total_detik() - p.total_detik()
        if sel < 0:
            sel = 0  # Mencegah nilai minus
        p_hasil.__jam = sel // 3600
        p_hasil.__menit = (sel % 3600) // 60
        p_hasil.__detik = sel % 60
        return p_hasil

    #  Selisih void
    def selisih_void(self, p1, p2):
        sel = p1.total_detik() - p2.total_detik()
        if sel < 0:
            sel = 0
        self.__jam = sel // 3600
        self.__menit = (sel % 3600) // 60
        self.__detik = sel % 60

    # output
    def __str__(self):
        return f"{self.__jam:02d}:{self.__menit:02d}:{self.__detik:02d}"

    def print_waktu(self):
        print(f" Waktu = {self}")


class Pegawai:
    # constructor kosong dan berparameter jadi satu
    def __init__(self, nip="", nama="", gol=0, datang=None, pulang=None):
        self.__nip = nip
        self.__nama = nama
        self.__gol = gol
        self.__datang = datang if datang is not None else Waktu()
        self.__pulang = pulang if pulang is not None else Waktu()
        self.__lama_kerja = Waktu()
        self.__jam_lembur = Waktu()
        self.__gaji_harian = 0
        self.__lembur = 0
        self.__total = 0
        self.__status_peringatan = ""

    # input dalam
    def input_pegawai(self):
        self.__nip = input("Masukkan NIP  : ")
        self.__nama = input("Masukkan Nama : ")
        while True:
            self.__gol = baca_int("Masukkan Gol (1-4) : ")
            if 1 <= self.__gol <= 4:
                break

        while True:
            print("Waktu Datang :")
            self.__datang.input_waktu()
            print("Waktu Pulang :")
            self.__pulang.input_waktu()
            if self.__pulang.total_detik() <= self.__datang.total_detik():
                print("Waktu pulang harus setelah waktu datang, ulangi!")
            else:
                break

    # setter & getter
    def set_pegawai(self, nip, nama, gol, datang, pulang):
        self.__nip = nip
        self.__nama = nama
        self.__gol = gol
        self.__datang = datang
        self.__pulang = pulang

    def set_nip(self, nip):
        self.__nip = nip

    def set_nama(self, nama):
        self.__nama = nama

    def set_gol(self, gol):
        self.__gol = gol

    def set_datang(self, datang):
        self.__datang = datang

    def set_pulang(self, pulang):
        self.__pulang = pulang

    def get_nip(self):
        return self.__nip

    def get_nama(self):
        return self.__nama

    def get_gol(self):
        return self.__gol

    def get_total(self):
        return self.__total

    def get_status_peringatan(self):
        return self.__status_peringatan


    # proses
    def proses_gaji(self):
        # menggunakan cara 2 (fungsi) untuk menghitung lama kerja
        self.__lama_kerja = self.__pulang.selisih_fungsi(self.__datang)

        batas = Waktu(8, 0, 0)
        if self.__lama_kerja.total_detik() >= batas.total_detik():
            # menggunakan cara 1 (void) untuk menghitung jam lembur
            self.__jam_lembur.selisih_void(self.__lama_kerja, batas)
            self.__status_peringatan = "ok"
        else:
            self.__jam_lembur = Waktu()
            self.__status_peringatan = "Peringatan"

        tarif = 0
        if self.__gol == 1:
            self.__gaji_harian, tarif = 150000, 50000
        elif self.__gol == 2:
            self.__gaji_harian, tarif = 200000, 75000
        elif self.__gol == 3:
            self.__gaji_harian, tarif = 400000, 150000
        elif self.__gol == 4:
            self.__gaji_harian, tarif = 500000, 200000

        self.__lembur = self.__jam_lembur.get_jam() * tarif
        self.__total = self.__gaji_harian + self.__lembur

    # output
    def print_pegawai(self, no):
        print(f"{no:<3} {self.__nip:<6} {self.__nama:<12} {self.__gol:<4} "
              f"{str(self.__datang):<9} {str(self.__pulang):<9} {str(self.__lama_kerja):<9} "
              f"{str(self.__jam_lembur):<11} {self.__rupiah(self.__gaji_harian):<12} "
              f"{self.__rupiah(self.__lembur):<9} {self.__rupiah(self.__total):<9} {self.__status_peringatan}")

    def __rupiah(self, nilai):
        return f"{nilai:,}".replace(",", ".")


#validasi
def baca_int(pesan):
    while True:
        try:
            return int(input(pesan).strip())
        except ValueError:
            print("Masukkan angka yang valid!")


def main():
    p1 = p2 = p3 = p4 = None

    while True:
        print()
        print("MENU GAJI HARIAN PT INFORMATIKA")
        print(" 1. Pegawai 1 : via setter(hardcode)")
        print(" 2. Pegawai 2 : via constructor berparameter(hardcode)")
        print(" 3. Pegawai 3 : input dalam class")
        print(" 4. Pegawai 4 : input luar class (Main)")
        print(" 5. Tampilkan daftar gaji harian")
        print(" 0. Keluar")
        pilih = baca_int("Pilih menu: ")

        #via setter
        if pilih == 1:
            p1 = Pegawai()
            datang1 = Waktu(8, 0, 0)
            pulang1 = Waktu(17, 15, 10)
            p1.set_nip("250001")
            p1.set_nama("Ali")
            p1.set_gol(3)
            p1.set_datang(datang1)
            p1.set_pulang(pulang1)
            p1.proses_gaji()
            print("Pegawai 1 berhasil diisi (setter).")

        #cons parameter
        elif pilih == 2:
            p2 = Pegawai("250002", "Budi", 1, Waktu(8, 0, 0), Waktu(15, 30, 0))
            p2.proses_gaji()
            print("Pegawai 2 berhasil diisi (constructor).")

        elif pilih == 3:
            print("\nPegawai 3")
            p3 = Pegawai()
            p3.input_pegawai()
            p3.proses_gaji()
            print("Pegawai 3 berhasil diisi (Scanner dalam class).")

        elif pilih == 4:
            print("\nPegawai 4")
            nip = input("Masukkan NIP  : ")
            nama = input("Masukkan Nama : ")

            while True:
                gol = baca_int("Masukkan Gol (1-4) : ")
                if 1 <= gol <= 4:
                    break

            datang4 = Waktu()
            pulang4 = Waktu()

            # loop validasi input luar agar pulang > datang
            while True:
                print("Waktu Datang :")
                while True:
                    j = baca_int("   Jam   (0-23) : ")
                    if 0 <= j <= 23:
                        break
                while True:
                    m = baca_int("   Menit (0-59) : ")
                    if 0 <= m <= 59:
                        break
                while True:
                    d = baca_int("   Detik (0-59) : ")
                    if 0 <= d <= 59:
                        break
                datang4.set_waktu(j, m, d)

                print("Waktu Pulang :")
                while True:
                    j = baca_int("   Jam   (0-23) : ")
                    if 0 <= j <= 23:
                        break
                while True:
                    m = baca_int("   Menit (0-59) : ")
                    if 0 <= m <= 59:
                        break
                while True:
                    d = baca_int("   Detik (0-59) : ")
                    if 0 <= d <= 59:
                        break
                pulang4.set_waktu(j, m, d)

                if pulang4.total_detik() <= datang4.total_detik():
                    print("Waktu pulang harus setelah waktu datang, ulangi!")
                else:
                    break

            p4 = Pegawai()
            p4.set_nip(nip)
            p4.set_nama(nama)
            p4.set_gol(gol)
            p4.set_datang(datang4)
            p4.set_pulang(pulang4)
            p4.proses_gaji()
            print("Pegawai 4 berhasil diisi (Scanner luar class).")

        elif pilih == 5:
            if p1 is None and p2 is None and p3 is None and p4 is None:
                print("Belum ada data pegawai!")
                continue
            garis = "-" * 127
            print()
            print("                              Daftar Gaji Harian PT Informatika")
            print(garis)
            print(f"{'No':<3} {'NIP':<6} {'Nama':<12} {'Gol':<4} {'Datang':<9} {'Pulang':<9} {'Lama':<9} "
                  f"{'Jam Lembur':<11} {'Gaji Harian':<12} {'Lembur':<9} {'Total':<9} Status")
            print(garis)
            if p1 is not None: p1.print_pegawai(1)
            if p2 is not None: p2.print_pegawai(2)
            if p3 is not None: p3.print_pegawai(3)
            if p4 is not None: p4.print_pegawai(4)
            print(garis)

        elif pilih == 0:
            print("Terima kasih!")
            break

        else:
            print("Menu tidak tersedia!")


if __name__ == "__main__":
    main()def baca_int(pesan):
    while True:
        try:
            return int(input(pesan))
        except ValueError:
            print("Masukkan angka yang valid!")


class Waktu:
    def __init__(self, jam=0, menit=0, detik=0):
        self.jam = jam
        self.menit = menit
        self.detik = detik

    def set_waktu(self, j, m, d):
        self.jam = j
        self.menit = m
        self.detik = d

    def set_jam(self, j):
        self.jam = j

    def set_menit(self, m):
        self.menit = m

    def set_detik(self, d):
        self.detik = d

    def get_jam(self):
        return self.jam

    def get_menit(self):
        return self.menit

    def get_detik(self):
        return self.detik

    def input_dalam(self):
        while True:
            self.jam = baca_int("   Jam   (0-23) : ")
            if 0 <= self.jam <= 23:
                break
        while True:
            self.menit = baca_int("   Menit (0-59) : ")
            if 0 <= self.menit <= 59:
                break
        while True:
            self.detik = baca_int("   Detik (0-59) : ")
            if 0 <= self.detik <= 59:
                break

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

    def to_string(self):
        return f"{self.jam:02d}:{self.menit:02d}:{self.detik:02d}"

    def output_dalam(self):
        print(f"Waktu = {self.to_string()}")


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

    def _rupiah(self, nilai):
        return f"{nilai:,}".replace(",", ".")

    def input_pegawai(self):
        self.nip = input("Masukkan NIP  : ")
        self.nama = input("Masukkan Nama : ")
        while True:
            self.gol = baca_int("Masukkan Gol (1-4) : ")
            if 1 <= self.gol <= 4:
                break

        while True:
            print("Waktu Datang :")
            self.datang.input_dalam()
            print("Waktu Pulang :")
            self.pulang.input_dalam()
            if self.pulang.total_detik() > self.datang.total_detik():
                break
            print("Waktu pulang harus setelah waktu datang, ulangi!")

    def set_nip(self, n):
        self.nip = n

    def set_nama(self, n):
        self.nama = n

    def set_gol(self, g):
        self.gol = g

    def set_datang(self, d):
        self.datang = d

    def set_pulang(self, p):
        self.pulang = p

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
        print(f"{no:<3}{self.nip:<7}{self.nama:<13}{self.gol:<5}"
              f"{self.datang.to_string():<10}{self.pulang.to_string():<10}"
              f"{self.lama_kerja.to_string():<10}{self.jam_lembur.to_string():<12}"
              f"{self._rupiah(self.gaji_harian):<13}{self._rupiah(self.lembur):<10}"
              f"{self._rupiah(self.total):<10}{self.status_peringatan}")


class Menu:
    def tampil_menu(self):
        p1 = p2 = p3 = p4 = None

        while True:
            print("\nMENU GAJI HARIAN PT INFORMATIKA")
            print(" 1. Pegawai 1 : via setter(hardcode)")
            print(" 2. Pegawai 2 : via constructor berparameter(hardcode)")
            print(" 3. Pegawai 3 : input dalam class")
            print(" 4. Pegawai 4 : input luar class (Main)")
            print(" 5. Tampilkan daftar gaji harian")
            print(" 0. Keluar")

            pilih = baca_int("Pilih menu: ")

            if pilih == 1:
                p1 = Pegawai()
                datang1 = Waktu(8, 0, 0)
                pulang1 = Waktu(17, 15, 10)
                p1.set_nip("250001")
                p1.set_nama("Ali")
                p1.set_gol(3)
                p1.set_datang(datang1)
                p1.set_pulang(pulang1)
                p1.proses_gaji()
                print("Pegawai 1 berhasil diisi (setter).")

            elif pilih == 2:
                p2 = Pegawai("250002", "Budi", 1, Waktu(8, 0, 0), Waktu(15, 30, 0))
                p2.proses_gaji()
                print("Pegawai 2 berhasil diisi (constructor).")

            elif pilih == 3:
                print("\nPegawai 3")
                p3 = Pegawai()
                p3.input_pegawai()
                p3.proses_gaji()
                print("Pegawai 3 berhasil diisi (Scanner/input dalam class).")

            elif pilih == 4:
                print("\nPegawai 4")
                nip = input("Masukkan NIP  : ")
                nama = input("Masukkan Nama : ")

                while True:
                    gol = baca_int("Masukkan Gol (1-4) : ")
                    if 1 <= gol <= 4:
                        break

                datang4 = Waktu()
                pulang4 = Waktu()

                while True:
                    print("Waktu Datang :")
                    while True:
                        j = baca_int("   Jam   (0-23) : ")
                        if 0 <= j <= 23: break
                    while True:
                        m = baca_int("   Menit (0-59) : ")
                        if 0 <= m <= 59: break
                    while True:
                        d = baca_int("   Detik (0-59) : ")
                        if 0 <= d <= 59: break
                    datang4.set_waktu(j, m, d)

                    print("Waktu Pulang :")
                    while True:
                        j = baca_int("   Jam   (0-23) : ")
                        if 0 <= j <= 23: break
                    while True:
                        m = baca_int("   Menit (0-59) : ")
                        if 0 <= m <= 59: break
                    while True:
                        d = baca_int("   Detik (0-59) : ")
                        if 0 <= d <= 59: break
                    pulang4.set_waktu(j, m, d)

                    if pulang4.total_detik() > datang4.total_detik():
                        break
                    print("Waktu pulang harus setelah waktu datang, ulangi!")

                p4 = Pegawai()
                p4.set_nip(nip)
                p4.set_nama(nama)
                p4.set_gol(gol)
                p4.set_datang(datang4)
                p4.set_pulang(pulang4)
                p4.proses_gaji()
                print("Pegawai 4 berhasil diisi (Scanner/input luar class).")

            elif pilih == 5:
                if not any([p1, p2, p3, p4]):
                    print("Belum ada data pegawai!")
                    continue

                garis = "-" * 127
                print("\n                              Daftar Gaji Harian PT Informatika")
                print(garis)
                print(f"{'No':<3}{'NIP':<7}{'Nama':<13}{'Gol':<5}"
                      f"{'Datang':<10}{'Pulang':<10}{'Lama':<10}"
                      f"{'Jam Lembur':<12}{'Gaji Harian':<13}{'Lembur':<10}"
                      f"{'Total':<10}Status")
                print(garis)
                if p1: p1.print_pegawai(1)
                if p2: p2.print_pegawai(2)
                if p3: p3.print_pegawai(3)
                if p4: p4.print_pegawai(4)
                print(garis)

            elif pilih == 0:
                print("Terima kasih!")
                break
            else:
                print("Menu tidak tersedia!")


if __name__ == "__main__":
    menu = Menu()
    menu.tampil_menu()