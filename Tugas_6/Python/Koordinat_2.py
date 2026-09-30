"""
Nama Program : Koordinat_2.java
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 29-09-2026
Deskripsi Program : Sebuah program untuk melakukan operasi perhitungan koordinat kartesius berupa 
                    1. mencari titik tengah
                    2. mencari jarak antara 2 titik
                    3. hasil pencerminan terhadap sumbu x atau sumbu y
                    dengan input dalam, input luar, output dalam dan output luar berbasis OOP.
"""

import math

class Koordinat:
    def __init__(self, absis=0.0, ordinat=0.0):
        self.absis = float(absis)
        self.ordinat = float(ordinat)

    def set_absis(self, absis):
        self.absis = float(absis)

    def set_ordinat(self, ordinat):
        self.ordinat = float(ordinat)

    def get_absis(self): return self.absis
    def get_ordinat(self): return self.ordinat

    def input_dalam(self):
        self.absis = float(input("Masukkan Nilai Absis (input dalam): "))
        self.ordinat = float(input("Masukkan Nilai Ordinat (input dalam): "))

    def titik_tengah_void(self, a, b):
        self.absis = (a.absis + b.absis) / 2
        self.ordinat = (a.ordinat + b.ordinat) / 2

    def titik_tengah_fungsi(self, p):
        tengah = Koordinat()
        tengah.absis = (p.absis + self.absis) / 2
        tengah.ordinat = (p.ordinat + self.ordinat) / 2
        return tengah

    def pencerminan_x_void(self, p_hasil):
        p_hasil.absis = self.absis
        p_hasil.ordinat = -1 * self.ordinat

    def pencerminan_x_fungsi(self):
        p_cermin = Koordinat()
        p_cermin.absis = self.absis
        p_cermin.ordinat = -1 * self.ordinat
        return p_cermin

    def pencerminan_y_void(self, p_hasil):
        p_hasil.absis = -1 * self.absis
        p_hasil.ordinat = self.ordinat

    def pencerminan_y_fungsi(self):
        p_cermin = Koordinat()
        p_cermin.absis = -1 * self.absis
        p_cermin.ordinat = self.ordinat
        return p_cermin

    def jarak_dua_titik_void(self, b, jarak):
        # jarak dikirim sebagai list (array 1 elemen) agar berlaku pass-by-reference
        jarak[0] = math.sqrt(math.pow(b.absis - self.absis, 2) + math.pow(b.ordinat - self.ordinat, 2))

    def jarak_dua_titik_fungsi(self, p):
        return math.sqrt(math.pow(p.absis - self.absis, 2) + math.pow(p.ordinat - self.ordinat, 2))

    def output_dalam(self, k2, k3, k4):
        print("\n=========================================")
        print("          OUTPUT DALAM (SEMUA OBJEK)     ")
        print("=========================================")
        print(f"Objek Koordinat ke-1 : ({self.absis}, {self.ordinat})")
        print(f"Objek Koordinat ke-2 : ({k2.get_absis()}, {k2.get_ordinat()})")
        print(f"Objek Koordinat ke-3 : ({k3.get_absis()}, {k3.get_ordinat()})")
        print(f"Objek Koordinat ke-4 : ({k4.get_absis()}, {k4.get_ordinat()})")
        print("=========================================")


def input_luar(k):
    a = float(input("Masukkan Nilai Absis (Input Luar): "))
    o = float(input("Masukkan Nilai Ordinat (Input Luar): "))
    k.set_absis(a)
    k.set_ordinat(o)


def output_luar(k1, k2, k3, k4):
    print("\n=========================================")
    print("               OUTPUT LUAR                ")
    print("=========================================")
    daftar_objek = [k1, k2, k3, k4]
    for i, obj in enumerate(daftar_objek):
        print(f"Objek Koordinat ke-{i + 1} : ({obj.get_absis()}, {obj.get_ordinat()})")


