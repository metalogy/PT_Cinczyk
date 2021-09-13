package game;

public class Player {

    private PawnsColorEnum pawnsColor;
    private String login;

    public Player(PawnsColorEnum pawnsColor, String login) {
        this.pawnsColor = pawnsColor;
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public PawnsColorEnum getPawnsColor() {
        return pawnsColor;
    }


}
