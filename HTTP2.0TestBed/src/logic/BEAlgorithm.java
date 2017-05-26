package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

import Client.Buffer;
import Client.Manifest;

public class BEAlgorithm extends AdaptationLogic {
    private int constantNumberOfSegments = 1;
    
    
    private int num_of_buffer_lvl = 6;
	private int num_of_q = 9;
	private int num_of_bw = 10;
	
	private float[][] bitrateVBR = new float[num_of_q][(int) (Manifest.VIDEO_DURATION / Manifest.REAL_SEGMENT_DURATION)];
	private float[] bwQuantizedLevels = new float[num_of_bw];
	private int segmentCount = 0;
	private static int selectedVersion = 4;
	
	private int num_of_state = num_of_buffer_lvl * num_of_bw * num_of_q; 
	private int[] policy_1d = new int[num_of_state];
	private int[][][] policy_3d = new int[num_of_buffer_lvl][num_of_bw][num_of_q];
	private int buffer_lvl = 1;
	private int bw = 1;
	private int q = 1;	
	
    public BEAlgorithm(Buffer buffer, Manifest manifest) throws NumberFormatException, IOException {
        super(buffer, manifest);
        int count = 0;
		// Import the LUT
		// read
		BufferedReader inStream = new BufferedReader(new FileReader("Input\\VBR\\policy_data.txt"));
		{
			String sCurrentLine;
			while ((sCurrentLine = inStream.readLine()) != null) {
				policy_1d[count] = ((int)Double.parseDouble(sCurrentLine));		
				count++;
			}
		}
		inStream.close();
		for(int i = 0; i < num_of_q; i++){
			count = 0;
			inStream = new BufferedReader(new FileReader("Input\\VBR\\Version" + (i+1) + ".txt"));
			String sCurrentLine;
			while ((sCurrentLine = inStream.readLine()) != null) {
				bitrateVBR[i][count] = Float.parseFloat(sCurrentLine);
				count++;
			}
		}
		
		count = 0;
		inStream = new BufferedReader(new FileReader("Input\\VBR\\quantizedLevels.txt"));
		{
			String sCurrentLine;
			while ((sCurrentLine = inStream.readLine()) != null) {
				bwQuantizedLevels[count] = Float.parseFloat(sCurrentLine);		
				count++;
			}
		}
		
		count = 0;
		for (int buff_lvl = 0; buff_lvl < num_of_buffer_lvl; buff_lvl++)
		{
			for (int bw = 0; bw < num_of_bw; bw++)
			{
				for (int q = 0; q < num_of_q; q++)
				{
					policy_3d[buff_lvl][bw][q] = policy_1d[count];
					count++;
				}
			}
		}
    }

    @Override
    protected AdaptationDecision decideNextRequest() {
    	

    	
    	int previousQ = selectedVersion;
    	
    	selectedVersion = 0;
		for (int tarQ = 1; tarQ <= 9; tarQ++)
		{
			if (estBitrate(previousQ, tarQ, bitrateVBR[previousQ][segmentCount]) <= overallThroughput)
			{
				selectedVersion = tarQ;
			}
			else
			{
				break;
			}
		}
		segmentCount++;
		
		
		
    	System.out.println(selectedVersion);
        return new AdaptationDecision((int) bitrateVBR[selectedVersion][segmentCount], constantNumberOfSegments);
    }

    @Override
    protected AdaptationDecision decideFirstRequest() {
        return new AdaptationDecision((int) bitrateVBR[selectedVersion][segmentCount], constantNumberOfSegments);
    }
    private double estBitrate(int prevQ, int targetQ, double prevBitrate)
	{
		double est_br = 0;
		est_br = 1.05 * prevBitrate * Math.pow(2, (Q2QP(prevQ) - Q2QP(targetQ)) / 6);
		
		return est_br;
	}
    
    private int Q2QP(int Q)
	{
		int qp_temp = 48;
		switch (Q)
		{
			case 1:
				qp_temp = 48;
				break;
			case 2:
				qp_temp = 42;
				break;
			case 3:
				qp_temp = 38;
				break;
			case 4:
				qp_temp = 34;
				break;
			case 5:
				qp_temp = 28;
				break;
			case 6:
				qp_temp = 24;
				break;
			case 7:
				qp_temp = 22;
				break;
			case 8:
				qp_temp = 16;
				break;
			case 9:
				qp_temp = 10;
				break;
			default:
				qp_temp = 48;
				break;
		}
		return qp_temp;
	}
    
    @Override
    public void writeExcelInfo(LinkedHashMap<String, Double> info) {
    	// TODO Auto-generated method stub
    	super.writeExcelInfo(info);
    	info.put("Version", (double) selectedVersion);
    }
}


	

