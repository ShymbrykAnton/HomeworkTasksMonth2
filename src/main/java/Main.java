import ilist.IList;
import ilist.alist2.ArrayList2;
import ilist.impl.LinkedList1;
import log4j.FirstException;
import log4j.RandomGenerator;
import oophomework.device.Device;

import java.util.ArrayList;
import java.util.List;

import static oophomework.utils.Constants.Components.*;
import static oophomework.utils.Constants.Components.memory2;

public class Main {
    public static void main(String[] args) throws FirstException{
//        System.out.println(devices[5].getSystemInfo());
//        Filtration filtration = new Filtration();
//        List<Device> devices;
//        devices = filtration.filtrateByOccupiedMemorySpace(25,"More");
//        System.out.println(devices.size());
        ArrayList2<String> stringGenericList = new ArrayList2<>();
        stringGenericList.add(new Device(appleA14Bionic, memory1).getSystemInfo());
        stringGenericList.add(new Device(samsungExynos3110, memory2).getSystemInfo());
        stringGenericList.add(new Device(qualcommSnapdragon855, memory1).getSystemInfo());
        stringGenericList.add(new Device(intelCoreI58300H, memory1).getSystemInfo());
        stringGenericList.print();
//        RandomGenerator randomGenerator = new RandomGenerator();
//        try {
//            randomGenerator.generate();
//        } catch (FirstException exception) {
//
//        }
    }
}
