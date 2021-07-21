package io.muic.ssc.zork.Map;

public enum MapType {
    TRAIN_GRAVEYARD(Kimochi.class, "Kimochi"),
    CORNEO_COLOSSEUM(Hell.class, "Hell");

    private Class<? extends Map> mapClass;
    private String mapName;
    private String mapDescription;

    MapType(Class<? extends Map> mapClass, String mapName) {
        this.mapClass = mapClass;
        this.mapName = mapName;
    }

    public Class<? extends Map> getMapClass() {
        return mapClass;
    }

    public String getMapName() {
        return mapName;
    }
}
