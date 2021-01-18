import oopHomework.Device.Device;
import oopHomework.filtration.Filtration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static oopHomework.filtration.Filtration.devices;

public class Main {
    public static void main(String[] args) {
        System.out.println(devices[5].getSystemInfo());
        Filtration filtration = new Filtration();
        List<Device> devices;
        devices = filtration.filtrateByOccupiedMemorySpace(25,"More");
        System.out.println(devices.size());
    }
}
