'''
Nama Program : 2dArray.py
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 09-10-2026
Deskripsi Program : Sebuah program untuk menghitung penjumlahan dan perkalian matriks 2 dimensi
                    dengan proses secara void dan fungsi.
'''

class Matriks2d:
    def __init__(self, baris=10, kolom=10):
        self.baris = baris
        self.kolom = kolom
        # // Inisialisasi list 2D (matrix) berukuran baris x kolom dengan nilai 0
        self.nilai = [[0 for _ in range(10)] for _ in range(10)]

    def setBaris(self, baris):
        self.baris = baris

    def setKolom(self, kolom):
        self.kolom = kolom

    # // Penyesuaian initializer_list Java menggunakan list of lists Python
    def setNilai(self, nilaiBaru):
        for i, baris_nilai in enumerate(nilaiBaru):
            if i >= self.baris or i >= 10:
                break
            for j, nilai_elemen in enumerate(baris_nilai):
                if j >= self.kolom or j >= 10:
                    break
                self.nilai[i][j] = nilai_elemen

    def getBaris(self):
        return self.baris

    def getKolom(self):
        return self.kolom

    def getNilai(self, i, j):
        return self.nilai[i][j]

    def inputMatriks(self):
        while True:
            try:
                self.baris = int(input("Masukkan jumlah baris matriks (max 10) : "))
                if 1 <= self.baris <= 10:
                    break
                print("Jumlah baris harus antara 1 dan 10.")
            except ValueError:
                print("Masukkan angka yang valid!")
        
        while True:
            try:
                self.kolom = int(input("Masukkan jumlah kolom matriks (max 10) : "))
                if 1 <= self.kolom <= 10:
                    break
                print("Jumlah kolom harus antara 1 dan 10.")
            except ValueError:
                print("Masukkan angka yang valid!")

        print("Masukkan elemen matriks:")
        for i in range(self.baris):
            for j in range(self.kolom):
                while True:
                    try:
                        self.nilai[i][j] = int(input(f"Nilai [{i}][{j}] : "))
                        break
                    except ValueError:
                        print("Masukkan angka yang valid!")

    def penjumlahanMatriksVoid(self, a, b):
        if a.baris != b.baris or a.kolom != b.kolom:
            print("Tidak dapat melakukan penjumlahan karena ordo matriks berbeda.")
            return
        self.baris = a.baris
        self.kolom = a.kolom
        for i in range(self.baris):
            for j in range(self.kolom):
                self.nilai[i][j] = a.nilai[i][j] + b.nilai[i][j]

    def penjumlahanMatriksFungsi(self, a, b):
        if a.baris != b.baris or a.kolom != b.kolom:
            print("Tidak dapat melakukan penjumlahan karena ordo matriks berbeda.")
            return Matriks2d()

        hasil = Matriks2d(a.baris, a.kolom)
        for i in range(a.baris):
            for j in range(a.kolom):
                hasil.nilai[i][j] = a.nilai[i][j] + b.nilai[i][j]
        return hasil

    def perkalianMatriksVoid(self, a, b):
        if a.kolom != b.baris:
            print("Tidak dapat melakukan perkalian karena kolom matriks pertama tidak sama dengan baris matriks kedua.")
            return
        self.baris = a.baris
        self.kolom = b.kolom
        for i in range(a.baris):
            for j in range(b.kolom):
                self.nilai[i][j] = 0
                for k in range(a.kolom):
                    self.nilai[i][j] += a.nilai[i][k] * b.nilai[k][j]

    def perkalianMatriksFungsi(self, a, b):
        if a.kolom != b.baris:
            print("Tidak dapat melakukan perkalian karena kolom matriks pertama tidak sama dengan baris matriks kedua.")
            return Matriks2d()

        hasil = Matriks2d(a.baris, b.kolom)
        for i in range(a.baris):
            for j in range(b.kolom):
                hasil.nilai[i][j] = 0
                for k in range(a.kolom):
                    hasil.nilai[i][j] += a.nilai[i][k] * b.nilai[k][j]
        return hasil

    def outputMatriks(self):
        print(f"Matriks ({self.baris}x{self.kolom}):")
        for i in range(self.baris):
            row_str = " ".join(str(self.nilai[i][j]) for j in range(self.kolom))
            print(row_str)


