package your.com.HelloWifi;

public class BSSID
{
	public String name ;
	public float strengthAverage;
	public float strengthVariance;
	public int difficultyLevel;
	public int stabilityLevel;
	
	public BSSID(String n, float sA, float sV, int d, int s){
		name = n;
		strengthAverage = sA;
		strengthVariance = sV;
		difficultyLevel = d;
		stabilityLevel = s;
	}
	public BSSID(String n){
		name = n;
	}
	public BSSID(){
		this("",0,0,0,0);
	}
}