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
    # Default & Constructor Parameter (Python menggunakan default argumen)
    def __init__(self, absis=0.0, ordinat=0.0):
        self.absis = absis
        self.ordinat = ordinat

    # Setter Absis
    def setAbsis(self, absis):
        self.absis = absis

    # Setter Ordinat
    def setOrdinat(self, ordinat):
        self.ordinat = ordinat
          
    # Getter Absis
    def getAbsis(self):
        return self.absis

    # Getter Ordinat
    def getOrdinat(self):
        return self.ordinat

    # Input Dalam Class
    def inputDalam(self):
        self.absis = float(input("Masukkan Nilai Absis (input dalam): "))
        self.ordinat = float(input("Masukkan Nilai Ordinat (input dalam): "))

    # Method Mencari titik Tengah (void) 
    def titikTengahVoid(self, a, b):
        self.absis = (a.absis + b.absis) / 2
        self.ordinat = (a.ordinat + b.ordinat) / 2

    # Method Mencari titik Tengah (Fungsi)
    def titikTengahFungsi(self, p):
        tengah = Koordinat()
        tengah.absis = (p.absis + self.absis) / 2
        tengah.ordinat = (p.ordinat + self.ordinat) / 2
        return tengah

    # Method Pencerminan terhadap sumbu X (void)
    def pencerminanXVoid(self, pHasil):
        pHasil.absis = self.absis
        pHasil.ordinat = -1 * self.ordinat

    # Method Pencerminan terhadap sumbu x (fungsi)
    def pencerminanXFungsi(self):
        pCermin = Koordinat()
        pCermin.absis = self.absis
        pCermin.ordinat = -1 * self.ordinat
        return pCermin

    # Method Pencerminan terhadap sumbu y (void)
    def pencerminanYVoid(self, pHasil):
        pHasil.absis = -1 * self.absis
        pHasil.ordinat = self.ordinat

    # Method Pencerminan terhadap sumbu y (Fungsi)
    def pencerminanYFungsi(self):
        pCermin = Koordinat()
        pCermin.absis = -1 * self.absis
        pCermin.ordinat = self.ordinat
        return pCermin

    # Method Mencari Jarak antar 2 titik (void) - list 1 index digunakan sebagai referensi
    def jarakDuaTitikVoid(self, b, jarak):
        jarak[0] = math.sqrt((b.absis - self.absis)**2 + (b.ordinat - self.ordinat)**2)

    # Method Mencari Jarak antar 2 titik (Fungsi)
    def jarakDuaTitikFungsi(self, p):
        jarak = math.sqrt((p.absis - self.absis)**2 + (p.ordinat - self.ordinat)**2)
        return jarak

    # Output Dalam
    def outputDalam(self, k2, k3, k4):
        print("\n=========================================")
        print("          OUTPUT DALAM (SEMUA OBJEK)     ")
        print("=========================================")
        print(f"Objek Koordinat ke-1 : ({self.absis}, {self.ordinat})")
        print(f"Objek Koordinat ke-2 : ({k2.getAbsis()}, {k2.getOrdinat()})")
        print(f"Objek Koordinat ke-3 : ({k3.getAbsis()}, {k3.getOrdinat()})")
        print(f"Objek Koordinat ke-4 : ({k4.getAbsis()}, {k4.getOrdinat()})")
        print("=========================================")


# Input Luar Class 
def inputLuar(k):
    a = float(input("Masukkan Nilai Absis (Input Luar): "))
    o = float(input("Masukkan Nilai Ordinat (Input Luar): "))
    k.setAbsis(a)
    k.setOrdinat(o)

# Output Luar 
def outputLuar(k1, k2, k3, k4):
    print("\n=========================================")
    print("               OUTPUT LUAR                ")
    print("=========================================")
    
    daftarObjek = [k1, k2, k3, k4]
    
    for i in range(4):
        print(f"Objek Koordinat ke-{i + 1} : ({daftarObjek[i].getAbsis()}, {daftarObjek[i].getOrdinat()})")

# validasi pilihan (angka harus di antara min dan max)
def bacaPilihan(pesan, min_val, max_val):
    while True:
        try:
            nilai = int(input(pesan))
            if min_val <= nilai <= max_val:
                return nilai
            print(f"Pilihan harus antara {min_val} sampai {max_val}!")
        except ValueError:
            # reset error state (otomatis via exception catch)
            # buang input invalid (otomatis loop back)
            print("Masukkan angka yang valid!")


