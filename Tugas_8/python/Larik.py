'''
Nama Program : Larik.java
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 08-10-2026
Deskripsi Program : Sebuah program untuk megimplementasikan array 1 dimensi dengan method untuk mencari :
                    1. Niai Max
                    2. Nilai Min
                    3. Nilai rata rata
                    4. Jumlah setiap elemen array
                    dengan 4 objek dan 3 class (kelas array, kelas menu dan kelas main)
'''

class MyLarik:
    def __init__(self, arg=10):
        if isinstance(arg, list):
            self.elemen = list(arg)
            self.ukuran = len(self.elemen)
        else:
            self.ukuran = arg
            self.elemen = [0] * self.ukuran

    def setUkuran(self, ukuran):
        self.ukuran = ukuran
        self.elemen = [0] * self.ukuran

    def setNilai(self, indeks, isi):
        if indeks >= len(self.elemen):
            self.elemen.extend([0] * (indeks - len(self.elemen) + 1))
        self.elemen[indeks] = isi

    def getUkuran(self):
        return self.ukuran

    def inputDalam(self):
        for i in range(self.ukuran):
            while True:
                try:
                    self.elemen[i] = int(input(f"Masukkan nilai ke-{i + 1} = "))
                    break
                except ValueError:
                    print("Masukkan angka yang valid!")

    def cariJumlah(self):
        return sum(self.elemen[:self.ukuran])

    def cariRata(self):
        if self.ukuran == 0:
            return 0.0
        return float(self.cariJumlah()) / self.ukuran

    def nilaiMax(self):
        return max(self.elemen[:self.ukuran])

    def nilaiMin(self):
        return min(self.elemen[:self.ukuran])

    def bubbleAsc(self):
        n = self.ukuran
        for i in range(n - 1):
            for j in range(n - 1 - i):
                if self.elemen[j] > self.elemen[j + 1]:
                    self.elemen[j], self.elemen[j + 1] = self.elemen[j + 1], self.elemen[j]

    def bubbleDesc(self):
        n = self.ukuran
        for i in range(n - 1):
            for j in range(n - 1 - i):
                if self.elemen[j] < self.elemen[j + 1]:
                    self.elemen[j], self.elemen[j + 1] = self.elemen[j + 1], self.elemen[j]

    def cetakLarik(self):
        print("Pencetakan Larik")
        for i in range(self.ukuran):
            print(f"Nilai ke-{i + 1} = {self.elemen[i]}")

    def cetakProses(self):
        print(f"Jumlah          = {self.cariJumlah()}")
        print(f"Rata-rata       = {self.cariRata()}")
        print(f"Nilai Tertinggi = {self.nilaiMax()}")
        print(f"Nilai Terendah  = {self.nilaiMin()}")


class MenuLarik:
    def __init__(self):
        #inisialisasi list objek array berukuran 4
        self.array = [None] * 4

    def inputInt(self, pesan):
        while True:
            try:
                return int(input(pesan))
            except ValueError:
                print("Masukkan angka yang valid!")

    def deklarasiObjek(self):
        # Objek A : input lewat constructor parameter
        self.array[0] = MyLarik([40, 55, 63, 17, 22, 68, 89, 97, 89])

        # Objek B : input lewat Setter
        self.array[1] = MyLarik()
        self.array[1].setUkuran(5)
        self.array[1].setNilai(0, 12)
        self.array[1].setNilai(1, 7)
        self.array[1].setNilai(2, 30)
        self.array[1].setNilai(3, 4)
        self.array[1].setNilai(4, 19)

        # Objek C : input melalui fungsi dalam class
        print("=== Input Larik C (input dalam) ===")
        nC = self.inputInt("Masukkan ukuran Larik C : ")
        self.array[2] = MyLarik(nC)
        self.array[2].inputDalam()

        # Objek D : input melalui fungsi luar class
        print("=== Input Larik D (input luar) ===")
        nD = self.inputInt("Masukkan ukuran Larik D : ")
        self.array[3] = MyLarik(nD)
        for i in range(nD):
            isi = self.inputInt(f"Masukkan nilai ke-{i + 1} = ")
            self.array[3].setNilai(i, isi)

    def tampilMenuUtama(self):
        print("\n===== MENU UTAMA =====")
        print("1. Larik A (input constructor)")
        print("2. Larik B (input setter)")
        print("3. Larik C (input dalam)")
        print("4. Larik D (input luar)")
        print("5. Tampilkan semua larik")
        print("0. Keluar")

    def tampilMenuProses(self, nama):
        print(f"\n--- Larik {nama} ---")
        print("1. Tampilkan larik")
        print("2. Hitung jumlah, rata-rata, max, min")
        print("3. Urutkan naik (ascending)")
        print("4. Urutkan turun (descending)")
        print("0. Kembali")

    def menuProses(self, indeks):
        nama = chr(ord('A') + indeks)
        obj = self.array[indeks]
        while True:
            self.tampilMenuProses(nama)
            pilihan = self.inputInt("Pilih menu : ")
            print()
            if pilihan == 1:
                obj.cetakLarik()
            elif pilihan == 2:
                obj.cetakProses()
            elif pilihan == 3:
                obj.bubbleAsc()
                print(f"Larik {nama} diurutkan naik.")
                obj.cetakLarik()
            elif pilihan == 4:
                obj.bubbleDesc()
                print(f"Larik {nama} diurutkan turun.")
                obj.cetakLarik()
            elif pilihan == 0:
                break
            else:
                print("Menu tidak valid.")

    def jalankan(self):
        self.deklarasiObjek()
        while True:
            self.tampilMenuUtama()
            pilihan = self.inputInt("Pilih menu : ")
            if 1 <= pilihan <= 4:
                self.menuProses(pilihan - 1)
            elif pilihan == 5:
                for i in range(len(self.array)):
                    print(f"\nLarik {chr(ord('A') + i)}")
                    self.array[i].cetakLarik()
            elif pilihan == 0:
                print("Program selesai.")
                break
            else:
                print("Input Menu tidak valid.")


if __name__ == "__main__":
    menu = MenuLarik()
    menu.jalankan()