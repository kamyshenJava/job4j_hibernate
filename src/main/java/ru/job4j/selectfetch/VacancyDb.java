package ru.job4j.selectfetch;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "vacancy_db")
public class VacancyDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Applicant applicant;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "vacancyDb_id")
    private Set<Vacancy> vacancies;

    public static VacancyDb of(String name) {
        VacancyDb vacancyDb = new VacancyDb();
        vacancyDb.name = name;
        return vacancyDb;
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

    public Set<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(Set<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VacancyDb vacancyDb = (VacancyDb) o;
        return id == vacancyDb.id && Objects.equals(name, vacancyDb.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "VacancyDb{id=" + id + ", name='" + name + '\'' + ", applicant=" + applicant + ", vacancies="
                + vacancies + '}';
    }
}
