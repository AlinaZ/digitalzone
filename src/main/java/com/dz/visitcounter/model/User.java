package com.dz.visitcounter.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "ip")
    private String ip;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Visit",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "page_id") }
    )
    Set<Page> pages = new HashSet<>();

    public User() {

    }

    public User(String ip){
        setIp(ip);
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
