import java.util.ArrayList;
import java.util.List;

class SubsribePizza{
    String pizzaType;
    Customers customers;

    SubsribePizza(String pizzaType, Customers customers){
        this.pizzaType = pizzaType;
        this.customers = customers;
    }
}
class PizzaPublisher{
    private List<SubsribePizza> customers = new ArrayList<>();

    void subscribe(SubsribePizza subsribePizza){
        customers.add(subsribePizza);
    }

    void unSubsribe(SubsribePizza subsribePizza){
        customers.remove(subsribePizza);
    }

    void notifi(String pizzaType){
        for(int i=0;i<customers.size();i++){
            SubsribePizza subsribePizza = customers.get(i);
            if(pizzaType == subsribePizza.pizzaType){
                subsribePizza.customers.update(subsribePizza.pizzaType);
            }
        }
    }

}

class PizzaMaker{
     PizzaPublisher pizzaPublisher;

    PizzaMaker(){
        pizzaPublisher = new PizzaPublisher();
    }

    void makePizza(String pizzaType){
        pizzaPublisher.notifi(pizzaType);
    }
}

interface Customers{
    void update(String eventType);
}

class Customer implements Customers{
    @Override
    public void update(String eventType) {
        System.out.println("New Pizza available: "+eventType);
    }
}
public class Main {
    public static void main(String[] args) {
        PizzaMaker pizzaMaker = new PizzaMaker();
        Customers customer1 = new Customer();
        pizzaMaker.pizzaPublisher.subscribe(new SubsribePizza("pizza maina",customer1));
        Customers customer2 = new Customer();
        pizzaMaker.pizzaPublisher.subscribe(new SubsribePizza("desert",customer2));
        pizzaMaker.makePizza("desert");
        pizzaMaker.makePizza("pizza maina");
    }
}