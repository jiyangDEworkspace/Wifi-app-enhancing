package your.com.HelloWifi;

import java.util.ArrayList;
import java.util.Arrays;

import android.util.Log;

public class School
{ 
	public static ArrayList<Building> buildings = new ArrayList<Building>();
	//以下是定义方式举例
	static Building library = new Building("library");
	static Building thirdBuilding = new Building("Third Building");
	static Building fourthBuilding = new Building("Fourth Building");
	
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
	
	static{thirdBuilding.datas.add(
			new Data("1101", 9, "10:8c:cf:10:80:20", "08:17:35:c7:f0:80", new ArrayList<BSSID>(Arrays.asList(
			new BSSID("08:17:35:c7:f0:80", 70.112f, 1.218f, 3, 9),
			new BSSID("08:17:35:c7:f4:f0", 98.094f, 1.221f, 3, 5),
			new BSSID("08:17:35:c7:f5:80", 95.211f, 2.392f, 3, 10),
			new BSSID("10:8c:cf:10:7e:d0", 97.734f, 1.113f, 3, 5),
			new BSSID("10:8c:cf:10:80:20", 67.488f, 2.382f, 3, 13),
			new BSSID("10:8c:cf:11:66:70", 98.612f, 1.419f, 2, 6),
			new BSSID("10:8c:cf:11:66:80", 92.547f, 1.835f, 3, 10),
			new BSSID("10:8c:cf:11:67:50", 98.675f, 1.522f, 2, 6),
			new BSSID("10:8c:cf:11:72:40", 86.368f, 2.124f, 3, 13))),
			new BSSIDConnected("08:17:35:c7:f0:80", 70.432f, 0.964f, 8, 65.0f, 0.0f, 1)));
		}

		static{thirdBuilding.datas.add(
			new Data("1102", 13, "10:8c:cf:10:7e:d0", "08:17:35:c7:f0:80", new ArrayList<BSSID>(Arrays.asList(
			new BSSID("08:17:35:c7:f0:80", 81.716f, 1.862f, 3, 12),
			new BSSID("08:17:35:c7:f2:10", 86.524f, 1.811f, 3, 13),
			new BSSID("08:17:35:c7:f6:c0", 92.632f, 1.91f, 3, 11),
			new BSSID("10:8c:cf:10:7e:d0", 75.88f, 1.415f, 3, 11),
			new BSSID("10:8c:cf:10:80:20", 93.197f, 3.095f, 3, 14),
			new BSSID("10:8c:cf:11:65:70", 88.663f, 1.947f, 3, 11),
			new BSSID("10:8c:cf:11:66:80", 94.62f, 2.129f, 3, 8),
			new BSSID("10:8c:cf:11:69:00", 94.556f, 2.278f, 3, 9),
			new BSSID("10:8c:cf:11:6a:f0", 94.152f, 1.97f, 3, 11),
			new BSSID("10:8c:cf:11:6d:10", 92.752f, 2.199f, 3, 9),
			new BSSID("10:8c:cf:11:6d:50", 94.903f, 2.822f, 3, 12),
			new BSSID("10:8c:cf:11:6e:90", 98.725f, 1.634f, 2, 7),
			new BSSID("10:8c:cf:11:72:40", 98.5f, 4.5f, 1, 2))),
			new BSSIDConnected("10:8c:cf:10:7e:d0", 76.67f, 1.214f, 9, 63.046f, 4.677f, 4)));
		}

		static{thirdBuilding.datas.add(
			new Data("1103", 9, "10:8c:cf:10:80:20", "08:17:35:c7:f5:80", new ArrayList<BSSID>(Arrays.asList(
			new BSSID("08:17:35:c7:f0:80", 83.944f, 3.393f, 3, 17),
			new BSSID("08:17:35:c7:f4:f0", 99.0f, 1.0f, 1, 2),
			new BSSID("08:17:35:c7:f5:80", 81.452f, 2.496f, 3, 16),
			new BSSID("08:17:35:c7:f6:c0", 97.167f, 0.373f, 1, 2),
			new BSSID("10:8c:cf:10:7e:d0", 89.112f, 2.643f, 3, 13),
			new BSSID("10:8c:cf:10:80:20", 76.232f, 2.54f, 3, 13),
			new BSSID("10:8c:cf:11:65:70", 97.269f, 1.722f, 2, 7),
			new BSSID("10:8c:cf:11:66:80", 86.116f, 2.181f, 3, 13),
			new BSSID("10:8c:cf:11:72:40", 90.112f, 1.983f, 3, 12))),
			new BSSIDConnected("10:8c:cf:10:80:20", 77.214f, 2.437f, 14, 65.0f, 0.0f, 1)));
		}

