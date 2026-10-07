'''
Nama Program : Koordinat.py
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
'''

import math

class Koordinat:
    def __init__(self, absis=0.0, ordinat=0.0):
        self.absis = float(absis)
        self.ordinat = float(ordinat)

    def set_absis(self, absis):
        self.absis = float(absis)

    def set_ordinat(self, ordinat):
        self.ordinat = float(ordinat)

    def get_absis(self):
        return self.absis

    def get_ordinat(self):
        return self.ordinat

    def input_dalam(self):
        self.absis = float(input("Masukkan Nilai Absis (input dalam): "))
        self.ordinat = float(input("Masukkan Nilai Ordinat (input dalam): "))

    def titik_tengah_void(self, a, b):
        self.absis = (a.absis + b.absis) / 2
        self.ordinat = (a.ordinat + b.ordinat) / 2

    def titik_tengah_fungsi(self, p):
        return Koordinat((p.absis + self.absis) / 2, (p.ordinat + self.ordinat) / 2)

    def pencerminan_x_void(self, p_hasil):
        p_hasil.absis = self.absis
        p_hasil.ordinat = -1 * self.ordinat

    def pencerminan_x_fungsi(self):
        return Koordinat(self.absis, -1 * self.ordinat)

    def pencerminan_y_void(self, p_hasil):
        p_hasil.absis = -1 * self.absis
        p_hasil.ordinat = self.ordinat

    def pencerminan_y_fungsi(self):
        return Koordinat(-1 * self.absis, self.ordinat)

    def jarak_dua_titik_void(self, b, jarak_list):
        jarak_list[0] = math.sqrt((b.absis - self.absis)**2 + (b.ordinat - self.ordinat)**2)

    def jarak_dua_titik_fungsi(self, p):
        return math.sqrt((p.absis - self.absis)**2 + (p.ordinat - self.ordinat)**2)

    def output_dalam(self, k2, k3, k4):
        print("\n=========================================")
        print("          OUTPUT DALAM (SEMUA OBJEK)     ")
        print("=========================================")
        print(f"Objek Koordinat ke-1 : ({format_num(self.absis)}, {format_num(self.ordinat)})")
        print(f"Objek Koordinat ke-2 : ({format_num(k2.get_absis())}, {format_num(k2.get_ordinat())})")
        print(f"Objek Koordinat ke-3 : ({format_num(k3.get_absis())}, {format_num(k3.get_ordinat())})")
        print(f"Objek Koordinat ke-4 : ({format_num(k4.get_absis())}, {format_num(k4.get_ordinat())})")
        print("=========================================")


def format_num(nilai):
    if nilai == int(nilai):
        return str(int(nilai))
    return f"{nilai:.6g}"


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
    for i, obj in enumerate(daftar_objek, start=1):
        print(f"Objek Koordinat ke-{i} : ({format_num(obj.get_absis())}, {format_num(obj.get_ordinat())})")


def baca_pilihan(pesan, min_val, max_val):
    while True:
        try:
            nilai = int(input(pesan))
            if min_val <= nilai <= max_val:
                return nilai
            print(f"Pilihan harus antara {min_val} sampai {max_val}!")
        except ValueError:
            print("Masukkan angka yang valid!")


