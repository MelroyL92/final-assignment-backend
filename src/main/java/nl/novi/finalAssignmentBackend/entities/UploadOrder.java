package nl.novi.finalAssignmentBackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class UploadOrder {

    @Id
    @GeneratedValue
    private String id;

    private String title;
    private String url;
    private String contentType;
    @Lob
    byte[] contents;

    public UploadOrder(String title, String url, String contentType, byte[] contents){
        this.contentType = contentType;
        this.title = title;
        this.url = url;
        this.contents = contents;
    }

    public UploadOrder() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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
