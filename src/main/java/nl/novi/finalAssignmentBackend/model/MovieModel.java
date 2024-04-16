package nl.novi.finalAssignmentBackend.model;


import nl.novi.finalAssignmentBackend.entities.ShoppingList;

public class MovieModel extends ProductModel {


        private Long id;

        private String genre;
        private String type;
        private String director;

        private String playtime;

        private ShoppingList shoppingList;

        public ShoppingList getShoppingList() {
        return shoppingList;
        }

        public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
         }

    public String getPlaytime() {
            return playtime;
        }

        public void setPlaytime(String playtime) {
            this.playtime = playtime;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

}


