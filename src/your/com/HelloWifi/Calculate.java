package your.com.HelloWifi;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Calculate {
	public static int time = 20;//ɨ�����
	
	//��ȡǿ��ƽ��ֵ�ľ���ֵ(3λС��),����Ϊǿ��ֵ����
	public static float getMean(int RSS[]){
		int len = RSS.length;
		float mean = 0;
		
		for(int i = 0; i < len; i ++){
			mean = mean + RSS[i];
		}
		return Float.parseFloat((new DecimalFormat("00.000").format(Math.abs(mean / len))));
	}
	
	//��ȡǿ�ȵķ���(3λС��)������Ϊǿ��ֵ�����ǿ��ƽ��ֵ
	public static float getVariance(int RSS[], float mean){
		int len = RSS.length;
		float var = 0;
		
		for(int i = 0; i < len; i ++){
			var = (float) (var + Math.pow(- RSS[i]- mean, 2));
		}
		return Float.parseFloat((new DecimalFormat("00.000").format(Math.sqrt(var / len))));
	}
	
	//��ȡ�����ź��Ѷȣ������յ�����ռ��ɨ�������ֵ(2λС��)��0.2Ϊ�ȼ��ּ���Ϊ1~5����5��
	//Ϊǿ����ߣ�����Ϊ���յ�����
	public static int getDifficultyOfReceiving(int len){
		int level = 1 + (int)((float)(len / time)*5);
		return level > 5?5:level;
		
	}
	
	//��ȡǿ�ȱ仯��Χ������ͬǿ��ֵ�ĸ���������Ϊ�ź�ǿ������
	public static int getRangeOfVariation(int RSS[]){
		int len = RSS.length, num = 1;
		int range[] = new int[len];
		int i, j;
		boolean flag = true;
		
		range[0] = RSS[0];
		for(i = 1; i < len; i ++, flag = true){
			for(j = 0; j < num; j ++){
				if(RSS[i] == range[j]){
					flag = false;
					break;
				}
			}
			if(flag){
				range[num ++] = RSS[i];
			}
		}
		return num;
	}
	
	//��ȡ��ͬBSSIDƥ�����������Ϊ����BSSID��String���������BSSID��String����
	public static int getMatchingNumber(String L_BSS[], String S_BSS[]){
		int num = 0;
		for(int i = 0; i < S_BSS.length; i ++){
			for(int j = 0; j < L_BSS.length; j ++){
				if(S_BSS[i].equals(L_BSS[j])){
					num ++;
					break;
				}
			}
		}
		return num;
	}
	
	//��ȡ��ǿ�ź�����Ӧ��MAC��ַ������Ϊ����Data
	public static String getStrongest(Data d){
		int i, strongest = 0;
		float tmp = (float) 200.0;
		for(i = 0; i < d.BSSIDs.size(); i ++){
			if( d.BSSIDs.get(i).strengthAverage < tmp){
				strongest = i;
				tmp = d.BSSIDs.get(i).strengthAverage;
			}
		}
		return d.BSSIDs.get(strongest).name;
	}
	
	//��ȡ����Data������ΪWifiSum
	public static Data getSampleData(ArrayList<ArrayList> WifiSum){
		Data sd = new Data();
		for(int i = 0; i < WifiSum.size(); i ++){
			int[] RSS = new int[WifiSum.get(i).size() - 2];//attention
			for(int j = 0; j < WifiSum.get(i).size() - 2; j ++){
				RSS[j] = (Integer) WifiSum.get(i).get(j + 2);
			}
			float mean = getMean(RSS);
			sd.BSSIDs.add(new BSSID((String)WifiSum.get(i).get(0), mean, getVariance(RSS, mean),
					getDifficultyOfReceiving(RSS.length),getRangeOfVariation(RSS)));
		}
		sd.BSSIDStrongest[0] = getStrongest(sd);
		sd.BSSIDNum = WifiSum.size();
		return sd;
	}
}

//���յ��ź�ת��ΪData Data sample = Calculate.getSampleData(WifiSum);