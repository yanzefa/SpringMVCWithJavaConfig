package com.zhangzhihao.SpringMVCWithJavaConfig.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "logging_event")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log implements Serializable{
    private static final long serialVersionUID =468468487400228L;
    private String timestmp;
    private String formatted_message;
    private String logger_name;
    private String level_string;
    private String thread_name;
    private Integer reference_flag;
    private String caller_filename;
    private String arg0;
    private String arg1;
    private String arg2;
    private String arg3;
    private String caller_class;
    private String caller_method;
    private String caller_line;
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    private Long event_id;
}
