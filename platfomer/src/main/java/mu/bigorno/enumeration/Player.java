package mu.bigorno.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Player {

    IDLE(0),
    RUNNING(1),
    JUMP(2),
    FALLING(3),
    GROUND(4),
    HIT(5),
    ATTACK_1(6),
    ATTACK_JUMP_1(7),
    ATTACK_JUMP_2(8);

    private final int value;

    public static int sprintingAmount(Player player) {
        return switch (player) {
            case RUNNING -> 6;
            case IDLE -> 5;
            case HIT -> 4;
            case JUMP, ATTACK_1, ATTACK_JUMP_1, ATTACK_JUMP_2 -> 3;
            case GROUND -> 2;
            case FALLING -> 1;
        };
    }
}
