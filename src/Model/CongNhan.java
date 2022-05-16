package Model;


public class CongNhan  extends CanBo{
    private static final String IdPREFIX = "CN";
    private String capBac;
    private String IDNhanVien;

    public CongNhan(int IdCanBo, String ten, String gioiTinh, int tuoi, String diaChi, String SDT, String email, String capBac) {
        super(IdCanBo, ten, gioiTinh, tuoi, diaChi, SDT, email);
        this.IDNhanVien = IdPREFIX + IdCanBo;
        this.capBac = capBac;
    }

    public int getIdCanBo() {
        return IdCanBo;
    }

    public void setIdCanBo(int idCanBo) {
        IdCanBo = idCanBo;
    }

    public String getCapBac() {
        return capBac;
    }

    public void setCapBac(String capBac) {
        this.capBac = capBac;
    }

    @Override
    public String toString() {
        return "CongNhan{" +
                "IdCongNhan=" + IDNhanVien +
                ", ten='" + ten + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", tuoi=" + tuoi +
                ", diaChi='" + diaChi + '\'' +
                ", SDT='" + SDT + '\'' +
                ", email='" + email + '\'' +
                ", capBac='" + capBac + '\'' +
                '}';
    }
}
