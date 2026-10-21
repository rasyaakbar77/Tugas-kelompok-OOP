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
    def __init__(self, size_arr=None, array=None):
        self.__ukuran = 0
        self.__arr = [0] * 100
        
        if size_arr is not None and array is not None:
            self.__ukuran = 100 if size_arr > 100 else size_arr
            for i in range(self.__ukuran):
                self.__arr[i] = array[i]

    def set_ukuran(self, size_arr):
        if size_arr > 100 or size_arr < 0:
            print("Ukuran Array Tidak Valid!.")
        else:
            self.__ukuran = size_arr

    def set_nilai(self, indeks, nilai):
        if 0 <= indeks < 100:
            self.__arr[indeks] = nilai
            return True
        return False

    def get_ukuran(self):
        return self.__ukuran

    def get_nilai(self, indeks):
        if 0 <= indeks < 100:
            return self.__arr[indeks]
        else:
            print(f"Indeks {indeks} tidak valid/kosong!")
            return -1

    def average_value(self):
        if self.__ukuran == 0:
            return 0
        total_sum = 0
        for i in range(self.__ukuran):
            total_sum += self.__arr[i]
        return total_sum / self.__ukuran

    def max_value(self):
        if self.__ukuran == 0:
            return 0
        maks = self.__arr[0]
        for i in range(self.__ukuran):
            if maks < self.__arr[i]:
                maks = self.__arr[i]
        return maks

    def min_value(self):
        if self.__ukuran == 0:
            return 0
        min_val = self.__arr[0]
        for i in range(self.__ukuran):
            if min_val > self.__arr[i]:
                min_val = self.__arr[i]
        return min_val

    def asc_sort(self):
        for i in range(self.__ukuran - 1):
            for j in range(self.__ukuran - i - 1):
                if self.__arr[j] > self.__arr[j + 1]:
                    temp = self.__arr[j]
                    self.__arr[j] = self.__arr[j + 1]
                    self.__arr[j + 1] = temp

    def desc_sort(self):
        for i in range(self.__ukuran - 1):
            for j in range(self.__ukuran - i - 1):
                if self.__arr[j] < self.__arr[j + 1]:
                    temp = self.__arr[j]
                    self.__arr[j] = self.__arr[j + 1]
                    self.__arr[j + 1] = temp

    def input_dalam(self):
        try:
            self.__ukuran = int(input("Masukkan ukuran array : "))
            if self.__ukuran > 100:
                self.__ukuran = 100
            if self.__ukuran < 0:
                self.__ukuran = 0
            for i in range(self.__ukuran):
                val = int(input(f"Masukkan nilai ke-{i + 1} = "))
                self.__arr[i] = val
        except ValueError:
            print("Input harus berupa angka!")

    def cetak_array(self):
        print("Print Array:")
        if self.__ukuran == 0:
            print("(Array masih kosong)")
            return
        for i in range(self.__ukuran):
            print(f"Nilai ke-{i + 1} = {self.__arr[i]}")

    def cetak_proses(self):
        print("\nArray :")
        self.cetak_array()
        print(f"Rata-rata         = {self.average_value()}")
        print(f"Nilai Tertinggi = {self.max_value()}")
        print(f"Nilai Terendah  = {self.min_value()}")


