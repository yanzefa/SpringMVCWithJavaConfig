package com.zhangzhihao.SpringMVCWithJavaConfig.Model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Project implements Serializable {
    @Id
    @NotNull
    private String projectId;
    @NotNull
    private String projectName;
    @NotNull
    @ManyToMany
    @JoinTable(name = "teacher_project", joinColumns = {@JoinColumn(name = "projectId")}, inverseJoinColumns = {@JoinColumn(name = "teacherId")})
    private List<Teacher> teacherList;

    public Project(String projectId, String projectName, List<Teacher> teacherList) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.teacherList = teacherList;
    }

    public Project() {
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", teacherList=" + teacherList +
                '}';
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }
}
