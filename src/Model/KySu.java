package Model;

public class KySu extends CanBo{
    private static final String IdPREFIX = "KS";
   
    private String nganhDaoTao;
    private String IDKySu;

    public String getIDKySu() {
        return IDKySu;
    }

    public KySu(int IdCanBo, String ten, String gioiTinh, int tuoi, String diaChi, String SDT, String email, String nganhDaoTao) {
        super(IdCanBo, ten, gioiTinh, tuoi, diaChi, SDT, email);
        this.IDKySu = IdPREFIX + IdCanBo;
        this.nganhDaoTao = nganhDaoTao;

    }

    public int getIdCanBo() {
        return IdCanBo;
    }

    public void setIdCanBo(int IdCanBo) {
        this.IdCanBo = IdCanBo;
    }

    public String getNganhDaoTao() {
        return nganhDaoTao;
    }

    public void setNganhDaoTao(String nganhDaoTao) {
        this.nganhDaoTao = nganhDaoTao;
    }

    @Override
    public String toString() {
        return "KySu{" +
                "  IdNhanVien=" + IDKySu + ", ten='" + ten + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", tuoi=" + tuoi +
                ", diaChi='" + diaChi + '\'' +
                ", SDT='" + SDT + '\'' +
                ", email='" + email + '\'' + ", nganhDaoTao='" + nganhDaoTao +
                '}';
    }
}
