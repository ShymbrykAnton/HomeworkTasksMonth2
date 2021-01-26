package oophomework.filtration;

import oophomework.device.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static oophomework.utils.Constants.Components.*;
import static oophomework.utils.Constants.Text.*;


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

    public List<Device> filtrateByArchitecture(String architecture) {
        List<Device> list = new ArrayList<>();
        for (Device device : devices) {
            if (device.processor.getArchitecture().equals(architecture)) {
                list.add(device);
            }
        }
        return list;
    }

    public List<Device> filtrateByMemoryVolume(int memoryVolume, String moreLess) {
        List<Device> list = new ArrayList<>();
        switch (moreLess.toLowerCase(Locale.ROOT)) {
            case more:
                for (Device device : devices) {
                    if (device.memory.memoryCell.length > memoryVolume) {
                        list.add(device);
                    }
                }
                return list;
            case less:
                for (Device device : devices) {
                    if (device.memory.memoryCell.length < memoryVolume) {
                        list.add(device);
                    }
                }
                return list;
            default:
                return list;
        }
    }

    public List<Device> filtrateByOccupiedMemorySpace(double occupiedMemorySpace, String moreLess) {
        List<Device> list = new ArrayList<>();
        switch (moreLess.toLowerCase(Locale.ROOT)) {
            case more:
                for (Device device : devices) {
                    if (device.memory.getMemoryInfo().getOccupiedMemorySpace() > occupiedMemorySpace) {
                        list.add(device);
                    }
                }
                return list;
            case less:
                for (Device device : devices) {
                    if (device.memory.getMemoryInfo().getOccupiedMemorySpace() < occupiedMemorySpace) {
                        list.add(device);
                    }
                }
                return list;
            default:
                return list;
        }
    }
}

