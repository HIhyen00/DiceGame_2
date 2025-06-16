package dice.service;

import dice.entity.Dice;

public interface DiceService {
    Dice diceRoll(Integer accountIdToken); //일반 다이스 굴리는 메소드 //account 고유 id랑 userId가 있어야 할듯
    int skillDIceRoll(Dice dice, int opponentScore);

}
