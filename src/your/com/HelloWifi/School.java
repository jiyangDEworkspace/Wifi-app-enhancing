package your.com.HelloWifi;

import java.util.ArrayList;
import java.util.Arrays;

public class School
{ 
	public static ArrayList<Building> buildings = new ArrayList<Building>();
	//以下是定义方式举例
	static Building library = new Building("library");
	static Building thirdBuilding = new Building("Third Building");
	
	static 
	{
		library.existingBSSIDs = new ArrayList<String>(Arrays.asList(
				" 04:c5:a4:08:ec:a0",
				"04:c5:a4:08:f6:f0",
				"04:c5:a4:09:4e:c0",
				"04:c5:a4:66:38:80",
				"44:e4:d9:00:7b:80",
				"44:e4:d9:01:04:f0",
				"44:e4:d9:40:2d:90",
				"44:e4:d9:84:e6:f0",
				"44:e4:d9:85:85:80"));
		buildings.add(library);
	}
	static
	{
		Data r1101 = new Data("1101",7,"10:8c:cf:10:80:20");
		r1101.BSSIDs.add(new BSSID("08:17:35:c7:f5:80",96.14f,3.842f,5,7));
		r1101.BSSIDs.add(new BSSID("10:8c:cf:10:7e:d0",96.031f,3.592f,4,4));
		r1101.BSSIDs.add(new BSSID("10:8c:cf:10:82:e0",82.0f,0.0f,1,1));
		r1101.BSSIDs.add(new BSSID("10:8c:cf:11:66:30",97.5f,3.015f,4,4));
		r1101.BSSIDs.add(new BSSID("10:8c:cf:10:80:20",71.36f,3.903f,5,10));
		r1101.BSSIDs.add(new BSSID("10:8c:cf:11:66:80",99.184f,2.21f,5,6));
		r1101.BSSIDs.add(new BSSID("08:17:35:c7:f0:80",72.102f,2.023f,5,9));

		Data r1102 = new Data("1102",14,"10:8c:cf:10:7e:d0");
		r1102.BSSIDs.add(new BSSID("08:17:35:c7:f5:80",93.72f,3.216f,5,8));
		r1102.BSSIDs.add(new BSSID("10:8c:cf:10:80:20",87.86f,2.888f,5,10));
		r1102.BSSIDs.add(new BSSID("08:17:35:c7:f0:80",85.17f,2.117f,5,8));
		r1102.BSSIDs.add(new BSSID("10:8c:cf:11:66:80",97.767f,1.126f,5,5));
		r1102.BSSIDs.add(new BSSID("10:8c:cf:10:7e:d0",72.5f,3.466f,5,9));
		r1102.BSSIDs.add(new BSSID("10:8c:cf:11:66:30",98.0f,0.0f,1,1));
		r1102.BSSIDs.add(new BSSID("10:8c:cf:11:65:70",87.02f,2.075f,5,11));
		r1102.BSSIDs.add(new BSSID("10:8c:cf:11:6a:f0",90.929f,1.836f,5,8));
		r1102.BSSIDs.add(new BSSID("08:17:35:c7:f6:c0",92.224f,1.074f,5,6));
		r1102.BSSIDs.add(new BSSID("08:17:35:c7:f2:10",94.551f,1.685f,5,7));
		r1102.BSSIDs.add(new BSSID("10:8c:cf:11:6d:10",96.344f,1.825f,5,6));
		r1102.BSSIDs.add(new BSSID("10:8c:cf:11:6e:90",94.0f,0.0f,2,1));
		r1102.BSSIDs.add(new BSSID("10:8c:cf:11:6d:50",92.681f,1.178f,5,5));
		r1102.BSSIDs.add(new BSSID("10:8c:cf:11:69:00",89.091f,0.996f,3,4));
		
		Data r1103 = new Data("1103",7,"10:8c:cf:10:80:20");
		r1103.BSSIDs.add(new BSSID("08:17:35:c7:f5:80",84.08f,2.357f,5,10));
		r1103.BSSIDs.add(new BSSID("10:8c:cf:10:80:20",80.08f,2.244f,5,9));
		r1103.BSSIDs.add(new BSSID("08:17:35:c7:f0:80",84.94f,1.642f,5,8));
		r1103.BSSIDs.add(new BSSID("10:8c:cf:10:7e:d0",89.16f,2.859f,5,11));
		r1103.BSSIDs.add(new BSSID("10:8c:cf:11:65:70",96.585f,2.036f,5,6));
		r1103.BSSIDs.add(new BSSID("10:8c:cf:11:66:80",89.521f,1.947f,5,10));
		r1103.BSSIDs.add(new BSSID("10:8c:cf:11:72:40",92.0f,1.72f,5,8));

		Data r1104 = new Data("1104",15,"10:8c:cf:10:7e:d0");
		r1104.BSSIDs.add(new BSSID("08:17:35:c7:f5:80",82.52f,3.727f,5,12));
		r1104.BSSIDs.add(new BSSID("10:8c:cf:10:80:20",81.0f,0.0f,1,1));
		r1104.BSSIDs.add(new BSSID("08:17:35:c7:f0:80",96.023f,3.279f,5,5));
		r1104.BSSIDs.add(new BSSID("10:8c:cf:10:7e:d0",63.86f,3.96f,5,7));
		r1104.BSSIDs.add(new BSSID("10:8c:cf:11:72:40",90.0f,0.0f,1,1));
		r1104.BSSIDs.add(new BSSID("10:8c:cf:11:66:80",87.0f,0.0f,1,1));
		r1104.BSSIDs.add(new BSSID("10:8c:cf:11:65:70",85.367f,3.212f,5,8));
		r1104.BSSIDs.add(new BSSID("10:8c:cf:11:6d:10",88.082f,1.412f,5,8));
		r1104.BSSIDs.add(new BSSID("10:8c:cf:11:6d:50",88.388f,1.563f,5,9));
		r1104.BSSIDs.add(new BSSID("08:17:35:c7:f6:c0",92.604f,2.289f,5,8));
		r1104.BSSIDs.add(new BSSID("10:8c:cf:11:6e:90",90.362f,1.119f,5,6));
		r1104.BSSIDs.add(new BSSID("08:17:35:c7:f2:10",89.702f,1.336f,5,7));
		r1104.BSSIDs.add(new BSSID("10:8c:cf:11:69:00",92.457f,2.253f,5,8));
		r1104.BSSIDs.add(new BSSID("10:8c:cf:11:6a:f0",94.629f,3.347f,4,5));
		r1104.BSSIDs.add(new BSSID("10:8c:cf:11:6f:d0",94.588f,1.611f,2,2));
		
		Data r1105 = new Data("1105",17,"08:17:35:c7:f5:80");
		r1105.BSSIDs.add(new BSSID("08:17:35:c7:f5:80",64.62f,3.594f,5,11));
		r1105.BSSIDs.add(new BSSID("10:8c:cf:10:7e:d0",79.5f,5.941f,5,17));
		r1105.BSSIDs.add(new BSSID("10:8c:cf:11:6d:10",91.0f,0.0f,1,1));
		r1105.BSSIDs.add(new BSSID("10:8c:cf:11:6d:50",96.368f,2.906f,2,3));
		r1105.BSSIDs.add(new BSSID("08:17:35:c7:f6:c0",94.0f,0.0f,1,1));
		r1105.BSSIDs.add(new BSSID("10:8c:cf:11:65:70",95.775f,3.844f,5,5));
		r1105.BSSIDs.add(new BSSID("10:8c:cf:11:6e:90",89.0f,0.0f,1,1));
		r1105.BSSIDs.add(new BSSID("08:17:35:c7:f2:10",89.0f,0.0f,1,1));
		r1105.BSSIDs.add(new BSSID("10:8c:cf:11:69:00",96.0f,0.0f,1,1));
		r1105.BSSIDs.add(new BSSID("08:17:35:c7:f0:80",94.9f,2.147f,5,7));
		r1105.BSSIDs.add(new BSSID("10:8c:cf:11:6a:f0",98.0f,0.0f,1,1));
		r1105.BSSIDs.add(new BSSID("10:8c:cf:10:80:20",92.122f,2.6f,5,11));
		r1105.BSSIDs.add(new BSSID("10:8c:cf:11:66:80",90.163f,2.151f,5,9));
		r1105.BSSIDs.add(new BSSID("10:8c:cf:11:66:30",92.234f,4.367f,5,11));
		r1105.BSSIDs.add(new BSSID("10:8c:cf:11:72:40",98.548f,2.551f,4,4));
		r1105.BSSIDs.add(new BSSID("08:17:35:c7:f4:f0",96.103f,1.751f,4,5));
		r1105.BSSIDs.add(new BSSID("10:8c:cf:10:82:e0",93.652f,0.476f,3,2));

		Data r1107 = new Data("1107",11,"10:8c:cf:10:82:e0");
		r1107.BSSIDs.add(new BSSID("08:17:35:c7:f5:80",81.18f,3.186f,5,10));
		r1107.BSSIDs.add(new BSSID("10:8c:cf:10:7e:d0",90.96f,1.095f,5,7));
		r1107.BSSIDs.add(new BSSID("10:8c:cf:11:6d:50",93.0f,0.0f,1,1));
		r1107.BSSIDs.add(new BSSID("10:8c:cf:10:2d:40",90.0f,0.0f,1,1));
		r1107.BSSIDs.add(new BSSID("10:8c:cf:11:6e:90",90.0f,0.0f,1,1));
		r1107.BSSIDs.add(new BSSID("10:8c:cf:10:82:e0",77.08f,1.623f,5,7));
		r1107.BSSIDs.add(new BSSID("10:8c:cf:11:69:20",96.0f,0.0f,1,1));
		r1107.BSSIDs.add(new BSSID("10:8c:cf:11:65:70",98.0f,0.0f,1,1));
		r1107.BSSIDs.add(new BSSID("08:17:35:c7:f2:10",98.0f,0.0f,1,1));
		r1107.BSSIDs.add(new BSSID("10:8c:cf:11:66:30",84.673f,1.361f,5,6));
		r1107.BSSIDs.add(new BSSID("10:8c:cf:11:50:90",97.592f,1.369f,5,7));

		thirdBuilding.datas.add(r1101);
		thirdBuilding.datas.add(r1102);
		thirdBuilding.datas.add(r1103);
		thirdBuilding.datas.add(r1104);
		thirdBuilding.datas.add(r1105);
		thirdBuilding.datas.add(r1107);
		
		thirdBuilding.getExistingBSSIDs();
	}
	static
	{
		thirdBuilding.existingBSSIDs = new ArrayList<String>(Arrays.asList(
				"08:17:35:c7:d4:80",
				"08:17:35:c7:f0:80",
				"08:17:35:c7:f2:10",
				"08:17:35:c7:f4:f0",
				"08:17:35:c7:f5:80",
				"08:17:35:c7:f6:c0",
				"08:17:35:c7:f9:e0",
				"10:8c:cf:10:2d:40",
				"10:8c:cf:10:7e:d0",
				"10:8c:cf:10:80:20",
				"10:8c:cf:10:82:e0",
				"10:8c:cf:10:99:50",
				"10:8c:cf:11:35:60",
				"10:8c:cf:11:50:90",
				"10:8c:cf:11:5e:20",
				"10:8c:cf:11:65:10",
				"10:8c:cf:11:65:70",
				"10:8c:cf:11:66:30",
				"10:8c:cf:11:66:80",
				"10:8c:cf:11:69:00",
				"10:8c:cf:11:69:20",
				"10:8c:cf:11:6a:00",
				"10:8c:cf:11:6a:f0",
				"10:8c:cf:11:6b:a0",
				"10:8c:cf:11:6d:10",
				"10:8c:cf:11:6d:50",
				"10:8c:cf:11:6e:90",
				"10:8c:cf:11:6f:d0",
				"10:8c:cf:11:72:40",
				"10:8c:cf:11:72:50"));
		buildings.add(thirdBuilding);
	}
	static
	{
		//library.existingBSSIDs = new ArrayList<String>(Arrays.asList("sd","dzsf"));
		buildings.add(thirdBuilding);
	}
	//挑选教学楼
	public static Building chooseFromBuildings(String BSSIDStrongest)	//先用一个最强的，若有问题再用几个或者匹配强度//限制看存在与否，有问题再看存在几个等等
	{
		int i,size;
		size = buildings.size();
		for(i = 0; i < size; i++)
		{
			if(buildings.get(i).existingBSSIDs.contains(BSSIDStrongest))
			{
				break;
			}			
		}
		if(i == size) 
		{
			return new Building();
		}
		return buildings.get(i);
	}	
	public static boolean test_chooseFromBuildings(String BSSIDStrongest)	//先用一个最强的，若有问题再用几个或者匹配强度//限制看存在与否，有问题再看存在几个等等
	{
		return buildings.get(1).existingBSSIDs.contains(BSSIDStrongest);		
	}	
}