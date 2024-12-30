package mu.bigorno.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Paths {

    CHARACTER("C:/Workspace/game/platfomer/src/main/resources/picture/player_sprites.png"),
    LEVEL("C:/Workspace/game/platfomer/src/main/resources/picture/outside_sprites.png"),
    LEVEL_ONE("C:/Workspace/game/platfomer/src/main/resources/picture/level_one_data.png");

    private final String path;
}
