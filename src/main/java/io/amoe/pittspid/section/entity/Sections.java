package io.amoe.pittspid.section.entity;

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
 * @since 2018-10-12
 */
public class Sections implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String session;

    @TableField("classNum")
    private Integer classNum;

    private String career;

    private Integer units;

    private String grading;

    private String description;

    @TableField("preRequire")
    private String preRequire;

    @TableField("lecRequre")
    private Integer lecRequre;

    @TableField("labRequire")
    private Integer labRequire;

    @TableField("recRequire")
    private Integer recRequire;

    @TableField("isRec")
    private Integer isRec;

    private String instructor;

    private String meets;

    @TableField("startDate")
    private LocalDate startDate;

    @TableField("endDate")
    private LocalDate endDate;

    private String room;

    private String campus;

    private String location;

    @TableField("textBook")
    private String textBook;

    private String status;

    @TableField("classCap")
    private Integer classCap;

    @TableField("waitListCap")
    private Integer waitListCap;

    private String subject;

    @TableField("subjectNum")
    private Integer subjectNum;

    @TableField("sectionNum")
    private Integer sectionNum;

    @TableField("courseNum")
    private Integer courseNum;

    private Integer term;

    private String title;

    private String classAttri;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }
    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }
    public String getGrading() {
        return grading;
    }

    public void setGrading(String grading) {
        this.grading = grading;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getPreRequire() {
        return preRequire;
    }

    public void setPreRequire(String preRequire) {
        this.preRequire = preRequire;
    }
    public Integer getLecRequre() {
        return lecRequre;
    }

    public void setLecRequre(Integer lecRequre) {
        this.lecRequre = lecRequre;
    }
    public Integer getLabRequire() {
        return labRequire;
    }

    public void setLabRequire(Integer labRequire) {
        this.labRequire = labRequire;
    }
    public Integer getRecRequire() {
        return recRequire;
    }

    public void setRecRequire(Integer recRequire) {
        this.recRequire = recRequire;
    }
    public Integer getIsRec() {
        return isRec;
    }

    public void setIsRec(Integer isRec) {
        this.isRec = isRec;
    }
    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    public String getMeets() {
        return meets;
    }

    public void setMeets(String meets) {
        this.meets = meets;
    }
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getTextBook() {
        return textBook;
    }

    public void setTextBook(String textBook) {
        this.textBook = textBook;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getClassCap() {
        return classCap;
    }

    public void setClassCap(Integer classCap) {
        this.classCap = classCap;
    }
    public Integer getWaitListCap() {
        return waitListCap;
    }

    public void setWaitListCap(Integer waitListCap) {
        this.waitListCap = waitListCap;
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
    public Integer getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(Integer sectionNum) {
        this.sectionNum = sectionNum;
    }
    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }
    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getClassAttri() {
        return classAttri;
    }

    public void setClassAttri(String classAttri) {
        this.classAttri = classAttri;
    }

    @Override
    public String toString() {
        return "Sections{" +
        "id=" + id +
        ", session=" + session +
        ", classNum=" + classNum +
        ", career=" + career +
        ", units=" + units +
        ", grading=" + grading +
        ", description=" + description +
        ", preRequire=" + preRequire +
        ", lecRequre=" + lecRequre +
        ", labRequire=" + labRequire +
        ", recRequire=" + recRequire +
        ", isRec=" + isRec +
        ", instructor=" + instructor +
        ", meets=" + meets +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", room=" + room +
        ", campus=" + campus +
        ", location=" + location +
        ", textBook=" + textBook +
        ", status=" + status +
        ", classCap=" + classCap +
        ", waitListCap=" + waitListCap +
        ", subject=" + subject +
        ", subjectNum=" + subjectNum +
        ", sectionNum=" + sectionNum +
        ", courseNum=" + courseNum +
        ", term=" + term +
        ", title=" + title +
        ", classAttri=" + classAttri +
        "}";
    }
}
