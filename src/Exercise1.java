import java.util.Scanner;

public class Exercise1 {
    public static class PhanSo {
        private int tuSo;
        private int mauSo;

        public int getTuSo() {
            return tuSo;
        }

        public void setTuSo(int tuSo) {
            this.tuSo = tuSo;
        }

        public int getMauSo() {
            return mauSo;
        }

        public void setMauSo(int mauSo) {
            this.mauSo = mauSo;
        }

        //Ham constructor
        public PhanSo(int tuSo, int mauSo) {
            this.tuSo = tuSo;
            this.mauSo = mauSo;
        }

        public PhanSo congHaiPS(PhanSo phanSoKhac) {
            int mauSoChung = this.mauSo * phanSoKhac.mauSo; //Quy đồng mẫu chung
            int congTu = this.tuSo * phanSoKhac.mauSo + this.mauSo * phanSoKhac.tuSo; // tử số này nhân mẫu số kia cộng lại
            return new PhanSo(congTu, mauSoChung);
        }

        public PhanSo nhanHaiHS(PhanSo phanSoKhac) {
            return new PhanSo(this.tuSo * phanSoKhac.tuSo, this.mauSo * phanSoKhac.mauSo);
        }

        public PhanSo chiaHaiPS(PhanSo phanSoKhac) {
            return new PhanSo(this.tuSo * phanSoKhac.mauSo, this.mauSo * phanSoKhac.tuSo);
        }

        public String StringFraction() //Viết phân số dạng String
        {
            return tuSo + "/" + mauSo;
        }
    }

    //Chuyển Phân số dạng đang ở dạng String (1/2, 1/3) sang 1 phân số
    public static PhanSo StringToFraction(String stringFraction) {
        //Tách các kí tự bởi biểu thức chính quy "/"
        String[] str = stringFraction.split("/");
        if (str.length != 2) return null;
        else {
            //Chuyển kí tự dạng String sang int
            int tuPS = Integer.parseInt(str[0].trim());
            int mauPS = Integer.parseInt(str[1].trim());
            return new PhanSo(tuPS, mauPS);
        }
    }

    public static double log2(PhanSo pSo) {
        double logTuSo = Math.log(pSo.getTuSo() / Math.log(2)); //Log2(tuSo)
        double logMaSo = Math.log(pSo.getMauSo() / Math.log(2)); //Log2(mauSo)
        return logTuSo - logMaSo; //log (A/B) = logA - logB
    }

    public void taoMaTran() {
        Scanner sc = new Scanner(System.in); //Nhap từ bàn phím
        int soHang;
        int soCot;
        System.out.println("Mời nhập bàn phím kích thước ma trận (MxN)");
        System.out.print("Nhập số hàng M: ");
        soHang = sc.nextInt();
        System.out.print("Nhập số cột N: ");
        soCot = sc.nextInt();
        //Kiểm tra điều kiện số nguyên dương của M và N
        while (soHang <= 0 || soCot <= 0) {
            System.out.println("Giá trị không hợp lệ, mời nhập giá trị nguyên dương cho M: ");
            soHang = sc.nextInt();
            System.out.println("Giá trị không hợp lệ, mời nhập giá trị nguyên dương cho N: ");
            soCot = sc.nextInt();
        }
        PhanSo[][] martrix = new PhanSo[soHang][soCot];
        PhanSo sum = new PhanSo(0,1);
        //Nhập số vào ma trận xác suất
        while (true) {
            for (int i = 0; i < soHang; i++) {
                for (int j = 0; j < soCot; j++) {
                    System.out.printf("Mời nhập phần tử a[%d][%d]: ", i,j);
                    String inputString = sc.next(); // Nhập vào bàn phím dạng String (1/4,1/5...)
                    PhanSo phanSo = StringToFraction(inputString); // Đổi String sang dạng phân số
                    if(phanSo.tuSo < 0 || phanSo.mauSo <= 0 || phanSo.tuSo / phanSo.mauSo > 1)
                    {
                        j--;
                        System.out.println("Xác suất không được âm và lớn hơn 1. Mời nhập lại!");
                    }
                    else
                    {
                        martrix[i][j] = phanSo;
                        sum = sum.congHaiPS(phanSo); //Cộng tổng các xác suất đã nhập vào
                    }
                }
            }
            //Kiem tra phân số sum nếu không bằng 1 thì ma trận sai, vì tổng ma trận xác xuất phải băng 1
            if(sum.tuSo != sum.mauSo)
            {
                System.out.println("Ma trận chưa đúng. Mời nhập lại");
                int i =0, j=0;
                sum = new PhanSo(0,1);
                continue;
            }

        }
    }

    public static void main(String[] args) {
        System.out.println("Test");
    }
}

