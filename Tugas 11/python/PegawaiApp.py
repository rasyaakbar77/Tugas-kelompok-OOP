import math

class Util:
    @staticmethod
    def baca_int(pesan):
        while True:
            try:
                val = int(input(pesan))
                return val
            except ValueError:
                print("Masukkan angka yang valid!")

    @staticmethod
    def baca_string(pesan):
        return input(pesan)

    @staticmethod
    def rupiah(nilai):
        s = str(nilai)
        n = len(s)
        parts = []
        for i in range(n, 0, -3):
            parts.insert(0, s[max(0, i - 3):i])
        return ".".join(parts)


class Waktu:
    def __init__(self, jam=0, menit=0, detik=0):
        self.jam = jam
        self.menit = menit
        self.detik = detik

    def set_waktu(self, j, m, d):
        self.jam = j
        self.menit = m
        self.detik = d

    def input_dalam(self):
        while True:
            self.jam = Util.baca_int("   Jam   (0-23) : ")
            if 0 <= self.jam <= 23:
                break
        while True:
            self.menit = Util.baca_int("   Menit (0-59) : ")
            if 0 <= self.menit <= 59:
                break
        while True:
            self.detik = Util.baca_int("   Detik (0-59) : ")
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


class Person:
    def __init__(self, nama="", alamat=""):
        self.nama = nama
        self.alamat = alamat

    def input_dalam(self):
        self.nama = Util.baca_string("Nama   : ")
        self.alamat = Util.baca_string("Alamat : ")

    def print_person(self, no):
        print(f"{no:<3} {self.nama:<13} {self.alamat:<17}", end="")


class Pegawai(Person):
    def __init__(self, nip="", nama="", alamat="", gol=1):
        super().__init__(nama, alamat)
        self.gol = gol
        self.nip = nip
        self.datang = Waktu()
        self.pulang = Waktu()
        self.lama_kerja = Waktu()
        self.jam_lembur = Waktu()
        self.status_peringatan = ""

    def input_pegawai(self):
        self.nip = Util.baca_string("NIP     : ")
        super().input_dalam()

        while True:
            self.gol = Util.baca_int("Masukkan Gol (1-4) : ")
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

    def get_nip(self):
        return self.nip

    def get_gaji_harian(self):
        if self.gol == 1: return 150000
        elif self.gol == 2: return 200000
        elif self.gol == 3: return 400000
        elif self.gol == 4: return 500000
        return 0

    def get_tarif_lembur(self):
        if self.gol == 1: return 50000
        elif self.gol == 2: return 75000
        elif self.gol == 3: return 150000
        elif self.gol == 4: return 200000
        return 0

    def get_lembur(self):
        return self.get_tarif_lembur() * self.jam_lembur.total_detik() // 3600

    def get_total(self):
        return self.get_gaji_harian() + self.get_lembur()

    def proses_gaji(self):
        self.lama_kerja = self.pulang.hitung_selisih_fungsi(self.datang)
        batas = Waktu(8, 0, 0)
        if self.lama_kerja.total_detik() >= batas.total_detik():
            self.jam_lembur.hitung_selisih_void(self.lama_kerja, batas)
            self.status_peringatan = "ok"
        else:
            self.jam_lembur = Waktu()
            self.status_peringatan = "Peringatan"

    def print_pegawai(self, no):
        self.print_person(no)
        print(f"{self.nip:<12} {self.gol:>5} {self.datang.to_string():>10} {self.pulang.to_string():>10} "
              f"{self.lama_kerja.to_string():>12} {self.jam_lembur.to_string():>12} "
              f"{Util.rupiah(self.get_gaji_harian()):>15} {Util.rupiah(self.get_lembur()):>15} "
              f"{Util.rupiah(self.get_total()):>15}  {self.status_peringatan:<12}")


