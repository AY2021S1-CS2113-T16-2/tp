import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer {

    public String name;
    public int ID;
    public int arriveTime;
    public int dayOfWeek;
    List<Canteen> openCanteens;

    public Customer(String name, int ID,  int arriveTime, int dayOfWeek) {
        this.name = name;
        this.ID = ID;
        this.arriveTime = arriveTime;
        this.dayOfWeek = dayOfWeek;
    }

//    public void printStalls(){
//        openStalls = this.canteen.checkOpenStall(this.dayOfWeek,this.arriveTime);
//        for(int i=0;i<openStalls.size();i++){
//            System.out.println(openStalls.get(i));
//        }
//    }
//    public void printDishes(String canteenName){
//        openStalls = this.canteen.checkOpenStall(this.dayOfWeek,this.arriveTime);
//        for(int i=0;i<openStalls.size();i++){
//            if(canteenName.compareTo(openStalls.get(i).stall_name)==0){
//                openStalls.get(i).printDishes();
//            }
//        }
//    }
//    public List<Stall> CheckOpenStalls(){
//        openStalls = this.canteen.checkOpenStall(this.dayOfWeek,this.arriveTime);
//        return openStalls;
//    }
    public List<Stall> checkOpenStalls(Canteen canteen){
        List<Stall> openStallList = new ArrayList<>();
        for(Stall stall:canteen.stallList){
            if(stall.isOpen(this.dayOfWeek, this.arriveTime)){
                openStallList.add(stall);
            }
        }
        return openStallList;
    }

    public List<Canteen> checkOpenCanteens(List<Canteen> ListCanteen){
        for(Canteen canteen:ListCanteen){
            if(canteen.isOpen(this.dayOfWeek, this.arriveTime)){
                openCanteens.add(canteen);
            }
        }
        return openCanteens;
    }
    public int checkWaitingTime(Stall stall){
        return stall.queue * stall.servingTimePerPersom;
    }
    public Order order(Canteen canteen,Stall stall,List<Dish> dish, String typeOfOrder) {

        if(typeOfOrder.compareTo("delivery")==0){
            Scanner s = new Scanner(System.in);
            int phoneNum = s.nextInt();
            String address = s.nextLine();
            return new deliveryOrder(canteen,stall,dish,this,true,address,phoneNum);
        }
        if(typeOfOrder.compareTo("dine in")==0){
            return new dineInOrder(canteen,stall,dish,this,true);
        }
        if(typeOfOrder.compareTo("take away")==0){
            return new takeAwayOrder(canteen,stall,dish,this,true);
        }
        return  null;
    }

    @Override
    public String toString(){
        return this.name + "arrives at " +this.arriveTime ;
    }
}


