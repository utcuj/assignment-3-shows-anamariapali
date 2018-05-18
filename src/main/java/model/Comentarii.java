package model;

import javax.persistence.*;

@Entity
@Table(name ="comentarii")
public
class Comentarii {
    @Id
    @Column(name="idComentariu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComentariu;
    @Column(name = "idUser")
    private int idUser;
    @Column(name = "idShow")
    private int idShow;
    @Column(name = "comentariu")
    private String comentariu;

    public
    Comentarii(int idUser, int idShow, String comentariu) {
        this.idUser = idUser;
        this.idShow = idShow;
        this.comentariu = comentariu;
    }
    public Comentarii(){}
    public
    int getIdComentariu() {
        return idComentariu;
    }

    public
    void setIdComentariu(int idComentariu) {
        this.idComentariu = idComentariu;
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

    public
    String getComentariu() {
        return comentariu;
    }

    public
    void setComentariu(String comentariu) {
        this.comentariu = comentariu;
    }
}
