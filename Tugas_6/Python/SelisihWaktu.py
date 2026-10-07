"""
Nama Program : SelisihWaktu.py
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 29-09-2026
Deskripsi Program : Sebuah program untuk mencari selisih antara waktu datang dan waktu keluar dengan metode OOP, 
                    menggunakan 3 objek denga 3 cara input berbeda da 2 method proses (selisih waktu) dengan 
                    rerturn value yang berbeda (fungsi dan void).
"""

class SelisihWaktu:
    def __init__(self, jam=0, menit=0, detik=0):
        self.jam = jam
        self.menit = menit
        self.detik = detik

    @staticmethod
    def baca_int(pesan):
        while True:
            try:
                return int(input(pesan))
            except ValueError:
                print("Masukkan angka yang valid!")

    def set_jam(self, jam):
        self.jam = jam

    def set_menit(self, menit):
        self.menit = menit

    def set_detik(self, detik):
        self.detik = detik

    def get_jam(self):
        return self.jam

    def get_menit(self):
        return self.menit

    def get_detik(self):
        return self.detik

    def input_dalam(self):
        while True:
            self.jam = self.baca_int("   Jam   (0-23) : ")
            if 0 <= self.jam <= 23:
                break
        while True:
            self.menit = self.baca_int("   Menit (0-59) : ")
            if 0 <= self.menit <= 59:
                break
        while True:
            self.detik = self.baca_int("   Detik (0-59) : ")
            if 0 <= self.detik <= 59:
                break

    def output_dalam(self):
        print(f"Waktu = {self.jam:02d}:{self.menit:02d}:{self.detik:02d}")

    def hitung_selisih_fungsi(self, w2):
        total_detik1 = (self.jam * 3600) + (self.menit * 60) + self.detik
        total_detik2 = (w2.jam * 3600) + (w2.menit * 60) + w2.detik
        selisih_total = abs(total_detik1 - total_detik2)

        hasil = SelisihWaktu()
        hasil.jam = selisih_total // 3600
        hasil.menit = (selisih_total % 3600) // 60
        hasil.detik = selisih_total % 60
        return hasil

    def hitung_selisih_void(self, w1, w2):
        total_detik1 = (w1.jam * 3600) + (w1.menit * 60) + w1.detik
        total_detik2 = (w2.jam * 3600) + (w2.menit * 60) + w2.detik
        selisih_total = abs(total_detik1 - total_detik2)

        self.jam = selisih_total // 3600
        self.menit = (selisih_total % 3600) // 60
        self.detik = selisih_total % 60


class Menu:
    def jalankan_menu(self):
        waktu1 = SelisihWaktu()
        waktu1.set_jam(2)
        waktu1.set_menit(3)
        waktu1.set_detik(4)

        waktu2 = SelisihWaktu(4, 5, 6)

        waktu3 = SelisihWaktu()
        print("Masukkan Waktu 3 (Input Dalam):")
        waktu3.input_dalam()

        while True:
            print("\n===============================================================")
            print("                    MENU UTAMA SELISIH WAKTU                   ")
            print("===============================================================")
            print("1. Tampilkan Semua Data Waktu")
            print("2. Ubah Data Waktu")
            print("3. Hitung Selisih Waktu (Fungsi Return)")
            print("4. Hitung Selisih Waktu (Void)")
            print("5. Keluar Program")
            print("===============================================================")

            pilihan = SelisihWaktu.baca_int("Pilih menu (1-5): ")
            print("===============================================================")

            if pilihan == 1:
                print("\n==============================================================")
                print("                       DATA WAKTU SAAT INI                    ")
                print("==============================================================")
                print("Waktu 1 (Setter)      : ", end="")
                waktu1.output_dalam()
                print("Waktu 2 (Constructor) : ", end="")
                waktu2.output_dalam()
                print("Waktu 3 (Input Dalam) : ", end="")
                waktu3.output_dalam()

            elif pilihan == 2:
                print("\n==============================================================")
                pilih_waktu = SelisihWaktu.baca_int("Pilih waktu yang ingin diubah (1/2/3): ")
                print("==============================================================")
                if pilih_waktu == 1:
                    print("Edit Waktu 1:")
                    waktu1.input_dalam()
                elif pilih_waktu == 2:
                    print("Edit Waktu 2:")
                    waktu2.input_dalam()
                elif pilih_waktu == 3:
                    print("Edit Waktu 3:")
                    waktu3.input_dalam()
                else:
                    print("Pilihan waktu tidak valid!")
                print("==============================================================\n")

            elif pilihan == 3:
                print("\n==============================================================")
                print(" HITUNG SELISIH (FUNGSI RETURN) ")
                print("Pilih objek yang akan diselisihkan:")
                print("1. Waktu 1 dan Waktu 2\n2. Waktu 1 dan Waktu 3\n3. Waktu 2 dan Waktu 3")
                print("==============================================================\n")
                sub_pilih = SelisihWaktu.baca_int("Pilihan (1-3): ")

                if sub_pilih == 1:
                    hasil_selisih = waktu1.hitung_selisih_fungsi(waktu2)
                    print("Selisih Waktu 1 dan Waktu 2 = ", end="")
                elif sub_pilih == 2:
                    hasil_selisih = waktu1.hitung_selisih_fungsi(waktu3)
                    print("Selisih Waktu 1 dan Waktu 3 = ", end="")
                elif sub_pilih == 3:
                    hasil_selisih = waktu2.hitung_selisih_fungsi(waktu3)
                    print("Selisih Waktu 2 dan Waktu 3 = ", end="")
                else:
                    print("Pilihan tidak valid!\n\n==============================================================\n")
                    continue

                hasil_selisih.output_dalam()
                print("==============================================================\n")

            elif pilihan == 4:
                print("\n==============================================================")
                print("                HITUNG SELISIH (METHOD VOID)                  ")
                print("==============================================================")
                print("Pilih objek yang akan diselisihkan:")
                print("1. Waktu 1 & Waktu 2\n2. Waktu 1 & Waktu 3\n3. Waktu 2 & Waktu 3")
                print("==============================================================\n")
                sub_pilih = SelisihWaktu.baca_int("Pilihan (1-3): ")

                hasil_void = SelisihWaktu()
                if sub_pilih == 1:
                    hasil_void.hitung_selisih_void(waktu1, waktu2)
                    print("Hasil Selisih (diobjek baru via Void) Waktu 1 & 2 = ", end="")
                elif sub_pilih == 2:
                    hasil_void.hitung_selisih_void(waktu1, waktu3)
                    print("Hasil Selisih (diobjek baru via Void) Waktu 1 & 3 = ", end="")
                elif sub_pilih == 3:
                    hasil_void.hitung_selisih_void(waktu2, waktu3)
                    print("Hasil Selisih (diobjek baru via Void) Waktu 2 & 3 = ", end="")
                else:
                    print("Pilihan tidak valid!\n\n==============================================================\n")
                    continue

                hasil_void.output_dalam()
                print("==============================================================\n")

            elif pilihan == 5:
                print("Terima kasih telah menggunakan program ini!\n")
                break
            else:
                print("Pilihan tidak valid, silakan coba lagi.\n")


if __name__ == "__main__":
    menu = Menu()
    menu.jalankan_menu()