def main():
    koor1 = Koordinat() # Input Melalui Setter
    koor1.setAbsis(3)
    koor1.setOrdinat(4)
    
    koor2 = Koordinat(5, 6) # Input melalui Constructor Parameter
    koor3 = Koordinat() # Input Melalui method inputDalam
    koor4 = Koordinat() # Input Melalui method inputLuar

    menuUtama = -1
    while menuUtama != 0:
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
            menuUtama = int(input(">> Masukkan Pilihan Menu : "))
        except ValueError:
            print("Pilihan tidak valid! Silakan coba lagi.")
            continue

        if menuUtama == 1:
            print("=========================================")
            print("PILIH OBJEK YANG MAU DI-INPUT")
            print("1. Koordinat 3 (via Input Dalam)")
            print("2. Koordinat 4 (via Input Luar)")
            print("=========================================")
            pilihObjek = int(input(">> Pilih Objek (1-2): "))

            if pilihObjek == 1:
                print("Input untuk Koordinat 3:")
                koor3.inputDalam()
            elif pilihObjek == 2:
                print("Input untuk Koordinat 4:")
                inputLuar(koor4)

        elif menuUtama == 2:
            print("=========================================")
            print("PENCERMINAN")
            print("1. Terhadap Sumbu X")
            print("2. Terhadap Sumbu Y")
            print("=========================================")
            
            # sumbu = int(input(">> Pilih Sumbu (1/2): "))
            
            sumbu = bacaPilihan(">> Pilih Sumbu (1/2): ", 1, 2)
            
            # pilihObjek = int(input(">> Pilih Objek yang dicerminkan (1-4): "))

            pilihObjek = bacaPilihan(">> Pilih Objek yang dicerminkan (1-4): ", 1, 4)

            arr = [koor1, koor2, koor3, koor4]
            target = arr[pilihObjek - 1]

            if sumbu == 1:
                hasilFungsi = target.pencerminanXFungsi()
                hasilVoid = Koordinat()
                target.pencerminanXVoid(hasilVoid)
                
                print("Hasil Pencerminan terhadap Sumbu X:")
                print(f"   > Versi Fungsi : ({hasilFungsi.getAbsis()}, {hasilFungsi.getOrdinat()})")
                print(f"   > Versi Void   : ({hasilVoid.getAbsis()}, {hasilVoid.getOrdinat()})")
            else:
                hasilFungsi = target.pencerminanYFungsi()
                hasilVoid = Koordinat()
                target.pencerminanYVoid(hasilVoid)

                print("Hasil Pencerminan terhadap Sumbu Y:")
                print(f"   > Versi Fungsi : ({hasilFungsi.getAbsis()}, {hasilFungsi.getOrdinat()})")
                print(f"   > Versi Void   : ({hasilVoid.getAbsis()}, {hasilVoid.getOrdinat()})")

        elif menuUtama == 3:
            print("=========================================")
            print("       TITIK TENGAH ANTARA 2 OBJEK       ")
            print("=========================================")
            # o1 = int(input("Pilih Objek Pertama (1-4): "))
            # o2 = int(input("Pilih Objek Kedua (1-4): "))

            o1 = bacaPilihan("Pilih Objek Pertama (1-4): ", 1, 4)
            o2 = bacaPilihan("Pilih Objek Kedua (1-4): ", 1, 4)

            arr = [koor1, koor2, koor3, koor4]
            
            hasilFungsi = arr[o1 - 1].titikTengahFungsi(arr[o2 - 1])
            hasilVoid = Koordinat()
            hasilVoid.titikTengahVoid(arr[o1 - 1], arr[o2 - 1])

            print(f"Titik Tengah antara Objek {o1} dan {o2} :")
            print(f"   > Versi Fungsi : ({hasilFungsi.getAbsis()}, {hasilFungsi.getOrdinat()})")
            print(f"   > Versi Void   : ({hasilVoid.getAbsis()}, {hasilVoid.getOrdinat()})")

        elif menuUtama == 4:
            print("=========================================")
            print("          JARAK ANTARA 2 TITIK           ")
            print("=========================================")
            # o1 = int(input("Pilih Objek Pertama (1-4): "))
            # o2 = int(input("Pilih Objek Kedua (1-4): "))

            o1 = bacaPilihan("Pilih Objek Pertama (1-4): ", 1, 4)
            o2 = bacaPilihan("Pilih Objek Kedua (1-4): ", 1, 4)

            arr = [koor1, koor2, koor3, koor4]

            jarakFungsi = arr[o1 - 1].jarakDuaTitikFungsi(arr[o2 - 1])
            jarakVoid = [0.0]
            arr[o1 - 1].jarakDuaTitikVoid(arr[o2 - 1], jarakVoid)

            print(f"Jarak antara Objek {o1} dan {o2} :")
            print(f"   - Versi Fungsi : {jarakFungsi}")
            print(f"   - Versi Void   : {jarakVoid[0]}")

        elif menuUtama == 5:
            print("=========================================")
            print("Pilih Menu Output")
            print("1. Output Dalam (Menampilkan semua objek via Method Class)")
            print("2. Output Luar (Menampilkan semua objek via Fungsi Luar)")
            print("=========================================")
            pilihOut = int(input(">> Pilih Menu Output : "))
            
            if pilihOut == 1:
                koor1.outputDalam(koor2, koor3, koor4)
            elif pilihOut == 2:
                outputLuar(koor1, koor2, koor3, koor4)

        elif menuUtama == 0:
            print("\nBye-bye!.")
        
        else:
            print("Pilihan tidak valid! Silakan coba lagi.")

if __name__ == "__main__":
    main()