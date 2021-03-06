package com.example.videoeditorplatform;

public class VideoSearchModel {
    Integer id;
    String NP;
    String NameVideo;
    String DeadLine;
    String Song;
    Boolean Status;

    public VideoSearchModel(Integer id, String NP, String nameVideo, String deadLine, String song, Boolean status) {
        this.id = id;
        this.NP = NP;
        NameVideo = nameVideo;
        DeadLine = deadLine;
        Song = song;
        Status = status;
    }

    public String getNP() {
        return NP;
    }

    public void setNP(String NP) {
        this.NP = NP;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameVideo() {
        return NameVideo;
    }

    public void setNameVideo(String nameVideo) {
        NameVideo = nameVideo;
    }

    public String getDeadLine() {
        return DeadLine;
    }

    public void setDeadLine(String deadLine) {
        DeadLine = deadLine;
    }

    public String getSong() {
        return Song;
    }

    public void setSong(String song) {
        Song = song;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }
}
