package nl.novi.finalAssignmentBackend.dtos.game;

import nl.novi.finalAssignmentBackend.dtos.product.productResponseDTO;

public class GameResponseDTO extends productResponseDTO {
    private Long id;
    private String platform;
    private String publisher;
    private Integer playDurationInMin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPlayDurationInMin() {
        return playDurationInMin;
    }

    public void setPlayDurationInMin(Integer playDurationInMin) {
        this.playDurationInMin = playDurationInMin;
    }
}
