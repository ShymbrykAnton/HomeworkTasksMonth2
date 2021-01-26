import oophomework.device.Device;
import oophomework.filtration.Filtration;

import java.util.List;

import static oophomework.filtration.Filtration.devices;

public class Main {
    public static void main(String[] args) {
        System.out.println(devices[5].getSystemInfo());
        Filtration filtration = new Filtration();
        List<Device> devices;
        devices = filtration.filtrateByOccupiedMemorySpace(25,"More");
        System.out.println(devices.size());
    }
}
