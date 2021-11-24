package hotciv.framework;

public class OperationNames {
    // Game operations
    public static final String GAME_GET_WINNER_OPERATION = "game-get-winner";
    public static final String GAME_GET_AGE_OPERATION = "game-get-age";
    public static final String GAME_GET_PLAYER_IN_TURN_OPERATION = "game-get-player-in-turn";
    public static final String GAME_MOVE_UNIT_OPERATION = "game-move-unit";
    public static final String GAME_END_TURN_OPERATION = "game-end-turn";
    public static final String GAME_CHANGE_WORKFORCE_FOCUS_IN_CITY_AT_OPERATION = "game-change-workforce-focus-in-city-at";
    public static final String GAME_CHANGE_PRODUCTION_IN_CITY_AT_OPERATION = "game-change-production-in-city-at";
    public static final String GAME_PERFORM_UNIT_ACTION_AT_OPERATION = "game-perform-unit-action-at";
    public static final String GAME_GET_TILE_AT_OPERATION = "game-get-tile-at";

    // City operations
    public static final String CITY_GET_OWNER_OPERATION = "city-get-owner";
    public static final String CITY_GET_SIZE_OPERATION = "city-get-size";
    public static final String CITY_GET_TREASURY_OPERATION = "city-get-treasury";
    public static final String CITY_GET_PRODUCTION_OPERATION = "city-get-production";
    public static final String CITY_GET_WORKFORCE_FOCUS_OPERATION = "city-get-workforce-focus";

    // Unit operations
    public static final String UNIT_GET_TYPE_STRING_OPERATION = "unit-get-type-string";
    public static final String UNIT_GET_OWNER_OPERATION = "unit-get-owner";
    public static final String UNIT_GET_MOVE_COUNT_OPERATION = "unit-get-move-count";
    public static final String UNIT_GET_DEFENSIVE_STRENGTH_OPERATION = "unit-get-defensive-strength";
    public static final String UNIT_GET_ATTACKING_STRENGTH_OPERATION = "unit-get-attacking-strength";

    // Tile operations
    public static final String TILE_GET_TYPE_STRING_OPERATION = "tile-get-type-string";
}
