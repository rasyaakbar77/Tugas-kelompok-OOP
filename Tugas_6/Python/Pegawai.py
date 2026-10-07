"""
Nama Program : Pegawai.py
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 29-09-2026
Deskripsi Program : Sebuah program untuk menghitung gaji karyawan dengan input NIP, nama, gol, waktu datang, waktu pulang.
"""

def baca_int(pesan):
    while True:
        try:
            return int(input(pesan).strip())
        except ValueError:
            print("Masukkan angka yang valid!")


class Waktu:
    def __init__(self, jam=0, menit=0, detik=0):
        self.__jam = jam if 0 <= jam <= 23 else 0
        self.__menit = menit if 0 <= menit <= 59 else 0
        self.__detik = detik if 0 <= detik <= 59 else 0

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

    def set_waktu(self, jam, menit, detik):
        self.__jam = jam if 0 <= jam <= 23 else 0
        self.__menit = menit if 0 <= menit <= 59 else 0
        self.__detik = detik if 0 <= detik <= 59 else 0

    def get_jam(self):
        return self.__jam

    def get_menit(self):
        return self.__menit

    def get_detik(self):
        return self.__detik

    def total_detik(self):
        return self.__jam * 3600 + self.__menit * 60 + self.__detik

    # Selisih Mengembalikan Objek (Fungsi)
    def selisih_fungsi(self, p):
        p_hasil = Waktu()
        sel = self.total_detik() - p.total_detik()
        if sel < 0:
            sel = 0
        p_hasil.set_waktu(sel // 3600, (sel % 3600) // 60, sel % 60)
        return p_hasil

    # Selisih Mengubah Instance Ini (Void)
    def selisih_void(self, p1, p2):
        sel = p1.total_detik() - p2.total_detik()
        if sel < 0:
            sel = 0
        self.set_waktu(sel // 3600, (sel % 3600) // 60, sel % 60)

    def __str__(self):
        return f"{self.__jam:02d}:{self.__menit:02d}:{self.__detik:02d}"


class Pegawai:
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
            if self.__pulang.total_detik() > self.__datang.total_detik():
                break
            print("Waktu pulang harus setelah waktu datang, ulangi!\n")

    def set_nip(self, nip): self.__nip = nip
    def set_nama(self, nama): self.__nama = nama
    def set_gol(self, gol): self.__gol = gol
    def set_datang(self, datang): self.__datang = datang
    def set_pulang(self, pulang): self.__pulang = pulang

    def proses_gaji(self):
        self.__lama_kerja = self.__pulang.selisih_fungsi(self.__datang)

        batas = Waktu(8, 0, 0)
        if self.__lama_kerja.total_detik() >= batas.total_detik():
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

    def print_pegawai(self, no):
        print(f"{no:<3} {self.__nip:<7} {self.__nama:<12} {self.__gol:<4} "
              f"{str(self.__datang):<9} {str(self.__pulang):<9} {str(self.__lama_kerja):<9} "
              f"{str(self.__jam_lembur):<11} {self.__rupiah(self.__gaji_harian):<12} "
              f"{self.__rupiah(self.__lembur):<10} {self.__rupiah(self.__total):<12} {self.__status_peringatan}")

    def __rupiah(self, nilai):
        return f"{nilai:,}".replace(",", ".")


def main():
    p1 = p2 = p3 = p4 = None

    while True:
        print("\nMENU GAJI HARIAN PT INFORMATIKA")
        print(" 1. Pegawai 1 : via setter (hardcode)")
        print(" 2. Pegawai 2 : via constructor berparameter (hardcode)")
        print(" 3. Pegawai 3 : input dalam class")
        print(" 4. Pegawai 4 : input luar class (Main)")
        print(" 5. Tampilkan daftar gaji harian")
        print(" 0. Keluar")
        pilih = baca_int("Pilih menu: ")

        if pilih == 1:
            p1 = Pegawai()
            p1.set_nip("250001")
            p1.set_nama("Ali")
            p1.set_gol(3)
            p1.set_datang(Waktu(8, 0, 0))
            p1.set_pulang(Waktu(17, 15, 10))
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
            print("Pegawai 3 berhasil diisi (input dalam class).")

        elif pilih == 4:
            print("\nPegawai 4")
            nip = input("Masukkan NIP  : ")
            nama = input("Masukkan Nama : ")

            while True:
                gol = baca_int("Masukkan Gol (1-4) : ")
                if 1 <= gol <= 4:
                    break

            datang4, pulang4 = Waktu(), Waktu()

            while True:
                print("Waktu Datang :")
                datang4.input_waktu()
                print("Waktu Pulang :")
                pulang4.input_waktu()

                if pulang4.total_detik() > datang4.total_detik():
                    break
                print("Waktu pulang harus setelah waktu datang, ulangi!\n")

            p4 = Pegawai()
            p4.set_nip(nip)
            p4.set_nama(nama)
            p4.set_gol(gol)
            p4.set_datang(datang4)
            p4.set_pulang(pulang4)
            p4.proses_gaji()
            print("Pegawai 4 berhasil diisi (input luar class).")

        elif pilih == 5:
            if not any([p1, p2, p3, p4]):
                print("Belum ada data pegawai!")
                continue

            garis = "-" * 115
            print("\n                              Daftar Gaji Harian PT Informatika")
            print(garis)
            print(f"{'No':<3} {'NIP':<7} {'Nama':<12} {'Gol':<4} {'Datang':<9} {'Pulang':<9} {'Lama':<9} "
                  f"{'Jam Lembur':<11} {'Gaji Harian':<12} {'Lembur':<10} {'Total':<12} Status")
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
    main()