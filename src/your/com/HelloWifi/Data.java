package your.com.HelloWifi;
import java.util.ArrayList;

public class Data
{
	public String place;
	public String SSID = "Tsinghua";
	public int BSSIDNum;
	public ArrayList<BSSID> BSSIDs = new ArrayList<BSSID>();
	public String[] BSSIDStrongest = {"",""};
	public BSSIDConnected BSSIDConnected = new BSSIDConnected() ;

	public Data(String pl, int bssn, String str1,   String str2, ArrayList<BSSID> bssids, BSSIDConnected bsc){
		place = pl;
		BSSIDNum = bssn;
		BSSIDStrongest[0] = str1;
		BSSIDStrongest[1] = str2;
		BSSIDs = bssids;
		BSSIDConnected = bsc;
	}
	public Data(){
	}
	
	//得到两个Data的相匹配的BSSID的个数
	public int getMatchingNumber(Data sample)
	{
		int num = 0, i, j;
		for(i = 0; i < BSSIDNum; i ++)
		{
			for(j = 0; j < sample.BSSIDNum; j ++)
			{
				if(sample.BSSIDs.get(j).name.equals(BSSIDs.get(i).name))
				{
					num ++;
					break;
				}
			}
		}
		return num;
	}
	
	//得到初选结果的各data中匹配BSSID下均值的相近数量
	public int getSimilarAverageNumber(Data sample)
	{
		int num = 0, i, j;
		for(i = 0; i < BSSIDNum; i ++)
		{
			for(j = 0; j < sample.BSSIDNum; j ++)
			{
				if(sample.BSSIDs.get(j).name.equals(BSSIDs.get(i).name)  && sample.BSSIDs.get(j).strengthAverage > (BSSIDs.get(i).strengthAverage - BSSIDs.get(i).strengthVariance) && sample.BSSIDs.get(j).strengthAverage < (BSSIDs.get(i).strengthAverage + BSSIDs.get(i).strengthVariance))
				{
					num ++;
					break;
				}
			}
		}
		return num;
	}
	
	//得到初选结果的各data中匹配BSSID下的接收难度相近的数量
	public int getSimilarDifficultyNumber(Data sample)
	{
		int num = 0, i, j;
		for(i = 0; i < BSSIDNum; i ++)
		{
			for(j = 0; j < sample.BSSIDNum; j ++)
			{
				if(sample.BSSIDs.get(j).name.equals(BSSIDs.get(i).name)  && sample.BSSIDs.get(j).difficultyLevel == BSSIDs.get(i).difficultyLevel)
				{
					if(place.equals("1109") || place.equals("1111"))
					{
						Recorder.recorder("\n" + place + " sample: " + sample.BSSIDs.get(j).name + sample.BSSIDs.get(j).difficultyLevel + "\t\tdata: " + BSSIDs.get(i).name + BSSIDs.get(i).difficultyLevel);
					}
					num ++;
					break;
				}
			}
		}
		return num;
	}
	
	//得到第二种匹配数，即匹配BSSID占初选结果data的BSSID的比例，若超过一定量，则返回1，认为二者相近，暂定为0.9，待实验观察
	public int getSecondMatchingNumber(Data sample)
	{
		int secondMatchingNumber = 0;
		int num;
		num = getMatchingNumber(sample);
		if((float)num/BSSIDNum > 0.9)
		{
			secondMatchingNumber = 1;			
		}
		return secondMatchingNumber;
	}
	
	//得到某一BSSID下的强度均值
	public float getStrengthAverage(String BSSID)
	{
		float strengthAverage = 0;
		for(BSSID temp : BSSIDs)
		{
			if(temp.name.equals(BSSID))
			{
				strengthAverage = temp.strengthAverage;
				break;
			}			
		}	
		return strengthAverage;
	}
	
	//得到是否有特征BSSID与初选结果的data相匹配,所给分数待调,可能分值要较高
	public int getStrongestBSSIDMatching(Data sample)
	{
		/*
		if(!BSSIDStrongest[0].equals("") && !sample.BSSIDStrongest[0].equals(""))
		{
			if(getStrengthAverage(BSSIDStrongest[0]) < 75)
			{
				if(sample.BSSIDStrongest[0].equals(BSSIDStrongest[0]) && sample.getStrengthAverage(sample.BSSIDStrongest[0]) < 75)
				{
					matchingScore += 1;
				}				
			}
			else
			{
				if(!BSSIDStrongest[1].equals("") && !sample.BSSIDStrongest[1].equals(""))
				{
					if(sample.BSSIDStrongest[0].equals(BSSIDStrongest[0]) && sample.BSSIDStrongest[1].equals(BSSIDStrongest[1]))
					{
						matchingScore += 1;
					}					
				}
			}
		}
		*/
		int matchingScore = 0;
		if(!BSSIDStrongest[0].equals("") && !sample.BSSIDStrongest[0].equals(""))
		{
			if(sample.getStrengthAverage(BSSIDStrongest[0]) < 70)//测试点有路由
			{
				if(sample.BSSIDStrongest[0].equals(BSSIDStrongest[0]) && getStrengthAverage(BSSIDStrongest[0]) < 70)
					matchingScore += 3;
			}
			else 
			{	
				if(!BSSIDStrongest[1].equals("") && !sample.BSSIDStrongest[1].equals(""))
				{
					if(sample.getStrengthAverage(BSSIDStrongest[0]) < 80 && sample.getStrengthAverage(BSSIDStrongest[0]) > 75)//测试点无路由
					{
						if((sample.BSSIDStrongest[0].equals(BSSIDStrongest[0]) && sample.BSSIDStrongest[1].equals(BSSIDStrongest[1]))	|| (sample.BSSIDStrongest[0].equals(BSSIDStrongest[1]) && sample.BSSIDStrongest[1].equals(BSSIDStrongest[0])))
						{							
							if(getStrengthAverage(BSSIDStrongest[0]) < 80 && getStrengthAverage(BSSIDStrongest[0]) > 75 && getStrengthAverage(BSSIDStrongest[1]) < 86 && getStrengthAverage(BSSIDStrongest[1]) > 78 && sample.getStrengthAverage(BSSIDStrongest[1]) < 86 && sample.getStrengthAverage(BSSIDStrongest[1]) > 78)
								matchingScore += 3;
						}
					}
				}
			}
		}
		return matchingScore;
	}
}

