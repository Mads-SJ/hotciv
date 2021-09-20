package hotciv.common;

import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;

public interface WorldLayoutStrategy {
    TileImpl[][] createWorldLayout();
}
