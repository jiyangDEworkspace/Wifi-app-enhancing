package your.com.HelloWifi;

import java.util.ArrayList;
import java.util.Arrays;

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
		System.out.println(existingBSSIDs);
	}
	
	//��ѡ����������
	//��ֻ����ѡ��BSSIDƥ�������ģ�������Ƿ�ѡ��ǰ��������ѡǰ������Ҫ����Ϊ��ͬ���μӷ�����
	public ArrayList<Data> firstChooseFromDatas(Data sample)
	{
		ArrayList<Data> chosenDatas = new ArrayList<Data>();
		int[] score =new int[datas.size()];
		int i, maxScore = 0;
		for(i = 0; i<datas.size(); i ++)
		{
			score[i] = datas.get(i).getMatchingNumber(sample);
			if(maxScore < score[i])
			{
				maxScore = score[i];
			}	
		}
		for(i = 0; i < score.length; i++ )
		{
			if(score[i] == maxScore)
			{
				chosenDatas.add(datas.get(i));				
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
		}		
		return chosenData;
	}
}