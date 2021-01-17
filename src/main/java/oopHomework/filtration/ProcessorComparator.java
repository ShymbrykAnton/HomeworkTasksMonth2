//package oopHomework.filtration;
//
//import oopHomework.processor.Processor;
//
//import java.util.Comparator;
//
//public class ProcessorComparator implements Comparator<Processor> {
//    @Override
//    public int compare(Processor o1, Processor o2) {
//        if (sortByBitCapacity(o1.getBitCapacity(), o2.getBitCapacity()) != 0) {
//            return sortByBitCapacity(o1.getBitCapacity(), o2.getBitCapacity());
//        }
//        if (sortByFrequency(o1.getFrequency(), o2.getFrequency()) != 0) {
//            return sortByFrequency(o1.getFrequency(), o2.getFrequency());
//        }
//        return sortByCache(o1.getCache(),o2.getCache());
//    }
//
//    private int sortByFrequency(double frequency1, double frequency2) {
//        return (int) (frequency1 - frequency2);
//    }
//
//    private int sortByCache(double cache1, double cache2) {
//        return (int) (cache1 - cache2);
//    }
//
//    private int sortByBitCapacity(int bitCapacity1, int bitCapacity2) {
//        return bitCapacity1 - bitCapacity2;
//    }
//}
