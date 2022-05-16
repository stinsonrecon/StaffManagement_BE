package Model;

public class NhanVien extends CanBo{
    private static  final String IdPREFIX = "NV";
    
    private String IDNhanVien ;

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    private String congViec;

    public NhanVien(int IdCanBo, String ten, String gioiTinh, int tuoi, String diaChi, String SDT, String email, String congViec) {
        super(IdCanBo, ten, gioiTinh, tuoi, diaChi, SDT, email);
        this.IDNhanVien = IdPREFIX+IdCanBo;
        this.congViec = congViec;
    }
    
    public int getIdCanBo() {
        return IdCanBo;
    }

    public void setIdCanBo(int idCanbo) {
        IdCanBo = idCanbo;
    }

    public String getCongViec() {
        return congViec;
    }

    public void setCongViec(String congViec) {
        this.congViec = congViec;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "IdNhanVien=" + IDNhanVien +
                ", ten='" + ten + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", tuoi=" + tuoi +
                ", diaChi='" + diaChi + '\'' +
                ", SDT='" + SDT + '\'' +
                ", email='" + email + '\'' + ", congViec='" + congViec +
                '}';
    }
}
