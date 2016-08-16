package com.github.izhangzhihao.SpringMVCSeedProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project implements Serializable {
    private static final long serialVersionUID = 7417435745786587568L;
    @Id
    @NotNull
    private String projectId;
    @NotNull
    private String projectName;
    @NotNull
    @ManyToMany
    @JoinTable(name = "teacher_project", joinColumns = {@JoinColumn(name = "projectId")}, inverseJoinColumns = {@JoinColumn(name = "teacherId")})
    private List<Teacher> teacherList;

}
