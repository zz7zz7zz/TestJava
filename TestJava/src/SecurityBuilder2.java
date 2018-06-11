import java.util.Random;

/**
 * 加解密算法
 * @author long
 *
 */
public final class SecurityBuilder2 {
	
	private static final String SECRET = ""
			+ "<<浪淘沙>> 李煜"
			+ "帘外雨潺潺，春意阑珊，罗衾不耐五更寒。梦里不知身是客，一晌贪欢。"
			+ "独自莫凭栏，无限江山，别时容易见时难。流水落花春去也，天上人间。";

	private static final void generate2(String key){
		
		Random mRandom = new Random();
		mRandom.setSeed(key.hashCode());
		
		int count = 0;
		byte[] flag = new byte[256];//标记
		byte[] send_mapping = new byte[256];
		byte[] recv_mapping = new byte[256];

		byte min = Byte.MIN_VALUE;
		byte max = Byte.MAX_VALUE;
		for(byte i = Byte.MIN_VALUE;i<= Byte.MAX_VALUE;i++){
			
			//1.找出一个没有被使用的数据
			byte value;
			while(true){
				value = (byte)(min+mRandom.nextInt(max - min + 1));
				if(flag[value + 128] == 1){
					continue;
				}else{
					flag[value + 128] = 1;
					break;
				}
			}
			
			//2.产生映射唤醒
			send_mapping[i + 128]= value;
			recv_mapping[value + 128]= i;
			
			count++;
			if(count >= 256){
				break;
			}
		}
	
		print(send_mapping);
		print(recv_mapping);
	}

	private static void print(byte[] data){
		System.out.print("\r\n");
		for(int i = 0;i<data.length;i++){
			if(i%16 == 0){
				System.out.println();
			}
			System.out.print(data[i]+",");
		}
		System.out.print("\r\n");
	}
	 
	//---------------------------------------------------
	public static void main(String[] args) {
		generate2(SECRET);
	}

}
