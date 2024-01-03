import java.util.Scanner;

abstract class Barang {
    protected String kodeBarang;
    protected String namaBarang;
    protected float hargaBarang;
    protected int jumlahBeli;

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public void setHargaBarang(float hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public void setJumlahBeli(int jumlahBeli) {
        this.jumlahBeli = jumlahBeli;
    }

    public float getBayar() {
        return hargaBarang * jumlahBeli;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public int getJumlahBeli() {
        return jumlahBeli;
    }

    public float getHargaBarang() {
        return hargaBarang;
    }

    public abstract void displayInfo();
}

class Databarang extends Barang {
    @Override
    public void displayInfo() {
        System.out.println((this.hashCode() % 1000) + "\t" + kodeBarang + "\t" + hargaBarang + "\t" + namaBarang + "\t" + jumlahBeli + "\t" + getBayar());
    }
}

interface Pembayaran {
    double hitungDiskon(double jumlahBayar);

    void displayMetodePembayaran();
}

class Transaksi implements Pembayaran {
    @Override
    public double hitungDiskon(double jumlahBayar) {
        if (jumlahBayar >= 500000) {
            return jumlahBayar * 0.05;
        }
        return 0;
    }

    @Override
    public void displayMetodePembayaran() {
        System.out.println("Metode pembayaran yang tersedia :");
        System.out.println("1. Visa");
        System.out.println("2. Debit");
        System.out.println("3. Cash");
    }
}

public class Penjualan {
    public static void main(String[] args) {
        Scanner inputUser = new Scanner(System.in);

        System.out.println("Masukkan Nama Kasir : ");
        String namaKasir = inputUser.nextLine();

        System.out.println("Nama Kasir: " + namaKasir);

        Barang[] databarangs = null;
        double jumlahBayar = 0;
        double diskon = 0, hargaDiskon = 0;
        int pilihan, jumlahBarang, x, pembayaran, biayaKartu = 0, totalbayar;

        Transaksi transaksi = new Transaksi();

        do {
            System.out.println("Silahkan pilih menu transaksi :");
            System.out.println("1. Input data penjualan");
            System.out.println("2. Pembayaran");
            System.out.println("3. Keluar Program");
            System.out.print("Masukan Pilihan : ");
            pilihan = inputUser.nextInt();
            inputUser.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Jumlah Barang : ");
                    jumlahBarang = inputUser.nextInt();
                    inputUser.nextLine();
                    databarangs = new Databarang[jumlahBarang];
                    for (x = 0; x < databarangs.length; x++) {
                        databarangs[x] = new Databarang();
                        System.out.println("Barang ke-" + (x + 1) + ":");
                        System.out.print("Masukkan Kode Barang : ");
                        databarangs[x].setKodeBarang(inputUser.next());
                        inputUser.nextLine();
                        System.out.print("Masukkan nama barang : ");
                        databarangs[x].setNamaBarang(inputUser.nextLine());
                        System.out.print("Masukkan harga barang : ");
                        databarangs[x].setHargaBarang(inputUser.nextFloat());
                        System.out.print("Masukkan jumlah beli : ");
                        databarangs[x].setJumlahBeli(inputUser.nextInt());
                        jumlahBayar += databarangs[x].getBayar();
                    }
                    break;

                case 2:
                    System.out.println("\t\t\t\t\t\t\tDATA PENJUALAN BARANG");
                    System.out.println("\t\t\t\t\t\t\t\tPT GALATIA LIMA");
                    System.out.println("Nama Kasir : " + namaKasir);
                    System.out.println("=================================================================================");
                    System.out.println("No. \tKode Barang \tHarga Barang \tNama Barang \tJumlah Beli \tBayar");
                    System.out.println("=================================================================================");
                    for (x = 0; x < databarangs.length; x++) {
                        System.out.println((x + 1) + "\t" + "\t" + databarangs[x].getKodeBarang() + "\t" + "\t" + "\t" + "\t" + databarangs[x].getHargaBarang() + "\t" + "\t" + databarangs[x].getNamaBarang() + "\t" + "\t" + "\t" + databarangs[x].getJumlahBeli() + "\t" + "\t" + "\t" + "\t" + databarangs[x].getBayar());
                    }


                    if (jumlahBayar >= 500000) {
                        diskon = jumlahBayar * 0.05;
                    }
                    hargaDiskon = jumlahBayar - diskon;

                    System.out.println("=================================================================================");
                    System.out.println("Jumlah Bayar \t\t: Rp." + jumlahBayar);
                    System.out.println("Diskon \t\t\t\t: Rp." + diskon);

                    transaksi.displayMetodePembayaran();
                    System.out.print("Masukan pilihan pembayaran : ");
                    pembayaran = inputUser.nextInt();

                    switch (pembayaran) {
                        case 1:
                            biayaKartu = (int) (0.3 * jumlahBayar);
                            System.out.println("Visa \tbiaya kartu : Rp. " + biayaKartu);
                            break;
                        case 2:
                            biayaKartu = 0;
                            System.out.println("Debit \tbiaya kartu : Rp.- ");
                            break;
                        case 3:
                            biayaKartu = 0;
                            System.out.println("Cash \tbiaya kartu : Rp.- ");
                            break;
                        default:
                            System.exit(0);
                    }

                    totalbayar = (int) (hargaDiskon + biayaKartu);
                    System.out.println("Total bayar \t\t : Rp." + totalbayar);
                    break;
            }
        } while (pilihan != 3);
    }
}
