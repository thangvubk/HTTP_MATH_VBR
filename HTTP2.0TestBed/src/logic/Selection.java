package logic;

public class Selection {
	public int bitrate;
	public int numOfSegment;
	public double requestCost, qualityCost, bufferCost, totalCost;
	public Selection(int bitrate, int numOfSegment){
		this.bitrate = bitrate;
		this.numOfSegment = numOfSegment;
	}
}
