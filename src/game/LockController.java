package game;

import game.enums.PawnStatusEnum;

import static java.lang.Integer.parseInt;

//#TODO do poprawek
public class LockController {
    public static void updateLock(Player player) {
        for (Pawn pawn : player.getPawns()) {
            if (checkIfLocked(player, pawn)) {
                pawn.setStatus(PawnStatusEnum.LOCKED_IN_HOME);
            }
        }
    }

    public static boolean checkIfLocked(Player player, Pawn pawn) {
        int endPosition = parseInt(pawn.getPosition().substring(pawn.getPosition().length() - 1)); //pola końcowego
        if (endPosition != 4) {
            for (Pawn otherPawn : player.getPawns()) {
                if (otherPawn.getPosition().equals("#end" + player.getPawnsColor() + (endPosition + 1)))//sprawdzamy pionek pole "do przodu"
                {
                    if (otherPawn.getStatus() == PawnStatusEnum.LOCKED_IN_HOME) {
                        return true; //pionek "przed nami" jest już zablokowany, więc blokujemy swój
                    }
                }
            }
            return false;
        }
        return true; //w przypadku gdy wchodzimy na 4 pole końcowe (ostatnie)
    }
}
