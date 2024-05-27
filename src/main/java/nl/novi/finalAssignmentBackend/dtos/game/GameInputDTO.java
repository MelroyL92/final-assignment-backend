package nl.novi.finalAssignmentBackend.dtos.game;


import jakarta.validation.constraints.*;
import nl.novi.finalAssignmentBackend.dtos.product.ProductInputDTO;


public class GameInputDTO extends ProductInputDTO {

        private Long id;

        @NotBlank(message = "please fill in the platforms suitable between 1 and 100 characters")
        @Size(min =1, max = 100, message = "please fill in the platforms suitable between 1 and 100 characters")
        private String platform;

        @NotBlank(message = "please fill in the name of the publisher between 1 and 100 characters")
        @Size(min =3, max = 100, message = "please fill in the name of the publisher between 1 and 100 characters")
        private String publisher;
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
