package timetable.domain.lecture;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import timetable.domain.lecture.memo.Memo;
import timetable.security.exception.CannotRegisterLecture;
import timetable.support.utils.LectureUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static timetable.support.utils.LectureUtils.*;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Memo> memos;

    private boolean registered = false;

    public Lecture(String dates) {
        this.dates = dates;
    }

    public Lecture() {
    }

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
        if (mulStartTimeEndTime(getFormattedStartTime(), getFormattedEndTime()) >= 2) return TWO;
        return "";
    }

    public String getHourOfStartTime() {
        return LectureUtils.getHourOfTime(getFormattedStartTime());
    }

    private int getDatesSize() {
        return this.dates.length();
    }

    public void register() {
        this.registered = true;
    }

    public void addMemo(Memo memo) {
        memo.setLecture(this);
        this.memos.add(memo);
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", professor='" + professor + '\'' +
                ", location='" + location + '\'' +
                ", dates='" + dates + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", registered=" + registered +
                '}';
    }

    public void deleteMemo(long id) {
        for (Memo memo : memos) {
            if (memo.getId() == id) {
                memos.remove(memo);
                return;
            }
        }
    }

    public void delete() {
        registered = false;
    }

    public boolean isPossibleRegister(List<Lecture> registeredLectures) {
        for (Lecture registeredLecture : registeredLectures) {
            if (!isDuplicateDate(registeredLecture)) continue;
            if ((this.startTime.isEqual(registeredLecture.startTime) && this.endTime.isEqual(registeredLecture.endTime))
                    || (this.startTime.isAfter(registeredLecture.startTime) && this.startTime.isBefore(registeredLecture.endTime))
                    || (this.endTime.isAfter(registeredLecture.startTime) && this.endTime.isBefore(registeredLecture.endTime)))
                throw new CannotRegisterLecture("과목이 겹칩니다.");
        }
        return true;
    }

    boolean isDuplicateDate(Lecture registeredLecture) {
        Set<Character> check = new HashSet<>();
        for (int i = 0; i < this.getDatesSize(); i++) check.add(this.dates.charAt(i));
        for (int i = 0; i < registeredLecture.getDatesSize(); i++) check.add(registeredLecture.dates.charAt(i));

        return check.size() != this.getDatesSize() + registeredLecture.getDatesSize();
    }
}
