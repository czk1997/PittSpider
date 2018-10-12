package io.amoe.pittspid.course.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2018-10-11
 */
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private String subject;

    @TableField("subjectNum")
    private Integer subjectNum;

    @TableField("courseNum")
    private Integer courseNum;

    @TableField("psmUri")
    private String psmUri;

    private Integer units;

    @TableField("lecRequired")
    private Integer lecRequired;

    @TableField("labRequired")
    private Integer labRequired;

    @TableField("recRequired")
    private Integer recRequired;

    @TableField("unknownDate")
    private LocalDate unknownDate;

    private String program;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public Integer getSubjectNum() {
        return subjectNum;
    }

    public void setSubjectNum(Integer subjectNum) {
        this.subjectNum = subjectNum;
    }
    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }
    public String getPsmUri() {
        return psmUri;
    }

    public void setPsmUri(String psmUri) {
        this.psmUri = psmUri;
    }
    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }
    public Integer getLecRequired() {
        return lecRequired;
    }

    public void setLecRequired(Integer lecRequired) {
        this.lecRequired = lecRequired;
    }
    public Integer getLabRequired() {
        return labRequired;
    }

    public void setLabRequired(Integer labRequired) {
        this.labRequired = labRequired;
    }
    public Integer getRecRequired() {
        return recRequired;
    }

    public void setRecRequired(Integer recRequired) {
        this.recRequired = recRequired;
    }
    public LocalDate getUnknownDate() {
        return unknownDate;
    }

    public void setUnknownDate(LocalDate unknownDate) {
        this.unknownDate = unknownDate;
    }
    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "Courses{" +
        "id=" + id +
        ", title=" + title +
        ", subject=" + subject +
        ", subjectNum=" + subjectNum +
        ", courseNum=" + courseNum +
        ", psmUri=" + psmUri +
        ", units=" + units +
        ", lecRequired=" + lecRequired +
        ", labRequired=" + labRequired +
        ", recRequired=" + recRequired +
        ", unknownDate=" + unknownDate +
        ", program=" + program +
        "}";
    }
}
