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

	public Data(String pl, int bssn, String str, String n){
		place = pl;
		BSSIDNum = bssn;
		BSSIDStrongest[0] = str;
		BSSIDConnected.name = n;
	}
	public Data(String pl, int bssn, String str){
		place = pl;
		BSSIDNum = bssn;
		BSSIDStrongest[0] = str;
	}
	public Data(){
		this("",0,"","");
	}
	
	//�õ�����Data����ƥ���BSSID�ĸ���
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
	
	//�õ���ѡ����ĸ�data��ƥ��BSSID�¾�ֵ���������
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
	
	//�õ���ѡ����ĸ�data��ƥ��BSSID�µĽ����Ѷ����������
	public int getSimilarDifficultyNumber(Data sample)
	{
		int num = 0, i, j;
		for(i = 0; i < BSSIDNum; i ++)
		{
			for(j = 0; j < sample.BSSIDNum; j ++)
			{
				if(sample.BSSIDs.get(j).name.equals(BSSIDs.get(i).name)  && sample.BSSIDs.get(j).difficultyLevel == BSSIDs.get(i).difficultyLevel)
				{
					num ++;
					break;
				}
			}
		}
		return num;
	}
	
	//�õ��ڶ���ƥ��������ƥ��BSSIDռ��ѡ���data��BSSID�ı�����������һ�������򷵻�1����Ϊ����������ݶ�Ϊ0.9����ʵ��۲�
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
	
	//�õ�ĳһBSSID�µ�ǿ�Ⱦ�ֵ
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
	
	//�õ��Ƿ�������BSSID���ѡ�����data��ƥ��,������������,���ܷ�ֵҪ�ϸ�
	public int getStrongestBSSIDMatching(Data sample)
	{
		int matchingScore = 0;
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
		return matchingScore;
	}
}

