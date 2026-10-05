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
        self._jam = jam
        self._menit = menit
        self._detik = detik

    # Validasi Input Interger
    @staticmethod
    def baca_int(pesan):
        while True:
            try:
                nilai = int(input(pesan))
                return nilai
            except ValueError:
                print("Masukkan angka yang valid!")

    # Setter Jam
    def set_jam(self, jam):
        self._jam = jam

    # Setter Menit
    def set_menit(self, menit):
        self._menit = menit

    # Setter Detik
    def set_detik(self, detik):
        self._detik = detik

    # Getter Jam
    def get_jam(self):
        return self._jam

    # Getter Menit
    def get_menit(self):
        return self._menit

    # Getter Detik
    def get_detik(self):
        return self._detik

    # Input Dalam
    def input_dalam(self):
        while True:
            self._jam = self.baca_int("   Jam   (0-23) : ")
            if 0 <= self._jam <= 23:
                break

        while True:
            self._menit = self.baca_int("   Menit (0-59) : ")
            if 0 <= self._menit <= 59:
                break

        while True:
            self._detik = self.baca_int("   Detik (0-59) : ")
            if 0 <= self._detik <= 59:
                break

    # Output Dalam 
    def output_dalam(self):
        print(f"Waktu = {self._jam:02d}:{self._menit:02d}:{self._detik:02d}")

    # Method Proses Cara 1: Fungsi Return
    def hitung_selisih_fungsi(self, w2):
        total_detik1 = (self._jam * 3600) + (self._menit * 60) + self._detik
        total_detik2 = (w2._jam * 3600) + (w2._menit * 60) + w2._detik
        selisih_total = abs(total_detik1 - total_detik2)

        hasil = SelisihWaktu()
        hasil._jam = selisih_total // 3600
        hasil._menit = (selisih_total % 3600) // 60
        hasil._detik = selisih_total % 60

        return hasil

    # Method Proses Cara 2: Void
    def hitung_selisih_void(self, w1, w2):
        total_detik1 = (w1._jam * 3600) + (w1._menit * 60) + w1._detik
        total_detik2 = (w2._jam * 3600) + (w2._menit * 60) + w2._detik
        selisih_total = abs(total_detik1 - total_detik2)

        self._jam = selisih_total // 3600
        self._menit = (selisih_total % 3600) // 60
        self._detik = selisih_total % 60

def main():
    # 1. Objek 1: Input Setter 
    waktu1 = SelisihWaktu() 
    waktu1.set_jam(2)
    waktu1.set_menit(3)
    waktu1.set_detik(4)

    # 2. Objek 2: Input Constructor Parameter
    waktu2 = SelisihWaktu(4, 5, 6)

    # 3. Objek 3: Input Fungsi dalam class
    waktu3 = SelisihWaktu()
    print("Masukkan Waktu 3 (Input Dalam):")
    waktu3.input_dalam()
    
    pilihan = 0
    while pilihan != 5:
        print("\n==============================================================")
        print("                    MENU UTAMA SELISIH WAKTU                  ")
        print("================================================================")
        print("1. Tampilkan Semua Data Waktu")
        print("2. Ubah Data Waktu")
        print("3. Hitung Selisih Waktu (Fungsi Return)")
        print("4. Hitung Selisih Waktu (Void)")
        print("5. Keluar Program")
        print("================================================================")
        
        pilihan = SelisihWaktu.baca_int("Pilih menu (1-5): ")
        print("==============================================================")

        if pilihan == 1:
            print("\n==============================================================")
            print("                       DATA WAKTU SAAT INI                    ")
            print("================================================================")
            print("Waktu 1 (Setter)      : ", end="") 
            waktu1.output_dalam()
            print("Waktu 2 (Constructor) : ", end="") 
            waktu2.output_dalam()
            print("Waktu 3 (Input Dalam) : ", end="") 
            waktu3.output_dalam()

        elif pilihan == 2:
            print("\n==============================================================")
            pilih_waktu = SelisihWaktu.baca_int("Pilih waktu yang ingin diubah (1/2/3): ")
            print("\n================================================================")
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
            print("================================================================")

        elif pilihan == 3:
            print("\n==============================================================")
            print("                  HITUNG SELISIH (FUNGSI RETURN)                ")
            print("================================================================")
            print("Pilih objek yang akan diselisihkan:")
            print("1. Waktu 1 dan Waktu 2\n2. Waktu 1 dan Waktu 3\n3. Waktu 2 dan Waktu 3")
            sub_pilih = SelisihWaktu.baca_int("Pilihan (1-3): ")
            
            hasil_selisih = None
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
                print("Pilihan tidak valid!\n")
                print("==============================================================")
                continue
            hasil_selisih.output_dalam()
            print("==============================================================")

        elif pilihan == 4:
            print("\n==============================================================")
            print("                HITUNG SELISIH (METHOD VOID)                  ")
            print("================================================================")
            print("Pilih objek yang akan diselisihkan:")
            print("1. Waktu 1 & Waktu 2\n2. Waktu 1 & Waktu 3\n3. Waktu 2 & Waktu 3")
            sub_pilih = SelisihWaktu.baca_int("Pilihan (1-3): ")
            
            hasil_void = SelisihWaktu()
            if sub_pilih == 1:
                hasil_void.hitung_selisih_void(waktu1, waktu2)
                print("Hasil Selisih (diobjek baru via Void) Waktu 1 dan Waktu 2 = ", end="")
            elif sub_pilih == 2:
                hasil_void.hitung_selisih_void(waktu1, waktu3)
                print("Hasil Selisih (diobjek baru via Void) Waktu 1 dan Waktu 3 = ", end="")
            elif sub_pilih == 3:
                hasil_void.hitung_selisih_void(waktu2, waktu3)
                print("Hasil Selisih (diobjek baru via Void) Waktu 2 dan Waktu 3 = ", end="")
            else:
                print("Pilihan tidak valid!\n")
                print("================================================================")
                continue
            hasil_void.output_dalam()
            print("==============================================================")

        elif pilihan == 5:
            print("Terima kasih telah menggunakan program ini!")
            break

        else:
            print("Pilihan tidak valid, silakan coba lagi.")


if __name__ == "__main__":
    main()