class ArrayPegawai:
    def __init__(self):
        self.max = 100
        self.arr = []
        self.jumlah = 0

    def get_jumlah(self):
        return self.jumlah

    def input_dalam(self):
        if self.jumlah >= self.max:
            print("\nKapasitas data pegawai sudah penuh (maksimal 100)!")
            return

        sisa_kapasitas = self.max - self.jumlah
        while True:
            tambahan = Util.baca_int(f"Masukkan jumlah pegawai yang ingin ditambahkan (1-{sisa_kapasitas}): ")
            if 1 <= tambahan <= sisa_kapasitas:
                break
            print("Jumlah tidak valid!")

        for i in range(tambahan):
            print(f"\nData Pegawai ke-{self.jumlah + 1}")
            p = Pegawai()
            p.input_pegawai()
            self.arr.append(p)
            self.jumlah += 1
        print(f"\nBerhasil menambahkan {tambahan} data pegawai baru!")

    def proses_data(self):
        for i in range(self.jumlah):
            self.arr[i].proses_gaji()

    def get_pegawai_at(self, index):
        if 0 <= index < self.jumlah:
            return self.arr[index]
        return None

    def output_data(self):
        print("\n\t\t\t\t\tDaftar Gaji Harian PT Informatika")
        print("=" * 150)
        print(f"{'No':<3} {'Nama':<13} {'Alamat':<17} {'NIP':<12} {'Gol':>5} {'Datang':>10} {'Pulang':>10} "
              f"{'Lama':>12} {'Jam Lembur':>12} {'Gaji Harian':>15} {'Lembur':>15} {'Total':>15}  {'Status':<12}")
        print("=" * 150)

        total_semua = 0
        max_gaji = self.arr[0].get_total() if self.jumlah > 0 else 0
        min_gaji = self.arr[0].get_total() if self.jumlah > 0 else 0

        for i in range(self.jumlah):
            self.arr[i].print_pegawai(i + 1)
            t = self.arr[i].get_total()
            total_semua += t
            if t > max_gaji: max_gaji = t
            if t < min_gaji: min_gaji = t

        print("=" * 150)
        rata_rata = (total_semua / self.jumlah) if self.jumlah > 0 else 0
        print(f"Rata-rata Gaji : {Util.rupiah(int(rata_rata)) if self.jumlah > 0 else '---'}")
        print(f"Gaji Tertinggi : {Util.rupiah(max_gaji) if self.jumlah > 0 else '---'}")
        print(f"Gaji Terendah  : {Util.rupiah(min_gaji) if self.jumlah > 0 else '---'}")


class Kendaraan:
    def __init__(self, merk="", plat="", warna=""):
        self.merk = merk
        self.plat = plat
        self.warna = warna
        self.nip_pemilik = ""
        self.nama_pemilik = ""
        self.masuk = Waktu()
        self.keluar = Waktu()

    def set_merk(self, m): self.merk = m
    def set_plat(self, p): self.plat = p
    def set_warna(self, w): self.warna = w
    def get_merk(self): return self.merk
    def get_plat(self): return self.plat
    def get_warna(self): return self.warna

    def set_pemilik(self, nip, nama):
        self.nip_pemilik = nip
        self.nama_pemilik = nama

    def get_nip_pemilik(self): return self.nip_pemilik
    def get_nama_pemilik(self): return self.nama_pemilik

    def set_masuk(self, w): self.masuk = w
    def get_masuk(self): return self.masuk

    def is_pegawai(self):
        return bool(self.nip_pemilik)

    def is_sudah_keluar(self):
        return self.keluar.total_detik() > 0

    def set_keluar(self, w):
        if w.total_detik() <= self.masuk.total_detik():
            return False
        self.keluar = w
        return True

    def get_keluar(self): return self.keluar

    def input_subclass(self): pass
    def hitung_biaya(self, db): pass
    def get_jenis_kendaraan(self): pass


class Motor(Kendaraan):
    def __init__(self, merk="", plat="", warna="", ada_footstep=False):
        super().__init__(merk, plat, warna)
        self.ada_footstep = ada_footstep

    def get_jenis_kendaraan(self):
        return "Motor"

    def input_subclass(self):
        while True:
            inp = Util.baca_string("Apakah ada footstep? (y/n): ")
            if inp in ['y', 'Y']:
                self.ada_footstep = True
                break
            elif inp in ['n', 'N']:
                self.ada_footstep = False
                break
            else:
                print("Input tidak valid. Silakan masukkan 'y' atau 'n'.")

    def hitung_biaya(self, db):
        total_detik = self.keluar.total_detik() - self.masuk.total_detik()
        if total_detik < 0: total_detik = 0

        total_jam = (total_detik + 3599) // 3600
        if total_jam < 1: total_jam = 1

        valid_pegawai = False
        if self.nip_pemilik:
            for i in range(db.get_jumlah()):
                if db.get_pegawai_at(i).get_nip() == self.nip_pemilik:
                    valid_pegawai = True
                    break

        if valid_pegawai:
            total_hari = (total_detik + 86399) // 86400
            if total_hari < 1: total_hari = 1
            return total_hari * 3000
        else:
            return total_jam * 2000


