package io.muic.ssc.zork.Map;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Locale;

public class MapFactory {

    private static final java.util.Map<String, Map> AVAILABLE_MAP = new HashMap<>() {{
        MapType[] mapTypes = MapType.values();
        for (int i = 0; i < mapTypes.length; i++) {
            try {
                Map map = mapTypes[i].getMapClass().getDeclaredConstructor().newInstance();
                put(mapTypes[i].getMapName(), map);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }};

    public static Map get(String mapName) {
        for (String map : AVAILABLE_MAP.keySet()) {
            if (map.toLowerCase(Locale.ROOT).equals(mapName.toLowerCase(Locale.ROOT)))
                return AVAILABLE_MAP.get(map);
        }
        return null;
    }

    public static java.util.Map<String, Map> getAvailableMap() {
        return AVAILABLE_MAP;
    }

    public Map createMap(String mapName) {
        for (String map : AVAILABLE_MAP.keySet()) {
            if (map.toLowerCase(Locale.ROOT).equals(mapName.toLowerCase(Locale.ROOT))) {
                try {
                    return AVAILABLE_MAP.get(map).getClass().getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
