package your.com.HelloWifi;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Calculate {
	public static int time = HelloWifiActivity.loopTime;//扫描次数
	public static int levelnum = 3;//强度等级数目
	
	//获取强度平均值的绝对值(3位小数),参数为强度值数组
	public static float getMean(int RSS[]){
		int len = RSS.length;
		float mean = 0;
		
		for(int i = 0; i < len; i ++){
			mean = mean + RSS[i];
		}
		return Float.parseFloat((new DecimalFormat("00.000").format(Math.abs(mean / len))));
	}
	
	//获取强度的方差(3位小数)，参数为强度值数组和强度平均值
	public static float getVariance(int RSS[], float mean){
		int len = RSS.length;
		float var = 0;
		
		for(int i = 0; i < len; i ++){
			var = (float) (var + Math.pow(- RSS[i]- mean, 2));
		}
		return Float.parseFloat((new DecimalFormat("00.000").format(Math.sqrt(var / len))));
	}
	
	//获取接收信号难度，即接收到次数占总扫描次数比值(2位小数)分级别为1~levelnum级，levelnum级
	//为强度最高，参数为接收到次数
	public static int getDifficultyOfReceiving(int len){
		int level = 1 + (int)((float)len / time*levelnum);
		return level > levelnum?levelnum:level;
		
	}
	
	//获取强度变化范围，即不同强度值的个数，参数为信号强度数组
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
	
	//获取不同BSSID匹配个数，参数为库中BSSID的String数组和样本BSSID的String数组
	/*public static int getMatchingNumber(String L_BSS[], String S_BSS[]){
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
	}*/
	
	//获取最强信号所对应的MAC地址，参数为样本Data
	public static String[] getStrongest(Data d){
		int i, index = 0, first = 0;
		float tmp = (float) 200.0;
		String[] strongest = new String[2];
		for(i = 0; i < d.BSSIDs.size(); i ++){
			if( d.BSSIDs.get(i).strengthAverage < tmp){
				index = i;
				tmp = d.BSSIDs.get(i).strengthAverage;
			}
		}
		first = index;
		strongest[0] = d.BSSIDs.get(index).name;
		tmp = (float) 200.0;
		for(i = 0; i < d.BSSIDs.size(); i ++){
			if( d.BSSIDs.get(i).strengthAverage < tmp && i != first){
				index = i;
				tmp = d.BSSIDs.get(i).strengthAverage;
			}
		}
		strongest[1] = d.BSSIDs.get(index).name;
		System.out.println(strongest[0]+", "+strongest[1]);
		return strongest;
	}
	
	//获取样本Data，参数为WifiSum
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
		sd.BSSIDStrongest = getStrongest(sd);
		sd.BSSIDNum = WifiSum.size();
		return sd;
	}
}

//将收到信号转换为Data Data sample = Calculate.getSampleData(WifiSum);