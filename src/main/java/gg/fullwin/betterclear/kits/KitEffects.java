package gg.fullwin.betterclear.kits;

// Kit Effects
public class KitEffects {
    private boolean speed, strength, black, white, cancer;

    public static String serialize(KitEffects effects) {
        StringBuilder builder = new StringBuilder();
        if (effects.speed) builder.append("speed;");
        if (effects.strength) builder.append("strength;");
        if (effects.black) builder.append("black;");
        if (effects.white) builder.append("white;");
        if (effects.cancer) builder.append("cancer;");
        if (!builder.isEmpty()) builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }
}
