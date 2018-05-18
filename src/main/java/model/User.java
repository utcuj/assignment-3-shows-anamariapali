package model;

import javax.persistence.*;

@Entity
@Table(name ="users")

public class User {
@Id
@Column(name="idUser")
@GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idUser;
@Column(name="username")
    private String userName;
@Column(name="password")
    private String password;
@Column(name="typeUser")
    private String typeUser;

   

    public String getTypeUser() {
	return typeUser;
}
public void setTypeUser(String typeUser) {
	this.typeUser = typeUser;
}
	public User(int id, String userName, String password,String type) {
        this.idUser=id;
        this.userName = userName;
        this.password = password;
        this.typeUser=type;
    }
	public User( String userName, String password,String type) {
        
        this.userName = userName;
        this.password = password;
        this.typeUser=type;
    }
    public User() {

    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;

    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