class Menu:
    def tampil_menu(self):
        koor1 = Koordinat()
        koor1.set_absis(3)
        koor1.set_ordinat(4)

        koor2 = Koordinat(5, 6)
        koor3 = Koordinat()
        koor4 = Koordinat()

        while True:
            print("\n=========================================")
            print("     MENU APLIKASI KOORDINAT KARTESIUS     ")
            print("=========================================")
            print("1. Input Koordinat 3 dan 4")
            print("2. Pencerminan")
            print("3. Titik Tengah")
            print("4. Jarak 2 Titik")
            print("5. Tampilkan Seluruh Objek")
            print("0. Keluar")
            print("=========================================")
            
            try:
                menu_utama = int(input(">> Masukkan Pilihan Menu : "))
            except ValueError:
                print("Pilihan tidak valid! Silakan coba lagi.")
                continue

            if menu_utama == 1:
                print("=========================================")
                print("PILIH OBJEK YANG MAU DI-INPUT")
                print("1. Koordinat 3 (via Input Dalam)")
                print("2. Koordinat 4 (via Input Luar)")
                print("=========================================")
                pilih = int(input(">> Pilih Objek (1-2): "))
                if pilih == 1:
                    print("Input untuk Koordinat 3:")
                    koor3.input_dalam()
                elif pilih == 2:
                    print("Input untuk Koordinat 4:")
                    input_luar(koor4)

            elif menu_utama == 2:
                print("=========================================")
                print("PENCERMINAN")
                print("1. Terhadap Sumbu X")
                print("2. Terhadap Sumbu Y")
                print("=========================================")
                sumbu = baca_pilihan(">> Pilih Sumbu (1/2): ", 1, 2)
                pilih_objek = baca_pilihan(">> Pilih Objek yang dicerminkan (1-4): ", 1, 4)

                arr = [koor1, koor2, koor3, koor4]
                target = arr[pilih_objek - 1]

                if sumbu == 1:
                    hasil_fungsi = target.pencerminan_x_fungsi()
                    hasil_void = Koordinat()
                    target.pencerminan_x_void(hasil_void)
                    print("Hasil Pencerminan terhadap Sumbu X:")
                    print(f"   > Versi Fungsi : ({format_num(hasil_fungsi.get_absis())}, {format_num(hasil_fungsi.get_ordinat())})")
                    print(f"   > Versi Void   : ({format_num(hasil_void.get_absis())}, {format_num(hasil_void.get_ordinat())})")
                else:
                    hasil_fungsi = target.pencerminan_y_fungsi()
                    hasil_void = Koordinat()
                    target.pencerminan_y_void(hasil_void)
                    print("Hasil Pencerminan terhadap Sumbu Y:")
                    print(f"   > Versi Fungsi : ({format_num(hasil_fungsi.get_absis())}, {format_num(hasil_fungsi.get_ordinat())})")
                    print(f"   > Versi Void   : ({format_num(hasil_void.get_absis())}, {format_num(hasil_void.get_ordinat())})")

            elif menu_utama == 3:
                print("=========================================")
                print("       TITIK TENGAH ANTARA 2 OBJEK       ")
                print("=========================================")
                o1 = baca_pilihan("Pilih Objek Pertama (1-4): ", 1, 4)
                o2 = baca_pilihan("Pilih Objek Kedua (1-4): ", 1, 4)

                arr = [koor1, koor2, koor3, koor4]
                hasil_fungsi = arr[o1 - 1].titik_tengah_fungsi(arr[o2 - 1])
                hasil_void = Koordinat()
                hasil_void.titik_tengah_void(arr[o1 - 1], arr[o2 - 1])

                print(f"Titik Tengah antara Objek {o1} dan {o2} :")
                print(f"   > Versi Fungsi : ({format_num(hasil_fungsi.get_absis())}, {format_num(hasil_fungsi.get_ordinat())})")
                print(f"   > Versi Void   : ({format_num(hasil_void.get_absis())}, {format_num(hasil_void.get_ordinat())})")

            elif menu_utama == 4:
                print("=========================================")
                print("          JARAK ANTARA 2 TITIK           ")
                print("=========================================")
                o1 = baca_pilihan("Pilih Objek Pertama (1-4): ", 1, 4)
                o2 = baca_pilihan("Pilih Objek Kedua (1-4): ", 1, 4)

                arr = [koor1, koor2, koor3, koor4]
                jarak_fungsi = arr[o1 - 1].jarak_dua_titik_fungsi(arr[o2 - 1])
                jarak_void = [0.0]
                arr[o1 - 1].jarak_dua_titik_void(arr[o2 - 1], jarak_void)

                print(f"Jarak antara Objek {o1} dan {o2} :")
                print(f"   - Versi Fungsi : {format_num(jarak_fungsi)}")
                print(f"   - Versi Void   : {format_num(jarak_void[0])}")

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
                print("Pilihan tidak valid! Silakan coba lagi.")


if __name__ == "__main__":
    menu = Menu()
    menu.tampil_menu()