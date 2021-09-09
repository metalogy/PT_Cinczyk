package game;

public class GamePlay {

    public static Integer rollDice()
    {
        int roll=(int) ((Math.random() * (6)) + 1);
        System.out.println("Roll: "+roll);
        return roll;
    }
}