class Menu:
    def __init__(self):
        self.obj1 = MyLarik()
        self.obj2 = MyLarik()
        self.obj3 = MyLarik()
        self.obj4 = MyLarik()

    def tampilkan_menu(self):
        while True:
            print("\n====================================")
            print("        MENU APLIKASI MYLARIK       ")
            print("====================================")
            print("1. Input Data Array")
            print("2. Tampilkan Array")
            print("3. Lihat Analisis")
            print("4. Sorting Array")
            print("0. Keluar")
            print("------------------------------------")
            
            pilihan = input("Pilih menu: ").strip()
            if not pilihan:
                continue

            if pilihan == '1':
                print("\n====================================")
                print("          PILIH OBJEK INPUT         ")
                print("====================================")
                print("1. Objek 1 (Via Setter)")
                print("2. Objek 2 (Via Cons Parameter)")
                print("3. Objek 3 (Via Input Dalam Class)")
                print("4. Objek 4 (Via Input Luar Class)")
                print("0. Kembali")
                print("------------------------------------")
                
                try:
                    pilih_menu1 = int(input("Pilih menu: "))
                except ValueError:
                    print("Input harus berupa angka!")
                    continue

                if pilih_menu1 == 1:
                    self.obj1.set_ukuran(3)
                    self.obj1.set_nilai(0, 10)
                    self.obj1.set_nilai(1, 20)
                    self.obj1.set_nilai(2, 30)
                    print("\n[Sukses] Data Objek 1 berhasil diisi via Setter!")
                elif pilih_menu1 == 2:
                    data_awal = [55, 75, 90, 40]
                    self.obj2 = MyLarik(4, data_awal)
                    print("\n[Sukses] Data Objek 2 berhasil diisi via Constructor Parameter!")
                elif pilih_menu1 == 3:
                    print("\n--- Input untuk Objek 3 ---")
                    self.obj3.input_dalam()
                    print("\n[Sukses] Data Objek 3 berhasil disimpan!")
                elif pilih_menu1 == 4:
                    try:
                        print("\n--- Input Luar Class untuk Objek 4 ---")
                        n_luar = int(input("Masukkan ukuran array: "))
                        arr_luar = []
                        for i in range(n_luar):
                            val = int(input(f"Masukkan nilai ke-{i + 1} = "))
                            arr_luar.append(val)
                        self.obj4 = MyLarik(n_luar, arr_luar)
                        print("\n[Sukses] Data Objek 4 berhasil disimpan!")
                    except ValueError:
                        print("Input harus berupa angka!")

            elif pilihan == '2':
                print("\n====================================")
                print("         PILIH OBJEK OUTPUT         ")
                print("====================================")
                print("1. Objek 1")
                print("2. Objek 2")
                print("3. Objek 3")
                print("4. Objek 4")
                print("0. Kembali")
                print("------------------------------------")
                
                try:
                    pilih_menu2 = int(input("Pilih menu: "))
                except ValueError:
                    print("Input harus berupa angka!")
                    continue

                if pilih_menu2 == 1:
                    self.obj1.cetak_array()
                elif pilih_menu2 == 2:
                    self.obj2.cetak_array()
                elif pilih_menu2 == 3:
                    self.obj3.cetak_array()
                elif pilih_menu2 == 4:
                    self.obj4.cetak_array()

            elif pilihan == '3':
                print("\n====================================")
                print("         PILIH OBJEK ANALISIS       ")
                print("====================================")
                print("1. Objek 1")
                print("2. Objek 2")
                print("3. Objek 3")
                print("4. Objek 4")
                print("0. Kembali")
                print("------------------------------------")
                
                try:
                    pilih_menu3 = int(input("Pilih menu: "))
                except ValueError:
                    print("Input harus berupa angka!")
                    continue

                if pilih_menu3 == 1:
                    self.obj1.cetak_proses()
                elif pilih_menu3 == 2:
                    self.obj2.cetak_proses()
                elif pilih_menu3 == 3:
                    self.obj3.cetak_proses()
                elif pilih_menu3 == 4:
                    self.obj4.cetak_proses()

            elif pilihan == '4':
                print("\n====================================")
                print("          PILIH OBJEK SORT          ")
                print("====================================")
                print("1. Objek 1")
                print("2. Objek 2")
                print("3. Objek 3")
                print("4. Objek 4")
                print("0. Kembali")
                print("------------------------------------")
                
                try:
                    pilih_menu4 = int(input("Pilih menu: "))
                except ValueError:
                    print("Input harus berupa angka!")
                    continue

                if 1 <= pilih_menu4 <= 4:
                    print("Pilih Jenis Sorting:")
                    print("1. Ascending (Kecil ke Besar)")
                    print("2. Descending (Besar ke Kecil)")
                    try:
                        jenis_sort = int(input("Pilihan: "))
                    except ValueError:
                        print("Input harus berupa angka!")
                        continue

                    target_obj = None
                    if pilih_menu4 == 1:
                        target_obj = self.obj1
                    elif pilih_menu4 == 2:
                        target_obj = self.obj2
                    elif pilih_menu4 == 3:
                        target_obj = self.obj3
                    elif pilih_menu4 == 4:
                        target_obj = self.obj4

                    if target_obj:
                        if jenis_sort == 1:
                            target_obj.asc_sort()
                        else:
                            target_obj.desc_sort()
                        print("\nHasil setelah sorting:")
                        target_obj.cetak_array()

            elif pilihan == '0':
                print("\nTerima kasih telah menggunakan program ini!")
                break
            else:
                print("\nPilihan tidak valid! Silakan masukkan angka yang benar.")


class MainApp:
    def __init__(self):
        self.menu_utama = Menu()

    def jalankan_aplikasi(self):
        self.menu_utama.tampilkan_menu()


if __name__ == "__main__":
    program_utama = MainApp()
    program_utama.jalankan_aplikasi()