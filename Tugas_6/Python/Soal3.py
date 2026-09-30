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
    def __init__(self, jam=0, menit=0, detik=0):
        # Sekaligus bertindak sebagai default dan parameter constructor dengan validasi
        self.jam = jam if 0 <= jam <= 23 else 0
        self.menit = menit if 0 <= menit <= 59 else 0
        self.detik = detik if 0 <= detik <= 59 else 0

    def input_waktu(self):
        while True:
            self.jam = baca_int("   Jam   (0-23) : ")
            if 0 <= self.jam <= 23: break
            
        while True:
            self.menit = baca_int("   Menit (0-59) : ")
            if 0 <= self.menit <= 59: break
            
        while True:
            self.detik = baca_int("   Detik (0-59) : ")
            if 0 <= self.detik <= 59: break

    def set_waktu(self, jam, menit, detik):
        self.jam = jam if 0 <= jam <= 23 else 0
        self.menit = menit if 0 <= menit <= 59 else 0
        self.detik = detik if 0 <= detik <= 59 else 0

    def set_jam(self, jam):
        self.jam = jam if 0 <= jam <= 23 else 0

    def set_menit(self, menit):
        self.menit = menit if 0 <= menit <= 59 else 0

    def set_detik(self, detik):
        self.detik = detik if 0 <= detik <= 59 else 0

    def get_jam(self): return self.jam
    def get_menit(self): return self.menit
    def get_detik(self): return self.detik

    def total_detik(self):
        return self.jam * 3600 + self.menit * 60 + self.detik

    def selisih(self, p):
        p_hasil = Waktu()
        sel = self.total_detik() - p.total_detik()
        if sel < 0: sel = 0
        p_hasil.jam = sel // 3600
        p_hasil.menit = (sel % 3600) // 60
        p_hasil.detik = sel % 60
        return p_hasil

    def __str__(self):
        return f"{self.jam:02d}:{self.menit:02d}:{self.detik:02d}"

    def print_waktu(self):
        print(f" Waktu = {self}")


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
            self.gol = baca_int("Masukkan Gol (1-4) : ")
            if 1 <= self.gol <= 4: break

        while True:
            print("Waktu Datang :")
            self.datang.input_waktu()
            print("Waktu Pulang :")
            self.pulang.input_waktu()
            
            if self.pulang.total_detik() <= self.datang.total_detik():
                print(" [!] Waktu pulang harus setelah waktu datang, ulangi!")
            else:
                break

    def set_pegawai(self, nip, nama, gol, datang, pulang):
        self.nip = nip
        self.nama = nama
        self.gol = gol
        self.datang = datang
        self.pulang = pulang

    def set_nip(self, nip): self.nip = nip
    def set_nama(self, nama): self.nama = nama
    def set_gol(self, gol): self.gol = gol
    def set_datang(self, datang): self.datang = datang
    def set_pulang(self, pulang): self.pulang = pulang

    def get_nip(self): return self.nip
    def get_nama(self): return self.nama
    def get_gol(self): return self.gol
    def get_total(self): return self.total
    def get_status_peringatan(self): return self.status_peringatan

    def proses_gaji(self):
        self.lama_kerja = self.pulang.selisih(self.datang)
        batas = Waktu(8, 0, 0)
        
        if self.lama_kerja.total_detik() >= batas.total_detik():
            self.jam_lembur = self.lama_kerja.selisih(batas)
            self.status_peringatan = "ok"
        else:
            self.jam_lembur = Waktu()
            self.status_peringatan = "Peringatan"

        tarif = 0
        if self.gol == 1:
            self.gaji_harian, tarif = 150000, 50000
        elif self.gol == 2:
            self.gaji_harian, tarif = 200000, 75000
        elif self.gol == 3:
            self.gaji_harian, tarif = 400000, 150000
        elif self.gol == 4:
            self.gaji_harian, tarif = 500000, 200000

        self.lembur = self.jam_lembur.get_jam() * tarif
        self.total = self.gaji_harian + self.lembur

    def rupiah(self, nilai):
        return f"{nilai:,}".replace(',', '.')

    def print_pegawai(self, no):
        print(f"{no:<3} {self.nip:<6} {self.nama:<12} {self.gol:<4} "
              f"{str(self.datang):<9} {str(self.pulang):<9} {str(self.lama_kerja):<9} "
              f"{str(self.jam_lembur):<11} {self.rupiah(self.gaji_harian):<12} "
              f"{self.rupiah(self.lembur):<9} {self.rupiah(self.total):<9} {self.status_peringatan}")


def baca_int(pesan):
    while True:
        try:
            return int(input(pesan).strip())
        except ValueError:
            print(" [!] Masukkan angka yang valid!")


def main():
    p1 = p2 = p3 = p4 = None

    while True:
        print("\nMENU GAJI HARIAN PT INFORMATIKA")
        print(" 1. Pegawai 1 : via setter(hardcode)")
        print(" 2. Pegawai 2 : via constructor berparameter(hardcode)")
        print(" 3. Pegawai 3 : input Scanner di dalam class")
        print(" 4. Pegawai 4 : input Scanner di luar class (Main)")
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
            print("Pegawai 3 berhasil diisi (Scanner dalam class).")

        elif pilih == 4:
            print("\nPegawai 4")
            nip = input("Masukkan NIP  : ")
            nama = input("Masukkan Nama : ")
            
            while True:
                gol = baca_int("Masukkan Gol (1-4) : ")
                if 1 <= gol <= 4: break

            datang4 = Waktu()
            pulang4 = Waktu()

            while True:
                print("Waktu Datang :")
                while True: 
                    j = baca_int("   Jam   (0-23) : "); 
                    if 0<=j<=23: break
                while True: 
                    m = baca_int("   Menit (0-59) : "); 
                    if 0<=m<=59: break
                while True: 
                    d = baca_int("   Detik (0-59) : "); 
                    if 0<=d<=59: break
                datang4.set_waktu(j, m, d)

                print("Waktu Pulang :")
                while True: 
                    j = baca_int("   Jam   (0-23) : "); 
                    if 0<=j<=23: break
                while True: 
                    m = baca_int("   Menit (0-59) : "); 
                    if 0<=m<=59: break
                while True: 
                    d = baca_int("   Detik (0-59) : "); 
                    if 0<=d<=59: break
                pulang4.set_waktu(j, m, d)

                if pulang4.total_detik() <= datang4.total_detik():
                    print(" [!] Waktu pulang harus setelah waktu datang, ulangi!")
                else:
                    break

            p4 = Pegawai(nip, nama, gol, datang4, pulang4)
            p4.proses_gaji()
            print("Pegawai 4 berhasil diisi (Scanner luar class).")

        elif pilih == 5:
            if not any([p1, p2, p3, p4]):
                print("Belum ada data pegawai!")
                continue

            garis = "-" * 127
            print("\n                              Daftar Gaji Harian PT Informatika")
            print(garis)
            print(f"{'No':<3} {'NIP':<6} {'Nama':<12} {'Gol':<4} {'Datang':<9} {'Pulang':<9} {'Lama':<9} "
                  f"{'Jam Lembur':<11} {'Gaji Harian':<12} {'Lembur':<9} {'Total':<9} Status")
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