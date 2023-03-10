package BusinessLogicLayer;

public abstract class MenuItem implements java.io.Serializable {
    protected String title;
    protected float rating;
    protected int calories;
    protected int protein;
    protected int fat;
    protected int sodium;
    protected int price;
    protected int nrTimesOrdered=0;
    public abstract int computePrice();
    public abstract float computeRating();
    public abstract int computeCalories();
    public abstract int computeProtein();
    public abstract int computeFat();
    public abstract int computeSodium();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public void increment()
    {
        nrTimesOrdered++;
    }
    public int getNrTimesOrdered() {
        return nrTimesOrdered;
    }

    public void setNrTimesOrdered(int nrTimesOrdered) {
        this.nrTimesOrdered = nrTimesOrdered;
    }

    public String convertToString()
    {
        return this.title+" "+this.rating+" "+this.calories+" "+this.protein+" "+this.fat+" "+this.sodium+" "+this.price;
    }



}
