package ru.job4j.selectfetch;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    public static Vacancy of(String description) {
        Vacancy vacancy = new Vacancy();
        vacancy.description = description;
        return vacancy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id && Objects.equals(description, vacancy.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