		static{thirdBuilding.datas.add(
			new Data("1104", 14, "10:8c:cf:10:7e:d0", "08:17:35:c7:f5:80", new ArrayList<BSSID>(Arrays.asList(
			new BSSID("08:17:35:c7:f0:80", 97.458f, 1.918f, 3, 8),
			new BSSID("08:17:35:c7:f2:10", 86.13f, 2.296f, 3, 13),
			new BSSID("08:17:35:c7:f5:80", 76.992f, 2.182f, 3, 13),
			new BSSID("08:17:35:c7:f6:c0", 94.016f, 2.154f, 3, 12),
			new BSSID("10:8c:cf:10:7e:d0", 64.524f, 1.859f, 3, 12),
			new BSSID("10:8c:cf:10:80:20", 98.071f, 1.15f, 1, 4),
			new BSSID("10:8c:cf:10:99:50", 99.0f, 0.816f, 1, 3),
			new BSSID("10:8c:cf:11:65:70", 81.55f, 2.235f, 3, 12),
			new BSSID("10:8c:cf:11:69:00", 95.463f, 2.123f, 3, 9),
			new BSSID("10:8c:cf:11:6a:f0", 90.651f, 2.542f, 3, 11),
			new BSSID("10:8c:cf:11:6d:10", 92.751f, 1.806f, 3, 9),
			new BSSID("10:8c:cf:11:6d:50", 91.486f, 2.197f, 3, 12),
			new BSSID("10:8c:cf:11:6e:90", 97.198f, 1.688f, 2, 7),
			new BSSID("10:8c:cf:11:6f:d0", 96.157f, 1.761f, 2, 8))),
			new BSSIDConnected("10:8c:cf:10:7e:d0", 65.34f, 1.814f, 13, 65.0f, 0.0f, 1)));
		}

		static{thirdBuilding.datas.add(
			new Data("1105", 11, "08:17:35:c7:f5:80", "10:8c:cf:10:7e:d0", new ArrayList<BSSID>(Arrays.asList(
			new BSSID("08:17:35:c7:f0:80", 95.659f, 2.363f, 3, 12),
			new BSSID("08:17:35:c7:f4:f0", 99.0f, 0.0f, 1, 1),
			new BSSID("08:17:35:c7:f5:80", 69.964f, 2.704f, 3, 15),
			new BSSID("10:8c:cf:10:7e:d0", 74.871f, 2.376f, 3, 14),
			new BSSID("10:8c:cf:10:80:20", 88.964f, 2.32f, 3, 12),
			new BSSID("10:8c:cf:10:82:e0", 96.167f, 2.238f, 3, 9),
			new BSSID("10:8c:cf:11:65:70", 97.612f, 1.808f, 2, 6),
			new BSSID("10:8c:cf:11:66:30", 94.288f, 3.185f, 3, 11),
			new BSSID("10:8c:cf:11:66:80", 94.488f, 2.568f, 3, 10),
			new BSSID("10:8c:cf:11:6d:50", 97.679f, 1.326f, 2, 8),
			new BSSID("10:8c:cf:11:72:40", 99.206f, 0.932f, 1, 3))),
			new BSSIDConnected("10:8c:cf:11:66:30", 75.86f, 2.345f, 14, 65.0f, 0.0f, 1)));
		}

