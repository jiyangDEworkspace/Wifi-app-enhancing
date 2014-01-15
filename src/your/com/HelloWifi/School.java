package your.com.HelloWifi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import android.util.Log;

public class School  implements Serializable
{ 		
	//private static final long serialVersionUID = 1L;
	public ArrayList<Building> buildings = new ArrayList<Building>();
	//以下是定义方式举例

	//挑选教学楼
	public Building chooseFromBuildings(String BSSIDStrongest)	//先用一个最强的，若有问题再用几个或者匹配强度//限制看存在与否，有问题再看存在几个等等
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
	public boolean test_chooseFromBuildings(String BSSIDStrongest)	//先用一个最强的，若有问题再用几个或者匹配强度//限制看存在与否，有问题再看存在几个等等
	{
		return buildings.get(1).existingBSSIDs.contains(BSSIDStrongest);		
	}	
}