package BusinessLogicLayer;

public class BaseProduct extends MenuItem {
    int nrTimesOrdered;
    public BaseProduct(String title, float rating,  int calories,int protein, int fat, int sodium, int price)
    {
        this.title=title;
        this.price=price;
        this.rating=rating;
        this.calories=calories;
        this.protein=protein;
        this.fat=fat;
        this.sodium=sodium;
        nrTimesOrdered=0;
    }
    @Override
    public int computePrice()
    {
        return getPrice();
    }

    @Override
    public float computeRating() {
        return getRating();
    }
    public void incrementOrdered()
    {
        nrTimesOrdered++;
    }
    @Override
    public int computeCalories()
    {
        return getCalories();
    }
    @Override
    public int computeProtein()
    {
        return getProtein();
    }
    @Override
    public int computeFat()
    {
        return getFat();
    }
    @Override
    public int computeSodium()
    {
        return getSodium();
    }

}
