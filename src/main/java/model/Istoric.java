package model;

import javax.persistence.*;

@Entity
@Table(name ="istoric")
public
class Istoric {
    @Id
    @Column(name="idIstoric")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIstoric;
    @Column(name = "idUser")
    private int idUser;
    @Column(name = "idShow")
    private int idShow;
    public Istoric(){}
    public
    Istoric(int idUser, int idShow) {
        this.idUser = idUser;
        this.idShow = idShow;
    }

    public
    int getIdIstoric() {
        return idIstoric;
    }

    public
    void setIdIstoric(int idIstoric) {
        this.idIstoric = idIstoric;
    }

    public
    int getIdUser() {
        return idUser;
    }

    public
    void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public
    int getIdShow() {
        return idShow;
    }

    public
    void setIdShow(int idShow) {
        this.idShow = idShow;
    }
}
