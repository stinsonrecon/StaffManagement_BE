package Model;

public class CanBo {

    protected int IdCanBo;
    protected String ten;
    protected String gioiTinh;
    protected int tuoi;
    protected String diaChi;
    protected String SDT;
    protected String email;


    public CanBo(int IdCanBo, String ten, String gioiTinh, int tuoi, String diaChi, String SDT, String email) {
        this.IdCanBo = IdCanBo;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.tuoi = tuoi;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.email = email;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getIdCanBo() {
        return IdCanBo;
    }

    public void setIdCanBo(int idCanBo) {
        IdCanBo = idCanBo;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CanBo{" +
                "IdCanBo=" + IdCanBo +
                ", ten='" + ten + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", tuoi='" + tuoi + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", SDT='" + SDT + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