		static{thirdBuilding.datas.add(
			new Data("man_WC", 15, "10:8c:cf:10:2d:40", "10:8c:cf:10:82:e0", new ArrayList<BSSID>(Arrays.asList(
			new BSSID("08:17:35:c7:f2:10", 96.69f, 2.591f, 2, 9),
			new BSSID("08:17:35:c7:f5:80", 90.101f, 2.607f, 3, 14),
			new BSSID("08:17:35:c7:f6:c0", 96.744f, 1.633f, 2, 7),
			new BSSID("10:8c:cf:10:2d:40", 80.434f, 3.156f, 3, 18),
			new BSSID("10:8c:cf:10:7e:d0", 90.28f, 3.039f, 3, 16),
			new BSSID("10:8c:cf:10:82:e0", 86.594f, 2.999f, 3, 15),
			new BSSID("10:8c:cf:10:99:50", 97.0f, 0.0f, 1, 1),
			new BSSID("10:8c:cf:11:35:60", 97.067f, 0.249f, 1, 2),
			new BSSID("10:8c:cf:11:5e:20", 96.722f, 1.53f, 3, 8),
			new BSSID("10:8c:cf:11:65:70", 94.377f, 2.279f, 3, 7),
			new BSSID("10:8c:cf:11:66:30", 97.169f, 1.322f, 2, 6),
			new BSSID("10:8c:cf:11:6a:f0", 98.5f, 1.5f, 1, 2),
			new BSSID("10:8c:cf:11:6d:10", 98.367f, 1.528f, 1, 5),
			new BSSID("10:8c:cf:11:6d:50", 94.696f, 2.597f, 2, 8),
			new BSSID("10:8c:cf:11:6e:90", 93.887f, 3.244f, 3, 13))),
			new BSSIDConnected("10:8c:cf:11:6e:90", 85.238f, 8.167f, 25, 31.75f, 27.666f, 9)));
		}

		static{thirdBuilding.datas.add(
			new Data("1107", 9, "08:17:35:c7:f5:80", "10:8c:cf:11:66:30", new ArrayList<BSSID>(Arrays.asList(
			new BSSID("08:17:35:c7:f0:80", 98.0f, 1.651f, 1, 3),
			new BSSID("08:17:35:c7:f5:80", 73.44f, 2.581f, 3, 14),
			new BSSID("10:8c:cf:10:2d:40", 98.057f, 1.459f, 1, 5),
			new BSSID("10:8c:cf:10:7e:d0", 88.142f, 2.876f, 3, 16),
			new BSSID("10:8c:cf:10:80:20", 97.715f, 1.216f, 2, 7),
			new BSSID("10:8c:cf:10:82:e0", 83.588f, 2.203f, 3, 14),
			new BSSID("10:8c:cf:11:66:30", 83.308f, 2.521f, 3, 15),
			new BSSID("10:8c:cf:11:66:70", 95.818f, 1.946f, 1, 6),
			new BSSID("10:8c:cf:11:66:80", 96.315f, 2.239f, 3, 9))),
			new BSSIDConnected("10:8c:cf:10:82:e0", 84.73f, 2.586f, 15, 14.278f, 14.037f, 11)));
		}

		static{thirdBuilding.datas.add(
			new Data("1108", 10, "10:8c:cf:10:2d:40", "10:8c:cf:11:5e:20", new ArrayList<BSSID>(Arrays.asList(
			new BSSID("10:8c:cf:10:2d:40", 64.216f, 2.794f, 3, 17),
			new BSSID("10:8c:cf:10:82:e0", 86.938f, 3.008f, 3, 13),
			new BSSID("10:8c:cf:10:99:50", 85.812f, 2.462f, 3, 15),
			new BSSID("10:8c:cf:11:35:60", 93.423f, 3.107f, 3, 13),
			new BSSID("10:8c:cf:11:50:90", 88.684f, 3.154f, 3, 14),
			new BSSID("10:8c:cf:11:5e:20", 84.882f, 2.476f, 3, 16),
			new BSSID("10:8c:cf:11:65:10", 90.216f, 2.216f, 3, 13),
			new BSSID("10:8c:cf:11:69:20", 88.0f, 2.384f, 3, 15),
			new BSSID("10:8c:cf:11:6a:00", 93.755f, 3.308f, 3, 13),
			new BSSID("10:8c:cf:11:6b:a0", 93.398f, 2.19f, 3, 11))),
			new BSSIDConnected("10:8c:cf:11:69:20", 88.05f, 2.154f, 14, 4.486f, 6.281f, 4)));
		}

