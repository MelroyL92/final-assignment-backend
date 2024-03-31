package nl.novi.finalAssignmentBackend.dtos.game;


import nl.novi.finalAssignmentBackend.entities.Product;

public class GameInputDto extends Product {

        private Long id;
        private String platform;
        private String publisher;
        private String playDuration;


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

        public String getPlayDuration() {
            return playDuration;
        }

        public void setPlayDuration(String playDuration) {
            this.playDuration = playDuration;
        }

}
