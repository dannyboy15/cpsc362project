package titanimite.cpsc362.csuf.com.mycsuf;

/**
 * Created by Daniel on 11/8/17.
 */

public class Note {
    private long id;
    private long suId;
    private long classId;
    private String dateCreated;
    private String status;
    private String msg;

    public Note() {
    }

    public Note(long suId, long classId, String dateCreated, String status, String msg) {
        this.suId = suId;
        this.classId = classId;
        this.dateCreated = dateCreated;
        this.status = status;
        this.msg = msg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSutId() {
        return suId;
    }

    public void setSuId(long id) {
        this.suId = id;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                "suId=" + suId +
                ", classId=" + classId +
                ", dateCreated='" + dateCreated + '\'' +
                ", status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