		static{thirdBuilding.datas.add(
			new Data("1106", 17, "10:8c:cf:10:2d:40", "10:8c:cf:10:82:e0", new ArrayList<BSSID>(Arrays.asList(
			new BSSID("08:17:35:c7:f2:10", 92.535f, 2.535f, 3, 10),
			new BSSID("08:17:35:c7:f5:80", 95.912f, 2.552f, 1, 6),
			new BSSID("10:8c:cf:10:2d:40", 78.307f, 2.624f, 3, 15),
			new BSSID("10:8c:cf:10:7e:d0", 93.706f, 3.459f, 2, 10),
			new BSSID("10:8c:cf:10:82:e0", 79.03f, 2.876f, 3, 15),
			new BSSID("10:8c:cf:10:86:d0", 97.64f, 0.93f, 1, 5),
			new BSSID("10:8c:cf:10:99:50", 92.402f, 3.496f, 3, 14),
			new BSSID("10:8c:cf:11:35:60", 94.904f, 2.198f, 2, 7),
			new BSSID("10:8c:cf:11:50:90", 94.143f, 2.799f, 1, 3),
			new BSSID("10:8c:cf:11:5e:20", 92.136f, 3.161f, 3, 13),
			new BSSID("10:8c:cf:11:65:70", 95.679f, 1.965f, 1, 3),
			new BSSID("10:8c:cf:11:69:20", 91.337f, 1.999f, 2, 10),
			new BSSID("10:8c:cf:11:6a:00", 94.678f, 3.416f, 2, 12),
			new BSSID("10:8c:cf:11:6b:a0", 95.412f, 2.316f, 3, 8),
			new BSSID("10:8c:cf:11:6d:10", 93.881f, 1.735f, 1, 3),
			new BSSID("10:8c:cf:11:6d:50", 89.481f, 2.368f, 3, 11),
			new BSSID("10:8c:cf:11:6e:90", 95.697f, 3.782f, 1, 2))),
			new BSSIDConnected("10:8c:cf:11:6a:00", 76.314f, 22.83f, 23, 44.896f, 27.745f, 8)));
		}

		static{thirdBuilding.datas.add(
			new Data("1109", 11, "10:8c:cf:10:82:e0", "10:8c:cf:10:2d:40", new ArrayList<BSSID>(Arrays.asList(
			new BSSID("08:17:35:c7:d4:80", 93.0f, 0.0f, 1, 1),
			new BSSID("08:17:35:c7:f5:80", 94.266f, 2.146f, 3, 11),
			new BSSID("10:8c:cf:10:2d:40", 85.718f, 2.651f, 3, 13),
			new BSSID("10:8c:cf:10:7e:d0", 98.0f, 0.0f, 1, 1),
			new BSSID("10:8c:cf:10:82:e0", 62.64f, 2.681f, 3, 15),
			new BSSID("10:8c:cf:11:50:90", 87.373f, 4.29f, 3, 17),
			new BSSID("10:8c:cf:11:5e:20", 96.75f, 1.984f, 1, 3),
			new BSSID("10:8c:cf:11:66:30", 92.948f, 2.54f, 3, 11),
			new BSSID("10:8c:cf:11:66:70", 97.5f, 1.5f, 1, 2),
			new BSSID("10:8c:cf:11:69:20", 97.459f, 0.958f, 3, 5),
			new BSSID("10:8c:cf:11:72:50", 96.588f, 1.726f, 3, 9))),
			new BSSIDConnected("10:8c:cf:11:72:50", 63.132f, 2.845f, 16, 65.0f, 0.0f, 1)));
		}

