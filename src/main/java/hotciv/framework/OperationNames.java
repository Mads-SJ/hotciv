package hotciv.framework;

public class OperationNames {
    // Seperator
    public static final String SEPERATOR = "-";

    // Type prefixes
    public static final String GAME_PREFIX = "game";
    public static final String CITY_PREFIX = "city";
    public static final String UNIT_PREFIX = "unit";
    public static final String TILE_PREFIX = "tile";

    // Game operations
    public static final String GAME_GET_WINNER_OPERATION = GAME_PREFIX + "-get-winner";
    public static final String GAME_GET_AGE_OPERATION = GAME_PREFIX + "-get-age";
    public static final String GAME_GET_PLAYER_IN_TURN_OPERATION = GAME_PREFIX + "-get-player-in-turn";
    public static final String GAME_MOVE_UNIT_OPERATION = GAME_PREFIX + "-move-unit";
    public static final String GAME_END_TURN_OPERATION = GAME_PREFIX + "-end-turn";
    public static final String GAME_CHANGE_WORKFORCE_FOCUS_IN_CITY_AT_OPERATION = GAME_PREFIX + "-change-workforce-focus-in-city-at";
    public static final String GAME_CHANGE_PRODUCTION_IN_CITY_AT_OPERATION = GAME_PREFIX + "-change-production-in-city-at";
    public static final String GAME_PERFORM_UNIT_ACTION_AT_OPERATION = GAME_PREFIX + "-perform-unit-action-at";
    public static final String GAME_GET_TILE_AT_OPERATION = GAME_PREFIX + "-get-tile-at";
    public static final String GAME_GET_UNIT_AT_OPERATION = GAME_PREFIX + "-get-unit-at";
    public static final String GAME_GET_CITY_AT_OPERATION = GAME_PREFIX + "-get-city-at";

    // City operations
    public static final String CITY_GET_OWNER_OPERATION = CITY_PREFIX + "-get-owner";
    public static final String CITY_GET_SIZE_OPERATION = CITY_PREFIX + "-get-size";
    public static final String CITY_GET_TREASURY_OPERATION = CITY_PREFIX + "-get-treasury";
    public static final String CITY_GET_PRODUCTION_OPERATION = CITY_PREFIX + "-get-production";
    public static final String CITY_GET_WORKFORCE_FOCUS_OPERATION = CITY_PREFIX + "-get-workforce-focus";

    // Unit operations
    public static final String UNIT_GET_TYPE_STRING_OPERATION = UNIT_PREFIX + "-get-type-string";
    public static final String UNIT_GET_OWNER_OPERATION = UNIT_PREFIX + "-get-owner";
    public static final String UNIT_GET_MOVE_COUNT_OPERATION = UNIT_PREFIX + "-get-move-count";
    public static final String UNIT_GET_DEFENSIVE_STRENGTH_OPERATION = UNIT_PREFIX + "-get-defensive-strength";
    public static final String UNIT_GET_ATTACKING_STRENGTH_OPERATION = UNIT_PREFIX + "-get-attacking-strength";

    // Tile operations
    public static final String TILE_GET_TYPE_STRING_OPERATION = TILE_PREFIX + "-get-type-string";
}
