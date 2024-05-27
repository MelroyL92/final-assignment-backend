package nl.novi.finalAssignmentBackend.entities;

import jakarta.persistence.*;

@Entity
public class UploadOrder {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "content_type")
    private String contentType;
    @Lob
    byte[] contents;

    @OneToOne
    private User user;

    public UploadOrder(String title, String url, String contentType, byte[] contents){
        this.contentType = contentType;
        this.title = title;
        this.url = url;
        this.contents = contents;
    }

    public UploadOrder() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }
}