class Mobil(Kendaraan):
    def __init__(self, merk="", plat="", warna="", jumlah_pintu=4, ada_sunroof=False):
        super().__init__(merk, plat, warna)
        self.jumlah_pintu = jumlah_pintu
        self.ada_sunroof = ada_sunroof

    def get_jenis_kendaraan(self):
        return "Mobil"

    def input_subclass(self):
        self.jumlah_pintu = Util.baca_int("Jumlah Pintu : ")
        while True:
            inp = Util.baca_string("Apakah ada sunroof? (y/n): ")
            if inp in ['y', 'Y']:
                self.ada_sunroof = True
                break
            elif inp in ['n', 'N']:
                self.ada_sunroof = False
                break
            else:
                print("Input tidak valid. Silakan masukkan 'y' atau 'n'.")

    def hitung_biaya(self, db):
        total_detik = self.keluar.total_detik() - self.masuk.total_detik()
        if total_detik < 0: total_detik = 0

        total_jam = (total_detik + 3599) // 3600
        if total_jam < 1: total_jam = 1

        valid_pegawai = False
        if self.nip_pemilik:
            for i in range(db.get_jumlah()):
                if db.get_pegawai_at(i).get_nip() == self.nip_pemilik:
                    valid_pegawai = True
                    break

        if valid_pegawai:
            total_hari = (total_detik + 86399) // 86400
            if total_hari < 1: total_hari = 1
            return total_hari * 6000
        else:
            return total_jam * 3000


class Truk(Kendaraan):
    def __init__(self, merk="", plat="", warna="", muatan=5.0, rem_angin=True):
        super().__init__(merk, plat, warna)
        self.kapasitas_muatan_ton = muatan
        self.ada_rem_angin = rem_angin

    def get_jenis_kendaraan(self):
        return "Truk"

    def input_subclass(self):
        while True:
            try:
                self.kapasitas_muatan_ton = float(Util.baca_string("Kapasitas Muatan (Ton) : "))
                break
            except ValueError:
                print("Masukkan angka yang valid!")

        while True:
            inp = Util.baca_string("Apakah ada rem angin? (y/n): ")
            if inp in ['y', 'Y']:
                self.ada_rem_angin = True
                break
            elif inp in ['n', 'N']:
                self.ada_rem_angin = False
                break
            else:
                print("Input tidak valid. Silakan masukkan 'y' atau 'n'.")

    def hitung_biaya(self, db):
        total_detik = self.keluar.total_detik() - self.masuk.total_detik()
        if total_detik < 0: total_detik = 0

        total_jam = (total_detik + 3599) // 3600
        if total_jam < 1: total_jam = 1

        return total_jam * 5000


