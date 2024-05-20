package nl.novi.finalAssignmentBackend.dtos.game;


import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import nl.novi.finalAssignmentBackend.model.ProductModel;

public class GameInputDTO extends ProductModel {

        private Long id;
        @NotBlank(message = "Platform must not be empty")
        @Size(min =1, max = 100, message = "please fill in a description between 3 and 100 characters")
        private String platform;

        @NotBlank(message = "Publisher must not be empty")
        @Size(min =3, max = 100, message = "please fill in a description between 3 and 100 characters")
        private String publisher;
        @Column(name = "play_duration_in_min")
        @NotNull(message = "the playtime should be between 1 and 10000 minutes")
        @Min(1)
        @Max(10000)
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