		static{thirdBuilding.datas.add(
			new Data("1111", 10, "10:8c:cf:10:2d:40", "10:8c:cf:11:50:90", new ArrayList<BSSID>(Arrays.asList(
			new BSSID("08:17:35:c7:d4:80", 97.839f, 1.365f, 2, 7),
			new BSSID("10:8c:cf:10:2d:40", 75.508f, 1.98f, 3, 13),
			new BSSID("10:8c:cf:10:82:e0", 80.008f, 1.925f, 3, 11),
			new BSSID("10:8c:cf:11:35:60", 96.725f, 1.533f, 1, 3),
			new BSSID("10:8c:cf:11:50:90", 77.683f, 3.413f, 3, 18),
			new BSSID("10:8c:cf:11:5e:20", 96.95f, 1.915f, 3, 8),
			new BSSID("10:8c:cf:11:66:30", 96.904f, 1.691f, 2, 6),
			new BSSID("10:8c:cf:11:66:70", 99.882f, 1.959f, 1, 5),
			new BSSID("10:8c:cf:11:69:20", 96.269f, 1.645f, 2, 6),
			new BSSID("10:8c:cf:11:72:50", 87.32f, 2.352f, 3, 14))),
			new BSSIDConnected("10:8c:cf:10:2d:40", 75.948f, 1.967f, 13, 64.986f, 0.313f, 2)));
		}

