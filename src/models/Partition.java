package models;

public class Partition implements Comparable<Partition>{
    private String name;
    private long PartitionSize;
    private ProcessSimulation processSimulations;
    private long lowerLimit;
    private long highLimit;

    public Partition(String name, long partitionSize, ProcessSimulation processSimulations, long lowerLimit, long highLimit) {
        this.name = name;
        PartitionSize = partitionSize;
        this.processSimulations = processSimulations;
        this.lowerLimit = lowerLimit;
        this.highLimit = highLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPartitionSize() {
        return PartitionSize;
    }

    public void setPartitionSize(long partitionSize) {
        PartitionSize = partitionSize;
    }

    public ProcessSimulation getProcessSimulations() {
        return processSimulations;
    }

    public void setProcessSimulations(ProcessSimulation processSimulations) {
        this.processSimulations = processSimulations;
    }

    public long getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(long lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public long getHighLimit() {
        return highLimit;
    }

    public void setHighLimit(long highLimit) {
        this.highLimit = highLimit;
    }

    @Override
    public int compareTo(Partition o) {
        if (this.getProcessSimulations().getTime() < o.getProcessSimulations().getTime()) {
            return -1;
        } else if (this.getProcessSimulations().getTime() > o.getProcessSimulations().getTime()) {
            return 1;
        } else {
            return 0;
        }
    }
}
