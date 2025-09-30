// Lớp Phân Số
class PhanSo(var tu: Int, var mau: Int) {

    // Nhập phân số
    fun nhap() {
        while (true) {
            print("Nhập tử số: ")
            tu = readln().toInt()
            print("Nhập mẫu số: ")
            mau = readln().toInt()

            if (mau != 0) break
            println("Mẫu số khác 0. Nhập lại!")
        }
    }

    // Xuất phân số
    fun xuat() {
        println("$tu/$mau")
    }

    // Rút gọn phân số
    fun rutGon() {
        val ucln = ucln(tu, mau)
        tu /= ucln
        mau /= ucln
        // giữ mẫu dương
        if (mau < 0) {
            tu = -tu
            mau = -mau
        }
    }

    // So sánh với phân số khác
    fun soSanh(ps: PhanSo): Int {
        val trai = tu * ps.mau
        val phai = ps.tu * mau
        return when {
            trai < phai -> -1
            trai == phai -> 0
            else -> 1
        }
    }

    // Cộng hai phân số
    fun cong(ps: PhanSo): PhanSo {
        val tuMoi = tu * ps.mau + ps.tu * mau
        val mauMoi = mau * ps.mau
        val kq = PhanSo(tuMoi, mauMoi)
        kq.rutGon()
        return kq
    }

    // Hàm tìm ước chung lớn nhất
    private fun ucln(a: Int, b: Int): Int {
        return if (b == 0) kotlin.math.abs(a) else ucln(b, a % b)
    }
}

fun main() {
    print("Nhập n: ")
    val n = readln().toInt()

    // Tạo mảng n phân số
    val ds = Array(n) { PhanSo(0, 1) }

    println("Nhập các phân số:")
    for (i in ds.indices) {
        println("Phân số thứ ${i + 1}:")
        ds[i].nhap()
    }

    println("\n Mảng phân số :")
    for (ps in ds) ps.xuat()

    println("\n Mảng rút gọn:")
    for (ps in ds) {
        ps.rutGon()
        ps.xuat()
    }

    // Tính tổng các phân số
    var tong = PhanSo(0, 1)
    for (ps in ds) tong = tong.cong(ps)
    print("\nTổng phân số = ")
    tong.xuat()

    // Tìm phân số lớn nhất
    var max = ds[0]
    for (ps in ds) {
        if (ps.soSanh(max) > 0) max = ps
    }
    print("\nPhân số lớn nhất : ")
    max.xuat()

    // Sắp xếp giảm dần
    ds.sortWith { a, b -> b.soSanh(a) }
    println("\nDãy sau khi sắp xếp giảm dần:")
    for (ps in ds) ps.xuat()
}