class Menu:
    def __init__(self):
        self.pilihan = 0

    def tampilanMenu(self):
        matriks1 = Matriks2d()
        matriks1.setBaris(3)
        matriks1.setKolom(3)
        matriks1.setNilai([[1, 2, 3], [4, 5, 6], [7, 8, 9]])

        matriks2 = Matriks2d(3, 3)
        matriks2.setNilai([[1, 0, 0], [0, 1, 0], [0, 0, 1]])

        matriks3 = Matriks2d()

        while True:
            print("=========================================")
            print("           PROGRAM 2D ARRAY              ")
            print("=========================================")
            print("1. Input Matriks 3")
            print("2. Lihat Semua Matriks")
            print("3. Penjumlahan Matriks")
            print("4. Perkalian Matriks")
            print("0. Keluar")
            print("=========================================")
            try:
                self.pilihan = int(input(">> Masukkan Pilihan Menu : "))
            except ValueError:
                print("Pilihan menu tidak valid.")
                continue

            if self.pilihan == 1:
                print("Input Matriks 3 (via Input Dalam):")
                matriks3.inputMatriks()
            elif self.pilihan == 2:
                print("Matriks 1 (via Setter):")
                matriks1.outputMatriks()
                print("Matriks 2 (via Constructor Parameter):")
                matriks2.outputMatriks()
                print("Matriks 3 (via Input Dalam):")
                matriks3.outputMatriks()
            elif self.pilihan == 3:
                print("=========================================")
                print("        PENJUMLAHAN 2 MATRIKS            ")
                print("=========================================")
                print("Pilihan Matriks (1: Matriks 1, 2: Matriks 2, 3: Matriks 3)")
                o1 = int(input("Pilih Matriks pertama : "))
                o2 = int(input("Pilih Matriks kedua : "))

                m1 = matriks1 if o1 == 1 else (matriks2 if o1 == 2 else matriks3)
                m2 = matriks1 if o2 == 1 else (matriks2 if o2 == 2 else matriks3)
                if m1.getBaris() != m2.getBaris() or m1.getKolom() != m2.getKolom():
                    print("Tidak dapat melakukan penjumlahan karena ordo matriks berbeda.")
                    continue

                hasilPenjumlahanVoid = Matriks2d()
                hasilPenjumlahanFungsi = Matriks2d()

                hasilPenjumlahanVoid.penjumlahanMatriksVoid(m1, m2)
                hasilPenjumlahanFungsi = hasilPenjumlahanFungsi.penjumlahanMatriksFungsi(m1, m2)

                print("Hasil Penjumlahan Matriks (via Void):")
                hasilPenjumlahanVoid.outputMatriks()
                print("Hasil Penjumlahan Matriks (via Fungsi):")
                hasilPenjumlahanFungsi.outputMatriks()

            elif self.pilihan == 4:
                print("=========================================")
                print("         PERKALIAN 2 MATRIKS             ")
                print("=========================================")
                print("Pilihan Matriks (1: Matriks 1, 2: Matriks 2, 3: Matriks 3)")
                o1 = int(input("Pilih Matriks pertama : "))
                o2 = int(input("Pilih Matriks kedua : "))

                m1 = matriks1 if o1 == 1 else (matriks2 if o1 == 2 else matriks3)
                m2 = matriks1 if o2 == 1 else (matriks2 if o2 == 2 else matriks3)

                # Validasi ordo perkalian di menu
                if m1.getKolom() != m2.getBaris():
                    print("Tidak dapat melakukan perkalian karena kolom matriks pertama tidak sama dengan baris matriks kedua.")
                    continue

                hasilPerkalianVoid = Matriks2d()
                hasilPerkalianFungsi = Matriks2d()

                hasilPerkalianVoid.perkalianMatriksVoid(m1, m2)
                hasilPerkalianFungsi = hasilPerkalianFungsi.perkalianMatriksFungsi(m1, m2)

                print("Hasil Perkalian Matriks (via Void):")
                hasilPerkalianVoid.outputMatriks()
                print("Hasil Perkalian Matriks (via Fungsi):")
                hasilPerkalianFungsi.outputMatriks()
            elif self.pilihan == 0:
                print("Keluar dari program.")
                break
            else:
                print("Pilihan menu tidak valid.")


if __name__ == "__main__":
    m = Menu()
    m.tampilanMenu()