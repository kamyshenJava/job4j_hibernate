package ru.job4j.selectfetch;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "applicants")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToOne(mappedBy = "applicant")
    private VacancyDb db;

    public static Applicant of(String name) {
        Applicant applicant = new Applicant();
        applicant.name = name;
        return applicant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VacancyDb getDb() {
        return db;
    }

    public void setDb(VacancyDb db) {
        this.db = db;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Applicant applicant = (Applicant) o;
        return id == applicant.id && Objects.equals(name, applicant.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
