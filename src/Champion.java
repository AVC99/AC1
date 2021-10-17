public class Champion {
    private static int count=0;
    private int id;
    private String name;
    private String title;
    private String role;
    private double difficulty;
    private int skins;
    private boolean reworked;
    private String url;

    public Champion(String name, String title, String role, double difficulty, int skins, boolean reworked, String url){
        this.id=count;
        count++;
        this.name=name;
        this.title=title;
        this.role=role;
        this.difficulty=difficulty;
        this.skins=skins;
        this.reworked=reworked;
        this.url=url;
    }
   static public Champion fromLine (String line){

        String[] part = line.split(",");

        return new Champion(part[0], part[1], part[2],  Double.parseDouble(part[3]),Integer.parseInt(part[4]), Boolean.parseBoolean(part[5]), part[6]);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getRole() {
        return role;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public int getSkins() {
        return skins;
    }

    public boolean isReworked() {
        return reworked;
    }

    public String getUrl() {
        return url;
    }

    public void setReworked(boolean reworked) {
        this.reworked = reworked;
    }
}