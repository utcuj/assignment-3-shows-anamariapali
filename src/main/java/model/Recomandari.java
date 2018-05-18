package model;

import javax.persistence.*;

@Entity
@Table(name ="recomandari")
public class Recomandari {
    @Id
    @Column(name="idRecomandare")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecomandare;
    @Column(name="idUser")
    private int idUser;
    @Column(name="idUserTo")
    private int idUserTo;
    @Column(name="idShow")
    private int idShow;
 
    private String detali;

    public
    Recomandari(int idUser, int idUserTo,int idShow) {
        this.idUser = idUser;
        this.idShow=idShow;
        this.idUserTo=idUserTo;
        //this.detali = detali;
    }
    public Recomandari(){}

    public
    String getDetali() {
        return detali;
    }

    public
    void setDetali(String detali) {
        this.detali = detali;
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
    int getIdRecomandare() {
        return idRecomandare;
    }

    public
    void setIdRecomandare(int idRecomandare) {
        this.idRecomandare = idRecomandare;
    }
    
    public int getIdUserTo() {
  		return idUserTo;
  	}
  	public void setIdUserTo(int idUserTo) {
  		this.idUserTo = idUserTo;
  	}
  	public int getIdShow() {
  		return idShow;
  	}
  	public void setIdShow(int idShow) {
  		this.idShow = idShow;
  	}
}
