package domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "job_applications")
public class JobApplication extends BaseEntity {
    private Sector Sector;
    private String profession;
    private BigDecimal salary;
    private String description;

    @Column(name = "sector", nullable = false)
    @Enumerated(EnumType.STRING)
    public Sector getSector() {
        return Sector;
    }

    public void setSector(Sector sector) {
        Sector = sector;
    }

    @Column(name = "profession", nullable = false)
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }


    @Column(name = "salary", nullable = false)
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
