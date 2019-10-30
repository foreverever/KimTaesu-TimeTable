package timetable.domain.lecture;

import timetable.domain.lecture.memo.Memo;
import timetable.support.utils.LectureUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String code;
    @Column
    private String name;
    @Column
    private String professor;
    @Column
    private String location;
    @Column
    private String dates;
    @Column
    private LocalDateTime startTime;
    @Column
    private LocalDateTime endTime;

    @OneToMany
    private List<Memo> memos;

    private boolean registered = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Memo> getMemos() {
        return memos;
    }

    public void setMemos(List<Memo> memos) {
        this.memos = memos;
    }

    public String getFormattedStartTime() {
        return startTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getFormattedEndTime() {
        return endTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getCodeNum() {
        return LectureUtils.SubCodeNum(code);
    }

    public String getHour() {
        if (dates.length() >= 2) return LectureUtils.TWO;
        return "";
    }

    public String getHourOfStartTime(){
        return LectureUtils.getHourOfStartTime(getFormattedStartTime());
    }

    public void register() {
        this.registered = true;
    }
}
