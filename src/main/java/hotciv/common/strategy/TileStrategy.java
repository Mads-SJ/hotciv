package hotciv.common.strategy;

public interface TileStrategy {
    String getResourceType(String typeString);
    int getResources(String typeString);
}
