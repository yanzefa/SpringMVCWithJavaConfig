package com.github.izhangzhihao.SpringMVCSeedProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Teacher")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements Serializable {
    private static final long serialVersionUID = 647657865874500228L;
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String passWord;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Course> courseList;

    @ManyToMany(mappedBy = "teacherList")
    private List<Project> projectList;

    public Teacher(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }

}

