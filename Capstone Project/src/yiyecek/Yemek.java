package yiyecek;

public class Yemek {
    private String adi;
    private int hazirlanmaSuresi;
    private int tuketilmeSuresi;

    public Yemek(String adi, int hazirlanmaSuresi,int tuketilmeSuresi) {
        this.adi = adi;
        this.hazirlanmaSuresi = hazirlanmaSuresi;
        this.tuketilmeSuresi = tuketilmeSuresi;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public int getHazirlanmaSuresi() {
        return hazirlanmaSuresi;
    }

    public void setHazirlanmaSuresi(int hazirlanmaSuresi) {
        this.hazirlanmaSuresi = hazirlanmaSuresi;
    }

    public int getTuketilmeSuresi() {
        return tuketilmeSuresi;
    }

    public void setTuketilmeSuresi(int tuketilmeSuresi) {
        this.tuketilmeSuresi = tuketilmeSuresi;
    }

    public String toString(){
        return adi;
    }
}