		static{thirdBuilding.datas.add(
			new Data("1113", 7, "10:8c:cf:11:50:90", "10:8c:cf:10:2d:40", new ArrayList<BSSID>(Arrays.asList(
			new BSSID("08:17:35:c7:d4:80", 100.0f, 0.0f, 1, 1),
			new BSSID("10:8c:cf:10:2d:40", 85.208f, 1.682f, 3, 10),
			new BSSID("10:8c:cf:10:82:e0", 91.625f, 2.224f, 3, 12),
			new BSSID("10:8c:cf:10:95:80", 100.0f, 0.0f, 1, 1),
			new BSSID("10:8c:cf:11:50:90", 63.508f, 1.652f, 3, 10),
			new BSSID("10:8c:cf:11:69:20", 97.789f, 0.408f, 1, 2),
			new BSSID("10:8c:cf:11:72:50", 93.29f, 2.28f, 3, 13))),
			new BSSIDConnected("10:8c:cf:11:50:90", 64.02f, 1.658f, 10, 65.0f, 0.0f, 1)));
		}
	//static	{		
	//	thirdBuilding.getExistingBSSIDs();	
	//}
		static{fourthBuilding.datas.add(
				new Data("r4401", 8, "c0:62:6b:66:51:50", "04:c5:a4:66:72:d0", new ArrayList<BSSID>(Arrays.asList(
				new BSSID("04:c5:a4:08:4a:20", 97.286f, 1.335f, 2, 5),
				new BSSID("04:c5:a4:66:72:d0", 77.964f, 3.267f, 3, 14),
				new BSSID("04:c5:a4:66:74:80", 96.148f, 2.805f, 3, 11),
				new BSSID("04:c5:a4:80:12:10", 92.45f, 2.889f, 3, 12),
				new BSSID("44:e4:d9:3f:7f:d0", 93.256f, 2.724f, 3, 13),
				new BSSID("44:e4:d9:41:0c:00", 93.888f, 2.76f, 3, 10),
				new BSSID("c0:62:6b:66:51:50", 73.75f, 1.711f, 3, 9),
				new BSSID("c0:62:6b:e4:85:50", 95.108f, 1.855f, 3, 7))),
				new BSSIDConnected("ff:ff:ff:ff:ff:ff", 0.0f, 0.0f, 1, 0.0f, 0.0f, 1)));
			}
			static{fourthBuilding.datas.add(
				new Data("r4402", 10, "c0:62:6b:e4:85:50", "04:c5:a4:66:74:80", new ArrayList<BSSID>(Arrays.asList(
				new BSSID("04:c5:a4:08:3c:c0", 91.582f, 2.036f, 3, 11),
				new BSSID("04:c5:a4:08:4a:20", 76.041f, 1.467f, 3, 10),
				new BSSID("04:c5:a4:66:70:b0", 93.832f, 1.715f, 3, 7),
				new BSSID("04:c5:a4:66:72:d0", 97.254f, 1.935f, 2, 5),
				new BSSID("04:c5:a4:66:74:80", 69.121f, 3.031f, 3, 11),
				new BSSID("04:c5:a4:66:a7:60", 95.472f, 2.089f, 3, 8),
				new BSSID("04:c5:a4:66:a8:e0", 94.4f, 1.2f, 1, 2),
				new BSSID("c0:62:6b:66:51:50", 94.813f, 2.254f, 3, 8),
				new BSSID("c0:62:6b:e4:80:c0", 96.777f, 2.052f, 3, 7),
				new BSSID("c0:62:6b:e4:85:50", 61.864f, 2.091f, 3, 11))),
				new BSSIDConnected("ff:ff:ff:ff:ff:ff", 0.0f, 0.0f, 1, 0.0f, 0.0f, 1)));
			}
			static{fourthBuilding.datas.add(
				new Data("r4403", 8, "04:c5:a4:80:12:10", "04:c5:a4:66:72:d0", new ArrayList<BSSID>(Arrays.asList(
				new BSSID("04:c5:a4:08:3a:a0", 92.634f, 2.112f, 3, 8),
				new BSSID("04:c5:a4:08:4a:20", 85.387f, 2.699f, 3, 12),
				new BSSID("04:c5:a4:66:70:b0", 91.955f, 3.226f, 2, 9),
				new BSSID("04:c5:a4:66:72:d0", 80.192f, 2.073f, 3, 12),
				new BSSID("04:c5:a4:80:12:10", 69.784f, 1.055f, 3, 8),
				new BSSID("44:e4:d9:3f:7f:d0", 94.044f, 1.693f, 3, 9),
				new BSSID("44:e4:d9:41:0c:00", 93.056f, 2.076f, 3, 10),
				new BSSID("c0:62:6b:66:51:50", 80.528f, 4.605f, 3, 17))),
				new BSSIDConnected("ff:ff:ff:ff:ff:ff", 0.0f, 0.0f, 1, 0.0f, 0.0f, 1)));
			}
			static{fourthBuilding.datas.add(
				new Data("r4404", 14, "04:c5:a4:08:4a:20", "04:c5:a4:66:70:b0", new ArrayList<BSSID>(Arrays.asList(
				new BSSID("04:c5:a4:08:4a:20", 72.502f, 2.541f, 3, 14),
				new BSSID("04:c5:a4:66:32:60", 96.98f, 1.518f, 3, 7),
				new BSSID("04:c5:a4:66:70:b0", 76.268f, 2.857f, 3, 14),
				new BSSID("04:c5:a4:66:72:d0", 97.0f, 0.0f, 1, 1),
				new BSSID("04:c5:a4:66:74:80", 96.302f, 2.463f, 2, 6),
				new BSSID("04:c5:a4:66:94:a0", 96.1f, 1.966f, 2, 6),
				new BSSID("04:c5:a4:66:a8:e0", 90.464f, 2.013f, 3, 11),
				new BSSID("04:c5:a4:80:12:10", 89.278f, 2.422f, 3, 14),
				new BSSID("44:e4:d9:40:07:e0", 84.45f, 2.538f, 3, 12),
				new BSSID("44:e4:d9:85:07:a0", 93.163f, 2.681f, 3, 10),
				new BSSID("44:e4:d9:bc:26:20", 97.616f, 1.43f, 1, 4),
				new BSSID("c0:62:6b:66:51:50", 96.358f, 1.577f, 2, 5),
				new BSSID("c0:62:6b:e4:80:c0", 97.487f, 1.275f, 3, 6),
				new BSSID("c0:62:6b:e4:85:50", 90.406f, 3.118f, 3, 13))),
				new BSSIDConnected("ff:ff:ff:ff:ff:ff", 0.0f, 0.0f, 1, 0.0f, 0.0f, 1)));
			}
			static{fourthBuilding.datas.add(
				new Data("r4406", 8, "04:c5:a4:66:70:b0", "44:e4:d9:40:07:e0", new ArrayList<BSSID>(Arrays.asList(
				new BSSID("04:c5:a4:08:4a:20", 93.758f, 2.949f, 3, 10),
				new BSSID("04:c5:a4:66:32:60", 92.177f, 1.735f, 3, 7),
				new BSSID("04:c5:a4:66:70:b0", 68.396f, 1.393f, 3, 8),
				new BSSID("04:c5:a4:66:94:a0", 91.907f, 2.552f, 3, 10),
				new BSSID("04:c5:a4:80:12:10", 93.436f, 2.176f, 3, 11),
				new BSSID("44:e4:d9:40:07:e0", 80.274f, 2.594f, 3, 15),
				new BSSID("44:e4:d9:bc:26:20", 97.0f, 0.0f, 1, 1),
				new BSSID("c0:62:6b:e4:80:c0", 97.377f, 2.234f, 2, 6))),
				new BSSIDConnected("ff:ff:ff:ff:ff:ff", 0.0f, 0.0f, 1, 0.0f, 0.0f, 1)));
			}
			static{fourthBuilding.datas.add(
				new Data("r4303", 16, "44:e4:d9:41:0c:00", "04:c5:a4:08:3a:a0", new ArrayList<BSSID>(Arrays.asList(
				new BSSID("04:c5:a4:08:26:b0", 84.283f, 1.674f, 1, 5),
				new BSSID("04:c5:a4:08:3a:a0", 70.262f, 5.304f, 2, 16),
				new BSSID("04:c5:a4:08:3c:c0", 89.043f, 2.694f, 1, 3),
				new BSSID("04:c5:a4:08:41:80", 88.975f, 4.939f, 2, 9),
				new BSSID("04:c5:a4:09:70:00", 86.371f, 2.231f, 1, 2),
				new BSSID("04:c5:a4:09:95:d0", 93.534f, 4.262f, 2, 7),
				new BSSID("04:c5:a4:66:32:60", 91.553f, 3.811f, 2, 8),
				new BSSID("04:c5:a4:66:52:80", 88.0f, 0.0f, 1, 1),
				new BSSID("04:c5:a4:66:72:d0", 93.392f, 0.488f, 1, 2),
				new BSSID("04:c5:a4:66:94:a0", 94.478f, 4.451f, 1, 3),
				new BSSID("04:c5:a4:66:9a:e0", 90.0f, 0.0f, 1, 1),
				new BSSID("04:c5:a4:66:a8:e0", 80.305f, 2.802f, 2, 12),
				new BSSID("04:c5:a4:80:12:10", 81.869f, 4.005f, 2, 13),
				new BSSID("44:e4:d9:3f:7f:d0", 76.481f, 4.015f, 2, 15),
				new BSSID("44:e4:d9:41:0c:00", 69.042f, 4.53f, 2, 12),
				new BSSID("c0:62:6b:66:51:50", 88.368f, 4.095f, 2, 10))),
				new BSSIDConnected("ff:ff:ff:ff:ff:ff", 0.0f, 0.0f, 1, 0.0f, 0.0f, 1)));
			}
			static{fourthBuilding.datas.add(
				new Data("r4304", 12, "04:c5:a4:66:a8:e0", "04:c5:a4:66:32:60", new ArrayList<BSSID>(Arrays.asList(
				new BSSID("04:c5:a4:08:3a:a0", 93.686f, 1.772f, 3, 7),
				new BSSID("04:c5:a4:08:3c:c0", 96.319f, 1.588f, 2, 6),
				new BSSID("04:c5:a4:08:41:80", 94.862f, 2.541f, 2, 7),
				new BSSID("04:c5:a4:08:4a:20", 94.531f, 2.336f, 2, 7),
				new BSSID("04:c5:a4:66:32:60", 80.092f, 1.803f, 3, 10),
				new BSSID("04:c5:a4:66:70:b0", 93.638f, 2.371f, 3, 9),
				new BSSID("04:c5:a4:66:94:a0", 87.549f, 3.222f, 3, 13),
				new BSSID("04:c5:a4:66:a7:60", 98.0f, 0.0f, 1, 1),
				new BSSID("04:c5:a4:66:a8:e0", 72.568f, 2.66f, 3, 14),
				new BSSID("44:e4:d9:3f:6f:d0", 95.1f, 1.868f, 2, 7),
				new BSSID("44:e4:d9:41:0c:00", 94.051f, 0.825f, 3, 5),
				new BSSID("44:e4:d9:bc:26:20", 98.0f, 0.0f, 1, 1))),
				new BSSIDConnected("ff:ff:ff:ff:ff:ff", 0.0f, 0.0f, 1, 0.0f, 0.0f, 1)));
			}
			static{fourthBuilding.datas.add(
				new Data("r4305", 10, "04:c5:a4:08:41:80", "04:c5:a4:08:3a:a0", new ArrayList<BSSID>(Arrays.asList(
				new BSSID("04:c5:a4:08:26:b0", 80.36f, 1.582f, 3, 8),
				new BSSID("04:c5:a4:08:3a:a0", 78.312f, 1.054f, 3, 6),
				new BSSID("04:c5:a4:08:41:80", 78.081f, 1.707f, 3, 10),
				new BSSID("04:c5:a4:66:32:60", 84.448f, 1.68f, 3, 10),
				new BSSID("04:c5:a4:66:52:80", 93.056f, 2.057f, 3, 9),
				new BSSID("04:c5:a4:66:94:a0", 91.46f, 2.259f, 3, 10),
				new BSSID("04:c5:a4:66:a8:e0", 97.0f, 0.0f, 1, 1),
				new BSSID("04:c5:a4:80:12:10", 95.818f, 1.466f, 1, 2),
				new BSSID("44:e4:d9:40:4d:70", 96.634f, 1.836f, 3, 5),
				new BSSID("44:e4:d9:41:0c:00", 94.516f, 1.406f, 3, 5))),
				new BSSIDConnected("ff:ff:ff:ff:ff:ff", 0.0f, 0.0f, 1, 0.0f, 0.0f, 1)));
			}
			static{fourthBuilding.datas.add(
				new Data("r4306", 6, "04:c5:a4:66:94:a0", "04:c5:a4:66:32:60", new ArrayList<BSSID>(Arrays.asList(
				new BSSID("04:c5:a4:08:26:b0", 94.972f, 2.641f, 3, 9),
				new BSSID("04:c5:a4:08:41:80", 88.476f, 1.808f, 3, 10),
				new BSSID("04:c5:a4:09:85:e0", 92.42f, 2.513f, 3, 10),
				new BSSID("04:c5:a4:66:32:60", 86.518f, 2.156f, 3, 11),
				new BSSID("04:c5:a4:66:94:a0", 83.672f, 2.582f, 3, 11),
				new BSSID("44:e4:d9:40:35:b0", 93.205f, 2.087f, 3, 9))),
				new BSSIDConnected("ff:ff:ff:ff:ff:ff", 0.0f, 0.0f, 1, 0.0f, 0.0f, 1)));
			}
			//existingBSSIDs
			static{
				fourthBuilding.existingBSSIDs = new ArrayList<String>(Arrays.asList(
					"04:c5:a4:08:26:b0",
					"04:c5:a4:08:3a:a0",
					"04:c5:a4:08:3c:c0",
					"04:c5:a4:08:41:80",
					"04:c5:a4:08:4a:20",
					"04:c5:a4:09:70:00",
					"04:c5:a4:09:85:e0",
					"04:c5:a4:09:95:d0",
					"04:c5:a4:66:32:60",
					"04:c5:a4:66:52:80",
					"04:c5:a4:66:70:b0",
					"04:c5:a4:66:72:d0",
					"04:c5:a4:66:74:80",
					"04:c5:a4:66:94:a0",
					"04:c5:a4:66:9a:e0",
					"04:c5:a4:66:a7:60",
					"04:c5:a4:66:a8:e0",
					"04:c5:a4:80:12:10",
					"44:e4:d9:3f:6f:d0",
					"44:e4:d9:3f:7f:d0",
					"44:e4:d9:40:07:e0",
					"44:e4:d9:40:35:b0",
					"44:e4:d9:40:4d:70",
					"44:e4:d9:41:0c:00",
					"44:e4:d9:85:07:a0",
					"44:e4:d9:bc:26:20",
					"c0:62:6b:66:51:50",
					"c0:62:6b:e4:80:c0",
					"c0:62:6b:e4:85:50"));
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
				"10:8c:cf:11:66:70",
				"10:8c:cf:11:67:50",
				"10:8c:cf:10:86:d0",
				"10:8c:cf:10:95:80", 
				"10:8c:cf:11:72:50"));
		buildings.add(thirdBuilding);
	}
	static
	{
		//library.existingBSSIDs = new ArrayList<String>(Arrays.asList("sd","dzsf"));
		buildings.add(thirdBuilding);
		buildings.add(fourthBuilding);
		
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