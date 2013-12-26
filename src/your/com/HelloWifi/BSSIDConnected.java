package your.com.HelloWifi;

public class BSSIDConnected
{
	public String name;
	public float RSSIAverage;
	public float RSSIVariance;
	public int RSSIStabilityLevel;
	public float linkSpeedAverage;
	public float linkSpeedVariance;
	public int linkSpeedStabilityLevel;
	
	public BSSIDConnected(){
		this("",0,0,0,0,0,0);
	}
	public BSSIDConnected(String n, float ra,float rv, int rs, float la,float lv, int ls){
		name = n;
		RSSIAverage = ra;
		RSSIVariance = rv;
		RSSIStabilityLevel = rs;
		linkSpeedAverage = la;
		linkSpeedVariance = lv;
		linkSpeedStabilityLevel = ls;
	}
}

