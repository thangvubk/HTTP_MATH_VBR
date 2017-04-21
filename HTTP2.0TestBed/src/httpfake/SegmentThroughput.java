package httpfake;

public class SegmentThroughput{
	public double time;
	public double throughput;
	public SegmentThroughput(double time, double thrp){
		this.time = time;
		this.throughput = thrp;
	}
	public SegmentThroughput(){}
}