package your.com.HelloWifi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

public class Building
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
	//����ֱ�Ӹ�ֵ�ķ�ʽpublic ArrayList<String> existingBSSIDs =  new ArrayList<String>(Arrays.asList("dsf","df"));      
	
	//�õ�һ����ѧ¥�е�����BSSID,�õ���ֱ�Ӹ�ֵ�����Բ��ô˷���
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
	
	//��ѡ����������
	//��ֻ����ѡ��BSSIDƥ�������ģ�������Ƿ�ѡ��ǰ��������ѡǰ������Ҫ����Ϊ��ͬ���μӷ�����
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
			recorder.write(("firstChoose\nmaxScore: "+maxScore+"\t����: "+chosenDatas.size()+"\nsecondScore: "+secondScore).getBytes());
	        recorder.close();
		 }
	    catch (IOException e) 
		{
	        Log.w("ExternalStorage", "Error writing " + file, e);
	    }	
		//������´������ӳ�ѡ���������Ľ�
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
	
	//��ѡ�����ڵĽ���
	public ArrayList<Data> secondChooseFromDatas(Data sample,ArrayList<Data> chosenDatas)
	{
		//Data chosenData = new Data();
		int[] score = new int[chosenDatas.size()];
		int i, maxScore = 0;
		ArrayList<Data> chosenData = new ArrayList<Data>();	//�����ܾ�ѡ���ü���
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