package gg.fullwin.betterclear.stats;

import com.mongodb.client.model.ReplaceOptions;
import gg.fullwin.betterclear.mongo.MongoDatabase;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public final class Stats {
    private final @NotNull UUID uid;
    private int kills;
    private int deaths;
    private int killstreak;
    private int maxstreak;

    public Stats(@NotNull UUID uid, int kills, int deaths, int killstreak, int maxstreak) {
        this.uid = uid;
        this.kills = kills;
        this.deaths = deaths;
        this.killstreak = killstreak;
        this.maxstreak = maxstreak;
    }

    public Stats(@NotNull UUID uid) {
        this(uid, 0, 0, 0, 0);
    }

    public @NotNull UUID uid() {
        return uid;
    }

    public int kills() {
        return kills;
    }

    public void kills(int kills) {
        this.kills = kills;
    }

    public int deaths() {
        return deaths;
    }

    public void deaths(int deaths) {
        this.deaths = deaths;
    }

    public int killstreak() {
        return killstreak;
    }

    public void killstreak(int killstreak) {
        this.killstreak = killstreak;
    }

    public int maxstreak() {
        return maxstreak;
    }

    public void maxstreak(int maxstreak) {
        this.maxstreak = maxstreak;
    }

    public double kdr() {
        return (double) kills / deaths == 0 ? 1 : deaths;
    }

    public void handleKill() {
        kills++;
        killstreak++;
        if (killstreak > maxstreak) maxstreak = killstreak;
    }

    public void handleDeath() {
        deaths++;
        killstreak = 0;
    }

    public void save() {
        Document document = new Document();
        document.put("uid", uid.toString());
        document.put("kills", kills);
        document.put("deaths", deaths);
        document.put("killstreak", killstreak);
        document.put("maxstreak", maxstreak);
        MongoDatabase.STATS.collection()
                .replaceOne(eq("uid", uid.toString()), document, new ReplaceOptions().upsert(true));
    }

    public static @NotNull Stats from(@NotNull Document document) {
        return new Stats(UUID.fromString(document.getString("uid")), document.getInteger("kills"),
                document.getInteger("deaths"), document.getInteger("killstreak"),
                document.getInteger("maxstreak"));
    }
}
