#include <iostream>

class KoordinatKartesian {
private:
    double absis;
    double ordinat;

public:
    KoordinatKartesian() : absis(0), ordinat(0) {}

    KoordinatKartesian(double absis, double ordinat)
        : absis(absis), ordinat(ordinat) {}

    double getAbsis() const {
        return absis;
    }

    double getOrdinat() const {
        return ordinat;
    }

    void setAbsis(double absis) {
        this->absis = absis;
    }

    void setOrdinat(double ordinat) {
        this->ordinat = ordinat;
    }

    void input() {
        std::cout << "Masukkan absis: ";
        std::cin >> absis;
        std::cout << "Masukkan ordinat: ";
        std::cin >> ordinat;
    }

    void tampilkan() const {
        std::cout << "(" << absis << ", " << ordinat << ")\n";
    }
};

int main() {
    // Objek 1: nilai koordinat diisi melalui setter
    KoordinatKartesian titik1;
    titik1.setAbsis(2);
    titik1.setOrdinat(3);

    // Objek 2: nilai koordinat diinput melalui method di dalam class
    KoordinatKartesian titik2;
    std::cout << "Input koordinat titik 2:\n";
    titik2.input();

    // Objek 3: nilai koordinat diberikan melalui constructor berparameter
    KoordinatKartesian titik3(4, 5);

    std::cout << "\nTitik 1 (melalui setter): ";
    titik1.tampilkan();

    std::cout << "Titik 2 (input di dalam class): ";
    titik2.tampilkan();

    std::cout << "Titik 3 (constructor berparameter): ";
    titik3.tampilkan();

    return 0;
}
