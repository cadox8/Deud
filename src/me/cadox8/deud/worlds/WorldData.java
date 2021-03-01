package me.cadox8.deud.worlds;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
public class WorldData {

    private final String version;
    private final String name;
    private final int width;
    private final int height;

    private final float light;

    private final String[] tiles;

    public List<String> getTiles() {
        return Arrays.asList(this.tiles);
    }
}
