package BusinessLogicLayer;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {
    private ArrayList<MenuItem> compositeProduct;

    public CompositeProduct(String title,ArrayList<MenuItem> compositeProduct) {
        this.compositeProduct = compositeProduct;
        this.title=title;
        this.rating=computeRating();
        this.calories=computeCalories();
        this.protein=computeProtein();
        this.sodium=computeSodium();
        this.price=computePrice();
        this.fat=computeFat();
    }
    @Override
    public int computePrice()
    {
        int price=0;
        for(MenuItem item:compositeProduct)
        {
            price+=item.getPrice();
        }
        return price;
    }

    @Override
    public float computeRating() {
        float rating=0;
        for (MenuItem item:compositeProduct)
        {
            rating+=item.getRating();
        }
        return rating/compositeProduct.size();
    }

    @Override
    public int computeProtein()
    {
        int protein=0;
        for(MenuItem item:compositeProduct)
        {
            protein+=item.getProtein();
        }
        return protein;
    }
    @Override
    public int computeCalories()
    {
        int calories=0;
        for(MenuItem item:compositeProduct)
        {
            calories+=item.getCalories();
        }
        return calories;
    }
    @Override
    public int computeFat()
    {
        int fat=0;
        for(MenuItem item:compositeProduct)
        {
            fat+=item.getFat();
        }
        return fat;
    }
    @Override
    public int computeSodium()
    {
        int sodium=0;
        for(MenuItem item:compositeProduct)
        {
            sodium+=item.getSodium();
        }
        return sodium;
    }
}
