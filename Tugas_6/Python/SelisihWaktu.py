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

    def set_jam(self, jam):
        self.jam = jam

    def set_menit(self, menit):
        self.menit = menit

    def set_detik(self, detik):
        self.detik = detik

    def input_dalam(self):
        print("Masukkan Waktu : ")
        self.jam = int(input("Jam   : "))
        self.menit = int(input("Menit : "))
        self.detik = int(input("Detik : "))

    def output_dalam(self):
        print(f"{self.jam} jam, {self.menit} menit, {self.detik} detik")

    def hitung_selisih_return(self, w2):
        total_detik1 = (self.jam * 3600) + (self.menit * 60) + self.detik
        total_detik2 = (w2.jam * 3600) + (w2.menit * 60) + w2.detik
        selisih_total = abs(total_detik1 - total_detik2)

        hasil = SelisihWaktu()
        hasil.jam = selisih_total // 3600
        selisih_total %= 3600
        hasil.menit = selisih_total // 60
        hasil.detik = selisih_total % 60

        return hasil

    def hitung_selisih_void(self, w1, w2):
        total_detik1 = (w1.jam * 3600) + (w1.menit * 60) + w1.detik
        total_detik2 = (w2.jam * 3600) + (w2.menit * 60) + w2.detik
        selisih_total = abs(total_detik1 - total_detik2)

        self.jam = selisih_total // 3600
        selisih_total %= 3600
        self.menit = selisih_total // 60
        self.detik = selisih_total % 60


def main():
    # 1. Objek 1: Input Menggunakan Setter 
    waktu1 = SelisihWaktu()
    waktu1.set_jam(8)
    waktu1.set_menit(30)
    waktu1.set_detik(0)

    # 2. Objek 2: Input Menggunakan Constructor Parameter
    waktu2 = SelisihWaktu(10, 15, 45)

    # 3. Objek 3: Input Menggunakan fungsi Input di Dalam Class
    waktu3 = SelisihWaktu()
    waktu3.input_dalam()

    print("\n--- Data Waktu ---")
    print("Waktu 1 (Setter): ", end="")
    waktu1.output_dalam()
    print("Waktu 2 (Constructor): ", end="")
    waktu2.output_dalam()
    print("Waktu 3 (Input Dalam): ", end="")
    waktu3.output_dalam()

    # Contoh Penggunaan Method Proses : Fungsi Return
    selisih = waktu1.hitung_selisih_return(waktu2)
    print("\nSelisih Waktu 1 dan Waktu 2 (Cara 1 - Return): ", end="")
    selisih.output_dalam()

    # Contoh Penggunaan Method Proses : Fungsi Void
    selisih_void = SelisihWaktu()
    selisih_void.hitung_selisih_void(waktu2, waktu3)
    print("Selisih Waktu 2 dan Waktu 3 (Cara 2 - Void): ", end="")
    selisih_void.output_dalam()

if __name__ == "__main__":
    main()