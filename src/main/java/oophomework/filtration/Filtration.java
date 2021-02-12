package oophomework.filtration;

import oophomework.device.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static oophomework.utils.Constants.Text.*;


public class Filtration {

    public List<Device> filtrateByProcessorParameters(double frequency, double cache, int bitCapacity, Device[] devices) {
        if (devices == null) {
            throw new IllegalArgumentException("Device не может быть null");
        }
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

    public List<Device> filtrateByArchitecture(String architecture, Device[] devices) {
        if (devices == null || architecture == null) {
            throw new IllegalArgumentException("Device не может быть null");
        }
        List<Device> list = new ArrayList<>();
        for (Device device : devices) {
            if (device.processor.getArchitecture().equals(architecture)) {
                list.add(device);
            }
        }
        return list;
    }

    public List<Device> filtrateByMemoryVolume(int memoryVolume, String moreLess, Device[] devices) {
        if (devices == null || moreLess == null) {
            throw new IllegalArgumentException("Device не может быть null");
        }
        List<Device> list = new ArrayList<>();
        switch (moreLess.toLowerCase(Locale.ROOT)) {
            case more:
                for (Device device : devices) {
                    if (device.memory.getMemoryCell().length > memoryVolume) {
                        list.add(device);
                    }
                }
                return list;
            case less:
                for (Device device : devices) {
                    if (device.memory.getMemoryCell().length < memoryVolume) {
                        list.add(device);
                    }
                }
                return list;
        }
        return list;
    }

    public List<Device> filtrateByOccupiedMemorySpace(double occupiedMemorySpace, String moreLess, Device[] devices) {
        if (devices == null || moreLess == null) {
            throw new IllegalArgumentException("Device не может быть null");
        }
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
        }
        return list;
    }
}

