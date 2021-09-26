package game;

import java.text.MessageFormat;
import java.util.LinkedHashSet;

public final class WinningConditionController {

    private WinningConditionController() {}

    /**
     * @param   color   color of current player pawns
     * @return          checklist of winning condition
     */
    private static LinkedHashSet<String> translateColor(String color){
        LinkedHashSet<String> response = new LinkedHashSet<>();
        Object[] params;
        for (int i = 1; i < 5; i++) {
            params = new Object[]{color, i};
            response.add(MessageFormat.format("#end{0}_{1}", params));
        }
        return response;
    }

    /**
     * @param   player  currentPlayer
     * @return  true    if player won
     *          false   if player is still in game
     */
    static boolean check(Player player){
        LinkedHashSet<String> checkList = translateColor(player.getPawnsColor());
        for (Pawn pawn: player.getPawns()) {
            checkList.remove(pawn.getPosition());
        }
        return checkList.isEmpty();
    }
}