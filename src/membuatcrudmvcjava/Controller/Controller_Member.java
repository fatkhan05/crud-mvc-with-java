package membuatcrudmvcjava.Controller;

import javax.swing.JOptionPane;
import membuatcrudmvcjava.DAO.DAO_Member;
import membuatcrudmvcjava.DAOImplement.Implement_Member;
import membuatcrudmvcjava.Model.Model_Member;
import membuatcrudmvcjava.Model.Tabel_Model_Member;
import membuatcrudmvcjava.View.View_Member;

/**
 *
 * @author FATKHAN
 */
public class Controller_Member {
    
    View_Member frame_member;
    Implement_Member implement_member;
    java.util.List<Model_Member> list_member;
    
    public Controller_Member(View_Member frame_member){
        this.frame_member = frame_member;
        implement_member = new  DAO_Member();
        list_member =implement_member.getAll();
    }
    
    // Tombol Reset
    public void reset(){
        frame_member.getTxtIDKode().setText("");
        frame_member.getTxtNamaPelanggan().setText("");
        frame_member.getTxtNoTelp().setText("");
        frame_member.getTxtAlamat().setText("");
        frame_member.getTxtPaketPelanggan().setSelectedItem("---------Pilih Paket-------");
    }
    
    // Tampil Data Ke tabel
    public void isiTable(){
        list_member = implement_member.getAll();
        Tabel_Model_Member tmb = new Tabel_Model_Member(list_member);
    }
    
    // Menampilkan Data Ke Form Ketika di Klik
    public void isiField(int row){
        frame_member.getTxtIDKode().setText(list_member.get(row).toString());
        frame_member.getTxtNamaPelanggan().setText(list_member.get(row).getNama());
        frame_member.getTxtNoTelp().setText(list_member.get(row).getNo_telp());
        frame_member.getTxtAlamat().setText(list_member.get(row).getAlamat());
        frame_member.getTxtPaketPelanggan().setSelectedItem(list_member.get(row).getPaket());
    }
    
    //  Insert Data Dari Form Ke Database
    public void insert(){
        if(!frame_member.getTxtNamaPelanggan().getText().trim().isEmpty()&
            !frame_member.getTxtNoTelp().getText().trim().isEmpty()&
                !frame_member.getTxtAlamat().getText().trim().isEmpty()){
        Model_Member b = new Model_Member();
        b.setNama(frame_member.getTxtNamaPelanggan().getText());
        b.setNo_telp(frame_member.getTxtNoTelp().getText());
        b.setAlamat(frame_member.getTxtAlamat().getText());
        b.setPaket(frame_member.getTxtPaketPelanggan().getSelectedItem().toString());
        
        implement_member.insert(b);
        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        } else {
            JOptionPane.showMessageDialog(frame_member, "Data Tidakk Boleh Kosong");
        }
    }
    
    // Update Data Dari Table Ke Database
    public void update(){
        if(!frame_member.getTxtIDKode().getText().trim().isEmpty()){
            Model_Member b = new Model_Member();
            b.setNama(frame_member.getTxtNamaPelanggan().getText());
            b.setNo_telp(frame_member.getTxtNoTelp().getText());
            b.setAlamat(frame_member.getTxtAlamat().getText());
            b.setPaket(frame_member.getTxtPaketPelanggan().getSelectedItem().toString());
            b.setId(Integer.parseInt(frame_member.getTxtIDKode().getText()));
            
            implement_member.update(b);
            JOptionPane.showMessageDialog(null, "Data Berhasil Diupdate");
        } else {
            JOptionPane.showMessageDialog(frame_member, "Silahkan Pilih Dta Yang Akan Diudate");
        }
    }
    
    // Hapus Data Data Tabel
    public void delete(){
        if(!frame_member.getTxtIDKode().getText().trim().isEmpty()){
            int id = Integer.parseInt(frame_member.getTxtIDKode().getText());
            implement_member.delete(id);
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        } else {
            JOptionPane.showMessageDialog(frame_member, "Silahkan Pilih Data Yang Akan Dihapus");
        }
    }
    
    // Cari Data
    public void isiTableCariNama(){
        list_member = implement_member.getCariNama(frame_member.getTxtCariData().getText());
        Tabel_Model_Member tmb = new Tabel_Model_Member(list_member);
        frame_member.getTabelDataMember().setModel(tmb);
    }
    
    public void cariNama(){
        if(!frame_member.getTxtCariData().getText().trim().isEmpty()){
            implement_member.getCariNama((frame_member.getTxtCariData().getText()));
            isiTableCariNama();
        } else {
            JOptionPane.showMessageDialog(frame_member, "Silahkan Pilih Data !!!!!");
        }
    }
}
