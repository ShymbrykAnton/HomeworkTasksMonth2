package oopHomework.filtration;

import oopHomework.Device.Device;
import oopHomework.processor.Processor;

import java.util.ArrayList;
import java.util.List;

import static oopHomework.Utils.Constants.Components.*;


public class Filtration {
    public static Device[] devices = {new Device(appleA14Bionic, memory1), new Device(samsungExynos3110, memory2),
            new Device(qualcommSnapdragon855, memory1), new Device(intelCoreI58300H, memory1),
            new Device(intel8086, memory3), new Device(intelPentiumPro200, memory2),
            new Device(appleA14Bionic, memory2), new Device(intelCoreI58300H, memory3),
            new Device(qualcommSnapdragon855, memory2), new Device(samsungExynos3110, memory3)};

    public List<Device> filtrateByProcessorParameters(double frequency, double cache, int bitCapacity) {
        List<Device> list = new ArrayList<>();
        for (Device device : devices) {
            if (device.processor.getFrequency() == frequency &&
                    device.processor.getCache() == cache &&
                    device.processor.getBitCapacity() == bitCapacity) {
                list.add(device);
            }
        }
        return list;
    }
}