class Parkir:
    def __init__(self):
        self.max_kendaraan = 100
        self.arr = [None] * self.max_kendaraan
        self.jumlah_kendaraan = 0

    def is_plat_aktif(self, plat):
        for i in range(self.jumlah_kendaraan):
            if not self.arr[i].is_sudah_keluar() and self.arr[i].get_plat().upper() == plat.upper():
                return True
        return False

    def input_kendaraan_masuk(self, db_pegawai):
        if self.jumlah_kendaraan >= self.max_kendaraan:
            print("\nArea parkir sudah penuh (maksimal 100)!")
            return

        while True:
            pilihan = Util.baca_int("\nPilih Jenis Kendaraan:\n1. Motor\n2. Mobil\n3. Truk\nPilihan (1-3): ")
            if 1 <= pilihan <= 3:
                break

        plat_temp = Util.baca_string("No. Plat : ")
        if self.is_plat_aktif(plat_temp):
            print("Error: Plat nomor tersebut masih aktif terparkir!")
            return

        k_baru = None
        if pilihan == 1: k_baru = Motor()
        elif pilihan == 2: k_baru = Mobil()
        elif pilihan == 3: k_baru = Truk()

        k_baru.set_plat(plat_temp)
        k_baru.set_merk(Util.baca_string("Merk  : "))
        k_baru.set_warna(Util.baca_string("Warna : "))
        
        k_baru.input_subclass()

        nip = Util.baca_string("NIP Pemilik (kosongkan jika umum): ")
        nama_pemilik_str = "Umum / Tamu"
        if nip:
            for i in range(db_pegawai.get_jumlah()):
                if db_pegawai.get_pegawai_at(i).get_nip() == nip:
                    nama_pemilik_str = db_pegawai.get_pegawai_at(i).get_nama()
                    break

        k_baru.set_pemilik(nip, nama_pemilik_str)

        print("Waktu Masuk:")
        w_masuk = Waktu()
        w_masuk.input_dalam()
        k_baru.set_masuk(w_masuk)

        self.arr[self.jumlah_kendaraan] = k_baru
        self.jumlah_kendaraan += 1
        print("Kendaraan berhasil masuk area parkir!")

    def proses_kendaraan_keluar(self, db_pegawai): 
        aktif_count = 0
        print("\n--- KENDARAAN YANG MASIH TERPARKIR ---")
        for i in range(self.jumlah_kendaraan):
            if not self.arr[i].is_sudah_keluar():
                print(f"{i + 1}. [{self.arr[i].get_jenis_kendaraan()}] Plat: {self.arr[i].get_plat()} (Masuk: {self.arr[i].get_masuk().to_string()})")
                aktif_count += 1

        if aktif_count == 0:
            print("Tidak ada kendaraan yang sedang terparkir.")
            return

        pilih = Util.baca_int("Pilih nomor kendaraan yang akan keluar: ")
        if pilih < 1 or pilih > self.jumlah_kendaraan or self.arr[pilih - 1].is_sudah_keluar():
            print("Pilihan tidak valid.")
            return

        k = self.arr[pilih - 1]
        w_keluar = Waktu()
        while True:
            print("Waktu Keluar:")
            w_keluar.input_dalam()
            if not k.set_keluar(w_keluar):
                print("Waktu keluar harus setelah waktu masuk, ulangi!")
            else:
                break

        total_biaya = k.hitung_biaya(db_pegawai)
        print("\nKendaraan berhasil keluar!")
        print(f"Total Biaya Parkir : Rp {Util.rupiah(int(total_biaya))}")

    def tampilkan_laporan_parkir(self, db_pegawai):
        if self.jumlah_kendaraan == 0:
            print("\nBelum ada data parkir.")
            return

        print("\n" + "\t" * 4 + "LAPORAN DATA PARKIR KENDARAAN")
        print("=" * 110)
        print(f"{'No':<4} {'Jenis':<10} {'Plat':<12} {'Merk':<15} {'Pemilik':<15} {'Masuk':<12} {'Keluar':<12} {'Status':<12}")
        print("=" * 110)

        total_pendapatan = 0
        for i in range(self.jumlah_kendaraan):
            status = "Keluar" if self.arr[i].is_sudah_keluar() else "Terparkir"
            jam_keluar = self.arr[i].get_keluar().to_string() if self.arr[i].is_sudah_keluar() else "---"

            print(f"{(i + 1):<4} {self.arr[i].get_jenis_kendaraan():<10} {self.arr[i].get_plat():<12} "
                  f"{self.arr[i].get_merk():<15} {self.arr[i].get_nama_pemilik():<15} "
                  f"{self.arr[i].get_masuk().to_string():<12} {jam_keluar:<12} {status:<12}", end="")

            if self.arr[i].is_sudah_keluar():
                biaya_item = self.arr[i].hitung_biaya(db_pegawai)
                print(f" | Biaya: Rp {Util.rupiah(int(biaya_item))}", end="")
            print()

        print("=" * 110)
        print(f"Total Pendapatan Parkir : Rp {Util.rupiah(int(total_pendapatan))}")


class Menu:
    def __init__(self):
        self.db_pegawai = ArrayPegawai()
        self.db_parkir = Parkir()

    def jalankan(self):
        while True:
            print("\n==========================================================")
            print("            MENU GAJI & PARKIR PT INFORMATIKA             ")
            print("==========================================================")
            print("1. Input Data Pegawai")
            print("2. Tampilkan Daftar Gaji")
            print("3. Kendaraan Masuk")
            print("4. Kendaraan Keluar")
            print("5. Laporan Parkir")
            print("6. Keluar")
            print("----------------------------------------------------------")

            pilihan = Util.baca_int("Pilih menu (1-6): ")
            print()

            if pilihan == 1:
                self.db_pegawai.input_dalam()
            elif pilihan == 2:
                self.db_pegawai.proses_data()
                self.db_pegawai.output_data()
            elif pilihan == 3:
                self.db_parkir.input_kendaraan_masuk(self.db_pegawai)
            elif pilihan == 4:
                self.db_parkir.proses_kendaraan_keluar(self.db_pegawai)
            elif pilihan == 5:
                self.db_parkir.tampilkan_laporan_parkir(self.db_pegawai)
            elif pilihan == 6:
                print("Terima kasih telah menggunakan sistem Gaji & Parkir PT Informatika!")
                break
            else:
                print("Pilihan tidak valid! Silakan masukkan angka 1 sampai 6.")

            input("\nTekan Enter untuk kembali ke menu...")


if __name__ == "__main__":
    app = Menu()
    app.jalankan()