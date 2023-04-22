package org.example.database.dao;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "catch", schema = "public", catalog = "examen")
public class CatchEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "nume", nullable = true, length = -1)
    private String nume;
    @Basic
    @Column(name = "ora", nullable = true)
    private Integer ora;
    @Basic
    @Column(name = "minute", nullable = true)
    private Integer minute;
    @Basic
    @Column(name = "cautara", nullable = true)
    private Integer cautara;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getOra() {
        return ora;
    }

    public void setOra(Integer ora) {
        this.ora = ora;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getCautara() {
        return cautara;
    }

    public void setCautara(Integer cautara) {
        this.cautara = cautara;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatchEntity that = (CatchEntity) o;
        return id == that.id && Objects.equals(nume, that.nume) && Objects.equals(ora, that.ora) && Objects.equals(minute, that.minute) && Objects.equals(cautara, that.cautara);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, ora, minute, cautara);
    }
}
