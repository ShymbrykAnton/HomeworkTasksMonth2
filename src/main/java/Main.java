import ilist.IList;
import ilist.impl.LinkedList1;

public class Main {
    public static void main(String[] args) {
//        System.out.println(devices[5].getSystemInfo());
//        Filtration filtration = new Filtration();
//        List<Device> devices;
//        devices = filtration.filtrateByOccupiedMemorySpace(25,"More");
//        System.out.println(devices.size());
        IList collection = new LinkedList1();
        collection.add(1);
        collection.add(0, 5);
        collection.print();
    }
}
