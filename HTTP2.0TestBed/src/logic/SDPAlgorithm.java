package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Client.Buffer;
import Client.Downloader;
import Client.Manifest;

public class SDPAlgorithm extends AdaptationLogic {
    private int constantNumberOfSegments = 1;
    
    
    private int num_of_buffer_lvl = 5;
	private int num_of_q = 9;
	private int num_of_bw = 10;
	
	private float[][] bitrateVBR = new float[num_of_q][(int) (Manifest.VIDEO_DURATION / Manifest.REAL_SEGMENT_DURATION)];
	private float[] bwQuantizedLevels = new float[num_of_bw];
	private int segmentCount = -1;
	private static int selectedVersion = 8;
	
	private int num_of_state = num_of_buffer_lvl * num_of_bw * num_of_q; 
	private int[] policy_1d = new int[num_of_state];
	private int[][][] policy_3d = new int[num_of_buffer_lvl][num_of_bw][num_of_q];
	private int buffer_lvl = 1;
	private int bw = 1;
	private int q = 1;
	
    public SDPAlgorithm(Buffer buffer, Manifest manifest) throws NumberFormatException, IOException {
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
    	segmentCount++;
    	buffer_lvl = (int)(Math.round(buffer.getBufferSize() / manifest.REAL_SEGMENT_DURATION));
		if (buffer_lvl > num_of_buffer_lvl-1) buffer_lvl = num_of_buffer_lvl-1;
		bw = getBwState(overallThroughput) - 1;
		q = selectedVersion - 1;
		
		
		System.out.println("buffer_lvl: " + buffer_lvl);
		System.out.println("bw: " + bw);
		System.out.println("q: " + q);
		System.out.println("policy: " + policy_3d[buffer_lvl][bw][q]);
		selectedVersion = policy_3d[buffer_lvl][bw][q] - 1;
		
    	
        return new AdaptationDecision((int) bitrateVBR[selectedVersion][segmentCount], constantNumberOfSegments);
    }

    @Override
    protected AdaptationDecision decideFirstRequest() {
        return new AdaptationDecision(3000, constantNumberOfSegments);
    }
    private int getBwState(double segThrp)
	{
		int bwState = 1;
		if (segThrp < ((double)bwQuantizedLevels[0] + (double)bwQuantizedLevels[1]) / 2 )
		{
			bwState = 1;
		}
		else if (segThrp >= ((double)bwQuantizedLevels[num_of_bw - 1] + (double)bwQuantizedLevels[num_of_bw - 2]) / 2)
		{	
			bwState = num_of_bw;
		}
		else
		{
			for (int i = 1; i < num_of_bw - 1; i++)
			{
				if ((segThrp >= ((double)bwQuantizedLevels[i] + (double)bwQuantizedLevels[i - 1]) / 2) && (segThrp < ((double)bwQuantizedLevels[i] + (double)bwQuantizedLevels[i + 1]) / 2) )
				{
					bwState = i + 1;
					break;
				}
			}
		}
		return bwState;
	}
    public static int getSelectedVersion(){
    	return selectedVersion + 1;
    }
}


	

