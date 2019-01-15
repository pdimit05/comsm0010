package uob_todo.api;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Users {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column()
    private int points;

    protected Users(){}

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
        this.points = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPoints() {
        return points;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public void setPassword(String password) {
        this.password=password;
    }


}
