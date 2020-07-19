package com.dz.visitcounter.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Page")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_id")
    private Long page_id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Visit",
            joinColumns = { @JoinColumn(name = "page_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    Set<User> users = new HashSet<>();

    public Long getPage_id() {
        return page_id;
    }

    public void setPage_id(Long page_id) {
        this.page_id = page_id;
    }
}
