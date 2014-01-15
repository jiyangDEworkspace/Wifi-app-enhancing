﻿package your.com.HelloWifi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

public class Building implements Serializable
{
	public Building()
	{
		name = null;
	}
	public Building(String s1)
	{
		name = s1;
	}
	public String name;
	public ArrayList<Data> datas = new ArrayList<Data>();	
	public ArrayList<String> existingBSSIDs = new ArrayList<String>();
	//对其直接赋值的方式public ArrayList<String> existingBSSIDs =  new ArrayList<String>(Arrays.asList("dsf","df"));      
	
	//得到一个教学楼中的所有BSSID,得到后直接赋值，可以不用此方法
	public void getExistingBSSIDs()
	{
		for(Data data : datas )
		{
			for(BSSID BSSID : data.BSSIDs)
			{
				if(!existingBSSIDs.contains(BSSID.name))
					existingBSSIDs.add(BSSID.name);
			}
		}
		//System.out.println(existingBSSIDs);
	}
	
	//初选出几个教室
	//先只考虑选择BSSID匹配数最大的，看情况是否选择前几名，若选前几名则还要考虑为不同名次加分问题
	public ArrayList<Data> firstChooseFromDatas(Data sample)
	{
		ArrayList<Data> chosenDatas = new ArrayList<Data>();
		int[] score =new int[datas.size()];
		int i, maxScore = 0, secondScore = 0;
		
		for(i = 0; i<datas.size(); i ++)
		{
			score[i] = datas.get(i).getMatchingNumber(sample);
			if(maxScore < score[i])
			{
				secondScore = maxScore;
				maxScore = score[i];
			}
			else
			{
				if(maxScore > score[i] && secondScore < score[i])
				{
					secondScore = score[i];
				}
			}
		}
		for(i = 0; i < score.length; i++ )
		{
			if(score[i] == maxScore)
			{
				chosenDatas.add(datas.get(i));				
			}
		}
		final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
		final String FILE_PATH = "/WifiInfo";
		final File path = new File(SD_PATH + FILE_PATH);
		final String fileName = "score_recorder.txt";

		File file = new File(SD_PATH + FILE_PATH, fileName);
	    if (!path.exists())
	    	path.mkdir();
	    try 
		{
	    	FileOutputStream recorder = new FileOutputStream(file,true);
			recorder.write(("firstChoose\nmaxScore: "+maxScore+"\t个数: "+chosenDatas.size()+"\nsecondScore: "+secondScore).getBytes());
	        recorder.close();
		 }
	    catch (IOException e) 
		{
	        Log.w("ExternalStorage", "Error writing " + file, e);
	    }	
		//添加以下代码增加初选个数，待改进
		if(chosenDatas.size() == 1)
		{
			for(i = 0; i < score.length; i++ )
			{
				if(score[i] == secondScore)
				{
					chosenDatas.add(datas.get(i));				
				}				
			}			
		}
		return chosenDatas;
	}
	
	//精选出所在的教室
	public ArrayList<Data> secondChooseFromDatas(Data sample,ArrayList<Data> chosenDatas)
	{
		//Data chosenData = new Data();
		int[] score = new int[chosenDatas.size()];
		int i, maxScore = 0;
		ArrayList<Data> chosenData = new ArrayList<Data>();	//最后可能精选出好几个
		for(i = 0; i<chosenDatas.size(); i++)
		{
			score[i] += chosenDatas.get(i).getSimilarAverageNumber(sample);
			score[i] += chosenDatas.get(i).getSimilarDifficultyNumber(sample);
			score[i] += chosenDatas.get(i).getSecondMatchingNumber(sample);
			score[i] += chosenDatas.get(i).getStrongestBSSIDMatching(sample);
			if(maxScore < score[i])
			{
				maxScore = score[i];
			}
		}
		for(i = 0; i<chosenDatas.size(); i++)
		{
			if(score[i] == maxScore)
			{
				chosenData.add(chosenDatas.get(i));	
			}
			if(chosenDatas.get(i).place.equals("1109") || chosenDatas.get(i).place.equals("1111"))
			{
				Recorder.recorder(("\nPlace: "+ chosenDatas.get(i).place +"\n"));
				Recorder.recorder(("\nSimilarAverageNumber: "+chosenDatas.get(i).getSimilarAverageNumber(sample)+"\n"));
				Recorder.recorder(("SimilarDifficultyNumber: "+chosenDatas.get(i).getSimilarDifficultyNumber(sample)+"\n"));
				Recorder.recorder(("\nSecondMatchingNumber: "+chosenDatas.get(i).getSecondMatchingNumber(sample)+"\n"));
				Recorder.recorder(("StrongestBSSIDMatching: "+chosenDatas.get(i).getStrongestBSSIDMatching(sample)+"\n"));
			}
		}		
		final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
		final String FILE_PATH = "/WifiInfo";
		final File path = new File(SD_PATH + FILE_PATH);
		final String fileName = "score_recorder.txt";

		File file = new File(SD_PATH + FILE_PATH, fileName);
	    if (!path.exists())
	    	path.mkdir();
	    try 
		{
	    	FileOutputStream recorder = new FileOutputStream(file,true);
			recorder.write(("\nsecondChoose\nmaxScore: "+maxScore+"\n").getBytes());
	        recorder.close();
		 }
	    catch (IOException e) 
		{
	        Log.w("ExternalStorage", "Error writing " + file, e);
	    }
		return chosenData;
	}
}