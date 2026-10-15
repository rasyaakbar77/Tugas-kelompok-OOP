/*
Nama Program : 2dArray.java
Anggota Kelompok - NPM: 
    1. Rasya Islami Akbar - 140810250009
    2. Ghiyats Khairul Mala - 140810250102
Kelas : A
Tanggal Pengerjaan : 09-10-2026
Deskripsi Program : Sebuah program untuk menghitung penjumlahan dan perkalian matriks 2 dimensi
                    dengan proses secara void dan fungsi.
*/

import java.util.Scanner;

class Matriks2d {
    private int baris;
    private int kolom;
    private int[][] nilai = new int[10][10];

    public Matriks2d() {
        baris = 10;
        kolom = 10;
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                this.nilai[i][j] = 0;
            }
        }
    }

    public Matriks2d(int baris, int kolom) {
        this.baris = baris;
        this.kolom = kolom;
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                this.nilai[i][j] = 0;
            }
        }
    }

    // Setter Baris
    public void setBaris(int baris) {
        this.baris = baris;
    }

    // Setter Kolom
    public void setKolom(int kolom) {
        this.kolom = kolom;
    }

    // Setter Nilai
    public void setNilai(int[][] nilaiBaru) {
        int i = 0;
        for (int[] barisNilai : nilaiBaru) {
            if (i >= baris || i >= 10) {
                break;
            }
            int j = 0;
            for (int nilaiElemen : barisNilai) {
                if (j >= kolom || j >= 10) {
                    break;
                }
                this.nilai[i][j] = nilaiElemen;
                ++j;
            }
            ++i;
        }
    }

    public int getBaris() {
        return baris;
    }

    public int getKolom() {
        return kolom;
    }

    public int getNilai(int i, int j) {
        return nilai[i][j];
    }

    // input dalam
    public void inputMatriks() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Masukkan jumlah baris matriks (max 10) : ");
            baris = sc.nextInt();
            if (baris < 1 || baris > 10) {
                System.out.println("Jumlah baris harus antara 1 dan 10.");
            }
        } while (baris < 1 || baris > 10);
        
        do {
            System.out.print("Masukkan jumlah kolom matriks (max 10) : ");
            kolom = sc.nextInt();
            if (kolom < 1 || kolom > 10) {
                System.out.println("Jumlah kolom harus antara 1 dan 10.");
            }
        } while (kolom < 1 || kolom > 10);

        System.out.println("Masukkan elemen matriks:");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Nilai [" + i + "][" + j + "] : ");
                nilai[i][j] = sc.nextInt();
            }
        }
    }

    // Method penjumlahan matriks (void)
    public void penjumlahanMatriksVoid(Matriks2d a, Matriks2d b) {
        if (a.baris != b.baris || a.kolom != b.kolom) {
            System.out.println("Tidak dapat melakukan penjumlahan karena ordo matriks berbeda.");
            return;
        }
        baris = a.baris;
        kolom = a.kolom;
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                this.nilai[i][j] = a.nilai[i][j] + b.nilai[i][j];
            }
        }
    }

    // Method penjumlahan matriks (fungsi)
    public Matriks2d penjumlahanMatriksFungsi(Matriks2d a, Matriks2d b) {
        if (a.baris != b.baris || a.kolom != b.kolom) {
            System.out.println("Tidak dapat melakukan penjumlahan karena ordo matriks berbeda.");
            return new Matriks2d();
        }

        Matriks2d hasil = new Matriks2d(a.baris, a.kolom);
        for (int i = 0; i < a.baris; i++) {
            for (int j = 0; j < a.kolom; j++) {
                hasil.nilai[i][j] = a.nilai[i][j] + b.nilai[i][j];
            }
        }
        return hasil;
    }

    // Method perkalian matriks (void) 
    public void perkalianMatriksVoid(Matriks2d a, Matriks2d b) {
        if (a.kolom != b.baris) {
            System.out.println("Tidak dapat melakukan perkalian karena kolom matriks pertama tidak sama dengan baris matriks kedua.");
            return;
        }
        baris = a.baris;
        kolom = b.kolom;
        for (int i = 0; i < a.baris; i++) {
            for (int j = 0; j < b.kolom; j++) {
                this.nilai[i][j] = 0;
                for (int k = 0; k < a.kolom; k++) {
                    this.nilai[i][j] += a.nilai[i][k] * b.nilai[k][j];
                }
            }
        }
    }

    // Method perkalian matriks (fungsi)
    public Matriks2d perkalianMatriksFungsi(Matriks2d a, Matriks2d b) {
        if (a.kolom != b.baris) {
            System.out.println("Tidak dapat melakukan perkalian karena kolom matriks pertama tidak sama dengan baris matriks kedua.");
            return new Matriks2d();
        }

        Matriks2d hasil = new Matriks2d(a.baris, b.kolom);
        for (int i = 0; i < a.baris; i++) {
            for (int j = 0; j < b.kolom; j++) {
                hasil.nilai[i][j] = 0;
                for (int k = 0; k < a.kolom; k++) {
                    hasil.nilai[i][j] += a.nilai[i][k] * b.nilai[k][j];
                }
            }
        }
        return hasil;
    }

    // output
    public void outputMatriks() {
        System.out.println("Matriks (" + baris + "x" + kolom + "):");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print(nilai[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class menu {
    private int pilihan;

    public menu() {
        pilihan = 0;
    }

    public void tampilanMenu() {
        Scanner sc = new Scanner(System.in);

        Matriks2d matriks1 = new Matriks2d(); // setter
        matriks1.setBaris(3);
        matriks1.setKolom(3);
        matriks1.setNilai(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});

        Matriks2d matriks2 = new Matriks2d(3, 3); // constructor parameter
        matriks2.setNilai(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}); 

        Matriks2d matriks3 = new Matriks2d(); // input dalam

        do {
            System.out.println("=========================================");
            System.out.println("           PROGRAM 2D ARRAY              ");
            System.out.println("=========================================");
            System.out.println("1. Input Matriks 3");
            System.out.println("2. Lihat Semua Matriks");
            System.out.println("3. Penjumlahan Matriks");
            System.out.println("4. Perkalian Matriks");
            System.out.println("0. Keluar");
            System.out.println("=========================================");
            System.out.print(">> Masukkan Pilihan Menu : ");
            pilihan = sc.nextInt();

            switch (pilihan) {
                case 1 : {
                    System.out.println("Input Matriks 3 (via Input Dalam):");
                    matriks3.inputMatriks();
                    break;
                }
                case 2 : {
                    System.out.println("Matriks 1 (via Setter):");
                    matriks1.outputMatriks();

                    System.out.println("Matriks 2 (via Constructor Parameter):");
                    matriks2.outputMatriks();

                    System.out.println("Matriks 3 (via Input Dalam):");
                    matriks3.outputMatriks();
                    break;
                }
                case 3 : {
                    int o1, o2;
                    System.out.println("=========================================");
                    System.out.println("        PENJUMLAHAN 2 MATRIKS            ");
                    System.out.println("=========================================");
                    System.out.println("Pilihan Matriks (1: Matriks 1, 2: Matriks 2, 3: Matriks 3)");
                    System.out.print("Pilih Matriks pertama : ");
                    o1 = sc.nextInt();
                    System.out.print("Pilih Matriks kedua : ");
                    o2 = sc.nextInt();

                    Matriks2d m1 = (o1 == 1) ? matriks1 : (o1 == 2 ? matriks2 : matriks3);
                    Matriks2d m2 = (o2 == 1) ? matriks1 : (o2 == 2 ? matriks2 : matriks3);

                    // Validasi ordo terlebih dahulu agar pesan error hanya muncul sekali dan tidak mencetak matriks kosong
                    if (m1.getBaris() != m2.getBaris() || m1.getKolom() != m2.getKolom()) {
                        System.out.println("Tidak dapat melakukan penjumlahan karena ordo matriks berbeda.");
                        break;
                    }

                    Matriks2d hasilPenjumlahanVoid = new Matriks2d();
                    Matriks2d hasilPenjumlahanFungsi = new Matriks2d();

                    hasilPenjumlahanVoid.penjumlahanMatriksVoid(m1, m2);
                    hasilPenjumlahanFungsi = hasilPenjumlahanFungsi.penjumlahanMatriksFungsi(m1, m2);

                    System.out.println("Hasil Penjumlahan Matriks (via Void):");
                    hasilPenjumlahanVoid.outputMatriks();

                    System.out.println("Hasil Penjumlahan Matriks (via Fungsi):");
                    hasilPenjumlahanFungsi.outputMatriks();
                    break;
                }
                case 4 : {
                    int o1, o2;
                    System.out.println("=========================================");
                    System.out.println("         PERKALIAN 2 MATRIKS             ");
                    System.out.println("=========================================");
                    System.out.println("Pilihan Matriks (1: Matriks 1, 2: Matriks 2, 3: Matriks 3)");
                    System.out.print("Pilih Matriks pertama : ");
                    o1 = sc.nextInt();
                    System.out.print("Pilih Matriks kedua : ");
                    o2 = sc.nextInt();

                    Matriks2d m1 = (o1 == 1) ? matriks1 : (o1 == 2 ? matriks2 : matriks3);
                    Matriks2d m2 = (o2 == 1) ? matriks1 : (o2 == 2 ? matriks2 : matriks3);

                    // Validasi ordo perkalian terlebih dahulu
                    if (m1.getKolom() != m2.getBaris()) {
                        System.out.println("Tidak dapat melakukan perkalian karena kolom matriks pertama tidak sama dengan baris matriks kedua.");
                        break;
                    }

                    Matriks2d hasilPerkalianVoid = new Matriks2d();
                    Matriks2d hasilPerkalianFungsi = new Matriks2d();

                    hasilPerkalianVoid.perkalianMatriksVoid(m1, m2);
                    hasilPerkalianFungsi = hasilPerkalianFungsi.perkalianMatriksFungsi(m1, m2);

                    System.out.println("Hasil Perkalian Matriks (via Void):");
                    hasilPerkalianVoid.outputMatriks();

                    System.out.println("Hasil Perkalian Matriks (via Fungsi):");
                    hasilPerkalianFungsi.outputMatriks();
                    break;
                }
                case 0 : {
                    System.out.println("Keluar dari program.");
                    break;
                }
                default : {
                    System.out.println("Pilihan menu tidak valid.");
                    break;
                }
            } 
        } while (pilihan != 0);
    }
}

class Array2d{
    public static void main(String[] args) {
        menu m = new menu();
        m.tampilanMenu();
    }
}