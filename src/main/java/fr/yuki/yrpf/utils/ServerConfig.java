package fr.yuki.yrpf.utils;

public class ServerConfig {
    private double deathRespawnX;
    private double deathRespawnY;
    private double deathRespawnZ;
    private double deathRespawnH;
    private int deathRespawnDelay;
    private double spawnPointX;
    private double spawnPointY;
    private double spawnPointZ;
    private double spawnPointH;
    private int xpRate;
    private String sqlHost;
    private String sqlUsername;
    private String sqlPassword;
    private String sqlDb;
    private String langPath;
    private String serverLanguage;
    private String tebexSecretKey;
    private int timePerHour;
    private int startHour;
    private String welcomeMessage;
    private String discordBotToken;

    public double getDeathRespawnX() {
        return deathRespawnX;
    }

    public void setDeathRespawnX(double deathRespawnX) {
        this.deathRespawnX = deathRespawnX;
    }

    public double getDeathRespawnY() {
        return deathRespawnY;
    }

    public void setDeathRespawnY(double deathRespawnY) {
        this.deathRespawnY = deathRespawnY;
    }

    public double getDeathRespawnZ() {
        return deathRespawnZ;
    }

    public void setDeathRespawnZ(double deathRespawnZ) {
        this.deathRespawnZ = deathRespawnZ;
    }

    public double getDeathRespawnH() {
        return deathRespawnH;
    }

    public void setDeathRespawnH(double deathRespawnH) {
        this.deathRespawnH = deathRespawnH;
    }

    public int getDeathRespawnDelay() {
        return deathRespawnDelay;
    }

    public void setDeathRespawnDelay(int deathRespawnDelay) {
        this.deathRespawnDelay = deathRespawnDelay;
    }

    public int getXpRate() {
        return xpRate;
    }

    public void setXpRate(int xpRate) {
        this.xpRate = xpRate;
    }

    public String getSqlHost() {
        return sqlHost;
    }

    public void setSqlHost(String sqlHost) {
        this.sqlHost = sqlHost;
    }

    public String getSqlUsername() {
        return sqlUsername;
    }

    public void setSqlUsername(String sqlUsername) {
        this.sqlUsername = sqlUsername;
    }

    public String getSqlDb() {
        return sqlDb;
    }

    public void setSqlDb(String sqlDb) {
        this.sqlDb = sqlDb;
    }

    public String getSqlPassword() {
        return sqlPassword;
    }

    public void setSqlPassword(String sqlPassword) {
        this.sqlPassword = sqlPassword;
    }

    public double getSpawnPointX() {
        return spawnPointX;
    }

    public void setSpawnPointX(double spawnPointX) {
        this.spawnPointX = spawnPointX;
    }

    public double getSpawnPointY() {
        return spawnPointY;
    }

    public void setSpawnPointY(double spawnPointY) {
        this.spawnPointY = spawnPointY;
    }

    public double getSpawnPointZ() {
        return spawnPointZ;
    }

    public void setSpawnPointZ(double spawnPointZ) {
        this.spawnPointZ = spawnPointZ;
    }

    public double getSpawnPointH() {
        return spawnPointH;
    }

    public void setSpawnPointH(double spawnPointH) {
        this.spawnPointH = spawnPointH;
    }

    public String getLangPath() {
        return langPath;
    }

    public void setLangPath(String langPath) {
        this.langPath = langPath;
    }

    public String getServerLanguage() {
        return serverLanguage;
    }

    public void setServerLanguage(String serverLanguage) {
        this.serverLanguage = serverLanguage;
    }

    public String getTebexSecretKey() {
        return tebexSecretKey;
    }

    public void setTebexSecretKey(String tebexSecretKey) {
        this.tebexSecretKey = tebexSecretKey;
    }

    public int getTimePerHour() {
        return timePerHour;
    }

    public void setTimePerHour(int timePerHour) {
        this.timePerHour = timePerHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getDiscordBotToken() {
        return discordBotToken;
    }

    public void setDiscordBotToken(String discordBotToken) {
        this.discordBotToken = discordBotToken;
    }
}
