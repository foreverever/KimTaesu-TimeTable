package timetable.domain.lecture.memo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import timetable.domain.lecture.Lecture;

import javax.persistence.*;

@Entity
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_memo_lecture"))
    @JsonBackReference
    private Lecture lecture;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", lecture=" + lecture +
                '}';
    }

    public boolean isEmptyValue() {
        return title.equals("") || contents.equals("");
    }

    public boolean isEqualId(long id) {
        return this.id == id;
    }
}