def main():
    koor1 = Koordinat()
    koor2 = Koordinat(0, 0)
    koor3 = Koordinat()
    koor4 = Koordinat()

    while True:
        print("\n=========================================")
        print("     MENU APLIKASI KOORDINAT KARTESIUS     ")
        print("=========================================")
        print("1. Input Koordinat")
        print("2. Pencerminan")
        print("3. Titik Tengah")
        print("4. Jarak 2 Titik")
        print("5. Tampilkan Seluruh Objek")
        print("0. Keluar")
        print("=========================================")
        menu_utama = int(input(">> Masukkan Pilihan Menu : "))

        if menu_utama == 1:
            print("=========================================")
            print("PILIH OBJEK YANG MAU DI-INPUT")
            print("1. Koordinat 1 (via Setter)")
            print("2. Koordinat 2 (via Constructor Parameter)")
            print("3. Koordinat 3 (via Input Dalam)")
            print("4. Koordinat 4 (via Input Luar)")
            print("=========================================")
            pilih_objek = int(input(">> Pilih Objek (1-4): "))

            if pilih_objek == 1:
                x = float(input("Masukkan Absis: "))
                y = float(input("Masukkan Ordinat: "))
                koor1.set_absis(x)
                koor1.set_ordinat(y)
            elif pilih_objek == 2:
                x = float(input("Masukkan Absis: "))
                y = float(input("Masukkan Ordinat: "))
                koor2 = Koordinat(x, y)
            elif pilih_objek == 3:
                print("Input untuk Koordinat 3:")
                koor3.input_dalam()
            elif pilih_objek == 4:
                print("Input untuk Koordinat 4:")
                input_luar(koor4)

        elif menu_utama == 2:
            print("=========================================")
            print("PENCERMINAN")
            print("1. Terhadap Sumbu X")
            print("2. Terhadap Sumbu Y")
            print("=========================================")
            sumbu = int(input(">> Pilih Sumbu (1/2): "))
            pilih_objek = int(input(">> Pilih Objek yang dicerminkan (1-4): "))

            arr = [koor1, koor2, koor3, koor4]
            target = arr[pilih_objek - 1]

            if sumbu == 1:
                hasil_fungsi = target.pencerminan_x_fungsi()
                hasil_void = Koordinat()
                target.pencerminan_x_void(hasil_void)
                
                print("Hasil Pencerminan terhadap Sumbu X:")
                print(f"   > Versi Fungsi : ({hasil_fungsi.get_absis()}, {hasil_fungsi.get_ordinat()})")
                print(f"   > Versi Void   : ({hasil_void.get_absis()}, {hasil_void.get_ordinat()})")
            else:
                hasil_fungsi = target.pencerminan_y_fungsi()
                hasil_void = Koordinat()
                target.pencerminan_y_void(hasil_void)

                print("Hasil Pencerminan terhadap Sumbu Y:")
                print(f"   > Versi Fungsi : ({hasil_fungsi.get_absis()}, {hasil_fungsi.get_ordinat()})")
                print(f"   > Versi Void   : ({hasil_void.get_absis()}, {hasil_void.get_ordinat()})")

        elif menu_utama == 3:
            print("=========================================")
            print("       TITIK TENGAH ANTARA 2 OBJEK       ")
            print("=========================================")
            o1 = int(input("Pilih Objek Pertama (1-4): "))
            o2 = int(input("Pilih Objek Kedua (1-4): "))

            arr = [koor1, koor2, koor3, koor4]
            hasil_fungsi = arr[o1 - 1].titik_tengah_fungsi(arr[o2 - 1])
            hasil_void = Koordinat()
            hasil_void.titik_tengah_void(arr[o1 - 1], arr[o2 - 1])

            print(f"Titik Tengah antara Objek {o1} dan {o2} :")
            print(f"   > Versi Fungsi : ({hasil_fungsi.get_absis()}, {hasil_fungsi.get_ordinat()})")
            print(f"   > Versi Void   : ({hasil_void.get_absis()}, {hasil_void.get_ordinat()})")

        elif menu_utama == 4:
            print("=========================================")
            print("          JARAK ANTARA 2 TITIK           ")
            print("=========================================")
            o1 = int(input("Pilih Objek Pertama (1-4): "))
            o2 = int(input("Pilih Objek Kedua (1-4): "))

            arr = [koor1, koor2, koor3, koor4]
            jarak_fungsi = arr[o1 - 1].jarak_dua_titik_fungsi(arr[o2 - 1])
            jarak_void = [0.0]  # Gunakan list agar bisa pass by reference
            arr[o1 - 1].jarak_dua_titik_void(arr[o2 - 1], jarak_void)

            print(f"Jarak antara Objek {o1} dan {o2} :")
            print(f"   - Versi Fungsi : {jarak_fungsi}")
            print(f"   - Versi Void   : {jarak_void[0]}")

        elif menu_utama == 5:
            print("=========================================")
            print("Pilih Menu Output")
            print("1. Output Dalam (Menampilkan semua objek via Method Class)")
            print("2. Output Luar (Menampilkan semua objek via Fungsi Luar)")
            print("=========================================")
            pilih_out = int(input(">> Pilih Menu Output : "))
            
            if pilih_out == 1:
                koor1.output_dalam(koor2, koor3, koor4)
            elif pilih_out == 2:
                output_luar(koor1, koor2, koor3, koor4)

        elif menu_utama == 0:
            print("\nBye-bye!.")
            break
        else:
            print("Pilihan tidak valid! Silakan coba lagi.\n")

if __name__ == "__main__":
    main()