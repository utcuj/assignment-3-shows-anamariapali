package model;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name ="shows")
public
class Shows {
    @Id
    @Column(name="idShow")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdShow;
    @Column(name = "nameShow")
    private String nameShow;
    @Column(name = "typeShow")
    private String typeShow;
    @Column(name = "detali")
    private String detali;
    @Column(name = "raiting")
    private int raiting;
    @Column(name = "releaseDate")
    private Date releaseDate;
    @Column(name = "no")
    private int no;
 
	public Shows(){}
    public
    Shows(int idShow,String nameShow,String typeShow ,String detali,Date realiseDate,int raiting,int no) {
        this.IdShow= idShow;
        this.typeShow=typeShow;
        this.nameShow = nameShow;
        this.detali = detali;
        this.raiting=raiting;
        this.releaseDate=realiseDate;
        this.no=no;
    }
    
    public int getNo() {
 		return no;
 	}
 	public void setNo(int no) {
 		this.no = no;
 	}
    public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getRaiting() {
		return raiting;
	}
	public void setRaiting(int raiting) {
		this.raiting = raiting;
	}
	public
    String getNameShow() {
        return nameShow;
    }

    public
    void setNameShow(String nameShow) {
        this.nameShow = nameShow;
    }

    public
    String getDetali() {
        return detali;
    }

    public
    void setDetali(String detali) {
        this.detali = detali;
    }
    public
    String getTypeShow() {
        return typeShow;
    }

    public
    void setTypeShow(String typeShow) {
        this.typeShow = typeShow;
    }

    public
    int getIdShow() {
        return IdShow;
    }

    public
    void setIdShow(int idShow) {
        IdShow = idShow;
    }
}

